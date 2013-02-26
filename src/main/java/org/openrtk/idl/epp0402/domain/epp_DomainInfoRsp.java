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

package org.openrtk.idl.epp0402.domain;


/**
 * Class that contains the elements used to represent the domain info
 * response from the EPP server.</p>
 * Note usually instantiated by the RTK user, but rather by EPPDomainInfo
 * and retrieved using that class' getResponseData() method.</p>
 * $Header: /cvsroot/epp-rtk/epp-rtk/java/src/org/openrtk/idl/epp0402/domain/epp_DomainInfoRsp.java,v 1.2 2003/09/10 21:29:55 tubadanm Exp $<br>
 * $Revision: 1.2 $<br>
 * $Date: 2003/09/10 21:29:55 $<br>
 * @see com.tucows.oxrs.epp0402.rtk.xml.EPPDomainInfo
 * @see org.openrtk.idl.epp0402.domain.epp_DomainInfoReq
 */
public class epp_DomainInfoRsp implements org.omg.CORBA.portable.IDLEntity
{
  /**
   * The common and generic response element.
   * @see #getRsp()
   */
  public org.openrtk.idl.epp0402.epp_Response m_rsp = null;
  /**
   * The name of the domain object in the registry.
   * @see #setName(String)
   * @see #getName()
   */
  public String m_name = null;
  /**
   * The repository object identifier associated with the domain object.
   * @see #setRoid(String)
   * @see #getRoid()
   */
  public String m_roid = null;
  /**
   * The array of status associated with the domain object.
   * @see #setStatus(org.openrtk.idl.epp0402.domain.epp_DomainStatus[])
   * @see #getStatus()
   */
  public org.openrtk.idl.epp0402.domain.epp_DomainStatus m_status[] = null;
  /**
   * The registrant associated with the domain object.
   * @see #setRegistrant(String)
   * @see #getRegistrant()
   */
  public String m_registrant = null;
  /**
   * The array of contacts associated with the domain object.
   * @see #setContacts(org.openrtk.idl.epp0402.domain.epp_DomainContact[])
   * @see #getContacts()
   */
  public org.openrtk.idl.epp0402.domain.epp_DomainContact m_contacts[] = null;
  /**
   * The array of name servers associated with the domain object.
   * @see #setNameServers(String[])
   * @see #getNameServers()
   */
  public String m_name_servers[] = null;
  /**
   * The array of host objects created under this domain object.
   * @see #setHosts(String[])
   * @see #getHosts()
   */
  public String m_hosts[] = null;
  /**
   * The identifier of the sponsoring client.
   * @see #setClientId(String)
   * @see #getClientId()
   */
  public String m_client_id = null;
  /**
   * The identifier of the client that created the domain object.
   * @see #setCreatedBy(String)
   * @see #getCreatedBy()
   */
  public String m_created_by = null;
  /**
   * The creation date of the domain object.
   * @see #setCreatedDate(String)
   * @see #getCreatedDate()
   */
  public String m_created_date = null;
  /**
   * The identifier of the client that last updated the domain object.
   * @see #setUpdatedBy(String)
   * @see #getUpdatedBy()
   */
  public String m_updated_by = null;
  /**
   * The most recent modification date of the domain object.
   * @see #setUpdatedDate(String)
   * @see #getUpdatedDate()
   */
  public String m_updated_date = null;
  /**
   * The expiration date of the domain object.
   * @see #setExpirationDate(String)
   * @see #getExpirationDate()
   */
  public String m_expiration_date = null;
  /**
   * The most recent transfer date of the domain object.
   * @see #setTransferDate(String)
   * @see #getTransferDate()
   */
  public String m_transfer_date = null;
  /**
   * The authorization information associated with the domain object.
   * @see #setAuthInfo(org.openrtk.idl.epp0402.epp_AuthInfo)
   * @see #getAuthInfo()
   */
  public org.openrtk.idl.epp0402.epp_AuthInfo m_auth_info = null;

  /**
   * Empty constructor
   */
  public epp_DomainInfoRsp ()
  {
  } // ctor

