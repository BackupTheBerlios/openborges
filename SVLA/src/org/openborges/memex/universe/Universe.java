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

/**
 * @author <a href="mailto:chris@openborges.org">chris</a>
 * @version $Id: Universe.java,v 1.2 2008/09/02 17:21:49 duf Exp $
 *
 */
public interface Universe extends Sem {

	/**
	 * @return
	 */
	public Metaphor getMetaphor();
	
	/**
	 * @param metaphor
	 */
	public void setMetaphor(Metaphor metaphor);
	
	/**
	 * @return
	 */
	public Measure getMeasure();
	
	/**
	 * @param measure
	 */
	public void setMeasure(Measure measure);
	
	/**
	 * @return
	 */
	public Sem resolve(String uri);
	
	/**
	 * @param restriction
	 * @return
	 */
	public List<Sem> resolve(Taxon restriction);
	
	/**
	 * @param center
	 * @param radius
	 * @return
	 */
	public List<Sem> resolve(Sem center, double radius);
	
	/**
	 * @param uri
	 * @param label
	 * @param description
	 * @param lang
	 * @param taxonomy
	 * @param state
	 * @param behavior
	 * @return
	 */
	public Sem createSem(String uri,
			String label,
			String description,
			String lang,
			Taxon[] taxonomy,
			Characteristic[] state,
			Behavior[] behavior);

	/**
	 * @param sem
	 * @param characteristic
	 * @param value
	 */
	public void setCharacteristic(Sem sem, 
			CharacteristicSpec characteristic,
			Object value);

	/**
	 * @param source
	 * @param destination
	 * @param behavior
	 * @return
	 */
	public Behavior setBehavior(Sem source,
			Sem destination,
			BehaviorSpec behavior);
}