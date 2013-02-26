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

package org.openrtk.idl.epprtk.contact;


/**
 * Class that contains the elements necessary to delete a contact
 * in the registry.</p>
 * $Header: /cvsroot/epp-rtk/epp-rtk/java/src/org/openrtk/idl/epprtk/contact/epp_ContactDeleteReq.java,v 1.1 2004/12/07 15:27:49 ewang2004 Exp $<br>
 * $Revision: 1.1 $<br>
 * $Date: 2004/12/07 15:27:49 $<br>
 * @see com.tucows.oxrs.epprtk.rtk.xml.EPPContactDelete
 * @see org.openrtk.idl.epprtk.contact.epp_ContactDeleteRsp
 */
public class epp_ContactDeleteReq implements org.omg.CORBA.portable.IDLEntity
{
  /**
   * The common and generic command element.
   * @see #setCmd(org.openrtk.idl.epprtk.epp_Command)
   * @see #getCmd()
   */
  public org.openrtk.idl.epprtk.epp_Command m_cmd = null;
  /**
   * The identifier for the contact object to be deleted in the registry.
   * @see #setId(String)
   * @see #getId()
   */
  public String m_id = null;

  /**
   * Empty constructor
   */
  public epp_ContactDeleteReq ()
  {
  } // ctor

  /**
   * The constructor with initializing variables.
   * @param _m_cmd The common and generic command element
   * @param _m_id The identifier for the contact object to be deleted
   */
  public epp_ContactDeleteReq (org.openrtk.idl.epprtk.epp_Command _m_cmd, String _m_id)
  {
    m_cmd = _m_cmd;
    m_id = _m_id;
  } // ctor

  /**
   * Accessor method for the common and generic command element
   * @param value The command element
   * @see #m_cmd
   */
  public void setCmd(org.openrtk.idl.epprtk.epp_Command value) { m_cmd = value; }
  /**
   * Accessor method for the common and generic command element
   * @return The command element
   * @see #m_cmd
   */
  public org.openrtk.idl.epprtk.epp_Command getCmd() { return m_cmd; }

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
   * Accessor method for the identifier for the contact object to be deleted
   * @param value The contact id
   * @see #m_id
   */
  public void setId(String value) { m_id = value; }
  /**
   * Accessor method for the identifier for the contact object to be deleted
   * @return The contact id
   * @see #m_id
   */
  public String getId() { return m_id; }

  /**
   * Converts this class into a string.
   * Typically used to view the object in debug output.
   * @return The string representation of this object instance
   */
  public String toString() { return this.getClass().getName() + ": { m_cmd ["+m_cmd+"] m_id ["+m_id+"] }"; }

} // class epp_ContactDeleteReq
