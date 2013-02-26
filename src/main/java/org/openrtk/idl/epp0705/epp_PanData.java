/*
**
** EPP RTK Java
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
 * This class is use exclusively to hold data associate with a Poll response for a pending action notification.</p>
 * $Header: /cvsroot/epp-rtk/epp-rtk/java/src/org/openrtk/idl/epp0705/epp_PanData.java,v 1.1 2003/03/20 22:42:19 tubadanm Exp $<br>
 * $Revision: 1.1 $<br>
 * $Date: 2003/03/20 22:42:19 $<br>
 */
public abstract class epp_PanData extends org.omg.CORBA.portable.ObjectImpl implements org.omg.CORBA.portable.IDLEntity
{

  /**
   * The result flag for this pending action -- true == successful
   */
  public boolean m_result;
  /**
   * The client and server TRID pair from the EPP request/response which trigger this
   * pending action.
   */
  public org.openrtk.idl.epp0705.epp_TransID m_trid;
  /**
   * The date this pending action completed or failed in the server.
   */
  public String m_date;

  /**
   * Empty constructor
   */
  public epp_PanData ()
  {
  } // ctor

  /**
   * The constructor with initializing variables.
   * @param _m_result The boolean result flag for the action: true == successful execution
   * @param _m_trid The server and client trid pair associated with this pending action
   * @param _m_date The date the pending action completed (or failed)
   */
  public epp_PanData (boolean _m_result, org.openrtk.idl.epp0705.epp_TransID _m_trid, String _m_date)
  {
    m_result = _m_result;
    m_trid = _m_trid;
    m_date = _m_date;
  } // ctor

  /**
   * Accessor method for the boolean result of the execution of the pending action.
   * true == successful execution
   * @param value The result flag
   * @see #m_result
   */
  public void setResult(boolean value) { m_result = value; }
  /**
   * Accessor method for the boolean result of the execution of the pending action.
   * true == successful execution
   * @return The result flag
   * @see #m_result
   */
  public boolean getResult() { return m_result; }

  /**
   * Accessor method for the client and server TRID pair associated with the pending action
   * @param value The client/server TRID pair
   * @see #m_trid
   */
  public void setTrid(org.openrtk.idl.epp0705.epp_TransID value) { m_trid = value; }
  /**
   * Accessor method for the client and server TRID pair associated with the pending action
   * @return The client/server TRID pair
   * @see #m_trid
   */
  public org.openrtk.idl.epp0705.epp_TransID getTrid() { return m_trid; }

  /**
   * Accessor method for the pending action's completion or failure
   * @param value The date of the pending action's completion or failure
   * @see #m_date
   */
  public void setDate(String value) { m_date = value; }
  /**
   * Accessor method for the pending action's completion or failure
   * @return The date of the pending action's completion or failure
   * @see #m_date
   */
  public String getDate() { return m_date; }

  /**
   * Method required by ObjectImpl and the CORBA Object interface.
   * Always returns null.  It's only here to satisfy the CORBA requirements
   * of the IDL usage.
   */
  public String[] _ids() { return null; }


} // interface epp_ContactPanData
