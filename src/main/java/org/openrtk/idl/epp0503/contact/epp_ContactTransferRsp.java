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

package org.openrtk.idl.epp0503.contact;


/**
 * Class that contains the elements used to represent the contact transfer
 * response from the EPP server.</p>
 * Note usually instantiated by the RTK user, but rather by EPPContactTransfer
 * and retrieved using that class' getResponseData() method.</p>
 * $Header: /cvsroot/epp-rtk/epp-rtk/java/src/org/openrtk/idl/epp0503/contact/epp_ContactTransferRsp.java,v 1.2 2003/09/10 21:29:56 tubadanm Exp $<br>
 * $Revision: 1.2 $<br>
 * $Date: 2003/09/10 21:29:56 $<br>
 * @see com.tucows.oxrs.epp0503.rtk.xml.EPPContactTransfer
 * @see org.openrtk.idl.epp0503.contact.epp_ContactTransferReq
 */
public class epp_ContactTransferRsp implements org.omg.CORBA.portable.IDLEntity
{
  /**
   * The common and generic response element.
   * @see #getRsp()
   */
  public org.openrtk.idl.epp0503.epp_Response m_rsp = null;
  /**
   * The transfer data for the contact object in the registry.
   * @see #setTrnData(org.openrtk.idl.epp0503.contact.epp_ContactTrnData)
   * @see #getTrnData()
   */
  public org.openrtk.idl.epp0503.contact.epp_ContactTrnData m_trn_data = null;

  /**
   * Empty constructor
   */
  public epp_ContactTransferRsp ()
  {
  } // ctor

  /**
   * The constructor with initializing variables.
   * @param _m_rsp The common and generic response element
   * @param _m_id The identifier for the contact object in the registry
   * @param _m_transfer_status The transfer status associated with the contact object
   * @param _m_request_client_id The identifier of the client that requested the contact object transfer
   * @param _m_request_date The date that the contact transfer was requested
   * @param _m_action_client_id The identifier of the client that should act upon the contact transfer request
   * @param _m_action_date The required or completed action date to the contact transfer request
   * @deprecated  Please use epp_ContactTransferRsp(epp_Response, epp_ContactTrnData) instead
   */
  public epp_ContactTransferRsp (org.openrtk.idl.epp0503.epp_Response _m_rsp, String _m_id, org.openrtk.idl.epp0503.epp_TransferStatusType _m_transfer_status, String _m_request_client_id, String _m_request_date, String _m_action_client_id, String _m_action_date)
  {
    m_rsp = _m_rsp;
    m_trn_data = new org.openrtk.idl.epp0503.contact.epp_ContactTrnData();
    m_trn_data.m_id = _m_id;
    m_trn_data.m_transfer_status = _m_transfer_status;
    m_trn_data.m_request_client_id = _m_request_client_id;
    m_trn_data.m_request_date = _m_request_date;
    m_trn_data.m_action_client_id = _m_action_client_id;
    m_trn_data.m_action_date = _m_action_date;
  } // ctor

  /**
   * The constructor with initializing variables.
   * @param _m_rsp The common and generic response element
   * @param _m_trn_data The transfer data for the contact object in the registry
   */
  public epp_ContactTransferRsp (org.openrtk.idl.epp0503.epp_Response _m_rsp, org.openrtk.idl.epp0503.contact.epp_ContactTrnData _m_trn_data)
  {
    m_rsp = _m_rsp;
    m_trn_data = _m_trn_data;
  } // ctor

  /**
   * Accessor method for the common and generic response element.
   * @param value The new value for the response element
   * @see #m_rsp
   */
  public void setRsp(org.openrtk.idl.epp0503.epp_Response value) { m_rsp = value; }
  /**
   * Accessor method for the common and generic response element.
   * @return The value for the response element
   * @see #m_rsp
   */
  public org.openrtk.idl.epp0503.epp_Response getRsp() { return m_rsp; }

  /**
   * Accessor method for the transfer data of the contact object in the registry
   * @param value The contact's transfer data
   * @see #m_trn_data
   */
  public void setTrnData(org.openrtk.idl.epp0503.contact.epp_ContactTrnData value) { m_trn_data = value; }
  /**
   * Accessor method for the transfer data of the contact object in the registry
   * @return The contact's transfer data
   * @see #m_trn_data
   */
  public org.openrtk.idl.epp0503.contact.epp_ContactTrnData getTrnData() { return m_trn_data; }

  /**
   * @deprecated
   * @see #setId(String)
   */
  public void setRoid(String value) { setId(value); }
  /**
   * @deprecated
   * @see #getId()
   */
  public String getRoid() { return getId(); }

