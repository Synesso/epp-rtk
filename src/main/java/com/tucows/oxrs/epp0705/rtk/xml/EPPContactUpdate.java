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
 * $Header: /cvsroot/epp-rtk/epp-rtk/java/src/com/tucows/oxrs/epp0705/rtk/xml/EPPContactUpdate.java,v 1.1 2003/03/20 22:42:00 tubadanm Exp $
 * $Revision: 1.1 $
 * $Date: 2003/03/20 22:42:00 $
 */

package com.tucows.oxrs.epp0705.rtk.xml;

import java.io.*;
import java.util.*;
import java.text.*;

import com.tucows.oxrs.epp0705.rtk.*;
import org.openrtk.idl.epp0705.*;
import org.openrtk.idl.epp0705.contact.*;

import org.w3c.dom.*;
import org.w3c.dom.traversal.*;
import org.apache.xerces.parsers.*;
import org.xml.sax.*;
import org.apache.xerces.dom.*;
import org.apache.xml.serialize.*;

/**
 * Class for the EPP Contact Update command and response.
 * Extends the epp_ContactUpdate interface from the EPP IDLs to provide
 * the XML translation for the EPP Contact Update command.</P>
 * The Update command allows the sponsoring client to change data associated
 * with an EPP object.</P>
 * Usage is demonstrated in the com.tucows.oxrs.epp0705.rtk.example.ContactExample
 * class.
 * @see com.tucows.oxrs.epp0705.rtk.example.ContactExample
 * @see org.openrtk.idl.epp0705.epp_Action
 * @see org.openrtk.idl.epp0705.contact.epp_ContactUpdate
 * @see org.openrtk.idl.epp0705.contact.epp_ContactUpdateReq
 * @see org.openrtk.idl.epp0705.contact.epp_ContactUpdateRsp
 * @see EPP Contact Spec for more information
 */
public class EPPContactUpdate extends EPPContactBase implements epp_ContactUpdate
{

    private epp_ContactUpdateReq action_request_;
    private epp_ContactUpdateRsp action_response_;

    /**
     * Default constructor
     */
    public EPPContactUpdate () {}

    /**
     * Constructor with response XML string to automatically parse.
     * @param xml The EPP Contact Update response XML String
     * @throws org.openrtk.idl.epp0705.epp_XMLException if the response XML is not parsable or does not contain the expected data
     * @throws org.openrtk.idl.epp0705.epp_Exception if the server has responded with an error code 
     * @see #fromXML(String)
     */
    public EPPContactUpdate (String xml) throws epp_XMLException, epp_Exception
    {
        String method_name = "EPPContactUpdate(String)";
        debug(DEBUG_LEVEL_TWO,method_name,"xml is ["+xml+"]");
        fromXML(xml);
    }

    /**
     * Accessor method for the contact update request data.
     * Must be set to for this command.
     * @param value org.openrtk.idl.epp0705.epp_ContactUpdateReq
     */
    public void setRequestData(epp_ContactUpdateReq value) { action_request_ = value; }
    /**
     * Accessor method for the contact update response data.
     * @return value org.openrtk.idl.epp0705.epp_ContactUpdateRsp
     */
    public epp_ContactUpdateRsp getResponseData() { return action_response_; }

