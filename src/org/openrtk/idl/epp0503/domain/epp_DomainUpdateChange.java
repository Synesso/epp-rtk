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
 * Class that contains the domain object elements to be changed in the registry.</p>
 * This class can only be used when modifying a domain object.</p>
 * $Header: /cvsroot/epp-rtk/epp-rtk/java/src/org/openrtk/idl/epp0503/domain/epp_DomainUpdateChange.java,v 1.1 2003/03/21 16:18:30 tubadanm Exp $<br>
 * $Revision: 1.1 $<br>
 * $Date: 2003/03/21 16:18:30 $<br>
 * @see org.openrtk.idl.epp0503.domain.epp_DomainUpdateReq#setChange(epp_DomainUpdateChange)
 */
public class epp_DomainUpdateChange implements org.omg.CORBA.portable.IDLEntity
{
  /**
   * The registrant to be associated with the domain object.
   * The registrant object must exist in the registry
   * @see #setRegistrant(String)
   * @see #getRegistrant()
   */
  public String m_registrant = null;
  /**
   * The authorization information to be associated with the domain object.
   * @see #setAuthInfo(org.openrtk.idl.epp0503.epp_AuthInfo)
   * @see #getAuthInfo()
   */
  public org.openrtk.idl.epp0503.epp_AuthInfo m_auth_info = null;

  /**
   * Empty constructor
   */
  public epp_DomainUpdateChange ()
  {
  } // ctor

  /**
   * The constructor with initializing variables.
   * @param _m_registrant The registrant to be associated with the domain object
   * @param _m_auth_info The authorization information to be associated with the domain object
   */
  public epp_DomainUpdateChange (String _m_registrant, org.openrtk.idl.epp0503.epp_AuthInfo _m_auth_info)
  {
    m_registrant = _m_registrant;
    m_auth_info = _m_auth_info;
  } // ctor

  /**
   * Accessor method for the registrant to be associated with the domain object
   * @param value The domain registrant
   * @see #m_registrant
   */
  public void setRegistrant(String value) { m_registrant = value; }
  /**
   * Accessor method for the registrant to be associated with the domain object
   * @return The domain registrant
   * @see #m_registrant
   */
  public String getRegistrant() { return m_registrant; }

  /**
   * Accessor method for the authorization information to be associated with the domain object
   * @param value The domain auth info
   * @see #m_auth_info
   */
  public void setAuthInfo(org.openrtk.idl.epp0503.epp_AuthInfo value) { m_auth_info = value; }
  /**
   * Accessor method for the authorization information to be associated with the domain object
   * @return The domain auth info
   * @see #m_auth_info
   */
  public org.openrtk.idl.epp0503.epp_AuthInfo getAuthInfo() { return m_auth_info; }

  /**
   * Converts this class into a string.
   * Typically used to view the object in debug output.
   * @return The string representation of this object instance
   */
  public String toString() { return this.getClass().getName() + ": { m_registrant ["+m_registrant+"] m_auth_info ["+m_auth_info+"] }"; }

} // class epp_DomainUpdateChange
