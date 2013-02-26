/*
**
** EPP RTK Java
** Copyright (C) 2001, Tucows, Inc.
**
**
** This library is free software; you can redistribute it and/or
** modify it under the terms of the GNU Lesser General Public
** License as published by the Free Software Foundation; either
** version 2.1 of the License, or (at your option) any later version.
**
** This library is distributed in the hope that it will be useful,
** but WITHOUT ANY WARRANTY; without even the implied warranty of
** MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
** Lesser General Public License for more details.
**
** You should have received a copy of the GNU Lesser General Public
** License along with this library; if not, write to the Free Software
** Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
**
*/

/*
 * $Header: /cvsroot/epp-rtk/epp-rtk/java/src/com/tucows/oxrs/epp0402/rtk/xml/EPPXMLBase.java,v 1.2 2003/09/02 20:04:56 tubadanm Exp $
 * $Revision: 1.2 $
 * $Date: 2003/09/02 20:04:56 $
 */

package com.tucows.oxrs.epp0402.rtk.xml;

import java.util.*;
import java.io.*;
import java.lang.reflect.Array;

import com.tucows.oxrs.epp0402.rtk.RTKBase;
import org.openrtk.idl.epp0402.*;

import org.w3c.dom.*;
import org.w3c.dom.traversal.*;
import org.apache.xerces.parsers.*;
import org.xml.sax.*;
import org.apache.xerces.dom.*;
import org.apache.xml.serialize.*;

import org.apache.regexp.*;

/**
 * Top-level abstract class for all RTK classes that deal with XML data.
 *
 * @author Daniel Manley
 * @version $Revision: 1.2 $ $Date: 2003/09/02 20:04:56 $
**/
public abstract class EPPXMLBase extends RTKBase
{

    /**
     * Array to allow for conversion from epp_TransferStatusType to
     * a String representation of the transfer status.
     * The epp_TransferStatusType static classes have a value() member
     * function which returns their integer position in the enum.
     * @see The EPP IDL epp.idl for the definition of the epp_TransferStatusType enum
     */
    protected static String[] transfer_op_to_string_array_ = {
                                "approve",
                                "cancel",
                                "query",
                                "reject",
                                "request"
                                };
    /**
     * Hashtable to allow for conversion from String transfer status to
     * epp_TransferStatusType.
     * @see The EPP IDL epp.idl for the definition of the epp_TransferStatusType enum
     */
    protected static Hashtable transfer_status_to_type_hash_;

    protected static String[] transfer_status_to_string_array_ = {
                                "clientApproved",
                                "clientCancelled",
                                "clientRejected",
                                "pending",
                                "serverApproved",
                                "serverCancelled"
                                };

    /**
     * Array to allow for conversion from epp_AuthInfoType to
     * a String representation of the auth id type.
     * The epp_AuthInfoType static classes have a value() member
     * function which returns their integer position in the enum.
     * @see The EPP IDL epp.idl for the definition of the epp_AuthInfoType enum
     */
    public static String[] auth_type_to_string_array_ = {
                                "pw"
                                };
    /**
     * Hashtable to allow for conversion from String auth id type to
     * epp_AuthInfoType.
     * @see The EPP IDL epp.idl for the definition of the epp_AuthInfoType enum
     */
    protected static Hashtable auth_type_string_to_type_hash_;

    /**
     * Holder of the response XML String
     */
    protected String xml_;

    /**
     * Default constructor
     */
    protected EPPXMLBase() { initHashes(); }

    /**
     * Constructor with XML String
     */
    protected EPPXMLBase(String xml)
    {
        xml_ = xml;
        initHashes();
    }

    /**
     * Accessor method for the response XML String
     * @return String of XML
     */
    public String getXML() { return xml_; }

    public static void initHashes()
    {
        initTransferStatusToTypeHash();
        initAuthInfoStringToTypeHash();
    }

