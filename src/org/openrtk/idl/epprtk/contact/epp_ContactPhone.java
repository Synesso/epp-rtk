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
 * Class that contains the elements used to represent contact's voice telephone
 * number or facsimile telephone number.</p>
 * $Header: /cvsroot/epp-rtk/epp-rtk/java/src/org/openrtk/idl/epprtk/contact/epp_ContactPhone.java,v 1.1 2004/12/07 15:27:49 ewang2004 Exp $<br>
 * $Revision: 1.1 $<br>
 * $Date: 2004/12/07 15:27:49 $<br>
 * @see org.openrtk.idl.epprtk.contact.epp_ContactCreateReq
 * @see org.openrtk.idl.epprtk.contact.epp_ContactInfoRsp
 * @see org.openrtk.idl.epprtk.contact.epp_ContactUpdateChange
 */
public class epp_ContactPhone implements org.omg.CORBA.portable.IDLEntity
{
  /**
   * The extension of the telephone number.
   * @see #setExtension(String)
   * @see #getExtension()
   */
  public String m_extension = null;
  /**
   * The contact's telephone number value.
   * @see #setValue(String)
   * @see #getValue()
   */
  public String m_value = null;

  /**
   * Empty constructor
   */
  public epp_ContactPhone ()
  {
  } // ctor

  /**
   * The constructor with initializing variables.
   * @param _m_extension The extension of the telephone number
   * @param _m_value The contact's telephone number value
   */
  public epp_ContactPhone (String _m_extension, String _m_value)
  {
    m_extension = _m_extension;
    m_value = _m_value;
  } // ctor

  /**
   * Accessor method for the extension of the telephone number
   * @param value The extension of the telephone number
   * @see #m_extension
   */
  public void setExtension(String value) { m_extension = value; }
  /**
   * Accessor method for the extension of the telephone number
   * @return The extension of the telephone number
   * @see #m_extension
   */
  public String getExtension() { return m_extension; }

  /**
   * Accessor method for the contact's telephone number value
   * @param value The contact's telephone number value
   * @see #m_value
   */
  public void setValue(String value) { m_value = value; }
  /**
   * Accessor method for the contact's telephone number value
   * @return The contact's telephone number value
   * @see #m_value
   */
  public String getValue() { return m_value; }

  /**
   * Converts this class into a string.
   * Typically used to view the object in debug output.
   * @return The string representation of this object instance
   */
  public String toString() { return this.getClass().getName() + ": { m_extension ["+m_extension+"] m_value ["+m_value+"] }"; }

} // class epp_ContactPhone
