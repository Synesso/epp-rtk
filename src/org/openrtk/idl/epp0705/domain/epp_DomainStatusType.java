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

package org.openrtk.idl.epp0705.domain;


/**
 * Class defining constant instances of status types for domains.</p>
 * Used in conjunction with the epp_DomainStatus class to indicate the domain status.</p>
 * $Header: /cvsroot/epp-rtk/epp-rtk/java/src/org/openrtk/idl/epp0705/domain/epp_DomainStatusType.java,v 1.1 2003/03/20 22:42:30 tubadanm Exp $<br>
 * $Revision: 1.1 $<br>
 * $Date: 2003/03/20 22:42:30 $<br>
 * @see org.openrtk.idl.epp0705.domain.epp_DomainStatus
 */
public class epp_DomainStatusType implements org.omg.CORBA.portable.IDLEntity
{
  private        int __value;
  private static int __size = 17;
  private static org.openrtk.idl.epp0705.domain.epp_DomainStatusType[] __array = new org.openrtk.idl.epp0705.domain.epp_DomainStatusType [__size];
  private static String[] __strings = {
                                    "clientDeleteProhibited",
                                    "clientHold",
                                    "clientRenewProhibited",
                                    "clientTransferProhibited",
                                    "clientUpdateProhibited",
                                    "inactive",
                                    "ok",
                                    "pendingCreate",
                                    "pendingDelete",
                                    "pendingRenew",
                                    "pendingTransfer",
                                    "pendingUpdate",
                                    "serverDeleteProhibited",
                                    "serverHold",
                                    "serverRenewProhibited",
                                    "serverTransferProhibited",
                                    "serverUpdateProhibited"
                                    };

