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
 * $Header: /cvsroot/epp-rtk/epp-rtk/java/src/com/tucows/oxrs/epp0402/rtk/xml/EPPLogin.java,v 1.1 2003/03/21 16:35:37 tubadanm Exp $
 * $Revision: 1.1 $
 * $Date: 2003/03/21 16:35:37 $
 */

package com.tucows.oxrs.epp0402.rtk.xml;

import java.io.*;
import java.util.*;

import com.tucows.oxrs.epp0402.rtk.*;
import org.openrtk.idl.epp0402.*;

import org.w3c.dom.*;
import org.w3c.dom.traversal.*;
import org.apache.xerces.parsers.*;
import org.xml.sax.*;
import org.apache.xerces.dom.*;
import org.apache.xml.serialize.*;

/**
 * Class to represent the EPP login to the server.
 *
 * @author Daniel Manley
 * @version $Revision: 1.1 $ $Date: 2003/03/21 16:35:37 $
 * @see com.tucows.oxrs.epp0402.rtk.EPPClient
**/
public class EPPLogin extends EPPXMLBase implements epp_Login
{

    private epp_LoginReq action_request_;
    private epp_LoginRsp action_response_;

    /**
     * Default constructor.
     */
    public EPPLogin () {}

    /**
     * Constructor with response XML string to automatically parse.
     * @param xml The EPP Login response XML String
     * @throws org.openrtk.idl.epp0402.epp_XMLException if the response XML is not parsable or does not contain the expected data
     * @throws org.openrtk.idl.epp0402.epp_Exception if the server has responded with an error code 
     * @see #fromXML(String)
     */
    public EPPLogin (String xml) throws epp_XMLException, epp_Exception
    {
        String method_name = "EPPLogin(String)";
        debug(DEBUG_LEVEL_TWO,method_name,"xml is ["+xml+"]");
        fromXML(xml);
    }
    
    /**
     * Accessor method for the login request data.
     * Must be set to for this command.
     * @param value org.openrtk.idl.epp0402.epp_LoginReq
     */
    public void setRequestData(epp_LoginReq value) { action_request_ = value; }
    /**
     * Accessor method for the login response data.
     * @return value org.openrtk.idl.epp0402.epp_LoginRsp
     */
    public epp_LoginRsp getResponseData() { return action_response_; }

    /**
     * Builds request XML from the request data.
     * Implemented method from org.openrtk.idl.epp0402.epp_Action interface.
     * @throws epp_XMLException if required data is missing
     * @see #setRequestData(epp_LoginReq)
     * @see org.openrtk.idl.epp0402.epp_Action
     */
    public String toXML() throws epp_Exception, epp_XMLException
    {
        String method_name = "toXML(EPPLoginReq)";
        debug(DEBUG_LEVEL_THREE,method_name,"Entered");

        if ( action_request_ == null ||
             action_request_.m_cmd == null ||
             action_request_.m_cmd.m_creds == null )
        {
            throw new epp_XMLException("missing login request or creds data");
        }

        if ( action_request_.m_services == null )
        {
            throw new epp_XMLException("missing services in login");
        }

        Document doc = new DocumentImpl();
        Element root = createDocRoot(doc);
        
        Element command = doc.createElement("command");
        Element login = doc.createElement("login");

        epp_Command command_data = action_request_.m_cmd;

        Element creds = prepareCreds( doc, command_data.m_creds );

        command.appendChild( creds );
        
        // Now for services
        List services = Arrays.asList(action_request_.m_services);
        
        Element services_element = doc.createElement("svcs");

        for (Iterator it = services.iterator(); it.hasNext();)
        {
            epp_Service service = (epp_Service)it.next();
            Element service_element = doc.createElement(service.m_name+":svc");
            service_element.setAttribute("xmlns:"+service.m_name, service.m_xmlns);
            service_element.setAttribute("xsi:schemaLocation", service.m_schema_location);
            services_element.appendChild(service_element);
        }

        if (action_request_.m_unspec_services != null)
	{
            List unspec_services = Arrays.asList(action_request_.m_unspec_services);
        
            Element unspec_services_element = doc.createElement("unspec");

            for (Iterator it = unspec_services.iterator(); it.hasNext();)
            {
                epp_Service service = (epp_Service)it.next();
                Element service_element = doc.createElement(service.m_name+":svc");
                service_element.setAttribute("xmlns:"+service.m_name, service.m_xmlns);
                service_element.setAttribute("xsi:schemaLocation", service.m_schema_location);
                unspec_services_element.appendChild(service_element);
            }
	    
	    services_element.appendChild(unspec_services_element);
	}

        login.appendChild( services_element );
        
        command.appendChild( login );

        // unspec is a raw string for now.
        command.appendChild( prepareUnspecElement( doc, command_data.m_unspec ) );

        if ( command_data.m_client_trid != null )
        {
            addXMLElement(doc, command, "clTRID", command_data.m_client_trid);
        }

        root.appendChild( command );
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
            Element epp_node = getDocumentElement();
            Node response_node = epp_node.getElementsByTagName("response").item(0);

            if ( response_node == null )
            {
                throw new epp_XMLException("unparsable or missing response");
            }

            action_response_ = new epp_LoginRsp();
            
            action_response_.m_rsp = parseGenericResult(response_node);

            if ( action_response_.m_rsp.m_results[0].m_code >= epp_Session.EPP_UNKNOWN_COMMAND )
            {
                throw new epp_Exception(action_response_.m_rsp.m_results);
            }

        }
        catch (epp_Exception xcp)
        {
            debug(DEBUG_LEVEL_ONE,method_name,xcp);
            throw xcp;
        }
        catch (Exception xcp)
        {
            debug(DEBUG_LEVEL_ONE,method_name,xcp);
            throw new epp_XMLException("unable to parse xml ["+xcp.getClass().getName()+"] ["+xcp.getMessage()+"]");
        }

    }
    
}
