/*
**
** EPP RTK Java
** Copyright (C) 2002, Tucows, Inc.
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

package org.openrtk.idl.epp0705;


/**
 * Class defining constant instances of data collection policy expiry types which describes
 * date type for the expiry of the data collection policy.</p>
 * Two access types are defined: ABSOLUTE, and RELATIVE.</p>
 * $Header: /cvsroot/epp-rtk/epp-rtk/java/src/org/openrtk/idl/epp0705/epp_dcpExpiryType.java,v 1.2 2003/09/10 21:29:59 tubadanm Exp $<br>
 * $Revision: 1.2 $<br>
 * $Date: 2003/09/10 21:29:59 $<br>
 * @see org.openrtk.idl.epp0705.epp_DataCollectionPolicy
 * @see com.tucows.oxrs.epp0705.rtk.xml.EPPGreeting
 */
public class epp_dcpExpiryType implements org.omg.CORBA.portable.IDLEntity
{
  private        int __value;
  private static int __size = 2;
  private static org.openrtk.idl.epp0705.epp_dcpExpiryType[] __array = new org.openrtk.idl.epp0705.epp_dcpExpiryType [__size];
  private static String[] __strings = { "absolute","relative" };

  /**
   * Integer value representing the ABSOLUTE access type.
   * @see #ABSOLUTE
   */
  public static final int _ABSOLUTE = 0;
  /**
   * Instance of epp_dcpExpiryType representing the ABSOLUTE expiry type.
   */
  public static final org.openrtk.idl.epp0705.epp_dcpExpiryType ABSOLUTE = new org.openrtk.idl.epp0705.epp_dcpExpiryType(_ABSOLUTE);
  /**
   * Integer value representing the RELATIVE access type.
   * @see #RELATIVE
   */
  public static final int _RELATIVE = 1;
  /**
   * Instance of epp_dcpExpiryType representing the RELATIVE expiry type.
   */
  public static final org.openrtk.idl.epp0705.epp_dcpExpiryType RELATIVE = new org.openrtk.idl.epp0705.epp_dcpExpiryType(_RELATIVE);

  /**
   * Accessor method for the internal integer representing the expiry type.
   * @return The integer value of this expiry type
   */
  public int value ()
  {
    return __value;
  }

  /**
   * Transform an integer into a epp_dcpExpiryType constant.
   * Given the integer representation of the expiry type, returns
   * one of the expiry type constants.
   * @param value The integer value for the desired expiry type
   */ 
  public static org.openrtk.idl.epp0705.epp_dcpExpiryType from_int (int value)
  {
    if (value >= 0 && value < __size)
      return __array[value];
    else
      throw new org.omg.CORBA.BAD_PARAM ();
  }

  /**
   * For internal use only.
   * Initializes the internal access type array.
   * @param value The integer value for the desired expiry type
   */
  protected epp_dcpExpiryType (int value)
  {
    __value = value;
    __array[__value] = this;
  }

  public String toString() { return __strings[this.value()]; }
} // class epp_dcpExpiryType
