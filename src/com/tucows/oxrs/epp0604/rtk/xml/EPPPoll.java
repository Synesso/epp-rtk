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

/*
 * $Header: /cvsroot/epp-rtk/epp-rtk/java/src/com/tucows/oxrs/epp0604/rtk/xml/EPPPoll.java,v 1.2 2004/01/19 18:47:59 tubadanm Exp $
 * $Revision: 1.2 $
 * $Date: 2004/01/19 18:47:59 $
 */

package com.tucows.oxrs.epp0604.rtk.xml;

import java.io.*;
import java.util.*;
import java.text.*;

import com.tucows.oxrs.epp0604.rtk.*;
import com.tucows.oxrs.epp0604.rtk.xml.poll.*;
import org.openrtk.idl.epp0604.*;

import org.w3c.dom.*;
import org.w3c.dom.traversal.*;
import org.apache.xerces.parsers.*;
import org.xml.sax.*;
import org.apache.xerces.dom.*;
import org.apache.xml.serialize.*;

import org.apache.regexp.*;


public class EPPPoll extends EPPXMLBase implements epp_Poll
{

    private epp_PollReq action_request_;
    private epp_PollRsp action_response_;
    
    private static Hashtable poll_op_to_string_hash_;

    public EPPPoll () { initHash(); }

    public EPPPoll (String xml) throws epp_XMLException, epp_Exception
    {
        super(xml);
        String method_name = "EPPPoll(String)";
        debug(DEBUG_LEVEL_TWO,method_name,"xml is ["+xml+"]");
        initHash();
        fromXML(xml);
    }

    private void initHash()
    {
        if ( poll_op_to_string_hash_ == null )
        {
            poll_op_to_string_hash_ = new Hashtable();
            poll_op_to_string_hash_.put(epp_PollOpType.REQ, "req");
            poll_op_to_string_hash_.put(epp_PollOpType.ACK, "ack");
        }
    }
    
    /**
     * Accessor method for the domain info request data.  Must be set to for this command.
     * @param value epp_LoginReq
     */
    public void setRequestData(epp_PollReq value) { action_request_ = value; }
    /**
     * Accessor method for the domain info request data.  Must be set to for this command.
     * @return epp_LoginRsp
     */
    public epp_PollRsp getResponseData() { return action_response_; }


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
        Element poll = doc.createElement("poll");

        epp_Command command_data = action_request_.m_cmd;

        if ( command_data.m_creds != null )
        {
            command.appendChild( prepareCreds( doc, command_data.m_creds ) );
        }
        
        if ( action_request_.m_op == null )
        {
            // default the op type to REQ because it's 
            // harmless and doesn't require extra info
            // like the ACK op.
            action_request_.m_op = epp_PollOpType.REQ;
        }
        
        poll.setAttribute("op", (String)poll_op_to_string_hash_.get(action_request_.m_op));
        if ( action_request_.m_msgID != null )
        {
            poll.setAttribute("msgID", action_request_.m_msgID);
        }
        
        command.appendChild( poll );

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
     * Parses the poll response XML.
     * If the response contains the resData tag, EPPPoll will attend to
     * extract the tag name of the first child tag of resData.  This tag
     * name will be used as a class name.  The resulting class must
     * implement the PollResData interface.  By default EPPPoll will
     * look in the com.tucows.oxrs.epp0604.rtk.xml.poll package for
     * the parser classes (eg. domaintranfer).  If it's not found in there
     * then the RTK property "poll.parser.extension.packages" will be used
     * to supply other package names for searching.</p>
     * So, let's say the first child of resData is "domain:trnData".
     * Non-alphanumeric characters are stripped out of the tag name and
     * the parser class "domaintrnData" is searched for first in
     * "com.tucows.oxrs.epp0604.rtk.xml.poll".  It turns out that this class
     * is found in this location, so searching stops there.  EPPPoll
     * then asks an instance of domaintransfer to "fromXML()" the
     * XML node.  The parser classes will attempt to populate an
     * epp_PollResData (including the union inside there).  This
     * data is then retrieved and put into m_res_data of epp_PollRsp.</P>
     * If "domaintrnData" was not found in the default package, then
     * the RTK property "poll.parser.extension.packages" is loaded.  This
     * must be a comma separated list of packages names in which the class
     * search will be continued.
     * @see com.tucows.oxrs.epp0604.rtk.RTKBase#getRTKProperties()
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

