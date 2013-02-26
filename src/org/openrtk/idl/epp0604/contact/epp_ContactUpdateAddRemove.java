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

package org.openrtk.idl.epp0604.contact;


/**
 * Class that contains the elements to be added into or removed from the contact
 * object in the registry.</p>
 * This class can only be used when modifying a contact object.</p>
 * $Header: /cvsroot/epp-rtk/epp-rtk/java/src/org/openrtk/idl/epp0604/contact/epp_ContactUpdateAddRemove.java,v 1.1 2003/03/21 15:54:59 tubadanm Exp $<br>
 * $Revision: 1.1 $<br>
 * $Date: 2003/03/21 15:54:59 $<br>
 * @see org.openrtk.idl.epp0604.contact.epp_ContactUpdateReq#setAdd(epp_ContactUpdateAddRemove)
 * @see org.openrtk.idl.epp0604.contact.epp_ContactUpdateReq#setRemove(epp_ContactUpdateAddRemove)
 */
public class epp_ContactUpdateAddRemove implements org.omg.CORBA.portable.IDLEntity
{
  /**
   * The array of status to be associated with or removed from the contact object.
   * @see #setStatus(org.openrtk.idl.epp0604.contact.epp_ContactStatus[])
   * @see #getStatus()
   */
  public org.openrtk.idl.epp0604.contact.epp_ContactStatus m_status[] = null;

  /**
   * Empty constructor
   */
  public epp_ContactUpdateAddRemove ()
  {
  } // ctor

  /**
   * The constructor with initializing variables.
   * @param _m_status The array of status to be associated with or removed from the contact object
   */
  public epp_ContactUpdateAddRemove (org.openrtk.idl.epp0604.contact.epp_ContactStatus[] _m_status)
  {
    m_status = _m_status;
  } // ctor

  /**
   * Accessor method for the array of status to be associated with or removed from the contact object
   * @param value The array of contact status values
   * @see #m_status
   */
  public void setStatus(org.openrtk.idl.epp0604.contact.epp_ContactStatus[] value) { m_status = value; }
  /**
   * Accessor method for the array of status to be associated with or removed from the contact object
   * @return The array of contact status values
   * @see #m_status
   */
  public org.openrtk.idl.epp0604.contact.epp_ContactStatus[] getStatus() { return m_status; }

  /**
   * Converts this class into a string.
   * Typically used to view the object in debug output.
   * @return The string representation of this object instance
   */
  public String toString() { return this.getClass().getName() + ": { m_status ["+(m_status != null ? java.util.Arrays.asList(m_status) : null)+"] }"; }

} // class epp_ContactUpdateAddRemove
