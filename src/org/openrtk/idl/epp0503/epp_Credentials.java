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
 * Class that contains client identity information necessary to establish
 * a session with the EPP server.</p>
 * $Header: /cvsroot/epp-rtk/epp-rtk/java/src/org/openrtk/idl/epp0503/epp_Credentials.java,v 1.1 2003/03/21 16:18:25 tubadanm Exp $<br>
 * $Revision: 1.1 $<br>
 * $Date: 2003/03/21 16:18:25 $<br>
 * @see org.openrtk.idl.epp0503.epp_Command
 */
public class epp_Credentials implements org.omg.CORBA.portable.IDLEntity
{
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
   * @see #setOptions(org.openrtk.idl.epp0503.epp_Options)
   * @see #getOptions()
   */
  public org.openrtk.idl.epp0503.epp_Options m_options = null;

  /**
   * Empty constructor
   */
  public epp_Credentials ()
  {
  } // ctor

  /**
   * The constructor with initializing variables.
   * @param _m_client_id The client identifier created on the server
   * @param _m_password The client password
   * @param _m_new_password The new client password
   * @param _m_options The options element that contains client's choices
   */
  public epp_Credentials (String _m_client_id, String _m_password, String _m_new_password, org.openrtk.idl.epp0503.epp_Options _m_options)
  {
    m_client_id = _m_client_id;
    m_password = _m_password;
    m_new_password = _m_new_password;
    m_options = _m_options;
  } // ctor

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
  public void setOptions(org.openrtk.idl.epp0503.epp_Options value) { m_options = value; }
  /**
   * Accessor method for the options element that contains client's choices
   * @return The options element
   * @see #m_options
   */
  public org.openrtk.idl.epp0503.epp_Options getOptions() { return m_options; }

  /**
   * Converts this class into a string.
   * Typically used to view the object in debug output.
   * @return The string representation of this object instance
   */
  public String toString() { return this.getClass().getName() + ": { m_client_id ["+m_client_id+"] m_password ["+m_password+"] m_new_password ["+m_new_password+"] m_options ["+m_options+"] }"; }

} // class epp_Credentials
