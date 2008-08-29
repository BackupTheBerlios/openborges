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

/** A <i>metaphor</i> defines a set of taxon, characteristics, and behaviors.
 * 
 * @author <a href="mailto:chris@openborges.org">chris</a>
 * @version $Id: Metaphor.java,v 1.1 2008/08/29 16:26:40 duf Exp $
 *
 */
public interface Metaphor extends Sem {

	/**
	 * @return
	 */
	public List<Taxon> getTaxonomy();
	
	/**
	 * @param taxonUri
	 * @return
	 */
	public Taxon resolveTaxon(String taxonUri);
	
	/**
	 * @param uri
	 * @param label
	 * @param description
	 * @param lang
	 * @param hypernyms
	 * @return
	 */
	public Taxon createTaxon(String uri, 
			String label,
			String description,
			String lang,
			Taxon[] hypernyms);	
	
	/**
	 * @return
	 */
	public List<CharacteristicSpec> getCharacteristics();
	
	/**
	 * @param characteristicUri
	 * @return
	 */
	public CharacteristicSpec resolveCharacteristic(String characteristicUri);
	
	/**
	 * @param uri
	 * @param label
	 * @param description
	 * @param lang
	 * @param hypernyms
	 * @return
	 */
	public CharacteristicSpec createCharacteristic(String uri, 
			String label,
			String description,
			String lang,
			CharacteristicSpec[] hypernyms);
	/**
	 * @return
	 */
	public List<BehaviorSpec> getBehaviors();
	
	/**
	 * @param behaviorUri
	 * @return
	 */
	public BehaviorSpec resolveBehavior(String behaviorUri);
	
	/**
	 * @param uri
	 * @param label
	 * @param description
	 * @param lang
	 * @param hypernyms
	 * @return
	 */
	public BehaviorSpec createBehavior(String uri, 
			String label,
			String description,
			String lang,
			BehaviorSpec[] hypernyms);
}
