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
 * $Header: /cvsroot/epp-rtk/epp-rtk/java/src/com/tucows/oxrs/epp0503/rtk/xml/EPPDomainTransfer.java,v 1.1 2003/03/21 16:18:22 tubadanm Exp $
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
 * Class for the EPP Domain Transfer command and response.
 * Extends the epp_DomainTransfer interface from the EPP IDLs to provide
 * the XML translation for the EPP Domain Transfer command.</P>
 * The Transfer command provides a way of changing the ownership of an object
 * from one sponsor to another.  Various transfer operations allow a sponsor
 * to query the current state of a transfer, or request, approve, cancel or
 * reject a transfer.</P>
 * Usage is demonstrated in the com.tucows.oxrs.epp0503.rtk.example.DomainExample
 * class.
 * @see com.tucows.oxrs.epp0503.rtk.example.DomainExample
 * @see org.openrtk.idl.epp0503.epp_Action
 * @see org.openrtk.idl.epp0503.domain.epp_DomainTransfer
 * @see org.openrtk.idl.epp0503.domain.epp_DomainTransferReq
 * @see org.openrtk.idl.epp0503.domain.epp_DomainTransferRsp
 * @see The General EPP Spec and the EPP Domain Spec for more information on the Transfer command
 */
public class EPPDomainTransfer extends EPPDomainBase implements epp_DomainTransfer
{

    private epp_DomainTransferReq action_request_;
    private epp_DomainTransferRsp action_response_;

    /**
     * Default constructor
     */
    public EPPDomainTransfer () {}

    /**
     * Constructor with response XML string to automatically parse.
     * @param xml The EPP Domain Transfer response XML String
     * @throws org.openrtk.idl.epp0503.epp_XMLException if the response XML is not parsable or does not contain the expected data
     * @throws org.openrtk.idl.epp0503.epp_Exception if the server has responded with an error code 
     * @see #fromXML(String)
     */
    public EPPDomainTransfer (String xml) throws epp_XMLException, epp_Exception
    {
        String method_name = "EPPDomainTransfer(String)";
        debug(DEBUG_LEVEL_TWO,method_name,"xml is ["+xml+"]");
        fromXML(xml);
    }

    /**
     * Accessor method for the domain transfer request data.
     * Must be set to for this command.
     * @param value org.openrtk.idl.epp0503.epp_DomainTransferReq
     */
    public void setRequestData(epp_DomainTransferReq value) { action_request_ = value; }
    /**
     * Accessor method for the domain transfer response data.
     * @param value org.openrtk.idl.epp0503.epp_DomainTransferRsp
     */
    public epp_DomainTransferRsp getResponseData() { return action_response_; }

    /**
     * Builds request XML from the request data.
     * Implemented method from org.openrtk.idl.epp0503.epp_Action interface.
     * @throws epp_XMLException if required data is missing
     * @see #setRequestData(epp_DomainTransferReq)
     * @see org.openrtk.idl.epp0503.epp_Action
     */
    public String toXML() throws epp_XMLException
    {
        String method_name = "toXML()";
        debug(DEBUG_LEVEL_THREE,method_name,"Entered");

        if ( action_request_ == null || 
             action_request_.m_cmd == null ||
             action_request_.m_trans == null ||
             action_request_.m_name == null )
        {
            throw new epp_XMLException("missing request data or domain name");
        }

        epp_Command command_data = action_request_.m_cmd;
        epp_TransferRequest transfer_request_data = action_request_.m_trans;
        
        if ( transfer_request_data.m_op == null )
        {
            debug(DEBUG_LEVEL_TWO,method_name,"transfer op is null, so assuming query");
            transfer_request_data.m_op = epp_TransferOpType.QUERY;
        }

        Document doc = new DocumentImpl();
        Element root = createDocRoot(doc);
        
        Element command = doc.createElement("command");

        if ( command_data.m_creds != null )
        {
            command.appendChild( prepareCreds( doc, command_data.m_creds ) );
        }
        
        Element transfer = doc.createElement("transfer");
        transfer.setAttribute("op", transfer_request_data.m_op.toString());

        Element domain_transfer = doc.createElement("domain:transfer");
        
        setCommonAttributes(domain_transfer);

        addXMLElement(doc, domain_transfer, "domain:name", action_request_.m_name);

        if ( action_request_.m_period != null )
        {
            epp_DomainPeriod domain_period = action_request_.m_period;
            Element period = addXMLElement(doc, domain_transfer, "domain:period", Short.toString(domain_period.m_value));
            period.setAttribute("unit", domain_period.m_unit.toString());
        }

        transfer.appendChild( domain_transfer );

        if ( transfer_request_data.m_auth_info != null )
        {
            domain_transfer.appendChild( prepareAuthInfo( doc, "domain:authInfo", transfer_request_data.m_auth_info ) );
        }
        else if ( transfer_request_data.m_op == epp_TransferOpType.REQUEST)
        {
            throw new epp_XMLException("missing request auth id");
        }

        command.appendChild( transfer );

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

            action_response_ = new epp_DomainTransferRsp();
            
            action_response_.m_rsp = parseGenericResult(response_node);

            if ( action_response_.m_rsp.m_results[0].m_code >= epp_Session.EPP_UNKNOWN_COMMAND )
            {
                throw new epp_Exception(action_response_.m_rsp.m_results);
            }

            Element response_data_element = getElement(response_node.getChildNodes(), "resData");

            NodeList domain_transfer_data_list = response_data_element.getElementsByTagName("domain:trnData").item(0).getChildNodes();

            debug(DEBUG_LEVEL_TWO,method_name,"domain:trnData's node count ["+domain_transfer_data_list.getLength()+"]");

            if ( domain_transfer_data_list.getLength() == 0 )
            {
                throw new epp_XMLException("missing domain transfer data");
            }

            action_response_.m_trn_data = getTrnData(domain_transfer_data_list);

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
