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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.net.MalformedURLException;
import java.util.HashMap;

import org.openborges.memex.ubus.IUniverseFactory;
import org.openborges.memex.universe.Universe;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.ontology.OntResource;
import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.util.iterator.ExtendedIterator;

/**
 * @author <a href="mailto:chris@openborges.org">chris</a>
 * @version $Id: FileUniverseFactory.java,v 1.1 2008/09/02 17:21:49 duf Exp $
 *
 */
public class FileUniverseFactory implements IUniverseFactory {

	///////////////////////////////////////////////////////////////////////////
	//
	// STATE
	//

	protected HashMap<String,Universe> _universeMap= new HashMap<String,Universe>();
	protected HashMap<String,File> _fileMap= new HashMap<String,File>();
	protected OntModel _factoryModel;
	
	protected File _factoryFile;
	protected String _repositoryBase;
	
	///////////////////////////////////////////////////////////////////////////
	//
	// CONSTRUCTORS
	//
	
	/**
	 * 
	 */
	public FileUniverseFactory(FileUniverseFactorySpec spec) {
		init_base(spec); // enforce canWrite on base directory
		init_factory_model(); // loads _fileMap from .factory file
	}

	
	///////////////////////////////////////////////////////////////////////////
	//
	// API [org.openborges.memex.ubus.IUniverseFactory]
	//
	
	/* (non-Javadoc)
	 * @see org.openborges.memex.ubus.IUniverseFactory#getUniverse(java.lang.String)
	 */
	public Universe createUniverse(String universeUri)
	{
		// if neither in universe map nor in files map
		if (! (_universeMap.containsKey(universeUri) || (_fileMap.containsKey(universeUri))) )
		{
			OntModel model= ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM_RDFS_INF);
			OntResource res= model.createOntResource(universeUri);
			
			commit_new_universe(res);
		}
		
		return _universeMap.get(universeUri);
	}

	/* (non-Javadoc)
	 * @see org.openborges.memex.ubus.IUniverseFactory#getUniverse(java.lang.String)
	 */
	public Universe getUniverse(String universeUri)
	{
		// if missing in universe map but present in files map
		if ( (! _universeMap.containsKey(universeUri)) 
				&& (_fileMap.containsKey(universeUri))
				)
		{
			OntModel model= ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM_RDFS_INF);
			
			try
			{
				model.read(_fileMap.get(universeUri).toURL().toExternalForm());
				OntResource universe= model.getOntResource(universeUri);
				
				load_universe(universe);
			}
			catch (MalformedURLException e)
			{
				throw new IllegalArgumentException(_fileMap.get(universeUri).getAbsolutePath(), e);
			}
		}
		
		return _universeMap.get(universeUri);
	}

	
	///////////////////////////////////////////////////////////////////////////
	//
	// PRIVATE IMPLEMENTATION
	//

	
	// enforce provided base directory exists and writable
	private void init_base(FileUniverseFactorySpec factorySpec)
	{
		_repositoryBase= factorySpec.getProperty(FileUniverseFactorySpec.REPOSITORY_BASE)
			+ File.separator;
		
		File baseDir= new File(_repositoryBase);
		if (! (baseDir.exists() || baseDir.mkdirs()) )
		{
			throw new IllegalArgumentException(factorySpec.getProperty(FileUniverseFactorySpec.REPOSITORY_BASE));
		}
		
		_factoryFile= new File(_repositoryBase+".factory");
	}
	
	// loads factory file and _fileMap 
	private void init_factory_model() 
	{
		_factoryModel= ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM_RDFS_INF);
		_factoryModel.setNsPrefix("ubus", FileUniverseMetaphor.NS_URI);
		
		try
		{
			if (_factoryFile.exists())
			{
				_factoryModel.read(_factoryFile.toURL().toExternalForm());
			}
			else
			{
				// loads factory metaphor as a submodel
				_factoryModel.addSubModel(FileUniverseMetaphor.getInstance().getModel());
			}
		}
		catch(Exception e)
		{
			throw new IllegalArgumentException(_factoryFile.getAbsolutePath(), e);
		}
		
		load_filemap();
	}
	
	// answers file: <REPOSITORY_BASE>/universe_<n>
	private File create_universe_file()
	{
		String universeBaseName= _repositoryBase + "universe_"; 
		
		long id= 0;
		String strId= Long.toString(id);
		String path= universeBaseName+strId;
		File file= new File(path);
		
		while(file.exists())
		{
			++id;
			strId= Long.toString(id);
			path= universeBaseName+strId;
			file= new File(path);
		}
			
		return file;		
	}
	
	private void commit_new_universe(OntResource universe)
	{
		// universe file
		File fileForUniverse= create_universe_file();
		
		// defines universe
		universe.setRDFType(FileUniverseMetaphor.getInstance().FileUniverse);
		universe.setPropertyValue(FileUniverseMetaphor.getInstance().FileCharacteristic,
				universe.getOntModel().createTypedLiteral(fileForUniverse.getName()));
		FileUniverseImpl fileUniverse= new FileUniverseImpl(universe, fileForUniverse);
		
		// register in universe map
		_universeMap.put(universe.getURI(), fileUniverse);
		
		// register in files map
		_fileMap.put(universe.getURI(), fileUniverse.getFile());

		// update factory conf
		OntResource universeInFactoryModel= _factoryModel.createOntResource(universe.getURI());
		universeInFactoryModel.setRDFType(FileUniverseMetaphor.getInstance().FileUniverse);
		universeInFactoryModel.setPropertyValue(FileUniverseMetaphor.getInstance().FileCharacteristic,
				_factoryModel.createTypedLiteral(fileForUniverse.getName()));
		save_factory_model();
	}

	private void load_universe(OntResource universe)
	{
		FileUniverseImpl fileUniverse= 
			new FileUniverseImpl(universe, _fileMap.get(universe.getURI()));
		
		// register in universe map
		_universeMap.put(universe.getURI(), fileUniverse);
	}
	
	private void load_filemap()
	{
		ExtendedIterator iterOnUniverses=
			_factoryModel.getOntClass(FileUniverseMetaphor.getInstance().FileUniverse.getURI()).listInstances();
		
		while(iterOnUniverses.hasNext())
		{
			OntResource universe= (OntResource) iterOnUniverses.next();
			RDFNode fileCharacteristic= 
				universe.getPropertyValue(FileUniverseMetaphor.getInstance().FileCharacteristic); 
			String universeFileName= ((Literal) fileCharacteristic.as(Literal.class)).getString();
			
			_fileMap.put(universe.getURI(), 
					new File(_repositoryBase+universeFileName));
		}
	}
	
	// save factory model to factory file
	private void save_factory_model()
	{
		try
		{
			_factoryModel.write(new FileOutputStream(_factoryFile));
		}
		catch (FileNotFoundException e)
		{
			throw new IllegalArgumentException(_factoryFile.getAbsolutePath(), e);
		}
	}
	
}
