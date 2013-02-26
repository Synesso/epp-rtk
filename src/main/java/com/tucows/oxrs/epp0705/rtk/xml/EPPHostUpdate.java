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
 * $Header: /cvsroot/epp-rtk/epp-rtk/java/src/com/tucows/oxrs/epp0705/rtk/xml/EPPHostUpdate.java,v 1.1 2003/03/20 22:42:00 tubadanm Exp $
 * $Revision: 1.1 $
 * $Date: 2003/03/20 22:42:00 $
 */

package com.tucows.oxrs.epp0705.rtk.xml;

import java.io.*;
import java.util.*;
import java.text.*;

import com.tucows.oxrs.epp0705.rtk.*;
import org.openrtk.idl.epp0705.*;
import org.openrtk.idl.epp0705.host.*;

import org.w3c.dom.*;
import org.w3c.dom.traversal.*;
import org.apache.xerces.parsers.*;
import org.xml.sax.*;
import org.apache.xerces.dom.*;
import org.apache.xml.serialize.*;

/**
 * Class for the EPP Host Update command and response.
 * Extends the epp_HostUpdate interface from the EPP IDLs to provide
 * the XML translation for the EPP Host Update command.</P>
 * The Update command allows the sponsoring client to change data associated
 * with an EPP object.</P>
 * Usage is demonstrated in the com.tucows.oxrs.epp0705.rtk.example.HostExample
 * class.
 * @see com.tucows.oxrs.epp0705.rtk.example.HostExample
 * @see org.openrtk.idl.epp0705.epp_Action
 * @see org.openrtk.idl.epp0705.host.epp_HostUpdate
 * @see org.openrtk.idl.epp0705.host.epp_HostUpdateReq
 * @see org.openrtk.idl.epp0705.host.epp_HostUpdateRsp
 * @see EPP Host Spec for more information
 */
public class EPPHostUpdate extends EPPHostBase implements epp_HostUpdate
{

    private epp_HostUpdateReq action_request_;
    private epp_HostUpdateRsp action_response_;

    /**
     * Default constructor
     */
    public EPPHostUpdate () {}

    /**
     * Constructor with response XML string to automatically parse.
     * @param xml The EPP Host Update response XML String
     * @throws org.openrtk.idl.epp0705.epp_XMLException if the response XML is not parsable or does not contain the expected data
     * @throws org.openrtk.idl.epp0705.epp_Exception if the server has responded with an error code 
     * @see #fromXML(String)
     */
    public EPPHostUpdate (String xml) throws epp_XMLException, epp_Exception
    {
        String method_name = "EPPHostUpdate(String)";
        debug(DEBUG_LEVEL_TWO,method_name,"xml is ["+xml+"]");
        fromXML(xml);
    }

    /**
     * Accessor method for the host update request data.
     * Must be set to for this command.
     * @param value org.openrtk.idl.epp0705.epp_HostUpdateReq
     */
    public void setRequestData(epp_HostUpdateReq value) { action_request_ = value; }
    /**
     * Accessor method for the host update response data.
     * @return value org.openrtk.idl.epp0705.epp_HostUpdateRsp
     */
    public epp_HostUpdateRsp getResponseData() { return action_response_; }

    /**
     * Builds request XML from the request data.
     * Implemented method from org.openrtk.idl.epp0705.epp_Action interface.
     * @throws epp_XMLException if required data is missing
     * @see #setRequestData(epp_HostUpdateReq)
     * @see org.openrtk.idl.epp0705.epp_Action
     */
    public String toXML() throws epp_XMLException
    {
        String method_name = "buildRequestXML()";
        debug(DEBUG_LEVEL_THREE,method_name,"Entered");

        if ( action_request_ == null || 
             action_request_.m_cmd == null ||
             action_request_.m_name == null )
        {
            throw new epp_XMLException("missing request data or host name");
        }

        Document doc = new DocumentImpl();
        Element root = createDocRoot(doc);

        Element command = doc.createElement("command");
        Element update = doc.createElement("update");

        epp_Command command_data = action_request_.m_cmd;

        Element host_update = doc.createElement("host:update");
        setCommonAttributes(host_update);

        addXMLElement(doc, host_update, "host:name", action_request_.m_name);

        Element add_element = getAddRemoveElement(doc,
                                                  action_request_.m_add,
                                                  "host:add");
        if ( add_element != null )
        {
            host_update.appendChild( add_element );
        }

        Element remove_element = getAddRemoveElement(doc,
                                                     action_request_.m_remove,
                                                     "host:rem");
        if ( remove_element != null )
        {
            host_update.appendChild( remove_element );
        }

        if ( action_request_.m_change != null &&
             action_request_.m_change.m_name != null )
        {
            Element change_element = doc.createElement("host:chg");
            addXMLElement(doc, change_element, "host:name", action_request_.m_change.m_name);
            host_update.appendChild( change_element );
        }
        
        update.appendChild( host_update );

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
                                        epp_HostUpdateAddRemove add_remove_items,
                                        String tag_name)
                            throws epp_XMLException
    {
        String method_name = "getAddRemoveElement(Document, epp_HostUpdateAddRemove, String)";
        debug(DEBUG_LEVEL_THREE,method_name,"Entered");

        Element add_remove_element = null;

        if ( add_remove_items != null )
        {
            if ( add_remove_items.m_addresses != null &&
                 add_remove_items.m_addresses.length > 0 )
            {
                add_remove_element = doc.createElement(tag_name);

                List status_list = Arrays.asList(add_remove_items.m_addresses);
                for (Iterator it = status_list.iterator(); it.hasNext();)
                {
                    epp_HostAddress address = (epp_HostAddress)it.next();
                    Element addr_element = addXMLElement(doc, add_remove_element, "host:addr", address.m_ip);
                    addr_element.setAttribute("ip", address.m_type.toString());
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
                    epp_HostStatus status = (epp_HostStatus)it.next();
                    Element status_element = addXMLElement(doc, add_remove_element, "host:status", status.m_value);
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

            action_response_ = new epp_HostUpdateRsp();
            
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
