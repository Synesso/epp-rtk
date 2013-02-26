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

package org.openrtk.idl.epprtk.contact;


/**
 * Class defining constant instances of status types for contact.</p>
 * Used in conjunction with the epp_ContactStatus class to indicate the contact status.</p>
 * $Header: /cvsroot/epp-rtk/epp-rtk/java/src/org/openrtk/idl/epprtk/contact/epp_ContactStatusType.java,v 1.2 2005/03/17 17:28:06 ewang2004 Exp $<br>
 * $Revision: 1.2 $<br>
 * $Date: 2005/03/17 17:28:06 $<br>
 * @see org.openrtk.idl.epprtk.contact.epp_ContactStatus
 */
public class epp_ContactStatusType implements org.omg.CORBA.portable.IDLEntity
{
  private        int __value;
  private static int __size = 12;
  private static org.openrtk.idl.epprtk.contact.epp_ContactStatusType[] __array = new org.openrtk.idl.epprtk.contact.epp_ContactStatusType [__size];
  private static String[] __strings = {
                                    "clientDeleteProhibited",
                                    "serverDeleteProhibited",
                                    "clientTransferProhibited",
                                    "serverTransferProhibited",
                                    "clientUpdateProhibited",
                                    "serverUpdateProhibited",
                                    "linked",
                                    "ok",
                                    "pendingCreate",
                                    "pendingDelete",
                                    "pendingTransfer",
                                    "pendingUpdate"
                                    };

