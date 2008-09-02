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

import java.util.Properties;

import org.openborges.memex.ubus.IFactorySpec;

/**
 * @author <a href="mailto:chris@openborges.org">chris</a>
 * @version $Id: FileUniverseFactorySpec.java,v 1.1 2008/09/02 17:21:49 duf Exp $
 *
 */
public class FileUniverseFactorySpec implements IFactorySpec {

	/**
	 * Absolute or relative path to the universe repository.
	 */
	public static final String REPOSITORY_BASE="org.openborges.memex.ubus.file.REPOSITORY_BASE";
	
	/**
	 * 
	 */
	public static final String FILE_CHARACTERISTIC_URI= "http://openborges.org/ns/memex/ubus/file#FileCharacteristic";
	
	///////////////////////////////////////////////////////////////////////////
	//
	// STATE
	//
	
	private Properties _properties;

	
	///////////////////////////////////////////////////////////////////////////
	//
	// CONSTRUCTORS
	//
	
	/**
	 * @param properties
	 */
	public FileUniverseFactorySpec(Properties properties)
	{
		_properties= properties;
	}
	
	
	///////////////////////////////////////////////////////////////////////////
	//
	// API [org.openborges.memex.ubus.IFactorySpec]
	//
	
	/* (non-Javadoc)
	 * @see org.openborges.memex.ubus.IFactorySpec#getProperties()
	 */
	public Properties getProperties() {
		return _properties;
	}

	/* (non-Javadoc)
	 * @see org.openborges.memex.ubus.IFactorySpec#getProperty(java.lang.String)
	 */
	public String getProperty(String key) {
		return _properties.getProperty(key);
	}

}
