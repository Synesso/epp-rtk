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

package org.openrtk.idl.epprtk.domain;


/**
 * Class defining constant instances of hosts types for domains.</p>
 * Used in conjunction with the epp_DomainInfoReq class to indicate the
 * type of the domain hosts to return: ALL, DEL, NONE, or SUB.</p>
 * The ALL type returns all hosts types.  The DEL type returns only
 * delegating hosts for the domain.  The NONE returns no hosts.  The SUB
 * returns only subordinant hosts in the Info response.</p>
 * $Header: /cvsroot/epp-rtk/epp-rtk/java/src/org/openrtk/idl/epprtk/domain/epp_DomainHostsType.java,v 1.1 2004/12/07 15:27:50 ewang2004 Exp $<br>
 * $Revision: 1.1 $<br>
 * $Date: 2004/12/07 15:27:50 $<br>
 * @see org.openrtk.idl.epprtk.domain.epp_DomainInfoReq
 */

public class epp_DomainHostsType implements org.omg.CORBA.portable.IDLEntity
{
  private        int __value;
  private static int __size = 4;
  private static org.openrtk.idl.epprtk.domain.epp_DomainHostsType[] __array = new org.openrtk.idl.epprtk.domain.epp_DomainHostsType [__size];
  private static final String __strings[] = {"all", "del", "none", "sub"};
  
  /**
   * Integer value representing the ALL host type.
   * @see #ALL
   */
  public static final int _ALL = 0;
  /**
   * Host instance of epp_DomainHostsType representing the ALL host type.
   * Used directly with epp_DomainInfoReq.
   * @see org.openrtk.idl.epprtk.domain.epp_DomainInfoReq
   */
  public static final org.openrtk.idl.epprtk.domain.epp_DomainHostsType ALL = new org.openrtk.idl.epprtk.domain.epp_DomainHostsType(_ALL);
  /**
   * Integer value representing the DEL host type.
   * @see #DEL
   */
  public static final int _DEL = 1;
  /**
   * Host instance of epp_DomainHostsType representing the DEL host type.
   * Used directly with epp_DomainInfoReq.
   * @see org.openrtk.idl.epprtk.domain.epp_DomainInfoReq
   */
  public static final org.openrtk.idl.epprtk.domain.epp_DomainHostsType DEL = new org.openrtk.idl.epprtk.domain.epp_DomainHostsType(_DEL);
  /**
   * Integer value representing the NONE host type.
   * @see #NONE
   */
  public static final int _NONE = 2;
  /**
   * Host instance of epp_DomainHostsType representing the NONE host type.
   * Used directly with epp_DomainInfoReq.
   * @see org.openrtk.idl.epprtk.domain.epp_DomainInfoReq
   */
  public static final org.openrtk.idl.epprtk.domain.epp_DomainHostsType NONE = new org.openrtk.idl.epprtk.domain.epp_DomainHostsType(_NONE);
  /**
   * Integer value representing the SUB host type.
   * @see #SUB
   */
  public static final int _SUB = 3;
  /**
   * Host instance of epp_DomainHostsType representing the SUB host type.
   * Used directly with epp_DomainInfoReq.
   * @see org.openrtk.idl.epprtk.domain.epp_DomainInfoReq
   */
  public static final org.openrtk.idl.epprtk.domain.epp_DomainHostsType SUB = new org.openrtk.idl.epprtk.domain.epp_DomainHostsType(_SUB);

  /**
   * Accessor method for the internal integer represent the type of host.
   * Would compared with _ADMIN, _BILLING, _TECH.
   * @return The integer value of this domain contact type
   */
  public int value ()
  {
    return __value;
  }

  /**
   * Transform an integer into a epp_DomainHostsType constant.
   * Given the integer representation of the host type, returns
   * one of the host type constants.
   * @param value The integer value for the desired host type
   */
  public static org.openrtk.idl.epprtk.domain.epp_DomainHostsType from_int (int value)
  {
    if (value >= 0 && value < __size)
      return __array[value];
    else
      throw new org.omg.CORBA.BAD_PARAM ();
  }

  protected epp_DomainHostsType (int value)
  {
    __value = value;
    __array[__value] = this;
  }

  /**
   * Converts this class into a string.
   * Typically used to view the object in debug output.
   * @return The string representation of this object instance
   */
  public String toString() { return __strings[this.value()]; }
} // class epp_DomainHostsType
