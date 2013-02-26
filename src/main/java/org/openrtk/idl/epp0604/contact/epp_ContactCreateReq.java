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
 * Class that contains the elements necessary to create a contact
 * in the registry.</p>
 * $Header: /cvsroot/epp-rtk/epp-rtk/java/src/org/openrtk/idl/epp0604/contact/epp_ContactCreateReq.java,v 1.2 2003/09/10 21:29:57 tubadanm Exp $<br>
 * $Revision: 1.2 $<br>
 * $Date: 2003/09/10 21:29:57 $<br>
 * @see com.tucows.oxrs.epp0604.rtk.xml.EPPContactCreate
 * @see org.openrtk.idl.epp0604.contact.epp_ContactCreateRsp
 */
public class epp_ContactCreateReq implements org.omg.CORBA.portable.IDLEntity
{
  /**
   * The common and generic command element.
   * @see #setCmd(org.openrtk.idl.epp0604.epp_Command)
   * @see #getCmd()
   */
  public org.openrtk.idl.epp0604.epp_Command m_cmd = null;
  /**
   * The identifier for the contact to be created.
   * @see #setId(String)
   * @see #getId()
   */
  public String m_id = null;
  /**
   * The contact name and address to be associated with the contact object.
   * Content of this element must be represented in a subset of UTF-8.
   * <i><b>Note:</b> Usage of this data member is deprecated in favour of the m_addresses
   * member (or better yet, its accessor methods).  If this data member is not null, then
   * m_addresses will be ignored.</i>
   * @see #m_addresses
   * @deprecated
   */
  public org.openrtk.idl.epp0604.contact.epp_ContactNameAddress m_ascii_address = null;
  /**
   * The contact name and address to be associated with the contact object.
   * Content of this element may be represented in unrestricted UTF-8.<p>
   * <i><b>Note:</b> Usage of this data member is deprecated in favour of the m_addresses
   * member (or better yet, its accessor methods).  The RTK code will only reference
   * this data member if m_ascii_address is not null.</i>
   * @see #m_addresses
   * @deprecated
   */
  public org.openrtk.idl.epp0604.contact.epp_ContactNameAddress m_i15d_address = null;
  /**
   * The contact names and addresses to be associated with the contact object.
   * EPP permits up to two addresses to be specified.  The first must always
   * be internationalized and be represented in a subset of UTF-8 limited to
   * 7-bit US-ASCII.  The optional second address may be represented in 
   * unrestricted UTF-8.
   * @see #setAddresses(org.openrtk.idl.epp0604.contact.epp_ContactNameAddress[])
   * @see #getAddresses()
   */
  public org.openrtk.idl.epp0604.contact.epp_ContactNameAddress m_addresses[] = null;
  /**
   * The telephone number of the contact.
   * @see #setVoice(org.openrtk.idl.epp0604.contact.epp_ContactPhone)
   * @see #getVoice()
   */
  public org.openrtk.idl.epp0604.contact.epp_ContactPhone m_voice = null;
  /**
   * The fax number of the contact.
   * @see #setFax(org.openrtk.idl.epp0604.contact.epp_ContactPhone)
   * @see #getFax()
   */
  public org.openrtk.idl.epp0604.contact.epp_ContactPhone m_fax = null;
  /**
   * The e-mail address of the contact.
   * @see #setEmail(String)
   * @see #getEmail()
   */
  public String m_email = null;
  /**
   * The authorization information to be associated with the contact object.
   * @see #setAuthInfo(org.openrtk.idl.epp0604.epp_AuthInfo)
   * @see #getAuthInfo()
   */
  public org.openrtk.idl.epp0604.epp_AuthInfo m_auth_info = null;

  /**
   * Empty constructor
   */
  public epp_ContactCreateReq ()
  {
  } // ctor

