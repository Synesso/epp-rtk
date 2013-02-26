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

package org.openrtk.idl.epp0705;

/**
 * Class defining constant instances of data collection purpose types which describes
 * EPP server's policy of data collection and management.</p>
 * Four purpose types are defined: ADMIN, CONTACT, OTHER_PURPOSE, and PROV.</p>
 * $Header: /cvsroot/epp-rtk/epp-rtk/java/src/org/openrtk/idl/epp0705/epp_dcpPurposeType.java,v 1.2 2003/09/10 21:29:59 tubadanm Exp $<br>
 * $Revision: 1.2 $<br>
 * $Date: 2003/09/10 21:29:59 $<br>
 * @see org.openrtk.idl.epp0705.epp_dcpStatement
 * @see com.tucows.oxrs.epp0705.rtk.xml.EPPGreeting
 */
public class epp_dcpPurposeType implements org.omg.CORBA.portable.IDLEntity
{
  private        int __value;
  private static int __size = 4;
  private static org.openrtk.idl.epp0705.epp_dcpPurposeType[] __array = new org.openrtk.idl.epp0705.epp_dcpPurposeType [__size];
  private static String[] __strings = { "admin","contact","other","prov" };

  /**
   * Integer value representing the ADMIN purpose type.
   * @see #ADMIN
   */
  public static final int _ADMIN = 0;
  /**
   * Instance of epp_dcpPurposeType representing the CONTACT purpose type.
   */
  public static final org.openrtk.idl.epp0705.epp_dcpPurposeType ADMIN = new org.openrtk.idl.epp0705.epp_dcpPurposeType(_ADMIN);
  /**
   * Integer value representing the CONTACT purpose type.
   * @see #CONTACT
   */
  public static final int _CONTACT = 1;
  /**
   * Instance of epp_dcpPurposeType representing the CONTACT purpose type.
   */
  public static final org.openrtk.idl.epp0705.epp_dcpPurposeType CONTACT = new org.openrtk.idl.epp0705.epp_dcpPurposeType(_CONTACT);
  /**
   * Integer value representing the OTHER_PURPOSE purpose type.
   * @see #OTHER_PURPOSE
   */
  public static final int _OTHER_PURPOSE = 2;
  /**
   * Instance of epp_dcpPurposeType representing the OTHER_PURPOSE purpose type.
   */
  public static final org.openrtk.idl.epp0705.epp_dcpPurposeType OTHER_PURPOSE = new org.openrtk.idl.epp0705.epp_dcpPurposeType(_OTHER_PURPOSE);
  /**
   * Integer value representing the PROV purpose type.
   * @see #PROV
   */
  public static final int _PROV = 3;
  /**
   * Instance of epp_dcpPurposeType representing the PROV purpose type.
   */
  public static final org.openrtk.idl.epp0705.epp_dcpPurposeType PROV = new org.openrtk.idl.epp0705.epp_dcpPurposeType(_PROV);

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
  public static org.openrtk.idl.epp0705.epp_dcpPurposeType from_int (int value)
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
