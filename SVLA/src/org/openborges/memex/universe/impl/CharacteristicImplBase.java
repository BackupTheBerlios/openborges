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

package org.openborges.memex.universe.impl;

import org.openborges.memex.universe.Characteristic;

/**
 * @author <a href="mailto:chris@openborges.org">chris</a>
 * @version $Id: CharacteristicImplBase.java,v 1.1 2008/09/02 17:21:49 duf Exp $
 *
 */
public class CharacteristicImplBase implements Characteristic {

	///////////////////////////////////////////////////////////////////////////
	//
	// STATE
	//

	protected String propertyUri;
	protected Object value;
	
	
	///////////////////////////////////////////////////////////////////////////
	//
	// CONSTRUCTORS
	//
	
	/**
	 * 
	 */
	public CharacteristicImplBase(String propertyUri, Object value) {
		this.propertyUri= propertyUri;
		this.value= value;
	}

	
	///////////////////////////////////////////////////////////////////////////
	//
	// API [org.openborges.memex.universe.Characteristic]
	//
	
	/* (non-Javadoc)
	 * @see org.openborges.memex.universe.Characteristic#getDefinition()
	 */
	public String getDefinition() {
		return this.propertyUri;
	}

	/* (non-Javadoc)
	 * @see org.openborges.memex.universe.Characteristic#getValue()
	 */
	public Object getValue() {
		return this.value;
	}

}
