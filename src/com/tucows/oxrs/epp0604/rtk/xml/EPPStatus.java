/*
**
** EPP RTK Java
** Copyright (C) 2001, Tucows Inc.
**
** This program is free software; you can redistribute it and/or modify
** it under the terms of the GNU General Public License as published by
** the Free Software Foundation; either version 2 of the License, or
** (at your option) any later version.
**
** This program is distributed in the hope that it will be useful,
** but WITHOUT ANY WARRANTY; without even the implied warranty of
** MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
** GNU General Public License for more details.
** 
** You should have received a copy of the GNU General Public License
** along with this program; if not, write to the Free Software
** Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
**
*/

package com.tucows.oxrs.epp0604.rtk.xml;

import java.io.*;
import java.util.*;
import java.text.*;

import com.tucows.oxrs.epp0604.rtk.*;
import org.openrtk.idl.epp0604.*;

import org.w3c.dom.*;
import org.w3c.dom.traversal.*;
import org.apache.xerces.parsers.*;
import org.xml.sax.*;
import org.apache.xerces.dom.*;
import org.apache.xml.serialize.*;

import org.apache.regexp.*;


/**
 * </p>
 * $Header: /cvsroot/epp-rtk/epp-rtk/java/src/com/tucows/oxrs/epp0604/rtk/xml/EPPStatus.java,v 1.1 2003/03/21 15:53:21 tubadanm Exp $<br>
 * $Revision: 1.1 $<br>
 * $Date: 2003/03/21 15:53:21 $<br>
 */
public class EPPStatus extends EPPXMLBase implements epp_Status
{

    private epp_StatusReq action_request_;
    private epp_StatusRsp action_response_;
    
    private static Hashtable status_command_to_string_hash_;

    public EPPStatus () { initHash(); }

    public EPPStatus (String xml) throws epp_XMLException, epp_Exception
    {
        super(xml);
        String method_name = "EPPStatus(String)";
        debug(DEBUG_LEVEL_TWO,method_name,"xml is ["+xml+"]");
        initHash();
        fromXML(xml);
    }

    private void initHash()
    {
        if ( status_command_to_string_hash_ == null )
        {
            status_command_to_string_hash_ = new Hashtable();
            status_command_to_string_hash_.put(epp_StatusCommandType.CREATE, "create");
            status_command_to_string_hash_.put(epp_StatusCommandType.DELETE, "delete");
            status_command_to_string_hash_.put(epp_StatusCommandType.RENEW, "renew");
            status_command_to_string_hash_.put(epp_StatusCommandType.TRANSFER, "transfer");
            status_command_to_string_hash_.put(epp_StatusCommandType.UPDATE, "update");
        }
    }
    
    /**
     * Accessor method for the domain info request data.  Must be set to for this command.
     * @param value epp_LoginReq
     */
    public void setRequestData(epp_StatusReq value) { action_request_ = value; }
    /**
     * Accessor method for the domain info request data.  Must be set to for this command.
     * @return epp_LoginRsp
     */
    public epp_StatusRsp getResponseData() { return action_response_; }


    public String toXML() throws epp_XMLException
    {
        String method_name = "buildRequestXML()";
        debug(DEBUG_LEVEL_THREE,method_name,"Entered");

        if ( action_request_ == null )
        {
            throw new epp_XMLException("missing request data");
        }

        Document doc = new DocumentImpl();
        Element root = createDocRoot(doc);

        Element command = doc.createElement("command");
        Element status = doc.createElement("status");

        epp_Command command_data = action_request_.m_cmd;

        if ( command_data.m_creds != null )
        {
            command.appendChild( prepareCreds( doc, command_data.m_creds ) );
        }
        
        if ( action_request_.m_command == null )
        {
            throw new epp_XMLException("missing status command type (no default allowed)");
        }
        
        Element cltrid = addXMLElement(doc, status, "clTRID", action_request_.m_client_trid);;
        status.setAttribute("command", (String)status_command_to_string_hash_.get(action_request_.m_command));
        
        command.appendChild( status );

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
     * Parses the status response XML.
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

            action_response_ = new epp_StatusRsp();
            
            action_response_.m_rsp = parseGenericResult(response_node);

            if ( action_response_.m_rsp.m_results[0].m_code >= epp_Session.EPP_UNKNOWN_COMMAND )
            {
                throw new epp_Exception(action_response_.m_rsp.m_results);
            }

            // nothing more to parse... that's it.
            
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
