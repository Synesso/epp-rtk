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
 * $Header: /cvsroot/epp-rtk/epp-rtk/java/src/com/tucows/oxrs/epp02/rtk/xml/EPPDomainBase.java,v 1.1 2003/03/21 17:13:41 tubadanm Exp $
 * $Revision: 1.1 $
 * $Date: 2003/03/21 17:13:41 $
 */

package com.tucows.oxrs.epp02.rtk.xml;

import java.io.*;
import java.util.*;

import com.tucows.oxrs.epp02.rtk.*;
import org.openrtk.idl.epp02.domain.*;

import org.w3c.dom.Element;

/**
 * Base abstract class for the Domain classes.  Defines common methods and data methods
 * used by all or most Domain classes.
 */
public abstract class EPPDomainBase extends EPPXMLBase
{

    /**
     * Hashtable to allow for conversion from String domain status to
     * epp_DomainStatusType.
     * @see The EPP IDL epp_domain.idl for the definition of the epp_DomainStatusType enum
     */
    protected static Hashtable domain_status_hash_;
    /**
     * Array to allow for conversion from epp_DomainStatusType to
     * a String representation of the domain status.
     * The epp_DomainStatusType static classes have a value() member
     * function which returns their integer position in the enum.
     * @see The EPP IDL epp_domain.idl for the definition of the epp_DomainStatusType enum
     */
    protected static String[] status_to_string_array_ = {
                                    "hold",
                                    "inactive",
                                    "lock",
                                    "new",
                                    "ok",
                                    "pendingDelete",
                                    "pendingTransfer",
                                    "pendingVerification",
                                    "clientHold",
                                    "clientLock",
                                    "clientTransferProhibited",
                                    "active"
                                    };
    /**
     * @see The EPP IDL epp_domain.idl for the definition of the epp_DomainStatusType enum
     */
    protected static Hashtable contact_type_hash_;
    /**
     * @see The EPP IDL epp_domain.idl for the definition of the epp_DomainStatusType enum
     */
    protected static String[] contact_type_to_string_array_ = {
                                    "admin",
                                    "billing",
                                    "tech"
                                    };
    /**
     * Array to allow for conversion from epp_DomainPeriodUnitType to
     * a String representation of the unit type.
     * The epp_DomainPeriodUnitType static classes have a value() member
     * function which returns their integer position in the enum.
     * @see The EPP IDL epp_domain.idl for the definition of the epp_DomainPeriodUnitType enum
     */
    public static String[] period_unit_to_string_array_ = {
                                    "y",
                                    "m"
                                    };
    
    /**
     * Default constructor.  
     * Initializes the static Hashtables.
     */
    public EPPDomainBase() { initHashes(); }

    /**
     * Constructor with XML String
     * Also initializes the static Hashtables.
     */
    public EPPDomainBase(String xml) { super(xml); initHashes(); }
    
    public static void initHashes()
    {
        initDomainStatusHash();
        initContactTypeHash();
    }
    
    protected static void initDomainStatusHash()
    {
        if ( domain_status_hash_ == null )
        {
            domain_status_hash_ = new Hashtable();
            domain_status_hash_.put("hold", epp_DomainStatusType.HOLD);
            domain_status_hash_.put("inactive", epp_DomainStatusType.INACTIVE);
            domain_status_hash_.put("lock", epp_DomainStatusType.LOCK);
            domain_status_hash_.put("new", epp_DomainStatusType.NEW);
            domain_status_hash_.put("ok", epp_DomainStatusType.OK);
            domain_status_hash_.put("pendingDelete", epp_DomainStatusType.PENDING_DELETE);
            domain_status_hash_.put("pendingTransfer", epp_DomainStatusType.PENDING_TRANSFER);
            domain_status_hash_.put("pendingVerification", epp_DomainStatusType.PENDING_VERIFICATION);
            domain_status_hash_.put("clientHold", epp_DomainStatusType.CLIENT_HOLD);
            domain_status_hash_.put("clientLock", epp_DomainStatusType.CLIENT_LOCK);
            domain_status_hash_.put("clientTransferProhibited", epp_DomainStatusType.CLIENT_TRANSFER_PROHIBITED);
            domain_status_hash_.put("active", epp_DomainStatusType.ACTIVE);
        }
    }

    protected static void initContactTypeHash()
    {
        if ( contact_type_hash_ == null )
        {
            contact_type_hash_ = new Hashtable();
            contact_type_hash_.put("admin", epp_DomainContactType.ADMIN);
            contact_type_hash_.put("billing", epp_DomainContactType.BILLING);
            contact_type_hash_.put("tech", epp_DomainContactType.TECH);
        }
    }

    /**
     * Sets the common XML attributes required for the domain object (eg. xmlns:domain, xsi:schemaLocation)
     * @param The domain action Element (eg Element with tag name "domain:create")
     */
    protected void setCommonAttributes(Element command)
    {
        command.setAttribute("xmlns:domain", "urn:iana:xml:ns:domain");
        command.setAttribute("xsi:schemaLocation", "urn:iana:xml:ns:domain domain.xsd");
    }

    /**
     * Convenience method to transform an epp_DomainStatusType to
     * a real domain status string.
     */
    public static String domainStatusToString(epp_DomainStatusType status_type)
    {
        return status_to_string_array_[status_type.value()];
    }

    /**
     * Convenience method to transform an epp_DomainContactType to
     * a real domain type string (i.e. admin, billing, tech).
     */
    public static String domainContactTypeToString(epp_DomainContactType contact_type)
    {
        return contact_type_to_string_array_[contact_type.value()];
    }
	

    public static epp_DomainStatusType domainStatusFromString(String s)
    {
        initDomainStatusHash();
        return (epp_DomainStatusType) domain_status_hash_.get(s);
    }
}
