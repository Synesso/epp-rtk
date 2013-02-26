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
 * Class that contains the elements used to represent host IP addresses
 * with the type: IPV4, IPV6.</p>
 * $Header: /cvsroot/epp-rtk/epp-rtk/java/src/org/openrtk/idl/epp0402/host/epp_HostAddress.java,v 1.1 2003/03/21 16:35:42 tubadanm Exp $<br>
 * $Revision: 1.1 $<br>
 * $Date: 2003/03/21 16:35:42 $<br>
 * @see org.openrtk.idl.epp0402.host.epp_HostUpdateAddRemove
 * @see org.openrtk.idl.epp0402.host.epp_HostCreateReq
 * @see org.openrtk.idl.epp0402.host.epp_HostInfoRsp
 */
public class epp_HostAddress implements org.omg.CORBA.portable.IDLEntity
{
  /**
   * The type of the host address.
   * @see #setType(org.openrtk.idl.epp0402.host.epp_HostAddressType)
   * @see #getType()
   */
  public org.openrtk.idl.epp0402.host.epp_HostAddressType m_type = null;
  /**
   * The ip address of the host address.
   * @see #setIp(String)
   * @see #getIp()
   */
  public String m_ip = null;

  /**
   * Empty constructor
   */
  public epp_HostAddress ()
  {
  } // ctor

  /**
   * The constructor with initializing variables.
   * @param _m_type The type of the host address
   * @param _m_ip The ip address of the host address
   */
  public epp_HostAddress (org.openrtk.idl.epp0402.host.epp_HostAddressType _m_type, String _m_ip)
  {
    m_type = _m_type;
    m_ip = _m_ip;
  } // ctor

  /**
   * Accessor method for the type of the host address
   * @param value The host address type
   * @see #m_type
   */
  public void setType(org.openrtk.idl.epp0402.host.epp_HostAddressType value) { m_type = value; }
  /**
   * Accessor method for the type of the host address
   * @return The host address type
   * @see #m_type
   */
  public org.openrtk.idl.epp0402.host.epp_HostAddressType getType() { return m_type; }

  /**
   * Accessor method for the ip address of the host address
   * @param value The host ip
   * @see #m_ip
   */
  public void setIp(String value) { m_ip = value; }
  /**
   * Accessor method for the ip address of the host address
   * @return The host ip
   * @see #m_ip
   */
  public String getIp() { return m_ip; }

  /**
   * Converts this class into a string.
   * Typically used to view the object in debug output.
   * @return The string representation of this object instance
   */
  public String toString() { return this.getClass().getName() + ": { m_type ["+m_type+"] m_ip ["+m_ip+"] }"; }

} // class epp_HostAddress
