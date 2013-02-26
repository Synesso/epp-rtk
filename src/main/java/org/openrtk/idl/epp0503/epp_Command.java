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
 * Class that contains the common and generic elements necessary for an EPP
 * command sending to the server.</p>
 * $Header: /cvsroot/epp-rtk/epp-rtk/java/src/org/openrtk/idl/epp0503/epp_Command.java,v 1.1 2003/03/21 16:18:25 tubadanm Exp $<br>
 * $Revision: 1.1 $<br>
 * $Date: 2003/03/21 16:18:25 $<br>
 * @see org.openrtk.idl.epp0503.epp_LoginReq#setCmd(epp_Command)
 * @see org.openrtk.idl.epp0503.domain.epp_DomainCreateReq#setCmd(epp_Command)
 */
public class epp_Command implements org.omg.CORBA.portable.IDLEntity
{
  /**
   * The element that provides client identity information.
   * @see #setCreds(org.openrtk.idl.epp0503.epp_Credentials)
   * @see #getCreds()
   */
  public org.openrtk.idl.epp0503.epp_Credentials m_creds = null;
  /**
   * The optional command extensions.
   * @see #setExtension(org.openrtk.idl.epp0503.epp_Extension)
   * @see #getExtension()
   */
  public org.openrtk.idl.epp0503.epp_Extension m_extension = null;
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
   * @param _m_creds The element that provides client identity information
   * @param _m_extension The optional command extensions
   * @param _m_client_trid The client transaction identifier assigned by the client to the command
   */
  public epp_Command (org.openrtk.idl.epp0503.epp_Credentials _m_creds, org.openrtk.idl.epp0503.epp_Extension _m_extension, String _m_client_trid)
  {
    m_creds = _m_creds;
    m_extension = _m_extension;
    m_client_trid = _m_client_trid;
  } // ctor

  /**
   * Accessor method for the element that provides client identity information
   * @param value The client identity credentials
   * @see #m_creds
   */
  public void setCreds(org.openrtk.idl.epp0503.epp_Credentials value) { m_creds = value; }
  /**
   * Accessor method for the element that provides client identity information
   * @return The client identity credentials
   * @see #m_creds
   */
  public org.openrtk.idl.epp0503.epp_Credentials getCreds() { return m_creds; }

  /**
   * Accessor method for the optional command extensions
   * @param value The optional command extensions
   * @see #m_extension
   */
  public void setExtension(org.openrtk.idl.epp0503.epp_Extension value) { m_extension = value; }
  /**
   * Accessor method for the optional command extensions
   * @return The optional command extensions
   * @see #m_extension
   */
  public org.openrtk.idl.epp0503.epp_Extension getExtension() { return m_extension; }

  /**
   * Accessor method for the optional command extensions
   * @param value The optional command extensions
   * @see #m_extension
   * @deprecated Please use setExtension()
   */
  public void setUnspec(org.openrtk.idl.epp0503.epp_Unspec value) { setExtension(value); }
  /**
   * Accessor method for the optional command extensions
   * @return The optional command extensions
   * @see #m_extension
   * @deprecated Please use getExtension()
   */
  public org.openrtk.idl.epp0503.epp_Unspec getUnspec() { return (epp_Unspec)getExtension(); }

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
  public String toString() { return this.getClass().getName() + ": { m_creds ["+m_creds+"] m_extension ["+m_extension+"] m_client_trid ["+m_client_trid+"] }"; }

} // class epp_Command
