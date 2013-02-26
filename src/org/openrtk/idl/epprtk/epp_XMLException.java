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

package org.openrtk.idl.epprtk;


/**
 * Exception class that indicates an error occurs when rendering or parsing an XML String.</p>
 * $Header: /cvsroot/epp-rtk/epp-rtk/java/src/org/openrtk/idl/epprtk/epp_XMLException.java,v 1.1 2004/12/07 15:27:49 ewang2004 Exp $<br>
 * $Revision: 1.1 $<br>
 * $Date: 2004/12/07 15:27:49 $<br>
 */
public class epp_XMLException extends org.omg.CORBA.UserException implements org.omg.CORBA.portable.IDLEntity
{
  /**
   * The error message.
   * @see #setErrorMessage(String)
   * @see #getErrorMessage()
   */
  public String m_error_message = null;

  /**
   * Empty constructor
   */
  public epp_XMLException ()
  {
  } // ctor

  /**
   * The constructor with initializing variables.
   * @param _m_error_message The error message
   */
  public epp_XMLException (String _m_error_message)
  {
    m_error_message = _m_error_message;
  } // ctor

  /**
   * Accessor method for the error message
   * @param value The error message
   * @see #m_error_message
   */
  public void setErrorMessage(String value) { m_error_message = value; }
  /**
   * Accessor method for the error message
   * @return The error message
   * @see #m_error_message
   */
  public String getErrorMessage() { return m_error_message; }

  /**
   * Converts this class into a string.
   * Typically used to view the object in debug output.
   * @return The string representation of this object instance
   */
  public String toString() { return this.getClass().getName() + ": { m_error_message ["+m_error_message+"] }"; }

} // class epp_XMLException
