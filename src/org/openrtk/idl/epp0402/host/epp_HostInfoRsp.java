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

package org.openrtk.idl.epp0402.host;


/**
 * Class that contains the elements used to represent the host info
 * response from the EPP server.</p>
 * Note usually instantiated by the RTK user, but rather by EPPHostInfo
 * and retrieved using that class' getResponseData() method.</p>
 * $Header: /cvsroot/epp-rtk/epp-rtk/java/src/org/openrtk/idl/epp0402/host/epp_HostInfoRsp.java,v 1.2 2003/09/10 21:29:56 tubadanm Exp $<br>
 * $Revision: 1.2 $<br>
 * $Date: 2003/09/10 21:29:56 $<br>
 * @see com.tucows.oxrs.epp0402.rtk.xml.EPPHostInfo
 * @see org.openrtk.idl.epp0402.host.epp_HostInfoReq
 */
public class epp_HostInfoRsp implements org.omg.CORBA.portable.IDLEntity
{
  /**
   * The common and generic response element.
   * @see #getRsp()
   */
  public org.openrtk.idl.epp0402.epp_Response m_rsp = null;
  /**
   * The name of the host object in the registry.
   * @see #setName(String)
   * @see #getName()
   */
  public String m_name = null;
  /**
   * The repository object identifier associated with the host object.
   * @see #setRoid(String)
   * @see #getRoid()
   */
  public String m_roid = null;
  /**
   * The array of status associated with the host object.
   * @see #setStatus(org.openrtk.idl.epp0402.host.epp_HostStatus[])
   * @see #getStatus()
   */
  public org.openrtk.idl.epp0402.host.epp_HostStatus m_status[] = null;
  /**
   * The array of IP addresses associated with the host object.
   * @see #setAddresses(org.openrtk.idl.epp0402.host.epp_HostAddress[])
   * @see #getAddresses()
   */
  public org.openrtk.idl.epp0402.host.epp_HostAddress m_addresses[] = null;
  /**
   * The identifier of the sponsoring client.
   * @see #setClientId(String)
   * @see #getClientId()
   */
  public String m_client_id = null;
  /**
   * The identifier of the client that created the host object.
   * @see #setCreatedBy(String)
   * @see #getCreatedBy()
   */
  public String m_created_by = null;
  /**
   * The creation date of the host object.
   * @see #setCreatedDate(String)
   * @see #getCreatedDate()
   */
  public String m_created_date = null;
  /**
   * The identifier of the client that last updated the host object.
   * @see #setUpdatedBy(String)
   * @see #getUpdatedBy()
   */
  public String m_updated_by = null;
  /**
   * The most recent modification date of the host object.
   * @see #setUpdatedDate(String)
   * @see #getUpdatedDate()
   */
  public String m_updated_date = null;
  /**
   * The most recent transfer date of the host object.
   * @see #setTransferDate(String)
   * @see #getTransferDate()
   */
  public String m_transfer_date = null;

  /**
   * Empty constructor
   */
  public epp_HostInfoRsp ()
  {
  } // ctor

