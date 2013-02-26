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
 * $Header: /cvsroot/epp-rtk/epp-rtk/java/src/com/tucows/oxrs/epp0503/rtk/xml/EPPContactInfo.java,v 1.2 2006/04/24 21:20:25 ewang2004 Exp $
 * $Revision: 1.2 $
 * $Date: 2006/04/24 21:20:25 $
 */

package com.tucows.oxrs.epp0503.rtk.xml;

import java.io.*;
import java.util.*;
import java.text.*;

import com.tucows.oxrs.epp0503.rtk.*;
import org.openrtk.idl.epp0503.*;
import org.openrtk.idl.epp0503.contact.*;

import org.w3c.dom.*;
import org.w3c.dom.traversal.*;
import org.apache.xerces.parsers.*;
import org.xml.sax.*;
import org.apache.xerces.dom.*;
import org.apache.xml.serialize.*;

/**
 * Class for the EPP Contact Info command and response.
 * Extends the epp_ContactInfo interface from the EPP IDLs to provide
 * the XML translation for the EPP Contact Info command.</P>
 * The Info command provides a way of retrieving extended information on an object.
 * Usage is demonstrated in the com.tucows.oxrs.epp0503.rtk.example.ContactExample
 * class.
 * @see com.tucows.oxrs.epp0503.rtk.example.ContactExample
 * @see org.openrtk.idl.epp0503.epp_Action
 * @see org.openrtk.idl.epp0503.contact.epp_ContactInfo
 * @see org.openrtk.idl.epp0503.contact.epp_ContactInfoReq
 * @see org.openrtk.idl.epp0503.contact.epp_ContactInfoRsp
 * @see EPP Contact Spec for more information
 */
public class EPPContactInfo extends EPPContactBase implements epp_ContactInfo
{

    private epp_ContactInfoReq action_request_;
    private epp_ContactInfoRsp action_response_;

    /**
     * Default constructor
     */
    public EPPContactInfo () {}

    /**
     * Constructor with response XML string to automatically parse.
     * @param xml The EPP Contact Info response XML String
     * @throws org.openrtk.idl.epp0503.epp_XMLException if the response XML is not parsable or does not contain the expected data
     * @throws org.openrtk.idl.epp0503.epp_Exception if the server has responded with an error code 
     * @see #fromXML(String)
     */
    public EPPContactInfo (String xml) throws epp_XMLException, epp_Exception
    {
        String method_name = "EPPContactInfo(String)";
        debug(DEBUG_LEVEL_TWO,method_name,"xml is ["+xml+"]");
        fromXML(xml);
    }

    /**
     * Accessor method for the contact info request data.
     * Must be set to for this command.
     * @param value org.openrtk.idl.epp0503.epp_ContactInfoReq
     */
    public void setRequestData(epp_ContactInfoReq value) { action_request_ = value; }
    /**
     * Accessor method for the contact info response data.
     * @return value org.openrtk.idl.epp0503.epp_ContactInfoRsp
     */
    public epp_ContactInfoRsp getResponseData() { return action_response_; }

