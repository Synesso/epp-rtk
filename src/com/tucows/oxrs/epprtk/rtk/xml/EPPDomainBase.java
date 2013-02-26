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
 * $Header: /cvsroot/epp-rtk/epp-rtk/java/src/com/tucows/oxrs/epprtk/rtk/xml/EPPDomainBase.java,v 1.1 2004/12/07 15:53:27 ewang2004 Exp $
 * $Revision: 1.1 $
 * $Date: 2004/12/07 15:53:27 $
 */

package com.tucows.oxrs.epprtk.rtk.xml;

import java.io.*;
import java.util.*;

import com.tucows.oxrs.epprtk.rtk.*;
import org.openrtk.idl.epprtk.domain.*;
import org.openrtk.idl.epprtk.epp_Exception;
import org.openrtk.idl.epprtk.epp_XMLException;
import org.openrtk.idl.epprtk.epp_TransferStatusType;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;

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
     * @see The EPP IDL epp_domain.idl for the definition of the epp_DomainStatusType enum
     */
    protected static Hashtable contact_type_hash_;
    
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
            domain_status_hash_.put("clientDeleteProhibited", epp_DomainStatusType.CLIENT_DELETE_PROHIBITED);
            domain_status_hash_.put("clientHold", epp_DomainStatusType.CLIENT_HOLD);
            domain_status_hash_.put("clientRenewProhibited", epp_DomainStatusType.CLIENT_RENEW_PROHIBITED);
            domain_status_hash_.put("clientTransferProhibited", epp_DomainStatusType.CLIENT_TRANSFER_PROHIBITED);
            domain_status_hash_.put("clientUpdateProhibited", epp_DomainStatusType.CLIENT_UPDATE_PROHIBITED);
            domain_status_hash_.put("inactive", epp_DomainStatusType.INACTIVE);
            domain_status_hash_.put("ok", epp_DomainStatusType.OK);
            domain_status_hash_.put("pendingCreate", epp_DomainStatusType.PENDING_CREATE);
            domain_status_hash_.put("pendingDelete", epp_DomainStatusType.PENDING_DELETE);
            domain_status_hash_.put("pendingTransfer", epp_DomainStatusType.PENDING_TRANSFER);
            domain_status_hash_.put("pendingRenew", epp_DomainStatusType.PENDING_RENEW);
            domain_status_hash_.put("pendingUpdate", epp_DomainStatusType.PENDING_UPDATE);
            domain_status_hash_.put("serverDeleteProhibited", epp_DomainStatusType.SERVER_DELETE_PROHIBITED);
            domain_status_hash_.put("serverHold", epp_DomainStatusType.SERVER_HOLD);
            domain_status_hash_.put("serverRenewProhibited", epp_DomainStatusType.SERVER_RENEW_PROHIBITED);
            domain_status_hash_.put("serverTransferProhibited", epp_DomainStatusType.SERVER_TRANSFER_PROHIBITED);
            domain_status_hash_.put("serverUpdateProhibited", epp_DomainStatusType.SERVER_UPDATE_PROHIBITED);
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
        command.setAttribute("xmlns:domain", "urn:ietf:params:xml:ns:domain-1.0");
        command.setAttribute("xsi:schemaLocation", "urn:ietf:params:xml:ns:domain-1.0 domain-1.0.xsd");
    }

    protected Element createNameServerElement(Document doc, List nameServers)
    {
        Element nameServerElement = doc.createElement("domain:ns");
        for (Iterator it = nameServers.iterator(); it.hasNext();)
        {
            String server_name = (String)it.next();
            addXMLElement(doc, nameServerElement, "domain:hostObj", server_name);
        }

        return nameServerElement;
    }

    /**
     * Convenience method to transform an epp_DomainStatusType to
     * a real domain status string.
     */
    public static String domainStatusToString(epp_DomainStatusType status_type)
    {
        return status_type.toString();
    }

    /**
     * Convenience method to transform an epp_DomainContactType to
     * a real domain type string (i.e. admin, billing, tech).
     */
    public static String domainContactTypeToString(epp_DomainContactType contact_type)
    {
        return contact_type.toString();
    }

    /**
     * Convenience method to convert a string status to an instance of
     * epp_DomainStatusType.
     */
    public static epp_DomainStatusType domainStatusFromString(String s)
    {
        initDomainStatusHash();
        return (epp_DomainStatusType) domain_status_hash_.get(s);
    }

    public static epp_DomainTrnData getTrnData(NodeList transfer_data_list)
                    throws epp_XMLException
    {
        epp_DomainTrnData trn_data = new epp_DomainTrnData();
        
        for (int count = 0; count < transfer_data_list.getLength(); count++)
        {
            Node a_node = transfer_data_list.item(count);

            if ( a_node.getNodeName().equals("domain:name") ) { trn_data.m_name = a_node.getFirstChild().getNodeValue(); }

            if ( a_node.getNodeName().equals("domain:reID") ) { trn_data.m_request_client_id = a_node.getFirstChild().getNodeValue(); }
            if ( a_node.getNodeName().equals("domain:acID") ) { trn_data.m_action_client_id = a_node.getFirstChild().getNodeValue(); }

            if ( a_node.getNodeName().equals("domain:trStatus") )
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

            if ( a_node.getNodeName().equals("domain:reDate") ) { trn_data.m_request_date = a_node.getFirstChild().getNodeValue(); }
            if ( a_node.getNodeName().equals("domain:acDate") ) { trn_data.m_action_date = a_node.getFirstChild().getNodeValue(); }
            if ( a_node.getNodeName().equals("domain:exDate") ) { trn_data.m_expiration_date = a_node.getFirstChild().getNodeValue(); }

        }

        return trn_data;
    }
}
