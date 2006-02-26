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

/** This interface specifies a base representation for a <i>valuated behavior</i>.
 * <p>A valuated behavior associates a definition to a source and a destination,
 * defining an edge in the model graph.</p>
 * 
 * @author <a href="mailto:chris@open-memex.org">chris</a>
 * @version $Id: Behavior.java,v 1.1 2006/02/26 17:59:02 duf Exp $
 *
 */
public interface Behavior {

	/** Accessor to the valuated behavior <i>definition</i>.
	 * 
	 * @return The specification of the valuated behavior.
	 */
	public BehaviorSpec getDefinition() ;
	
	/** Accessor to this behavior source.
	 * 
	 * @return The sem source of this behavior.
	 */
	public Sem getSource();
	
	/** Accessor to this valuated behavior <i>destination</i>.
	 * 
	 * @return The sem source of this valuated behavior.
	 */
	public Sem getDestination() ;
	
}
