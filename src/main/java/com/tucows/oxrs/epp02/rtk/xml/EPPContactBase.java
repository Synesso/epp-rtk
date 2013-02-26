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
 * $Header: /cvsroot/epp-rtk/epp-rtk/java/src/com/tucows/oxrs/epp02/rtk/xml/EPPContactBase.java,v 1.1 2003/03/21 17:13:40 tubadanm Exp $
 * $Revision: 1.1 $
 * $Date: 2003/03/21 17:13:40 $
 */

package com.tucows.oxrs.epp02.rtk.xml;

import java.io.*;
import java.util.*;

import com.tucows.oxrs.epp02.rtk.*;
import org.openrtk.idl.epp02.epp_XMLException;
import org.openrtk.idl.epp02.contact.*;

import org.w3c.dom.*;

/**
 * Base abstract class for the Contact classes.  Defines common methods and data members
 * used by all or most Contact classes.
 */
public abstract class EPPContactBase extends EPPXMLBase
{

    /**
     * Hashtable to allow for conversion from String contact status to
     * epp_ContactStatusType.
     * @see The EPP IDL epp_contact.idl for the definition of the epp_ContactStatusType enum
     */
    protected static Hashtable contact_status_hash_;
    /**
     * Array to allow for conversion from epp_ContactStatusType to
     * a String representation of the contact status.
     * The epp_ContactStatusType static classes have a value() member
     * function which returns their integer position in the enum.
     * @see The EPP IDL epp_contact.idl for the definition of the epp_ContactStatusType enum
     */
    // The ordering of the strings in this array must match the
    // enum in epp_contact.idl, otherwise the wrong statuses
    // will be used.
    protected static String[] status_to_string_array_ = {
                                    "lock",
                                    "ok",
                                    "pendingDelete",
                                    "pendingTransfer",
                                    "clientLock",
                                    "clientTransferProhibited"
                                    };
    /**
     * Default Constructor.
     */
    protected EPPContactBase() { initHashes(); }

    /**
     * Constructor with the XML String.
     */
    protected EPPContactBase(String xml) { super(xml); initHashes(); }
    
    public static void initHashes()
    {
        initContactStatusHash();
    }
    
    public static void initContactStatusHash()
    {
        if ( contact_status_hash_ == null )
        {
            contact_status_hash_ = new Hashtable();
            contact_status_hash_.put("lock", epp_ContactStatusType.LOCK);
            contact_status_hash_.put("ok", epp_ContactStatusType.OK);
            contact_status_hash_.put("pendingDelete", epp_ContactStatusType.PENDING_DELETE);
            contact_status_hash_.put("pendingTransfer", epp_ContactStatusType.PENDING_DELETE);
            contact_status_hash_.put("clientLock", epp_ContactStatusType.CLIENT_LOCK);
            contact_status_hash_.put("clientTransferProhibited", epp_ContactStatusType.CLIENT_LOCK);
        }
    }

    /**
     * Sets the common XML attributes required for the contact object (eg. xmlns:contact, xsi:schemaLocation)
     * @param The contact action Element (eg Element with tag name "contact:create")
     */
    protected void setCommonAttributes(Element command)
    {
        command.setAttribute("xmlns:contact", "urn:iana:xml:ns:contact");
        command.setAttribute("xsi:schemaLocation", "urn:iana:xml:ns:contact contact.xsd");
    }

    /**
     * Converts a given epp_ContactNameAddress to an XML Element.
     * If any of the data members of the epp_ContactNameAddress are null
     * then they are not included in the Element.  If they are empty Strings
     * then they are included as empty Elements.
     * @param Document the Document instance that is creating the Elements
     * @param String the overall contact name/address tag name (eg. "contact:ascii")
     * @param epp_ContactNameAddress the name/address data to use in the Elements
     * @return Element the resulting contact name/address Element
     */
    protected Element addressToXML(Document doc,
                                   String tag_name,
                                   epp_ContactNameAddress name_address)
                    throws epp_XMLException
    {
        String method_name = "addressToXML(Document, String, epp_ContactNameAddress)";
        debug(DEBUG_LEVEL_THREE,method_name,"Entered");
        
        Element name_address_element = null;
        
        if ( name_address != null )
        {
            name_address_element = doc.createElement(tag_name);
            
            if ( name_address.m_name != null ) { addXMLElement(doc, name_address_element, "contact:name", name_address.m_name); }
            if ( name_address.m_org != null )  { addXMLElement(doc, name_address_element, "contact:org", name_address.m_org); }

            if ( name_address.m_address != null )
            {
                epp_ContactAddress address = name_address.m_address;
                Element address_element = doc.createElement("contact:addr");
                // Because this method is used by contact create and update,
                // the lowest common denominator (update), says that all
                // members are optional.
                if ( address.m_street1 != null ) { addXMLElement(doc, address_element, "contact:street", address.m_street1); }
                if ( address.m_street2 != null ) { addXMLElement(doc, address_element, "contact:street", address.m_street2); }
                if ( address.m_street3 != null ) { addXMLElement(doc, address_element, "contact:street", address.m_street3); }
                if ( address.m_city != null ) { addXMLElement(doc, address_element, "contact:city", address.m_city); }
                if ( address.m_state_province != null ) { addXMLElement(doc, address_element, "contact:sp", address.m_state_province); }
                if ( address.m_postal_code != null ) { addXMLElement(doc, address_element, "contact:pc", address.m_postal_code); }
                if ( address.m_country_code != null ) { addXMLElement(doc, address_element, "contact:cc", address.m_country_code); }

                if ( address_element.getChildNodes().getLength() > 0 )
                {
                    name_address_element.appendChild(address_element);
                }
            }
        }
        
        debug(DEBUG_LEVEL_THREE,method_name,"Leaving");
        return name_address_element;
    }

    /**
     * Convenience method to get a contact status string from an
     * epp_ContactStatusType object.
     */
    public static String contactStatusToString(epp_ContactStatusType status_type)
    {
        return status_to_string_array_[status_type.value()];
    }

    public static epp_ContactStatusType contactStatusFromString(String s)
    {
        initContactStatusHash();
        return (epp_ContactStatusType) contact_status_hash_.get(s);
    }

}
