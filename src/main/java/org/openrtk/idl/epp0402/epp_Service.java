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
 * Class that contains service object or custom service object extensions
 * supported by the server.</p>
 * $Header: /cvsroot/epp-rtk/epp-rtk/java/src/org/openrtk/idl/epp0402/epp_Service.java,v 1.1 2003/03/21 16:35:39 tubadanm Exp $<br>
 * $Revision: 1.1 $<br>
 * $Date: 2003/03/21 16:35:39 $<br>
 * @see org.openrtk.idl.epp0402.epp_LoginReq
 * @see org.openrtk.idl.epp0402.epp_Greeting
 */
public class epp_Service implements org.omg.CORBA.portable.IDLEntity
{
  /**
   * The name of the object to be managed by the server.
   * @see #setName(String)
   * @see #getName()
   */
  public String m_name = null;
  /**
   * The xml namespace of the object.
   * @see #setXmlns(String)
   * @see #getXmlns()
   */
  public String m_xmlns = null;
  /**
   * The xml schema location of the object.
   * @see #setSchemaLocation(String)
   * @see #getSchemaLocation()
   */
  public String m_schema_location = null;

  /**
   * Empty constructor
   */
  public epp_Service ()
  {
  } // ctor

  /**
   * The constructor with initializing variables.
   * @param _m_name The name of the object to be managed by the server
   * @param _m_xmlns The xml namespace of the object
   * @param _m_schema_location The xml schema location of the object
   */
  public epp_Service (String _m_name, String _m_xmlns, String _m_schema_location)
  {
    m_name = _m_name;
    m_xmlns = _m_xmlns;
    m_schema_location = _m_schema_location;
  } // ctor

  /**
   * Accessor method for the name of the object to be managed by the server
   * @param value The object name
   * @see #m_name
   */
  public void setName(String value) { m_name = value; }
  /**
   * Accessor method for the name of the object to be managed by the server
   * @return The object name
   * @see #m_name
   */
  public String getName() { return m_name; }

  /**
   * Accessor method for the xml namespace of the object
   * @param value The xml namespace of the object
   * @see #m_xmlns
   */
  public void setXmlns(String value) { m_xmlns = value; }
  /**
   * Accessor method for the xml namespace of the object
   * @return The xml namespace of the object
   * @see #m_xmlns
   */
  public String getXmlns() { return m_xmlns; }

  /**
   * Accessor method for the xml schema location of the object
   * @param value The xml schema location
   * @see #m_schema_location
   */
  public void setSchemaLocation(String value) { m_schema_location = value; }
  /**
   * Accessor method for the xml schema location of the object
   * @return The xml schema location
   * @see #m_schema_location
   */
  public String getSchemaLocation() { return m_schema_location; }

  /**
   * Converts this class into a string.
   * Typically used to view the object in debug output.
   * @return The string representation of this object instance
   */
  public String toString() { return this.getClass().getName() + ": { m_name ["+m_name+"] m_xmlns ["+m_xmlns+"] m_schema_location ["+m_schema_location+"] }"; }

} // class epp_Service
