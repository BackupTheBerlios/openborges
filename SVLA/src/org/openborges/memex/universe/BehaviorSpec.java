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

/** This interface specifies a base representation for a sem <i>behavior</i>.
 * <p>A behavior is defined through :
 * <ul>
 * <li>A <i>domain</i> : determines the sem population that may appears as a source
 * for this behavior.</li>
 * <li>A <i>range</i> : determines the sem population that may appears as a destination
 * for this behavior.</li>
 * <li>A <i>generalization</i> : a behavior <code>B1</code> is a generalization of <code>B2</code>,
 * whenever the model states <code>{s1 , B1, s2} => {s1 , B2, s2}</code>.</li>
 * <li>A <i>specialization</i> : a behavior <code>B1</code> is a specialization of <code>B2</code>,
 * whenever the model states <code>{s1 , B2, s2} => {s1 , B1, s2}</code>.</li>
 * </ul></p>
 * 
 * @author <a href="mailto:chris@openborges.org">chris</a>
 * @version $Id: BehaviorSpec.java,v 1.1 2008/08/29 14:21:32 duf Exp $ 
 */
public interface BehaviorSpec extends Sem {

	/** Accessor to this behavior <i>domain</i>.
	 * 
	 * @param strict Determine wether to recursively include (<code>false</code>), or not (<code>true</code>),
	 * the domain taxon hyponyms.
	 * @return An array containing the taxon the population of which is known as a possible source
	 * for this behavior.
	 */
	public List<Taxon> getDomain(boolean strict);

	/** Accessor to this behavior <i>range</i>.
	 * 
	 * @param strict Determine wether to recursively include (<code>false</code>), or not (<code>true</code>),
	 * the range taxon hyponyms.
	 * @return A list containing the taxon the population of which is known as a possible destination
	 * for this behavior.
	 */
	public List<Taxon> getRange(boolean strict);
	
	/** Accessor to this behavior <i>generalization</i>.
	 * 
	 * @param strict Determine wether to recursively include (<code>false</code>), or not (<code>true</code>),
	 * the behavior generalizations.
	 * @return A list containing the behaviors known as generalization of this behavior.
	 */
	public List<BehaviorSpec> getGeneralization(boolean strict) ;

	/** Accessor to this behavior <i>specialization</i>.
	 * 
	 * @param strict Determine wether to recursively include (<code>false</code>), or not (<code>true</code>),
	 * the behavior specializations.
	 * @return A list containing the behaviors known as specialization of this behavior.
	 */
	public List<BehaviorSpec> getSpecialization(boolean strict) ;
}
