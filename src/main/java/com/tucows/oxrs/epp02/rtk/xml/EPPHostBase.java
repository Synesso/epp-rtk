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
 * $Header: /cvsroot/epp-rtk/epp-rtk/java/src/com/tucows/oxrs/epp02/rtk/xml/EPPHostBase.java,v 1.1 2003/03/21 17:13:42 tubadanm Exp $
 * $Revision: 1.1 $
 * $Date: 2003/03/21 17:13:42 $
 */

package com.tucows.oxrs.epp02.rtk.xml;

import java.io.*;
import java.util.*;

import com.tucows.oxrs.epp02.rtk.*;
import org.openrtk.idl.epp02.host.*;

import org.w3c.dom.Element;

/**
 * Base abstract class for the Host classes.  Defines common data members
 * used by all or most Host classes.
 */
public abstract class EPPHostBase extends EPPXMLBase
{

    /**
     * Hashtable to allow for conversion from String host status to
     * epp_HostStatusType.
     * @see The EPP IDL epp_host.idl for the definition of the epp_HostStatusType enum
     */
    protected static Hashtable host_status_hash_;
    /**
     * Array to allow for conversion from epp_HostStatusType to
     * a String representation of the host status.
     * The epp_HostStatusType static classes have a value() member
     * function which returns their integer position in the enum.
     * @see The EPP IDL epp_host.idl for the definition of the epp_HostStatusType enum
     */
    protected static String[] status_to_string_array_ = {
                                    "lock",
                                    "ok",
                                    "pendingDelete",
                                    "clientLock"
                                    };
    /**
     * Hashtable to allow for conversion from String address type to
     * epp_HostAddressType.
     * @see The EPP IDL epp_host.idl for the definition of the epp_HostAddressType enum
     */
    protected static Hashtable host_ip_to_type_hash_;
    /**
     * Array to allow for conversion from epp_HostAddressType to
     * a String representation of the host address.
     * The epp_HostAddressType static classes have a value() member
     * function which returns their integer position in the enum.
     * @see The EPP IDL epp_host.idl for the definition of the epp_HostAddressType enum
     */
    protected static String[] host_ip_to_string_array_ = {
                                    "v4",
                                    "v6",
                                    };

    /**
     * Default constructor.  
     * Initializes the static Hashtables.
     */
    protected EPPHostBase() { initHashes(); }

    /**
     * Constructor with XML String
     * Also initializes the static Hashtables.
     */
    protected EPPHostBase(String xml) { super(xml); initHashes(); }
    
    public static void initHashes()
    {
        initHostIpToTypeHash();
        initHostStatusHash();
    }
    
    protected static void initHostIpToTypeHash()
    {
        if ( host_ip_to_type_hash_ == null )
        {
            host_ip_to_type_hash_ = new Hashtable();
            host_ip_to_type_hash_.put("v4", epp_HostAddressType.IPV4);
            host_ip_to_type_hash_.put("v6", epp_HostAddressType.IPV6);
        }
    }

    protected static void initHostStatusHash()
    {
        if ( host_status_hash_ == null )
        {
            host_status_hash_ = new Hashtable();
            host_status_hash_.put("lock", epp_HostStatusType.LOCK);
            host_status_hash_.put("ok", epp_HostStatusType.OK);
            host_status_hash_.put("pendingDelete", epp_HostStatusType.PENDING_DELETE);
            host_status_hash_.put("clientLock", epp_HostStatusType.CLIENT_LOCK);
        }
    }

    /**
     * Sets the common XML attributes required for the host object (eg. xmlns:host, xsi:schemaLocation)
     * @param The host action Element (eg Element with tag name "host:create")
     */
    protected void setCommonAttributes(Element command)
    {
        command.setAttribute("xmlns:host", "urn:iana:xml:ns:host");
        command.setAttribute("xsi:schemaLocation", "urn:iana:xml:ns:host host.xsd");
    }

    /**
     * Convenience method to get a host status string from an
     * epp_HostStatusType object.
     */
    public static String hostStatusToString(epp_HostStatusType status_type)
    {
        return status_to_string_array_[status_type.value()];
    }

    /**
     * Convenience method to get a host address type string from an
     * epp_HostAddressType object.
     */
    public static String hostAddressTypeToString(epp_HostAddressType address_type)
    {
        return host_ip_to_string_array_[address_type.value()];
    }

    public static epp_HostStatusType hostStatusFromString(String s)
    {
        initHostStatusHash();
        return (epp_HostStatusType) host_status_hash_.get(s);
    }

}
