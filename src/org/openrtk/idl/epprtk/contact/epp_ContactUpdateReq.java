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
 * Class that contains the elements necessary to modify a contact
 * in the registry.</p>
 * $Header: /cvsroot/epp-rtk/epp-rtk/java/src/org/openrtk/idl/epprtk/contact/epp_ContactUpdateReq.java,v 1.1 2004/12/07 15:27:49 ewang2004 Exp $<br>
 * $Revision: 1.1 $<br>
 * $Date: 2004/12/07 15:27:49 $<br>
 * @see com.tucows.oxrs.epprtk.rtk.xml.EPPContactUpdate
 * @see org.openrtk.idl.epprtk.contact.epp_ContactUpdateRsp
 */
public class epp_ContactUpdateReq implements org.omg.CORBA.portable.IDLEntity
{
  /**
   * The common and generic command element.
   * @see #setCmd(org.openrtk.idl.epprtk.epp_Command)
   * @see #getCmd()
   */
  public org.openrtk.idl.epprtk.epp_Command m_cmd = null;
  /**
   * The identifier for the contact object to be modified in the registry.
   * @see #setId(String)
   * @see #getId()
   */
  public String m_id = null;
  /**
   * The add element that contains attribute values to be added to the contact object.
   * @see #setAdd(org.openrtk.idl.epprtk.contact.epp_ContactUpdateAddRemove)
   * @see #getAdd()
   */
  public org.openrtk.idl.epprtk.contact.epp_ContactUpdateAddRemove m_add = null;
  /**
   * The remove element that contains attribute values to be removed from the contact object.
   * @see #setRemove(org.openrtk.idl.epprtk.contact.epp_ContactUpdateAddRemove)
   * @see #getRemove()
   */
  public org.openrtk.idl.epprtk.contact.epp_ContactUpdateAddRemove m_remove = null;
  /**
   * The change element that contains attribute values to be changed in the contact object.
   * @see #setChange(org.openrtk.idl.epprtk.contact.epp_ContactUpdateChange)
   * @see #getChange()
   */
  public org.openrtk.idl.epprtk.contact.epp_ContactUpdateChange m_change = null;

  /**
   * Empty constructor
   */
  public epp_ContactUpdateReq ()
  {
  } // ctor

  /**
   * The constructor with initializing variables.
   * @param _m_cmd The common and generic command element
   * @param _m_id The identifier for the contact object to be modified in the registry
   * @param _m_add The add element that contains attribute values to be added to the contact object
   * @param _m_remove The remove element that contains attribute values to be removed from the contact object
   * @param _m_change The change element that contains attribute values to be changed in the contact object
   */
  public epp_ContactUpdateReq (org.openrtk.idl.epprtk.epp_Command _m_cmd, String _m_id, org.openrtk.idl.epprtk.contact.epp_ContactUpdateAddRemove _m_add, org.openrtk.idl.epprtk.contact.epp_ContactUpdateAddRemove _m_remove, org.openrtk.idl.epprtk.contact.epp_ContactUpdateChange _m_change)
  {
    m_cmd = _m_cmd;
    m_id = _m_id;
    m_add = _m_add;
    m_remove = _m_remove;
    m_change = _m_change;
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
   * Accessor method for the identifier for the contact object to be modified
   * @param value The contact id
   * @see #m_id
   */
  public void setId(String value) { m_id = value; }
  /**
   * Accessor method for the identifier for the contact object to be modified
   * @return The contact id
   * @see #m_id
   */
  public String getId() { return m_id; }

  /**
   * Accessor method for the add element that contains attribute values to be added to the contact object
   * @param value The add element
   * @see #m_add
   */
  public void setAdd(org.openrtk.idl.epprtk.contact.epp_ContactUpdateAddRemove value) { m_add = value; }
  /**
   * Accessor method for the add element that contains attribute values to be added to the contact object
   * @return The add element
   * @see #m_add
   */
  public org.openrtk.idl.epprtk.contact.epp_ContactUpdateAddRemove getAdd() { return m_add; }

  /**
   * Accessor method for the remove element that contains attribute values to be removed from the contact object
   * @param value The remove element
   * @see #m_remove
   */
  public void setRemove(org.openrtk.idl.epprtk.contact.epp_ContactUpdateAddRemove value) { m_remove = value; }
  /**
   * Accessor method for the remove element that contains attribute values to be removed from the contact object
   * @return The remove element
   * @see #m_remove
   */
  public org.openrtk.idl.epprtk.contact.epp_ContactUpdateAddRemove getRemove() { return m_remove; }

  /**
   * Accessor method for the change element that contains attribute values to be changed in the contact object
   * @param value The change element
   * @see #m_change
   */
  public void setChange(org.openrtk.idl.epprtk.contact.epp_ContactUpdateChange value) { m_change = value; }
  /**
   * Accessor method for the change element that contains attribute values to be changed in the contact object
   * @return The change element
   * @see #m_change
   */
  public org.openrtk.idl.epprtk.contact.epp_ContactUpdateChange getChange() { return m_change; }

  /**
   * Converts this class into a string.
   * Typically used to view the object in debug output.
   * @return The string representation of this object instance
   */
  public String toString() { return this.getClass().getName() + ": { m_cmd ["+m_cmd+"] m_id ["+m_id+"] m_add ["+m_add+"] m_remove ["+m_remove+"] m_change ["+m_change+"] }"; }

} // class epp_ContactUpdateReq
