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

package org.openrtk.idl.epp0503;


/**
 * Class that contains the common and generic elements returned by
 * the EPP server in response to a client EPP command.</p>
 * $Header: /cvsroot/epp-rtk/epp-rtk/java/src/org/openrtk/idl/epp0503/epp_Response.java,v 1.1 2003/03/21 16:18:26 tubadanm Exp $<br>
 * $Revision: 1.1 $<br>
 * $Date: 2003/03/21 16:18:26 $<br>
 * @see org.openrtk.idl.epp0503.epp_LoginRsp#setRsp(epp_Response)
 * @see org.openrtk.idl.epp0503.domain.epp_DomainCreateRsp#setRsp(epp_Response)
 */
public class epp_Response implements org.omg.CORBA.portable.IDLEntity
{
  /**
   * The array of the results that document the success or failure of an EPP command execution.
   * @see #setResults(org.openrtk.idl.epp0503.epp_Result[])
   * @see #getResults()
   */
  public org.openrtk.idl.epp0503.epp_Result m_results[] = null;
  /**
   * The element that indicates stauts of the messages queued in the EPP server for client retrieval.
   * @see #setMessageQueue(org.openrtk.idl.epp0503.epp_MessageQueue)
   * @see #getMessageQueue()
   */
  public org.openrtk.idl.epp0503.epp_MessageQueue m_message_queue = null;

  /**
   * The optional response extensions from the registry.
   * @see #setExtensionString(String)
   * @see #getExtensionString()
   */
  public String m_extension_string = null;
  /**
   * The transaction identifiers assigned by the client and the server.
   * @see #setTransId(org.openrtk.idl.epp0503.epp_TransID)
   * @see #getTransId()
   */
  public org.openrtk.idl.epp0503.epp_TransID m_trans_id = null;

  /**
   * Empty constructor
   */
  public epp_Response ()
  {
  } // ctor

  /**
   * The constructor with initializing variables.
   * @param _m_results The array of the results that document the success or failure of an EPP command execution
   * @param _m_message_queue The element that indicates the status of the messages queued in the EPP server
   * @param _m_extension_string The response extensions from the registry
   * @param _m_trans_id The transaction identifiers assigned by the client and the server
   */
  public epp_Response (org.openrtk.idl.epp0503.epp_Result[] _m_results, org.openrtk.idl.epp0503.epp_MessageQueue _m_message_queue, String _m_extension_string, org.openrtk.idl.epp0503.epp_TransID _m_trans_id)
  {
    m_results = _m_results;
    m_message_queue = _m_message_queue;
    m_extension_string = _m_extension_string;
    m_trans_id = _m_trans_id;
  } // ctor

  /**
   * Accessor method for the array of the results that document the success or failure of an EPP command execution
   * @param value The array of the results
   * @see #m_results
   */
  public void setResults(org.openrtk.idl.epp0503.epp_Result[] value) { m_results = value; }
  /**
   * Accessor method for the array of the results that document the success or failure of an EPP command execution
   * @return The array of the results
   * @see #m_results
   */
  public org.openrtk.idl.epp0503.epp_Result[] getResults() { return m_results; }

  /**
   * Accessor method for the element that indicates the status of the messages queued in the EPP server
   * @param value The messages queue element
   * @see #m_message_queue
   */
  public void setMessageQueue(org.openrtk.idl.epp0503.epp_MessageQueue value) { m_message_queue = value; }
  /**
   * Accessor method for the element that indicates the status of the messages queued in the EPP server
   * @return The messages queue element
   * @see #m_message_queue
   */
  public org.openrtk.idl.epp0503.epp_MessageQueue getMessageQueue() { return m_message_queue; }

  /**
   * Accessor method for the response extensions from the registry
   * @param value The response extensions
   * @see #m_extension_string
   */
  public void setExtensionString(String value) { m_extension_string = value; }
  /**
   * Accessor method for the response extensions from the registry
   * @return The response extensions
   * @see #m_extension_string
   */
  public String getExtensionString() { return m_extension_string; }

  /**
   * Accessor method for the response extensions from the registry
   * @param value The response extensions
   * @see #m_extension_string
   * @deprecated Please use getExtensionString() instead.
   */
  public void setUnspecString(String value) { setExtensionString(value); }
  /**
   * Accessor method for the response extensions from the registry
   * @return The response extensions
   * @see #m_extension_string
   * @deprecated Please use getExtensionString() instead.
   */
  public String getUnspecString() { return getExtensionString(); }

  /**
   * Accessor method for the transaction identifiers assigned by the client and the server
   * @param value The transaction identifiers
   * @see #m_trans_id
   */
  public void setTransId(org.openrtk.idl.epp0503.epp_TransID value) { m_trans_id = value; }
  /**
   * Accessor method for the transaction identifiers assigned by the client and the server
   * @return The transaction identifiers
   * @see #m_trans_id
   */
  public org.openrtk.idl.epp0503.epp_TransID getTransId() { return m_trans_id; }

  /**
   * Converts this class into a string.
   * Typically used to view the object in debug output.
   * @return The string representation of this object instance
   */
  public String toString() { return this.getClass().getName() + ": { m_results ["+(m_results != null ? java.util.Arrays.asList(m_results) : null)+"] m_message_queue ["+m_message_queue+"] m_extension_string ["+m_extension_string+"] m_trans_id ["+m_trans_id+"] }"; }

} // class epp_Response
