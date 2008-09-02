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

package org.openborges.memex.ubus.file;

import com.hp.hpl.jena.ontology.DatatypeProperty;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.vocabulary.XSD;

/**
 * @author <a href="mailto:chris@openborges.org">chris</a>
 * @version $Id: FileUniverseMetaphor.java,v 1.1 2008/09/02 17:21:49 duf Exp $
 *
 */
public class FileUniverseMetaphor {
	
	///////////////////////////////////////////////////////////////////////////
	//
	// STATE
	//
	
	/**
	 * 
	 */
	public static final String NS_URI= "http://openborges.org/ns/memex/ubus/file#"; 
	
	/**
	 * 
	 */
	public DatatypeProperty FileCharacteristic;
	
	/**
	 * 
	 */
	public OntClass FileUniverse;
	
	// Singleton
	//
	private static FileUniverseMetaphor _instance= null;
	
	
	///////////////////////////////////////////////////////////////////////////
	//
	// CONSTRUCTORS
	//
	
	private FileUniverseMetaphor()
	{
		// FileUniverse
		//
		FileUniverse= _metaphor.createClass(NS_URI+"FileUniverse");
		
		// FileCharacteristic
		//
		FileCharacteristic= _metaphor.createDatatypeProperty(NS_URI+"FileCharacteristic");
		FileCharacteristic.addDomain(FileUniverse);
		FileCharacteristic.addRange(XSD.xstring);
	}
	private OntModel _metaphor= ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM_RDFS_INF);;

	/**
	 * @return
	 */
	public static FileUniverseMetaphor getInstance()
	{
		if (_instance == null)
		{
			_instance= new FileUniverseMetaphor();
		}
		
		return _instance;
	}
	
	
	///////////////////////////////////////////////////////////////////////////
	//
	// API []
	//
	public OntModel getModel()
	{
		return _metaphor;
	}
	
	///////////////////////////////////////////////////////////////////////////
	//
	// PRIVATE IMPLEMENTATION
	//

}
