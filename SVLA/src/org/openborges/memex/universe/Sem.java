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

/** This interface specifies a base representation for
 * a semantic resource, or <i>sem</i>.
 * 
 * <p>A <b>sem</b> is defined through :
 * <ul>
 * <li>An <i>identity</i> : This identity is provided by an Uniform Resource
 * Identifier [cf. <a href="http://www.ietf.org/rfc/rfc2396.txt">RFC 2396</a> or 
 * <a href="http://www.gbiv.com/protocols/uri/rev-2002/issues.html">
 * URI specification revision (RFC 2396 to RFC 3986)</a> ]. 
 * An issue is that interesting or feature-rich semantic operations involve <i>anonymous resources</i>,
 * that may not accept <i>uniform</i> identifiers in the words and letter of the URI specifications.
 * This uniformity approximation is resolved localy by providing <i>anonymous identifiers</i>, of
 * limited scope and life-cycle, and remotely by considering <i>inverse functionnal properties</i>, or
 * other <i>sameAs</i>-like technics.</li>
 * <li>A reference to a <i>specification</i> : This reference is again provided by
 * an URI, while the referenced resource may be interpreted as a  
 * <a href="http://www.w3.org/TR/rdf-schema/#ch_isdefinedby">rdfs:isDefinedBy</a> property object.</li>
 * <li>A location in a <i>taxonomy</i> : This is the resource projection upon a graph of hyperymy/hyponymy
 * relations, provided through the set of <i>taxon</i> this sem is known as a population member of.</li>
 * <li>An <i>internal state</i> : This state does not involve any other sem, and is
 * provided through a set of <i>valuated characteristics</i>. Labels and descriptions in natural
 * languages are base characteristics.
 * </li>
 * <li>A <i>directed closure</i> : This closure is the subgraph of all directed relations associating
 * this sem to others, provided as a set of <i>valuated behaviors</i>.  
 * </li>
 * </ul></p>
 * 
 * @author <a href="mailto:chris@openborges.org">chris</a>
 * @version $Id: Sem.java,v 1.2 2008/09/02 17:21:49 duf Exp $ 
 */
public interface Sem {

	/** Accessor to this sem <i>identity</i>.
	 * 
	 * @return An URI or an anonymous identifier.
	 */
	public String getURI() ;
	
	/** Accessor to this sem labels.
	 * 
	 * <p>A label characteristic MAY be interpreted as <a href="http://www.w3.org/TR/rdf-schema/#ch_label">rdfs:label</a>.</p>
	 * 
	 * @param lang The requested language.
	 * @return A list containing the known label for the requested language.
	 */
	public List<String> getLabel(String lang) ;

	/** Accessor to this sem description.
	 * 
	 * <p>A comment characteristic may be interpreted as <a href="http://www.w3.org/TR/rdf-schema/#ch_comment">rdfs:comment</a>.</p>
	 * 
	 * @param lang The requested language.
	 * @return A list containing the known comments for the requested language.
	 */
	public List<String> getDescription(String lang) ;
	
	/** Accessor to this sem <i>spcecification</i>.
	 * 
	 * <p>An abstract principal may be interpreted as <a href="http://www.w3.org/TR/rdf-schema/#ch_isdefinedby">rdfs:isDefinedBy</a>.</p>
	 * 
	 * @return An URI.
	 */
	public String getSpecification() ;

	/**
	 * Accessor to this sem taxonomy.
	 * 
	 * @return The list of taxon this sem is <i>explicitly known</i> as a member of.
	 */
	public List<Taxon> getTaxonomy();
	
	/** Accessor to this sem <i>hypernymy</i>.
	 * 
	 * @return A list containing the <i>taxon</i> known as hypernyms of this.
	 */
	public List<Taxon> getHypernymy() ;

	/** Accessor to this sem <i>hyponymy</i>.
	 * 
	 * @return A list containing the <i>taxon</i> known as hyponyms of this.
	 */
	public List<Taxon> getHyponymy() ;
	
	/** Accessor to a <i>valuated characteristc</i>.
	 * 
	 * @param chracteristicURI The URI identifying the requested <i>characteristic</i>.
	 * @return A list containing the known values for the requested characteristic. 
	 */
	public List<Characteristic> getCharacteristic(String chracteristicURI) ;
	
	/** Accessor to this sem <i>internal state</i>.
	 * 
	 * @return A list containing all known characteristic values.
	 */
	public List<Characteristic> getInternalState() ;
	
	/** Accessor to a <i>valuated behavior</i>.
	 * 
	 * @param behaviorURI The URI identifying the requested <i>behavior</i>.
	 * @return A list containing the known values for the requested behavior. 
	 */
	public List<Behavior> getBehavior(String behaviorURI) ;
	
	/** Accessor to this sem <i>closure</i>.
	 * 
	 * @return A list containing all known behavior values.
	 */
	public List<Behavior> getClosure() ;
	
	/**
	 * @return
	 */
	public Object getModel();
	
}
