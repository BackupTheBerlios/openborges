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
package org.openborges.memex.universe;

import java.util.List;

/** This interface specifies a base representation for a sem <i>characteristic</i>.
 * <p>Rem : this does not specify how model value types map to implementation datatypes.</p> 
 * 
 * @author <a href="mailto:chris@openborges.org">chris</a>
 * @version $Id: CharacteristicSpec.java,v 1.3 2008/09/02 17:21:49 duf Exp $ 
 */
public interface CharacteristicSpec extends Sem {

	/** The <i>model type</i> this characteristic should be evaluated to.
	 * 
	 * @return An URI.
	 */
	public List<String> getValueType() ;

	/**
	 * @param datatypeUri
	 */
	public void addDatatype(String datatypeUri);

}
