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
 * $Header: /cvsroot/epp-rtk/epp-rtk/java/src/com/tucows/oxrs/epp0503/rtk/xml/EPPDomainRenew.java,v 1.1 2003/03/21 16:18:22 tubadanm Exp $
 * $Revision: 1.1 $
 * $Date: 2003/03/21 16:18:22 $
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
 * Class for the EPP Domain Renew command and response.
 * Extends the epp_DomainRenew interface from the EPP IDLs to provide
 * the XML translation for the EPP Domain Renew command.</P>
 * The Renew command provides a facility to extend the lifetime of an EPP object.</P>
 * Usage is demonstrated in the com.tucows.oxrs.epp0503.rtk.example.DomainExample
 * class.
 * @see com.tucows.oxrs.epp0503.rtk.example.DomainExample
 * @see org.openrtk.idl.epp0503.epp_Action
 * @see org.openrtk.idl.epp0503.domain.epp_DomainRenew
 * @see org.openrtk.idl.epp0503.domain.epp_DomainRenewReq
 * @see org.openrtk.idl.epp0503.domain.epp_DomainRenewRsp
 * @see EPP Domain Spec for more information
 */
public class EPPDomainRenew extends EPPDomainBase implements epp_DomainRenew
{

    private epp_DomainRenewReq action_request_;
    private epp_DomainRenewRsp action_response_;

    /**
     * Default constructor
     */
    public EPPDomainRenew () {}

    /**
     * Constructor with response XML string to automatically parse.
     * @param xml The EPP Domain Renew response XML String
     * @throws org.openrtk.idl.epp0503.epp_XMLException if the response XML is not parsable or does not contain the expected data
     * @throws org.openrtk.idl.epp0503.epp_Exception if the server has responded with an error code 
     * @see #fromXML(String)
     */
    public EPPDomainRenew (String xml) throws epp_XMLException, epp_Exception
    {
        String method_name = "EPPDomainRenew(String)";
        debug(DEBUG_LEVEL_TWO,method_name,"xml is ["+xml+"]");
        fromXML(xml);
    }

    /**
     * Accessor method for the domain renew request data.
     * Must be set to for this command.
     * @param value org.openrtk.idl.epp0503.epp_DomainRenewReq
     */
    public void setRequestData(epp_DomainRenewReq value) { action_request_ = value; }
    /**
     * Accessor method for the domain check response data.
     * @param value org.openrtk.idl.epp0503.epp_DomainRenewRsp
     */
    public epp_DomainRenewRsp getResponseData() { return action_response_; }

    /**
     * Builds request XML from the request data.
     * Implemented method from org.openrtk.idl.epp0503.epp_Action interface.
     * @throws epp_XMLException if required data is missing
     * @see #setRequestData(epp_DomainRenewReq)
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

        Document doc = new DocumentImpl();
        Element root = createDocRoot(doc);

        Element command = doc.createElement("command");
        Element renew = doc.createElement("renew");

        epp_Command command_data = action_request_.m_cmd;

        if ( command_data.m_creds != null )
        {
            command.appendChild( prepareCreds( doc, command_data.m_creds ) );
        }
        
        Element domain_renew = doc.createElement("domain:renew");
        setCommonAttributes(domain_renew);

        addXMLElement(doc, domain_renew, "domain:name", action_request_.m_name);
        addXMLElement(doc, domain_renew, "domain:curExpDate", action_request_.m_current_expiration_date);

        if ( action_request_.m_period != null )
        {
                epp_DomainPeriod domain_period = action_request_.m_period;
            Element period = addXMLElement(doc, domain_renew, "domain:period", Short.toString(domain_period.m_value));
            period.setAttribute("unit", domain_period.m_unit.toString());
        }

        renew.appendChild( domain_renew );

        command.appendChild( renew );

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

            action_response_ = new epp_DomainRenewRsp();
            
            action_response_.m_rsp = parseGenericResult(response_node);

            if ( action_response_.m_rsp.m_results[0].m_code >= epp_Session.EPP_UNKNOWN_COMMAND )
            {
                throw new epp_Exception(action_response_.m_rsp.m_results);
            }

            Element response_data_element = getElement(response_node.getChildNodes(), "resData");

            NodeList domain_renew_data_list = response_data_element.getElementsByTagName("domain:renData").item(0).getChildNodes();

            debug(DEBUG_LEVEL_TWO,method_name,"domain:renData's node count ["+domain_renew_data_list.getLength()+"]");

            if ( domain_renew_data_list == null ||
                 domain_renew_data_list.getLength() == 0 )
            {
                throw new epp_XMLException("missing domain renew response data");
            }

            for (int count = 0; count < domain_renew_data_list.getLength(); count++)
            {
                Node a_node = domain_renew_data_list.item(count);

                if ( a_node.getNodeName().equals("domain:name") ) { action_response_.m_name = a_node.getFirstChild().getNodeValue(); }
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
