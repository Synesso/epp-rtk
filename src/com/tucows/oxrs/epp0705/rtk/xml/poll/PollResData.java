/*
**
** EPP RTK Java
** Copyright (C) 2002, Tucows, Inc.
** Copyright (C) 2003, Tucows, Inc.
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

import org.openrtk.idl.epp0705.epp_PollResData;
import org.openrtk.idl.epp0705.epp_XMLException;
import org.w3c.dom.Node;

/**
 * This interface is used by EPPPoll to instantiate a Poll parser
 * class without having to rely on a particular classname.
 * Implementors of this class have to be able to populate
 * an epp_PollResData structure (via fromXML()) and should 
 * be able to pass this data back (via getPollResData()).
 */
public interface PollResData
{

    /**
     * Converts a poll response data sub node (and its children nodes
     * to the appropriate epp struct depending on the implementing class.
     * An example of a res data subnode would be "domain:transfer".
     */
    public void fromXML(Node res_data_sub_node) throws epp_XMLException;
    
    /**
     * Returns the epp_PollResData that was populated in the call
     * to "fromXML()".
     */
    public epp_PollResData getPollResData();

}
