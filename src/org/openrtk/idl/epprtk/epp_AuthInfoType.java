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

package org.openrtk.idl.epprtk;


/**
 * Class defining constant instances of authorization information types
 * generic for all objects.</p>
 * Used in conjunction with the epp_AuthInfo class to indicate the object
 * authorization information.</p>
 * Currently, only one type is permitted: PW (password).</p>
 * $Header: /cvsroot/epp-rtk/epp-rtk/java/src/org/openrtk/idl/epprtk/epp_AuthInfoType.java,v 1.1 2004/12/07 15:27:49 ewang2004 Exp $<br>
 * $Revision: 1.1 $<br>
 * $Date: 2004/12/07 15:27:49 $<br>
 * @see org.openrtk.idl.epprtk.epp_AuthInfo
 */
public class epp_AuthInfoType implements org.omg.CORBA.portable.IDLEntity
{
  private        int __value;
  private static int __size = 2;
  private static org.openrtk.idl.epprtk.epp_AuthInfoType[] __array = new org.openrtk.idl.epprtk.epp_AuthInfoType [__size];
  private static String[] __strings = { "pw", "ext" };

  /**
   * Integer value representing the PW authorization information type.
   * @see #PW
   */
  public static final int _PW = 0;
  /**
   * Instance of epp_AuthInfoType representing the PW authorization information type.
   * Used directly with epp_AuthInfo.
   * @see org.openrtk.idl.epprtk.epp_AuthInfo
   */
  public static final org.openrtk.idl.epprtk.epp_AuthInfoType PW = new org.openrtk.idl.epprtk.epp_AuthInfoType(_PW);

  /**
   * Integer value representing the EXT authorization information type.
   * @see #EXT
   */
  public static final int _EXT = 1;
  /**
   * Instance of epp_AuthInfoType representing the EXT authorization information type.
   * Used directly with epp_AuthInfo.
   * @see org.openrtk.idl.epprtk.epp_AuthInfo
   */
  public static final org.openrtk.idl.epprtk.epp_AuthInfoType EXT = new org.openrtk.idl.epprtk.epp_AuthInfoType(_EXT);

  /**
   * Accessor method for the internal integer representing the type of authorization information.
   * @return The integer value of this authorization information type
   */
  public int value ()
  {
    return __value;
  }

  /**
   * Transform an integer into a epp_AuthInfoType constant.
   * Given the integer representation of the authorization information type,
   * returns one of the authorization information type constants.
   * @param value The integer value for the desired authorization information type
   */
  public static org.openrtk.idl.epprtk.epp_AuthInfoType from_int (int value)
  {
    if (value >= 0 && value < __size)
      return __array[value];
    else
      throw new org.omg.CORBA.BAD_PARAM ();
  }

  /**
   * For internal use only.
   * Initializes the internal authorization information type array.
   * @param value The integer value for the desired authorization information type
   */
  protected epp_AuthInfoType (int value)
  {
    __value = value;
    __array[__value] = this;
  }
  public String toString() { return __strings[this.value()]; }
} // class epp_AuthInfoType
