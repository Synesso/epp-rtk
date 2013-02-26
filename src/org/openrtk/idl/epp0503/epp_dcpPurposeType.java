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

package org.openrtk.idl.epp0503;

/**
 * Class defining constant instances of data collection purpose types which describes
 * EPP server's policy of data collection and management.</p>
 * Five purpose types are defined: CONTACT, DN_REG, IP_REG, OTHER_PURPOSE, TM_REG.</p>
 * $Header: /cvsroot/epp-rtk/epp-rtk/java/src/org/openrtk/idl/epp0503/epp_dcpPurposeType.java,v 1.2 2003/09/10 21:29:56 tubadanm Exp $<br>
 * $Revision: 1.2 $<br>
 * $Date: 2003/09/10 21:29:56 $<br>
 * @see org.openrtk.idl.epp0503.epp_dcpStatement
 * @see com.tucows.oxrs.epp0503.rtk.xml.EPPGreeting
 */
public class epp_dcpPurposeType implements org.omg.CORBA.portable.IDLEntity
{
  private        int __value;
  private static int __size = 5;
  private static org.openrtk.idl.epp0503.epp_dcpPurposeType[] __array = new org.openrtk.idl.epp0503.epp_dcpPurposeType [__size];
  private static String[] __strings = { "contact","dnReg","ipReg","other","tmReg" };

  /**
   * Integer value representing the CONTACT purpose type.
   * @see #CONTACT
   */
  public static final int _CONTACT = 0;
  /**
   * Instance of epp_dcpPurposeType representing the CONTACT purpose type.
   */
  public static final org.openrtk.idl.epp0503.epp_dcpPurposeType CONTACT = new org.openrtk.idl.epp0503.epp_dcpPurposeType(_CONTACT);
  /**
   * Integer value representing the DN_REG purpose type.
   * @see #DN_REG
   */
  public static final int _DN_REG = 1;
  /**
   * Instance of epp_dcpPurposeType representing the DN_REG purpose type.
   */
  public static final org.openrtk.idl.epp0503.epp_dcpPurposeType DN_REG = new org.openrtk.idl.epp0503.epp_dcpPurposeType(_DN_REG);
  /**
   * Integer value representing the IP_REG purpose type.
   * @see #IP_REG
   */
  public static final int _IP_REG = 2;
  /**
   * Instance of epp_dcpPurposeType representing the IP_REG purpose type.
   */
  public static final org.openrtk.idl.epp0503.epp_dcpPurposeType IP_REG = new org.openrtk.idl.epp0503.epp_dcpPurposeType(_IP_REG);
  /**
   * Integer value representing the OTHER_PURPOSE purpose type.
   * @see #OTHER_PURPOSE
   */
  public static final int _OTHER_PURPOSE = 3;
  /**
   * Instance of epp_dcpPurposeType representing the OTHER_PURPOSE purpose type.
   */
  public static final org.openrtk.idl.epp0503.epp_dcpPurposeType OTHER_PURPOSE = new org.openrtk.idl.epp0503.epp_dcpPurposeType(_OTHER_PURPOSE);
  /**
   * Integer value representing the TM_REG purpose type.
   * @see #TM_REG
   */
  public static final int _TM_REG = 4;
  /**
   * Instance of epp_dcpPurposeType representing the TM_REG purpose type.
   */
  public static final org.openrtk.idl.epp0503.epp_dcpPurposeType TM_REG = new org.openrtk.idl.epp0503.epp_dcpPurposeType(_TM_REG);

  /**
   * Accessor method for the internal integer representing the data collection purpose type.
   * @return The integer value of this purpose type
   */
  public int value ()
  {
    return __value;
  }

  /**
   * Transform an integer into a epp_dcpPurposeType constant.
   * Given the integer representation of the purpose type, returns
   * one of the purpose type constants.
   * @param value The integer value for the desired purpose type
   */
  public static org.openrtk.idl.epp0503.epp_dcpPurposeType from_int (int value)
  {
    if (value >= 0 && value < __size)
      return __array[value];
    else
      throw new org.omg.CORBA.BAD_PARAM ();
  }

  /**
   * For internal use only.
   * Initializes the internal purpose type array.
   * @param value The integer value for the desired purpose type
   */
  protected epp_dcpPurposeType (int value)
  {
    __value = value;
    __array[__value] = this;
  }

  public String toString() { return __strings[this.value()]; }
} // class epp_dcpPurposeType