    public static void initTransferStatusToTypeHash()
    {
        if ( transfer_status_to_type_hash_ == null )
        {
            transfer_status_to_type_hash_ = new Hashtable();
            transfer_status_to_type_hash_.put("clientApproved", epp_TransferStatusType.CLIENT_APPROVED);
            transfer_status_to_type_hash_.put("clientCancelled", epp_TransferStatusType.CLIENT_CANCELLED);
            transfer_status_to_type_hash_.put("clientRejected", epp_TransferStatusType.CLIENT_REJECTED);
            transfer_status_to_type_hash_.put("pending", epp_TransferStatusType.PENDING);
            transfer_status_to_type_hash_.put("serverApproved", epp_TransferStatusType.SERVER_APPROVED);
            transfer_status_to_type_hash_.put("serverCancelled", epp_TransferStatusType.SERVER_CANCELLED);
       }
    }

    public static void initAuthInfoStringToTypeHash()
    {
        if ( auth_type_string_to_type_hash_ == null )
        {
            auth_type_string_to_type_hash_ = new Hashtable();
            auth_type_string_to_type_hash_.put("pw", epp_AuthInfoType.PW);
       }
    }

    /**
     * Given the response XML node, it extracts the result code and result text.
     * Please see the Xerces documentation for more information regarding
     * Nodes.
     * @param Node EPP response node
     * @return epp_Response the generic response structure
     * @throws org.openrtk.idl.epp0402.epp_XMLException if the node list does not contain the result text
     */
    protected epp_Response parseGenericResult(Node response_node) throws epp_XMLException
    {
        String method_name = "parseGenericResult(Node)";
        debug(DEBUG_LEVEL_THREE,method_name,"Entered");

        epp_Response generic_response = new epp_Response();

        NodeList result_nodes = ((Element)response_node).getElementsByTagName("result");

        List results = (List)new ArrayList();

        if ( result_nodes.getLength() > 0 )
        {
            for (int count1 = 0; count1 < result_nodes.getLength(); count1++)
            {
                Element an_element = (Element)result_nodes.item(count1);

                epp_Result result = new epp_Result();
                result.m_code = Short.parseShort(an_element.getAttribute("code"));

                Node msg_node = an_element.getElementsByTagName("msg").item(0);
                result.m_lang = ((Element)msg_node).getAttribute("lang");
                result.m_id = ((Element)msg_node).getAttribute( "id" );
                result.m_msg = msg_node.getFirstChild().getNodeValue();

                NodeList value_nodes = an_element.getElementsByTagName("value");

                List values = (List)new ArrayList();

                if ( value_nodes.getLength() > 0 )
                {
                    for (int count2 = 0; count2 < value_nodes.getLength(); count2++)
                    {
                        Node value_node = value_nodes.item(count2);
                        if ( value_node.getFirstChild() != null )
                        {
                            values.add(value_node.getFirstChild().getNodeValue());
                        }
                    }
                    result.m_values = convertListToStringArray(values);

                }

                results.add(result);

            }
        }
        else
        {
            throw new epp_XMLException("missing result text in response");
        }

        generic_response.m_results = (epp_Result []) convertListToArray((new epp_Result()).getClass(), results);

        // Unspec is a raw string (which could include XML) which
        // we give directly back to the user for external parsing
        // (which might be provided by RTK classes if the extension
        // is common
        NodeList unspec_nodes = ((Element)response_node).getElementsByTagName("unspec");

        if ( unspec_nodes.getLength() == 0 )
        {
            generic_response.m_unspec_string = null;
        }
        else
        {
            Node unspec_node = unspec_nodes.item(0);
            
            Document udoc = new DocumentImpl();
            String unspec_string;

            // Have to create a new doc from this unspec node to
            // get the raw XML.
            
            udoc.appendChild( udoc.importNode( unspec_node, true ) );

            try
            {
                unspec_string = createXMLFromDoc( udoc );
                // Now have to isolate the unspec XML without
                // the <unspec> wrapping tags.
                RE regexp = new RE("<unspec>(.*)</unspec>$");
                regexp.match(unspec_string);
                generic_response.m_unspec_string = regexp.getParen(1);
            }
            catch( IOException ioe )
            {
                throw new epp_XMLException( "IOException in building XML [" + ioe.getMessage() + "]" );
            }
            catch (RESyntaxException xcp)
            {
                throw new epp_XMLException( "Bad regexp when trying to parse Unspec [" + xcp.getMessage() + "]" );
            }

        }

        epp_TransID trans_id = new epp_TransID();

        NodeList trans_id_nodes = ((Element)response_node).getElementsByTagName("trID");

        if ( trans_id_nodes.getLength() == 0 )
        {
            throw new epp_XMLException("missing result trans id");
        }

        Node trans_id_node = trans_id_nodes.item(0);

        NodeList inner_nodes = trans_id_node.getChildNodes();

        for (int count = 0; count < inner_nodes.getLength(); count++)
        {
            Node a_node = inner_nodes.item(count);

            if ( a_node.getNodeName().equals("clTRID") ) { trans_id.m_client_trid = a_node.getFirstChild().getNodeValue(); }
            if ( a_node.getNodeName().equals("svTRID") ) { trans_id.m_server_trid = a_node.getFirstChild().getNodeValue(); }
        }

        generic_response.m_trans_id = trans_id;
	
        NodeList msg_queue_nodes = ((Element)response_node).getElementsByTagName("msgQ");
	
	if ( msg_queue_nodes.getLength() > 0 )
        {
            epp_MessageQueue msg_queue = new epp_MessageQueue();
	    
	    Element msg_queue_element = (Element)msg_queue_nodes.item(0);
	    msg_queue.m_count = Integer.parseInt(msg_queue_element.getAttribute("count"));
	    
	    NodeList queue_date_nodes = msg_queue_element.getElementsByTagName("qDate");
	    if ( queue_date_nodes.getLength()>0 ) { msg_queue.m_queue_date = queue_date_nodes.item(0).getFirstChild().getNodeValue(); }
            
	    generic_response.m_message_queue = msg_queue;
	}
		
        debug(DEBUG_LEVEL_THREE,method_name,"Leaving");

        return generic_response;

    }

