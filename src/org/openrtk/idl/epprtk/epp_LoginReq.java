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

package org.openrtk.idl.epprtk;


/**
 * Class that contains the elements necessary to establish a session with
 * an EPP server.</p>
 * $Header: /cvsroot/epp-rtk/epp-rtk/java/src/org/openrtk/idl/epprtk/epp_LoginReq.java,v 1.1 2004/12/07 15:27:49 ewang2004 Exp $<br>
 * $Revision: 1.1 $<br>
 * $Date: 2004/12/07 15:27:49 $<br>
 * @see com.tucows.oxrs.epprtk.rtk.xml.EPPLogin
 * @see org.openrtk.idl.epprtk.epp_LoginRsp
 */
public class epp_LoginReq implements org.omg.CORBA.portable.IDLEntity
{
  /**
   * The common and generic command element.
   * @see #setCmd(org.openrtk.idl.epprtk.epp_Command)
   * @see #getCmd()
   */
  public org.openrtk.idl.epprtk.epp_Command m_cmd = null;
  /**
   * The client identifier created on the server.
   * @see #setClientId(String)
   * @see #getClientId()
   */
  public String m_client_id = null;
  /**
   * The client password.
   * @see #setPassword(String)
   * @see #getPassword()
   */
  public String m_password = null;
  /**
   * The new client password to be used for the future session establishment.
   * @see #setNewPassword(String)
   * @see #getNewPassword()
   */
  public String m_new_password = null;
  /**
   * The options element that contains client's choices on the protocol version and response language
   * @see #setOptions(org.openrtk.idl.epprtk.epp_Options)
   * @see #getOptions()
   */
  public org.openrtk.idl.epprtk.epp_Options m_options = null;

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
   * @param _m_client_id The client identifier created on the server
   * @param _m_password The client password
   * @param _m_new_password The new client password
   * @param _m_options The options element that contains client's choices
   * @param _m_services The array of services supported by the server
   * @param _m_extensions The array of service extensions supported by the server
   */
  public epp_LoginReq (org.openrtk.idl.epprtk.epp_Command _m_cmd, String _m_client_id, String _m_password, String _m_new_password, org.openrtk.idl.epprtk.epp_Options _m_options, String[] _m_services, String[] _m_extensions)
  {
    m_cmd = _m_cmd;
    m_client_id = _m_client_id;
    m_password = _m_password;
    m_new_password = _m_new_password;
    m_options = _m_options;
    m_services = _m_services;
    m_extensions = _m_extensions;
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
   * Accessor method for the client identifier created on the server
   * @param value The client identifier
   * @see #m_client_id
   */
  public void setClientId(String value) { m_client_id = value; }
  /**
   * Accessor method for the client identifier created on the server
   * @return The client identifier
   * @see #m_client_id
   */
  public String getClientId() { return m_client_id; }

  /**
   * Accessor method for the client password
   * @param value The password
   * @see #m_password
   */
  public void setPassword(String value) { m_password = value; }
  /**
   * Accessor method for the client password
   * @return The password
   * @see #m_password
   */
  public String getPassword() { return m_password; }

  /**
   * Accessor method for the new client password
   * @param value The new password
   * @see #m_new_password
   */
  public void setNewPassword(String value) { m_new_password = value; }
  /**
   * Accessor method for the new client password
   * @return The new password
   * @see #m_new_password
   */
  public String getNewPassword() { return m_new_password; }

  /**
   * Accessor method for the options element that contains client's choices
   * @param value The options element
   * @see #m_options
   */
  public void setOptions(org.openrtk.idl.epprtk.epp_Options value) { m_options = value; }
  /**
   * Accessor method for the options element that contains client's choices
   * @return The options element
   * @see #m_options
   */
  public org.openrtk.idl.epprtk.epp_Options getOptions() { return m_options; }

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
  public String toString() { return this.getClass().getName() + ": { m_cmd ["+m_cmd+"] m_client_id ["+m_client_id+"] m_password ["+m_password+"] m_new_password ["+m_new_password+"] m_options ["+m_options+"] m_services ["+(m_services != null ? java.util.Arrays.asList(m_services) : null)+"] m_extensions ["+(m_extensions != null ? java.util.Arrays.asList(m_extensions) : null)+"] }"; }

} // class epp_LoginReq
