/**
 *  OpenMemex.org
 *	Copyright 2005 Christophe Dufaza - chris@open-memex.org
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
package org.openmemex.svla.model;

/** This interface specifies a base representation for a sem <i>characteristic</i>.
 * <p>Rem : this does not specify how model value types map to implementation datatypes.</p> 
 * 
 * @author <a href="mailto:chris@open-memex.org">chris</a>
 *
 */
public interface CharacteristicSpec extends Sem {

	/** The <i>model type</i> this characteristic should be evaluated to.
	 * 
	 * @return An URI.
	 */
	public String getValueType() ;

	/** Accessor to the characteristics known as a generalization (hypernymy) for
	 * this one.
	 * 
	 * @param strict Determine wether to recursively include (<code>false</code>), or not (<code>true</code>),
	 * this hypernym generalizations.
	 * @return An array containing the chractaeristics known as generalizations of this one.
	 */
	public CharacteristicSpec[] getGeneralization(boolean strict) ;

	/** Accessor to the characteristics known as a specialization (hypornymy) for
	 * this one.
	 * 
	 * @param strict Determine wether to recursively include (<code>false</code>), or not (<code>true</code>),
	 * this hypernym specializations.
	 * @return An array containing the chractaeristics known as specializations of this one.
	 */
	public CharacteristicSpec[] getSpecialization(boolean strict) ;

}