    /**
     * Given the node list, it finds the node for the given name
     * Please see the Xerces documentation for more information regarding
     * Nodes and Nodelists
     * @param Nodelist XML nodes
     * @param String Name of the node to find
     * @return Node for the given node name, or null if not found
     */
    protected Node getNode(NodeList node_list, String node_name)
    {
        String method_name = "getNode(NodeList,String)";
        debug(DEBUG_LEVEL_THREE,method_name,"Entered");

        Node desired_node = null;

        for (int count = 0; count < node_list.getLength(); count++)
        {
            Node a_node = node_list.item(count);
            debug(DEBUG_LEVEL_TWO,method_name,"Node's name ["+a_node.getNodeName()+"]");
            if ( a_node.getNodeName().equals(node_name) )
            {
                debug(DEBUG_LEVEL_TWO,method_name,"Found desired_node node");
                desired_node = a_node;
                break;
            }
        }

        debug(DEBUG_LEVEL_THREE,method_name,"Leaving");

        return desired_node;
    }

    /**
     * Given the node list, it finds the Element for the given name.  Actually
     * calls getNode() and casts the result into an Element.
     * Please see the Xerces documentation for more information regarding
     * Elements and Nodelists.
     * @param Nodelist XML nodes
     * @param String Name of the element to find
     * @return Element for the given element name, or null if not found
     * @see #getNode(NodeList,String)
     */
    protected Element getElement(NodeList node_list, String node_name)
    {
        String method_name = "getNode(NodeList,String)";
        debug(DEBUG_LEVEL_THREE,method_name,"Entered");

        debug(DEBUG_LEVEL_THREE,method_name,"Leaving");
        return (Element) getNode(node_list, node_name);
    }

    /**
     * Using pre-set response XML, returns the document's Element.
     * Please see the Xerces documentation for more information regarding
     * Parsers, Documents and Elements.
     * @return Element for the document
     * @throws IOException if there is an error getting the bytes from the XML
     * @throws SAXException if there are XML errors
     * @throws RuntimeException if the XML document does not support Traversal v2.0
     */
    protected Element getDocumentElement() throws IOException, SAXException
    {
        String method_name = "getDocumentElement()";
        debug(DEBUG_LEVEL_THREE,method_name,"Entered");
        DOMParser parser = new DOMParser();
        EPPXMLErrors errors = new EPPXMLErrors();
        parser.setErrorHandler(errors);
        parser.setFeature("http://apache.org/xml/features/dom/defer-node-expansion", true);
        parser.setFeature("http://apache.org/xml/features/continue-after-fatal-error", false);
        parser.setFeature("http://apache.org/xml/features/dom/include-ignorable-whitespace", false);

        if ( xml_ != null )
        {
            try
            {        
                // Xerces does not like spaces between tags because we don't
                // have a DTD to tell the parser that the spaces are
                // ignorable.  So let's get rid of them!
                RE regexp = new RE(">\\s+<");
                xml_ = regexp.subst(xml_, "><", RE.REPLACE_ALL);
            }
            catch (RESyntaxException xcp)
            {
                System.out.println("apparently, a bad regex!  The nerve!");
            }
        }

        parser.parse(new InputSource(new ByteArrayInputStream(xml_.getBytes())));
        Document document = parser.getDocument();

        if (!document.isSupported("Traversal", "2.0")) throw new RuntimeException("This DOM Document does not support Traversal");

        Element epp_node = document.getDocumentElement();
        debug(DEBUG_LEVEL_THREE,method_name,"Leaving");
        return epp_node;
    }

