/**
 *  OpenBorges.org
 *	Copyright 2008 Christophe Dufaza - chris@openborges.org
 *  
 * 	This program is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; either version 2 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program; if not, write to the Free Software
 *  Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 * 
 */

package org.openborges.memex.ubus.impl.test;

import java.io.File;
import java.util.Properties;

import org.openborges.memex.ubus.IUniverseFactory;
import org.openborges.memex.ubus.file.FileUniverseFactory;
import org.openborges.memex.ubus.file.FileUniverseFactorySpec;
import org.openborges.memex.ubus.file.IFileUniverse;
import org.openborges.memex.ubus.impl.LocalBusImpl;
import org.openborges.memex.universe.Sem;
import org.openborges.memex.universe.Universe;
import org.testng.annotations.Test;

/**
 * @author <a href="mailto:chris@openborges.org">chris</a>
 * @version $Id: LocalBusImplTest.java,v 1.1 2008/09/02 17:21:47 duf Exp $
 *
 */
public class LocalBusImplTest {

	private static final String DEFAULT_UNIVERSE_URI= 
		"http://openborges.org/ns/universe/local/default";
	
	private static final String REPOSITORY_BASE="tmp-universe-factory";
	
	/**
	 * 
	 */
	@Test(groups= {"UBUS.local.factory"}, 
			description="Creates default universe factory and checks its consistency")
	public void getDefaultUniverseFactory()
	{
		IUniverseFactory universeFactory
			=LocalBusImpl.getInstance().getUniverseFactory();
		assert(universeFactory != null);
		
		Universe universe= 
			universeFactory.createUniverse(DEFAULT_UNIVERSE_URI);
		assert(universe != null);		
		assert(DEFAULT_UNIVERSE_URI.equals(universe.getURI()));
		
		Sem sem= universe.resolve(DEFAULT_UNIVERSE_URI);
		assert(sem != null);
		assert(DEFAULT_UNIVERSE_URI.equals(sem.getURI()));
		assert(sem instanceof Universe);
	}
	
	@Test(groups= {"UBUS.local.factory"}, 
			description="Creates file-based universe factory and checks its consistency")
	public void createFileUniverseFactory()
	{
		clean_repository_base();
		
		Properties props= new Properties();
		props.setProperty(FileUniverseFactorySpec.REPOSITORY_BASE, REPOSITORY_BASE);
		FileUniverseFactorySpec factorySpec= new FileUniverseFactorySpec(props);
		
		IUniverseFactory universeFactory
			=LocalBusImpl.getInstance().getUniverseFactory(factorySpec);
		assert(universeFactory != null);
		assert(universeFactory instanceof FileUniverseFactory);
		
		Universe universe= 
			universeFactory.createUniverse(DEFAULT_UNIVERSE_URI);
		assert(universe != null);		
		assert(universe instanceof IFileUniverse);		
		assert(DEFAULT_UNIVERSE_URI.equals(universe.getURI()));
		
		Sem sem= universe.resolve(DEFAULT_UNIVERSE_URI);
		assert(sem != null);
		assert(sem instanceof Universe);
		assert(sem instanceof IFileUniverse);		
		assert(DEFAULT_UNIVERSE_URI.equals(sem.getURI()));
		
		IFileUniverse fu= (IFileUniverse) universe;
		fu.save();
	}
	
	@Test(groups= {"UBUS.local.factory"}, 
			description="A file factory URI-resolve a previously saved universe",
			dependsOnMethods={ "createFileUniverseFactory" })
	public void getUniverseFromFileMap()
	{
		Properties props= new Properties();
		props.setProperty(FileUniverseFactorySpec.REPOSITORY_BASE, REPOSITORY_BASE);
		FileUniverseFactorySpec factorySpec= new FileUniverseFactorySpec(props);
		
		IUniverseFactory universeFactory
			=LocalBusImpl.getInstance().getUniverseFactory(factorySpec);
		assert(universeFactory != null);
		assert(universeFactory instanceof FileUniverseFactory);
		
		Universe universe= 
			universeFactory.getUniverse(DEFAULT_UNIVERSE_URI);
		assert(universe != null);		
		assert(universe instanceof IFileUniverse);		
		assert(DEFAULT_UNIVERSE_URI.equals(universe.getURI()));
	}
	
	private void clean_repository_base()
	{
		File base= new File(REPOSITORY_BASE);
		
		if (base.exists())
		{
			File[] files= base.listFiles();
			
			for(File f : files)
			{
				f.delete();
			}
		
			base.delete();
		}
	}
}