  /**
   * Integer value representing the CLIENT_DELETE_PROHIBITED status type.
   * @see #CLIENT_DELETE_PROHIBITED
   */
  public static final int _CLIENT_DELETE_PROHIBITED = 0;
  /**
   * Instance of epp_ContactStatusType representing the CLIENT_DELETE_PROHIBITED status type.
   * Used directly with epp_ContactStatus.
   * @see org.openrtk.idl.epprtk.contact.epp_ContactStatus
   */
  public static final org.openrtk.idl.epprtk.contact.epp_ContactStatusType CLIENT_DELETE_PROHIBITED = new org.openrtk.idl.epprtk.contact.epp_ContactStatusType(_CLIENT_DELETE_PROHIBITED);
  /**
   * Integer value representing the SERVER_DELETE_PROHIBITED status type.
   * @see #SERVER_DELETE_PROHIBITED
   */
  public static final int _SERVER_DELETE_PROHIBITED = 1;
  /**
   * Instance of epp_ContactStatusType representing the SERVER_DELETE_PROHIBITED status type.
   * Used directly with epp_ContactStatus.
   * @see org.openrtk.idl.epprtk.contact.epp_ContactStatus
   */
  public static final org.openrtk.idl.epprtk.contact.epp_ContactStatusType SERVER_DELETE_PROHIBITED = new org.openrtk.idl.epprtk.contact.epp_ContactStatusType(_SERVER_DELETE_PROHIBITED);
  /**
   * Integer value representing the CLIENT_TRANSFER_PROHIBITED status type.
   * @see #CLIENT_TRANSFER_PROHIBITED
   */
  public static final int _CLIENT_TRANSFER_PROHIBITED = 2;
  /**
   * Instance of epp_ContactStatusType representing the CLIENT_TRANSFER_PROHIBITED status type.
   * Used directly with epp_ContactStatus.
   * @see org.openrtk.idl.epprtk.contact.epp_ContactStatus
   */
  public static final org.openrtk.idl.epprtk.contact.epp_ContactStatusType CLIENT_TRANSFER_PROHIBITED = new org.openrtk.idl.epprtk.contact.epp_ContactStatusType(_CLIENT_TRANSFER_PROHIBITED);
  /**
   * Integer value representing the SERVER_TRANSFER_PROHIBITED status type.
   * @see #SERVER_TRANSFER_PROHIBITED
   */
  public static final int _SERVER_TRANSFER_PROHIBITED = 3;
  /**
   * Instance of epp_ContactStatusType representing the SERVER_TRANSFER_PROHIBITED status type.
   * Used directly with epp_ContactStatus.
   * @see org.openrtk.idl.epprtk.contact.epp_ContactStatus
   */
  public static final org.openrtk.idl.epprtk.contact.epp_ContactStatusType SERVER_TRANSFER_PROHIBITED = new org.openrtk.idl.epprtk.contact.epp_ContactStatusType(_SERVER_TRANSFER_PROHIBITED);
  /**
   * Integer value representing the CLIENT_UPDATE_PROHIBITED status type.
   * @see #CLIENT_UPDATE_PROHIBITED
   */
  public static final int _CLIENT_UPDATE_PROHIBITED = 4;
  /**
   * Instance of epp_ContactStatusType representing the CLIENT_UPDATE_PROHIBITED status type.
   * Used directly with epp_ContactStatus.
   * @see org.openrtk.idl.epprtk.contact.epp_ContactStatus
   */
  public static final org.openrtk.idl.epprtk.contact.epp_ContactStatusType CLIENT_UPDATE_PROHIBITED = new org.openrtk.idl.epprtk.contact.epp_ContactStatusType(_CLIENT_UPDATE_PROHIBITED);
  /**
   * Integer value representing the SERVER_UPDATE_PROHIBITED status type.
   * @see #SERVER_UPDATE_PROHIBITED
   */
  public static final int _SERVER_UPDATE_PROHIBITED = 5;
  /**
   * Instance of epp_ContactStatusType representing the SERVER_UPDATE_PROHIBITED status type.
   * Used directly with epp_ContactStatus.
   * @see org.openrtk.idl.epprtk.contact.epp_ContactStatus
   */
  public static final org.openrtk.idl.epprtk.contact.epp_ContactStatusType SERVER_UPDATE_PROHIBITED = new org.openrtk.idl.epprtk.contact.epp_ContactStatusType(_SERVER_UPDATE_PROHIBITED);
  /**
   * Integer value representing the LINKED status type.
   * @see #LINKED
   */
  public static final int _LINKED = 6;
  /**
   * Instance of epp_ContactStatusType representing the LINKED status type.
   * Used directly with epp_ContactStatus.
   * @see org.openrtk.idl.epprtk.contact.epp_ContactStatus
   */
  public static final org.openrtk.idl.epprtk.contact.epp_ContactStatusType LINKED = new org.openrtk.idl.epprtk.contact.epp_ContactStatusType(_LINKED);
  /**
   * Integer value representing the OK status type.
   * @see #OK
   */
  public static final int _OK = 7;
  /**
   * Instance of epp_ContactStatusType representing the OK status type.
   * Used directly with epp_ContactStatus.
   * @see org.openrtk.idl.epprtk.contact.epp_ContactStatus
   */
  public static final org.openrtk.idl.epprtk.contact.epp_ContactStatusType OK = new org.openrtk.idl.epprtk.contact.epp_ContactStatusType(_OK);
  /**
   * Integer value representing the PENDING_CREATE status type.
   * @see #PENDING_CREATE
   */
  public static final int _PENDING_CREATE = 8;
  /**
   * Instance of epp_ContactStatusType representing the PENDING_CREATE status type.
   * Used directly with epp_ContactStatus.
   * @see org.openrtk.idl.epprtk.contact.epp_ContactStatus
   */
  public static final org.openrtk.idl.epprtk.contact.epp_ContactStatusType PENDING_CREATE = new org.openrtk.idl.epprtk.contact.epp_ContactStatusType(_PENDING_CREATE);
  /**
   * Integer value representing the PENDING_DELETE status type.
   * @see #PENDING_DELETE
   */
  public static final int _PENDING_DELETE = 9;
  /**
   * Instance of epp_ContactStatusType representing the PENDING_DELETE status type.
   * Used directly with epp_ContactStatus.
   * @see org.openrtk.idl.epprtk.contact.epp_ContactStatus
   */
  public static final org.openrtk.idl.epprtk.contact.epp_ContactStatusType PENDING_DELETE = new org.openrtk.idl.epprtk.contact.epp_ContactStatusType(_PENDING_DELETE);
  /**
   * Integer value representing the PENDING_TRANSFER status type.
   * @see #PENDING_TRANSFER
   */
  public static final int _PENDING_TRANSFER = 10;
  /**
   * Instance of epp_ContactStatusType representing the PENDING_TRANSFER status type.
   * Used directly with epp_ContactStatus.
   * @see org.openrtk.idl.epprtk.contact.epp_ContactStatus
   */
  public static final org.openrtk.idl.epprtk.contact.epp_ContactStatusType PENDING_TRANSFER = new org.openrtk.idl.epprtk.contact.epp_ContactStatusType(_PENDING_TRANSFER);
  /**
   * Integer value representing the PENDING_UPDATE status type.
   * @see #PENDING_UPDATE
   */
  public static final int _PENDING_UPDATE = 11;
  /**
   * Instance of epp_ContactStatusType representing the PENDING_UPDATE status type.
   * Used directly with epp_ContactStatus.
   * @see org.openrtk.idl.epprtk.contact.epp_ContactStatus
   */
  public static final org.openrtk.idl.epprtk.contact.epp_ContactStatusType PENDING_UPDATE = new org.openrtk.idl.epprtk.contact.epp_ContactStatusType(_PENDING_UPDATE);

  /**
   * Accessor method for the internal integer representing the type of status.
   * @return The integer value of this contact status type
   */
  public int value ()
  {
    return __value;
  }

  /**
   * Transform an integer into a epp_ContactStatusType constant.
   * Given the integer representation of the status type, returns
   * one of the status type constants.
   * @param value The integer value for the desired status type
   */
  public static org.openrtk.idl.epprtk.contact.epp_ContactStatusType from_int (int value)
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
  protected epp_ContactStatusType (int value)
  {
    __value = value;
    __array[__value] = this;
  }

  public String toString() { return __strings[this.value()]; }
} // class epp_ContactStatusType