    /**
     * Given a Document, this method create the root epp tag and populates
     * its attributes with xmlns, xmlns:xsi, and xsi:schemaLocation values.
     * @param Document which implements the createElement() method
     * @return Element The newly created epp Element
     */
    protected Element createDocRoot(Document doc)
    {
        String method_name = "createDocRoot()";
        debug(DEBUG_LEVEL_THREE,method_name,"Entered");

        Element root = doc.createElement("epp");
        root.setAttribute("xmlns", "urn:iana:xml:ns:epp-1.0");
        root.setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
        root.setAttribute("xsi:schemaLocation", "urn:iana:xml:ns:epp-1.0 epp-1.0.xsd");

        debug(DEBUG_LEVEL_THREE,method_name,"Leaving");
        return root;
    }

    /**
     * Given a Document with all of its children elements appended, converts
     * this document to an XML String.
     * @param Document the complete XML Document
     * @return String the XML String
     * @throws java.io.IOException
     */
    protected String createXMLFromDoc(Document doc) throws IOException
    {
        String method_name = "createXMLFromDoc()";
        debug(DEBUG_LEVEL_THREE,method_name,"Entered");

        OutputFormat format = new OutputFormat( doc );
        StringWriter stringOut = new StringWriter();
        XMLSerializer serial = new XMLSerializer( stringOut, format );

        serial.asDOMSerializer();
        serial.serialize( doc.getDocumentElement() );

        debug(DEBUG_LEVEL_THREE,method_name,"Leaving");
        return stringOut.toString();
    }

    /**
     * Creates an Element for the given tag name and appends it to the given
     * containing Element.
     * @param Document the Document instance that is creating the Elements
     * @param Element the Element that will contain the new Element created
     * @param String the tag name for the new Element
     * @param String the String value to put into the tag.  Can be null for an empty tag.
     * @return Element the newly created Element which was added to the containing Element
     */
    protected Element addXMLElement(Document doc, Element containing_element, String tag_name, String value)
    {
        Element xml_element = doc.createElement(tag_name);
        if ( value != null && value.length() != 0 )
        {
            xml_element.appendChild( doc.createTextNode(value) );
        }
        containing_element.appendChild( xml_element );
        return xml_element;
    }

    /**
     * Converts a List of any size to a Java Object array
     * that can be cast into an array of any class.
     * All of the objects in the List must be of the same class otherwise a
     * run-time exception will be throws.
     * Example:</P>
     * <PRE>List integer_list = (List)ArrayList();</PRE>
     * <PRE>integer_list.add(new Integer(1));</PRE>
     * <PRE>integer_list.add(new Integer(2));</PRE>
     * <PRE>Integer[] string_array = (Integer[]) EPPXMLBase.convertListToArray((new Integer()).getClass(), string_list)</PRE>
     * @param Class the Class of the resulting array.
     * @param List the List of instances of the given Class
     * @return Object the newly created array.
     */
    public static Object convertListToArray(Class the_class, List the_list)
    {
        Object[] the_array = (Object []) Array.newInstance(the_class, the_list.size());
        int index = 0;
        for (Iterator it = the_list.iterator(); it.hasNext();)
        {
            the_array[index] = it.next();
            index++;
        }

        return the_array;
    }

