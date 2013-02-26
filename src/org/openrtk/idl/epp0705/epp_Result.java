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
 * Class that contains the elements necessary to document the success or
 * failure of an EPP command execution.</p>
 * $Header: /cvsroot/epp-rtk/epp-rtk/java/src/org/openrtk/idl/epp0705/epp_Result.java,v 1.1 2003/03/20 22:42:19 tubadanm Exp $<br>
 * $Revision: 1.1 $<br>
 * $Date: 2003/03/20 22:42:19 $<br>
 * @see org.openrtk.idl.epp0705.epp_Exception
 * @see org.openrtk.idl.epp0705.epp_Response
 */
public class epp_Result implements org.omg.CORBA.portable.IDLEntity
{
  /**
   * The decimal number that describes the success or failure of an EPP command.
   * @see #setCode(short)
   * @see #getCode()
   */
  public short m_code = (short)0;
  /**
   * The array of values that provide server error details.<br><br>
   * If null, m_ext_values should be checked.
   * @see #setValues(epp_ResultValue[])
   * @see #getValues()
   */
  public org.openrtk.idl.epp0705.epp_ResultValue m_values[] = null;
  /**
   * The array of values that provide server error details.
   * Can provide slightly more information than m_values (ie. reason for error).<br><br>
   * If null, m_values should be checked.
   * @see #setValues(epp_ExtResultValue[])
   * @see #getValues()
   */
  public org.openrtk.idl.epp0705.epp_ExtResultValue m_ext_values[] = null;
  /**
   * The human-readable description of the response code.
   * @see #setMsg(String)
   * @see #getMsg()
   */
  public String m_msg = null;
  /**
   * The language used to express the response message.
   * @see #setLang(String)
   * @see #getLang()
   */
  public String m_lang = null;

  /**
   * Empty constructor
   */
  public epp_Result ()
  {
  } // ctor

  /**
   * The constructor with initializing variables.
   * @param _m_code The decimal number that describes the success or failure of an EPP command
   * @param _m_msg The human-readable description of the response code
   * @param _m_lang The language used to express the response message
   * @param _m_values The array of messages that provide server error details
   */
  public epp_Result (short _m_code, String _m_msg, String _m_lang, epp_ResultValue[] _m_values)
  {
    // Can we call another constructor in here???
    // Don't really want to repeat this code.
    m_code = _m_code;
    m_values = _m_values;
    m_msg = _m_msg;
    m_lang = _m_lang;
  } // ctor

  /**
   * The constructor with initializing variables.
   * @param _m_code The decimal number that describes the success or failure of an EPP command
   * @param _m_msg The human-readable description of the response code
   * @param _m_lang The language used to express the response message
   * @param _m_values The array of messages that provide server error details
   * @param _m_data The generic result data.
   */
  public epp_Result (short _m_code, org.openrtk.idl.epp0705.epp_ResultValue[] _m_values, org.openrtk.idl.epp0705.epp_ExtResultValue[] _m_ext_values, String _m_msg, String _m_lang)
  {
    m_code = _m_code;
    m_values = _m_values;
    m_ext_values = _m_ext_values;
    m_msg = _m_msg;
    m_lang = _m_lang;
  } // ctor

  /**
   * Accessor method for the decimal number that describes the success or failure of an EPP command
   * @param value The response code
   * @see #m_code
   */
  public void setCode(short value) { m_code = value; }
  /**
   * Accessor method for the decimal number that describes the success or failure of an EPP command
   * @return The response code
   * @see #m_code
   */
  public short getCode() { return m_code; }

  /**
   * Accessor method for the array of value that provide server error details
   * @param value The array of error details
   * @see #m_values
   */
  public void setValues(org.openrtk.idl.epp0705.epp_ResultValue[] value) { m_values = value; }
  /**
   * Accessor method for the array of messages that provide server error details
   * @return The array of error details
   * @see #m_values
   */
  public org.openrtk.idl.epp0705.epp_ResultValue[] getValues() { return m_values; }

  /**
   * Accessor method for the array of extended messages that provide server error details
   * @param value The array of error details
   * @see #m_ext_values
   */
  public void setExtValues(org.openrtk.idl.epp0705.epp_ExtResultValue[] value) { m_ext_values = value; }
  /**
   * Accessor method for the array of extended messages that provide server error details
   * @return The array of error details
   * @see #m_ext_values
   */
  public org.openrtk.idl.epp0705.epp_ExtResultValue[] getExtValues() { return m_ext_values; }

  /**
   * Accessor method for the human-readable description of the response code
   * @param value The reponse code description
   * @see #m_msg
   */
  public void setMsg(String value) { m_msg = value; }
  /**
   * Accessor method for the human-readable description of the response code
   * @return The response code description
   * @see #m_msg
   */
  public String getMsg() { return m_msg; }

  /**
   * Accessor method for the language used to express the response message
   * @param value The language of the response message
   * @see #m_lang
   */
  public void setLang(String value) { m_lang = value; }
  /**
   * Accessor method for the language used to express the response message
   * @return The language of the response message
   * @see #m_lang
   */
  public String getLang() { return m_lang; }

  /**
   * Converts this class into a string.
   * Typically used to view the object in debug output.
   * @return The string representation of this object instance
   */
  public String toString() { return this.getClass().getName() + ": { m_code ["+m_code+"] m_values ["+(m_values != null ? java.util.Arrays.asList(m_values) : null)+"] m_ext_values ["+(m_ext_values != null ? java.util.Arrays.asList(m_ext_values) : null)+"] m_msg ["+m_msg+"] m_lang ["+m_lang+"] }"; }

} // class epp_Result
