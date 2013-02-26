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

package org.openrtk.idl.epp0604;


/**
 * Class defining constant instances of transfer types generic for all objects.</p>
 * Six transfer status types are permitted: PENDING, CLIENT_APROVED, CLIENT_CANCELLED,
 * CLIENT_REJECTED, SERVER_APPROVED and SERVER_CANCELLED.</p>
 * $Header: /cvsroot/epp-rtk/epp-rtk/java/src/org/openrtk/idl/epp0604/epp_TransferStatusType.java,v 1.1 2003/03/21 15:54:39 tubadanm Exp $<br>
 * $Revision: 1.1 $<br>
 * $Date: 2003/03/21 15:54:39 $<br>
 * @see org.openrtk.idl.epp0604.domain.epp_DomainTransferRsp
 * @see org.openrtk.idl.epp0604.contact.epp_ContactTransferRsp
 */
public class epp_TransferStatusType implements org.omg.CORBA.portable.IDLEntity
{
  private        int __value;
  private static int __size = 6;
  private static org.openrtk.idl.epp0604.epp_TransferStatusType[] __array = new org.openrtk.idl.epp0604.epp_TransferStatusType [__size];
  private static String[] __strings = { "clientApproved", "clientCancelled", "clientRejected", "pending", "serverApproved", "serverCancelled" };

  /**
   * Integer value representing the CLIENT_APPROVED transfer status type.
   * @see #CLIENT_APPROVED
   */
  public static final int _CLIENT_APPROVED = 0;
  /**
   * Instance of epp_TransferStatusType representing the CLIENT_APPROVED status type.
   * @see org.openrtk.idl.epp0604.domain.epp_DomainTransferRsp
   */
  public static final org.openrtk.idl.epp0604.epp_TransferStatusType CLIENT_APPROVED = new org.openrtk.idl.epp0604.epp_TransferStatusType(_CLIENT_APPROVED);
  /**
   * Integer value representing the CLIENT_CANCELLED transfer status type.
   * @see #CLIENT_CANCELLED
   */
  public static final int _CLIENT_CANCELLED = 1;
  /**
   * Instance of epp_TransferStatusType representing the CLIENT_CANCELLED status type.
   * @see org.openrtk.idl.epp0604.domain.epp_DomainTransferRsp
   */
  public static final org.openrtk.idl.epp0604.epp_TransferStatusType CLIENT_CANCELLED = new org.openrtk.idl.epp0604.epp_TransferStatusType(_CLIENT_CANCELLED);
  /**
   * Integer value representing the CLIENT_REJECTED transfer status type.
   * @see #CLIENT_REJECTED
   */
  public static final int _CLIENT_REJECTED = 2;
  /**
   * Instance of epp_TransferStatusType representing the CLIENT_REJECTED status type.
   * @see org.openrtk.idl.epp0604.domain.epp_DomainTransferRsp
   */
  public static final org.openrtk.idl.epp0604.epp_TransferStatusType CLIENT_REJECTED = new org.openrtk.idl.epp0604.epp_TransferStatusType(_CLIENT_REJECTED);
  /**
   * Integer value representing the PENDING transfer status type.
   * @see #PENDING
   */
  public static final int _PENDING = 3;
  /**
   * Instance of epp_TransferStatusType representing the PENDING status type.
   * @see org.openrtk.idl.epp0604.domain.epp_DomainTransferRsp
   */
  public static final org.openrtk.idl.epp0604.epp_TransferStatusType PENDING = new org.openrtk.idl.epp0604.epp_TransferStatusType(_PENDING);
  /**
   * Integer value representing the SERVER_APPROVED transfer status type.
   * @see #SERVER_APPROVED
   */
  public static final int _SERVER_APPROVED = 4;
  /**
   * Instance of epp_TransferStatusType representing the SERVER_APPROVED status type.
   * @see org.openrtk.idl.epp0604.domain.epp_DomainTransferRsp
   */
  public static final org.openrtk.idl.epp0604.epp_TransferStatusType SERVER_APPROVED = new org.openrtk.idl.epp0604.epp_TransferStatusType(_SERVER_APPROVED);
  /**
   * Integer value representing the SERVER_CANCELLED transfer status type.
   * @see #SERVER_CANCELLED
   */
  public static final int _SERVER_CANCELLED = 5;
  /**
   * Instance of epp_TransferStatusType representing the SERVER_CANCELLED status type.
   * @see org.openrtk.idl.epp0604.domain.epp_DomainTransferRsp
   */
  public static final org.openrtk.idl.epp0604.epp_TransferStatusType SERVER_CANCELLED = new org.openrtk.idl.epp0604.epp_TransferStatusType(_SERVER_CANCELLED);

  /**
   * Accessor method for the internal integer representing the type of transfer status.
   * @return The integer value of this transfer status type
   */
  public int value ()
  {
    return __value;
  }

  /**
   * Transform an integer into a epp_TransferStatusType constant.
   * Given the integer representation of the status type, returns
   * one of the status type constants.
   * @param value The integer value for the desired status type
   */
  public static org.openrtk.idl.epp0604.epp_TransferStatusType from_int (int value)
  {
    if (value >= 0 && value < __size)
      return __array[value];
    else
      throw new org.omg.CORBA.BAD_PARAM ();
  }

  /**
   * For internal use only.
   * Initializes the internal transfer status type array.
   * @param value The integer value for the desired transfer status type
   */
  protected epp_TransferStatusType (int value)
  {
    __value = value;
    __array[__value] = this;
  }

  public String toString() { return __strings[this.value()]; }
} // class epp_TransferStatusType