  /**
   * The constructor with initializing variables.
   * @param _m_rsp The common and generic response element
   * @param _m_name The name of the host object in the registry
   * @param _m_roid The repository object identifier associated with the host object
   * @param _m_status The array of status associated with the host object
   * @param _m_addresses The array of IP addresses associated with the host object
   * @param _m_client_id The identifier of the sponsoring client
   * @param _m_created_by The identifier of the client that created the host object
   * @param _m_created_date The creation date of the host object
   * @param _m_updated_by The identifier of the client that last updated the host object
   * @param _m_updated_date The most recent modification date of the host object
   * @param _m_transfer_date The most recent transfer date of the host object
   */
  public epp_HostInfoRsp (org.openrtk.idl.epp0402.epp_Response _m_rsp, String _m_name, String _m_roid, org.openrtk.idl.epp0402.host.epp_HostStatus[] _m_status, org.openrtk.idl.epp0402.host.epp_HostAddress[] _m_addresses, String _m_client_id, String _m_created_by, String _m_created_date, String _m_updated_by, String _m_updated_date, String _m_transfer_date)
  {
    m_rsp = _m_rsp;
    m_name = _m_name;
    m_roid = _m_roid;
    m_status = _m_status;
    m_addresses = _m_addresses;
    m_client_id = _m_client_id;
    m_created_by = _m_created_by;
    m_created_date = _m_created_date;
    m_updated_by = _m_updated_by;
    m_updated_date = _m_updated_date;
    m_transfer_date = _m_transfer_date;
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
   * Accessor method for the name of the host object in the registry
   * @param value The host name
   * @see #m_name
   */
  public void setName(String value) { m_name = value; }
  /**
   * Accessor method for the name of the host object in the registry
   * @return The host name
   * @see #m_name
   */
  public String getName() { return m_name; }

  /**
   * Accessor method for the repository object identifier associated with the host object
   * @param value The host repository object identifier
   * @see #m_roid
   */
  public void setRoid(String value) { m_roid = value; }
  /**
   * Accessor method for the repository object identifier associated with the host object
   * @return The host repository object identifier
   * @see #m_roid
   */
  public String getRoid() { return m_roid; }

  /**
   * Accessor method for the array of status associated with the host object
   * @param value The array of host status
   * @see #m_status
   */
  public void setStatus(org.openrtk.idl.epp0402.host.epp_HostStatus[] value) { m_status = value; }
  /**
   * Accessor method for the array of status associated with the host object
   * @return The array of host status
   * @see #m_status
   */
  public org.openrtk.idl.epp0402.host.epp_HostStatus[] getStatus() { return m_status; }

  /**
   * Accessor method for the array of IP addresses associated with the host object
   * @param value The array of host IP addresses
   * @see #m_addresses
   */
  public void setAddresses(org.openrtk.idl.epp0402.host.epp_HostAddress[] value) { m_addresses = value; }
  /**
   * Accessor method for the array of IP addresses associated with the host object
   * @return The array of host IP addresses
   * @see #m_addresses
   */
  public org.openrtk.idl.epp0402.host.epp_HostAddress[] getAddresses() { return m_addresses; }

  /**
   * Accessor method for the identifier of the sponsoring client
   * @param value The host sponsoring client id
   * @see #m_client_id
   */
  public void setClientId(String value) { m_client_id = value; }
  /**
   * Accessor method for the identifier of the sponsoring client
   * @return The host sponsoring client id
   * @see #m_client_id
   */
  public String getClientId() { return m_client_id; }

  /**
   * Accessor method for the identifier of the client that created the host object
   * @param value The identifier of the client that created the host object
   * @see #m_created_by
   */
  public void setCreatedBy(String value) { m_created_by = value; }
  /**
   * Accessor method for the identifier of the client that created the host object
   * @return The identifier of the client that created the host object
   * @see #m_created_by
   */
  public String getCreatedBy() { return m_created_by; }

  /**
   * Accessor method for the creation date of the host object
   * @param value The host creation date
   * @see #m_created_date
   */
  public void setCreatedDate(String value) { m_created_date = value; }
  /**
   * Accessor method for the creation date of the host object
   * @return The host creation date
   * @see #m_created_date
   */
  public String getCreatedDate() { return m_created_date; }

  /**
   * Accessor method for the identifier of the client that last updated the host object
   * @param value The identifier of the client that last updated the host object
   * @see #m_updated_by
   */
  public void setUpdatedBy(String value) { m_updated_by = value; }
  /**
   * Accessor method for the identifier of the client that last updated the host object
   * @return The identifier of the client that last updated the host object
   * @see #m_updated_by
   */
  public String getUpdatedBy() { return m_updated_by; }

  /**
   * Accessor method for the most recent modification date of the host object
   * @param value The most recent modification date
   * @see #m_updated_date
   */
  public void setUpdatedDate(String value) { m_updated_date = value; }
  /**
   * Accessor method for the most recent modification date of the host object
   * @return The most recent modification date
   * @see #m_updated_date
   */
  public String getUpdatedDate() { return m_updated_date; }

  /**
   * Accessor method for the most recent transfer date of the host object
   * @param value The most recent host transfer date
   * @see #m_transfer_date
   */
  public void setTransferDate(String value) { m_transfer_date = value; }
  /**
   * Accessor method for the most recent transfer date of the host object
   * @return The most recent host transfer date
   * @see #m_transfer_date
   */
  public String getTransferDate() { return m_transfer_date; }

  /**
   * Converts this class into a string.
   * Typically used to view the object in debug output.
   * @return The string representation of this object instance
   */
  public String toString() { return this.getClass().getName() + ": { m_rsp ["+m_rsp+"] m_name ["+m_name+"] m_roid ["+m_roid+"] m_status ["+(m_status != null ? java.util.Arrays.asList(m_status) : null)+"] m_addresses ["+(m_addresses != null ? java.util.Arrays.asList(m_addresses) : null)+"] m_client_id ["+m_client_id+"] m_created_by ["+m_created_by+"] m_created_date ["+m_created_date+"] m_updated_by ["+m_updated_by+"] m_updated_date ["+m_updated_date+"] m_transfer_date ["+m_transfer_date+"] }"; }

} // class epp_HostInfoRsp
