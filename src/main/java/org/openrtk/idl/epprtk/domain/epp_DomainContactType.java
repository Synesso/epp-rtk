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

package org.openrtk.idl.epprtk.domain;


/**
 * Class defining constant instances of contact types for domains.</p>
 * Used in conjunction with the epp_DomainContact class to indicate the
 * type of the domain contact: ADMIN, BILLING, TECH.</p>
 * Note that the registrant "contact" is not an epp_DomainContact.  The
 * registrant's contact id is associated with a domain directly as a String
 * in such classes as epp_DomainCreateReq and epp_DomainUpdateChange.</p>
 * $Header: /cvsroot/epp-rtk/epp-rtk/java/src/org/openrtk/idl/epprtk/domain/epp_DomainContactType.java,v 1.1 2004/12/07 15:27:50 ewang2004 Exp $<br>
 * $Revision: 1.1 $<br>
 * $Date: 2004/12/07 15:27:50 $<br>
 * @see org.openrtk.idl.epprtk.domain.epp_DomainContact
 */

public class epp_DomainContactType implements org.omg.CORBA.portable.IDLEntity
{
  private        int __value;
  private static int __size = 3;
  private static org.openrtk.idl.epprtk.domain.epp_DomainContactType[] __array = new org.openrtk.idl.epprtk.domain.epp_DomainContactType [__size];
  private static String[] __strings = {
                                    "admin",
                                    "billing",
                                    "tech"
                                    };

  /**
   * Integer value representing the ADMIN contact type.
   * @see #ADMIN
   */
  public static final int _ADMIN = 0;
  /**
   * Contact instance of epp_DomainContactType representing the ADMIN contact type.
   * Used directly with epp_DomainContact.
   * @see org.openrtk.idl.epprtk.domain.epp_DomainContact
   */
  public static final org.openrtk.idl.epprtk.domain.epp_DomainContactType ADMIN = new org.openrtk.idl.epprtk.domain.epp_DomainContactType(_ADMIN);
  /**
   * Integer value representing the BILLING contact type.
   * @see #BILLING
   */
  public static final int _BILLING = 1;
  /**
   * Contact instance of epp_DomainContactType representing the BILLING contact type.
   * Used directly with epp_DomainContact.
   * @see org.openrtk.idl.epprtk.domain.epp_DomainContact
   */
  public static final org.openrtk.idl.epprtk.domain.epp_DomainContactType BILLING = new org.openrtk.idl.epprtk.domain.epp_DomainContactType(_BILLING);
  /**
   * Integer value representing the TECH contact type.
   * @see #TECH
   */
  public static final int _TECH = 2;
  /**
   * Contact instance of epp_DomainContactType representing the TECH contact type.
   * Used directly with epp_DomainContact.
   * @see org.openrtk.idl.epprtk.domain.epp_DomainContact
   */
  public static final org.openrtk.idl.epprtk.domain.epp_DomainContactType TECH = new org.openrtk.idl.epprtk.domain.epp_DomainContactType(_TECH);

  /**
   * Accessor method for the internal integer represent the type of contact.
   * Would compared with _ADMIN, _BILLING, _TECH.
   * @return The integer value of this domain contact type
   */
  public int value ()
  {
    return __value;
  }

  /**
   * Transform an integer into a epp_DomainContactType constant.
   * Given the integer representation of the contact type, returns
   * one of the contact type constants.
   * @param value The integer value for the desired contact type
   */
  public static org.openrtk.idl.epprtk.domain.epp_DomainContactType from_int (int value)
  {
    if (value >= 0 && value < __size)
      return __array[value];
    else
      throw new org.omg.CORBA.BAD_PARAM ();
  }

  /**
   * For internal use only.
   * Initializes the internal contact type array.
   * @param value The integer value for the desired contact type
   */
  protected epp_DomainContactType (int value)
  {
    __value = value;
    __array[__value] = this;
  }

  public String toString() { return __strings[this.value()]; }
} // class epp_DomainContactType
