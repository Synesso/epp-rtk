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

package org.openrtk.idl.epp0402;


/**
 * Class defining constant instances of transfer operation types generic for all objects.</p>
 * Used in conjunction with the epp_TransferRequest class to indicate the object transfer.</p>
 * Five transfer operation types supported: APPROVE, CANCEL, QUERY, REJECT and REQUEST.</p>
 * $Header: /cvsroot/epp-rtk/epp-rtk/java/src/org/openrtk/idl/epp0402/epp_TransferOpType.java,v 1.1 2003/03/21 16:35:39 tubadanm Exp $<br>
 * $Revision: 1.1 $<br>
 * $Date: 2003/03/21 16:35:39 $<br>
 * @see org.openrtk.idl.epp0402.epp_TransferRequest
 */
public class epp_TransferOpType implements org.omg.CORBA.portable.IDLEntity
{
  private        int __value;
  private static int __size = 5;
  private static org.openrtk.idl.epp0402.epp_TransferOpType[] __array = new org.openrtk.idl.epp0402.epp_TransferOpType [__size];
  private static String[] __strings = { 
                                    "approve",
                                    "cancel",
                                    "query",
                                    "reject",
                                    "request"
                                    };

  /**
   * Integer value representing the APPROVE transfer operation type.
   * @see #APPROVE
   */
  public static final int _APPROVE = 0;
  /**
   * Instance of epp_TransferOpType representing the APPROVE transfer operation type.
   * Used directly with epp_TransferRequest.
   * @see org.openrtk.idl.epp0402.epp_TransferRequest
   */
  public static final org.openrtk.idl.epp0402.epp_TransferOpType APPROVE = new org.openrtk.idl.epp0402.epp_TransferOpType(_APPROVE);
  /**
   * Integer value representing the CANCEL transfer operation type.
   * @see #CANCEL
   */
  public static final int _CANCEL = 1;
  /**
   * Instance of epp_TransferOpType representing the CANCEL transfer operation type.
   * Used directly with epp_TransferRequest.
   * @see org.openrtk.idl.epp0402.epp_TransferRequest
   */
  public static final org.openrtk.idl.epp0402.epp_TransferOpType CANCEL = new org.openrtk.idl.epp0402.epp_TransferOpType(_CANCEL);
  /**
   * Integer value representing the QUERY transfer operation type.
   * @see #QUERY
   */
  public static final int _QUERY = 2;
  /**
   * Instance of epp_TransferOpType representing the QUERY transfer operation type.
   * Used directly with epp_TransferRequest.
   * @see org.openrtk.idl.epp0402.epp_TransferRequest
   */
  public static final org.openrtk.idl.epp0402.epp_TransferOpType QUERY = new org.openrtk.idl.epp0402.epp_TransferOpType(_QUERY);
  /**
   * Integer value representing the REJECT transfer operation type.
   * @see #REJECT
   */
  public static final int _REJECT = 3;
  /**
   * Instance of epp_TransferOpType representing the REJECT transfer operation type.
   * Used directly with epp_TransferRequest.
   * @see org.openrtk.idl.epp0402.epp_TransferRequest
   */
  public static final org.openrtk.idl.epp0402.epp_TransferOpType REJECT = new org.openrtk.idl.epp0402.epp_TransferOpType(_REJECT);
  /**
   * Integer value representing the REQUEST transfer operation type.
   * @see #REQUEST
   */
  public static final int _REQUEST = 4;
  /**
   * Instance of epp_TransferOpType representing the REQUEST transfer operation type.
   * Used directly with epp_TransferRequest.
   * @see org.openrtk.idl.epp0402.epp_TransferRequest
   */
  public static final org.openrtk.idl.epp0402.epp_TransferOpType REQUEST = new org.openrtk.idl.epp0402.epp_TransferOpType(_REQUEST);

  /**
   * Accessor method for the internal integer representing the transfer operation type.
   * @return The integer value of this transfer operation type
   */
  public int value ()
  {
    return __value;
  }

  /**
   * Transform an integer into a epp_TransferOpType constant.
   * Given the integer representation of the operation type, returns
   * one of the operation type constants.
   * @param value The integer value for the desired operation type
   */
  public static org.openrtk.idl.epp0402.epp_TransferOpType from_int (int value)
  {
    if (value >= 0 && value < __size)
      return __array[value];
    else
      throw new org.omg.CORBA.BAD_PARAM ();
  }

  /**
   * For internal use only.
   * Initializes the internal transfer operation type array.
   * @param value The integer value for the desired transfer operation type
   */
  protected epp_TransferOpType (int value)
  {
    __value = value;
    __array[__value] = this;
  }

  public String toString() { return __strings[this.value()]; }
} // class epp_TransferOpType
