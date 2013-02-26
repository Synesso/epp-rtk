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
 * $Header: /cvsroot/epp-rtk/epp-rtk/java/src/com/tucows/oxrs/epp0402/rtk/xml/EPPGreeting.java,v 1.1 2003/03/21 16:35:37 tubadanm Exp $
 * $Revision: 1.1 $
 * $Date: 2003/03/21 16:35:37 $
 */

package com.tucows.oxrs.epp0402.rtk.xml;

import java.io.*;
import java.util.*;
import java.lang.reflect.*;

import com.tucows.oxrs.epp0402.rtk.*;
import org.openrtk.idl.epp0402.*;

import org.w3c.dom.*;
import org.w3c.dom.traversal.*;
import org.apache.xerces.parsers.*;
import org.xml.sax.*;
import org.apache.xerces.dom.*;
import org.apache.xml.serialize.*;

/**
 * Class to represent the EPP greeting from the server.
 *
 * @author Daniel Manley
 * @version $Revision: 1.1 $ $Date: 2003/03/21 16:35:37 $
 * @see com.tucows.oxrs.epp0402.rtk.EPPClient
 */
public class EPPGreeting extends EPPXMLBase implements epp_Hello
{

    private epp_Greeting greeting_;

    /**
     * Default constructor.
     */
    public EPPGreeting () {}

    /**
     * Constructor with greeting XML string to automatically parse.
     * @param xml The EPP Greeting XML String
     * @throws org.openrtk.idl.epp0402.epp_XMLException if the response XML is not parsable or does not contain the expected data
     * @throws org.openrtk.idl.epp0402.epp_Exception if the server has responded with an error code 
     * @see #fromXML(String)
     */
    public EPPGreeting (String xml) throws epp_XMLException, epp_Exception
    {
        String method_name = "EPPGreeting(String)";
        debug(DEBUG_LEVEL_TWO,method_name,"xml is ["+xml+"]");
        fromXML(xml);
    }

    /**
     * Accessor method for the greeting data.
     * @return value org.openrtk.idl.epp0402.epp_Greeting
     */
    public epp_Greeting getResponseData() { return greeting_; }

    /**
     * Builds request XML from the request data.
     * Implemented method from org.openrtk.idl.epp0402.epp_Action interface.
     * Note that there is no request data to set for this action.
     * @throws epp_XMLException if required data is missing
     * @see org.openrtk.idl.epp0402.epp_Action
     */
    public String toXML() throws epp_XMLException
    {
        String method_name = "buildRequestXML()";
        debug(DEBUG_LEVEL_THREE,method_name,"Entered");

        Document doc = new DocumentImpl();
        Element root = createDocRoot(doc);
        
        Element hello = doc.createElement("hello");

        root.appendChild( hello );
        doc.appendChild( root );
        
        String request_xml;
        
            try
        {
            request_xml = createXMLFromDoc(doc);
        }
        catch (IOException xcp)
        {
            throw new epp_XMLException("IOException in building XML ["+xcp.getMessage()+"]");
        }

        debug(DEBUG_LEVEL_THREE,method_name,"Leaving");

        return request_xml;
    }

