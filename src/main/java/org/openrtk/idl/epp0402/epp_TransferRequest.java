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
 * Class that contains elements used in requests to transfer any EPP object.</p>
 * The minimum required data elements are the object's authorization information
 * and one of the transfer operation types: APPROVE, CANCEL, QUERY, REJECT and
 * REQUEST.</p>
 * $Header: /cvsroot/epp-rtk/epp-rtk/java/src/org/openrtk/idl/epp0402/epp_TransferRequest.java,v 1.1 2003/03/21 16:35:39 tubadanm Exp $<br>
 * $Revision: 1.1 $<br>
 * $Date: 2003/03/21 16:35:39 $<br>
 * @see org.openrtk.idl.epp0402.domain.epp_DomainTransferReq
 * @see org.openrtk.idl.epp0402.contact.epp_ContactTransferReq
 */
public class epp_TransferRequest implements org.omg.CORBA.portable.IDLEntity
{
  /**
   * The authorization information required to authorize object transfers.
   * @see #setAuthInfo(org.openrtk.idl.epp0402.epp_AuthInfo)
   * @see #getAuthInfo()
   */
  public org.openrtk.idl.epp0402.epp_AuthInfo m_auth_info = null;
  /**
   * The transfer operation type.
   * @see #setOp(org.openrtk.idl.epp0402.epp_TransferOpType)
   * @see #getOp()
   */
  public org.openrtk.idl.epp0402.epp_TransferOpType m_op = null;

  /**
   * Empty constructor
   */
  public epp_TransferRequest ()
  {
  } // ctor

  /**
   * The constructor with initializing variables.
   * @param _m_auth_info The authorization information required to authorize object transfers
   * @param _m_op The transfer operation type
   */
  public epp_TransferRequest (org.openrtk.idl.epp0402.epp_AuthInfo _m_auth_info, org.openrtk.idl.epp0402.epp_TransferOpType _m_op)
  {
    m_auth_info = _m_auth_info;
    m_op = _m_op;
  } // ctor

  /**
   * Accessor method for the authorization information
   * @param value The authorization information
   * @see #m_auth_info
   */
  public void setAuthInfo(org.openrtk.idl.epp0402.epp_AuthInfo value) { m_auth_info = value; }
  /**
   * Accessor method for the authorization information
   * @return The authorization information
   * @see #m_auth_info
   */
  public org.openrtk.idl.epp0402.epp_AuthInfo getAuthInfo() { return m_auth_info; }

  /**
   * Accessor method for the transfer operation type
   * @param value The transfer operation type
   * @see #m_op
   */
  public void setOp(org.openrtk.idl.epp0402.epp_TransferOpType value) { m_op = value; }
  /**
   * Accessor method for the transfer operation type
   * @return The transfer operation type
   * @see #m_op
   */
  public org.openrtk.idl.epp0402.epp_TransferOpType getOp() { return m_op; }

  /**
   * Converts this class into a string.
   * Typically used to view the object in debug output.
   * @return The string representation of this object instance
   */
  public String toString() { return this.getClass().getName() + ": { m_auth_info ["+m_auth_info+"] m_op ["+m_op+"] }"; }

} // class epp_TransferRequest
