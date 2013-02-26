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

package org.openrtk.idl.epp0705;


/**
 * Class defining constant instances of poll operation types.</p>
 * Two poll operation types permitted are: REQ (request) and ACK (acknowledge).</p> 
 * $Header: /cvsroot/epp-rtk/epp-rtk/java/src/org/openrtk/idl/epp0705/epp_PollOpType.java,v 1.1 2003/03/20 22:42:19 tubadanm Exp $<br>
 * $Revision: 1.1 $<br>
 * $Date: 2003/03/20 22:42:19 $<br>
 * @see org.openrtk.idl.epp0705.epp_PollReq
 */
public class epp_PollOpType implements org.omg.CORBA.portable.IDLEntity
{
  private        int __value;
  private static int __size = 2;
  private static org.openrtk.idl.epp0705.epp_PollOpType[] __array = new org.openrtk.idl.epp0705.epp_PollOpType [__size];
  private static String[] __strings = { "ack", "req" };

  /**
   * Integer value representing the ACK poll operation type.
   * @see #ACK
   */
  public static final int _ACK = 0;
  /**
   * Instance of epp_PollOpType representing the ACK poll operation type.
   * Used directly with epp_PollReq.
   * @see org.openrtk.idl.epp0705.epp_PollReq
   */
  public static final org.openrtk.idl.epp0705.epp_PollOpType ACK = new org.openrtk.idl.epp0705.epp_PollOpType(_ACK);
  /**
   * Integer value representing the REQ poll operation type.
   * @see #REQ
   */
  public static final int _REQ = 1;
  /**
   * Instance of epp_PollOpType representing the REQ poll operation type.
   * Used directly with epp_PollReq.
   * @see org.openrtk.idl.epp0705.epp_PollReq
   */
  public static final org.openrtk.idl.epp0705.epp_PollOpType REQ = new org.openrtk.idl.epp0705.epp_PollOpType(_REQ);

  /**
   * Accessor method for the internal integer representing the poll operation type.
   * @return The integer value of this poll operation type
   */
  public int value ()
  {
    return __value;
  }

  /**
   * Transform an integer into a epp_PollOpType constant.
   * Given the integer representation of the operation type, returns
   * one of the operation type constants.
   * @param value The integer value for the desired operation type
   */
  public static org.openrtk.idl.epp0705.epp_PollOpType from_int (int value)
  {
    if (value >= 0 && value < __size)
      return __array[value];
    else
      throw new org.omg.CORBA.BAD_PARAM ();
  }

  /**
   * For internal use only.
   * Initializes the internal poll operation type array.
   * @param value The integer value for the desired poll operation type
   */
  protected epp_PollOpType (int value)
  {
    __value = value;
    __array[__value] = this;
  }
  public String toString() { return __strings[this.value()]; }
} // class epp_PollOpType
