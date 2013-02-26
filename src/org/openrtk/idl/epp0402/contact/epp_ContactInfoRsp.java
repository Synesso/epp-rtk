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
 * Class that contains the elements used to represent the contact info
 * response from the EPP server.</p>
 * Note usually instantiated by the RTK user, but rather by EPPContactInfo
 * and retrieved using that class' getResponseData() method.</p>
 * $Header: /cvsroot/epp-rtk/epp-rtk/java/src/org/openrtk/idl/epp0402/contact/epp_ContactInfoRsp.java,v 1.2 2003/09/10 21:29:55 tubadanm Exp $<br>
 * $Revision: 1.2 $<br>
 * $Date: 2003/09/10 21:29:55 $<br>
 * @see com.tucows.oxrs.epp0402.rtk.xml.EPPContactInfo
 * @see org.openrtk.idl.epp0402.contact.epp_ContactInfoReq
 */
public class epp_ContactInfoRsp implements org.omg.CORBA.portable.IDLEntity
{
  /**
   * The common and generic response element.
   * @see #getRsp()
   */
  public org.openrtk.idl.epp0402.epp_Response m_rsp = null;
  /**
   * The identifier for the contact object in the registry.
   * @see #setId(String)
   * @see #getId()
   */
  public String m_id = null;
  /**
   * The repository object identifier associated with the domain object.
   * @see #setRoid(String)
   * @see #getRoid()
   */
  public String m_roid = null;
  /**
   * The contact name and address associated with the contact object.
   * Content of this element must be represented in a subset of UTF-8.
   * @see #setAsciiAddress(org.openrtk.idl.epp0402.contact.epp_ContactNameAddress)
   * @see #getAsciiAddress()
   */
  public org.openrtk.idl.epp0402.contact.epp_ContactNameAddress m_ascii_address = null;
  /**
   * The contact name and address associated with the contact object.
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
   * The array of status associated with the contact object.
   * @see #setStatus(org.openrtk.idl.epp0402.contact.epp_ContactStatus[])
   * @see #getStatus()
   */
  public org.openrtk.idl.epp0402.contact.epp_ContactStatus m_status[] = null;
  /**
   * The identifier of the sponsoring client.
   * @see #setClientId(String)
   * @see #getClientId()
   */
  public String m_client_id = null;
  /**
   * The identifier of the client that created the contact object.
   * @see #setCreatedBy(String)
   * @see #getCreatedBy()
   */
  public String m_created_by = null;
  /**
   * The creation date of the contact object.
   * @see #setCreatedDate(String)
   * @see #getCreatedDate()
   */
  public String m_created_date = null;
  /**
   * The identifier of the client that last updated the contact object.
   * @see #setUpdatedBy(String)
   * @see #getUpdatedBy()
   */
  public String m_updated_by = null;
  /**
   * The most recent modification date of the contact object.
   * @see #setUpdatedDate(String)
   * @see #getUpdatedDate()
   */
  public String m_updated_date = null;
  /**
   * The most recent transfer date of the contact object.
   * @see #setTransferDate(String)
   * @see #getTransferDate()
   */
  public String m_transfer_date = null;
  /**
   * The authorization information associated with the contact object.
   * @see #setAuthInfo(org.openrtk.idl.epp0402.epp_AuthInfo)
   * @see #getAuthInfo()
   */
  public org.openrtk.idl.epp0402.epp_AuthInfo m_auth_info = null;

  /**
   * Empty constructor
   */
  public epp_ContactInfoRsp ()
  {
  } // ctor

