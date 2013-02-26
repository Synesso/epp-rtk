/*
**
** EPP RTK Java
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
 * Extension to the epp_ResultValue class (though, not a subclass by the IDL).
 * Incorporate the data of the epp_ResultValue via a "has-a" relation and adds
 * a reason text.</p>
 * $Header: /cvsroot/epp-rtk/epp-rtk/java/src/org/openrtk/idl/epp0705/epp_ExtResultValue.java,v 1.1 2003/03/20 22:42:18 tubadanm Exp $<br>
 * $Revision: 1.1 $<br>
 * $Date: 2003/03/20 22:42:18 $<br>
 * @see org.openrtk.idl.epp0705.epp_Result#setExtValues(epp_ExtResultValue[])
 */
public class epp_ExtResultValue implements org.omg.CORBA.portable.IDLEntity
{
  /**
   * The offending XML element which caused the error response from the EPP server.
   */
  public org.openrtk.idl.epp0705.epp_ResultValue m_value = null;
  /**
   * The free-text reason for the error.
   */
  public String m_reason = null;

  /**
   * The empty constructor
   */
  public epp_ExtResultValue ()
  {
  } // ctor

  /**
   * The constructor with parameters
   * @param _m_value The offending XML element.
   * @param _m_reason The reason for the error.
   */
  public epp_ExtResultValue (org.openrtk.idl.epp0705.epp_ResultValue _m_value, String _m_reason)
  {
    m_value = _m_value;
    m_reason = _m_reason;
  } // ctor

  /**
   * The accessor for setting the offencing XML element.
   * @param value The offending XML element
   */
  public void setValue(org.openrtk.idl.epp0705.epp_ResultValue value) { m_value = value; }
  /**
   * The accessor for retrieving the offencing XML element.
   * @return value The offending XML element
   */
  public org.openrtk.idl.epp0705.epp_ResultValue getValue() { return m_value; }

  /**
   * The accessor for setting the reason for the error.
   * @param value The reason text
   */
  public void setReason(String value) { m_reason = value; }
  /**
   * The accessor for retrieving the reason for the error.
   * @param value The reason for the error
   */
  public String getReason() { return m_reason; }

  /**
   * Converts this class into a string.
   * Typically used to view the object in debug output.
   * @return The string representation of this object instance
   */
  public String toString() { return this.getClass().getName() + ": { m_value ["+m_value+"] m_reason ["+m_reason+"] }"; }

} // class epp_ExtResultValue
