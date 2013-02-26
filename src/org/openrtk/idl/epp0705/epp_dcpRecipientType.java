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
 * Class defining constant instances of data recipient types which describes
 * EPP server's policy of data collection and management.</p>
 * Five recipient types are defined: OTHER_RECIPIENT, OURS, PUBLIK, SAME, UNRELATED.</p>
 * $Header: /cvsroot/epp-rtk/epp-rtk/java/src/org/openrtk/idl/epp0705/epp_dcpRecipientType.java,v 1.2 2003/09/10 21:29:59 tubadanm Exp $<br>
 * $Revision: 1.2 $<br>
 * $Date: 2003/09/10 21:29:59 $<br>
 * @see org.openrtk.idl.epp0705.epp_dcpStatement
 * @see com.tucows.oxrs.epp0705.rtk.xml.EPPGreeting
 */
public class epp_dcpRecipientType implements org.omg.CORBA.portable.IDLEntity
{
  private        int __value;
  private static int __size = 5;
  private static org.openrtk.idl.epp0705.epp_dcpRecipientType[] __array = new org.openrtk.idl.epp0705.epp_dcpRecipientType [__size];
  private static String[] __strings = { "other","ours","public","same","unrelated" };

  /**
   * Integer value representing the OTHER_RECIPIENT recipient type.
   * @see #OTHER_RECIPIENT
   */
  public static final int _OTHER_RECIPIENT = 0;
  /**
   * Instance of epp_dcpRecipientType representing the OTHER_RECIPIENT recipient type.
   */
  public static final org.openrtk.idl.epp0705.epp_dcpRecipientType OTHER_RECIPIENT = new org.openrtk.idl.epp0705.epp_dcpRecipientType(_OTHER_RECIPIENT);
  /**
   * Integer value representing the OURS recipient type.
   * @see #OURS
   */
  public static final int _OURS = 1;
  /**
   * Instance of epp_dcpRecipientType representing the OURS recipient type.
   */
  public static final org.openrtk.idl.epp0705.epp_dcpRecipientType OURS = new org.openrtk.idl.epp0705.epp_dcpRecipientType(_OURS);
  /**
   * Integer value representing the PUBLIK recipient type.
   * @see #PUBLIK
   */
  public static final int _PUBLIK = 2;
  /**
   * Instance of epp_dcpRecipientType representing the PUBLIK recipient type.
   */
  public static final org.openrtk.idl.epp0705.epp_dcpRecipientType PUBLIK = new org.openrtk.idl.epp0705.epp_dcpRecipientType(_PUBLIK);
  /**
   * Integer value representing the SAME recipient type.
   * @see #SAME
   */
  public static final int _SAME = 3;
  /**
   * Instance of epp_dcpRecipientType representing the SAME recipient type.
   */
  public static final org.openrtk.idl.epp0705.epp_dcpRecipientType SAME = new org.openrtk.idl.epp0705.epp_dcpRecipientType(_SAME);
  /**
   * Integer value representing the UNRELATED recipient type.
   * @see #UNRELATED
   */
  public static final int _UNRELATED = 4;
  /**
   * Instance of epp_dcpRecipientType representing the UNRELATED recipient type.
   */
  public static final org.openrtk.idl.epp0705.epp_dcpRecipientType UNRELATED = new org.openrtk.idl.epp0705.epp_dcpRecipientType(_UNRELATED);

  /**
   * Accessor method for the internal integer representing the data recipient type.
   * @return The integer value of this recipient type
   */
  public int value ()
  {
    return __value;
  }

  /**
   * Transform an integer into a epp_dcpRecipientType constant.
   * Given the integer representation of the recipient type, returns
   * one of the recipient type constants.
   * @param value The integer value for the desired recipient type
   */
  public static org.openrtk.idl.epp0705.epp_dcpRecipientType from_int (int value)
  {
    if (value >= 0 && value < __size)
      return __array[value];
    else
      throw new org.omg.CORBA.BAD_PARAM ();
  }

  /**
   * For internal use only.
   * Initializes the internal recipient type array.
   * @param value The integer value for the desired recipient type
   */
  protected epp_dcpRecipientType (int value)
  {
    __value = value;
    __array[__value] = this;
  }

  public String toString() { return __strings[this.value()]; }
} // class epp_dcpRecipientType