  /**
   * The constructor with initializing variables.
   * @param _m_cmd The common and generic command element
   * @param _m_id The identifier for the contact to be created
   * @param _m_ascii_address The ascii contact name and address to be associated with the contact object
   * @param _m_i15d_address The i15d contact name and address to be associated with the contact object
   * @param _m_voice The telephone number of the contact
   * @param _m_fax The fax number of the contact
   * @param _m_email The e-mail address of the contact
   * @param _m_auth_info The authorization information to be associated with the contact object
   * @deprecated
   */
  public epp_ContactCreateReq (org.openrtk.idl.epp0604.epp_Command _m_cmd, String _m_id, org.openrtk.idl.epp0604.contact.epp_ContactNameAddress _m_ascii_address, org.openrtk.idl.epp0604.contact.epp_ContactNameAddress _m_i15d_address, org.openrtk.idl.epp0604.contact.epp_ContactPhone _m_voice, org.openrtk.idl.epp0604.contact.epp_ContactPhone _m_fax, String _m_email, org.openrtk.idl.epp0604.epp_AuthInfo _m_auth_info)
  {
    m_cmd = _m_cmd;
    m_id = _m_id;
    m_ascii_address = _m_ascii_address;
    m_i15d_address = _m_i15d_address;
    m_voice = _m_voice;
    m_fax = _m_fax;
    m_email = _m_email;
    m_auth_info = _m_auth_info;
  } // ctor

  /**
   * The constructor with initializing variables.
   * @param _m_cmd The common and generic command element
   * @param _m_id The identifier for the contact to be created
   * @param _m_addresses The contact names and addresses to be associated with the contact object
   * @param _m_voice The telephone number of the contact
   * @param _m_fax The fax number of the contact
   * @param _m_email The e-mail address of the contact
   * @param _m_auth_info The authorization information to be associated with the contact object
   * @see #m_addresses
   */
  public epp_ContactCreateReq (org.openrtk.idl.epp0604.epp_Command _m_cmd, String _m_id, org.openrtk.idl.epp0604.contact.epp_ContactNameAddress[] _m_addresses, org.openrtk.idl.epp0604.contact.epp_ContactPhone _m_voice, org.openrtk.idl.epp0604.contact.epp_ContactPhone _m_fax, String _m_email, org.openrtk.idl.epp0604.epp_AuthInfo _m_auth_info)
  {
    m_cmd = _m_cmd;
    m_id = _m_id;
    m_addresses = _m_addresses;
    m_voice = _m_voice;
    m_fax = _m_fax;
    m_email = _m_email;
    m_auth_info = _m_auth_info;
  } // ctor

  /**
   * Accessor method for the common and generic command element
   * @param value The command element
   * @see #m_cmd
   */
  public void setCmd(org.openrtk.idl.epp0604.epp_Command value) { m_cmd = value; }
  /**
   * Accessor method for the common and generic command element
   * @return The command element
   * @see #m_cmd
   */
  public org.openrtk.idl.epp0604.epp_Command getCmd() { return m_cmd; }

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
   * Accessor method for the identifier for the contact to be created
   * @param value The contact id
   * @see #m_id
   */
  public void setId(String value) { m_id = value; }
  /**
   * Accessor method for the identifier for the contact to be created
   * @return The contact id
   * @see #m_id
   */
  public String getId() { return m_id; }

  /**
   * Accessor method for the ascii contact name and address to be associated with the contact object
   * @param value The contact ascii name and address
   * @see #m_ascii_address
   * @deprecated
   */
  public void setAsciiAddress(org.openrtk.idl.epp0604.contact.epp_ContactNameAddress value) { m_ascii_address = value; }
  /**
   * Accessor method for the ascii contact name and address to be associated with the contact object
   * @return The contact ascii name and address
   * @see #m_ascii_address
   * @deprecated
   */
  public org.openrtk.idl.epp0604.contact.epp_ContactNameAddress getAsciiAddress() { return m_ascii_address; }

