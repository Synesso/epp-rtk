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
 * $Header: /cvsroot/epp-rtk/epp-rtk/java/src/com/tucows/oxrs/epp0705/rtk/xml/EPPHostInfo.java,v 1.2 2006/04/24 21:20:25 ewang2004 Exp $
 * $Revision: 1.2 $
 * $Date: 2006/04/24 21:20:25 $
 */

package com.tucows.oxrs.epp0705.rtk.xml;

import java.io.*;
import java.util.*;
import java.text.*;

import com.tucows.oxrs.epp0705.rtk.*;
import org.openrtk.idl.epp0705.*;
import org.openrtk.idl.epp0705.host.*;

import org.w3c.dom.*;
import org.w3c.dom.traversal.*;
import org.apache.xerces.parsers.*;
import org.xml.sax.*;
import org.apache.xerces.dom.*;
import org.apache.xml.serialize.*;

/**
 * Class for the EPP Host Info command and response.
 * Extends the epp_HostInfo interface from the EPP IDLs to provide
 * the XML translation for the EPP Host Info command.</P>
 * The Info command provides a way of retrieving extended information on an object.
 * Usage is demonstrated in the com.tucows.oxrs.epp0705.rtk.example.HostExample
 * class.
 * @see com.tucows.oxrs.epp0705.rtk.example.HostExample
 * @see org.openrtk.idl.epp0705.epp_Action
 * @see org.openrtk.idl.epp0705.host.epp_HostInfo
 * @see org.openrtk.idl.epp0705.host.epp_HostInfoReq
 * @see org.openrtk.idl.epp0705.host.epp_HostInfoRsp
 * @see EPP Host Spec for more information
 */
public class EPPHostInfo extends EPPHostBase implements epp_HostInfo
{

    private epp_HostInfoReq action_request_;
    private epp_HostInfoRsp action_response_;

    /**
     * Default constructor
     */
    public EPPHostInfo () {}

    /**
     * Constructor with response XML string to automatically parse.
     * @param xml The EPP Host Info response XML String
     * @throws org.openrtk.idl.epp0705.epp_XMLException if the response XML is not parsable or does not contain the expected data
     * @throws org.openrtk.idl.epp0705.epp_Exception if the server has responded with an error code 
     * @see #fromXML(String)
     */
    public EPPHostInfo (String xml) throws epp_XMLException, epp_Exception
    {
        String method_name = "EPPHostInfo(String)";
        debug(DEBUG_LEVEL_TWO,method_name,"xml is ["+xml+"]");
        fromXML(xml);
    }

    /**
     * Accessor method for the host info request data.
     * Must be set to for this command.
     * @param value org.openrtk.idl.epp0705.epp_HostInfoReq
     */
    public void setRequestData(epp_HostInfoReq value) { action_request_ = value; }
    /**
     * Accessor method for the host info response data.
     * @return value org.openrtk.idl.epp0705.epp_HostInfoRsp
     */
    public epp_HostInfoRsp getResponseData() { return action_response_; }

