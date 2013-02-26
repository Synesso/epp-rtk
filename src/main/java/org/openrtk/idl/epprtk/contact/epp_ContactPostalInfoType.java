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
 * Class that represents the types of postal info used in EPP -- international or local</p>
 * $Header: /cvsroot/epp-rtk/epp-rtk/java/src/org/openrtk/idl/epprtk/contact/epp_ContactPostalInfoType.java,v 1.1 2004/12/07 15:27:49 ewang2004 Exp $<br>
 * $Revision: 1.1 $<br>
 * $Date: 2004/12/07 15:27:49 $<br>
 * @see org.openrtk.idl.epprtk.contact.epp_ContactCreateReq
 * @see org.openrtk.idl.epprtk.contact.epp_ContactInfoRsp
 * @see org.openrtk.idl.epprtk.contact.epp_ContactUpdateChange
 */
public class epp_ContactPostalInfoType implements org.omg.CORBA.portable.IDLEntity
{
  private        int __value;
  private static int __size = 2;
  private static org.openrtk.idl.epprtk.contact.epp_ContactPostalInfoType[] __array = new org.openrtk.idl.epprtk.contact.epp_ContactPostalInfoType [__size];
  private static String[] __strings = {
                                    "loc",
                                    "int"
                                    };

  /**
   * Integer value representing the LOC postal info type.
   * @see #LOC
   */
  public static final int _LOC = 0;
  /**
   * Instance of epp_ContactPostalInfoType representing the LOC status type.
   * Used directly with epp_ContactNameAddress.
   * @see org.openrtk.idl.epprtk.contact.epp_ContactNameAddress
   */
  public static final org.openrtk.idl.epprtk.contact.epp_ContactPostalInfoType LOC = new org.openrtk.idl.epprtk.contact.epp_ContactPostalInfoType(_LOC);
  /**
   * Integer value representing the INT postal info type.
   * @see #INT
   */
  public static final int _INT = 1;
  /**
   * Instance of epp_ContactPostalInfoType representing the INT status type.
   * Used directly with epp_ContactNameAddress.
   * @see org.openrtk.idl.epprtk.contact.epp_ContactNameAddress
   */
  public static final org.openrtk.idl.epprtk.contact.epp_ContactPostalInfoType INT = new org.openrtk.idl.epprtk.contact.epp_ContactPostalInfoType(_INT);

  /**
   * Accessor method for the internal integer representing the type of postal info.
   * @return The integer value of this contact postal info type
   */
  public int value ()
  {
    return __value;
  }

  /**
   * Transform an integer into a epp_ContactPostalInfoType constant.
   * Given the integer representation of the postal info type, returns
   * one of the postal info type constants.
   * @param value The integer value for the desired postal info type
   */
  public static org.openrtk.idl.epprtk.contact.epp_ContactPostalInfoType from_int (int value)
  {
    if (value >= 0 && value < __size)
      return __array[value];
    else
      throw new org.omg.CORBA.BAD_PARAM ();
  }

  /**
   * For internal use only.
   * Initializes the internal postal info type array.
   * @param value The integer value for the desired postal info type
   */
  protected epp_ContactPostalInfoType (int value)
  {
    __value = value;
    __array[__value] = this;
  }

  public String toString() { return __strings[this.value()]; }
} // class epp_ContactPostalInfoType
