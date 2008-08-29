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


/** This interface specifies a base representation for
 * a systematic unit, or <i>taxon</i>.
 * 
 * @author <a href="mailto:chris@openborges.org">chris</a>
 * @version $Id: Taxon.java,v 1.1 2008/08/29 14:21:32 duf Exp $ 
 */
public interface Taxon extends Sem {

	/** Accessor to this taxon hypernyms.
	 * 
	 * @param strict Determines wether to recursively include (<code>false</code>), 
	 * or not (<code>true</code>), this taxon hypernyms.
	 * @return A list containing all taxon known as hypernym of this one		.
	 */
	public List<Taxon> getHypernymy(boolean strict) ;
	
	/** Accessor to this taxon hyponyms.
	 * 
	 * @param strict Determines wether to recursively include (<code>false</code>), 
	 * or not (<code>true</code>), this taxon hyponyms.
	 * @return A list containing all taxon known as hyponym of this one.
	 */
	public List<Taxon> getHyponymy(boolean strict) ;

	/** Accessor to this taxon <i>population</i>.
	 * 
	 * @return A list containing all sem known as member of this
	 * systematic unit.
	 */
	public List<Sem> getPopulation() ;
	
	/** Accessor to the characteristics known to apply to this taxon.
	 * 
	 * @param strict Determines wether to recursively include (<code>false</code>), 
	 * or not (<code>true</code>), characteristics that apply to hypernyms.
	 * @return A list containing defined characteristics. 
	 */
	public List<CharacteristicSpec> getDefinedCharacteristics(boolean strict) ;
	
	/** Accessor to the behaviors known to admit this taxon in their domain.
	 * 
	 * @param strict Determines wether to recursively include (<code>false</code>), 
	 * or not (<code>true</code>), behaviors that are defined for this taxon hypernyms.
	 * @return A list containing defined behaviors. 
	 */
	public List<BehaviorSpec> getDefinedBehaviors(boolean strict) ;
	
}
