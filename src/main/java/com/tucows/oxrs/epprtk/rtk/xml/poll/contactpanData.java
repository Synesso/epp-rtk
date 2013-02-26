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

package com.tucows.oxrs.epprtk.rtk.xml.poll;

import com.tucows.oxrs.epprtk.rtk.xml.EPPXMLBase;
import org.openrtk.idl.epprtk.*;
import org.openrtk.idl.epprtk.contact.epp_ContactPanData;
import org.w3c.dom.*;


/**
 * An implementation of PollResData for contact transfer poll
 * response data.
 * Populates the epp_PollResData with the epp_PollResDataUnion
 * and m_contact_transfer member set.</p>
 * $Header: /cvsroot/epp-rtk/epp-rtk/java/src/com/tucows/oxrs/epprtk/rtk/xml/poll/contactpanData.java,v 1.1 2004/12/07 22:44:07 ewang2004 Exp $<br>
 * $Revision: 1.1 $<br>
 * $Date: 2004/12/07 22:44:07 $<br>
 */
public class contactpanData extends EPPXMLBase implements PollResData
{

    private epp_ContactPanData poll_res_data_;
    
    
    /**
     * Populates the poll res data with pending action notification data.
     */
    public void fromXML(Node res_data_node) throws epp_XMLException
    {
        String method_name = "fromXML(Node)";
        
        NodeList contact_pan_data_list = res_data_node.getChildNodes();
        
        poll_res_data_ = new epp_ContactPanData();
        
        debug(DEBUG_LEVEL_TWO,method_name,"contact:panData's node count ["+contact_pan_data_list.getLength()+"]");

        if ( contact_pan_data_list.getLength() == 0 )
        {
            throw new epp_XMLException("missing contact pan data");
        }

        String contact_id = EPPXMLBase.getPanData( contact_pan_data_list, 
                                                   res_data_node.getNamespaceURI(),
                                                   (epp_PanData)poll_res_data_ );

        poll_res_data_.setId(contact_id);
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
