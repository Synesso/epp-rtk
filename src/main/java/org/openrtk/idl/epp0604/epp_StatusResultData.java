/*
**
** EPP RTK Java
** Copyright (C) 2002, Tucows, Inc.
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

package org.openrtk.idl.epp0604;


/**
 * Class that contains the status command result data.</p>
 * $Header: /cvsroot/epp-rtk/epp-rtk/java/src/org/openrtk/idl/epp0604/epp_StatusResultData.java,v 1.1 2003/03/21 15:54:34 tubadanm Exp $<br>
 * $Revision: 1.1 $<br>
 * $Date: 2003/03/21 15:54:34 $<br>
 * @see org.openrtk.idl.epp0604.epp_ResultData
 */
public class epp_StatusResultData implements org.omg.CORBA.portable.IDLEntity
{
  public boolean m_ack = false;
  public String m_cltrid = null;

  /**
   * Empty constructor.
   */
  public epp_StatusResultData ()
  {
  } // ctor

  /**
   * Constructor with initializers
   * @param _m_ack The acknowledge flag
   * @param _m_cltrid The client TRID
   */
  public epp_StatusResultData (boolean _m_ack, String _m_cltrid)
  {
    m_ack = _m_ack;
    m_cltrid = _m_cltrid;
  } // ctor

  /**
   * Accessor method for the transaction's acknowledge flag
   * @param value The Ack flag
   * @see #m_ack
   */
  public void setAck(boolean value) { m_ack = value; }
  /**
   * Accessor method for the transaction's acknowledge flag
   * @return The Ack flag
   * @see #m_ack
   */
  public boolean getAck() { return m_ack; }

  /**
   * Accessor method for the client transaction id
   * @param value The client TRID
   * @see #m_cltrid
   */
  public void setCltrid(String value) { m_cltrid = value; }
  /**
   * Accessor method for the client transaction id
   * @return The client TRID
   * @see #m_cltrid
   */
  public String getCltrid() { return m_cltrid; }

  /**
   * Converts this class into a string.
   * Typically used to view the object in debug output.
   * @return The string representation of this object instance
   */
  public String toString() { return this.getClass().getName() + ": { m_ack ["+m_ack+"] m_cltrid ["+m_cltrid+"] }"; }

} // class epp_StatusResultData
