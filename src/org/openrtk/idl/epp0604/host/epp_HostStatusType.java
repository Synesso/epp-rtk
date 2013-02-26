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

package org.openrtk.idl.epp0604.host;


/**
 * Class defining constant instances of status types for host.</p>
 * Used in conjunction with the epp_HostStatus class to indicate the host status.</p>
 * $Header: /cvsroot/epp-rtk/epp-rtk/java/src/org/openrtk/idl/epp0604/host/epp_HostStatusType.java,v 1.1 2003/03/21 15:55:24 tubadanm Exp $<br>
 * $Revision: 1.1 $<br>
 * $Date: 2003/03/21 15:55:24 $<br>
 * @see org.openrtk.idl.epp0604.host.epp_HostStatus
 */
public class epp_HostStatusType implements org.omg.CORBA.portable.IDLEntity
{
  private        int __value;
  private static int __size = 8;
  private static org.openrtk.idl.epp0604.host.epp_HostStatusType[] __array = new org.openrtk.idl.epp0604.host.epp_HostStatusType [__size];
  private static String[] __strings = {
                                    "clientDeleteProhibited",
                                    "clientUpdateProhibited",
                                    "linked",
                                    "ok",
                                    "pendingDelete",
                                    "pendingTransfer",
                                    "serverDeleteProhibited",
                                    "serverUpdateProhibited"
                                    };

  /**
   * Integer value representing the CLIENT_DELETE_PROHIBITED status type.
   * @see #CLIENT_DELETE_PROHIBITED
   */
  public static final int _CLIENT_DELETE_PROHIBITED = 0;
  /**
   * Instance of epp_HostStatusType representing the CLIENT_DELETE_PROHIBITED status type.
   * Used directly with epp_HostStatus.
   * @see org.openrtk.idl.epp0604.host.epp_HostStatus
   */
  public static final org.openrtk.idl.epp0604.host.epp_HostStatusType CLIENT_DELETE_PROHIBITED = new org.openrtk.idl.epp0604.host.epp_HostStatusType(_CLIENT_DELETE_PROHIBITED);
  /**
   * Integer value representing the CLIENT_UPDATE_PROHIBITED status type.
   * @see #CLIENT_UPDATE_PROHIBITED
   */
  public static final int _CLIENT_UPDATE_PROHIBITED = 1;
  /**
   * Instance of epp_HostStatusType representing the CLIENT_UPDATE_PROHIBITED status type.
   * Used directly with epp_HostStatus.
   * @see org.openrtk.idl.epp0604.host.epp_HostStatus
   */
  public static final org.openrtk.idl.epp0604.host.epp_HostStatusType CLIENT_UPDATE_PROHIBITED = new org.openrtk.idl.epp0604.host.epp_HostStatusType(_CLIENT_UPDATE_PROHIBITED);
  /**
   * Integer value representing the LINKED status type.
   * @see #LINKED
   */
  public static final int _LINKED = 2;
  /**
   * Instance of epp_HostStatusType representing the LINKED status type.
   * Used directly with epp_HostStatus.
   * @see org.openrtk.idl.epp0604.host.epp_HostStatus
   */
  public static final org.openrtk.idl.epp0604.host.epp_HostStatusType LINKED = new org.openrtk.idl.epp0604.host.epp_HostStatusType(_LINKED);
  /**
   * Integer value representing the OK status type.
   * @see #OK
   */
  public static final int _OK = 3;
  /**
   * Instance of epp_HostStatusType representing the OK status type.
   * Used directly with epp_HostStatus.
   * @see org.openrtk.idl.epp0604.host.epp_HostStatus
   */
  public static final org.openrtk.idl.epp0604.host.epp_HostStatusType OK = new org.openrtk.idl.epp0604.host.epp_HostStatusType(_OK);
  /**
   * Integer value representing the PENDING_DELETE status type.
   * @see #PENDING_DELETE
   */
  public static final int _PENDING_DELETE = 4;
  /**
   * Instance of epp_HostStatusType representing the PENDING_DELETE status type.
   * Used directly with epp_HostStatus.
   * @see org.openrtk.idl.epp0604.host.epp_HostStatus
   */
  public static final org.openrtk.idl.epp0604.host.epp_HostStatusType PENDING_DELETE = new org.openrtk.idl.epp0604.host.epp_HostStatusType(_PENDING_DELETE);
  /**
   * Integer value representing the PENDING_TRANSFER status type.
   * @see #PENDING_TRANSFER
   */
  public static final int _PENDING_TRANSFER = 5;
  /**
   * Instance of epp_HostStatusType representing the PENDING_TRANSFER status type.
   * Used directly with epp_HostStatus.
   * @see org.openrtk.idl.epp0604.host.epp_HostStatus
   */
  public static final org.openrtk.idl.epp0604.host.epp_HostStatusType PENDING_TRANSFER = new org.openrtk.idl.epp0604.host.epp_HostStatusType(_PENDING_TRANSFER);
  /**
   * Integer value representing the SERVER_DELETE_PROHIBITED status type.
   * @see #SERVER_DELETE_PROHIBITED
   */
  public static final int _SERVER_DELETE_PROHIBITED = 6;
  /**
   * Instance of epp_HostStatusType representing the SERVER_DELETE_PROHIBITED status type.
   * Used directly with epp_HostStatus.
   * @see org.openrtk.idl.epp0604.host.epp_HostStatus
   */
  public static final org.openrtk.idl.epp0604.host.epp_HostStatusType SERVER_DELETE_PROHIBITED = new org.openrtk.idl.epp0604.host.epp_HostStatusType(_SERVER_DELETE_PROHIBITED);
  /**
   * Integer value representing the SERVER_UPDATE_PROHIBITED status type.
   * @see #SERVER_UPDATE_PROHIBITED
   */
  public static final int _SERVER_UPDATE_PROHIBITED = 7;
  /**
   * Instance of epp_HostStatusType representing the SERVER_UPDATE_PROHIBITED status type.
   * Used directly with epp_HostStatus.
   * @see org.openrtk.idl.epp0604.host.epp_HostStatus
   */
  public static final org.openrtk.idl.epp0604.host.epp_HostStatusType SERVER_UPDATE_PROHIBITED = new org.openrtk.idl.epp0604.host.epp_HostStatusType(_SERVER_UPDATE_PROHIBITED);

  /**
   * Accessor method for the internal integer representing the type of status.
   * @return The integer value of this host status type
   */
  public int value ()
  {
    return __value;
  }

  /**
   * Transform an integer into a epp_HostStatusType constant.
   * Given the integer representation of the status type, returns
   * one of the status type constants.
   * @param value The integer value for the desired status type
   */
  public static org.openrtk.idl.epp0604.host.epp_HostStatusType from_int (int value)
  {
    if (value >= 0 && value < __size)
      return __array[value];
    else
      throw new org.omg.CORBA.BAD_PARAM ();
  }

  /**
   * For internal use only.
   * Initializes the internal status type array.
   * @param value The integer value for the desired status type
   */
  protected epp_HostStatusType (int value)
  {
    __value = value;
    __array[__value] = this;
  }

  public String toString() { return __strings[this.value()]; }
} // class epp_HostStatusType