  /**
   * Accessor method for the identifier for the contact object in the registry
   * @param value The contact id
   * @see #m_trn_data
   * @deprecated Please use setTrnData(org.openrtk.idl.epp0503.contact.epp_ContactTrnData) instead
   */
  public void setId(String value) { if ( m_trn_data == null ) { m_trn_data = new epp_ContactTrnData(); } m_trn_data.m_id = value; }
  /**
   * Accessor method for the identifier for the contact object in the registry
   * @return The contact id
   * @see #m_trn_data
   * @deprecated Please use getTrnData() instead
   */
  public String getId() { return m_trn_data == null ? null : m_trn_data.m_id; }

  /**
   * Accessor method for the transfer status associated with the contact object
   * @param value The contact transfer status
   * @see #m_trn_data
   * @deprecated Please use setTrnData(org.openrtk.idl.epp0503.contact.epp_ContactTrnData) instead
   */
  public void setTransferStatus(org.openrtk.idl.epp0503.epp_TransferStatusType value) { if ( m_trn_data == null ) { m_trn_data = new epp_ContactTrnData(); } m_trn_data.m_transfer_status = value; }
  /**
   * Accessor method for the transfer status associated with the contact object
   * @return The contact transfer status
   * @see #m_trn_data
   * @deprecated Please use getTrnData() instead
   */
  public org.openrtk.idl.epp0503.epp_TransferStatusType getTransferStatus() { return m_trn_data == null ? null : m_trn_data.m_transfer_status; }

  /**
   * Accessor method for the client that requested the contact object transfer
   * @param value The client that requested the contact object transfer
   * @see #m_trn_data
   * @deprecated Please use setTrnData(org.openrtk.idl.epp0503.contact.epp_ContactTrnData) instead
   */
  public void setRequestClientId(String value) { if ( m_trn_data == null ) { m_trn_data = new epp_ContactTrnData(); } m_trn_data.m_request_client_id = value; }
  /**
   * Accessor method for the client that requested the contact object transfer
   * @return The client that requested the contact object transfer
   * @see #m_trn_data
   * @deprecated Please use getTrnData() instead
   */
  public String getRequestClientId() { return m_trn_data == null ? null : m_trn_data.m_request_client_id; }

  /**
   * Accessor method for the date that the contact transfer was requested
   * @param value The date that the contact transfer was requested
   * @see #m_trn_data
   * @deprecated Please use setTrnData(org.openrtk.idl.epp0503.contact.epp_ContactTrnData) instead
   */
  public void setRequestDate(String value) { if ( m_trn_data == null ) { m_trn_data = new epp_ContactTrnData(); } m_trn_data.m_request_date = value; }
  /**
   * Accessor method for the date that the contact transfer was requested
   * @return The date that the contact transfer was requested
   * @see #m_trn_data
   * @deprecated Please use getTrnData() instead
   */
  public String getRequestDate() { return m_trn_data == null ? null : m_trn_data.m_request_date; }

  /**
   * Accessor method for the identifier of the client that should act upon the contact transfer request
   * @param value The identifier of the client that should act upon the contact transfer request
   * @see #m_trn_data
   * @deprecated Please use setTrnData(org.openrtk.idl.epp0503.contact.epp_ContactTrnData) instead
   */
  public void setActionClientId(String value) { if ( m_trn_data == null ) { m_trn_data = new epp_ContactTrnData(); } m_trn_data.m_action_client_id = value; }
  /**
   * Accessor method for the identifier of the client that should act upon the contact transfer request
   * @return The identifier of the client that should act upon the contact transfer request
   * @see #m_trn_data
   * @deprecated Please use getTrnData() instead
   */
  public String getActionClientId() { return m_trn_data == null ? null : m_trn_data.m_action_client_id; }

  /**
   * Accessor method for the required or completed action date to the contact transfer request
   * @param value The contact transfer action date
   * @see #m_trn_data
   * @deprecated Please use setTrnData(org.openrtk.idl.epp0503.contact.epp_ContactTrnData) instead
   */
  public void setActionDate(String value) { if ( m_trn_data == null ) { m_trn_data = new epp_ContactTrnData(); } m_trn_data.m_action_date = value; }
  /**
   * Accessor method for the required or completed action date to the contact transfer request
   * @return The contact transfer action date
   * @see #m_trn_data
   * @deprecated Please use getTrnData() instead
   */
  public String getActionDate() { return m_trn_data == null ? null : m_trn_data.m_action_date; }

  /**
   * Converts this class into a string.
   * Typically used to view the object in debug output.
   * @return The string representation of this object instance
   */
  public String toString() { return this.getClass().getName() + ": { m_rsp ["+m_rsp+"] m_trn_data ["+m_trn_data+"] }"; }

} // class epp_ContactTransferRsp