  /**
   * The constructor with initializing variables.
   * @param _m_rsp The common and generic response element
   * @param _m_name The name of the domain object in the registry
   * @param _m_roid The repository object identifier associated with the domain object
   * @param _m_status The array of status associated with the domain object
   * @param _m_registrant The registrant associated with the domain object
   * @param _m_contacts The array of contacts associated with the domain object
   * @param _m_name_servers The array of name servers associated with the domain object
   * @param _m_hosts The array of host objects created under this domain object
   * @param _m_client_id The identifier of the sponsoring client
   * @param _m_created_by The identifier of the client that created the domain object
   * @param _m_created_date The creation date of the domain object
   * @param _m_updated_by The identifier of the client that last updated the domain object
   * @param _m_updated_date The most recent modification date of the domain object
   * @param _m_expiration_date The expiration date of the domain object
   * @param _m_transfer_date The most recent transfer date of the domain object
   * @param _m_auth_info The authorization information associated with the domain object
   */
  public epp_DomainInfoRsp (org.openrtk.idl.epp0402.epp_Response _m_rsp, String _m_name, String _m_roid, org.openrtk.idl.epp0402.domain.epp_DomainStatus[] _m_status, String _m_registrant, org.openrtk.idl.epp0402.domain.epp_DomainContact[] _m_contacts, String[] _m_name_servers, String[] _m_hosts, String _m_client_id, String _m_created_by, String _m_created_date, String _m_updated_by, String _m_updated_date, String _m_expiration_date, String _m_transfer_date, org.openrtk.idl.epp0402.epp_AuthInfo _m_auth_info)
  {
    m_rsp = _m_rsp;
    m_name = _m_name;
    m_roid = _m_roid;
    m_status = _m_status;
    m_registrant = _m_registrant;
    m_contacts = _m_contacts;
    m_name_servers = _m_name_servers;
    m_hosts = _m_hosts;
    m_client_id = _m_client_id;
    m_created_by = _m_created_by;
    m_created_date = _m_created_date;
    m_updated_by = _m_updated_by;
    m_updated_date = _m_updated_date;
    m_expiration_date = _m_expiration_date;
    m_transfer_date = _m_transfer_date;
    m_auth_info = _m_auth_info;
  } // ctor

  /**
   * Accessor method for the common and generic response element.
   * @param value The new value for the response element
   * @see #m_rsp
   */
  public void setRsp(org.openrtk.idl.epp0402.epp_Response value) { m_rsp = value; }
  /**
   * Accessor method for the common and generic response element.
   * @return The value for the response element
   * @see #m_rsp
   */
  public org.openrtk.idl.epp0402.epp_Response getRsp() { return m_rsp; }

  /**
   * Accessor method for the name of the domain object in the registry
   * @param value The domain name
   * @see #m_name
   */
  public void setName(String value) { m_name = value; }
  /**
   * Accessor method for the name of the domain object in the registry
   * @return The domain name
   * @see #m_name
   */
  public String getName() { return m_name; }

  /**
   * Accessor method for the repository object identifier associated with the domain object
   * @param value The domain repository object identifier
   * @see #m_roid
   */
  public void setRoid(String value) { m_roid = value; }
  /**
   * Accessor method for the repository object identifier associated with the domain object
   * @return The domain repository object identifier
   * @see #m_roid
   */
  public String getRoid() { return m_roid; }

  /**
   * Accessor method for the array of status associated with the domain object
   * @param value The array of domain status
   * @see #m_status
   */
  public void setStatus(org.openrtk.idl.epp0402.domain.epp_DomainStatus[] value) { m_status = value; }
  /**
   * Accessor method for the array of status associated with the domain object
   * @return The array of domain status
   * @see #m_status
   */
  public org.openrtk.idl.epp0402.domain.epp_DomainStatus[] getStatus() { return m_status; }

  /**
   * Accessor method for the registrant associated with the domain object
   * @param value The domain registrant
   * @see #m_registrant
   */
  public void setRegistrant(String value) { m_registrant = value; }
  /**
   * Accessor method for the registrant associated with the domain object
   * @return The domain registrant
   * @see #m_registrant
   */
  public String getRegistrant() { return m_registrant; }

  /**
   * Accessor method for the array of contacts associated with the domain object
   * @param value The array of domain contacts
   * @see #m_contacts
   */
  public void setContacts(org.openrtk.idl.epp0402.domain.epp_DomainContact[] value) { m_contacts = value; }
  /**
   * Accessor method for the array of contacts associated with the domain object
   * @return The array of domain contacts
   * @see #m_contacts
   */
  public org.openrtk.idl.epp0402.domain.epp_DomainContact[] getContacts() { return m_contacts; }

  /**
   * Accessor method for the array of name servers associated with the domain object
   * @param value The array of domain name servers
   * @see #m_name_servers
   */
  public void setNameServers(String[] value) { m_name_servers = value; }
  /**
   * Accessor method for the array of name servers associated with the domain object
   * @return The array of domain name servers
   * @see #m_name_servers
   */
  public String[] getNameServers() { return m_name_servers; }

