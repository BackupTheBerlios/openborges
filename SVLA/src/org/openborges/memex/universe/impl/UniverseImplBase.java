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

package org.openborges.memex.universe.impl;

import java.util.HashMap;
import java.util.List;

import org.openborges.memex.universe.Behavior;
import org.openborges.memex.universe.BehaviorSpec;
import org.openborges.memex.universe.Characteristic;
import org.openborges.memex.universe.CharacteristicSpec;
import org.openborges.memex.universe.Measure;
import org.openborges.memex.universe.Metaphor;
import org.openborges.memex.universe.Sem;
import org.openborges.memex.universe.Taxon;
import org.openborges.memex.universe.Universe;

import com.hp.hpl.jena.ontology.DatatypeProperty;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntResource;

/**
 * @author <a href="mailto:chris@openborges.org">chris</a>
 * @version $Id: UniverseImplBase.java,v 1.1 2008/09/02 17:21:49 duf Exp $
 *
 */
public class UniverseImplBase extends SemImplBase implements Universe {
	
	///////////////////////////////////////////////////////////////////////////
	//
	// STATE
	//
	
	protected HashMap<String,Sem> _semMap= new HashMap<String,Sem>();
	protected OntModel _universeModel;
	
	
	///////////////////////////////////////////////////////////////////////////
	//
	// CONSTRUCTORS
	//
	
	/**
	 * @param model
	 * @param uri
	 * @param label
	 * @param description
	 * @param lang
	 * @param specification
	 */
	public UniverseImplBase(OntResource resource) {
		super(resource);
		
		_semMap.put(resource.getURI(), this);
		_universeModel= resource.getOntModel();
	}

	///////////////////////////////////////////////////////////////////////////
	//
	// API [org.openborges.memex.universe.Universe]
	//
	
	/* (non-Javadoc)
	 * @see org.openborges.memex.universe.Universe#createSem(java.lang.String, java.lang.String, java.lang.String, java.lang.String, org.openborges.memex.universe.Taxon[], org.openborges.memex.universe.Characteristic[], org.openborges.memex.universe.Behavior[])
	 */
	public Sem createSem(String uri, String label, String description,
			String lang, Taxon[] taxonomy, Characteristic[] state,
			Behavior[] behavior) {
		
		OntResource resource= _universeModel.createOntResource(uri);
		
		resource.addLabel(label, lang);
		resource.addComment(description, lang);
		resource.addIsDefinedBy(_universeModel.getResource(getURI()));

//TODO set taxonymy, characteristics, and behavior
		
		SemImplBase sem= new SemImplBase(resource);
		_semMap.put(uri, sem);
		
		return sem;
	}

	/* (non-Javadoc)
	 * @see org.openborges.memex.universe.Universe#resolve(java.lang.String)
	 */
	public Sem resolve(String uri) {
		if (_semMap.containsKey(uri))
		{
			return _semMap.get(uri);
		}
		
		if (_universeModel.containsResource(_universeModel.getResource(uri)) )
		{
			OntResource res= _universeModel.getOntResource(uri);
			Sem sem= new SemImplBase(res);
			_semMap.put(uri, sem);
			
			return sem;
		}
		
		return null;
	}
	
	/* (non-Javadoc)
	 * @see org.openborges.memex.universe.Universe#setCharacteristic(org.openborges.memex.universe.Sem, org.openborges.memex.universe.CharacteristicSpec, java.lang.Object)
	 */
	public void setCharacteristic(Sem sem,
			CharacteristicSpec characteristicSpec, Object value) {
		
		if (_resource.getOntModel().getDatatypeProperty(characteristicSpec.getURI()) != null)
		{
			DatatypeProperty characteristic= _resource.getOntModel().getDatatypeProperty(characteristicSpec.getURI());
			
			OntResource subject= _universeModel.getOntResource(sem.getURI());
			if (subject != null)
			{
				_universeModel.createLiteralStatement(subject, characteristic, value);
			}
			else
			{
				throw new IllegalArgumentException(sem.getURI());
			}
		}
		else
		{
			throw new IllegalArgumentException(characteristicSpec.getURI());
		}		
	}
	
	/* (non-Javadoc)
	 * @see org.openborges.memex.universe.Universe#getMeasure()
	 */
	public Measure getMeasure() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.openborges.memex.universe.Universe#getMetaphor()
	 */
	public Metaphor getMetaphor() {
		// TODO Auto-generated method stub
		return null;
	}


	/* (non-Javadoc)
	 * @see org.openborges.memex.universe.Universe#resolve(org.openborges.memex.universe.Taxon)
	 */
	public List<Sem> resolve(Taxon restriction) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.openborges.memex.universe.Universe#resolve(org.openborges.memex.universe.Sem, double)
	 */
	public List<Sem> resolve(Sem center, double radius) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.openborges.memex.universe.Universe#setBehavior(org.openborges.memex.universe.Sem, org.openborges.memex.universe.Sem, org.openborges.memex.universe.BehaviorSpec)
	 */
	public Behavior setBehavior(Sem source, Sem destination,
			BehaviorSpec behavior) {
		// TODO Auto-generated method stub
		return null;
	}


	/* (non-Javadoc)
	 * @see org.openborges.memex.universe.Universe#setMeasure(org.openborges.memex.universe.Measure)
	 */
	public void setMeasure(Measure measure) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.openborges.memex.universe.Universe#setMetaphor(org.openborges.memex.universe.Metaphor)
	 */
	public void setMetaphor(Metaphor metaphor) {
		// TODO Auto-generated method stub

	}

}
