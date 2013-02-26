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

package org.openrtk.idl.epp0604.domain;


/**
 * Class that contains the elements used to represent the domain transfer
 * response from the EPP server.</p>
 * Note usually instantiated by the RTK user, but rather by EPPDomainTransfer
 * and retrieved using that class' getResponseData() method.</p>
 * $Header: /cvsroot/epp-rtk/epp-rtk/java/src/org/openrtk/idl/epp0604/domain/epp_DomainTransferRsp.java,v 1.2 2003/09/10 21:29:58 tubadanm Exp $<br>
 * $Revision: 1.2 $<br>
 * $Date: 2003/09/10 21:29:58 $<br>
 * @see com.tucows.oxrs.epp0604.rtk.xml.EPPDomainTransfer
 * @see org.openrtk.idl.epp0604.domain.epp_DomainTransferReq
 */
public class epp_DomainTransferRsp implements org.omg.CORBA.portable.IDLEntity
{
  /**
   * The common and generic response element.
   * @see #getRsp()
   */
  public org.openrtk.idl.epp0604.epp_Response m_rsp = null;
  /**
   * The transfer data of the domain in the registry.
   * @see #setTrnData(org.openrtk.idl.epp0604.domain.epp_DomainTrnData)
   * @see #getTrnData()
   */
  public org.openrtk.idl.epp0604.domain.epp_DomainTrnData m_trn_data = null;

  /**
   * Empty constructor
   */
  public epp_DomainTransferRsp ()
  {
  } // ctor

  /**
   * The constructor with initializing variables.
   * @param _m_rsp The common and generic response element
   * @param _m_name The name of the domain object in the registry
   * @param _m_transfer_status The transfer status associated with the domain object
   * @param _m_request_client_id The identifier of the client that requested the domain object transfer
   * @param _m_action_client_id The identifier of the client that should act upon the domain transfer request
   * @param _m_request_date The date that the domain transfer was requested
   * @param _m_action_date The required or completed action date to the domain transfer request
   * @param _m_expiration_date The expiration date of the domain object
   * @deprecated  Please use epp_DomainTransferRsp(epp_Response, epp_DomainTrnData) instead
   */
  public epp_DomainTransferRsp (org.openrtk.idl.epp0604.epp_Response _m_rsp, String _m_name, org.openrtk.idl.epp0604.epp_TransferStatusType _m_transfer_status, String _m_request_client_id, String _m_action_client_id, String _m_request_date, String _m_action_date, String _m_expiration_date)
  {
    m_rsp = _m_rsp;
    m_trn_data = new org.openrtk.idl.epp0604.domain.epp_DomainTrnData();
    m_trn_data.m_name = _m_name;
    m_trn_data.m_transfer_status = _m_transfer_status;
    m_trn_data.m_request_client_id = _m_request_client_id;
    m_trn_data.m_action_client_id = _m_action_client_id;
    m_trn_data.m_request_date = _m_request_date;
    m_trn_data.m_action_date = _m_action_date;
    m_trn_data.m_expiration_date = _m_expiration_date;
  } // ctor

