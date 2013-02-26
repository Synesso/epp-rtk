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

package org.openrtk.idl.epprtk;


/**
 * Class that contains elements necessary to present status of the service messages
 * queued in the EPP server for client retrieval.</p>
 * $Header: /cvsroot/epp-rtk/epp-rtk/java/src/org/openrtk/idl/epprtk/epp_MessageQueue.java,v 1.1 2004/12/07 15:27:49 ewang2004 Exp $<br>
 * $Revision: 1.1 $<br>
 * $Date: 2004/12/07 15:27:49 $<br>
 * @see org.openrtk.idl.epprtk.epp_Response#setMessageQueue(epp_MessageQueue)
 */
public class epp_MessageQueue implements org.omg.CORBA.portable.IDLEntity
{
  /**
   * The number of the service messages queued in EPP server for client retrieval.
   * @see #setCount(int)
   * @see #getCount()
   */
  public int m_count = (int)0;
  /**
   * The date and time that a particular message was enqueued.
   * Only be present when returning a message in a poll response
   * @see #setQueueDate(String)
   * @see #getQueueDate()
   */
  public String m_queue_date = null;

  public org.openrtk.idl.epprtk.epp_ExtMessage m_msg = null;
  public String m_id = null;

  /**
   * Empty constructor
   */
  public epp_MessageQueue ()
  {
  } // ctor

  /**
   * The constructor with initializing variables.
   * @param _m_count The number of the service messages queued for client retrieval
   * @param _m_queue_date The date and time that a particular message was enqueued
   */
  public epp_MessageQueue (int _m_count, String _m_queue_date, org.openrtk.idl.epprtk.epp_ExtMessage _m_msg, String _m_id)
  {
    m_count = _m_count;
    m_queue_date = _m_queue_date;
    m_msg = _m_msg;
    m_id = _m_id;
  } // ctor

  /**
   * Accessor method for the number of the service messages queued for client retrieval
   * @param value The number of the service messages
   * @see #m_count
   */
  public void setCount(int value) { m_count = value; }
  /**
   * Accessor method for the number of the service messages queued for client retrieval
   * @return The number of the service messages
   * @see #m_count
   */
  public int getCount() { return m_count; }

  /**
   * Accessor method for the date and time that a particular message was enqueued
   * @param value The date and time that a particular message was enqueued
   * @see #m_queue_date
   */
  public void setQueueDate(String value) { m_queue_date = value; }
  /**
   * Accessor method for the date and time that a particular message was enqueued
   * @return The date and time that a particular message was enqueued
   * @see #m_queue_date
   */
  public String getQueueDate() { return m_queue_date; }

  /**
   * @deprecated Please use setExtMsg() instead
   */
  public void setMsg(String value) { m_msg = new org.openrtk.idl.epprtk.epp_ExtMessage(value, null); }
  /**
   * @deprecated Please use getExtMsg() instead
   */
  public String getMsg() { return ( m_msg == null ? null : m_msg.getValue() ); }
  
  /**
   * Accessor to the queue message.
   */
  public void setExtMsg(org.openrtk.idl.epprtk.epp_ExtMessage value) { m_msg = value; }
  /**
   * Accessor to the queue message.
   */
  public org.openrtk.idl.epprtk.epp_ExtMessage getExtMsg() { return m_msg; }

  public void setId(String value) { m_id = value; }
  public String getId() { return m_id; }

  /**
   * Converts this class into a string.
   * Typically used to view the object in debug output.
   * @return The string representation of this object instance
   */
  public String toString() { return this.getClass().getName() + ": { m_count ["+m_count+"] m_queue_date ["+m_queue_date+"] m_msg ["+m_msg+"] m_id ["+m_id+"] }"; }

} // class epp_MessageQueue