  /**
   * Accessor method for the i15d contact name and address to be associated with the contact object
   * @param value The contact i15d name and address
   * @see #m_i15d_address
   * @deprecated
   */
  public void setI15dAddress(org.openrtk.idl.epp0604.contact.epp_ContactNameAddress value) { m_i15d_address = value; }
  /**
   * Accessor method for the i15d contact name and address to be associated with the contact object
   * @return The contact i15d name and address
   * @see #m_i15d_address
   * @deprecated
   */
  public org.openrtk.idl.epp0604.contact.epp_ContactNameAddress getI15dAddress() { return m_i15d_address; }

  /**
   * Accessor method for the contact names and addresses to be associated with the contact object
   * @param value The contact names and addresses
   * @see #m_addresses
   */
  public void setAddresses(org.openrtk.idl.epp0604.contact.epp_ContactNameAddress[] value) { m_addresses = value; }
  /**
   * Accessor method for the contact names and addresses to be associated with the contact object
   * @return The contact names and addresses
   * @see #m_addresses
   */
  public org.openrtk.idl.epp0604.contact.epp_ContactNameAddress[] getAddresses() { return m_addresses; }

  /**
   * Accessor method for the telephone number of the contact
   * @param value The contact's telephone number
   * @see #m_voice
   */
  public void setVoice(org.openrtk.idl.epp0604.contact.epp_ContactPhone value) { m_voice = value; }
  /**
   * Accessor method for the telephone number of the contact
   * @return The contact's telephone number
   * @see #m_voice
   */
  public org.openrtk.idl.epp0604.contact.epp_ContactPhone getVoice() { return m_voice; }

  /**
   * Accessor method for the fax number of the contact
   * @param value The contact's fax number
   * @see #m_fax
   */
  public void setFax(org.openrtk.idl.epp0604.contact.epp_ContactPhone value) { m_fax = value; }
  /**
   * Accessor method for the fax number of the contact
   * @param value The contact's fax number
   * @see #m_fax
   */
  // included String version for backward compatibility
  public void setFax(String value) { m_fax = new org.openrtk.idl.epp0604.contact.epp_ContactPhone("",value); }
  /**
   * Accessor method for the fax number of the contact
   * @return The contact's fax number
   * @see #m_fax
   */
  public org.openrtk.idl.epp0604.contact.epp_ContactPhone getFax() { return m_fax; }

  /**
   * Accessor method for the e-mail address of the contact
   * @param value The contact's e-mail address
   * @see #m_email
   */
  public void setEmail(String value) { m_email = value; }
  /**
   * Accessor method for the e-mail address of the contact
   * @return The contact's e-mail address
   * @see #m_email
   */
  public String getEmail() { return m_email; }

  /**
   * Accessor method for the authorization information to be associated with the contact object
   * @param value The contact auth info
   * @see #m_auth_info
   */
  public void setAuthInfo(org.openrtk.idl.epp0604.epp_AuthInfo value) { m_auth_info = value; }
  /**
   * Accessor method for the authorization information to be associated with the contact object
   * @return The contact auth info
   * @see #m_auth_info
   */
  public org.openrtk.idl.epp0604.epp_AuthInfo getAuthInfo() { return m_auth_info; }

  /**
   * Converts this class into a string.
   * Typically used to view the object in debug output.
   * @return The string representation of this object instance
   */
  public String toString() {
    if ( m_ascii_address == null )
      return this.getClass().getName() + ": { m_cmd ["+m_cmd+"] m_id ["+m_id+"] m_ascii_address ["+m_ascii_address+"] m_i15d_address ["+m_i15d_address+"] m_voice ["+m_voice+"] m_fax ["+m_fax+"] m_email ["+m_email+"] m_auth_info ["+m_auth_info+"] }";
    else
      return this.getClass().getName() + ": { m_cmd ["+m_cmd+"] m_id ["+m_id+"] m_addresses ["+(m_addresses != null ? java.util.Arrays.asList(m_addresses) : null)+"] m_voice ["+m_voice+"] m_fax ["+m_fax+"] m_email ["+m_email+"] m_auth_info ["+m_auth_info+"] }";
  }

} // class epp_ContactCreateReq
