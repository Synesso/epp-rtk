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
 * $Header: /cvsroot/epp-rtk/epp-rtk/java/src/com/tucows/oxrs/epp0503/rtk/xml/EPPDomainInfo.java,v 1.2 2006/04/24 20:28:15 ewang2004 Exp $
 * $Revision: 1.2 $
 * $Date: 2006/04/24 20:28:15 $
 */

package com.tucows.oxrs.epp0503.rtk.xml;

import java.io.*;
import java.util.*;
import java.text.*;

import com.tucows.oxrs.epp0503.rtk.*;
import org.openrtk.idl.epp0503.*;
import org.openrtk.idl.epp0503.domain.*;

import org.w3c.dom.*;
import org.w3c.dom.traversal.*;
import org.apache.xerces.parsers.*;
import org.xml.sax.*;
import org.apache.xerces.dom.*;
import org.apache.xml.serialize.*;

/**
 * Class for the EPP Domain Info command and response.
 * Extends the epp_DomainInfo interface from the EPP IDLs to provide
 * the XML translation for the EPP Domain Info command.</P>
 * The Info command provides a way of retrieving extended information on an object.
 * Usage is demonstrated in the com.tucows.oxrs.epp0503.rtk.example.DomainExample
 * class.
 * @see com.tucows.oxrs.epp0503.rtk.example.DomainExample
 * @see org.openrtk.idl.epp0503.epp_Action
 * @see org.openrtk.idl.epp0503.domain.epp_DomainInfo
 * @see org.openrtk.idl.epp0503.domain.epp_DomainInfoReq
 * @see org.openrtk.idl.epp0503.domain.epp_DomainInfoRsp
 * @see EPP Domain Spec for more information
 */
public class EPPDomainInfo extends EPPDomainBase implements epp_DomainInfo
{

    private epp_DomainInfoReq action_request_;
    private epp_DomainInfoRsp action_response_;

    /**
     * Default constructor
     */
    public EPPDomainInfo () {}

    /**
     * Constructor with response XML string to automatically parse.
     * @param xml The EPP Domain Info response XML String
     * @throws org.openrtk.idl.epp0503.epp_XMLException if the response XML is not parsable or does not contain the expected data
     * @throws org.openrtk.idl.epp0503.epp_Exception if the server has responded with an error code 
     * @see #fromXML(String)
     */
    public EPPDomainInfo (String xml) throws epp_XMLException, epp_Exception
    {
        String method_name = "EPPDomainInfo(String)";
        debug(DEBUG_LEVEL_TWO,method_name,"xml is ["+xml+"]");
        fromXML(xml);
    }

    /**
     * Accessor method for the domain info request data.
     * Must be set to for this command.
     * @param value org.openrtk.idl.epp0503.epp_DomainInfoReq
     */
    public void setRequestData(epp_DomainInfoReq value) { action_request_ = value; }
    /**
     * Accessor method for the domain info response data.
     * @return value org.openrtk.idl.epp0503.epp_DomainInfoRsp
     */
    public epp_DomainInfoRsp getResponseData() { return action_response_; }

