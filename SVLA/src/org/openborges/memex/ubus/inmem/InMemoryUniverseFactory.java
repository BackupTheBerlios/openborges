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

package org.openborges.memex.ubus.inmem;

import java.util.HashMap;

import org.openborges.memex.ubus.IUniverseFactory;
import org.openborges.memex.universe.Universe;
import org.openborges.memex.universe.impl.UniverseImplBase;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.ontology.OntResource;
import com.hp.hpl.jena.rdf.model.ModelFactory;

/**
 * @author <a href="mailto:chris@openborges.org">chris</a>
 * @version $Id: InMemoryUniverseFactory.java,v 1.1 2008/09/02 17:21:49 duf Exp $
 *
 */
public class InMemoryUniverseFactory implements IUniverseFactory {
	
	///////////////////////////////////////////////////////////////////////////
	//
	// STATE
	//

	private HashMap<String,Universe> _universeMap= new HashMap<String,Universe>();
	
	
	///////////////////////////////////////////////////////////////////////////
	//
	// API [org.openborges.memex.ubus.IUniverseFactory]
	//
	
	/* (non-Javadoc)
	 * @see org.openborges.memex.ubus.IUniverseFactory#createUniverse(java.lang.String)
	 */
	public Universe createUniverse(String universeUri) {
		if (! _universeMap.containsKey(universeUri))
		{
			OntModel model= ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM_RDFS_INF);
			OntResource res= model.createOntResource(universeUri);
			
//TODO set a user defined label			
			res.addLabel(res.getURI(), "EN");
			
			_universeMap.put(universeUri, new UniverseImplBase(res));
		}
		return _universeMap.get(universeUri);
	}
	
	/* (non-Javadoc)
	 * @see org.openborges.memex.ubus.IUniverseFactory#getUniverse(java.lang.String)
	 */
	public Universe getUniverse(String universeUri) {
		return _universeMap.get(universeUri);
	}

}