    /**
     * Builds request XML from the request data.
     * Implemented method from org.openrtk.idl.epp0705.epp_Action interface.
     * @throws epp_XMLException if required data is missing
     * @see #setRequestData(epp_HostInfoReq)
     * @see org.openrtk.idl.epp0705.epp_Action
     */
    public String toXML() throws epp_XMLException
    {
        String method_name = "buildRequestXML()";
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
        Element info = doc.createElement("info");

        epp_Command command_data = action_request_.m_cmd;

        Element host_info = doc.createElement("host:info");
        setCommonAttributes(host_info);

        addXMLElement(doc, host_info, "host:name", action_request_.m_name);

        info.appendChild( host_info );

        command.appendChild( info );

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
     * Implemented method from org.openrtk.idl.epp0705.epp_Action interface.
     * @param A new XML String to parse
     * @throws epp_XMLException if the response XML is not parsable or does not contain the expected data
     * @throws org.openrtk.idl.epp0705.epp_Exception if the server has responded with an error code 
     * @see org.openrtk.idl.epp0705.epp_Action
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

            action_response_ = new epp_HostInfoRsp();
            
            action_response_.m_rsp = parseGenericResult(response_node);

            if ( action_response_.m_rsp.m_results[0].m_code >= epp_Session.EPP_UNKNOWN_COMMAND )
            {
                throw new epp_Exception(action_response_.m_rsp.m_results);
            }

            Element response_data_element = getElement(response_node.getChildNodes(), "resData");

            NodeList host_info_result_list = response_data_element.getElementsByTagName("host:infData").item(0).getChildNodes();

            debug(DEBUG_LEVEL_TWO,method_name,"host:infData's node count ["+host_info_result_list.getLength()+"]");

            if ( host_info_result_list.getLength() == 0 )
            {
                throw new epp_XMLException("missing info results");
            }

            List addresses = (List)new ArrayList();
            List statuses = (List)new ArrayList();

            for (int count = 0; count < host_info_result_list.getLength(); count++)
            {
                Node a_node = host_info_result_list.item(count);

                if ( a_node.getNodeName().equals("host:name") ) { action_response_.m_name = a_node.getFirstChild().getNodeValue(); }
                if ( a_node.getNodeName().equals("host:roid") ) { action_response_.m_roid = a_node.getFirstChild().getNodeValue(); }

                if ( a_node.getNodeName().equals("host:addr") )
                {
                    epp_HostAddress address = new epp_HostAddress();
                    address.m_ip = a_node.getFirstChild().getNodeValue();
                    if ( ! host_ip_to_type_hash_.containsKey( ((Element)a_node).getAttribute("ip") ) )
                    {
                        address.m_type = epp_HostAddressType.IPV4;
                    }
                    else
                    {
                        address.m_type = (epp_HostAddressType)host_ip_to_type_hash_.get( ((Element)a_node).getAttribute("ip") );
                    }
                    addresses.add(address);
                }

                if ( a_node.getNodeName().equals("host:status") )
                {
                    epp_HostStatus status = new epp_HostStatus();
                    Node status_value_node = a_node.getFirstChild();
                    if ( status_value_node != null )
                    {
                        status.m_value = status_value_node.getNodeValue();
                    }
                    String lang = ((Element)a_node).getAttribute("lang");
                    if (lang != null && lang.length() > 0)
                    {
                        status.m_lang = lang;
                    }
                    if ( ! host_status_hash_.containsKey( ((Element)a_node).getAttribute("s") ) )
                    {
                        status.m_value = "invalid host status from server";
                        status.m_lang = "en";
                        status.m_type = null;
                    }
                    else
                    {
                        status.m_type = (epp_HostStatusType)host_status_hash_.get( ((Element)a_node).getAttribute("s") );
                    }
                    statuses.add(status);
                }

                if ( a_node.getNodeName().equals("host:clID") ) { action_response_.m_client_id = a_node.getFirstChild().getNodeValue(); }

                if ( a_node.getNodeName().equals("host:crID") ) { action_response_.m_created_by = a_node.getFirstChild().getNodeValue(); }
                if ( a_node.getNodeName().equals("host:crDate") ) { action_response_.m_created_date = a_node.getFirstChild().getNodeValue(); }
                if ( a_node.getNodeName().equals("host:upID") ) { action_response_.m_updated_by = a_node.getFirstChild().getNodeValue(); }
                if ( a_node.getNodeName().equals("host:upDate") ) { action_response_.m_updated_date = a_node.getFirstChild().getNodeValue(); }

                if ( a_node.getNodeName().equals("host:trDate") ) { action_response_.m_transfer_date = a_node.getFirstChild().getNodeValue(); }
            }

            if ( addresses.size() > 0 ) { action_response_.m_addresses = (epp_HostAddress[]) convertListToArray((new epp_HostAddress()).getClass(), addresses); }
            if ( statuses.size() > 0 ) { action_response_.m_status = (epp_HostStatus[]) convertListToArray((new epp_HostStatus()).getClass(), statuses); }

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
