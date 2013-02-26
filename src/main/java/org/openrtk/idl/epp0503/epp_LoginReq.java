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

package org.openrtk.idl.epp0503;


/**
 * Class that contains the elements necessary to establish a session with
 * an EPP server.</p>
 * $Header: /cvsroot/epp-rtk/epp-rtk/java/src/org/openrtk/idl/epp0503/epp_LoginReq.java,v 1.2 2003/09/10 21:29:56 tubadanm Exp $<br>
 * $Revision: 1.2 $<br>
 * $Date: 2003/09/10 21:29:56 $<br>
 * @see com.tucows.oxrs.epp0503.rtk.xml.EPPLogin
 * @see org.openrtk.idl.epp0503.epp_LoginRsp
 */
public class epp_LoginReq implements org.omg.CORBA.portable.IDLEntity
{
  /**
   * The common and generic command element.
   * @see #setCmd(org.openrtk.idl.epp0503.epp_Command)
   * @see #getCmd()
   */
  public org.openrtk.idl.epp0503.epp_Command m_cmd = null;
  /**
   * The array of service URIs.
   * Each URI identifies an object to be managed during the session.
   * @see #setServices(String[])
   * @see #getServices()
   */
  public String m_services[] = null;
  /**
   * The array of service extensions.
   * Each element identifies a custom object extensions supported by the server.
   * @see #setExtensions(String[])
   * @see #getExtensions()
   */
  public String m_extensions[] = null;

  /**
   * Empty constructor
   */
  public epp_LoginReq ()
  {
  } // ctor

  /**
   * The constructor with initializing variables.
   * @param _m_cmd The common and generic command element
   * @param _m_services The array of services supported by the server
   * @param _m_extensions The array of service extensions supported by the server
   */
  public epp_LoginReq (org.openrtk.idl.epp0503.epp_Command _m_cmd, String[] _m_services, String[] _m_extensions)
  {
    m_cmd = _m_cmd;
    m_services = _m_services;
    m_extensions = _m_extensions;
  } // ctor

  /**
   * Accessor method for the common and generic command element
   * @param value The command element
   * @see #m_cmd
   */
  public void setCmd(org.openrtk.idl.epp0503.epp_Command value) { m_cmd = value; }
  /**
   * Accessor method for the common and generic command element
   * @return The command element
   * @see #m_cmd
   */
  public org.openrtk.idl.epp0503.epp_Command getCmd() { return m_cmd; }

  /**
   * Accessor method for the array of services supported by the server
   * @param value The array of service URIs
   * @see #m_services
   */
  public void setServices(String[] value) { m_services = value; }
  /**
   * Accessor method for the array of services supported by the server
   * @return The array of service URIs
   * @see #m_services
   */
  public String[] getServices() { return m_services; }

  /**
   * Accessor method for the array of service extensions supported by the server
   * @param value The array of service extensions
   * @see #m_extensions
   */
  public void setExtensions(String[] value) { m_extensions = value; }
  /**
   * Accessor method for the array of service extensions supported by the server
   * @return The array of service extensions
   * @see #m_extensions
   */
  public String[] getExtensions() { return m_extensions; }

  /**
   * Converts this class into a string.
   * Typically used to view the object in debug output.
   * @return The string representation of this object instance
   */
  public String toString() { return this.getClass().getName() + ": { m_cmd ["+m_cmd+"] m_services ["+(m_services != null ? java.util.Arrays.asList(m_services) : null)+"] m_extensions ["+(m_extensions != null ? java.util.Arrays.asList(m_extensions) : null)+"] }"; }

} // class epp_LoginReq
