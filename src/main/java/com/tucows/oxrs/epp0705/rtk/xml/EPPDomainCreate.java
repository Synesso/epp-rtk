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
 * $Header: /cvsroot/epp-rtk/epp-rtk/java/src/com/tucows/oxrs/epp0705/rtk/xml/EPPDomainCreate.java,v 1.1 2003/03/20 22:42:00 tubadanm Exp $
 * $Revision: 1.1 $
 * $Date: 2003/03/20 22:42:00 $
 */

package com.tucows.oxrs.epp0705.rtk.xml;

import java.io.*;
import java.util.*;
import java.text.*;

import com.tucows.oxrs.epp0705.rtk.*;
import org.openrtk.idl.epp0705.*;
import org.openrtk.idl.epp0705.domain.*;

import org.w3c.dom.*;
import org.w3c.dom.traversal.*;
import org.apache.xerces.parsers.*;
import org.xml.sax.*;
import org.apache.xerces.dom.*;
import org.apache.xml.serialize.*;

/**
 * Class for the EPP Domain Create command and response.
 * Extends the epp_DomainCreate interface from the EPP IDLs to provide
 * the XML translation for the EPP Domain Create command.</P>
 * The Create command allows a client to create an instance of an EPP object in the server.</P>
 * Usage is demonstrated in the com.tucows.oxrs.epp0705.rtk.example.DomainExample
 * class.
 * @see com.tucows.oxrs.epp0705.rtk.example.DomainExample
 * @see org.openrtk.idl.epp0705.epp_Action
 * @see org.openrtk.idl.epp0705.domain.epp_DomainCreate
 * @see org.openrtk.idl.epp0705.domain.epp_DomainCreateReq
 * @see org.openrtk.idl.epp0705.domain.epp_DomainCreateRsp
 * @see EPP Domain Spec for more information
 */
public class EPPDomainCreate extends EPPDomainBase implements epp_DomainCreate
{

    private epp_DomainCreateReq action_request_;
    private epp_DomainCreateRsp action_response_;

    /**
     * Default constructor
     */
    public EPPDomainCreate () {}

    /**
     * Constructor with response XML string to automatically parse.
     * @param xml The EPP Domain Create response XML String
     * @throws org.openrtk.idl.epp0705.epp_XMLException if the response XML is not parsable or does not contain the expected data
     * @throws org.openrtk.idl.epp0705.epp_Exception if the server has responded with an error code 
     * @see #fromXML(String)
     */
    public EPPDomainCreate (String xml) throws epp_XMLException, epp_Exception
    {
        String method_name = "EPPDomainCreate(String)";
        debug(DEBUG_LEVEL_TWO,method_name,"xml is ["+xml+"]");
        fromXML(xml);
    }

    /**
     * Accessor method for the domain create request data.
     * Must be set to for this command.
     * @param value org.openrtk.idl.epp0705.epp_DomainCreateReq
     */
    public void setRequestData(epp_DomainCreateReq value) { action_request_ = value; }
    /**
     * Accessor method for the domain create response data.
     * @return value org.openrtk.idl.epp0705.epp_DomainCreateRsp
     */
    public epp_DomainCreateRsp getResponseData() { return action_response_; }

    /**
     * Builds request XML from the request data.
     * Implemented method from org.openrtk.idl.epp0705.epp_Action interface.
     * @throws epp_XMLException if required data is missing
     * @see #setRequestData(epp_DomainCreateReq)
     * @see org.openrtk.idl.epp0705.epp_Action
     */
    public String toXML() throws epp_XMLException
    {
        String method_name = "buildRequestXML(EPPTransID)";
        debug(DEBUG_LEVEL_THREE,method_name,"Entered");

        if ( action_request_ == null || 
             action_request_.m_cmd == null ||
             action_request_.m_name == null )
        {
            throw new epp_XMLException("missing request data or domain name");
        }

        Document doc = new DocumentImpl();
        Element root = createDocRoot(doc);

        Element command = doc.createElement("command");
        Element create = doc.createElement("create");

        epp_Command command_data = action_request_.m_cmd;

        Element domain_create = doc.createElement("domain:create");
        setCommonAttributes(domain_create);

        addXMLElement(doc, domain_create, "domain:name", action_request_.m_name);

        if ( action_request_.m_period != null )
        {
            epp_DomainPeriod domain_period = action_request_.m_period;
            Element period = addXMLElement(doc, domain_create, "domain:period", Short.toString(domain_period.m_value));
            period.setAttribute("unit", domain_period.m_unit.toString());
        }

        if ( action_request_.m_name_servers != null &&
             action_request_.m_name_servers.length > 0 )
        {
            List servers = Arrays.asList(action_request_.m_name_servers);
            for (Iterator it = servers.iterator(); it.hasNext();)
            {
                String server_name = (String)it.next();
                        addXMLElement(doc, domain_create, "domain:ns", server_name);
            }
        }

        if ( action_request_.m_registrant != null )
        {
            addXMLElement(doc, domain_create, "domain:registrant", action_request_.m_registrant);
        }

        if ( action_request_.m_contacts != null &&
             action_request_.m_contacts.length > 0 )
        {
            List contacts = Arrays.asList(action_request_.m_contacts);
            for (Iterator it = contacts.iterator(); it.hasNext();)
            {
                epp_DomainContact domain_contact = (epp_DomainContact)it.next();
                Element contact_element = addXMLElement(doc, domain_create, "domain:contact", domain_contact.m_id);
                contact_element.setAttribute("type", domain_contact.m_type.toString());
            }
        }

        if ( action_request_.m_auth_info == null )
        {
            throw new epp_XMLException( "missing auth info" );
        }

        domain_create.appendChild( prepareAuthInfo( doc, "domain", action_request_.m_auth_info ) );
        
        create.appendChild( domain_create );
        
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

            action_response_ = new epp_DomainCreateRsp();
            
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

            NodeList domain_create_data_list = response_data_element.getElementsByTagName("domain:creData").item(0).getChildNodes();

            debug(DEBUG_LEVEL_TWO,method_name,"domain:creData's node count ["+domain_create_data_list.getLength()+"]");

            if ( domain_create_data_list == null ||
                 domain_create_data_list.getLength() == 0 )
            {
                throw new epp_XMLException("missing domain create response data");
            }

            for (int count = 0; count < domain_create_data_list.getLength(); count++)
            {
                Node a_node = domain_create_data_list.item(count);

                if ( a_node.getNodeName().equals("domain:name") ) { action_response_.m_name = a_node.getFirstChild().getNodeValue(); }
                if ( a_node.getNodeName().equals("domain:crDate") ) { action_response_.m_creation_date = a_node.getFirstChild().getNodeValue(); }
                if ( a_node.getNodeName().equals("domain:exDate") ) { action_response_.m_expiration_date = a_node.getFirstChild().getNodeValue(); }

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