  /**
   * Accessor method for the array of host objects created under this domain object
   * @param value The array of host objects
   * @see #m_hosts
   */
  public void setHosts(String[] value) { m_hosts = value; }
  /**
   * Accessor method for the array of host objects created under this domain object
   * @return The array of host objects
   * @see #m_hosts
   */
  public String[] getHosts() { return m_hosts; }

  /**
   * Accessor method for the identifier of the sponsoring client
   * @param value The domain sponsoring client id
   * @see #m_client_id
   */
  public void setClientId(String value) { m_client_id = value; }
  /**
   * Accessor method for the identifier of the sponsoring client
   * @return The domain sponsoring client id
   * @see #m_client_id
   */
  public String getClientId() { return m_client_id; }

  /**
   * Accessor method for the identifier of the client that created the domain object
   * @param value The identifier of the client that created the domain object
   * @see #m_created_by
   */
  public void setCreatedBy(String value) { m_created_by = value; }
  /**
   * Accessor method for the identifier of the client that created the domain object
   * @return The identifier of the client that created the domain object
   * @see #m_created_by
   */
  public String getCreatedBy() { return m_created_by; }

  /**
   * Accessor method for the creation date of the domain object
   * @param value The domain creation date
   * @see #m_created_date
   */
  public void setCreatedDate(String value) { m_created_date = value; }
  /**
   * Accessor method for the creation date of the domain object
   * @return The domain creation date
   * @see #m_created_date
   */
  public String getCreatedDate() { return m_created_date; }

  /**
   * Accessor method for the identifier of the client that last updated the domain object
   * @param value The identifier of the client that last updated the domain object
   * @see #m_updated_by
   */
  public void setUpdatedBy(String value) { m_updated_by = value; }
  /**
   * Accessor method for the identifier of the client that last updated the domain object
   * @return The identifier of the client that last updated the domain object
   * @see #m_updated_by
   */
  public String getUpdatedBy() { return m_updated_by; }

  /**
   * Accessor method for the most recent modification date of the domain object
   * @param value The most recent modification date
   * @see #m_updated_date
   */
  public void setUpdatedDate(String value) { m_updated_date = value; }
  /**
   * Accessor method for the most recent modification date of the domain object
   * @return The most recent modification date
   * @see #m_updated_date
   */
  public String getUpdatedDate() { return m_updated_date; }

  /**
   * Accessor method for the expiration date of the domain object
   * @param value The domain expiration date
   * @see #m_expiration_date
   */
  public void setExpirationDate(String value) { m_expiration_date = value; }
  /**
   * Accessor method for the expiration date of the domain object
   * @return The domain expiration date
   * @see #m_expiration_date
   */
  public String getExpirationDate() { return m_expiration_date; }

  /**
   * Accessor method for the most recent transfer date of the domain object
   * @param value The domain transfer date
   * @see #m_transfer_date
   */
  public void setTransferDate(String value) { m_transfer_date = value; }
  /**
   * Accessor method for the most recent transfer date of the domain object
   * @return The domain transfer date
   * @see #m_transfer_date
   */
  public String getTransferDate() { return m_transfer_date; }

  /**
   * Accessor method for the authorization information associated with the domain object
   * @param value The domain authorization information
   * @see #m_auth_info
   */
  public void setAuthInfo(org.openrtk.idl.epp0402.epp_AuthInfo value) { m_auth_info = value; }
  /**
   * Accessor method for the authorization information associated with the domain object
   * @return The domain authorization information
   * @see #m_auth_info
   */
  public org.openrtk.idl.epp0402.epp_AuthInfo getAuthInfo() { return m_auth_info; }

  /**
   * Converts this class into a string.
   * Typically used to view the object in debug output.
   * @return The string representation of this object instance
   */
  public String toString() { return this.getClass().getName() + ": { m_rsp ["+m_rsp+"] m_name ["+m_name+"] m_roid ["+m_roid+"] m_status ["+(m_status != null ? java.util.Arrays.asList(m_status) : null)+"] m_registrant ["+m_registrant+"] m_contacts ["+(m_contacts != null ? java.util.Arrays.asList(m_contacts) : null)+"] m_name_servers ["+(m_name_servers != null ? java.util.Arrays.asList(m_name_servers) : null)+"] m_hosts ["+(m_hosts != null ? java.util.Arrays.asList(m_hosts) : null)+"] m_client_id ["+m_client_id+"] m_created_by ["+m_created_by+"] m_created_date ["+m_created_date+"] m_updated_by ["+m_updated_by+"] m_updated_date ["+m_updated_date+"] m_expiration_date ["+m_expiration_date+"] m_transfer_date ["+m_transfer_date+"] m_auth_info ["+m_auth_info+"] }"; }

} // class epp_DomainInfoRsp
