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

package org.openrtk.idl.epp0705;


/**
 * Class that acts as a container for any exception thrown if a communication error
 * occurs with the EPP server or if the EPP server returns an error code.</p>
 * An EPP response code is considered an error code if the numeric value
 * is greater than or equal to 2000.  Communication errors are not official EPP errors
 * but the sake of consistency, they have been defined in the RTK with values
 * in the 2600-2699 range.  The error codes and symbolic names for these are defined
 * in epp_Action.
 * $Header: /cvsroot/epp-rtk/epp-rtk/java/src/org/openrtk/idl/epp0705/epp_Exception.java,v 1.1 2003/03/20 22:42:18 tubadanm Exp $<br>
 * $Revision: 1.1 $<br>
 * $Date: 2003/03/20 22:42:18 $<br>
 */
public class epp_Exception extends org.omg.CORBA.UserException implements org.omg.CORBA.portable.IDLEntity
{
  /**
   * The array of error details.
   * @see #setDetails(org.openrtk.idl.epp0705.epp_Result[])
   * @see #getDetails()
   */
  public org.openrtk.idl.epp0705.epp_Result m_details[] = null;
  public org.openrtk.idl.epp0705.epp_TransID m_trans_id = null;

  // Multiple extensions allows in EPP
  public String m_ext_strings[] = null;

  /**
   * Empty constructor
   */
  public epp_Exception ()
  {
  } // ctor

  /**
   * The constructor with initializing variables.
   * @param _m_details The array of error details
   */
  public epp_Exception (org.openrtk.idl.epp0705.epp_Result[] _m_details)
  {
    m_details = _m_details;
  } // ctor

  /**
   * The constructor with initializing variables.
   * @param _m_details The array of error details
   */
  public epp_Exception (org.openrtk.idl.epp0705.epp_Result[] _m_details, org.openrtk.idl.epp0705.epp_TransID _m_trans_id, String[] _m_ext_strings)
  {
    m_details = _m_details;
    m_trans_id = _m_trans_id;
    m_ext_strings = _m_ext_strings;
  } // ctor

  /**
   * Accessor method for the array of error details
   * @param value The array of error details
   * @see #m_details
   */
  public void setDetails(org.openrtk.idl.epp0705.epp_Result[] value) { m_details = value; }
  /**
   * Accessor method for the array of error details
   * @return The array of error details
   * @see #m_details
   */
  public org.openrtk.idl.epp0705.epp_Result[] getDetails() { return m_details; }

  public void setTransId(org.openrtk.idl.epp0705.epp_TransID value) { m_trans_id = value; }
  public org.openrtk.idl.epp0705.epp_TransID getTransId() { return m_trans_id; }

  public void setExtStrings(String[] value) { m_ext_strings = value; }
  public String[] getExtStrings() { return m_ext_strings; }

  /**
   * Converts this class into a string.
   * Typically used to view the object in debug output.
   * @return The string representation of this object instance
   */
  public String toString() { return this.getClass().getName() + ": { m_details ["+(m_details != null ? java.util.Arrays.asList(m_details) : null)+"] m_trans_id ["+m_trans_id+"] m_ext_strings ["+(m_ext_strings != null ? java.util.Arrays.asList(m_ext_strings) : null)+"] }"; }

} // class epp_Exception
