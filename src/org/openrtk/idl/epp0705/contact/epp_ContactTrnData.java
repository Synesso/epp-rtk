/*
**
** EPP RTK Java
** Copyright (C) 2002, Tucows, Inc.
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

package org.openrtk.idl.epp0705.contact;


/**
 * FIXME!</p>
 * $Header: /cvsroot/epp-rtk/epp-rtk/java/src/org/openrtk/idl/epp0705/contact/epp_ContactTrnData.java,v 1.1 2003/03/20 22:42:27 tubadanm Exp $<br>
 * $Revision: 1.1 $<br>
 * $Date: 2003/03/20 22:42:27 $<br>
 * @see org.openrtk.idl.epp0705.contact.epp_ContactInfoReq
 */

public class epp_ContactTrnData extends org.omg.CORBA.portable.ObjectImpl implements org.omg.CORBA.portable.IDLEntity, org.openrtk.idl.epp0705.epp_PollResData 
{

  /**
   * Constant variable defined by IDLs.</p>
   * Used when this classes is used to hold the poll response
   * data pertaining to contact transfer notifications.  The value
   * of this constant links this class to a set of poll response
   * data.
   */
  public static final String m_type = "contact:trnData";
  /**
   * Access method for the m_type constant.
   * @see #m_type
   */
  public String getType() { return m_type; }

  /**
   * The id of the contact object in the registry.
   * @see #setId(String)
   * @see #getId()
   */
  public String m_id = null;
  /**
   * The transfer status associated with the contact object.
   * @see #setTransferStatus(org.openrtk.idl.epp0705.epp_TransferStatusType)
   * @see #getTransferStatus()
   */
  public org.openrtk.idl.epp0705.epp_TransferStatusType m_transfer_status = null;
  /**
   * The identifier of the client that requested the contact object transfer.
   * @see #setRequestClientId(String)
   * @see #getRequestClientId()
   */
  public String m_request_client_id = null;
  /**
   * The identifier of the client that should act upon the contact transfer request.
   * @see #setActionClientId(String)
   * @see #getActionClientId()
   */
  public String m_action_client_id = null;
  /**
   * The date that the contact transfer was requested.
   * @see #setRequestDate(String)
   * @see #getRequestDate()
   */
  public String m_request_date = null;
  /**
   * The required or completed action date to the contact transfer request.
   * @see #setActionDate(String)
   * @see #getActionDate()
   */
  public String m_action_date = null;

  /**
   * Empty constructor
   */
  public epp_ContactTrnData ()
  {
  } // ctor

  /**
   * The constructor with initializing variables.
   * @param _m_id The id of the contact object in the registry
   * @param _m_transfer_status The transfer status associated with the contact object
   * @param _m_request_client_id The identifier of the client that requested the contact object transfer
   * @param _m_action_client_id The identifier of the client that should act upon the contact transfer request
   * @param _m_request_date The date that the contact transfer was requested
   * @param _m_action_date The required or completed action date to the contact transfer request
   */
  public epp_ContactTrnData (String _m_id, org.openrtk.idl.epp0705.epp_TransferStatusType _m_transfer_status, String _m_request_client_id, String _m_action_client_id, String _m_request_date, String _m_action_date)
  {
    m_id = _m_id;
    m_transfer_status = _m_transfer_status;
    m_request_client_id = _m_request_client_id;
    m_action_client_id = _m_action_client_id;
    m_request_date = _m_request_date;
    m_action_date = _m_action_date;
  } // ctor

  /**
   * Accessor method for the id of the contact object in the registry
   * @param value The contact id
   * @see #m_id
   */
  public void setId(String value) { m_id = value; }
  /**
   * Accessor method for the id of the contact object in the registry
   * @return The contact id
   * @see #m_id
   */
  public String getId() { return m_id; }

  /**
   * Accessor method for the transfer status associated with the contact object
   * @param value The contact transfer status
   * @see #m_transfer_status
   */
  public void setTransferStatus(org.openrtk.idl.epp0705.epp_TransferStatusType value) { m_transfer_status = value; }
  /**
   * Accessor method for the transfer status associated with the contact object
   * @return The contact transfer status
   * @see #m_transfer_status
   */
  public org.openrtk.idl.epp0705.epp_TransferStatusType getTransferStatus() { return m_transfer_status; }

  /**
   * Accessor method for the identifier of the client that requested the contact object transfer
   * @param value The contact request client id
   * @see #m_request_client_id
   */
  public void setRequestClientId(String value) { m_request_client_id = value; }
  /**
   * Accessor method for the identifier of the client that requested the contact object transfer
   * @return The contact request client id
   * @see #m_request_client_id
   */
  public String getRequestClientId() { return m_request_client_id; }

  /**
   * Accessor method for the identifier of the client that should act upon the contact transfer request
   * @param value The contact action client id
   * @see #m_action_client_id
   */
  public void setActionClientId(String value) { m_action_client_id = value; }
  /**
   * Accessor method for the identifier of the client that should act upon the contact transfer request
   * @return The contact action client id
   * @see #m_action_client_id
   */
  public String getActionClientId() { return m_action_client_id; }

  /**
   * Accessor method for the date that the contact transfer was requested
   * @param value The contact transfer request date
   * @see #m_request_date
   */
  public void setRequestDate(String value) { m_request_date = value; }
  /**
   * Accessor method for the date that the contact transfer was requested
   * @return The contact transfer request date
   * @see #m_request_date
   */
  public String getRequestDate() { return m_request_date; }

  /**
   * Accessor method for the required or completed action date to the contact transfer request
   * @param value The action date to the contact transfer request
   * @see #m_action_date
   */
  public void setActionDate(String value) { m_action_date = value; }
  /**
   * Accessor method for the required or completed action date to the contact transfer request
   * @return The action date to the contact transfer request
   * @see #m_action_date
   */
  public String getActionDate() { return m_action_date; }

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
  public String toString() { return this.getClass().getName() + ": { m_id ["+m_id+"] m_transfer_status ["+m_transfer_status+"] m_request_client_id ["+m_request_client_id+"] m_action_client_id ["+m_action_client_id+"] m_request_date ["+m_request_date+"] m_action_date ["+m_action_date+"] }"; }

} // interface epp_ContactTrnData