  /**
   * The constructor with initializing variables.
   * @param _m_rsp The common and generic response element
   * @param _m_trn_data The transfer data for the domain object in the registry
   */
  public epp_DomainTransferRsp (org.openrtk.idl.epp0604.epp_Response _m_rsp, org.openrtk.idl.epp0604.domain.epp_DomainTrnData _m_trn_data)
  {
    m_rsp = _m_rsp;
    m_trn_data = _m_trn_data;
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
   * Accessor method for the transfer data of the domain object in the registry
   * @param value The domain's transfer data
   * @see #m_trn_data
   */
  public void setTrnData(org.openrtk.idl.epp0604.domain.epp_DomainTrnData value) { m_trn_data = value; }
  /**
   * Accessor method for the transfer data of the domain object in the registry
   * @return The domain's transfer data
   * @see #m_trn_data
   */
  public org.openrtk.idl.epp0604.domain.epp_DomainTrnData getTrnData() { return m_trn_data; }

  /**
   * Accessor method for the name of the domain object in the registry
   * @param value The domain name
   * @see #m_trn_data
   * @deprecated Please use setTrnData(org.openrtk.idl.epp0604.domain.epp_DomainTrnData) instead
   */
  public void setName(String value) { if ( m_trn_data == null ) { m_trn_data = new epp_DomainTrnData(); } m_trn_data.m_name = value; }
  /**
   * Accessor method for the name of the domain object in the registry
   * @return The domain name
   * @see #m_trn_data
   * @deprecated Please use getTrnData() instead
   */
  public String getName() { return m_trn_data == null ? null : m_trn_data.m_name; }

  /**
   * Accessor method for the transfer status associated with the domain object
   * @param value The domain transfer status
   * @see #m_trn_data
   * @deprecated Please use setTrnData(org.openrtk.idl.epp0604.domain.epp_DomainTrnData) instead
   */
  public void setTransferStatus(org.openrtk.idl.epp0604.epp_TransferStatusType value) { if ( m_trn_data == null ) { m_trn_data = new epp_DomainTrnData(); } m_trn_data.m_transfer_status = value; }
  /**
   * Accessor method for the transfer status associated with the domain object
   * @return The domain transfer status
   * @see #m_trn_data
   * @deprecated Please use getTrnData() instead
   */
  public org.openrtk.idl.epp0604.epp_TransferStatusType getTransferStatus() { return m_trn_data == null ? null : m_trn_data.m_transfer_status; }

  /**
   * Accessor method for the identifier of the client that requested the domain object transfer
   * @param value The domain request client id
   * @see #m_trn_data
   * @deprecated Please use setTrnData(org.openrtk.idl.epp0604.domain.epp_DomainTrnData) instead
   */
  public void setRequestClientId(String value) { if ( m_trn_data == null ) { m_trn_data = new epp_DomainTrnData(); } m_trn_data.m_request_client_id = value; }
  /**
   * Accessor method for the identifier of the client that requested the domain object transfer
   * @return The domain request client id
   * @see #m_trn_data
   * @deprecated Please use getTrnData() instead
   */
  public String getRequestClientId() { return m_trn_data == null ? null : m_trn_data.m_request_client_id; }

  /**
   * Accessor method for the identifier of the client that should act upon the domain transfer request
   * @param value The domain action client id
   * @see #m_trn_data
   * @deprecated Please use setTrnData(org.openrtk.idl.epp0604.domain.epp_DomainTrnData) instead
   */
  public void setActionClientId(String value) { if ( m_trn_data == null ) { m_trn_data = new epp_DomainTrnData(); } m_trn_data.m_action_client_id = value; }
  /**
   * Accessor method for the identifier of the client that should act upon the domain transfer request
   * @return The domain action client id
   * @see #m_trn_data
   * @deprecated Please use getTrnData() instead
   */
  public String getActionClientId() { return m_trn_data == null ? null : m_trn_data.m_action_client_id; }

  /**
   * Accessor method for the date that the domain transfer was requested
   * @param value The domain transfer request date
   * @see #m_trn_data
   * @deprecated Please use setTrnData(org.openrtk.idl.epp0604.domain.epp_DomainTrnData) instead
   */
  public void setRequestDate(String value) { if ( m_trn_data == null ) { m_trn_data = new epp_DomainTrnData(); } m_trn_data.m_request_date = value; }
  /**
   * Accessor method for the date that the domain transfer was requested
   * @return The domain transfer request date
   * @see #m_trn_data
   * @deprecated Please use getTrnData() instead
   */
  public String getRequestDate() { return m_trn_data == null ? null : m_trn_data.m_request_date; }

  /**
   * Accessor method for the required or completed action date to the domain transfer request
   * @param value The action date to the domain transfer request
   * @see #m_trn_data
   * @deprecated Please use setTrnData(org.openrtk.idl.epp0604.domain.epp_DomainTrnData) instead
   */
  public void setActionDate(String value) { if ( m_trn_data == null ) { m_trn_data = new epp_DomainTrnData(); } m_trn_data.m_action_date = value; }
  /**
   * Accessor method for the required or completed action date to the domain transfer request
   * @return The action date to the domain transfer request
   * @see #m_trn_data
   * @deprecated Please use getTrnData() instead
   */
  public String getActionDate() { return m_trn_data == null ? null : m_trn_data.m_action_date; }

  /**
   * Accessor method for the expiration date of the domain object
   * @param value The domain expiration date
   * @see #m_trn_data
   * @deprecated Please use setTrnData(org.openrtk.idl.epp0604.domain.epp_DomainTrnData) instead
   */
  public void setExpirationDate(String value) { if ( m_trn_data == null ) { m_trn_data = new epp_DomainTrnData(); } m_trn_data.m_expiration_date = value; }
  /**
   * Accessor method for the expiration date of the domain object
   * @return The domain expiration date
   * @see #m_trn_data
   * @deprecated Please use getTrnData() instead
   */
  public String getExpirationDate() { return m_trn_data == null ? null : m_trn_data.m_expiration_date; }

  /**
   * Converts this class into a string.
   * Typically used to view the object in debug output.
   * @return The string representation of this object instance
   */
  public String toString() { return this.getClass().getName() + ": { m_rsp ["+m_rsp+"] m_trn_data ["+m_trn_data+"] }"; }

} // class epp_DomainTransferRsp