    /**
     * Builds request XML from the request data.
     * Implemented method from org.openrtk.idl.epp0503.epp_Action interface.
     * @throws epp_XMLException if required data is missing
     * @see #setRequestData(epp_DomainInfoReq)
     * @see org.openrtk.idl.epp0503.epp_Action
     */
    public String toXML() throws epp_XMLException
    {
        String method_name = "buildRequestXML()";
        debug(DEBUG_LEVEL_THREE,method_name,"Entered");

        if ( action_request_ == null || 
             action_request_.m_cmd == null ||
             action_request_.m_name == null )
        {
            throw new epp_XMLException("missing request data or domain name");
        }

        if ( action_request_.m_hosts_type == null )
        {
            debug(DEBUG_LEVEL_TWO,method_name,"hosts type is null, so assuming ALL");
            action_request_.m_hosts_type = epp_DomainHostsType.ALL;
        }

        Document doc = new DocumentImpl();
        Element root = createDocRoot(doc);

        Element command = doc.createElement("command");
        Element info = doc.createElement("info");

        epp_Command command_data = action_request_.m_cmd;

        if ( command_data.m_creds != null )
        {
            command.appendChild( prepareCreds( doc, command_data.m_creds ) );
        }
        
        Element domain_info = doc.createElement("domain:info");
        setCommonAttributes(domain_info);

        Element domain_name = addXMLElement(doc, domain_info, "domain:name", action_request_.m_name);
        domain_name.setAttribute("hosts", action_request_.m_hosts_type.toString());

        info.appendChild( domain_info );

        command.appendChild( info );

        prepareExtensionElement( doc, command, command_data.m_extension );

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
     * Implemented method from org.openrtk.idl.epp0503.epp_Action interface.
     * @param A new XML String to parse
     * @throws epp_XMLException if the response XML is not parsable or does not contain the expected data
     * @throws org.openrtk.idl.epp0503.epp_Exception if the server has responded with an error code 
     * @see org.openrtk.idl.epp0503.epp_Action
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

            action_response_ = new epp_DomainInfoRsp();
            
            action_response_.m_rsp = parseGenericResult(response_node);

            if ( action_response_.m_rsp.m_results[0].m_code >= epp_Session.EPP_UNKNOWN_COMMAND )
            {
                throw new epp_Exception(action_response_.m_rsp.m_results);
            }

            Element response_data_element = getElement(response_node.getChildNodes(), "resData");

            NodeList domain_info_result_list = response_data_element.getElementsByTagName("domain:infData").item(0).getChildNodes();

            debug(DEBUG_LEVEL_TWO,method_name,"domain:infData's node count ["+domain_info_result_list.getLength()+"]");

            if ( domain_info_result_list.getLength() == 0 )
            {
                throw new epp_XMLException("missing info results");
            }

            List statuses = (List)new ArrayList();
            List contacts = (List)new ArrayList();
            List name_servers = (List)new ArrayList();
            List hosts = (List)new ArrayList();

            for (int count = 0; count < domain_info_result_list.getLength(); count++)
            {
                Node a_node = domain_info_result_list.item(count);

                if ( a_node.getNodeName().equals("domain:name") ) { action_response_.m_name = a_node.getFirstChild().getNodeValue(); }
                if ( a_node.getNodeName().equals("domain:roid") ) { action_response_.m_roid = a_node.getFirstChild().getNodeValue(); }

                if ( a_node.getNodeName().equals("domain:registrant") ) { action_response_.m_registrant = a_node.getFirstChild().getNodeValue(); }
                if ( a_node.getNodeName().equals("domain:contact") )
                {
                    epp_DomainContact domain_contact = new epp_DomainContact();
                    domain_contact.m_id = a_node.getFirstChild().getNodeValue();
                    domain_contact.m_type = (epp_DomainContactType)contact_type_hash_.get( ((Element)a_node).getAttribute("type") );
                    contacts.add(domain_contact);
                }

                if ( a_node.getNodeName().equals("domain:status") )
                {
                    epp_DomainStatus status = new epp_DomainStatus();
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
                    if ( ! domain_status_hash_.containsKey( ((Element)a_node).getAttribute("s") ) )
                    {
                        status.m_value = "invalid domain status from server";
                        status.m_lang = "en";
                        status.m_type = null;
                    }
                    else
                    {
                        status.m_type = (epp_DomainStatusType)domain_status_hash_.get( ((Element)a_node).getAttribute("s") );
                    }
                    statuses.add(status);
                }

                if ( a_node.getNodeName().equals("domain:ns") ) { name_servers.add(a_node.getFirstChild().getNodeValue()); }
                if ( a_node.getNodeName().equals("domain:host") ) { hosts.add(a_node.getFirstChild().getNodeValue()); }

                if ( a_node.getNodeName().equals("domain:clID") ) { action_response_.m_client_id = a_node.getFirstChild().getNodeValue(); }

                if ( a_node.getNodeName().equals("domain:crID") ) { action_response_.m_created_by = a_node.getFirstChild().getNodeValue(); }
                if ( a_node.getNodeName().equals("domain:crDate") ) { action_response_.m_created_date = a_node.getFirstChild().getNodeValue(); }
                if ( a_node.getNodeName().equals("domain:upID") ) { action_response_.m_updated_by = a_node.getFirstChild().getNodeValue(); }
                if ( a_node.getNodeName().equals("domain:upDate") ) { action_response_.m_updated_date = a_node.getFirstChild().getNodeValue(); }

                if ( a_node.getNodeName().equals("domain:trDate") ) { action_response_.m_transfer_date = a_node.getFirstChild().getNodeValue(); }
                if ( a_node.getNodeName().equals("domain:exDate") ) { action_response_.m_expiration_date = a_node.getFirstChild().getNodeValue(); }

                if ( a_node.getNodeName().equals("domain:authInfo") )
                {
                    action_response_.m_auth_info = new epp_AuthInfo();
                    action_response_.m_auth_info.m_value = a_node.getFirstChild().getNodeValue();
                    action_response_.m_auth_info.m_type = (epp_AuthInfoType)auth_type_string_to_type_hash_.get( ((Element)a_node).getAttribute("type") );
                    action_response_.m_auth_info.m_roid = ((Element)a_node).getAttribute("roid");
                }
            }

            if ( name_servers.size() > 0 ) { action_response_.m_name_servers = convertListToStringArray(name_servers); }
            if ( hosts.size()        > 0 ) { action_response_.m_hosts        = convertListToStringArray(hosts); }

            if ( contacts.size() > 0 ) { action_response_.m_contacts = (epp_DomainContact[]) convertListToArray((new epp_DomainContact()).getClass(), contacts); }
            if ( statuses.size() > 0 ) { action_response_.m_status   = (epp_DomainStatus[])  convertListToArray((new epp_DomainStatus()).getClass(),  statuses); }

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