  /**
   * Integer value representing the CLIENT_DELETE_PROHIBITED status type.
   * @see #CLIENT_DELETE_PROHIBITED
   */
  public static final int _CLIENT_DELETE_PROHIBITED = 0;
  /**
   * Instance of epp_DomainStatusType representing the CLIENT_DELETE_PROHIBITED status type.
   * Used directly with epp_DomainStatus.
   * @see org.openrtk.idl.epp0705.domain.epp_DomainStatus
   */
  public static final org.openrtk.idl.epp0705.domain.epp_DomainStatusType CLIENT_DELETE_PROHIBITED = new org.openrtk.idl.epp0705.domain.epp_DomainStatusType(_CLIENT_DELETE_PROHIBITED);
  /**
   * Integer value representing the CLIENT_HOLD status type.
   * @see #CLIENT_HOLD
   */
  public static final int _CLIENT_HOLD = 1;
  /**
   * Instance of epp_DomainStatusType representing the CLIENT_HOLD status type.
   * Used directly with epp_DomainStatus.
   * @see org.openrtk.idl.epp0705.domain.epp_DomainStatus
   */
  public static final org.openrtk.idl.epp0705.domain.epp_DomainStatusType CLIENT_HOLD = new org.openrtk.idl.epp0705.domain.epp_DomainStatusType(_CLIENT_HOLD);
  /**
   * Integer value representing the CLIENT_RENEW_PROHIBITED status type.
   * @see #CLIENT_RENEW_PROHIBITED
   */
  public static final int _CLIENT_RENEW_PROHIBITED = 2;
  /**
   * Instance of epp_DomainStatusType representing the CLIENT_RENEW_PROHIBITED status type.
   * Used directly with epp_DomainStatus.
   * @see org.openrtk.idl.epp0705.domain.epp_DomainStatus
   */
  public static final org.openrtk.idl.epp0705.domain.epp_DomainStatusType CLIENT_RENEW_PROHIBITED = new org.openrtk.idl.epp0705.domain.epp_DomainStatusType(_CLIENT_RENEW_PROHIBITED);
  /**
   * Integer value representing the CLIENT_TRANSFER_PROHIBITED status type.
   * @see #CLIENT_TRANSFER_PROHIBITED
   */
  public static final int _CLIENT_TRANSFER_PROHIBITED = 3;
  /**
   * Instance of epp_DomainStatusType representing the CLIENT_TRANSFER_PROHIBITED status type.
   * Used directly with epp_DomainStatus.
   * @see org.openrtk.idl.epp0705.domain.epp_DomainStatus
   */
  public static final org.openrtk.idl.epp0705.domain.epp_DomainStatusType CLIENT_TRANSFER_PROHIBITED = new org.openrtk.idl.epp0705.domain.epp_DomainStatusType(_CLIENT_TRANSFER_PROHIBITED);
  /**
   * Integer value representing the CLIENT_UPDATE_PROHIBITED status type.
   * @see #CLIENT_UPDATE_PROHIBITED
   */
  public static final int _CLIENT_UPDATE_PROHIBITED = 4;
  /**
   * Instance of epp_DomainStatusType representing the CLIENT_UPDATE_PROHIBITED status type.
   * Used directly with epp_DomainStatus.
   * @see org.openrtk.idl.epp0705.domain.epp_DomainStatus
   */
  public static final org.openrtk.idl.epp0705.domain.epp_DomainStatusType CLIENT_UPDATE_PROHIBITED = new org.openrtk.idl.epp0705.domain.epp_DomainStatusType(_CLIENT_UPDATE_PROHIBITED);
  /**
   * Integer value representing the INACTIVE status type.
   * @see #INACTIVE
   */
  public static final int _INACTIVE = 5;
  /**
   * Instance of epp_DomainStatusType representing the INACTIVE status type.
   * Used directly with epp_DomainStatus.
   * @see org.openrtk.idl.epp0705.domain.epp_DomainStatus
   */
  public static final org.openrtk.idl.epp0705.domain.epp_DomainStatusType INACTIVE = new org.openrtk.idl.epp0705.domain.epp_DomainStatusType(_INACTIVE);
  /**
   * Integer value representing the OK status type.
   * @see #OK
   */
  public static final int _OK = 6;
  /**
   * Instance of epp_DomainStatusType representing the OK status type.
   * Used directly with epp_DomainStatus.
   * @see org.openrtk.idl.epp0705.domain.epp_DomainStatus
   */
  public static final org.openrtk.idl.epp0705.domain.epp_DomainStatusType OK = new org.openrtk.idl.epp0705.domain.epp_DomainStatusType(_OK);
  /**
   * Integer value representing the PENDING_CREATE status type.
   * @see #PENDING_CREATE
   */
  public static final int _PENDING_CREATE = 7;
  /**
   * Instance of epp_DomainStatusType representing the PENDING_CREATE status type.
   * Used directly with epp_DomainStatus.
   * @see org.openrtk.idl.epp0705.domain.epp_DomainStatus
   */
  public static final org.openrtk.idl.epp0705.domain.epp_DomainStatusType PENDING_CREATE = new org.openrtk.idl.epp0705.domain.epp_DomainStatusType(_PENDING_CREATE);
  /**
   * Integer value representing the PENDING_DELETE status type.
   * @see #PENDING_DELETE
   */
  public static final int _PENDING_DELETE = 8;
  /**
   * Instance of epp_DomainStatusType representing the PENDING_DELETE status type.
   * Used directly with epp_DomainStatus.
   * @see org.openrtk.idl.epp0705.domain.epp_DomainStatus
   */
  public static final org.openrtk.idl.epp0705.domain.epp_DomainStatusType PENDING_DELETE = new org.openrtk.idl.epp0705.domain.epp_DomainStatusType(_PENDING_DELETE);
  /**
   * Integer value representing the PENDING_RENEW status type.
   * @see #PENDING_RENEW
   */
  public static final int _PENDING_RENEW = 9;
  /**
   * Instance of epp_DomainStatusType representing the PENDING_RENEW status type.
   * Used directly with epp_DomainStatus.
   * @see org.openrtk.idl.epp0705.domain.epp_DomainStatus
   */
  public static final org.openrtk.idl.epp0705.domain.epp_DomainStatusType PENDING_RENEW = new org.openrtk.idl.epp0705.domain.epp_DomainStatusType(_PENDING_RENEW);
  /**
   * Integer value representing the PENDING_TRANSFER status type.
   * @see #PENDING_TRANSFER
   */
  public static final int _PENDING_TRANSFER = 10;
  /**
   * Instance of epp_DomainStatusType representing the PENDING_TRANSFER status type.
   * Used directly with epp_DomainStatus.
   * @see org.openrtk.idl.epp0705.domain.epp_DomainStatus
   */
  public static final org.openrtk.idl.epp0705.domain.epp_DomainStatusType PENDING_TRANSFER = new org.openrtk.idl.epp0705.domain.epp_DomainStatusType(_PENDING_TRANSFER);
  /**
   * Integer value representing the PENDING_UPDATE status type.
   * @see #PENDING_UPDATE
   */
  public static final int _PENDING_UPDATE = 11;
  /**
   * Instance of epp_DomainStatusType representing the PENDING_UPDATE status type.
   * Used directly with epp_DomainStatus.
   * @see org.openrtk.idl.epp0705.domain.epp_DomainStatus
   */
  public static final org.openrtk.idl.epp0705.domain.epp_DomainStatusType PENDING_UPDATE = new org.openrtk.idl.epp0705.domain.epp_DomainStatusType(_PENDING_UPDATE);
  /**
   * Integer value representing the SERVER_DELETE_PROHIBITED status type.
   * @see #SERVER_DELETE_PROHIBITED
   */
  public static final int _SERVER_DELETE_PROHIBITED = 12;
  /**
   * Instance of epp_DomainStatusType representing the SERVER_DELETE_PROHIBITED status type.
   * Used directly with epp_DomainStatus.
   * @see org.openrtk.idl.epp0705.domain.epp_DomainStatus
   */
  public static final org.openrtk.idl.epp0705.domain.epp_DomainStatusType SERVER_DELETE_PROHIBITED = new org.openrtk.idl.epp0705.domain.epp_DomainStatusType(_SERVER_DELETE_PROHIBITED);
  /**
   * Integer value representing the SERVER_HOLD status type.
   * @see #SERVER_HOLD
   */
  public static final int _SERVER_HOLD = 13;
  /**
   * Instance of epp_DomainStatusType representing the SERVER_HOLD status type.
   * Used directly with epp_DomainStatus.
   * @see org.openrtk.idl.epp0705.domain.epp_DomainStatus
   */
  public static final org.openrtk.idl.epp0705.domain.epp_DomainStatusType SERVER_HOLD = new org.openrtk.idl.epp0705.domain.epp_DomainStatusType(_SERVER_HOLD);
  /**
   * Integer value representing the SERVER_RENEW_PROHIBITED status type.
   * @see #SERVER_RENEW_PROHIBITED
   */
  public static final int _SERVER_RENEW_PROHIBITED = 14;
  /**
   * Instance of epp_DomainStatusType representing the SERVER_RENEW_PROHIBITED status type.
   * Used directly with epp_DomainStatus.
   * @see org.openrtk.idl.epp0705.domain.epp_DomainStatus
   */
  public static final org.openrtk.idl.epp0705.domain.epp_DomainStatusType SERVER_RENEW_PROHIBITED = new org.openrtk.idl.epp0705.domain.epp_DomainStatusType(_SERVER_RENEW_PROHIBITED);
  /**
   * Integer value representing the SERVER_TRANSFER_PROHIBITED status type.
   * @see #SERVER_TRANSFER_PROHIBITED
   */
  public static final int _SERVER_TRANSFER_PROHIBITED = 15;
  /**
   * Instance of epp_DomainStatusType representing the SERVER_TRANSFER_PROHIBITED status type.
   * Used directly with epp_DomainStatus.
   * @see org.openrtk.idl.epp0705.domain.epp_DomainStatus
   */
  public static final org.openrtk.idl.epp0705.domain.epp_DomainStatusType SERVER_TRANSFER_PROHIBITED = new org.openrtk.idl.epp0705.domain.epp_DomainStatusType(_SERVER_TRANSFER_PROHIBITED);
  /**
   * Integer value representing the SERVER_UPDATE_PROHIBITED status type.
   * @see #SERVER_UPDATE_PROHIBITED
   */
  public static final int _SERVER_UPDATE_PROHIBITED = 16;
  /**
   * Instance of epp_DomainStatusType representing the SERVER_UPDATE_PROHIBITED status type.
   * Used directly with epp_DomainStatus.
   * @see org.openrtk.idl.epp0705.domain.epp_DomainStatus
   */
  public static final org.openrtk.idl.epp0705.domain.epp_DomainStatusType SERVER_UPDATE_PROHIBITED = new org.openrtk.idl.epp0705.domain.epp_DomainStatusType(_SERVER_UPDATE_PROHIBITED);

  /**
   * Accessor method for the internal integer representing the type of status.
   * @return The integer value of this domain status type
   */
  public int value ()
  {
    return __value;
  }

  /**
   * Transform an integer into a epp_DomainStatusType constant.
   * Given the integer representation of the status type, returns
   * one of the status type constants.
   * @param value The integer value for the desired status type
   */
  public static org.openrtk.idl.epp0705.domain.epp_DomainStatusType from_int (int value)
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
  protected epp_DomainStatusType (int value)
  {
    __value = value;
    __array[__value] = this;
  }

  public String toString() { return __strings[this.value()]; }
} // class epp_DomainStatusType