    /**
     * Builds request XML from the request data.
     * Implemented method from org.openrtk.idl.epp0705.epp_Action interface.
     * @throws epp_XMLException if required data is missing
     * @see #setRequestData(epp_ContactUpdateReq)
     * @see org.openrtk.idl.epp0705.epp_Action
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
        Element update = doc.createElement("update");

        epp_Command command_data = action_request_.m_cmd;

        Element contact_update = doc.createElement("contact:update");
        setCommonAttributes(contact_update);

        addXMLElement(doc, contact_update, "contact:id", action_request_.m_id);

        Element add_element = getAddRemoveElement(doc,
                                                  action_request_.m_add,
                                                  "contact:add");
        if ( add_element != null )
        {
            contact_update.appendChild( add_element );
        }

        Element remove_element = getAddRemoveElement(doc,
                                                     action_request_.m_remove,
                                                     "contact:rem");
        if ( remove_element != null )
        {
            contact_update.appendChild( remove_element );
        }

        Element change_element = getChangeElement(doc,
                                                  action_request_.m_change,
                                                  "contact:chg");
        if ( change_element != null )
        {
            contact_update.appendChild( change_element );
        }

        update.appendChild( contact_update );

        command.appendChild( update );

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

    private Element getAddRemoveElement(Document doc,
                                        epp_ContactUpdateAddRemove add_remove_items,
                                        String tag_name)
                            throws epp_XMLException
    {
        String method_name = "getAddRemoveElement(Document, epp_ContactUpdateAddRemove, String)";
        debug(DEBUG_LEVEL_THREE,method_name,"Entered");

        Element add_remove_element = null;

        if ( add_remove_items != null )
        {
            if ( add_remove_items.m_status != null &&
                 add_remove_items.m_status.length > 0 )
            {
                if ( add_remove_element == null )
                {
                    add_remove_element = doc.createElement(tag_name);
                }

                List status_list = Arrays.asList(add_remove_items.m_status);
                for (Iterator it = status_list.iterator(); it.hasNext();)
                {
                    epp_ContactStatus status = (epp_ContactStatus)it.next();
                    Element status_element = addXMLElement(doc, add_remove_element, "contact:status", status.m_value);
                    status_element.setAttribute("s", status.m_type.toString());
                    if ( status.m_lang != null )
                    {
                        status_element.setAttribute("lang", status.m_lang);
                    }
                }
            }

        }

        debug(DEBUG_LEVEL_THREE,method_name,"Leaving");

        return add_remove_element;
    }

    private Element getChangeElement(Document doc,
                                     epp_ContactUpdateChange change,
                                     String tag_name)
                        throws epp_XMLException
    {
        String method_name = "fromXML()";
        debug(DEBUG_LEVEL_THREE,method_name,"Entered");

        Element change_element = null;
        
        if ( change != null )
        {
            change_element = doc.createElement(tag_name);

            if ( change.m_ascii_address != null )
            {
                // XXX the usage of m_ascii_address and m_i15d_address
                //     is deprecated, so this code exists for backward compatibility
                if ( change.m_ascii_address != null ) { change.m_ascii_address.m_type = epp_ContactPostalInfoType.INT; }
                Element ascii_address = addressToXML(doc, "contact:postalInfo", change.m_ascii_address);
                if ( ascii_address != null )
                {
                    change_element.appendChild(ascii_address);
                }

                if ( change.m_i15d_address != null ) { change.m_i15d_address.m_type = epp_ContactPostalInfoType.LOC; }
                Element i15d_address = addressToXML(doc, "contact:postalInfo", change.m_i15d_address);
                if ( i15d_address != null )
                {
                    change_element.appendChild(i15d_address);
                }
            }
            else if ( change.m_addresses != null &&
                      change.m_addresses.length > 0 )
            {
                List addresses = Arrays.asList(change.m_addresses);
                for (Iterator it = addresses.iterator(); it.hasNext();)
                {
                    Element address = addressToXML(doc, "contact:postalInfo", (epp_ContactNameAddress)it.next());
                    if ( address != null )
                    {
                        change_element.appendChild(address);
                    }
                }
            }

            if ( change.m_voice != null )
            {
                Element voice = addXMLElement(doc, change_element, "contact:voice", change.m_voice.m_value);
                if ( change.m_voice.m_extension != null )
                {
                    voice.setAttribute("x", change.m_voice.m_extension);
                }
            }
            if ( change.m_fax != null )
            {
                Element voice = addXMLElement(doc, change_element, "contact:fax", change.m_fax.m_value);
                if ( change.m_fax.m_extension != null )
                {
                    voice.setAttribute("x", change.m_fax.m_extension);
                }
            }
            if ( change.m_email != null ) { addXMLElement(doc, change_element, "contact:email", change.m_email); }

            if ( change.m_auth_info != null )
            {
                change_element.appendChild( prepareAuthInfo( doc, "contact", change.m_auth_info ) );
            }
            
        }
        
        debug(DEBUG_LEVEL_THREE,method_name,"Leaving");
        return change_element;
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

            action_response_ = new epp_ContactUpdateRsp();
            
            action_response_.m_rsp = parseGenericResult(response_node);

            if ( action_response_.m_rsp.m_results[0].m_code >= epp_Session.EPP_UNKNOWN_COMMAND )
            {
                throw new epp_Exception(action_response_.m_rsp.m_results);
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
