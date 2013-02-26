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

package org.openrtk.idl.epp0604;


/**
 * Class that contains the elements used to represent the poll
 * response from the EPP server.</p>
 * Note usually instantiated by the RTK user, but rather by EPPPoll
 * and retrieved using that class' getResponseData() method.</p>
 * $Header: /cvsroot/epp-rtk/epp-rtk/java/src/org/openrtk/idl/epp0604/epp_PollRsp.java,v 1.2 2003/09/10 21:29:57 tubadanm Exp $<br>
 * $Revision: 1.2 $<br>
 * $Date: 2003/09/10 21:29:57 $<br>
 * @see com.tucows.oxrs.epp0604.rtk.xml.EPPPoll
 * @see org.openrtk.idl.epp0604.epp_PollReq
 */
public class epp_PollRsp implements org.omg.CORBA.portable.IDLEntity
{
  /**
   * The common and generic response element.
   * @see #getRsp()
   */
  public org.openrtk.idl.epp0604.epp_Response m_rsp = null;
  /**
   * The retrieved message data.
   * @see #setResData(org.openrtk.idl.epp0604.epp_PollResData)
   * @see #getResData()
   */
  public org.openrtk.idl.epp0604.epp_PollResData m_res_data = null;

  /**
   * Empty constructor
   */
  public epp_PollRsp ()
  {
  } // ctor

  /**
   * The constructor with initializing variables.
   * @param _m_rsp The common and generic response element
   * @param _m_res_data The retrieved message data
   */
  public epp_PollRsp (org.openrtk.idl.epp0604.epp_Response _m_rsp, org.openrtk.idl.epp0604.epp_PollResData _m_res_data)
  {
    m_rsp = _m_rsp;
    m_res_data = _m_res_data;
  } // ctor

  /**
   * Accessor method for the common and generic response element.
   * @param value The new value for the response element
   * @see #m_rsp
   */
  public void setRsp(org.openrtk.idl.epp0604.epp_Response value) { m_rsp = value; }
  /**
   * Accessor method for the common and generic response element.
   * @return The value for the response element
   * @see #m_rsp
   */
  public org.openrtk.idl.epp0604.epp_Response getRsp() { return m_rsp; }

  /**
   * Accessor method for the retrieved message data
   * @param value The retrieved message data
   * @see #m_res_data
   */
  public void setResData(org.openrtk.idl.epp0604.epp_PollResData value) { m_res_data = value; }
  /**
   * Accessor method for the retrieved message data
   * @return The retrieved message data
   * @see #m_res_data
   */
  public org.openrtk.idl.epp0604.epp_PollResData getResData() { return m_res_data; }

  /**
   * Converts this class into a string.
   * Typically used to view the object in debug output.
   * @return The string representation of this object instance
   */
  public String toString() { return this.getClass().getName() + ": { m_rsp ["+m_rsp+"] m_res_data ["+m_res_data+"] }"; }

} // class epp_PollRsp