  /**
   * The constructor with initializing variables.
   * @param _m_rsp The common and generic response element
   * @param _m_id The identifier for the contact object in the registry
   * @param _m_roid The repository object identifier associated with the domain object
   * @param _m_ascii_address The ascii contact name and address associated with the contact object
   * @param _m_i15d_address The i15d contact name and address associated with the contact object
   * @param _m_voice The contact's telephone number
   * @param _m_fax The contact's fax number
   * @param _m_email The contact's e-mail address
   * @param _m_status The array of status associated with the contact object
   * @param _m_client_id The identifier of the sponsoring client
   * @param _m_created_by The identifier of the client that created the contact object
   * @param _m_created_date The creation date of the contact object
   * @param _m_updated_by The identifier of the client that last updated the contact object
   * @param _m_updated_date The most recent modification date of the contact object
   * @param _m_transfer_date The most recent transfer date of the contact object
   * @param _m_auth_info The authorization information associated with the contact object
   */
  public epp_ContactInfoRsp (org.openrtk.idl.epp0402.epp_Response _m_rsp, String _m_id, String _m_roid, org.openrtk.idl.epp0402.contact.epp_ContactNameAddress _m_ascii_address, org.openrtk.idl.epp0402.contact.epp_ContactNameAddress _m_i15d_address, org.openrtk.idl.epp0402.contact.epp_ContactPhone _m_voice, org.openrtk.idl.epp0402.contact.epp_ContactPhone _m_fax, String _m_email, org.openrtk.idl.epp0402.contact.epp_ContactStatus[] _m_status, String _m_client_id, String _m_created_by, String _m_created_date, String _m_updated_by, String _m_updated_date, String _m_transfer_date, org.openrtk.idl.epp0402.epp_AuthInfo _m_auth_info)
  {
    m_rsp = _m_rsp;
    m_id = _m_id;
    m_roid = _m_roid;
    m_ascii_address = _m_ascii_address;
    m_i15d_address = _m_i15d_address;
    m_voice = _m_voice;
    m_fax = _m_fax;
    m_email = _m_email;
    m_status = _m_status;
    m_client_id = _m_client_id;
    m_created_by = _m_created_by;
    m_created_date = _m_created_date;
    m_updated_by = _m_updated_by;
    m_updated_date = _m_updated_date;
    m_transfer_date = _m_transfer_date;
    m_auth_info = _m_auth_info;
  } // ctor

  /**
   * Accessor method for the common and generic response element.
   * @param value The new value for the response element
   * @see #m_rsp
   */
  public void setRsp(org.openrtk.idl.epp0402.epp_Response value) { m_rsp = value; }
  /**
   * Accessor method for the common and generic response element.
   * @return The value for the response element
   * @see #m_rsp
   */
  public org.openrtk.idl.epp0402.epp_Response getRsp() { return m_rsp; }

  /**
   * Accessor method for the identifier for the contact object in the registry
   * @param value The contact id
   * @see #m_id
   */
  public void setId(String value) { m_id = value; }
  /**
   * Accessor method for the identifier for the contact object in the registry
   * @return The contact id
   * @see #m_id
   */
  public String getId() { return m_id; }

  /**
   * Accessor method for the repository object identifier associated with the domain object
   * @param value The contact roid
   * @see #m_roid
   */
  public void setRoid(String value) { m_roid = value; }
  /**
   * Accessor method for the repository object identifier associated with the domain object
   * @return The contact roid
   * @see #m_roid
   */
  public String getRoid() { return m_roid; }

  /**
   * Accessor method for the ascii contact name and address associated with the contact object
   * @param value The contact ascii name and address
   * @see #m_ascii_address
   */
  public void setAsciiAddress(org.openrtk.idl.epp0402.contact.epp_ContactNameAddress value) { m_ascii_address = value; }
  /**
   * Accessor method for the ascii contact name and address associated with the contact object
   * @return The contact ascii name and address
   * @see #m_ascii_address
   */
  public org.openrtk.idl.epp0402.contact.epp_ContactNameAddress getAsciiAddress() { return m_ascii_address; }

  /**
   * Accessor method for the i15d contact name and address associated with the contact object
   * @param value The contact i15d name and address
   * @see #m_i15d_address
   */
  public void setI15dAddress(org.openrtk.idl.epp0402.contact.epp_ContactNameAddress value) { m_i15d_address = value; }
  /**
   * Accessor method for the i15d contact name and address associated with the contact object
   * @return The contact i15d name and address
   * @see #m_i15d_address
   */
  public org.openrtk.idl.epp0402.contact.epp_ContactNameAddress getI15dAddress() { return m_i15d_address; }

