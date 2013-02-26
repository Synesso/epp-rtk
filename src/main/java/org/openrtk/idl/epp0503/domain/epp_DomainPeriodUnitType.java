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

package org.openrtk.idl.epp0503.domain;


/**
 * Class defining constant instances of period unit types for domains.</p>
 * Used in conjunction with the epp_DomainPeriod class to indicate the domain period.</p>
 * $Header: /cvsroot/epp-rtk/epp-rtk/java/src/org/openrtk/idl/epp0503/domain/epp_DomainPeriodUnitType.java,v 1.1 2003/03/21 16:18:30 tubadanm Exp $<br>
 * $Revision: 1.1 $<br>
 * $Date: 2003/03/21 16:18:30 $<br>
 * @see org.openrtk.idl.epp0503.domain.epp_DomainPeriod
 */
public class epp_DomainPeriodUnitType implements org.omg.CORBA.portable.IDLEntity
{
  private        int __value;
  private static int __size = 2;
  private static org.openrtk.idl.epp0503.domain.epp_DomainPeriodUnitType[] __array = new org.openrtk.idl.epp0503.domain.epp_DomainPeriodUnitType [__size];
  private static String[] __strings = {
                                    "y",
                                    "m"
                                    };

  /**
   * Integer value representing the YEAR period unit type.
   * @see #YEAR
   */
  public static final int _YEAR = 0;
  /**
   * Instance of epp_DomainPeriodUnitType representing the YEAR period unit type.
   * Used directly with epp_DomainPeriod.
   * @see org.openrtk.idl.epp0503.domain.epp_DomainPeriod
   */
  public static final org.openrtk.idl.epp0503.domain.epp_DomainPeriodUnitType YEAR = new org.openrtk.idl.epp0503.domain.epp_DomainPeriodUnitType(_YEAR);
  /**
   * Integer value representing the MONTH period unit type.
   * @see #MONTH
   */
  public static final int _MONTH = 1;
  /**
   * Instance of epp_DomainPeriodUnitType representing the MONTH period unit type.
   * Used directly with epp_DomainPeriod.
   * @see org.openrtk.idl.epp0503.domain.epp_DomainPeriod
   */
  public static final org.openrtk.idl.epp0503.domain.epp_DomainPeriodUnitType MONTH = new org.openrtk.idl.epp0503.domain.epp_DomainPeriodUnitType(_MONTH);

  /**
   * Accessor method for the internal integer representing the unit type of period.
   * Would compared with _YEAR, _MONTH.
   * @return The integer value of this domain registration period unit type
   */
  public int value ()
  {
    return __value;
  }

  /**
   * Transform an integer into a epp_DomainPeriodUnitType constant.
   * Given the integer representation of the period unit type, returns
   * one of the period unit type constants.
   * @param value The integer value for the desired period unit type
   */
  public static org.openrtk.idl.epp0503.domain.epp_DomainPeriodUnitType from_int (int value)
  {
    if (value >= 0 && value < __size)
      return __array[value];
    else
      throw new org.omg.CORBA.BAD_PARAM ();
  }

  /**
   * For internal use only.
   * Initializes the internal period unit type array.
   * @param value The integer value for the desired period unit type
   */
  protected epp_DomainPeriodUnitType (int value)
  {
    __value = value;
    __array[__value] = this;
  }

  public String toString() { return __strings[this.value()]; }
} // class epp_DomainPeriodUnitType
