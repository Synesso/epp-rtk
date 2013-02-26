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

package org.openrtk.idl.epp0503.contact;


/**
 * Class that contains the elements used to represent contact name and address.</p>
 * $Header: /cvsroot/epp-rtk/epp-rtk/java/src/org/openrtk/idl/epp0503/contact/epp_ContactNameAddress.java,v 1.1 2003/03/21 16:18:28 tubadanm Exp $<br>
 * $Revision: 1.1 $<br>
 * $Date: 2003/03/21 16:18:28 $<br>
 * @see org.openrtk.idl.epp0503.contact.epp_ContactCreateReq
 * @see org.openrtk.idl.epp0503.contact.epp_ContactInfoRsp
 * @see org.openrtk.idl.epp0503.contact.epp_ContactUpdateChange
 */
public class epp_ContactNameAddress implements org.omg.CORBA.portable.IDLEntity
{
  /**
   * The contact individual name.
   * @see #setName(String)
   * @see #getName()
   */
  public String m_name = null;
  /**
   * The name of the organization with which the contact is affiliated.
   * @see #setOrg(String)
   * @see #getOrg()
   */
  public String m_org = null;
  /**
   * The contact's address information.
   * @see #setAddress(org.openrtk.idl.epp0503.contact.epp_ContactAddress)
   * @see #getAddress()
   */
  public org.openrtk.idl.epp0503.contact.epp_ContactAddress m_address = null;

  /**
   * Empty constructor
   */
  public epp_ContactNameAddress ()
  {
  } // ctor

  /**
   * The constructor with initializing variables.
   * @param _m_name The contact individual name
   * @param _m_org The name of the organization with which the contact is affiliated
   * @param _m_address The contact's address information
   */
  public epp_ContactNameAddress (String _m_name, String _m_org, org.openrtk.idl.epp0503.contact.epp_ContactAddress _m_address)
  {
    m_name = _m_name;
    m_org = _m_org;
    m_address = _m_address;
  } // ctor

  /**
   * Accessor method for the contact individual name
   * @param value The contact name
   * @see #m_name
   */
  public void setName(String value) { m_name = value; }
  /**
   * Accessor method for the contact individual name
   * @return The contact name
   * @see #m_name
   */
  public String getName() { return m_name; }

  /**
   * Accessor method for the name of the organization with which the contact is affiliated
   * @param value The contact organization
   * @see #m_org
   */
  public void setOrg(String value) { m_org = value; }
  /**
   * Accessor method for the name of the organization with which the contact is affiliated
   * @return The contact organization
   * @see #m_org
   */
  public String getOrg() { return m_org; }

  /**
   * Accessor method for the contact's address information
   * @param value The contact's address
   * @see #m_address
   */
  public void setAddress(org.openrtk.idl.epp0503.contact.epp_ContactAddress value) { m_address = value; }
  /**
   * Accessor method for the contact's address information
   * @return The contact's address
   * @see #m_address
   */
  public org.openrtk.idl.epp0503.contact.epp_ContactAddress getAddress() { return m_address; }

  /**
   * Converts this class into a string.
   * Typically used to view the object in debug output.
   * @return The string representation of this object instance
   */
  public String toString() { return this.getClass().getName() + ": { m_name ["+m_name+"] m_org ["+m_org+"] m_address ["+m_address+"] }"; }

} // class epp_ContactNameAddress
