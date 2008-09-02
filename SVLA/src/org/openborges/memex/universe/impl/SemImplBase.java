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

import org.openborges.memex.universe.Behavior;
import org.openborges.memex.universe.Characteristic;
import org.openborges.memex.universe.Sem;
import org.openborges.memex.universe.Taxon;

import com.hp.hpl.jena.ontology.OntResource;
import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.NodeIterator;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.util.iterator.ExtendedIterator;

/**
 * @author <a href="mailto:chris@openborges.org">chris</a>
 * @version $Id: SemImplBase.java,v 1.1 2008/09/02 17:21:49 duf Exp $
 *
 */
public class SemImplBase implements Sem {

	///////////////////////////////////////////////////////////////////////////
	//
	// STATE
	//
	
	protected OntResource _resource;
	
	///////////////////////////////////////////////////////////////////////////
	//
	// CONSTRUCTORS
	//
	
	/**
	 * @param resource
	 */
	public SemImplBase(OntResource  resource)
	{
		_resource= resource;
	}
	
	///////////////////////////////////////////////////////////////////////////
	//
	// OVERRIDE
	//
	
	@Override
	public boolean equals(Object obj)
	{
		if ((obj != null) && (obj instanceof Sem))
		{
			return getURI().equals(((Sem) obj).getURI());
		}
		
		return false;
	}

	@Override
	public int hashCode()
	{
		return getURI().hashCode();
	}

	@Override
	public String toString()
	{
		return getURI();
	}

	///////////////////////////////////////////////////////////////////////////
	//
	// API [org.openborges.memex.universe.Sem]
	//
	
	/* (non-Javadoc)
	 * @see org.openborges.memex.universe.Sem#getURI()
	 */
	public String getURI()
	{
		return _resource.getURI();
	}

	/* (non-Javadoc)
	 * @see org.openborges.memex.universe.Sem#getLabel(java.lang.String)
	 */
	public List<String> getLabel(String lang)
	{
		List<String> labels= new ArrayList<String>();
		
		ExtendedIterator iterOnLabels= _resource.listLabels(lang);
		List<Literal> labelList= (List<Literal>) iterOnLabels.toList();
		for (Literal label:  labelList)
		{
			labels.add(label.getString());
		}
		return labels;
	}
	
	/* (non-Javadoc)
	 * @see org.openborges.memex.universe.Sem#getDescription(java.lang.String)
	 */
	public List<String> getDescription(String lang)
	{
		List<String> comments= new ArrayList<String>();
		
		ExtendedIterator iterOnComments= _resource.listComments(lang);
		List<Literal> commentsList= (List<Literal>) iterOnComments.toList();
		for (Literal comment: commentsList)
		{
			comments.add(comment.getString());
		}
		return comments;
	}

	/* (non-Javadoc)
	 * @see org.openborges.memex.universe.Sem#getBehavior(java.lang.String)
	 */
	public List<Behavior> getBehavior(String behaviorURI)
	{
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.openborges.memex.universe.Sem#getCharacteristic(java.lang.String)
	 */
	public List<Characteristic> getCharacteristic(String characteristicUri)
	{
		List<Characteristic> characteristics= new ArrayList<Characteristic>(); 
		
		if (_resource.getOntModel().getDatatypeProperty(characteristicUri) != null)
		{
			NodeIterator iterOnValues= _resource.listPropertyValues(_resource.getOntModel().getDatatypeProperty(characteristicUri));
		
			while (iterOnValues.hasNext())
			{
				RDFNode node= iterOnValues.nextNode();
				Literal value= (Literal) node.as(Literal.class);
				characteristics.add(new CharacteristicImplBase(characteristicUri, value.getValue()));
			}
		}
		else
		{
			throw new IllegalArgumentException(characteristicUri);
		}
		
		return characteristics;
	}

	/* (non-Javadoc)
	 * @see org.openborges.memex.universe.Sem#getClosure()
	 */
	public List<Behavior> getClosure() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.openborges.memex.universe.Sem#getInternalState()
	 */
	public List<Characteristic> getInternalState() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.openborges.memex.universe.Sem#getSpecification()
	 */
	public String getSpecification()
	{
		if (_resource.getIsDefinedBy() != null)
		{
			return _resource.getIsDefinedBy().getURI();
		}
		
		return null;
	}

	/* (non-Javadoc)
	 * @see org.openborges.memex.universe.Sem#getTaxonomy()
	 */
	public List<Taxon> getTaxonomy() {
		// TODO Auto-generated method stub
		return null;
	}
	
	/* (non-Javadoc)
	 * @see org.openborges.memex.universe.Sem#getTaxonomy(boolean)
	 */
	public List<Taxon> getHypernymy() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.openborges.memex.universe.Sem#getHyponymy()
	 */
	public List<Taxon> getHyponymy() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.openborges.memex.universe.Sem#getModel()
	 */
	public Object getModel() {
		return _resource;
	}

}
