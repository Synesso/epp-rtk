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
 * Class defining constant instances of data retention types which describes
 * EPP server's policy of data collection and management.</p>
 * Five retention types are defined: BUSINESS, FUNCTIONAL, INDEFINITE, LEGAL, NONE.</p>
 * $Header: /cvsroot/epp-rtk/epp-rtk/java/src/org/openrtk/idl/epp0503/epp_dcpRetentionType.java,v 1.2 2003/09/10 21:29:56 tubadanm Exp $<br>
 * $Revision: 1.2 $<br>
 * $Date: 2003/09/10 21:29:56 $<br>
 * @see org.openrtk.idl.epp0503.epp_dcpStatement
 * @see com.tucows.oxrs.epp0503.rtk.xml.EPPGreeting
 */
public class epp_dcpRetentionType implements org.omg.CORBA.portable.IDLEntity
{
  private        int __value;
  private static int __size = 5;
  private static org.openrtk.idl.epp0503.epp_dcpRetentionType[] __array = new org.openrtk.idl.epp0503.epp_dcpRetentionType [__size];
  private static String[] __strings = { "business","functional","indefinite","legal","none" };

  /**
   * Integer value representing the BUSINESS retention type.
   * @see #BUSINESS
   */
  public static final int _BUSINESS = 0;
  /**
   * Instance of epp_dcpRetentionType representing the BUSINESS retention type.
   */
  public static final org.openrtk.idl.epp0503.epp_dcpRetentionType BUSINESS = new org.openrtk.idl.epp0503.epp_dcpRetentionType(_BUSINESS);
  /**
   * Integer value representing the FUNCTIONAL retention type.
   * @see #FUNCTIONAL
   */
  public static final int _FUNCTIONAL = 1;
  /**
   * Instance of epp_dcpRetentionType representing the FUNCTIONAL retention type.
   */
  public static final org.openrtk.idl.epp0503.epp_dcpRetentionType FUNCTIONAL = new org.openrtk.idl.epp0503.epp_dcpRetentionType(_FUNCTIONAL);
  /**
   * Integer value representing the INDEFINITE retention type.
   * @see #INDEFINITE
   */
  public static final int _INDEFINITE = 2;
  /**
   * Instance of epp_dcpRetentionType representing the INDEFINITE retention type.
   */
  public static final org.openrtk.idl.epp0503.epp_dcpRetentionType INDEFINITE = new org.openrtk.idl.epp0503.epp_dcpRetentionType(_INDEFINITE);
  /**
   * Integer value representing the LEGAL retention type.
   * @see #LEGAL
   */
  public static final int _LEGAL = 3;
  /**
   * Instance of epp_dcpRetentionType representing the LEGAL retention type.
   */
  public static final org.openrtk.idl.epp0503.epp_dcpRetentionType LEGAL = new org.openrtk.idl.epp0503.epp_dcpRetentionType(_LEGAL);
  /**
   * Integer value representing the NONE retention type.
   * @see #NONE
   */
  public static final int _NONE = 4;
  /**
   * Instance of epp_dcpRetentionType representing the NONE retention type.
   */
  public static final org.openrtk.idl.epp0503.epp_dcpRetentionType NONE = new org.openrtk.idl.epp0503.epp_dcpRetentionType(_NONE);

  /**
   * Accessor method for the internal integer representing the data retention type.
   * @return The integer value of this retention type
   */
  public int value ()
  {
    return __value;
  }

  /**
   * Transform an integer into a epp_dcpRetentionType constant.
   * Given the integer representation of the retention type, returns
   * one of the retention type constants.
   * @param value The integer value for the desired retention type
   */
  public static org.openrtk.idl.epp0503.epp_dcpRetentionType from_int (int value)
  {
    if (value >= 0 && value < __size)
      return __array[value];
    else
      throw new org.omg.CORBA.BAD_PARAM ();
  }

  /**
   * For internal use only.
   * Initializes the internal retention type array.
   * @param value The integer value for the desired retention type
   */
  protected epp_dcpRetentionType (int value)
  {
    __value = value;
    __array[__value] = this;
  }

  public String toString() { return __strings[this.value()]; }
} // class epp_dcpRetentionType
