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
 * Class that contains the common and generic elements necessary for an EPP
 * command sending to the server.</p>
 * $Header: /cvsroot/epp-rtk/epp-rtk/java/src/org/openrtk/idl/epprtk/epp_Command.java,v 1.1 2004/12/07 15:27:49 ewang2004 Exp $<br>
 * $Revision: 1.1 $<br>
 * $Date: 2004/12/07 15:27:49 $<br>
 * @see org.openrtk.idl.epprtk.epp_LoginReq#setCmd(epp_Command)
 * @see org.openrtk.idl.epprtk.domain.epp_DomainCreateReq#setCmd(epp_Command)
 */
public class epp_Command implements org.omg.CORBA.portable.IDLEntity
{
  /**
   * The optional command extensions.
   * @see #setExtensions(org.openrtk.idl.epprtk.epp_Extension)
   * @see #getExtensions()
   */
  public org.openrtk.idl.epprtk.epp_Extension m_extensions[] = null;
  /**
   * The client transaction identifier assigned by the client to the command.
   * @see #setClientTrid(String)
   * @see #getClientTrid()
   */
  public String m_client_trid = null;

  /**
   * Empty constructor
   */
  public epp_Command ()
  {
  } // ctor

  /**
   * The constructor with initializing variables.
   * @param _m_extensions The optional command extensions
   * @param _m_client_trid The client transaction identifier assigned by the client to the command
   */
  public epp_Command (org.openrtk.idl.epprtk.epp_Extension[] _m_extensions, String _m_client_trid)
  {
    m_extensions = _m_extensions;
    m_client_trid = _m_client_trid;
  } // ctor

  /**
   * Accessor method for the optional command extensions
   * @param value The optional command extensions
   * @see #m_extensions
   * @deprecated
   */
  public void setExtension(org.openrtk.idl.epprtk.epp_Extension value) {
    m_extensions = new org.openrtk.idl.epprtk.epp_Extension[1];
    m_extensions[0] = value;
  }
  /**
   * Accessor method for the optional command extensions
   * @return The optional command extensions
   * @see #m_extensions
   * @deprecated
   */
  public org.openrtk.idl.epprtk.epp_Extension getExtension() { return m_extensions == null ? null : m_extensions[0]; }

  /**
   * Accessor method for the optional command extensions
   * @param value The optional command extensions
   * @see #m_extensions
   */
  public void setExtensions(org.openrtk.idl.epprtk.epp_Extension[] value) { m_extensions = value; }
  /**
   * Accessor method for the optional command extensions
   * @return The optional command extensions
   * @see #m_extensions
   */
  public org.openrtk.idl.epprtk.epp_Extension[] getExtensions() { return m_extensions; }

  /**
   * Accessor method for the optional command extensions
   * @param value The optional command extensions
   * @see #m_extension
   * @deprecated Please use setExtension()
   */
  public void setUnspec(org.openrtk.idl.epprtk.epp_Unspec value) { setExtension(value); }
  /**
   * Accessor method for the optional command extensions
   * @return The optional command extensions
   * @see #m_extension
   * @deprecated Please use getExtension()
   */
  public org.openrtk.idl.epprtk.epp_Unspec getUnspec() { return (epp_Unspec)getExtension(); }

  /**
   * Accessor method for the client transaction identifier assigned by the client to the command
   * @param value The client transaction identifier
   * @see #m_client_trid
   */
  public void setClientTrid(String value) { m_client_trid = value; }
  /**
   * Accessor method for the client transaction identifier assigned by the client to the command
   * @return The client transaction identifier
   * @see #m_client_trid
   */
  public String getClientTrid() { return m_client_trid; }

  /**
   * Converts this class into a string.
   * Typically used to view the object in debug output.
   * @return The string representation of this object instance
   */
  public String toString() { return this.getClass().getName() + ": { m_extensions ["+(m_extensions != null ? java.util.Arrays.asList(m_extensions) : null)+"] m_client_trid ["+m_client_trid+"] }"; }

} // class epp_Command
