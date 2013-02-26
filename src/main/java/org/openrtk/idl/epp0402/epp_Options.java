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

package org.openrtk.idl.epp0402;


/**
 * Class that contains the client's choices on the protocol version and
 * the text response language to be used during the ongoing server session.</p>
 * It is used to establish a session with an EPP server.</p>
 * $Header: /cvsroot/epp-rtk/epp-rtk/java/src/org/openrtk/idl/epp0402/epp_Options.java,v 1.1 2003/03/21 16:35:39 tubadanm Exp $<br>
 * $Revision: 1.1 $<br>
 * $Date: 2003/03/21 16:35:39 $<br>
 * @see org.openrtk.idl.epp0402.epp_Credentials
 */
public class epp_Options implements org.omg.CORBA.portable.IDLEntity
{
  /**
   * The protocol version to be used for the command or ongoing server session.
   * The value of the version must exactly match one of the values presented in the EPP greeting.
   * @see #setVersion(String)
   * @see #getVersion()
   */
  public String m_version = null;
  /**
   * The text response language to be used for the command or ongoing server session.
   * The value of the language must exactly match one of the values presented in the EPP greeting.
   * @see #setLang(String)
   * @see #getLang()
   */
  public String m_lang = null;

  /**
   * Empty constructor
   */
  public epp_Options ()
  {
  } // ctor

  /**
   * The constructor with initializing variables.
   * @param _m_version The protocol version to be used during the EPP server session
   * @param _m_lang The text response language to be used during the EPP server session
   */
  public epp_Options (String _m_version, String _m_lang)
  {
    m_version = _m_version;
    m_lang = _m_lang;
  } // ctor

  /**
   * Accessor method for the protocol version to be used during the EPP server session
   * @param value The protocol version
   * @see #m_version
   */
  public void setVersion(String value) { m_version = value; }
  /**
   * Accessor method for the protocol version to be used during the EPP server session
   * @return The protocol version
   * @see #m_version
   */
  public String getVersion() { return m_version; }

  /**
   * Accessor method for the text response language to be used during the EPP server session
   * @param value The text response language
   * @see #m_lang
   */
  public void setLang(String value) { m_lang = value; }
  /**
   * Accessor method for the text response language to be used during the EPP server session
   * @return The text response language
   * @see #m_lang
   */
  public String getLang() { return m_lang; }

  /**
   * Converts this class into a string.
   * Typically used to view the object in debug output.
   * @return The string representation of this object instance
   */
  public String toString() { return this.getClass().getName() + ": { m_version ["+m_version+"] m_lang ["+m_lang+"] }"; }

} // class epp_Options
