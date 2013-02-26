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
 * $Header: /cvsroot/epp-rtk/epp-rtk/java/src/com/tucows/oxrs/epp02/rtk/xml/EPPDomainUpdate.java,v 1.1 2003/03/21 17:13:42 tubadanm Exp $
 * $Revision: 1.1 $
 * $Date: 2003/03/21 17:13:42 $
 */

package com.tucows.oxrs.epp02.rtk.xml;

import java.io.*;
import java.util.*;
import java.text.*;

import com.tucows.oxrs.epp02.rtk.*;
import org.openrtk.idl.epp02.*;
import org.openrtk.idl.epp02.domain.*;

import org.w3c.dom.*;
import org.w3c.dom.traversal.*;
import org.apache.xerces.parsers.*;
import org.xml.sax.*;
import org.apache.xerces.dom.*;
import org.apache.xml.serialize.*;

/**
 * Class for the EPP Domain Update command and response.
 * Extends the epp_DomainUpdate interface from the EPP IDLs to provide
 * the XML translation for the EPP Domain Update command.</P>
 * The Update command allows the sponsoring client to change data associated
 * with an EPP object.</P>
 * Usage is demonstrated in the com.tucows.oxrs.epp02.rtk.example.DomainExample
 * class.
 * @see com.tucows.oxrs.epp02.rtk.example.DomainExample
 * @see org.openrtk.idl.epp02.epp_Action
 * @see org.openrtk.idl.epp02.domain.epp_DomainUpdate
 * @see org.openrtk.idl.epp02.domain.epp_DomainUpdateReq
 * @see org.openrtk.idl.epp02.domain.epp_DomainUpdateRsp
 * @see EPP Domain Spec for more information
 */
public class EPPDomainUpdate extends EPPDomainBase implements epp_DomainUpdate
{

    private epp_DomainUpdateReq action_request_;
    private epp_DomainUpdateRsp action_response_;

    /**
     * Default constructor
     */
    public EPPDomainUpdate () {}

    /**
     * Constructor with response XML string to automatically parse.
     * @param xml The EPP Domain Update response XML String
     * @throws org.openrtk.idl.epp02.epp_XMLException if the response XML is not parsable or does not contain the expected data
     * @throws org.openrtk.idl.epp02.epp_Exception if the server has responded with an error code 
     * @see #fromXML(String)
     */
    public EPPDomainUpdate (String xml) throws epp_XMLException, epp_Exception
    {
        String method_name = "EPPDomainUpdate(String)";
        debug(DEBUG_LEVEL_TWO,method_name,"xml is ["+xml+"]");
        fromXML(xml);
    }

    /**
     * Accessor method for the domain update request data.
     * Must be set to for this command.
     * @param value org.openrtk.idl.epp02.epp_DomainUpdateReq
     */
    public void setRequestData(epp_DomainUpdateReq value) { action_request_ = value; }
    /**
     * Accessor method for the domain update response data.
     * @return value org.openrtk.idl.epp02.epp_DomainUpdateRsp
     */
    public epp_DomainUpdateRsp getResponseData() { return action_response_; }

    /**
     * Builds request XML from the request data.
     * Implemented method from org.openrtk.idl.epp02.epp_Action interface.
     * @throws epp_XMLException if required data is missing
     * @see #setRequestData(epp_DomainUpdateReq)
     * @see org.openrtk.idl.epp02.epp_Action
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
        Element update = doc.createElement("update");

        epp_Command command_data = action_request_.m_cmd;

        if ( command_data.m_creds != null )
        {
            command.appendChild( prepareCreds( doc, command_data.m_creds ) );
        }
        
        Element domain_update = doc.createElement("domain:update");
        setCommonAttributes(domain_update);

        addXMLElement(doc, domain_update, "domain:name", action_request_.m_name);

        Element add_element = getAddRemoveElement(doc,
                                                  action_request_.m_add,
                                                  "domain:add");
        if ( add_element != null )
        {
            domain_update.appendChild( add_element );
        }

        Element remove_element = getAddRemoveElement(doc,
                                                     action_request_.m_remove,
                                                     "domain:rem");
        if ( remove_element != null )
        {
            domain_update.appendChild( remove_element );
        }

        if ( action_request_.m_change != null )
        {
            Element change_element = doc.createElement("domain:chg");

            if ( action_request_.m_change.m_registrant != null )
            {
                addXMLElement(doc, change_element, "domain:registrant", action_request_.m_change.m_registrant);
            }

            if ( action_request_.m_change.m_auth_info != null )
            {
                change_element.appendChild( prepareAuthInfo( doc, "domain:authInfo", action_request_.m_change.m_auth_info ) );
            }

            domain_update.appendChild( change_element );
        }
        
        update.appendChild( domain_update );

        command.appendChild( update );

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

    private Element getAddRemoveElement(Document doc,
                                        epp_DomainUpdateAddRemove add_remove_items,
                                        String tag_name)
                            throws epp_XMLException
    {
        String method_name = "getAddRemoveElement(Document, epp_DomainUpdateAddRemove, String)";
        debug(DEBUG_LEVEL_THREE,method_name,"Entered");

        Element add_remove_element = null;

        if ( add_remove_items != null )
        {
                
            if ( add_remove_items.m_name_servers != null &&
                 add_remove_items.m_name_servers.length > 0 )
            {
                add_remove_element = doc.createElement(tag_name);

                List server_list = Arrays.asList(add_remove_items.m_name_servers);
                for (Iterator it = server_list.iterator(); it.hasNext();)
                {
                    String server_name = (String)it.next();
                    addXMLElement(doc, add_remove_element, "domain:ns", server_name);
                }
            }

            if ( add_remove_items.m_contacts != null &&
                 add_remove_items.m_contacts.length > 0 )
            {
                if ( add_remove_element == null )
                {
                    add_remove_element = doc.createElement(tag_name);
                }

                List contact_list = Arrays.asList(add_remove_items.m_contacts);
                for (Iterator it = contact_list.iterator(); it.hasNext();)
                {
                    epp_DomainContact domain_contact = (epp_DomainContact)it.next();
                    Element contact_element = addXMLElement(doc, add_remove_element, "domain:contact", domain_contact.m_roid);
                    contact_element.setAttribute("type", contact_type_to_string_array_[domain_contact.m_type.value()]);
                }
            }

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
                    epp_DomainStatus status = (epp_DomainStatus)it.next();
                    Element status_element = addXMLElement(doc, add_remove_element, "domain:status", status.m_value);
                    if ( status.m_type.value() >= status_to_string_array_.length )
                    {
                        throw new epp_XMLException("invalid status in request data");
                    }
                    status_element.setAttribute("s", status_to_string_array_[status.m_type.value()]);
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

    /**
     * Parses a new XML String and populates the response data member.
     * Implemented method from org.openrtk.idl.epp02.epp_Action interface.
     * @param A new XML String to parse
     * @throws epp_XMLException if the response XML is not parsable or does not contain the expected data
     * @throws org.openrtk.idl.epp02.epp_Exception if the server has responded with an error code 
     * @see org.openrtk.idl.epp02.epp_Action
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

            action_response_ = new epp_DomainUpdateRsp();
            
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