    /**
     * Parses a new XML String and populates the response data member.
     * Implemented method from org.openrtk.idl.epp0402.epp_Action interface.
     * @param A new XML String to parse
     * @throws epp_XMLException if the response XML is not parsable or does not contain the expected data
     * @throws org.openrtk.idl.epp0402.epp_Exception if the server has responded with an error code 
     * @see org.openrtk.idl.epp0402.epp_Action
     */
    public void fromXML(String xml) throws epp_XMLException, epp_Exception
    {
        String method_name = "fromXML()";
        debug(DEBUG_LEVEL_THREE,method_name,"Entered");

        xml_ = xml;

        try
        {
            greeting_ = new epp_Greeting();
            
            Element epp_node = getDocumentElement();
            NodeList greeting_nodes = epp_node.getElementsByTagName("greeting").item(0).getChildNodes();

            if ( greeting_nodes == null || greeting_nodes.getLength() == 0 )
            {
                throw new epp_XMLException("unparsable or missing greeting");
            }

            Node service_menu = null;
	    Node unspec_service_menu = null;
            
            for (int count = 0; count < greeting_nodes.getLength(); count++)
            {
                Node a_node = greeting_nodes.item(count);
    
                if ( a_node.getNodeName().equals("svID") ) { greeting_.m_server_id = a_node.getFirstChild().getNodeValue(); }
                if ( a_node.getNodeName().equals("svDate") ) { greeting_.m_server_date = a_node.getFirstChild().getNodeValue(); }
                if ( a_node.getNodeName().equals("svcMenu") ) { service_menu = a_node; }
            }

            if (service_menu != null)
            {
                NodeList inner_nodes = service_menu.getChildNodes();
                
                List service_list = (List)new ArrayList();
                List version_list = (List)new ArrayList();
                List lang_list = (List)new ArrayList();
                
                for (int count = 0; count < inner_nodes.getLength(); count++)
                {
                    Node a_node = inner_nodes.item(count);
        
                    if ( a_node.getNodeName().equals("version") ) { version_list.add(a_node.getFirstChild().getNodeValue()); }
                    if ( a_node.getNodeName().equals("lang") ) { lang_list.add(a_node.getFirstChild().getNodeValue()); }
		    if ( a_node.getNodeName().equals("unspec") ) { unspec_service_menu = a_node; }
                    if ( a_node.getNodeName().endsWith(":svc") )
                    {
                        epp_Service service = new epp_Service();
                        String name = a_node.getNodeName();
                        int index_of_colon = name.indexOf(':');
			service.m_name = name.substring(0, index_of_colon);
                        service.m_xmlns = ((Element)a_node).getAttribute("xmlns:"+service.m_name);
                        service.m_schema_location = ((Element)a_node).getAttribute("xsi:schemaLocation");
                        service_list.add(service);
                    }
                }
                
                greeting_.m_versions = convertListToStringArray(version_list);
                greeting_.m_langs = convertListToStringArray(lang_list);
                greeting_.m_services = (epp_Service[]) convertListToArray((new epp_Service()).getClass(),
                                                                          service_list);
            }
	    
	    if (unspec_service_menu != null)
            {
                NodeList inner_nodes = unspec_service_menu.getChildNodes();
		List unspec_service_list = (List)new ArrayList();

                for (int count = 0; count < inner_nodes.getLength(); count++)
                {
                    Node a_node = inner_nodes.item(count);
        
                    if ( a_node.getNodeName().endsWith(":svc") )
                    {
                        epp_Service service = new epp_Service();
                        String name = a_node.getNodeName();
                        int index_of_colon = name.indexOf(':');
			service.m_name = name.substring(0, index_of_colon);
                        service.m_xmlns = ((Element)a_node).getAttribute("xmlns:"+service.m_name);
                        service.m_schema_location = ((Element)a_node).getAttribute("xsi:schemaLocation");
                        unspec_service_list.add(service);
                    }
                }		

                if (unspec_service_list.size() > 0 )
		{
	            greeting_.m_unspec_services = (epp_Service[]) convertListToArray((new epp_Service()).getClass(), unspec_service_list);
		}
            }
		    
        }
        catch (SAXException xcp)
        {
            debug(DEBUG_LEVEL_ONE,method_name,xcp);
            throw new epp_XMLException("unable to parse xml ["+xcp.getClass().getName()+"] ["+xcp.getMessage()+"]");
        }
        catch (IOException xcp)
        {
            debug(DEBUG_LEVEL_ONE,method_name,xcp);
            throw new epp_XMLException("unable to parse xml ["+xcp.getClass().getName()+"] ["+xcp.getMessage()+"]");
        }

    }
    
}
