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

package org.openborges.memex.ubus.impl;

import org.openborges.memex.ubus.IFactorySpec;
import org.openborges.memex.ubus.IUniverseBus;
import org.openborges.memex.ubus.IUniverseFactory;
import org.openborges.memex.ubus.file.FileUniverseFactory;
import org.openborges.memex.ubus.file.FileUniverseFactorySpec;
import org.openborges.memex.ubus.inmem.InMemoryUniverseFactory;

/**
 * @author <a href="mailto:chris@openborges.org">chris</a>
 * @version $Id: LocalBusImpl.java,v 1.1 2008/09/02 17:21:49 duf Exp $
 *
 */
public class LocalBusImpl implements IUniverseBus {

	///////////////////////////////////////////////////////////////////////////
	//
	// STATE
	//
	
	private static LocalBusImpl _instance= null;
	
	
	///////////////////////////////////////////////////////////////////////////
	//
	// CONSTRUCTORS
	//
	
	private LocalBusImpl(){};

	/**
	 * @return
	 */
	public static IUniverseBus getInstance()
	{
		if (_instance == null)
		{
			_instance= new LocalBusImpl();
		}
		
		return _instance;
	}
	
	
	///////////////////////////////////////////////////////////////////////////
	//
	// API [org.openborges.memex.ubus.IUniverseBus]
	//
	
	/* (non-Javadoc)
	 * @see org.openborges.memex.ubus.IUniverseBus#getUniverseFactory()
	 */
	public IUniverseFactory getUniverseFactory() {
		return new InMemoryUniverseFactory();
	}

	/* (non-Javadoc)
	 * @see org.openborges.memex.ubus.IUniverseBus#getUniverseFactory(java.lang.String)
	 */
	public IUniverseFactory getUniverseFactory(IFactorySpec factorySpec) {
		if (factorySpec instanceof FileUniverseFactorySpec)
		{
			return new FileUniverseFactory((FileUniverseFactorySpec) factorySpec);
		}
		
		return null;
	}

	/* (non-Javadoc)
	 * @see org.openborges.memex.ubus.IUniverseBus#getMeasureFactory()
	 */
	public IUniverseFactory getMeasureFactory() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.openborges.memex.ubus.IUniverseBus#getMeasureFactory(org.openborges.memex.ubus.IFactorySpec)
	 */
	public IUniverseFactory getMeasureFactory(IFactorySpec factorySpec) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/* (non-Javadoc)
	 * @see org.openborges.memex.ubus.IUniverseBus#getMetaphorFactory()
	 */
	public IUniverseFactory getMetaphorFactory() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.openborges.memex.ubus.IUniverseBus#getMetaphorFactory(org.openborges.memex.ubus.IFactorySpec)
	 */
	public IUniverseFactory getMetaphorFactory(IFactorySpec factorySpec) {
		// TODO Auto-generated method stub
		return null;
	}

}
