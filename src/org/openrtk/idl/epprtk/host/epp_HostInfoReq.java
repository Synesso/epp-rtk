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

package org.openrtk.idl.epprtk.host;


/**
 * Class that contains the elements necessary to retrieve detailed
 * information associated with a host object in the registry.</p>
 * $Header: /cvsroot/epp-rtk/epp-rtk/java/src/org/openrtk/idl/epprtk/host/epp_HostInfoReq.java,v 1.1 2004/12/07 15:27:50 ewang2004 Exp $<br>
 * $Revision: 1.1 $<br>
 * $Date: 2004/12/07 15:27:50 $<br>
 * @see com.tucows.oxrs.epprtk.rtk.xml.EPPHostInfo
 * @see org.openrtk.idl.epprtk.host.epp_HostInfoRsp
 */
public class epp_HostInfoReq implements org.omg.CORBA.portable.IDLEntity
{
  /**
   * The common and generic command element.
   * @see #setCmd(org.openrtk.idl.epprtk.epp_Command)
   * @see #getCmd()
   */
  public org.openrtk.idl.epprtk.epp_Command m_cmd = null;
  /**
   * The name of the host object to be queried.
   * @see #setName(String)
   * @see #getName()
   */
  public String m_name = null;

  /**
   * Empty constructor
   */
  public epp_HostInfoReq ()
  {
  } // ctor

  /**
   * The constructor with initializing variables.
   * @param _m_cmd The common and generic command element
   * @param _m_name The name of the host object to be queried
   */
  public epp_HostInfoReq (org.openrtk.idl.epprtk.epp_Command _m_cmd, String _m_name)
  {
    m_cmd = _m_cmd;
    m_name = _m_name;
  } // ctor

  /**
   * Accessor method for the common and generic command element
   * @param value The command element
   * @see #m_cmd
   */
  public void setCmd(org.openrtk.idl.epprtk.epp_Command value) { m_cmd = value; }
  /**
   * Accessor method for the common and generic command element
   * @return The command element
   * @see #m_cmd
   */
  public org.openrtk.idl.epprtk.epp_Command getCmd() { return m_cmd; }

  /**
   * Accessor method for the name of the host object to be queried
   * @param value The host name
   * @see #m_name
   */
  public void setName(String value) { m_name = value; }
  /**
   * Accessor method for the name of the host object to be queried
   * @return The host name
   * @see #m_name
   */
  public String getName() { return m_name; }

  /**
   * Converts this class into a string.
   * Typically used to view the object in debug output.
   * @return The string representation of this object instance
   */
  public String toString() { return this.getClass().getName() + ": { m_cmd ["+m_cmd+"] m_name ["+m_name+"] }"; }

} // class epp_HostInfoReq
