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
 * Class that contains the elements generic to all the check command responses.</p>
 * $Header: /cvsroot/epp-rtk/epp-rtk/java/src/org/openrtk/idl/epp0402/epp_CheckResult.java,v 1.1 2003/03/21 16:35:38 tubadanm Exp $<br>
 * $Revision: 1.1 $<br>
 * $Date: 2003/03/21 16:35:38 $<br>
 * @see org.openrtk.idl.epp0402.domain.epp_DomainCheckRsp#getResults()
 * @see org.openrtk.idl.epp0402.host.epp_HostCheckRsp#getResults()
 * @see org.openrtk.idl.epp0402.contact.epp_ContactCheckRsp#getResults()
 */
public class epp_CheckResult implements org.omg.CORBA.portable.IDLEntity
{
  /**
   * The boolean value indicating whether an object exists in the registry or not.
   * @see #setExists(boolean)
   * @see #getExists()
   */
  public boolean m_exists = false;
  /**
   * The identifier or name of the object checked in the registry.
   * @see #setValue(String)
   * @see #getValue()
   */
  public String m_value = null;

  /**
   * Empty constructor
   */
  public epp_CheckResult ()
  {
  } // ctor

  /**
   * The constructor with initializing variables.
   * @param _m_exists The boolean value indicating whether an object exists in the registry or not
   * @param _m_value The identifier or name of the object checked in the registry
   */
  public epp_CheckResult (boolean _m_exists, String _m_value)
  {
    m_exists = _m_exists;
    m_value = _m_value;
  } // ctor

  /**
   * Accessor method for the boolean value indicating whether an object exists in the registry or not
   * @param value The boolean value indicating whether an object exists in the registry or not
   * @see #m_exists
   */
  public void setExists(boolean value) { m_exists = value; }
  /**
   * Accessor method for the boolean value indicating whether an object exists in the registry or not
   * @return The boolean value indicating whether an object exists in the registry or not
   * @see #m_exists
   */
  public boolean getExists() { return m_exists; }

  /**
   * Accessor method for the identifier or name of the object checked in the registry
   * @param value The identifier or name of the object
   * @see #m_value
   */
  public void setValue(String value) { m_value = value; }
  /**
   * Accessor method for the identifier or name of the object checked in the registry
   * @return The identifier or name of the object
   * @see #m_value
   */
  public String getValue() { return m_value; }

  /**
   * Converts this class into a string.
   * Typically used to view the object in debug output.
   * @return The string representation of this object instance
   */
  public String toString() { return this.getClass().getName() + ": { m_exists ["+m_exists+"] m_value ["+m_value+"] }"; }

} // class epp_CheckResult
