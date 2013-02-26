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
 * $Header: /cvsroot/epp-rtk/epp-rtk/java/src/com/tucows/oxrs/epprtk/rtk/xml/EPPContactBase.java,v 1.1 2004/12/07 15:53:27 ewang2004 Exp $
 * $Revision: 1.1 $
 * $Date: 2004/12/07 15:53:27 $
 */

package com.tucows.oxrs.epprtk.rtk.xml;

import java.io.*;
import java.util.*;

import com.tucows.oxrs.epprtk.rtk.*;
import org.openrtk.idl.epprtk.epp_XMLException;
import org.openrtk.idl.epprtk.contact.*;
import org.openrtk.idl.epprtk.epp_Exception;
import org.openrtk.idl.epprtk.epp_XMLException;
import org.openrtk.idl.epprtk.epp_TransferStatusType;

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
     * Hashtable to allow for conversion from String contact address type
     * (aka postal info type) to epp_ContactPostalInfoType.
     * @see The EPP IDL epp_contact.idl for the definition of the epp_ContactPostalInfoType enum
     */
    protected static Hashtable contact_address_type_hash_;

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
        initContactAddressTypeHash();
    }

    public static void initContactStatusHash()
    {
        if ( contact_status_hash_ == null )
        {
            contact_status_hash_ = new Hashtable();
            contact_status_hash_.put("clientDeleteProhibited", epp_ContactStatusType.CLIENT_DELETE_PROHIBITED);
            contact_status_hash_.put("serverDeleteProhibited", epp_ContactStatusType.SERVER_DELETE_PROHIBITED);
            contact_status_hash_.put("clientTransferProhibited", epp_ContactStatusType.CLIENT_TRANSFER_PROHIBITED);
            contact_status_hash_.put("serverTransferProhibited", epp_ContactStatusType.SERVER_TRANSFER_PROHIBITED);
            contact_status_hash_.put("clientUpdateProhibited", epp_ContactStatusType.CLIENT_UPDATE_PROHIBITED);
            contact_status_hash_.put("serverUpdateProhibited", epp_ContactStatusType.SERVER_UPDATE_PROHIBITED);
            contact_status_hash_.put("linked", epp_ContactStatusType.LINKED);
            contact_status_hash_.put("ok", epp_ContactStatusType.OK);
            contact_status_hash_.put("pendingCreate", epp_ContactStatusType.PENDING_CREATE);
            contact_status_hash_.put("pendingDelete", epp_ContactStatusType.PENDING_DELETE);
            contact_status_hash_.put("pendingTransfer", epp_ContactStatusType.PENDING_TRANSFER);
            contact_status_hash_.put("pendingUpdate", epp_ContactStatusType.PENDING_UPDATE);
        }
    }

    public static void initContactAddressTypeHash()
    {
        if ( contact_address_type_hash_ == null )
        {
            contact_address_type_hash_ = new Hashtable();
            contact_address_type_hash_.put("loc", epp_ContactPostalInfoType.LOC);
            contact_address_type_hash_.put("int", epp_ContactPostalInfoType.INT);
        }
    }

    /**
     * Sets the common XML attributes required for the contact object (eg. xmlns:contact, xsi:schemaLocation)
     * @param The contact action Element (eg Element with tag name "contact:create")
     */
    protected void setCommonAttributes(Element command)
    {
        command.setAttribute("xmlns:contact", "urn:ietf:params:xml:ns:contact-1.0");
        command.setAttribute("xsi:schemaLocation", "urn:ietf:params:xml:ns:contact-1.0 contact-1.0.xsd");
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
            
            if ( name_address.m_type == null ) {
                throw new epp_XMLException("missing the address type (postal info)");
            }
            
            name_address_element.setAttribute("type", name_address.m_type.toString());
            
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
        return status_type.toString();
    }

    /**
     * Convenience method to convert a string status to an instance of
     * epp_ContactStatusType.
     */
    public static epp_ContactStatusType contactStatusFromString(String s)
    {
        initContactStatusHash();
        return (epp_ContactStatusType) contact_status_hash_.get(s);
    }

    public static epp_ContactTrnData getTrnData(NodeList transfer_data_list)
                    throws epp_XMLException
    {
        epp_ContactTrnData trn_data = new epp_ContactTrnData();
        
        for (int count = 0; count < transfer_data_list.getLength(); count++)
        {
            Node a_node = transfer_data_list.item(count);

            if ( a_node.getNodeName().equals("contact:id") ) { trn_data.m_id = a_node.getFirstChild().getNodeValue(); }

            if ( a_node.getNodeName().equals("contact:reID") ) { trn_data.m_request_client_id = a_node.getFirstChild().getNodeValue(); }
            if ( a_node.getNodeName().equals("contact:acID") ) { trn_data.m_action_client_id = a_node.getFirstChild().getNodeValue(); }

            if ( a_node.getNodeName().equals("contact:trStatus") )
            {
                String status_value = a_node.getFirstChild().getNodeValue();
                if ( ! transfer_status_to_type_hash_.containsKey(status_value) )
                {
                    throw new epp_XMLException("unknown returned transfer status ["+status_value+"]");
                }
                else
                {
                    trn_data.m_transfer_status = (epp_TransferStatusType)transfer_status_to_type_hash_.get(status_value);
                }

            }

            if ( a_node.getNodeName().equals("contact:reDate") ) { trn_data.m_request_date = a_node.getFirstChild().getNodeValue(); }
            if ( a_node.getNodeName().equals("contact:acDate") ) { trn_data.m_action_date = a_node.getFirstChild().getNodeValue(); }

        }

        return trn_data;
    }

}
