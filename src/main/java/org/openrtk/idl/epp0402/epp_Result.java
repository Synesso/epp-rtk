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
 * Class that contains the elements necessary to document the success or
 * failure of an EPP command execution.</p>
 * $Header: /cvsroot/epp-rtk/epp-rtk/java/src/org/openrtk/idl/epp0402/epp_Result.java,v 1.1 2003/03/21 16:35:39 tubadanm Exp $<br>
 * $Revision: 1.1 $<br>
 * $Date: 2003/03/21 16:35:39 $<br>
 * @see org.openrtk.idl.epp0402.epp_Exception
 * @see org.openrtk.idl.epp0402.epp_Response
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
   * The identifier of the service message dequeued from the server by the client.
   * Normally be present when responding to an EPP poll command.
   * @see #setId(String)
   * @see #getId()
   */
  public String m_id = null;
  /**
   * The array of messages that provide server error details.
   * @see #setValues(String[])
   * @see #getValues()
   */
  public String m_values[] = null;

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
   * @param _m_id The identifier of the service message dequeued from the server by the client
   * @param _m_values The array of messages that provide server error details
   */
  public epp_Result (short _m_code, String _m_msg, String _m_lang, String _m_id, String[] _m_values)
  {
    m_code = _m_code;
    m_msg = _m_msg;
    m_lang = _m_lang;
    m_id = _m_id;
    m_values = _m_values;
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
   * Accessor method for the identifier of the service message dequeued from the server by the client
   * @param value The service message identifier
   * @see #m_id
   */
  public void setId(String value) { m_id = value; }
  /**
   * Accessor method for the identifier of the service message dequeued from the server by the client
   * @return The service message identifier
   * @see #m_id
   */
  public String getId() { return m_id; }

  /**
   * Accessor method for the array of messages that provide server error details
   * @param value The array of error details
   * @see #m_values
   */
  public void setValues(String[] value) { m_values = value; }
  /**
   * Accessor method for the array of messages that provide server error details
   * @return The array of error details
   * @see #m_values
   */
  public String[] getValues() { return m_values; }

  /**
   * Converts this class into a string.
   * Typically used to view the object in debug output.
   * @return The string representation of this object instance
   */
  public String toString() { return this.getClass().getName() + ": { m_code ["+m_code+"] m_msg ["+m_msg+"] m_lang ["+m_lang+"] m_id ["+m_id+"] m_values ["+(m_values != null ? java.util.Arrays.asList(m_values) : null)+"] }"; }

} // class epp_Result