    /**
     * Builds request XML from the request data.
     * Implemented method from org.openrtk.idl.epp0503.epp_Action interface.
     * @throws epp_XMLException if required data is missing
     * @see #setRequestData(epp_ContactInfoReq)
     * @see org.openrtk.idl.epp0503.epp_Action
     */
    public String toXML() throws epp_XMLException
    {
        String method_name = "buildRequestXML()";
        debug(DEBUG_LEVEL_THREE,method_name,"Entered");

        if ( action_request_ == null || 
             action_request_.m_cmd == null ||
             action_request_.m_id == null )
        {
            throw new epp_XMLException("missing request data or contact id");
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
        
        Element contact_info = doc.createElement("contact:info");
        setCommonAttributes(contact_info);

        addXMLElement(doc, contact_info, "contact:id", action_request_.m_id);

        info.appendChild( contact_info );

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
     * @param xml A new XML String to parse
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

            action_response_ = new epp_ContactInfoRsp();
            
            action_response_.m_rsp = parseGenericResult(response_node);

            if ( action_response_.m_rsp.m_results[0].m_code >= epp_Session.EPP_UNKNOWN_COMMAND )
            {
                throw new epp_Exception(action_response_.m_rsp.m_results);
            }

            Element response_data_element = getElement(response_node.getChildNodes(), "resData");

            NodeList contact_info_result_list = response_data_element.getElementsByTagName("contact:infData").item(0).getChildNodes();

            debug(DEBUG_LEVEL_TWO,method_name,"contact:infData's node count ["+contact_info_result_list.getLength()+"]");

            if ( contact_info_result_list.getLength() == 0 )
            {
                throw new epp_XMLException("missing info results");
            }

            List statuses = (List)new ArrayList();

            for (int count = 0; count < contact_info_result_list.getLength(); count++)
            {
                Node a_node = contact_info_result_list.item(count);

                if ( a_node.getNodeName().equals("contact:id") ) { action_response_.m_id = a_node.getFirstChild().getNodeValue(); }
                if ( a_node.getNodeName().equals("contact:roid") ) { action_response_.m_roid = a_node.getFirstChild().getNodeValue(); }

                if ( a_node.getNodeName().equals("contact:ascii") ) { action_response_.m_ascii_address = addressFromXML(a_node); }
                if ( a_node.getNodeName().equals("contact:i15d") ) { action_response_.m_i15d_address = addressFromXML(a_node); }

                if ( a_node.getNodeName().equals("contact:voice") )
                {
                    action_response_.m_voice = new epp_ContactPhone();
                    action_response_.m_voice.m_value = a_node.getFirstChild().getNodeValue();
                    action_response_.m_voice.m_extension = ((Element)a_node).getAttribute("x");
                }
                if ( a_node.getNodeName().equals("contact:fax") )
                {
                    action_response_.m_fax = new epp_ContactPhone();
                    action_response_.m_fax.m_value = a_node.getFirstChild().getNodeValue();
                    action_response_.m_fax.m_extension = ((Element)a_node).getAttribute("x");
                }
                if ( a_node.getNodeName().equals("contact:email") ) { action_response_.m_email = a_node.getFirstChild().getNodeValue(); }

                if ( a_node.getNodeName().equals("contact:status") )
                {
                    epp_ContactStatus status = new epp_ContactStatus();
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
                    if ( ! contact_status_hash_.containsKey( ((Element)a_node).getAttribute("s") ) )
                    {
                        status.m_value = "invalid contact status from server";
                        status.m_lang = "en-US";
                        status.m_type = null;
                    }
                    else
                    {
                        status.m_type = (epp_ContactStatusType)contact_status_hash_.get( ((Element)a_node).getAttribute("s") );
                    }
                    statuses.add(status);
                }

                if ( a_node.getNodeName().equals("contact:clID") ) { action_response_.m_client_id = a_node.getFirstChild().getNodeValue(); }

                if ( a_node.getNodeName().equals("contact:crID") ) { action_response_.m_created_by = a_node.getFirstChild().getNodeValue(); }
                if ( a_node.getNodeName().equals("contact:crDate") ) { action_response_.m_created_date = a_node.getFirstChild().getNodeValue(); }
                if ( a_node.getNodeName().equals("contact:upID") ) { action_response_.m_updated_by = a_node.getFirstChild().getNodeValue(); }
                if ( a_node.getNodeName().equals("contact:upDate") ) { action_response_.m_updated_date = a_node.getFirstChild().getNodeValue(); }

                if ( a_node.getNodeName().equals("contact:trDate") ) { action_response_.m_transfer_date = a_node.getFirstChild().getNodeValue(); }

                if ( a_node.getNodeName().equals("contact:authInfo") )
                {
                    action_response_.m_auth_info = new epp_AuthInfo();
                    action_response_.m_auth_info.m_value = a_node.getFirstChild().getNodeValue();
                    action_response_.m_auth_info.m_type = (epp_AuthInfoType)auth_type_string_to_type_hash_.get( ((Element)a_node).getAttribute("type") );
                    action_response_.m_auth_info.m_roid = ((Element)a_node).getAttribute("roid");
                }
            }

            if ( statuses.size() > 0 ) { action_response_.m_status = (epp_ContactStatus[]) convertListToArray((new epp_ContactStatus()).getClass(), statuses); }

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
    
    private epp_ContactNameAddress addressFromXML(Node address_node) throws epp_XMLException
    {
        String method_name = "fromXML()";
        debug(DEBUG_LEVEL_THREE,method_name,"Entered");

        epp_ContactNameAddress name_address = new epp_ContactNameAddress();
        NodeList address_nodes = address_node.getChildNodes();
        
        for (int count = 0; count < address_nodes.getLength(); count++)
        {
            Node a_node = address_nodes.item(count);

            if ( a_node.getNodeName().equals("contact:name") ) { name_address.m_name = a_node.getFirstChild().getNodeValue(); }
            if ( a_node.getNodeName().equals("contact:org") ) { name_address.m_org = a_node.getFirstChild().getNodeValue(); }

            if ( a_node.getNodeName().equals("contact:addr") )
            {
                epp_ContactAddress address = new epp_ContactAddress();
                address.m_street1 = address.m_street2 = address.m_street3 = null;
                NodeList address_sub_nodes = a_node.getChildNodes();
                for (int count2 = 0; count2 < address_sub_nodes.getLength(); count2++)
                {
                    Node an_inner_node = address_sub_nodes.item(count2);

                    if ( an_inner_node.getNodeName().equals("contact:street") )
                    {
                        if ( address.m_street1 == null )
                        {
                            address.m_street1 = an_inner_node.getFirstChild().getNodeValue();
                        }
                        else if ( address.m_street2 == null )
                        {
                            address.m_street2 = an_inner_node.getFirstChild().getNodeValue();
                        }
                        else if ( address.m_street3 == null )
                        {
                            address.m_street3 = an_inner_node.getFirstChild().getNodeValue();
                        }
                        // No "else"... just ignoring situation where the
                        // server sends more street values than we expect.
                    }

                    if ( an_inner_node.getNodeName().equals("contact:city") ) { address.m_city = an_inner_node.getFirstChild().getNodeValue(); }
                    if ( an_inner_node.getNodeName().equals("contact:sp") ) { address.m_state_province = an_inner_node.getFirstChild().getNodeValue(); }
                    if ( an_inner_node.getNodeName().equals("contact:pc") ) { address.m_postal_code = an_inner_node.getFirstChild().getNodeValue(); }
                    if ( an_inner_node.getNodeName().equals("contact:cc") ) { address.m_country_code = an_inner_node.getFirstChild().getNodeValue(); }
                }
                name_address.m_address = address;
            }
            
        }
        
        debug(DEBUG_LEVEL_THREE,method_name,"Leaving");
        return name_address;
    }

}
