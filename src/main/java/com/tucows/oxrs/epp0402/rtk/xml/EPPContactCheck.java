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
 * $Header: /cvsroot/epp-rtk/epp-rtk/java/src/com/tucows/oxrs/epp0402/rtk/xml/EPPContactCheck.java,v 1.1 2003/03/21 16:35:35 tubadanm Exp $
 * $Revision: 1.1 $
 * $Date: 2003/03/21 16:35:35 $
 */

package com.tucows.oxrs.epp0402.rtk.xml;

import java.io.*;
import java.util.*;

import com.tucows.oxrs.epp0402.rtk.*;
import org.openrtk.idl.epp0402.*;
import org.openrtk.idl.epp0402.contact.*;

import org.w3c.dom.*;
import org.w3c.dom.traversal.*;
import org.apache.xerces.parsers.*;
import org.xml.sax.*;
import org.apache.xerces.dom.*;
import org.apache.xml.serialize.*;

/**
 * Class for the EPP Contact Check command and response.
 * Extends the epp_ContactCheck interface from the EPP IDLs to provide
 * the XML translation for the EPP Contact Check command.</P>
 * The Check command provides a way of telling if an object exists in the registry server.</P>
 * Usage is demonstrated in the com.tucows.oxrs.epp0402.rtk.example.ContactExample
 * class.
 * @see com.tucows.oxrs.epp0402.rtk.example.ContactExample
 * @see org.openrtk.idl.epp0402.epp_Action
 * @see org.openrtk.idl.epp0402.contact.epp_ContactCheck
 * @see org.openrtk.idl.epp0402.contact.epp_ContactCheckReq
 * @see org.openrtk.idl.epp0402.contact.epp_ContactCheckRsp
 * @see EPP Contact Spec for more information
 */
public class EPPContactCheck extends EPPContactBase implements epp_ContactCheck
{

    private epp_ContactCheckReq action_request_;
    private epp_ContactCheckRsp action_response_;

    /**
     * Default constructor
     */
    public EPPContactCheck () {}

    /**
     * Constructor with response XML string to automatically parse.
     * @param xml The EPP Contact Check response XML String
     * @throws org.openrtk.idl.epp0402.epp_XMLException if the response XML is not parsable or does not contain the expected data
     * @throws org.openrtk.idl.epp0402.epp_Exception if the server has responded with an error code 
     * @see #fromXML(String)
     */
    public EPPContactCheck (String xml) throws epp_XMLException, epp_Exception
    {
        String method_name = "EPPContactCheck(String)";
        debug(DEBUG_LEVEL_TWO,method_name,"xml is ["+xml+"]");
        fromXML(xml);
    }

    /**
     * Accessor method for the contact check request data.
     * Must be set to for this command.
     * @param value org.openrtk.idl.epp0402.epp_ContactCheckReq
     */
    public void setRequestData(epp_ContactCheckReq value) { action_request_ = value; }
    /**
     * Accessor method for the contact check response data.
     * @return value org.openrtk.idl.epp0402.epp_ContactCheckRsp
     */
    public epp_ContactCheckRsp getResponseData() { return action_response_; }

    /**
     * Builds request XML from the request data.
     * Implemented method from org.openrtk.idl.epp0402.epp_Action interface.
     * @throws epp_XMLException if required data is missing
     * @see #setRequestData(epp_ContactCheckReq)
     * @see org.openrtk.idl.epp0402.epp_Action
     */
    public String toXML() throws epp_XMLException
    {
        String method_name = "buildRequestXML()";
        debug(DEBUG_LEVEL_THREE,method_name,"Entered");

        if ( action_request_ == null || 
             action_request_.m_cmd == null ||
             action_request_.m_ids == null ||
             action_request_.m_ids.length == 0 )
        {
            throw new epp_XMLException("missing request data");
        }

        Document doc = new DocumentImpl();
        Element root = createDocRoot(doc);

        Element command = doc.createElement("command");
        Element check = doc.createElement("check");

        epp_Command command_data = action_request_.m_cmd;

        if ( command_data.m_creds != null )
        {
            command.appendChild( prepareCreds( doc, command_data.m_creds ) );
        }
        
        Element contact_check = doc.createElement("contact:check");
        setCommonAttributes(contact_check);

        List contact_ids = Arrays.asList(action_request_.m_ids);
        
        for (Iterator it = contact_ids.iterator(); it.hasNext();)
        {
            String contact_id = (String)it.next();
            addXMLElement(doc, contact_check, "contact:id", contact_id);
        }

        check.appendChild( contact_check );

        command.appendChild( check );

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

            action_response_ = new epp_ContactCheckRsp();
            
            action_response_.m_rsp = parseGenericResult(response_node);

            if ( action_response_.m_rsp.m_results[0].m_code >= epp_Session.EPP_UNKNOWN_COMMAND )
            {
                throw new epp_Exception(action_response_.m_rsp.m_results);
            }

            Element response_data_element = getElement(response_node.getChildNodes(), "resData");

            NodeList contact_check_result_list = response_data_element.getElementsByTagName("contact:chkData").item(0).getChildNodes();

            debug(DEBUG_LEVEL_TWO,method_name,"contact:chkData's node count ["+contact_check_result_list.getLength()+"]");

            if ( contact_check_result_list.getLength() == 0 )
            {
                throw new epp_XMLException("missing check results");
            }

            List check_list = (List) new ArrayList();
            
            for (int count = 0; count < contact_check_result_list.getLength(); count++)
            {
                Node a_node = contact_check_result_list.item(count);
                
                epp_CheckResult check_result = new epp_CheckResult();
                check_result.m_value = a_node.getFirstChild().getNodeValue();
                check_result.m_exists = ( ((Element)a_node).getAttribute("x").equals("+") ? true : false );
                check_list.add(check_result);
            }
            
            action_response_.m_results = (epp_CheckResult[]) convertListToArray((new epp_CheckResult()).getClass(), check_list);

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
