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

import java.util.ArrayList;
import java.util.List;

import org.openborges.memex.universe.CharacteristicSpec;

import com.hp.hpl.jena.ontology.DataRange;
import com.hp.hpl.jena.ontology.DatatypeProperty;
import com.hp.hpl.jena.ontology.OntResource;
import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.RDFList;

/**
 * @author <a href="mailto:chris@openborges.org">chris</a>
 * @version $Id: CharacteristicSpecImplBase.java,v 1.1 2008/09/02 17:21:49 duf Exp $
 *
 */
public class CharacteristicSpecImplBase extends SemImplBase 
	implements CharacteristicSpec {

	///////////////////////////////////////////////////////////////////////////
	//
	// STATE
	//
	
	protected DatatypeProperty _characteristic;
	
	
	///////////////////////////////////////////////////////////////////////////
	//
	// CONSTRUCTORS
	//
	
	/**
	 * @param resource
	 */
	public CharacteristicSpecImplBase(OntResource resource) {
		super(resource);
		
		if (resource.canAs(DatatypeProperty.class))
		{
			_characteristic= (DatatypeProperty) _resource.asDatatypeProperty();	
		}
		else {
			throw new IllegalArgumentException(resource.getRDFType(true).getURI());
		}
		
	}

	
	///////////////////////////////////////////////////////////////////////////
	//
	// API [org.openborges.memex.universe.CharacteristicSpec]
	//

	/* (non-Javadoc)
	 * @see org.openborges.memex.universe.CharacteristicSpec#getValueType()
	 */
	public List<String> getValueType() {
		List<String> dataTypes= new ArrayList<String>();
		
		OntResource range= _characteristic.getRange();
		DataRange dataRange= range.asDataRange();
		RDFList dataTypeList= dataRange.getOneOf();
		
		for (int t=0; t< dataTypeList.size(); t++)
		{
			Literal l= (Literal) dataTypeList.get(t).as(Literal.class);
			dataTypes.add(l.getDatatypeURI());
		}
		
		return dataTypes;
	}

	/* (non-Javadoc)
	 * @see org.openborges.memex.universe.CharacteristicSpec#addDatatype(java.lang.String)
	 */
	public void addDatatype(String datatypeUri) {
		_characteristic.addRange(_characteristic.getModel().getResource(datatypeUri));
	}
}
