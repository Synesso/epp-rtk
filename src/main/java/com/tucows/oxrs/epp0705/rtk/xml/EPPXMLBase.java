/*
**
** EPP RTK Java
** Copyright (C) 2001-2002, Tucows, Inc.
** Copyright (C) 2003, Liberty RMS
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
 * $Header: /cvsroot/epp-rtk/epp-rtk/java/src/com/tucows/oxrs/epp0705/rtk/xml/EPPXMLBase.java,v 1.4 2004/07/29 16:43:59 tubadanm Exp $
 * $Revision: 1.4 $
 * $Date: 2004/07/29 16:43:59 $
 */

package com.tucows.oxrs.epp0705.rtk.xml;

import java.util.*;
import java.io.*;
import java.lang.reflect.Array;

import com.tucows.oxrs.epp0705.rtk.RTKBase;
import org.openrtk.idl.epp0705.*;

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
 * @version $Revision: 1.4 $ $Date: 2004/07/29 16:43:59 $
**/
public abstract class EPPXMLBase extends RTKBase
{

    /**
     * Hashtable to allow for conversion from String transfer status to
     * epp_TransferStatusType.
     * @see The EPP IDL epp.idl for the definition of the epp_TransferStatusType enum
     */
    protected static Hashtable transfer_status_to_type_hash_;

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
            auth_type_string_to_type_hash_.put("ext", epp_AuthInfoType.EXT);
       }
    }

    /**
     * Given the response XML node, it extracts the result code and result text.
     * Please see the Xerces documentation for more information regarding
     * Nodes.
     * @param Node EPP response node
     * @return epp_Response the generic response structure
     * @throws org.openrtk.idl.epp0705.epp_XMLException if the node list does not contain the result text
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
                result.m_msg = msg_node.getFirstChild().getNodeValue();

                NodeList value_nodes = an_element.getElementsByTagName("value");

                List values = (List)new ArrayList();

                if ( value_nodes.getLength() > 0 )
                {
                    for (int count2 = 0; count2 < value_nodes.getLength(); count2++)
                    {
                        Node value_node = value_nodes.item(count2);
                        
                        // getElementsByTagName() is recursive to the children, so we might have
                        // a <value> node which is really under an <extValue> parent.
                        if ( value_node.getParentNode().getNodeName().equals("extValue") ) {
                            // skip...   leave it to when we process <extValue>'s in the next block
                            break;
                        }
                        
                        Node value_contents = value_node.getFirstChild();
                        debug(DEBUG_LEVEL_TWO,method_name,"the value contents node name ["+value_contents.getNodeName()+"]");

                        String value_xml_string = "";

                        boolean good_xml = true;

                        try
                        {
                            Document udoc = new DocumentImpl();
                            udoc.appendChild( udoc.importNode( value_contents, true ) );
                            value_xml_string = createXMLSnippetFromDoc( udoc );
                        }
                        catch( IOException ioe )
                        {
                            good_xml = false;
                            value_xml_string = value_contents.getNodeValue();
                        }

                        epp_ResultValue value = new epp_ResultValue( value_contents.getNamespaceURI(),
                                                                     value_xml_string,
                                                                     ( good_xml ? value_contents.getNodeName() : null ),
                                                                     ( good_xml && value_contents.getFirstChild() != null ? value_contents.getFirstChild().getNodeValue() : null ) );
                        // XXX have to parse stuff here if we can.
                        values.add(value);
                    }
                    result.m_values = (epp_ResultValue[])convertListToArray((new epp_ResultValue()).getClass(), values);

                }

                NodeList ext_value_nodes = an_element.getElementsByTagName("extValue");

                List ext_values = (List)new ArrayList();

                if ( ext_value_nodes.getLength() > 0 )
                {
                    for (int count2 = 0; count2 < ext_value_nodes.getLength(); count2++)
                    {
                        Node ext_value_node = ext_value_nodes.item(count2);
                        
                        NodeList ext_value_children = ext_value_node.getChildNodes();
                        
                        epp_ExtResultValue ext_value = null;

                        for (int count3 = 0; count3 < ext_value_children.getLength(); count3++)
                        {
                            Node a_node = ext_value_children.item(count3);

                            if ( ext_value == null ) {
                                ext_value = new epp_ExtResultValue();
                            }
                            
                            if ( a_node.getNodeName().equals("value") )
                            {

                                Node value_contents = a_node.getFirstChild();
                                debug(DEBUG_LEVEL_TWO,method_name,"the value contents node name ["+value_contents.getNodeName()+"]");

                                String value_xml_string = "";

                                boolean good_xml = true;
                                try
                                {
                                    Document udoc = new DocumentImpl();
                                    udoc.appendChild( udoc.importNode( value_contents, true ) );
                                    value_xml_string = createXMLSnippetFromDoc( udoc );
                                }
                                catch( IOException ioe )
                                {
                                    good_xml = false;
                                    value_xml_string = value_contents.getNodeValue();
                                }


                                epp_ResultValue value = new epp_ResultValue( value_contents.getNamespaceURI(),
                                                                             value_xml_string,
                                                                             ( good_xml ? value_contents.getNodeName() : null ),
                                                                             ( good_xml && value_contents.getFirstChild() != null ? value_contents.getFirstChild().getNodeValue() : null ) );

                                ext_value.m_value = value;
                            }
                            if ( a_node.getNodeName().equals("reason") ) 
                            {
                                ext_value.m_reason = a_node.getFirstChild().getNodeValue();
                            }
                        }
                                                
                        // XXX have to parse stuff here if we can.
                        if ( ext_value != null ) {
                            ext_values.add(ext_value);
                        }
                    }
                    result.m_ext_values = (epp_ExtResultValue[])convertListToArray((new epp_ExtResultValue()).getClass(), ext_values);

                }


                results.add(result);

            }
        }
        else
        {
            throw new epp_XMLException("missing result text in response");
        }

        generic_response.m_results = (epp_Result []) convertListToArray((new epp_Result()).getClass(), results);

        // Extension is a raw string (which could include XML) which
        // we give directly back to the user for external parsing
        // (which might be provided by RTK classes if the extension
        // is common
        NodeList extension_nodes = ((Element)response_node).getElementsByTagName("extension");

        if ( extension_nodes.getLength() == 0 )
        {
            generic_response.m_extension_strings = null;
        }
        else
        {
            Node extension_node = extension_nodes.item(0);
            
            // Have to loop through each child node and save the XML from it.
            NodeList extension_children = extension_node.getChildNodes();
            
            debug(DEBUG_LEVEL_TWO,method_name,"response extension node count ["+extension_children.getLength()+"]");
            
            List extension_strings = (List)new ArrayList();
            
            for (int count = 0; count < extension_children.getLength(); count++)
            {
                try
                {
                    Node a_node = extension_children.item(count);

                    Document udoc = new DocumentImpl();

                    // Have to create a new doc from this extension node to
                    // get the raw XML.

                    udoc.appendChild( udoc.importNode( extension_node, true ) );

                    String extension_string = createXMLSnippetFromDoc( udoc );
                    debug(DEBUG_LEVEL_TWO,method_name,"here's an extension xml string ["+extension_string+"]");
                    extension_strings.add(extension_string);

                }
                catch( IOException ioe )
                {
                    throw new epp_XMLException( "IOException in building XML [" + ioe.getMessage() + "]" );
                }
            }

            if ( extension_strings.size() > 0 )
            {
                generic_response.m_extension_strings = convertListToStringArray(extension_strings);
            }

        }

        NodeList trans_id_nodes = ((Element)response_node).getElementsByTagName("trID");
        if ( trans_id_nodes.getLength() == 0 )
        {
            throw new epp_XMLException("missing result trans id");
        }
        Node trans_id_node = trans_id_nodes.item(0);
        generic_response.m_trans_id = getTransID(trans_id_node);
	
        NodeList msg_queue_nodes = ((Element)response_node).getElementsByTagName("msgQ");
	
	if ( msg_queue_nodes.getLength() > 0 )
        {
            epp_MessageQueue msg_queue = new epp_MessageQueue();
	    
	    Element msg_queue_element = (Element)msg_queue_nodes.item(0);
	    msg_queue.m_count = Integer.parseInt(msg_queue_element.getAttribute("count"));
            msg_queue.m_id = msg_queue_element.getAttribute("id");
	    
	    NodeList queue_date_nodes = msg_queue_element.getElementsByTagName("qDate");
	    if ( queue_date_nodes.getLength() > 0 ) { msg_queue.m_queue_date = queue_date_nodes.item(0).getFirstChild().getNodeValue(); }
            
	    NodeList the_msg_nodes = msg_queue_element.getElementsByTagName("msg");
	    if ( the_msg_nodes.getLength() > 0 )
            {
                Element msg_element = (Element)the_msg_nodes.item(0);
                msg_queue.m_msg = new epp_ExtMessage();
                msg_queue.m_msg.m_lang  = msg_element.getAttribute("lang");
                msg_queue.m_msg.m_value = msg_element.getFirstChild().getNodeValue();
            }
            
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
        root.setAttribute("xmlns", "urn:ietf:params:xml:ns:epp-1.0");
        root.setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
        root.setAttribute("xsi:schemaLocation", "urn:ietf:params:xml:ns:epp-1.0 epp-1.0.xsd");

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
     * Given a Document with all of its children elements appended, converts
     * this document to an XML Snippet.
     * The difference between this and createXMLFromDoc() is that the XML
     * headers are not included.
     * @param Document the complete XML Document
     * @return String the XML String
     * @throws java.io.IOException
     */
    protected String createXMLSnippetFromDoc(Document doc) throws IOException
    {
        String method_name = "createXMLSnippetFromDoc()";
        debug(DEBUG_LEVEL_THREE,method_name,"Entered");

        OutputFormat format = new OutputFormat( doc );
        format.setOmitDocumentType(true);
        format.setOmitXMLDeclaration(true);
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
     * Creates an EPP <Object:authInfo> Element.
     * @param Document the Document instance that is creating the Elements
     * @param String the tag's name
     * @param epp_AuthInfo element that contains authorization information
     * @return Element the resulting EPP <Object:authInfo> Element
     */
    protected Element prepareAuthInfo(Document doc,
                                      String object_name,
                                      epp_AuthInfo auth_info)
    {
        Element auth_info_element = doc.createElement(object_name+":authInfo");

        if ( auth_info.m_type == null )
        {
            // At the time of writing this, the two values possible
            // were PW or EXT.  PW is the most common, so let's default it.
            auth_info.m_type = epp_AuthInfoType.PW;
        }

        Element authinfo_sub_el = addXMLElement(doc, auth_info_element, object_name+":"+auth_info.m_type, auth_info.m_value);

        if ( auth_info.m_roid != null && auth_info.m_roid.length() > 0 )
        {
            authinfo_sub_el.setAttribute( "roid", auth_info.m_roid );
        }

        return auth_info_element;
    }

    /**
     * Given the chkData XML node, it extracts the check value, object availability and reason text.
     * Please see the Xerces documentation for more information regarding Nodes.
     * @param Node EPP chkData node
     * @return epp_CheckResult[] the generic check results array
     * @throws org.openrtk.idl.epp0705.epp_XMLException if the node list does not contain the data
     */
    protected epp_CheckResult[] parseGenericCheckResults(Node chkdata_node) throws epp_XMLException
    {
        String method_name = "parseGenericCheckResults(Node)";
        debug(DEBUG_LEVEL_THREE,method_name,"Entered");

	if (chkdata_node == null || chkdata_node.getChildNodes().getLength() == 0)
	{
	    throw new epp_XMLException("missing check results");
	}
 
	NodeList check_result_list = chkdata_node.getChildNodes();
	debug(DEBUG_LEVEL_TWO,method_name,"chkData's node count ["+check_result_list.getLength()+"]");

	List check_list = (List) new ArrayList();

	for (int count = 0; count < check_result_list.getLength(); count++)
	{
	    Node a_node = check_result_list.item(count);
	    if (! a_node.getNodeName().endsWith(":cd")) continue;

	    NodeList one_check_list = a_node.getChildNodes();
	    if (one_check_list.getLength() == 0)
	    {
		throw new epp_XMLException("missing check result");
	    }

	    epp_CheckResult chk_result = new epp_CheckResult();
	    for (int i = 0; i < one_check_list.getLength(); i++)
	    {
		Node the_node = one_check_list.item(i);
		if (the_node.getNodeName().endsWith(":name") ||
		    the_node.getNodeName().endsWith(":id"))
		{
		    chk_result.m_value = the_node.getFirstChild().getNodeValue();
		    chk_result.m_avail = ((Element)the_node).getAttribute("avail").equals("1") ? true : false;
		}
		if (the_node.getNodeName().endsWith(":reason"))
		{
		    chk_result.m_reason = the_node.getFirstChild().getNodeValue();
		    chk_result.m_lang = ((Element)the_node).getAttribute("lang");
		}
	    }
	    check_list.add(chk_result);
	}

	debug(DEBUG_LEVEL_THREE,method_name,"Leaving");
	return (epp_CheckResult[])convertListToArray(epp_CheckResult.class, check_list);
    }

    /**
     * Given an array of epp_CheckResult's, finds the matching value
     * and returns it's exists value in a Boolean object.
     * This method works for Check Results on any object.
     * @param epp_CheckResult[] array of check results.
     * @param String the value for which to search
     * @return Boolean the exists boolean in a Boolean object. Will return
     *                 null if the array is null or if the value was not found.
     * @deprecated Please use getAvailResultFor(epp_CheckResult[] check_results,String check_value)
     */
    public static Boolean getCheckResultFor(epp_CheckResult[] check_results,
                                            String check_value)
    {
        Boolean wanted_result = getAvailResultFor(check_results, check_value);

        if ( wanted_result != null ) wanted_result = new Boolean(! wanted_result.booleanValue());

        return wanted_result;
    }

    /**
     * Given an array of epp_CheckResult's, finds the matching value
     * and returns it's avail value in a Boolean object.
     * This method works for Check Results on any object.
     * @param epp_CheckResult[] array of check results.
     * @param String the value for which to search
     * @return Boolean the available boolean in a Boolean object. Will return
     *                 null if the array is null or if the value was not found.
     */
    public static Boolean getAvailResultFor(epp_CheckResult[] check_results,
                                            String check_value)
    {
        Boolean wanted_result = null;

        if ( check_results != null )
        {
            for ( int index = 0; index < check_results.length; index++ )
            {
                if ( check_results[index].m_value.equalsIgnoreCase(check_value) )
                {
                    wanted_result = new Boolean( check_results[index].getAvail() );
                    break;
                }
            }
        }

        return wanted_result;
    }

    /**
     * Gets the Node representing the extension xml tag.
     * @return Element for the extension XML
     * @throws IOException if there is an error getting the bytes from the XML
     * @throws SAXException if there are XML errors
     * @throws RuntimeException if the XML document does not support Traversal v2.0
     */
    protected Node getExtensionNode(String extension_string) throws IOException, SAXException
    {
        String method_name = "getExtensionElement()";
        debug(DEBUG_LEVEL_THREE,method_name,"Entered");
        DOMParser parser = new DOMParser();
        EPPXMLErrors errors = new EPPXMLErrors();
        parser.setErrorHandler(errors);
        parser.setFeature("http://apache.org/xml/features/dom/defer-node-expansion", true);
        parser.setFeature("http://apache.org/xml/features/continue-after-fatal-error", false);
        parser.setFeature("http://apache.org/xml/features/dom/include-ignorable-whitespace", false);

        parser.parse(new InputSource(new ByteArrayInputStream(extension_string.getBytes())));
        Document document = parser.getDocument();

        if (!document.isSupported("Traversal", "2.0")) throw new RuntimeException("This DOM Document does not support Traversal");

        Node extension_node = (Node) document.getDocumentElement();
        debug(DEBUG_LEVEL_THREE,method_name,"Leaving");
        return extension_node;
    }

    /**
     * Method to change an epp_Extension instance to XML elements.
     */
    protected void prepareExtensionElement( Document doc,
                                            Element command,
                                            epp_Extension[] extensions )
                        throws epp_XMLException
    {
        String method_name = "prepareExtensionElement()";
        debug(DEBUG_LEVEL_THREE,method_name,"Entered");
        if ( extensions != null )
        {
            for ( int count = 0; count < extensions.length; count++ )
            {
                epp_Extension extension = extensions[count];
                
                String extension_string = extension.toXML();
                if ( extension_string != null &&
                     extension_string.length() != 0 )
                {
                    try
                    {
                        Element extension_element = doc.createElement("extension");
                        Node extension_node = getExtensionNode(extension_string);
                        if ( extension_node != null )
                        {
                            extension_node = doc.importNode( getExtensionNode(extension_string), true );
                            extension_element.appendChild( extension_node );
                        }
                        else
                        {
                            // if the extension node was null, then it wasn't valid XML or
                            // it is just a string (eg "hellothere") and this
                            // can be included as a raw string in the extension tag.
                            extension_element.appendChild( doc.createTextNode(extension_string) );
                        }

                        command.appendChild( extension_element );
                    }
                    catch ( Exception xcp )
                    {
                        debug(DEBUG_LEVEL_ONE,method_name,xcp);
                        throw new epp_XMLException( "error in extension XML ["+xcp.getClass().getName()+"] ["+xcp.getMessage()+"]" );
                    }
                }
            }
        }
        debug(DEBUG_LEVEL_THREE,method_name,"Leaving");
    }

    /**
     * Convenience method to get a transfer status string from an
     * epp_TransferStatusType object.
     */
    public static String transferStatusToString(epp_TransferStatusType status_type)
    {
        return status_type.toString();
    }

    public static epp_TransferStatusType transferStatusFromString(String s)
    {
	return (epp_TransferStatusType) transfer_status_to_type_hash_.get(s.toLowerCase());
    }

    public static String getPanData(NodeList pan_data_list, String namespace_uri, epp_PanData pan_data)
                    throws epp_XMLException
    {
        String the_key = "";
        
        for (int count = 0; count < pan_data_list.getLength(); count++)
        {
            Node a_node = pan_data_list.item(count);

            if ( a_node.getLocalName().equals("id") ||
                 a_node.getLocalName().equals("name") )
            {
                String paResult = ((Element)a_node).getAttribute("paResult");
                pan_data.setResult( ( paResult.equals("1") || paResult.equals("true") ? true : false ) );
                the_key = a_node.getFirstChild().getNodeValue();
            }
            if ( a_node.getLocalName().equals("paDate") ) { pan_data.setDate(a_node.getFirstChild().getNodeValue()); }
            if ( a_node.getLocalName().equals("paTRID") )
            {
                NodeList inner_nodes = a_node.getChildNodes();
                pan_data.setTrid(getTransID(a_node));
            }

        }

        return the_key;
    }

    public static epp_TransID getTransID(Node trans_id_node)
    {
        epp_TransID trans_id = new epp_TransID();

        NodeList inner_nodes = trans_id_node.getChildNodes();

        for (int count = 0; count < inner_nodes.getLength(); count++)
        {
            Node a_node = inner_nodes.item(count);

            if ( a_node.getLocalName().equals("clTRID") ) { trans_id.m_client_trid = a_node.getFirstChild().getNodeValue(); }
            if ( a_node.getLocalName().equals("svTRID") ) { trans_id.m_server_trid = a_node.getFirstChild().getNodeValue(); }
        }

        return trans_id;
    }
}
