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
 * Class that contains the elements used to represent contact address.</p>
 * $Header: /cvsroot/epp-rtk/epp-rtk/java/src/org/openrtk/idl/epprtk/contact/epp_ContactAddress.java,v 1.1 2004/12/07 15:27:49 ewang2004 Exp $<br>
 * $Revision: 1.1 $<br>
 * $Date: 2004/12/07 15:27:49 $<br>
 * @see org.openrtk.idl.epprtk.contact.epp_ContactNameAddress
 */
public class epp_ContactAddress implements org.omg.CORBA.portable.IDLEntity
{
  /**
   * The part 1 of the contact's street address.
   * @see #setStreet1(String)
   * @see #getStreet1()
   */
  public String m_street1 = null;
  /**
   * The part 2 of the contact's street address.
   * @see #setStreet2(String)
   * @see #getStreet2()
   */
  public String m_street2 = null;
  /**
   * The part 3 of the contact's street address.
   * @see #setStreet3(String)
   * @see #getStreet3()
   */
  public String m_street3 = null;
  /**
   * The contact's city.
   * @see #setCity(String)
   * @see #getCity()
   */
  public String m_city = null;
  /**
   * The contact's state or province.
   * @see #setStateProvince(String)
   * @see #getStateProvince()
   */
  public String m_state_province = null;
  /**
   * The contact's postal code.
   * @see #setPostalCode(String)
   * @see #getPostalCode()
   */
  public String m_postal_code = null;
  /**
   * The contact's country code.
   * @see #setCountryCode(String)
   * @see #getCountryCode()
   */
  public String m_country_code = null;

  /**
   * Empty constructor
   */
  public epp_ContactAddress ()
  {
  } // ctor

  /**
   * The constructor with initializing variables.
   * @param _m_street1 The part 1 of the contact's street address
   * @param _m_street2 The part 2 of the contact's street address
   * @param _m_street3 The part 3 of the contact's street address
   * @param _m_city The contact's city
   * @param _m_state_province The contact's state or province
   * @param _m_postal_code The contact's postal code
   * @param _m_country_code The contact's country code
   */
  public epp_ContactAddress (String _m_street1, String _m_street2, String _m_street3, String _m_city, String _m_state_province, String _m_postal_code, String _m_country_code)
  {
    m_street1 = _m_street1;
    m_street2 = _m_street2;
    m_street3 = _m_street3;
    m_city = _m_city;
    m_state_province = _m_state_province;
    m_postal_code = _m_postal_code;
    m_country_code = _m_country_code;
  } // ctor

  /**
   * Accessor method for the part 1 of the contact's street address
   * @param value The part 1 of contact's street address
   * @see #m_street1
   */
  public void setStreet1(String value) { m_street1 = value; }
  /**
   * Accessor method for the part 1 of the contact's street address
   * @return The part 1 of the contact's street address
   * @see #m_street1
   */
  public String getStreet1() { return m_street1; }

  /**
   * Accessor method for the part 2 of the contact's street address
   * @param value The part 2 of the contact's street address
   * @see #m_street2
   */
  public void setStreet2(String value) { m_street2 = value; }
  /**
   * Accessor method for the part 2 of the contact's street address
   * @return The part 2 of the contact's street address
   * @see #m_street2
   */
  public String getStreet2() { return m_street2; }

  /**
   * Accessor method for the part 3 of the contact's street address
   * @param value The part 3 of the contact's street address
   * @see #m_street3
   */
  public void setStreet3(String value) { m_street3 = value; }
  /**
   * Accessor method for the part 3 of the contact's street address
   * @return The part 3 of the contact's street address
   * @see #m_street3
   */
  public String getStreet3() { return m_street3; }

  /**
   * Accessor method for the contact's city
   * @param value The contact's city
   * @see #m_city
   */
  public void setCity(String value) { m_city = value; }
  /**
   * Accessor method for the contact's city
   * @return The contact's city
   * @see #m_city
   */
  public String getCity() { return m_city; }

  /**
   * Accessor method for the contact's state or province
   * @param value The contact's state or province
   * @see #m_state_province
   */
  public void setStateProvince(String value) { m_state_province = value; }
  /**
   * Accessor method for the contact's state or province
   * @return The contact's state or province
   * @see #m_state_province
   */
  public String getStateProvince() { return m_state_province; }

  /**
   * Accessor method for the contact's postal code
   * @param value The contact's postal code
   * @see #m_postal_code
   */
  public void setPostalCode(String value) { m_postal_code = value; }
  /**
   * Accessor method for the contact's postal code
   * @return The contact's postal code
   * @see #m_postal_code
   */
  public String getPostalCode() { return m_postal_code; }

  /**
   * Accessor method for the contact's country code
   * @param value The contact's country code
   * @see #m_country_code
   */
  public void setCountryCode(String value) { m_country_code = value; }
  /**
   * Accessor method for the contact's country code
   * @return The contact's country code
   * @see #m_country_code
   */
  public String getCountryCode() { return m_country_code; }

  /**
   * Converts this class into a string.
   * Typically used to view the object in debug output.
   * @return The string representation of this object instance
   */
  public String toString() { return this.getClass().getName() + ": { m_street1 ["+m_street1+"] m_street2 ["+m_street2+"] m_street3 ["+m_street3+"] m_city ["+m_city+"] m_state_province ["+m_state_province+"] m_postal_code ["+m_postal_code+"] m_country_code ["+m_country_code+"] }"; }

} // class epp_ContactAddress
