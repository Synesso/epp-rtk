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

package org.openrtk.idl.epp0503;


/**
 * Class that contains the generic, non-object specific EPP result data.</p>
 * Used in epp_Result.  Contains data for the results to the Status command.</p>
 * $Header: /cvsroot/epp-rtk/epp-rtk/java/src/org/openrtk/idl/epp0503/epp_ResultData.java,v 1.1 2003/03/21 16:18:26 tubadanm Exp $<br>
 * $Revision: 1.1 $<br>
 * $Date: 2003/03/21 16:18:26 $<br>
 * @see org.openrtk.idl.epp0503.epp_Result
 * @see org.openrtk.idl.epp0503.epp_Status
 */
public class epp_ResultData implements org.omg.CORBA.portable.IDLEntity
{
  /**
   * Placeholder for the result data to the EPP Status command
   * @see #setStatus(org.openrtk.idl.epp0503.epp_StatusResultData)
   * @see #getStatus()
   */
  public org.openrtk.idl.epp0503.epp_StatusResultData m_status = null;

  /**
   * Empty constructor
   */
  public epp_ResultData ()
  {
  } // ctor

  /**
   * The constructor with initializing variables.
   * @param _m_status The result data for the status command
   */
  public epp_ResultData (org.openrtk.idl.epp0503.epp_StatusResultData _m_status)
  {
    m_status = _m_status;
  } // ctor

  /**
   * Accessor method for the status result data
   * @param value The status result data
   * @see #m_status
   */
  public void setStatus(org.openrtk.idl.epp0503.epp_StatusResultData value) { m_status = value; }
  /**
   * Accessor method for the status result data
   * @return The status result data
   * @see #m_status
   */
  public org.openrtk.idl.epp0503.epp_StatusResultData getStatus() { return m_status; }

  /**
   * Converts this class into a string.
   * Typically used to view the object in debug output.
   * @return The string representation of this object instance
   */
  public String toString() { return this.getClass().getName() + ": { m_status ["+m_status+"] }"; }

} // class epp_ResultData