    /**
     * Converts a List of any size of Strings to a Java Object array.
     * A convenience version of the convertListToArray() method.
     * Example:</P>
     * <PRE>List string_list = (List)ArrayList();</PRE>
     * <PRE>string_list.add("Hello");</PRE>
     * <PRE>string_list.add("Goodbye");</PRE>
     * <PRE>// Note that no casting is required here.</PRE>
     * <PRE>String[] string_array = EPPXMLBase.convertListToStringArray(string_list)</PRE>
     * @param List the List of instances of Strings
     * @return String[] the resulting String array
     */
    public static String[] convertListToStringArray(List the_list)
    {
        String[] the_array = (String []) Array.newInstance((new String()).getClass(), the_list.size());
        int index = 0;
        for (Iterator it = the_list.iterator(); it.hasNext();)
        {
            the_array[index] = (String)it.next();
            index++;
        }

        return the_array;
    }

    /**
     * Given a list of String values, create Elements all with the same
     * tag name and all going under the same Element wrapper.
     * @param Document the Document instance that is creating the Elements
     * @param Element the Element that will contain the new Elements created
     * @param String the tag name to use for all the new Elements
     * @param List the List of String values to put into the new Elements.
     */
    protected void stringListToXML(Document doc,
                                   Element wrapper,
                                   String tag_name,
                                   List list)
    {
        for (Iterator it = list.iterator(); it.hasNext();)
        {
            String string_value = (String)it.next();
            addXMLElement(doc, wrapper, tag_name, string_value);
        }
    }

    /**
     * Creates an EPP <creds> Element using the given epp_Credentials.
     * The epp_Credentials cannot be null.  If the new password in the creds
     * is null, it is not added to the resulting Element.
     * @param Document the Document instance that is creating the Elements
     * @param epp_Credentials the non-null credentials
     * @return Element the resulting EPP <creds> Element
     */
    protected Element prepareCreds(Document doc,
                                   epp_Credentials creds)
    {
        Element creds_element = doc.createElement("creds");

        addXMLElement(doc, creds_element, "clID", creds.m_client_id);
        addXMLElement(doc, creds_element, "pw", creds.m_password);
        if ( creds.m_new_password != null )
        {
            addXMLElement(doc, creds_element, "newPW", creds.m_new_password);
        }

        if ( creds.m_options != null )
        {
            Element options_element = doc.createElement("options");
            if ( creds.m_options.m_version != null )
            {
                addXMLElement(doc, options_element, "version", creds.m_options.m_version);
            }
            if ( creds.m_options.m_lang != null )
            {
                addXMLElement(doc, options_element, "lang", creds.m_options.m_lang);
            }
            creds_element.appendChild( options_element );
        }

        return creds_element;
    }

    /**
     * Creates an EPP <Object:authInfo> Element.
     * @param Document the Document instance that is creating the Elements
     * @param String the tag's name
     * @param epp_AuthInfo element that contains authorization information
     * @return Element the resulting EPP <Object:authInfo> Element
     */
    protected Element prepareAuthInfo(Document doc,
                                      String tag_name,
                                      epp_AuthInfo auth_info)
    {
        Element auth_info_element = doc.createElement(tag_name);

        auth_info_element.appendChild( doc.createTextNode(auth_info.m_value) );

        if ( auth_info.m_type == null )
        {
            // At the time of writing this, the only value possible
            // was PW.  So let's default it if it's not set.
            auth_info.m_type = epp_AuthInfoType.PW;
        }

        auth_info_element.setAttribute( "type", auth_type_to_string_array_[auth_info.m_type.value()] );

        if ( auth_info.m_roid != null )
        {
            auth_info_element.setAttribute( "roid", auth_info.m_roid );
        }

        return auth_info_element;
    }

    /**
     * Given an array of epp_CheckResult's, finds the matching value
     * and returns it's exists value in a Boolean object.
     * This method works for Check Results on any object.
     * @param epp_CheckResult[] array of check results.
     * @param String the value for which to search
     * @return Boolean the exists boolean in a Boolean object. Will return
     *                 null if the array is null or if the value was not found.
     */
    public static Boolean getCheckResultFor(epp_CheckResult[] check_results,
                                            String check_value)
    {
        Boolean wanted_result = null;

        if ( check_results != null )
        {
            for ( int index = 0; index < check_results.length; index++ )
            {
                if ( check_results[index].m_value.equalsIgnoreCase(check_value) )
                {
                    wanted_result = new Boolean( check_results[index].m_exists );
                    break;
                }
            }
        }

        return wanted_result;
    }

