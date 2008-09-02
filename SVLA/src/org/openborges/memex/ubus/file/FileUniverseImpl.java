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

package org.openborges.memex.ubus.file;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.openborges.memex.universe.impl.UniverseImplBase;

import com.hp.hpl.jena.ontology.OntResource;

/**
 * @author <a href="mailto:chris@openborges.org">chris</a>
 * @version $Id: FileUniverseImpl.java,v 1.1 2008/09/02 17:21:49 duf Exp $
 *
 */
public class FileUniverseImpl extends UniverseImplBase implements IFileUniverse {

	///////////////////////////////////////////////////////////////////////////
	//
	// STATE
	//
	
	private File _file;

	
	///////////////////////////////////////////////////////////////////////////
	//
	// CONSTRUCTORS
	//
	
	/**
	 * @param model
	 * @param uri
	 * @param label
	 * @param description
	 * @param lang
	 */
	public FileUniverseImpl(OntResource resource, File file)
	{
		super(resource);
		_file= file;
	}

	
	///////////////////////////////////////////////////////////////////////////
	//
	// API [org.openborges.memex.ubus.file.IFileUniverse]
	//

	/* (non-Javadoc)
	 * @see org.openborges.memex.ubus.file.IFileUniverse#getFile()
	 */
	public File getFile() {
		return _file;
	}
	
	/* (non-Javadoc)
	 * @see org.openborges.memex.ubus.file.IFileUniverse#save()
	 */
	public void save() {
		
		try
		{
			_resource.getOntModel().setNsPrefix("ubus", FileUniverseMetaphor.NS_URI);
			_resource.getOntModel().write(new FileOutputStream(_file));
		}
		catch (FileNotFoundException e)
		{
			throw new IllegalArgumentException(_file.getAbsolutePath());
		}

	}

}
