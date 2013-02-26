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
 * Class that contains the elements to be added into or removed from the domain
 * object in the registry.</p>
 * This class can only be used when modifying a domain object.</p>
 * $Header: /cvsroot/epp-rtk/epp-rtk/java/src/org/openrtk/idl/epp0503/domain/epp_DomainUpdateAddRemove.java,v 1.1 2003/03/21 16:18:30 tubadanm Exp $<br>
 * $Revision: 1.1 $<br>
 * $Date: 2003/03/21 16:18:30 $<br>
 * @see org.openrtk.idl.epp0503.domain.epp_DomainUpdateReq#setAdd(epp_DomainUpdateAddRemove)
 * @see org.openrtk.idl.epp0503.domain.epp_DomainUpdateReq#setRemove(epp_DomainUpdateAddRemove)
 */
public class epp_DomainUpdateAddRemove implements org.omg.CORBA.portable.IDLEntity
{
  /**
   * The array of name servers to be associated with or removed from the domain object.
   * Name servers must exist in the registry
   * @see #setNameServers(String[])
   * @see #getNameServers()
   */
  public String m_name_servers[] = null;
  /**
   * The array of contact objects to be associated with or removed from the domain object.
   * Contact objects must exist in the registry
   * @see #setContacts(org.openrtk.idl.epp0503.domain.epp_DomainContact[])
   * @see #getContacts()
   */
  public org.openrtk.idl.epp0503.domain.epp_DomainContact m_contacts[] = null;
  /**
   * The array of status to be associated with or removed from the domain object.
   * @see #setStatus(org.openrtk.idl.epp0503.domain.epp_DomainStatus[])
   * @see #getStatus()
   */
  public org.openrtk.idl.epp0503.domain.epp_DomainStatus m_status[] = null;

  /**
   * Empty constructor
   */
  public epp_DomainUpdateAddRemove ()
  {
  } // ctor

  /**
   * The constructor with initializing variables.
   * @param _m_name_servers The array of name servers
   * @param _m_contacts The array of contact objects
   * @param _m_status The array of domain status values
   */
  public epp_DomainUpdateAddRemove (String[] _m_name_servers, org.openrtk.idl.epp0503.domain.epp_DomainContact[] _m_contacts, org.openrtk.idl.epp0503.domain.epp_DomainStatus[] _m_status)
  {
    m_name_servers = _m_name_servers;
    m_contacts = _m_contacts;
    m_status = _m_status;
  } // ctor

  /**
   * Accessor method for the array of domain name servers
   * @param value The array of domain name servers
   * @see #m_name_servers
   */
  public void setNameServers(String[] value) { m_name_servers = value; }
  /**
   * Accessor method for the array of domain name servers
   * @return The array of domain name servers
   * @see #m_name_servers
   */
  public String[] getNameServers() { return m_name_servers; }

  /**
   * Accessor method for the array of domain contact objects
   * @param value The array of domain contacts
   * @see #m_contacts
   */
  public void setContacts(org.openrtk.idl.epp0503.domain.epp_DomainContact[] value) { m_contacts = value; }
  /**
   * Accessor method for the array of domain contact objects
   * @return The array of domain contacts
   * @see #m_contacts
   */
  public org.openrtk.idl.epp0503.domain.epp_DomainContact[] getContacts() { return m_contacts; }

  /**
   * Accessor method for the array of domain status values
   * @param value The array of domain status values
   * @see #m_status
   */
  public void setStatus(org.openrtk.idl.epp0503.domain.epp_DomainStatus[] value) { m_status = value; }
  /**
   * Accessor method for the array of domain status values
   * @return The array of domain status values
   * @see #m_status
   */
  public org.openrtk.idl.epp0503.domain.epp_DomainStatus[] getStatus() { return m_status; }

  /**
   * Converts this class into a string.
   * Typically used to view the object in debug output.
   * @return The string representation of this object instance
   */
  public String toString() { return this.getClass().getName() + ": { m_name_servers ["+(m_name_servers != null ? java.util.Arrays.asList(m_name_servers) : null)+"] m_contacts ["+(m_contacts != null ? java.util.Arrays.asList(m_contacts) : null)+"] m_status ["+(m_status != null ? java.util.Arrays.asList(m_status) : null)+"] }"; }

} // class epp_DomainUpdateAddRemove
