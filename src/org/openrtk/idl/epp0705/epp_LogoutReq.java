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

package org.openrtk.idl.epp0705;


/**
 * Class that contains the elements necessary to end a session with the EPP server.</p>
 * $Header: /cvsroot/epp-rtk/epp-rtk/java/src/org/openrtk/idl/epp0705/epp_LogoutReq.java,v 1.2 2003/09/10 21:29:58 tubadanm Exp $<br>
 * $Revision: 1.2 $<br>
 * $Date: 2003/09/10 21:29:58 $<br>
 * @see com.tucows.oxrs.epp0705.rtk.xml.EPPLogout
 * @see org.openrtk.idl.epp0705.epp_LogoutRsp
 */
public class epp_LogoutReq implements org.omg.CORBA.portable.IDLEntity
{
  /**
   * The optional command extensions.
   * @see #setExtensions(org.openrtk.idl.epp0705.epp_Extension[])
   * @see #getExtensions()
   */
  public org.openrtk.idl.epp0705.epp_Extension m_extensions[] = null;
  /**
   * The client transaction identifier assigned by the client to the command.
   * @see #setClientTrid(String)
   * @see #getClientTrid()
   */
  public String m_client_trid = null;

  /**
   * Empty constructor
   */
  public epp_LogoutReq ()
  {
  } // ctor

  /**
   * The constructor with initializing variables.
   * @param _m_extension The optional command extensions
   * @param _m_client_trid The client transaction identifier assigned by the client to the command
   * @deprecated
   */
  public epp_LogoutReq (org.openrtk.idl.epp0705.epp_Extension _m_extension, String _m_client_trid)
  {
    m_extensions = new org.openrtk.idl.epp0705.epp_Extension[1];
    m_extensions[0] = _m_extension;
    m_client_trid = _m_client_trid;
  } // ctor

  /**
   * The constructor with initializing variables.
   * @param _m_extensions The optional command extensions
   * @param _m_client_trid The client transaction identifier assigned by the client to the command
   */
  public epp_LogoutReq (org.openrtk.idl.epp0705.epp_Extension[] _m_extensions, String _m_client_trid)
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
  public void setExtension(org.openrtk.idl.epp0705.epp_Extension value) {
    m_extensions = new org.openrtk.idl.epp0705.epp_Extension[1];
    m_extensions[0] = value;
  }
  /**
   * Accessor method for the optional command extensions
   * @return The optional command extensions
   * @see #m_extensions
   * @deprecated
   */
  public org.openrtk.idl.epp0705.epp_Extension getExtension() { return m_extensions == null ? null : m_extensions[0]; }

  /**
   * Accessor method for the optional command extensions
   * @param value The optional command extensions
   * @see #m_extensions
   */
  public void setExtensions(org.openrtk.idl.epp0705.epp_Extension[] value) { m_extensions = value; }
  /**
   * Accessor method for the optional command extensions
   * @return The optional command extensions
   * @see #m_extensions
   */
  public org.openrtk.idl.epp0705.epp_Extension[] getExtensions() { return m_extensions; }

  /**
   * Accessor method for the optional command extensions
   * @param value The optional command extensions
   * @see #m_extension
   * @deprecated Please use setExtension() instead.
   */
  public void setUnspec(org.openrtk.idl.epp0705.epp_Unspec value) { setExtension(value); }
  /**
   * Accessor method for the optional command extensions
   * @return The optional command extensions
   * @see #m_extension
   * @deprecated Please use getExtension() intead.
   */
  public org.openrtk.idl.epp0705.epp_Unspec getUnspec() { return (epp_Unspec)getExtension(); }

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

} // class epp_LogoutReq
