/*
**
** EPP RTK Java
** Copyright (C) 2002, Tucows, Inc.
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

package com.tucows.oxrs.epp0503.rtk.xml.poll;

import com.tucows.oxrs.epp0503.rtk.xml.EPPXMLBase;
import com.tucows.oxrs.epp0503.rtk.xml.EPPContactBase;
import org.openrtk.idl.epp0503.*;
import org.openrtk.idl.epp0503.contact.epp_ContactTrnData;
import org.w3c.dom.*;

/**
 * An implementation of PollResData for contact transfer poll
 * response data.
 * Populates the epp_PollResData with the epp_PollResDataUnion
 * and m_contact_transfer member set.</p>
 * $Header: /cvsroot/epp-rtk/epp-rtk/java/src/com/tucows/oxrs/epp0503/rtk/xml/poll/contacttrnData.java,v 1.1 2003/03/21 16:18:24 tubadanm Exp $<br>
 * $Revision: 1.1 $<br>
 * $Date: 2003/03/21 16:18:24 $<br>
 */
public class contacttrnData extends EPPXMLBase implements PollResData
{

    private epp_ContactTrnData poll_res_data_;
    
    /**
     * Populates the poll res data with epp_PollContactTransfer
     * data.
     */
    public void fromXML(Node res_data_sub_node) throws epp_XMLException
    {
        String method_name = "fromXML(Node)";
        
        NodeList contact_transfer_data_list = res_data_sub_node.getChildNodes();
        
        poll_res_data_ = new epp_ContactTrnData();

        debug(DEBUG_LEVEL_TWO,method_name,"contact:trnData's node count ["+contact_transfer_data_list.getLength()+"]");

        if ( contact_transfer_data_list.getLength() == 0 )
        {
            throw new epp_XMLException("missing contact transfer data");
        }

        poll_res_data_ = EPPContactBase.getTrnData(contact_transfer_data_list);

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
