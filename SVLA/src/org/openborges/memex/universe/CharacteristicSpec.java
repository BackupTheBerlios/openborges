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
 * @version $Id: CharacteristicSpec.java,v 1.2 2008/08/29 16:26:40 duf Exp $ 
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
	 * <p>A <i>generalization</i> is defined by: a characteristic <code>C1</code> is a generalization of <code>C2</code>,
	 * whenever the model states <code>{s , C1, v} => {s , C2, v}</code>.
	 * 
	 * @param strict Determine wether to recursively include (<code>false</code>), or not (<code>true</code>),
	 * the hypernyms.
	 * @return A list containing the characteristics known as generalizations of this one.
	 */
	public List<CharacteristicSpec> getGeneralization(boolean strict) ;

	/** Accessor to the characteristics known as a specialization (hyponymy) for
	 * this one.
	 * 
	 * <p>A <i>specialization</i> : a characteristic <code>C1</code> is a specialization of <code>C2</code>,
	 * whenever the model states <code>{s , C2, v} => {s , C1, v}</code>.
	 * 
	 * @param strict Determine wether to recursively include (<code>false</code>), or not (<code>true</code>),
	 * the hyponyms.
	 * @return A list containing the characteristics known as specializations of this one.
	 */
	public List<CharacteristicSpec> getSpecialization(boolean strict) ;
}
