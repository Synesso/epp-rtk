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
 * Class that contains the elements used to discover and retrieve client
 * service messages from the EPP server</p>
 * $Header: /cvsroot/epp-rtk/epp-rtk/java/src/org/openrtk/idl/epp0402/epp_PollReq.java,v 1.2 2003/09/10 21:29:55 tubadanm Exp $<br>
 * $Revision: 1.2 $<br>
 * $Date: 2003/09/10 21:29:55 $<br>
 * @see com.tucows.oxrs.epp0402.rtk.xml.EPPPoll
 * @see org.openrtk.idl.epp0402.epp_PollRsp
 */
public class epp_PollReq implements org.omg.CORBA.portable.IDLEntity
{
  /**
   * The common and generic command element.
   * @see #setCmd(org.openrtk.idl.epp0402.epp_Command)
   * @see #getCmd()
   */
  public org.openrtk.idl.epp0402.epp_Command m_cmd = null;
  /**
   * The poll operation type.
   * @see #setOp(org.openrtk.idl.epp0402.epp_PollOpType)
   * @see #getOp()
   */
  public org.openrtk.idl.epp0402.epp_PollOpType m_op = null;
  /**
   * The response message id from the EPP server.
   * @see #setMsgID(String)
   * @see #getMsgID()
   */
  public String m_msgID = null;

  /**
   * Empty constructor
   */
  public epp_PollReq ()
  {
  } // ctor

  /**
   * The constructor with initializing variables.
   * @param _m_cmd The common and generic command element
   * @param _m_op The poll operation type
   * @param _m_msgID The response message id from the EPP server
   */
  public epp_PollReq (org.openrtk.idl.epp0402.epp_Command _m_cmd, org.openrtk.idl.epp0402.epp_PollOpType _m_op, String _m_msgID)
  {
    m_cmd = _m_cmd;
    m_op = _m_op;
    m_msgID = _m_msgID;
  } // ctor

  /**
   * Accessor method for the common and generic command element
   * @param value The command element
   * @see #m_cmd
   */
  public void setCmd(org.openrtk.idl.epp0402.epp_Command value) { m_cmd = value; }
  /**
   * Accessor method for the common and generic command element
   * @return The command element
   * @see #m_cmd
   */
  public org.openrtk.idl.epp0402.epp_Command getCmd() { return m_cmd; }

  /**
   * Accessor method for the poll operation type
   * @param value The poll operation type
   * @see #m_op
   */
  public void setOp(org.openrtk.idl.epp0402.epp_PollOpType value) { m_op = value; }
  /**
   * Accessor method for the poll operation type
   * @return The poll operation type
   * @see #m_op
   */
  public org.openrtk.idl.epp0402.epp_PollOpType getOp() { return m_op; }

  /**
   * Accessor method for the response message id from the EPP server
   * @param value The response message id
   * @see #m_msgID
   */
  public void setMsgID(String value) { m_msgID = value; }
  /**
   * Accessor method for the response message id from the EPP server
   * @return The response message id
   * @see #m_msgID
   */
  public String getMsgID() { return m_msgID; }

  /**
   * Converts this class into a string.
   * Typically used to view the object in debug output.
   * @return The string representation of this object instance
   */
  public String toString() { return this.getClass().getName() + ": { m_cmd ["+m_cmd+"] m_op ["+m_op+"] m_msgID ["+m_msgID+"] }"; }

} // class epp_PollReq
