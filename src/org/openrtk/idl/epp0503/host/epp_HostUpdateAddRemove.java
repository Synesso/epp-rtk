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

package org.openrtk.idl.epp0503.host;


/**
 * Class that contains the elements to be added into or removed from the host
 * object in the registry.</p>
 * This class can only be used when modifying a host object.</p>
 * $Header: /cvsroot/epp-rtk/epp-rtk/java/src/org/openrtk/idl/epp0503/host/epp_HostUpdateAddRemove.java,v 1.1 2003/03/21 16:18:31 tubadanm Exp $<br>
 * $Revision: 1.1 $<br>
 * $Date: 2003/03/21 16:18:31 $<br>
 * @see org.openrtk.idl.epp0503.host.epp_HostUpdateReq#setAdd(epp_HostUpdateAddRemove)
 * @see org.openrtk.idl.epp0503.host.epp_HostUpdateReq#setRemove(epp_HostUpdateAddRemove)
 */
public class epp_HostUpdateAddRemove implements org.omg.CORBA.portable.IDLEntity
{
  /**
   * The array of IP addresses to be associated with or removed from the host object.
   * @see #setAddresses(org.openrtk.idl.epp0503.host.epp_HostAddress[])
   * @see #getAddresses()
   */
  public org.openrtk.idl.epp0503.host.epp_HostAddress m_addresses[] = null;
  /**
   * The array of status values to be associated with or removed from the host object.
   * @see #setStatus(org.openrtk.idl.epp0503.host.epp_HostStatus[])
   * @see #getStatus()
   */
  public org.openrtk.idl.epp0503.host.epp_HostStatus m_status[] = null;

  /**
   * Empty constructor
   */
  public epp_HostUpdateAddRemove ()
  {
  } // ctor

  /**
   * The constructor with initializing variables.
   * @param _m_addresses The array of IP addresses
   * @param _m_status The array of host status values
   */
  public epp_HostUpdateAddRemove (org.openrtk.idl.epp0503.host.epp_HostAddress[] _m_addresses, org.openrtk.idl.epp0503.host.epp_HostStatus[] _m_status)
  {
    m_addresses = _m_addresses;
    m_status = _m_status;
  } // ctor

  /**
   * Accessor method for the array of IP addresses
   * @param value The array of host IP addresses
   * @see #m_addresses
   */
  public void setAddresses(org.openrtk.idl.epp0503.host.epp_HostAddress[] value) { m_addresses = value; }
  /**
   * Accessor method for the array of IP addresses
   * @return The array of host addresses
   * @see #m_addresses
   */
  public org.openrtk.idl.epp0503.host.epp_HostAddress[] getAddresses() { return m_addresses; }

  /**
   * Accessor method for the array of host status values
   * @param value The array of host status values
   * @see #m_status
   */
  public void setStatus(org.openrtk.idl.epp0503.host.epp_HostStatus[] value) { m_status = value; }
  /**
   * Accessor method for the array of host status values
   * @return The array of host status values
   * @see #m_status
   */
  public org.openrtk.idl.epp0503.host.epp_HostStatus[] getStatus() { return m_status; }

  /**
   * Converts this class into a string.
   * Typically used to view the object in debug output.
   * @return The string representation of this object instance
   */
  public String toString() { return this.getClass().getName() + ": { m_addresses ["+(m_addresses != null ? java.util.Arrays.asList(m_addresses) : null)+"] m_status ["+(m_status != null ? java.util.Arrays.asList(m_status) : null)+"] }"; }

} // class epp_HostUpdateAddRemove
