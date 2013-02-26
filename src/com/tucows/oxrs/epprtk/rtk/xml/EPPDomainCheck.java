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
 * $Header: /cvsroot/epp-rtk/epp-rtk/java/src/com/tucows/oxrs/epprtk/rtk/xml/EPPDomainCheck.java,v 1.1 2004/12/07 15:53:27 ewang2004 Exp $
 * $Revision: 1.1 $
 * $Date: 2004/12/07 15:53:27 $
 */

package com.tucows.oxrs.epprtk.rtk.xml;

import java.io.*;
import java.util.*;

import com.tucows.oxrs.epprtk.rtk.*;
import org.openrtk.idl.epprtk.*;
import org.openrtk.idl.epprtk.domain.*;

import org.w3c.dom.*;
import org.w3c.dom.traversal.*;
import org.apache.xerces.parsers.*;
import org.xml.sax.*;
import org.apache.xerces.dom.*;
import org.apache.xml.serialize.*;

/**
 * Class for the EPP Domain Check command and response.
 * Extends the epp_DomainCheck interface from the EPP IDLs to provide
 * the XML translation for the EPP Domain Check command.</P>
 * The Check command provides a way of telling if an object exists in the registry server.</P>
 * Usage is demonstrated in the com.tucows.oxrs.epprtk.rtk.example.DomainExample
 * class.
 * @see com.tucows.oxrs.epprtk.rtk.example.DomainExample
 * @see org.openrtk.idl.epprtk.epp_Action
 * @see org.openrtk.idl.epprtk.domain.epp_DomainCheck
 * @see org.openrtk.idl.epprtk.domain.epp_DomainCheckReq
 * @see org.openrtk.idl.epprtk.domain.epp_DomainCheckRsp
 * @see EPP Domain Spec for more information
 */
public class EPPDomainCheck extends EPPDomainBase implements epp_DomainCheck
{

    private epp_DomainCheckReq action_request_;
    private epp_DomainCheckRsp action_response_;

    /**
     * Default constructor
     */
    public EPPDomainCheck () {}

    /**
     * Constructor with response XML string to automatically parse.
     * @param xml The EPP Domain Check response XML String
     * @throws org.openrtk.idl.epprtk.epp_XMLException if the response XML is not parsable or does not contain the expected data
     * @throws org.openrtk.idl.epprtk.epp_Exception if the server has responded with an error code 
     * @see #fromXML(String)
     */
    public EPPDomainCheck (String xml) throws epp_XMLException, epp_Exception
    {
        String method_name = "EPPDomainCheck(String)";
        debug(DEBUG_LEVEL_TWO,method_name,"xml is ["+xml+"]");
        fromXML(xml);
    }

    /**
     * Accessor method for the domain check request data.
     * Must be set to for this command.
     * @param value org.openrtk.idl.epprtk.epp_DomainCheckReq
     */
    public void setRequestData(epp_DomainCheckReq value) { action_request_ = value; }
    /**
     * Accessor method for the domain check response data.
     * @return value org.openrtk.idl.epprtk.epp_DomainCheckRsp
     */
    public epp_DomainCheckRsp getResponseData() { return action_response_; }

    /**
     * Builds request XML from the request data.
     * Implemented method from org.openrtk.idl.epprtk.epp_Action interface.
     * @throws epp_XMLException if required data is missing
     * @see #setRequestData(epp_DomainCheckReq)
     * @see org.openrtk.idl.epprtk.epp_Action
     */
    public String toXML() throws epp_XMLException
    {
        String method_name = "buildRequestXML()";
        debug(DEBUG_LEVEL_THREE,method_name,"Entered");

        if ( action_request_ == null || 
             action_request_.m_cmd == null ||
             action_request_.m_names == null ||
             action_request_.m_names.length == 0 )
        {
            throw new epp_XMLException("missing request data");
        }

        Document doc = new DocumentImpl();
        Element root = createDocRoot(doc);

        Element command = doc.createElement("command");
        Element check = doc.createElement("check");

        epp_Command command_data = action_request_.m_cmd;

        Element domain_check = doc.createElement("domain:check");
        setCommonAttributes(domain_check);

        List domain_names = Arrays.asList(action_request_.m_names);
        
        for (Iterator it = domain_names.iterator(); it.hasNext();)
        {
            String domain_name = (String)it.next();
            addXMLElement(doc, domain_check, "domain:name", domain_name);
        }

        check.appendChild( domain_check );

        command.appendChild( check );

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
     * Implemented method from org.openrtk.idl.epprtk.epp_Action interface.
     * @param A new XML String to parse
     * @throws epp_XMLException if the response XML is not parsable or does not contain the expected data
     * @throws org.openrtk.idl.epprtk.epp_Exception if the server has responded with an error code 
     * @see org.openrtk.idl.epprtk.epp_Action
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

            action_response_ = new epp_DomainCheckRsp();
            
            action_response_.m_rsp = parseGenericResult(response_node);

            if ( action_response_.m_rsp.m_results[0].m_code >= epp_Session.EPP_UNKNOWN_COMMAND )
            {
                throw new epp_Exception(action_response_.m_rsp.m_results);
            }

            Element response_data_element = getElement(response_node.getChildNodes(), "resData");

            Node domain_check_data_node = response_data_element.getElementsByTagName("domain:chkData").item(0);

            action_response_.m_results = parseGenericCheckResults(domain_check_data_node);

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
