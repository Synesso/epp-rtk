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
 * Contains the information of the XML element from the request 
 * which cause the server to return an error.</p>
 * $Header: /cvsroot/epp-rtk/epp-rtk/java/src/org/openrtk/idl/epp0705/epp_ResultValue.java,v 1.1 2003/03/20 22:42:19 tubadanm Exp $<br>
 * $Revision: 1.1 $<br>
 * $Date: 2003/03/20 22:42:19 $<br>
 * @see org.openrtk.idl.epp0705.epp_Result#setValues(epp_ResultValue[])
 */
public class epp_ResultValue implements org.omg.CORBA.portable.IDLEntity
{

  /**
   * The namespace of the XML element.
   * Can be null/empty if the XML is not parsable.
   */
  public String m_namespace = null;

  /**
   * The entire XML string of the offending element.
   * Always set.
   */
  public String m_xml_string = null;

  /**
   * The name of the XML element.
   * Can be null/empty if the XML is not parsable.
   */
  public String m_element_name = null;

  /**
   * The value of the element.
   * Can be null if the XML is unparsable.
   */
  public String m_element_value = null;

  public epp_ResultValue ()
  {
  } // ctor

  public epp_ResultValue (String _m_namespace, String _m_xml_string, String _m_element_name, String _m_element_value)
  {
    m_namespace = _m_namespace;
    m_xml_string = _m_xml_string;
    m_element_name = _m_element_name;
    m_element_value = _m_element_value;
  } // ctor

  public void setNamespace(String value) { m_namespace = value; }
  public String getNamespace() { return m_namespace; }

  public void setXmlString(String value) { m_xml_string = value; }
  public String getXmlString() { return m_xml_string; }

  public void setElementName(String value) { m_element_name = value; }
  public String getElementName() { return m_element_name; }

  public void setElementValue(String value) { m_element_value = value; }
  public String getElementValue() { return m_element_value; }

  /**
   * Converts this class into a string.
   * Typically used to view the object in debug output.
   * @return The string representation of this object instance
   */
  public String toString() { return this.getClass().getName() + ": { m_namespace ["+m_namespace+"] m_xml_string ["+m_xml_string+"] m_element_name ["+m_element_name+"] m_element_value ["+m_element_value+"] }"; }

} // class epp_ResultValue
