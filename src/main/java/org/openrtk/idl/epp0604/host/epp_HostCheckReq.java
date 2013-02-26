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

package org.openrtk.idl.epp0604.host;


/**
 * Class that contains the elements necessary to check to see if
 * a host is available in the registry.</p>
 * $Header: /cvsroot/epp-rtk/epp-rtk/java/src/org/openrtk/idl/epp0604/host/epp_HostCheckReq.java,v 1.2 2003/09/10 21:29:58 tubadanm Exp $<br>
 * $Revision: 1.2 $<br>
 * $Date: 2003/09/10 21:29:58 $<br>
 * @see com.tucows.oxrs.epp0604.rtk.xml.EPPHostCheck
 * @see org.openrtk.idl.epp0604.host.epp_HostCheckRsp
 */
public class epp_HostCheckReq implements org.omg.CORBA.portable.IDLEntity
{
  /**
   * The common and generic command element.
   * @see #setCmd(org.openrtk.idl.epp0604.epp_Command)
   * @see #getCmd()
   */
  public org.openrtk.idl.epp0604.epp_Command m_cmd = null;
  /**
   * The array of host names to check in the registry.
   * @see #setNames(String[])
   * @see #getNames()
   */
  public String m_names[] = null;

  /**
   * Empty constructor
   */
  public epp_HostCheckReq ()
  {
  } // ctor

  /**
   * The constructor with initializing variables.
   * @param _m_cmd The common and generic command element
   * @param _m_names The array of host names to check
   */
  public epp_HostCheckReq (org.openrtk.idl.epp0604.epp_Command _m_cmd, String[] _m_names)
  {
    m_cmd = _m_cmd;
    m_names = _m_names;
  } // ctor

  /**
   * Accessor method for the common and generic command element
   * @param value The command element
   * @see #m_cmd
   */
  public void setCmd(org.openrtk.idl.epp0604.epp_Command value) { m_cmd = value; }
  /**
   * Accessor method for the common and generic command element
   * @return The command element
   * @see #m_cmd
   */
  public org.openrtk.idl.epp0604.epp_Command getCmd() { return m_cmd; }

  /**
   * Accessor method for the array of host names to check in the registry
   * @param value The array of host names
   * @see #m_names
   */
  public void setNames(String[] value) { m_names = value; }
  /**
   * Accessor method for the array of host names to check in the registry
   * @return The array of host names
   * @see #m_names
   */
  public String[] getNames() { return m_names; }

  /**
   * Converts this class into a string.
   * Typically used to view the object in debug output.
   * @return The string representation of this object instance
   */
  public String toString() { return this.getClass().getName() + ": { m_cmd ["+m_cmd+"] m_names ["+(m_names != null ? java.util.Arrays.asList(m_names) : null)+"] }"; }

} // class epp_HostCheckReq
