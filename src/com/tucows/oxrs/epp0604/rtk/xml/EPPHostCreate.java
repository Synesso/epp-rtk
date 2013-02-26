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
 * $Header: /cvsroot/epp-rtk/epp-rtk/java/src/com/tucows/oxrs/epp0604/rtk/xml/EPPHostCreate.java,v 1.1 2003/03/21 15:53:10 tubadanm Exp $
 * $Revision: 1.1 $
 * $Date: 2003/03/21 15:53:10 $
 */

package com.tucows.oxrs.epp0604.rtk.xml;

import java.io.*;
import java.util.*;
import java.text.*;

import com.tucows.oxrs.epp0604.rtk.*;
import org.openrtk.idl.epp0604.*;
import org.openrtk.idl.epp0604.host.*;

import org.w3c.dom.*;
import org.w3c.dom.traversal.*;
import org.apache.xerces.parsers.*;
import org.xml.sax.*;
import org.apache.xerces.dom.*;
import org.apache.xml.serialize.*;

/**
 * Class for the EPP Host Create command and response.
 * Extends the epp_HostCreate interface from the EPP IDLs to provide
 * the XML translation for the EPP Host Create command.</P>
 * The Create command allows a client to create an instance of an EPP object in the server.</P>
 * Usage is demonstrated in the com.tucows.oxrs.epp0604.rtk.example.HostExample
 * class.
 * @see com.tucows.oxrs.epp0604.rtk.example.HostExample
 * @see org.openrtk.idl.epp0604.epp_Action
 * @see org.openrtk.idl.epp0604.host.epp_HostCreate
 * @see org.openrtk.idl.epp0604.host.epp_HostCreateReq
 * @see org.openrtk.idl.epp0604.host.epp_HostCreateRsp
 * @see EPP Host Spec for more information
 */
public class EPPHostCreate extends EPPHostBase implements epp_HostCreate
{

    private epp_HostCreateReq action_request_;
    private epp_HostCreateRsp action_response_;

    /**
     * Default constructor
     */
    public EPPHostCreate () {}

    /**
     * Constructor with response XML string to automatically parse.
     * @param xml The EPP Host Create response XML String
     * @throws org.openrtk.idl.epp0604.epp_XMLException if the response XML is not parsable or does not contain the expected data
     * @throws org.openrtk.idl.epp0604.epp_Exception if the server has responded with an error code 
     * @see #fromXML(String)
     */
    public EPPHostCreate (String xml) throws epp_XMLException, epp_Exception
    {
        String method_name = "EPPHostCreate(String)";
        debug(DEBUG_LEVEL_TWO,method_name,"xml is ["+xml+"]");
        fromXML(xml);
    }

    /**
     * Accessor method for the host create request data.
     * Must be set to for this command.
     * @param value org.openrtk.idl.epp0604.epp_HostCreateReq
     */
    public void setRequestData(epp_HostCreateReq value) { action_request_ = value; }
    /**
     * Accessor method for the host create response data.
     * @return value org.openrtk.idl.epp0604.epp_HostCreateRsp
     */
    public epp_HostCreateRsp getResponseData() { return action_response_; }

    /**
     * Builds request XML from the request data.
     * Implemented method from org.openrtk.idl.epp0604.epp_Action interface.
     * @throws epp_XMLException if required data is missing
     * @see #setRequestData(epp_HostCreateReq)
     * @see org.openrtk.idl.epp0604.epp_Action
     */
    public String toXML() throws epp_XMLException
    {
        String method_name = "buildRequestXML(EPPTransID)";
        debug(DEBUG_LEVEL_THREE,method_name,"Entered");

        if ( action_request_ == null || 
             action_request_.m_cmd == null ||
             action_request_.m_name == null )
        {
            throw new epp_XMLException("missing request data or host name");
        }

        Document doc = new DocumentImpl();
        Element root = createDocRoot(doc);

        Element command = doc.createElement("command");
        Element create = doc.createElement("create");

        epp_Command command_data = action_request_.m_cmd;

        if ( command_data.m_creds != null )
        {
            command.appendChild( prepareCreds( doc, command_data.m_creds ) );
        }
        
        Element host_create = doc.createElement("host:create");
        setCommonAttributes(host_create);

        addXMLElement(doc, host_create, "host:name", action_request_.m_name);

        if ( action_request_.m_addresses != null &&
             action_request_.m_addresses.length > 0 )
        {
            List addresses = Arrays.asList(action_request_.m_addresses);
            for (Iterator it = addresses.iterator(); it.hasNext();)
            {
                epp_HostAddress address = (epp_HostAddress)it.next();
                Element addr_element = addXMLElement(doc, host_create, "host:addr", address.m_ip);
                addr_element.setAttribute("ip", address.m_type.toString());
            }
        }

        create.appendChild( host_create );
        
        command.appendChild( create );

        prepareExtensionElement( doc, command, command_data.m_extensions );

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
     * Implemented method from org.openrtk.idl.epp0604.epp_Action interface.
     * @param A new XML String to parse
     * @throws epp_XMLException if the response XML is not parsable or does not contain the expected data
     * @throws org.openrtk.idl.epp0604.epp_Exception if the server has responded with an error code 
     * @see org.openrtk.idl.epp0604.epp_Action
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

            action_response_ = new epp_HostCreateRsp();
            
            action_response_.m_rsp = parseGenericResult(response_node);

            if ( action_response_.m_rsp.m_results[0].m_code >= epp_Session.EPP_UNKNOWN_COMMAND )
            {
                throw new epp_Exception(action_response_.m_rsp.m_results);
            }

            Element response_data_element = getElement(response_node.getChildNodes(), "resData");
            if ( response_data_element == null )
            {
                throw new epp_XMLException("response is missing the resData element");
            }

            NodeList host_create_data_list = response_data_element.getElementsByTagName("host:creData").item(0).getChildNodes();

            debug(DEBUG_LEVEL_TWO,method_name,"host:creData's node count ["+host_create_data_list.getLength()+"]");

            if ( host_create_data_list == null ||
                 host_create_data_list.getLength() == 0 )
            {
                throw new epp_XMLException("missing host create response data");
            }

            for (int count = 0; count < host_create_data_list.getLength(); count++)
            {
                Node a_node = host_create_data_list.item(count);

                if ( a_node.getNodeName().equals("host:name") ) { action_response_.m_name = a_node.getFirstChild().getNodeValue(); }
                if ( a_node.getNodeName().equals("host:crDate") ) { action_response_.m_creation_date = a_node.getFirstChild().getNodeValue(); }
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
