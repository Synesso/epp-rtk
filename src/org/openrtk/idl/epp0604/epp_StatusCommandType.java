/*
**
** EPP RTK Java
** Copyright (C) 2002, Tucows, Inc.
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
 * Class defining constant instances of status command types.</p>
 * The status command types permitted are: CREATE, DELETE, RENEW, TRANSFER and UPDATE.</p> 
 * $Header: /cvsroot/epp-rtk/epp-rtk/java/src/org/openrtk/idl/epp0604/epp_StatusCommandType.java,v 1.1 2003/03/21 15:54:32 tubadanm Exp $<br>
 * $Revision: 1.1 $<br>
 * $Date: 2003/03/21 15:54:32 $<br>
 * see org.openrtk.idl.epp0604.epp_StatusReq
 */
public class epp_StatusCommandType implements org.omg.CORBA.portable.IDLEntity
{
  private        int __value;
  private static int __size = 5;
  private static org.openrtk.idl.epp0604.epp_StatusCommandType[] __array = new org.openrtk.idl.epp0604.epp_StatusCommandType [__size];
  private static String[] __strings = { "create", "delete", "renew", "transfer", "update" };

  public static final int _CREATE = 0;
  public static final org.openrtk.idl.epp0604.epp_StatusCommandType CREATE = new org.openrtk.idl.epp0604.epp_StatusCommandType(_CREATE);
  public static final int _DELETE = 1;
  public static final org.openrtk.idl.epp0604.epp_StatusCommandType DELETE = new org.openrtk.idl.epp0604.epp_StatusCommandType(_DELETE);
  public static final int _RENEW = 2;
  public static final org.openrtk.idl.epp0604.epp_StatusCommandType RENEW = new org.openrtk.idl.epp0604.epp_StatusCommandType(_RENEW);
  public static final int _TRANSFER = 3;
  public static final org.openrtk.idl.epp0604.epp_StatusCommandType TRANSFER = new org.openrtk.idl.epp0604.epp_StatusCommandType(_TRANSFER);
  public static final int _UPDATE = 4;
  public static final org.openrtk.idl.epp0604.epp_StatusCommandType UPDATE = new org.openrtk.idl.epp0604.epp_StatusCommandType(_UPDATE);

  public int value ()
  {
    return __value;
  }

  public static org.openrtk.idl.epp0604.epp_StatusCommandType from_int (int value)
  {
    if (value >= 0 && value < __size)
      return __array[value];
    else
      throw new org.omg.CORBA.BAD_PARAM ();
  }

  protected epp_StatusCommandType (int value)
  {
    __value = value;
    __array[__value] = this;
  }
  
  public String toString() { return __strings[this.value()]; }
} // class epp_StatusCommandType
