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

package org.openrtk.idl.epp0705.contact;


/**
 * Class that contains the elements used to represent contact status with different type.</p>
 * $Header: /cvsroot/epp-rtk/epp-rtk/java/src/org/openrtk/idl/epp0705/contact/epp_ContactStatus.java,v 1.1 2003/03/20 22:42:27 tubadanm Exp $<br>
 * $Revision: 1.1 $<br>
 * $Date: 2003/03/20 22:42:27 $<br>
 * @see org.openrtk.idl.epp0705.contact.epp_ContactInfoRsp
 * @see org.openrtk.idl.epp0705.contact.epp_ContactUpdateAddRemove
 */
public class epp_ContactStatus implements org.omg.CORBA.portable.IDLEntity
{
  /**
   * The type of the contact status.
   * @see #setType(org.openrtk.idl.epp0705.contact.epp_ContactStatusType)
   * @see #getType()
   */
  public org.openrtk.idl.epp0705.contact.epp_ContactStatusType m_type = null;
  /**
   * The language used to express the contact status value.
   * @see #setLang(String)
   * @see #getLang()
   */
  public String m_lang = null;
  /**
   * The value of the contact status.
   * @see #setValue(String)
   * @see #getValue()
   */
  public String m_value = null;

  /**
   * Empty constructor
   */
  public epp_ContactStatus ()
  {
  } // ctor

  /**
   * The constructor with initializing variables.
   * @param _m_type The type of the contact status
   * @param _m_lang The language used to express the contact status value
   * @param _m_value The value of the contact status
   */
  public epp_ContactStatus (org.openrtk.idl.epp0705.contact.epp_ContactStatusType _m_type, String _m_lang, String _m_value)
  {
    m_type = _m_type;
    m_lang = _m_lang;
    m_value = _m_value;
  } // ctor

  /**
   * Accessor method for the type of the contact status
   * @param value The contact status type
   * @see #m_type
   */
  public void setType(org.openrtk.idl.epp0705.contact.epp_ContactStatusType value) { m_type = value; }
  /**
   * Accessor method for the type of the contact status
   * @return The contact status type
   * @see #m_type
   */
  public org.openrtk.idl.epp0705.contact.epp_ContactStatusType getType() { return m_type; }

  /**
   * Accessor method for the language used to express the contact status value
   * @param value The language of the status value
   * @see #m_lang
   */
  public void setLang(String value) { m_lang = value; }
  /**
   * Accessor method for the language used to express the contact status value
   * @return The language of the status value
   * @see #m_lang
   */
  public String getLang() { return m_lang; }

  /**
   * Accessor method for the value of the contact status
   * @param value The contact status value
   * @see #m_value
   */
  public void setValue(String value) { m_value = value; }
  /**
   * Accessor method for the value of the contact status
   * @return The contact status value
   * @see #m_value
   */
  public String getValue() { return m_value; }

  /**
   * Converts this class into a string.
   * Typically used to view the object in debug output.
   * @return The string representation of this object instance
   */
  public String toString() { return this.getClass().getName() + ": { m_type ["+m_type+"] m_lang ["+m_lang+"] m_value ["+m_value+"] }"; }

} // class epp_ContactStatus