  /**
   * Accessor method for contact's telephone number
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
   * Accessor method for the array of status associated with the contact object
   * @param value The array of contact status
   * @see #m_status
   */
  public void setStatus(org.openrtk.idl.epp0402.contact.epp_ContactStatus[] value) { m_status = value; }
  /**
   * Accessor method for the array of status associated with the contact object
   * @return The array of contact status
   * @see #m_status
   */
  public org.openrtk.idl.epp0402.contact.epp_ContactStatus[] getStatus() { return m_status; }

  /**
   * Accessor method for the identifier of the sponsoring client
   * @param value The identifier of the sponsoring client
   * @see #m_client_id
   */
  public void setClientId(String value) { m_client_id = value; }
  /**
   * Accessor method for the identifier of the sponsoring client
   * @return The identifier of the sponsoring client
   * @see #m_client_id
   */
  public String getClientId() { return m_client_id; }

  /**
   * Accessor method for the identifier of the client that created the contact object
   * @param value The identifier of the client that created the contact object
   * @see #m_created_by
   */
  public void setCreatedBy(String value) { m_created_by = value; }
  /**
   * Accessor method for the identifier of the client that created the contact object
   * @return The identifier of the client that created the contact object
   * @see #m_created_by
   */
  public String getCreatedBy() { return m_created_by; }

  /**
   * Accessor method for the creation date of the contact object
   * @param value The creation date of the contact
   * @see #m_created_date
   */
  public void setCreatedDate(String value) { m_created_date = value; }
  /**
   * Accessor method for the creation date of the contact object
   * @return The creation date of the contact
   * @see #m_created_date
   */
  public String getCreatedDate() { return m_created_date; }

  /**
   * Accessor method for the identifier of the client that last updated the contact object
   * @param value The identifier of the client that last updated the contact object
   * @see #m_updated_by
   */
  public void setUpdatedBy(String value) { m_updated_by = value; }
  /**
   * Accessor method for the identifier of the client that last updated the contact object
   * @return The identifier of the client that last updated the contact object
   * @see #m_updated_by
   */
  public String getUpdatedBy() { return m_updated_by; }

  /**
   * Accessor method for the most recent modification date of the contact object
   * @param value The most recent modification date
   * @see #m_updated_date
   */
  public void setUpdatedDate(String value) { m_updated_date = value; }
  /**
   * Accessor method for the most recent modification date of the contact object
   * @return The most recent modification date
   * @see #m_updated_date
   */
  public String getUpdatedDate() { return m_updated_date; }

  /**
   * Accessor method for the most recent transfer date of the contact object
   * @param value The most recent transfer date
   * @see #m_transfer_date
   */
  public void setTransferDate(String value) { m_transfer_date = value; }
  /**
   * Accessor method for the most recent transfer date of the contact object
   * @return The most recent transfer date
   * @see #m_transfer_date
   */
  public String getTransferDate() { return m_transfer_date; }

  /**
   * Accessor method for the authorization information associated with the contact object
   * @param value The contact auth info
   * @see #m_auth_info
   */
  public void setAuthInfo(org.openrtk.idl.epp0402.epp_AuthInfo value) { m_auth_info = value; }
  /**
   * Accessor method for the authorization information associated with the contact object
   * @return The contact auth info
   * @see #m_auth_info
   */
  public org.openrtk.idl.epp0402.epp_AuthInfo getAuthInfo() { return m_auth_info; }

  /**
   * Converts this class into a string.
   * Typically used to view the object in debug output.
   * @return The string representation of this object instance
   */
  public String toString() { return this.getClass().getName() + ": { m_rsp ["+m_rsp+"] m_id ["+m_id+"] m_roid ["+m_roid+"] m_ascii_address ["+m_ascii_address+"] m_i15d_address ["+m_i15d_address+"] m_voice ["+m_voice+"] m_fax ["+m_fax+"] m_email ["+m_email+"] m_status ["+(m_status != null ? java.util.Arrays.asList(m_status) : null)+"] m_client_id ["+m_client_id+"] m_created_by ["+m_created_by+"] m_created_date ["+m_created_date+"] m_updated_by ["+m_updated_by+"] m_updated_date ["+m_updated_date+"] m_transfer_date ["+m_transfer_date+"] m_auth_info ["+m_auth_info+"] }"; }

} // class epp_ContactInfoRsp
