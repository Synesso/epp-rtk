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

package org.openrtk.idl.epp0503.domain;


/**
 * Class that contains the elements used to represent the domain create
 * response from the EPP server.</p>
 * Note usually instantiated by the RTK user, but rather by EPPDomainCreate
 * and retrieved using that class' getResponseData() method.</p>
 * $Header: /cvsroot/epp-rtk/epp-rtk/java/src/org/openrtk/idl/epp0503/domain/epp_DomainCreateRsp.java,v 1.2 2003/09/10 21:29:57 tubadanm Exp $<br>
 * $Revision: 1.2 $<br>
 * $Date: 2003/09/10 21:29:57 $<br>
 * @see com.tucows.oxrs.epp0503.rtk.xml.EPPDomainCreate
 * @see org.openrtk.idl.epp0503.domain.epp_DomainCreateReq
 */
public class epp_DomainCreateRsp implements org.omg.CORBA.portable.IDLEntity
{
  /**
   * The common and generic response element.
   * @see #getRsp()
   */
  public org.openrtk.idl.epp0503.epp_Response m_rsp = null;
  /**
   * The name of the domain object in the registry.
   * @see #setName(String)
   * @see #getName()
   */
  public String m_name = null;
  /**
   * The creation date of the domain object.
   * @see #setCreationDate(String)
   * @see #getCreationDate()
   */
  public String m_creation_date = null;
  /**
   * The expiration date of the domain object.
   * @see #setExpirationDate(String)
   * @see #getExpirationDate()
   */
  public String m_expiration_date = null;

  /**
   * Empty constructor
   */
  public epp_DomainCreateRsp ()
  {
  } // ctor

  /**
   * The constructor with initializing variables.
   * @param _m_rsp The common and generic response element
   * @param _m_name The name of the domain object in the registry
   * @param _m_creation_date The creation date of the domain object
   * @param _m_expiration_date The expiration date of the domain object
   */
  public epp_DomainCreateRsp (org.openrtk.idl.epp0503.epp_Response _m_rsp, String _m_name, String _m_creation_date, String _m_expiration_date)
  {
    m_rsp = _m_rsp;
    m_name = _m_name;
    m_creation_date = _m_creation_date;
    m_expiration_date = _m_expiration_date;
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
   * Accessor method for the name of the domain object in the registry
   * @param value The domain name
   * @see #m_name
   */
  public void setName(String value) { m_name = value; }
  /**
   * Accessor method for the name of the domain object in the registry
   * @return The domain name
   * @see #m_name
   */
  public String getName() { return m_name; }

  /**
   * Accessor method for the creation date of the domain object
   * @param value The domain creation date
   * @see #m_creation_date
   */
  public void setCreationDate(String value) { m_creation_date = value; }
  /**
   * Accessor method for the creation date of the domain object
   * @return The domain creation date
   * @see #m_creation_date
   */
  public String getCreationDate() { return m_creation_date; }

  /**
   * Accessor method for the expiration date of the domain object
   * @param value The domain expiration date
   * @see #m_expiration_date
   */
  public void setExpirationDate(String value) { m_expiration_date = value; }
  /**
   * Accessor method for the expiration date of the domain object
   * @return The domain expiration date
   * @see #m_expiration_date
   */
  public String getExpirationDate() { return m_expiration_date; }

  /**
   * Converts this class into a string.
   * Typically used to view the object in debug output.
   * @return The string representation of this object instance
   */
  public String toString() { return this.getClass().getName() + ": { m_rsp ["+m_rsp+"] m_name ["+m_name+"] m_creation_date ["+m_creation_date+"] m_expiration_date ["+m_expiration_date+"] }"; }

} // class epp_DomainCreateRsp
