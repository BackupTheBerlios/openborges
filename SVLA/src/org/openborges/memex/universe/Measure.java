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

/**
 * @author <a href="mailto:chris@openborges.org">chris</a>
 * @version $Id: Measure.java,v 1.1 2008/08/29 16:26:40 duf Exp $
 *
 */
public interface Measure extends Sem {

	/**
	 * @param s1
	 * @param s2
	 * @return
	 */
	public double measure(Sem s1, Sem s2);
	
	/**
	 * @param behavior
	 * @return
	 */
	public double measure(Behavior behavior);
	
}
