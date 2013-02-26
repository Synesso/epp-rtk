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

package org.openrtk.idl.epp0503;


/**
 * Class that contains the elements necessary to send the status command
 * to an EPP server.</p>
 * $Header: /cvsroot/epp-rtk/epp-rtk/java/src/org/openrtk/idl/epp0503/epp_StatusReq.java,v 1.2 2003/09/10 21:29:56 tubadanm Exp $<br>
 * $Revision: 1.2 $<br>
 * $Date: 2003/09/10 21:29:56 $<br>
 * @see com.tucows.oxrs.epp0503.rtk.xml.EPPStatus
 * @see org.openrtk.idl.epp0503.epp_StatusRsp
 */
public class epp_StatusReq implements org.omg.CORBA.portable.IDLEntity
{
  /**
   * The common and generic command element.
   * @see #setCmd(org.openrtk.idl.epp0503.epp_Command)
   * @see #getCmd()
   */
  public org.openrtk.idl.epp0503.epp_Command m_cmd = null;
  /**
   * The client transaction id upon the status command will be applied
   * This is not the same as the client trid use in the generic command
   * arguments.
   * @see #setClientTrid(String)
   * @see #getClientTrid()
   */
  public String m_client_trid = null;
  /**
   * The type of command upon the status command will be applied
   * @see #setCommand(org.openrtk.idl.epp0503.epp_StatusCommandType)
   * @see #getCommand()
   */
  public org.openrtk.idl.epp0503.epp_StatusCommandType m_command = null;

  /**
   * Empty constructor
   */
  public epp_StatusReq ()
  {
  } // ctor

  /**
   * The constructor with initializing variables.
   * @param _m_cmd The common and generic command element
   * @param _m_client_trid The client transaction id for the status command
   * @param _m_command The status command type
   */
  public epp_StatusReq (org.openrtk.idl.epp0503.epp_Command _m_cmd, String _m_client_trid, org.openrtk.idl.epp0503.epp_StatusCommandType _m_command)
  {
    m_cmd = _m_cmd;
    m_client_trid = _m_client_trid;
    m_command = _m_command;
  } // ctor

  /**
   * Accessor method for the common and generic command element
   * @param value The command element
   * @see #m_cmd
   */
  public void setCmd(org.openrtk.idl.epp0503.epp_Command value) { m_cmd = value; }
  /**
   * Accessor method for the common and generic command element
   * @return The command element
   * @see #m_cmd
   */
  public org.openrtk.idl.epp0503.epp_Command getCmd() { return m_cmd; }

  /**
   * Accessor method for the client transaction id
   * @param value The client trid
   * @see #m_client_trid
   */
  public void setClientTrid(String value) { m_client_trid = value; }
  /**
   * Accessor method for the client transaction id
   * @return The client trid
   * @see #m_client_trid
   */
  public String getClientTrid() { return m_client_trid; }

  /**
   * Accessor method for the command type
   * @param value The command type
   * @see #m_command
   */
  public void setCommand(org.openrtk.idl.epp0503.epp_StatusCommandType value) { m_command = value; }
  /**
   * Accessor method for the command type
   * @return The command type
   * @see #m_command
   */
  public org.openrtk.idl.epp0503.epp_StatusCommandType getCommand() { return m_command; }

  /**
   * Converts this class into a string.
   * Typically used to view the object in debug output.
   * @return The string representation of this object instance
   */
  public String toString() { return this.getClass().getName() + ": { m_cmd ["+m_cmd+"] m_client_trid ["+m_client_trid+"] m_command ["+m_command+"] }"; }

} // class epp_StatusReq
