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

package org.openrtk.idl.epp0402.contact;


/**
 * Class that contains the contact object elements to be changed in the registry.</p>
 * This class can only be used when modifying a contact object.</p>
 * $Header: /cvsroot/epp-rtk/epp-rtk/java/src/org/openrtk/idl/epp0402/contact/epp_ContactUpdateChange.java,v 1.1 2003/03/21 16:35:40 tubadanm Exp $<br>
 * $Revision: 1.1 $<br>
 * $Date: 2003/03/21 16:35:40 $<br>
 * @see org.openrtk.idl.epp0402.contact.epp_ContactUpdateReq#setChange(epp_ContactUpdateChange)
 */
public class epp_ContactUpdateChange implements org.omg.CORBA.portable.IDLEntity
{
  /**
   * The contact name and address to be associated with the contact object.
   * Content of this element must be represented in a subset of UTF-8.
   * @see #setAsciiAddress(org.openrtk.idl.epp0402.contact.epp_ContactNameAddress)
   * @see #getAsciiAddress()
   */
  public org.openrtk.idl.epp0402.contact.epp_ContactNameAddress m_ascii_address = null;
  /**
   * The contact name and address to be associated with the contact object.
   * Content of this element may be represented in unrestricted UTF-8.
   * @see #setI15dAddress(org.openrtk.idl.epp0402.contact.epp_ContactNameAddress)
   * @see #getI15dAddress()
   */
  public org.openrtk.idl.epp0402.contact.epp_ContactNameAddress m_i15d_address = null;
  /**
   * The contact's telephone number.
   * @see #setVoice(org.openrtk.idl.epp0402.contact.epp_ContactPhone)
   * @see #getVoice()
   */
  public org.openrtk.idl.epp0402.contact.epp_ContactPhone m_voice = null;
  /**
   * The contact's fax number.
   * @see #setFax(org.openrtk.idl.epp0402.contact.epp_ContactPhone)
   * @see #getFax()
   */
  public org.openrtk.idl.epp0402.contact.epp_ContactPhone m_fax = null;
  /**
   * The contact's e-mail address.
   * @see #setEmail(String)
   * @see #getEmail()
   */
  public String m_email = null;
  /**
   * The authorization information to be associated with the contact object.
   * @see #setAuthInfo(org.openrtk.idl.epp0402.epp_AuthInfo)
   * @see #getAuthInfo()
   */
  public org.openrtk.idl.epp0402.epp_AuthInfo m_auth_info = null;

  /**
   * Empty constructor
   */
  public epp_ContactUpdateChange ()
  {
  } // ctor

  /**
   * The constructor with initializing variables.
   * @param _m_ascii_address The ascii contact name and address to be associated with the contact object
   * @param _m_i15d_address The i15d contact name and address to be associated with the contact object
   * @param _m_voice The contact's telephone number
   * @param _m_fax The contact's fax number
   * @param _m_email The contact's e-mail address
   * @param _m_auth_info The authorization information to be associated with the contact object
   */
  public epp_ContactUpdateChange (org.openrtk.idl.epp0402.contact.epp_ContactNameAddress _m_ascii_address, org.openrtk.idl.epp0402.contact.epp_ContactNameAddress _m_i15d_address, org.openrtk.idl.epp0402.contact.epp_ContactPhone _m_voice, org.openrtk.idl.epp0402.contact.epp_ContactPhone _m_fax, String _m_email, org.openrtk.idl.epp0402.epp_AuthInfo _m_auth_info)
  {
    m_ascii_address = _m_ascii_address;
    m_i15d_address = _m_i15d_address;
    m_voice = _m_voice;
    m_fax = _m_fax;
    m_email = _m_email;
    m_auth_info = _m_auth_info;
  } // ctor

  /**
   * Accessor method for the ascii contact name and address to be associated with the contact object
   * @param value The contact ascii name and address
   * @see #m_ascii_address
   */
  public void setAsciiAddress(org.openrtk.idl.epp0402.contact.epp_ContactNameAddress value) { m_ascii_address = value; }
  /**
   * Accessor method for the ascii contact name and address to be associated with the contact object
   * @return The contact ascii name and address
   * @see #m_ascii_address
   */
  public org.openrtk.idl.epp0402.contact.epp_ContactNameAddress getAsciiAddress() { return m_ascii_address; }

  /**
   * Accessor method for the i15d contact name and address to be associated with the contact object
   * @param value The contact i15d name and address
   * @see #m_i15d_address
   */
  public void setI15dAddress(org.openrtk.idl.epp0402.contact.epp_ContactNameAddress value) { m_i15d_address = value; }
  /**
   * Accessor method for the i15d contact name and address to be associated with the contact object
   * @return The contact i15d name and address
   * @see #m_i15d_address
   */
  public org.openrtk.idl.epp0402.contact.epp_ContactNameAddress getI15dAddress() { return m_i15d_address; }

  /**
   * Accessor method for the contact's telephone number
   * @param value The contact's telephone number
   * @see #m_voice
   */
  public void setVoice(org.openrtk.idl.epp0402.contact.epp_ContactPhone value) { m_voice = value; }
  /**
   * Accessor method for the contact's telephone number
   * @return The contact's telephone number
   * @see #m_voice
   */
  public org.openrtk.idl.epp0402.contact.epp_ContactPhone getVoice() { return m_voice; }

  /**
   * Accessor method for the contact's fax number
   * @param value The contact's fax number
   * @see #m_fax
   */
  public void setFax(org.openrtk.idl.epp0402.contact.epp_ContactPhone value) { m_fax = value; }
  /**
   * Accessor method for the contact's fax number
   * @param value The contact's fax number
   * @see #m_fax
   */
  // included String version for backward compatibility
  public void setFax(String value) { m_fax = new org.openrtk.idl.epp0402.contact.epp_ContactPhone("",value); }
  /**
   * Accessor method for the contact's fax number
   * @return The contact's fax number
   * @see #m_fax
   */
  public org.openrtk.idl.epp0402.contact.epp_ContactPhone getFax() { return m_fax; }

  /**
   * Accessor method for the contact's e-mail address
   * @param value The contact's e-mail address
   * @see #m_email
   */
  public void setEmail(String value) { m_email = value; }
  /**
   * Accessor method for the contact's e-mail address
   * @return The contact's e-mail address
   * @see #m_email
   */
  public String getEmail() { return m_email; }

  /**
   * Accessor method for the authorization information to be associated with the contact object
   * @param value The contact auth info
   * @see #m_auth_info
   */
  public void setAuthInfo(org.openrtk.idl.epp0402.epp_AuthInfo value) { m_auth_info = value; }
  /**
   * Accessor method for the authorization information to be associated with the contact object
   * @return The contact auth info
   * @see #m_auth_info
   */
  public org.openrtk.idl.epp0402.epp_AuthInfo getAuthInfo() { return m_auth_info; }

  /**
   * Converts this class into a string.
   * Typically used to view the object in debug output.
   * @return The string representation of this object instance
   */
  public String toString() { return this.getClass().getName() + ": { m_ascii_address ["+m_ascii_address+"] m_i15d_address ["+m_i15d_address+"] m_voice ["+m_voice+"] m_fax ["+m_fax+"] m_email ["+m_email+"] m_auth_info ["+m_auth_info+"] }"; }

} // class epp_ContactUpdateChange
