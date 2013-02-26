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
 * $Header: /cvsroot/epp-rtk/epp-rtk/java/src/com/tucows/oxrs/epp0402/rtk/xml/EPPContactCreate.java,v 1.1 2003/03/21 16:35:35 tubadanm Exp $
 * $Revision: 1.1 $
 * $Date: 2003/03/21 16:35:35 $
 */

package com.tucows.oxrs.epp0402.rtk.xml;

import java.io.*;
import java.util.*;
import java.text.*;

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
 * Class for the EPP Contact Create command and response.
 * Extends the epp_ContactCreate interface from the EPP IDLs to provide
 * the XML translation for the EPP Contact Create command.</P>
 * The Create command allows a client to create an instance of an EPP object in the server.</P>
 * Usage is demonstrated in the com.tucows.oxrs.epp0402.rtk.example.ContactExample
 * class.
 * @see com.tucows.oxrs.epp0402.rtk.example.ContactExample
 * @see org.openrtk.idl.epp0402.epp_Action
 * @see org.openrtk.idl.epp0402.contact.epp_ContactCreate
 * @see org.openrtk.idl.epp0402.contact.epp_ContactCreateReq
 * @see org.openrtk.idl.epp0402.contact.epp_ContactCreateRsp
 * @see EPP Contact Spec for more information
 */
public class EPPContactCreate extends EPPContactBase implements epp_ContactCreate
{

    private epp_ContactCreateReq action_request_;
    private epp_ContactCreateRsp action_response_;

    /**
     * Default constructor
     */
    public EPPContactCreate () {}

    /**
     * Constructor with response XML string to automatically parse.
     * @param xml The EPP Contact Create response XML String
     * @throws org.openrtk.idl.epp0402.epp_XMLException if the response XML is not parsable or does not contain the expected data
     * @throws org.openrtk.idl.epp0402.epp_Exception if the server has responded with an error code 
     * @see #fromXML(String)
     */
    public EPPContactCreate (String xml) throws epp_XMLException, epp_Exception
    {
        String method_name = "EPPContactCreate(String)";
        debug(DEBUG_LEVEL_TWO,method_name,"xml is ["+xml+"]");
        fromXML(xml);
    }

    /**
     * Accessor method for the contact create request data.
     * Must be set to for this command.
     * @param value org.openrtk.idl.epp0402.epp_ContactCreateReq
     */
    public void setRequestData(epp_ContactCreateReq value) { action_request_ = value; }
    /**
     * Accessor method for the contact create response data.
     * @return value org.openrtk.idl.epp0402.epp_ContactCreateRsp
     */
    public epp_ContactCreateRsp getResponseData() { return action_response_; }

    /**
     * Builds request XML from the request data.
     * Implemented method from org.openrtk.idl.epp0402.epp_Action interface.
     * @throws epp_XMLException if required data is missing
     * @see #setRequestData(epp_ContactCreateReq)
     * @see org.openrtk.idl.epp0402.epp_Action
     */
    public String toXML() throws epp_XMLException
    {
        String method_name = "buildRequestXML(EPPTransID)";
        debug(DEBUG_LEVEL_THREE,method_name,"Entered");

        if ( action_request_ == null || 
             action_request_.m_cmd == null )
        {
            throw new epp_XMLException("missing request data");
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
        
        Element contact_create = doc.createElement("contact:create");
        setCommonAttributes(contact_create);

        if ( action_request_.m_id == null )
        {
            throw new epp_XMLException( "missing contact id" );
        }
        addXMLElement(doc, contact_create, "contact:id", action_request_.m_id);

        Element ascii_address = addressToXML(doc, "contact:ascii", action_request_.m_ascii_address);
        if ( ascii_address != null )
        {
            contact_create.appendChild(ascii_address);
        }

        Element i15d_address = addressToXML(doc, "contact:i15d", action_request_.m_i15d_address);
        if ( i15d_address != null )
        {
            contact_create.appendChild(i15d_address);
        }

        if ( action_request_.m_voice != null )
        {
            Element voice = addXMLElement(doc, contact_create, "contact:voice", action_request_.m_voice.m_value);
            if ( action_request_.m_voice.m_extension != null )
            {
                voice.setAttribute("x", action_request_.m_voice.m_extension);
            }
        }
        if ( action_request_.m_fax != null )
        {
            Element voice = addXMLElement(doc, contact_create, "contact:fax", action_request_.m_fax.m_value);
            if ( action_request_.m_fax.m_extension != null )
            {
                voice.setAttribute("x", action_request_.m_fax.m_extension);
            }
        }
        if ( action_request_.m_email != null ) { addXMLElement(doc, contact_create, "contact:email", action_request_.m_email); }

        if ( action_request_.m_auth_info == null )
        {
            throw new epp_XMLException( "missing auth info" );
        }

        contact_create.appendChild( prepareAuthInfo( doc, "contact:authInfo", action_request_.m_auth_info ) );
        
        create.appendChild( contact_create );
        
        command.appendChild( create );

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

            action_response_ = new epp_ContactCreateRsp();
            
            action_response_.m_rsp = parseGenericResult(response_node);

            if ( action_response_.m_rsp.m_results[0].m_code >= epp_Session.EPP_UNKNOWN_COMMAND )
            {
                throw new epp_Exception(action_response_.m_rsp.m_results);
            }

            Element response_data_element = getElement(response_node.getChildNodes(), "resData");

            NodeList contact_create_data_list = response_data_element.getElementsByTagName("contact:creData").item(0).getChildNodes();

            debug(DEBUG_LEVEL_TWO,method_name,"contact:creData's node count ["+contact_create_data_list.getLength()+"]");

            if ( contact_create_data_list == null ||
                 contact_create_data_list.getLength() == 0 )
            {
                throw new epp_XMLException("missing contact create response data");
            }

            for (int count = 0; count < contact_create_data_list.getLength(); count++)
            {
                Node a_node = contact_create_data_list.item(count);

                if ( a_node.getNodeName().equals("contact:id") ) { action_response_.m_id = a_node.getFirstChild().getNodeValue(); }

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