            action_response_ = new epp_PollRsp();
            
            action_response_.m_rsp = parseGenericResult(response_node);

            if ( action_response_.m_rsp.m_results[0].m_code >= epp_Session.EPP_UNKNOWN_COMMAND )
            {
                throw new epp_Exception(action_response_.m_rsp.m_results);
            }

            Element response_data_element = getElement(response_node.getChildNodes(), "resData");

            if ( response_data_element != null )
            {
                // get all child nodes to resData
                NodeList poll_resData_nodelist = response_data_element.getElementsByTagName("*");

                debug(DEBUG_LEVEL_TWO,method_name,"poll_resData_nodelist count ["+poll_resData_nodelist.getLength()+"]");

                for (int count = 0; count < poll_resData_nodelist.getLength(); count++)
                {
                    Node a_node = poll_resData_nodelist.item(count);

                    String a_node_name = a_node.getNodeName();

                    debug(DEBUG_LEVEL_TWO,method_name,"poll resData node name ["+a_node_name+"]");

                    // search through poll extension package names


                    // first, let's strip out the non-word characters (i.e.
                    // only [a-zA-Z0-9_] will be left behind.)
                    RE regexp = new RE("\\W");
                    String class_name = regexp.subst(a_node_name, "");

                    String poll_extension_package_name = "com.tucows.oxrs.epp0604.rtk.xml.poll";

                    StringTokenizer poll_package_names = new StringTokenizer(
                                                    (String) RTKBase.getRTKProperties().getProperty("poll.parser.extension.packages", ""),
                                                    ",");

                    // Try to find the class for the resData in the default package

                    PollResData poll_res_data = null;

                    while ( poll_res_data == null )
                    {
                        try
                        {
                            debug(DEBUG_LEVEL_TWO,method_name,"Trying to instantiate poll resData parser class ["+poll_extension_package_name+class_name+"]");
                            poll_res_data = (PollResData) Class.forName( poll_extension_package_name + "." + class_name ).newInstance();
                        }
                        catch (ClassNotFoundException xcp)
                        {
                            // try next package name

                            // checking for null in case there are none specified
                            // that could be because the properties fil
                            if ( poll_package_names == null ||
                                 ! poll_package_names.hasMoreTokens() )
                            {
                                // We've run out of luck... no more packages to look in
                                // for a parser for this poll resData
                                break;
                            }
                            poll_extension_package_name = poll_package_names.nextToken();

                        }
                        catch (Exception xcp)
                        {
                            throw new epp_XMLException("Error loading Poll resData parser class ["+xcp.getClass().getName()+"] ["+xcp.getMessage()+"]");
                        }

                        debug(DEBUG_LEVEL_TWO,method_name,"Found class.");
                    }

                    if ( poll_res_data == null )
                    {
                        throw new epp_XMLException("Unrecognized Poll resData ["+a_node_name+"]");
                    }               

                    poll_res_data.fromXML(a_node);

                    action_response_.m_res_data = poll_res_data.getPollResData();

                    // for now we only accept one Poll resData node.
                    break;
                }

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
        catch (RESyntaxException xcp)
        {
            debug(DEBUG_LEVEL_ONE,method_name,xcp);
            throw new epp_XMLException("Error in parsing poll resData parser class name["+xcp.getClass().getName()+"] ["+xcp.getMessage()+"]");
        }

    }
    
}
