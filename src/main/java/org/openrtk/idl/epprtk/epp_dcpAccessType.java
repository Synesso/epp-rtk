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
 * Class defining constant instances of data collection policy access types which describes
 * access provided by the server to data received from the client.</p>
 * Six access types are defined: ALL, NONE, NULL_ACCESS, OTHER, PERSONAL, and PERSONAL_AND_OTHER.</p>
 * $Header: /cvsroot/epp-rtk/epp-rtk/java/src/org/openrtk/idl/epprtk/epp_dcpAccessType.java,v 1.1 2004/12/07 15:27:49 ewang2004 Exp $<br>
 * $Revision: 1.1 $<br>
 * $Date: 2004/12/07 15:27:49 $<br>
 * @see org.openrtk.idl.epprtk.epp_DataCollectionPolicy
 * @see com.tucows.oxrs.epprtk.rtk.xml.EPPGreeting
 */
public class epp_dcpAccessType implements org.omg.CORBA.portable.IDLEntity
{
  private        int __value;
  private static int __size = 6;
  private static org.openrtk.idl.epprtk.epp_dcpAccessType[] __array = new org.openrtk.idl.epprtk.epp_dcpAccessType [__size];
  private static String[] __strings = { "all","none","null","other","personal","personalAndOther" };

  /**
   * Integer value representing the ALL access type.
   * @see #ALL
   */
  public static final int _ALL = 0;
  /**
   * Instance of epp_dcpAccessType representing the ALL access type.
   */
  public static final org.openrtk.idl.epprtk.epp_dcpAccessType ALL = new org.openrtk.idl.epprtk.epp_dcpAccessType(_ALL);
  /**
   * Integer value representing the NONE_ACCESS access type.
   * @see #NONE_ACCESS
   */
  public static final int _NONE_ACCESS = 1;
  /**
   * Instance of epp_dcpAccessType representing the NONE_ACCESS access type.
   */
  public static final org.openrtk.idl.epprtk.epp_dcpAccessType NONE_ACCESS = new org.openrtk.idl.epprtk.epp_dcpAccessType(_NONE_ACCESS);
  /**
   * Integer value representing the NULL_ACCESS access type.
   * @see #NULL_ACCESS
   */
  public static final int _NULL_ACCESS = 2;
  /**
   * Instance of epp_dcpAccessType representing the NULL_ACCESS access type.
   */
  public static final org.openrtk.idl.epprtk.epp_dcpAccessType NULL_ACCESS = new org.openrtk.idl.epprtk.epp_dcpAccessType(_NULL_ACCESS);
  /**
   * Integer value representing the OTHER access type.
   * @see #OTHER
   */
  public static final int _OTHER = 3;
  /**
   * Instance of epp_dcpAccessType representing the OTHER access type.
   */
  public static final org.openrtk.idl.epprtk.epp_dcpAccessType OTHER = new org.openrtk.idl.epprtk.epp_dcpAccessType(_OTHER);
  /**
   * Integer value representing the PERSONAL access type.
   * @see #PERSONAL
   */
  public static final int _PERSONAL = 4;
  /**
   * Instance of epp_dcpAccessType representing the PERSONAL access type.
   */
  public static final org.openrtk.idl.epprtk.epp_dcpAccessType PERSONAL = new org.openrtk.idl.epprtk.epp_dcpAccessType(_PERSONAL);
  /**
   * Integer value representing the PERSONAL_AND_OTHER access type.
   * @see #PERSONAL_AND_OTHER
   */
  public static final int _PERSONAL_AND_OTHER = 4;
  /**
   * Instance of epp_dcpAccessType representing the PERSONAL_AND_OTHER access type.
   */
  public static final org.openrtk.idl.epprtk.epp_dcpAccessType PERSONAL_AND_OTHER = new org.openrtk.idl.epprtk.epp_dcpAccessType(_PERSONAL_AND_OTHER);

  /**
   * Accessor method for the internal integer representing the access type.
   * @return The integer value of this access type
   */
  public int value ()
  {
    return __value;
  }

  /**
   * Transform an integer into a epp_dcpAccessType constant.
   * Given the integer representation of the access type, returns
   * one of the access type constants.
   * @param value The integer value for the desired access type
   */ 
  public static org.openrtk.idl.epprtk.epp_dcpAccessType from_int (int value)
  {
    if (value >= 0 && value < __size)
      return __array[value];
    else
      throw new org.omg.CORBA.BAD_PARAM ();
  }

  /**
   * For internal use only.
   * Initializes the internal access type array.
   * @param value The integer value for the desired access type
   */
  protected epp_dcpAccessType (int value)
  {
    __value = value;
    __array[__value] = this;
  }

  public String toString() { return __strings[this.value()]; }
} // class epp_dcpAccessType
