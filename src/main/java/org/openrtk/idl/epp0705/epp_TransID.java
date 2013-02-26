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
 * Class that contains elements necessary to represent the client transaction
 * identifier and the server transaction identifier.</p>
 * $Header: /cvsroot/epp-rtk/epp-rtk/java/src/org/openrtk/idl/epp0705/epp_TransID.java,v 1.1 2003/03/20 22:42:19 tubadanm Exp $<br>
 * $Revision: 1.1 $<br>
 * $Date: 2003/03/20 22:42:19 $<br>
 * @see org.openrtk.idl.epp0705.epp_Response
 */
public class epp_TransID implements org.omg.CORBA.portable.IDLEntity
{
  /**
   * The transaction identifier assigned by the client.
   * @see #setClientTrid(String)
   * @see #getClientTrid()
   */
  public String m_client_trid = null;
  /**
   * The transaction identifier assigned by the server.
   * @see #setServerTrid(String)
   * @see #getServerTrid()
   */
  public String m_server_trid = null;

  /**
   * Empty constructor
   */
  public epp_TransID ()
  {
  } // ctor

  /**
   * The constructor with initializing variables.
   * @param _m_client_trid The transaction identifier assigned by the client
   * @param _m_server_trid The transaction identifier assigned by the server
   */
  public epp_TransID (String _m_client_trid, String _m_server_trid)
  {
    m_client_trid = _m_client_trid;
    m_server_trid = _m_server_trid;
  } // ctor

  /**
   * Accessor method for the transaction identifier assigned by the client
   * @param value The client transaction id
   * @see #m_client_trid
   */
  public void setClientTrid(String value) { m_client_trid = value; }
  /**
   * Accessor method for the transaction identifier assigned by the client
   * @return The client transaction id
   * @see #m_client_trid
   */
  public String getClientTrid() { return m_client_trid; }

  /**
   * Accessor method for the transaction identifier assigned by the server
   * @param value The server transaction id
   * @see #m_server_trid
   */
  public void setServerTrid(String value) { m_server_trid = value; }
  /**
   * Accessor method for the transaction identifier assigned by the server
   * @return The server transaction id
   * @see #m_server_trid
   */
  public String getServerTrid() { return m_server_trid; }

  /**
   * Converts this class into a string.
   * Typically used to view the object in debug output.
   * @return The string representation of this object instance
   */
  public String toString() { return this.getClass().getName() + ": { m_client_trid ["+m_client_trid+"] m_server_trid ["+m_server_trid+"] }"; }

} // class epp_TransID
