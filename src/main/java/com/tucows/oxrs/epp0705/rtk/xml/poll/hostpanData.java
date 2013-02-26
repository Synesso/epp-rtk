/*
**
** EPP RTK Java
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

package com.tucows.oxrs.epp0705.rtk.xml.poll;

import com.tucows.oxrs.epp0705.rtk.xml.EPPXMLBase;
import com.tucows.oxrs.epp0705.rtk.xml.EPPHostBase;
import org.openrtk.idl.epp0705.*;
import org.openrtk.idl.epp0705.host.epp_HostPanData;
import org.w3c.dom.*;


/**
 * An implementation of PollResData for contact transfer poll
 * response data.
 * Populates the epp_PollResData with the epp_PollResDataUnion
 * and m_host_transfer member set.</p>
 * $Header: /cvsroot/epp-rtk/epp-rtk/java/src/com/tucows/oxrs/epp0705/rtk/xml/poll/hostpanData.java,v 1.1 2003/03/20 22:42:01 tubadanm Exp $<br>
 * $Revision: 1.1 $<br>
 * $Date: 2003/03/20 22:42:01 $<br>
 */
public class hostpanData extends EPPXMLBase implements PollResData
{

    private epp_HostPanData poll_res_data_;
    
    
    /**
     * Populates the poll res data with pending action notification data.
     */
    public void fromXML(Node res_data_node) throws epp_XMLException
    {
        String method_name = "fromXML(Node)";
        
        NodeList host_pan_data_list = res_data_node.getChildNodes();
        
        poll_res_data_ = new epp_HostPanData();
        
        debug(DEBUG_LEVEL_TWO,method_name,"host:panData's node count ["+host_pan_data_list.getLength()+"]");

        if ( host_pan_data_list.getLength() == 0 )
        {
            throw new epp_XMLException("missing host pan data");
        }

        String host_name = EPPXMLBase.getPanData( host_pan_data_list, 
                                                  res_data_node.getNamespaceURI(),
                                                  (epp_PanData)poll_res_data_ );

        poll_res_data_.setName(host_name);
    }
    
    /**
     * Returns the epp_PollResData private member.
     * Should only be called after a successful fromXML()
     * otherwise, the poll res data will be null.
     */
    public epp_PollResData getPollResData()
    {
        return poll_res_data_;
    }

}