    /**
     * Gets the Node representing the unspec xml tag.
     * @return Element for the unspec XML
     * @throws IOException if there is an error getting the bytes from the XML
     * @throws SAXException if there are XML errors
     * @throws RuntimeException if the XML document does not support Traversal v2.0
     */
    protected Node getUnspecNode(String unspec_string) throws IOException, SAXException
    {
        String method_name = "getUnspecNode()";
        debug(DEBUG_LEVEL_THREE,method_name,"Entered");
        DOMParser parser = new DOMParser();
        EPPXMLErrors errors = new EPPXMLErrors();
        parser.setErrorHandler(errors);
        parser.setFeature("http://apache.org/xml/features/dom/defer-node-expansion", true);
        parser.setFeature("http://apache.org/xml/features/continue-after-fatal-error", false);
        parser.setFeature("http://apache.org/xml/features/dom/include-ignorable-whitespace", false);

        if ( xml_ != null )
        {
            try
            {        
                // in some testing with the .info registry, we found that
                // Xerces does not like spaces between tags and doesn't like
                // XML header.  So let's get rid of them!
                
                // First eliminate the spaces between the tags
                RE regexp = new RE("> +<");
                xml_ = regexp.subst(xml_, "><", RE.REPLACE_ALL);
                // Then eliminate the xml header
                regexp = new RE(" *<[?].+[?]>");
                xml_ = regexp.subst(xml_, "", RE.REPLACE_ALL);
            }
            catch (RESyntaxException xcp)
            {
                System.out.println("apparently, a bad regex!  The nerve!");
            }
        }
        debug(DEBUG_LEVEL_TWO,method_name,"The unspec_string is ["+unspec_string+"]");
        try
        {
            parser.parse(new InputSource(new ByteArrayInputStream(unspec_string.getBytes())));
        }
        catch (SAXException xcp)
        {
            debug(DEBUG_LEVEL_THREE,method_name,xcp);
            // silently accept SAXException -- they mean that
            // the XML was unparseable... ie. the unspec string is just 
            // a string, nothing special.  The resulting document will
            // simply contain the unspec string.
        }
        Document document = parser.getDocument();

        if (!document.isSupported("Traversal", "2.0")) throw new RuntimeException("This DOM Document does not support Traversal");

        Node unspec_node = (Node) document.getDocumentElement();
        debug(DEBUG_LEVEL_THREE,method_name,"Leaving");
        return unspec_node;
    }

    /**
     * Method to change an epp_Unspec instance to XML elements.
     */
    protected Element prepareUnspecElement( Document doc,
                                            epp_Unspec unspec )
                        throws epp_XMLException
    {
        String method_name = "prepareUnspecElement()";
        debug(DEBUG_LEVEL_THREE,method_name,"Entered");
        Element unspec_element = doc.createElement("unspec");
        if ( unspec != null )
        {
            String unspec_string = unspec.toXML();
            if ( unspec_string != null &&
                 unspec_string.length() != 0 )
            {
                try
                {
                    Node unspec_node = getUnspecNode(unspec_string);
                    if ( unspec_node != null )
                    {
                        unspec_node = doc.importNode( getUnspecNode(unspec_string), true );
                        unspec_element.appendChild( unspec_node );
                    }
                    else
                    {
                        // if the unspec node was null, then it wasn't valid XML or
                        // it is just a string (eg "hellothere") and this
                        // can be included as a raw string in the unspec tag.
                       unspec_element.appendChild( doc.createTextNode(unspec_string) );
                    }
                }
                catch ( Exception xcp )
                {
                    debug(DEBUG_LEVEL_ONE,method_name,xcp);
                    throw new epp_XMLException( "error in unspec XML ["+xcp.getClass().getName()+"] ["+xcp.getMessage()+"]" );
                }
            }
        }
        debug(DEBUG_LEVEL_THREE,method_name,"Leaving");
        return unspec_element;
    }

    /**
     * Convenience method to get a transfer status string from an
     * epp_TransferStatusType object.
     */
    public static String transferStatusToString(epp_TransferStatusType status_type)
    {
        return transfer_status_to_string_array_[status_type.value()];
    }

	public static epp_TransferStatusType transferStatusFromString(String s)
    {
		return (epp_TransferStatusType) transfer_status_to_type_hash_.get(s);
    }
}
