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

package org.openrtk.idl.epp0402.domain;


/**
 * FIXME!</p>
 * $Header: /cvsroot/epp-rtk/epp-rtk/java/src/org/openrtk/idl/epp0402/domain/epp_DomainTrnData.java,v 1.1 2003/03/21 16:35:42 tubadanm Exp $<br>
 * $Revision: 1.1 $<br>
 * $Date: 2003/03/21 16:35:42 $<br>
 * @see org.openrtk.idl.epp0402.domain.epp_DomainInfoReq
 */

public class epp_DomainTrnData extends org.omg.CORBA.portable.ObjectImpl implements org.omg.CORBA.portable.IDLEntity, org.openrtk.idl.epp0402.epp_PollResData 
{

  /**
   * Constant variable defined by IDLs.</p>
   * Used when this classes is used to hold the poll response
   * data pertaining to contact transfer notifications.  The value
   * of this constant links this class to a set of poll response
   * data.
   */
  public static final String m_type = "domain:trnData";
  /**
   * Access method for the m_type constant.
   * @see #m_type
   */
  public String getType() { return m_type; }

  /**
   * The name of the domain object in the registry.
   * @see #setName(String)
   * @see #getName()
   */
  public String m_name = null;
  /**
   * The transfer status associated with the domain object.
   * @see #setTransferStatus(org.openrtk.idl.epp0402.epp_TransferStatusType)
   * @see #getTransferStatus()
   */
  public org.openrtk.idl.epp0402.epp_TransferStatusType m_transfer_status = null;
  /**
   * The identifier of the client that requested the domain object transfer.
   * @see #setRequestClientId(String)
   * @see #getRequestClientId()
   */
  public String m_request_client_id = null;
  /**
   * The identifier of the client that should act upon the domain transfer request.
   * @see #setActionClientId(String)
   * @see #getActionClientId()
   */
  public String m_action_client_id = null;
  /**
   * The date that the domain transfer was requested.
   * @see #setRequestDate(String)
   * @see #getRequestDate()
   */
  public String m_request_date = null;
  /**
   * The required or completed action date to the domain transfer request.
   * @see #setActionDate(String)
   * @see #getActionDate()
   */
  public String m_action_date = null;
  /**
   * The expiration date of the domain object.
   * @see #setExpirationDate(String)
   * @see #getExpirationDate()
   */
  public String m_expiration_date = null;

  /**
   * Empty constructor
   */
  public epp_DomainTrnData ()
  {
  } // ctor

  /**
   * The constructor with initializing variables.
   * @param _m_name The name of the domain object in the registry
   * @param _m_transfer_status The transfer status associated with the domain object
   * @param _m_request_client_id The identifier of the client that requested the domain object transfer
   * @param _m_action_client_id The identifier of the client that should act upon the domain transfer request
   * @param _m_request_date The date that the domain transfer was requested
   * @param _m_action_date The required or completed action date to the domain transfer request
   * @param _m_expiration_date The expiration date of the domain object
   */
  public epp_DomainTrnData (String _m_name, org.openrtk.idl.epp0402.epp_TransferStatusType _m_transfer_status, String _m_request_client_id, String _m_action_client_id, String _m_request_date, String _m_action_date, String _m_expiration_date)
  {
    m_name = _m_name;
    m_transfer_status = _m_transfer_status;
    m_request_client_id = _m_request_client_id;
    m_action_client_id = _m_action_client_id;
    m_request_date = _m_request_date;
    m_action_date = _m_action_date;
    m_expiration_date = _m_expiration_date;
  } // ctor

  /**
   * Accessor method for the name of the domain object in the registry
   * @param value The domain name
   * @see #m_name
   */
  public void setName(String value) { m_name = value; }
  /**
   * Accessor method for the name of the domain object in the registry
   * @return The domain name
   * @see #m_name
   */
  public String getName() { return m_name; }

  /**
   * Accessor method for the transfer status associated with the domain object
   * @param value The domain transfer status
   * @see #m_transfer_status
   */
  public void setTransferStatus(org.openrtk.idl.epp0402.epp_TransferStatusType value) { m_transfer_status = value; }
  /**
   * Accessor method for the transfer status associated with the domain object
   * @return The domain transfer status
   * @see #m_transfer_status
   */
  public org.openrtk.idl.epp0402.epp_TransferStatusType getTransferStatus() { return m_transfer_status; }

  /**
   * Accessor method for the identifier of the client that requested the domain object transfer
   * @param value The domain request client id
   * @see #m_request_client_id
   */
  public void setRequestClientId(String value) { m_request_client_id = value; }
  /**
   * Accessor method for the identifier of the client that requested the domain object transfer
   * @return The domain request client id
   * @see #m_request_client_id
   */
  public String getRequestClientId() { return m_request_client_id; }

  /**
   * Accessor method for the identifier of the client that should act upon the domain transfer request
   * @param value The domain action client id
   * @see #m_action_client_id
   */
  public void setActionClientId(String value) { m_action_client_id = value; }
  /**
   * Accessor method for the identifier of the client that should act upon the domain transfer request
   * @return The domain action client id
   * @see #m_action_client_id
   */
  public String getActionClientId() { return m_action_client_id; }

  /**
   * Accessor method for the date that the domain transfer was requested
   * @param value The domain transfer request date
   * @see #m_request_date
   */
  public void setRequestDate(String value) { m_request_date = value; }
  /**
   * Accessor method for the date that the domain transfer was requested
   * @return The domain transfer request date
   * @see #m_request_date
   */
  public String getRequestDate() { return m_request_date; }

  /**
   * Accessor method for the required or completed action date to the domain transfer request
   * @param value The action date to the domain transfer request
   * @see #m_action_date
   */
  public void setActionDate(String value) { m_action_date = value; }
  /**
   * Accessor method for the required or completed action date to the domain transfer request
   * @return The action date to the domain transfer request
   * @see #m_action_date
   */
  public String getActionDate() { return m_action_date; }

  /**
   * Accessor method for the expiration date of the domain object
   * @param value The domain expiration date
   * @see #m_expiration_date
   */
  public void setExpirationDate(String value) { m_expiration_date = value; }
  /**
   * Accessor method for the expiration date of the domain object
   * @return The domain expiration date
   * @see #m_expiration_date
   */
  public String getExpirationDate() { return m_expiration_date; }

  /**
   * Method required by ObjectImpl and the CORBA Object interface.
   * Always returns null.  It's only here to satisfy the CORBA requirements
   * of the IDL usage.
   */
  public String[] _ids() { return null; }

  /**
   * Converts this class into a string.
   * Typically used to view the object in debug output.
   * @return The string representation of this object instance
   */
  public String toString() { return this.getClass().getName() + ": { m_name ["+m_name+"] m_transfer_status ["+m_transfer_status+"] m_request_client_id ["+m_request_client_id+"] m_action_client_id ["+m_action_client_id+"] m_request_date ["+m_request_date+"] m_action_date ["+m_action_date+"] m_expiration_date ["+m_expiration_date+"] }"; }

} // interface epp_DomainTrnData
