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

package org.openrtk.idl.epp0503.contact;


/**
 * Class that contains the elements used to represent the contact create
 * response from the EPP server.</p>
 * Note usually instantiated by the RTK user, but rather by EPPContactCreate
 * and retrieved using that class' getResponseData() method.</p>
 * $Header: /cvsroot/epp-rtk/epp-rtk/java/src/org/openrtk/idl/epp0503/contact/epp_ContactCreateRsp.java,v 1.2 2003/09/10 21:29:56 tubadanm Exp $<br>
 * $Revision: 1.2 $<br>
 * $Date: 2003/09/10 21:29:56 $<br>
 * @see com.tucows.oxrs.epp0503.rtk.xml.EPPContactCreate
 * @see org.openrtk.idl.epp0503.contact.epp_ContactCreateReq
 */
public class epp_ContactCreateRsp implements org.omg.CORBA.portable.IDLEntity
{
  /**
   * The common and generic response element.
   * @see #getRsp()
   */
  public org.openrtk.idl.epp0503.epp_Response m_rsp = null;
  /**
   * The identifier for the contact object in the registry.
   * @see #setId(String)
   * @see #getId()
   */
  public String m_id = null;
  /**
   * The creation date of the contact object.
   * @see #setCreationDate(String)
   * @see #getCreationDate()
   */
  public String m_creation_date = null;

  /**
   * Empty constructor
   */
  public epp_ContactCreateRsp ()
  {
  } // ctor

  /**
   * The constructor with initializing variables.
   * @param _m_rsp The common and generic response element
   * @param _m_id The identifier for the contact object in the registry
   * @param _m_creation_date The creation date of the contact object
   */
  public epp_ContactCreateRsp (org.openrtk.idl.epp0503.epp_Response _m_rsp, String _m_id, String _m_creation_date)
  {
    m_rsp = _m_rsp;
    m_id = _m_id;
    m_creation_date = _m_creation_date;
  } // ctor

  /**
   * Accessor method for the common and generic response element.
   * @param value The new value for the response element
   * @see #m_rsp
   */
  public void setRsp(org.openrtk.idl.epp0503.epp_Response value) { m_rsp = value; }
  /**
   * Accessor method for the common and generic response element.
   * @return The value for the response element
   * @see #m_rsp
   */
  public org.openrtk.idl.epp0503.epp_Response getRsp() { return m_rsp; }

  /**
   * @deprecated
   * @see #setId(String)
   */
  public void setRoid(String value) { setId(value); }
  /**
   * @deprecated
   * @see #getId()
   */
  public String getRoid() { return getId(); }

  /**
   * Accessor method for the identifier for the contact object in the registry
   * @param value The contact id
   * @see #m_id
   */
  public void setId(String value) { m_id = value; }
  /**
   * Accessor method for the identifier for the contact object in the registry
   * @return The contact id
   * @see #m_id
   */
  public String getId() { return m_id; }

  /**
   * Accessor method for the creation date of the contact object
   * @param value The contact creation date
   * @see #m_creation_date
   */
  public void setCreationDate(String value) { m_creation_date = value; }
  /**
   * Accessor method for the creation date of the contact object
   * @return The contact creation date
   * @see #m_creation_date
   */
  public String getCreationDate() { return m_creation_date; }

  /**
   * Converts this class into a string.
   * Typically used to view the object in debug output.
   * @return The string representation of this object instance
   */
  public String toString() { return this.getClass().getName() + ": { m_rsp ["+m_rsp+"] m_id ["+m_id+"] m_creation_date ["+m_creation_date+"] }"; }

} // class epp_ContactCreateRsp
