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
 * Class that contains the elements necessary to end a session with the EPP server.</p>
 * $Header: /cvsroot/epp-rtk/epp-rtk/java/src/org/openrtk/idl/epp0402/epp_LogoutReq.java,v 1.2 2003/09/10 21:29:55 tubadanm Exp $<br>
 * $Revision: 1.2 $<br>
 * $Date: 2003/09/10 21:29:55 $<br>
 * @see com.tucows.oxrs.epp0402.rtk.xml.EPPLogout
 * @see org.openrtk.idl.epp0402.epp_LogoutRsp
 */
public class epp_LogoutReq implements org.omg.CORBA.portable.IDLEntity
{
  /**
   * The optional command extensions.
   * @see #setUnspec(org.openrtk.idl.epp0402.epp_Unspec)
   * @see #getUnspec()
   */
  public org.openrtk.idl.epp0402.epp_Unspec m_unspec = null;
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
   * @param _m_unspec The optional command extensions
   * @param _m_client_trid The client transaction identifier assigned by the client to the command
   */
  public epp_LogoutReq (org.openrtk.idl.epp0402.epp_Unspec _m_unspec, String _m_client_trid)
  {
    m_unspec = _m_unspec;
    m_client_trid = _m_client_trid;
  } // ctor

  /**
   * Accessor method for the optional command extensions
   * @param value The optional command extensions
   * @see #m_unspec
   */
  public void setUnspec(org.openrtk.idl.epp0402.epp_Unspec value) { m_unspec = value; }
  /**
   * Accessor method for the optional command extensions
   * @return The optional command extensions
   * @see #m_unspec
   */
  public org.openrtk.idl.epp0402.epp_Unspec getUnspec() { return m_unspec; }

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
  public String toString() { return this.getClass().getName() + ": { m_unspec ["+m_unspec+"] m_client_trid ["+m_client_trid+"] }"; }

} // class epp_LogoutReq
