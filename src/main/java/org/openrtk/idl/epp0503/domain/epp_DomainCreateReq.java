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
 * Class that contains the elements necessary to create a domain
 * in the registry.</p>
 * $Header: /cvsroot/epp-rtk/epp-rtk/java/src/org/openrtk/idl/epp0503/domain/epp_DomainCreateReq.java,v 1.2 2003/09/10 21:29:57 tubadanm Exp $<br>
 * $Revision: 1.2 $<br>
 * $Date: 2003/09/10 21:29:57 $<br>
 * @see com.tucows.oxrs.epp0503.rtk.xml.EPPDomainCreate
 * @see org.openrtk.idl.epp0503.domain.epp_DomainCreateRsp
 */
public class epp_DomainCreateReq implements org.omg.CORBA.portable.IDLEntity
{
  /**
   * The common and generic command element.
   * @see #setCmd(org.openrtk.idl.epp0503.epp_Command)
   * @see #getCmd()
   */
  public org.openrtk.idl.epp0503.epp_Command m_cmd = null;
  /**
   * The name of the domain object to be created in the registry.
   * @see #setName(String)
   * @see #getName()
   */
  public String m_name = null;
  /**
   * The registration period of the domain object.
   * @see #setPeriod(org.openrtk.idl.epp0503.domain.epp_DomainPeriod)
   * @see #getPeriod()
   */
  public org.openrtk.idl.epp0503.domain.epp_DomainPeriod m_period = null;
  /**
   * The array of name servers to be associated with the domain object.
   * Name servers must exist in the registry
   * @see #setNameServers(String[])
   * @see #getNameServers()
   */
  public String m_name_servers[] = null;
  /**
   * The domain registrant to be associated with the domain object.
   * Registrant must exist in the registry
   * @see #setRegistrant(String)
   * @see #getRegistrant()
   */
  public String m_registrant = null;
  /**
   * The array of contacts to be associated with the domain object.
   * Contact objects must exist in the registry
   * @see #setContacts(org.openrtk.idl.epp0503.domain.epp_DomainContact[])
   * @see #getContacts()
   */
  public org.openrtk.idl.epp0503.domain.epp_DomainContact m_contacts[] = null;
  /**
   * The authorization information to be associated with the domain object.
   * @see #setAuthInfo(org.openrtk.idl.epp0503.epp_AuthInfo)
   * @see #getAuthInfo()
   */
  public org.openrtk.idl.epp0503.epp_AuthInfo m_auth_info = null;

  /**
   * Empty constructor
   */
  public epp_DomainCreateReq ()
  {
  } // ctor

  /**
   * The constructor with initializing variables.
   * @param _m_cmd The common and generic command element
   * @param _m_name The name of the domain object to be created in the registry
   * @param _m_period The registration period of the domain object
   * @param _m_name_servers The array of name servers to be associated with the domain object
   * @param _m_registrant The registrant to be associated with the domain object
   * @param _m_contacts The array of contacts to be associated with the domain object
   * @param _m_auth_info The authorization information to be associated with the domain object
   */
  public epp_DomainCreateReq (org.openrtk.idl.epp0503.epp_Command _m_cmd, String _m_name, org.openrtk.idl.epp0503.domain.epp_DomainPeriod _m_period, String[] _m_name_servers, String _m_registrant, org.openrtk.idl.epp0503.domain.epp_DomainContact[] _m_contacts, org.openrtk.idl.epp0503.epp_AuthInfo _m_auth_info)
  {
    m_cmd = _m_cmd;
    m_name = _m_name;
    m_period = _m_period;
    m_name_servers = _m_name_servers;
    m_registrant = _m_registrant;
    m_contacts = _m_contacts;
    m_auth_info = _m_auth_info;
  } // ctor

  /**
   * Accessor method for the common and generic command element
   * @param value The command element
   * @see #m_cmd
   */
  public void setCmd(org.openrtk.idl.epp0503.epp_Command value) { m_cmd = value; }
  /**
   * Accessor method for the common and generic command element
   * @return The command element
   * @see #m_cmd
   */
  public org.openrtk.idl.epp0503.epp_Command getCmd() { return m_cmd; }

  /**
   * Accessor method for the name of the domain object to be created in the registry
   * @param value The domain name
   * @see #m_name
   */
  public void setName(String value) { m_name = value; }
  /**
   * Accessor method for the name of the domain object to be created in the registry
   * @return The domain name
   * @see #m_name
   */
  public String getName() { return m_name; }

  /**
   * Accessor method for the registration period of the domain object
   * @param value The domain period
   * @see #m_period
   */
  public void setPeriod(org.openrtk.idl.epp0503.domain.epp_DomainPeriod value) { m_period = value; }
  /**
   * Accessor method for the registration period of the domain object
   * @return The domain period
   * @see #m_period
   */
  public org.openrtk.idl.epp0503.domain.epp_DomainPeriod getPeriod() { return m_period; }

  /**
   * Accessor method for the array of name servers to be associated with the domain object
   * @param value The array of domain name servers
   * @see #m_name_servers
   */
  public void setNameServers(String[] value) { m_name_servers = value; }
  /**
   * Accessor method for the array of name servers to be associated with the domain object
   * @return The array of domain name servers
   * @see #m_name_servers
   */
  public String[] getNameServers() { return m_name_servers; }

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
   * Accessor method for the array of contacts to be associated with the domain object
   * @param value The array of domain contacts
   * @see #m_contacts
   */
  public void setContacts(org.openrtk.idl.epp0503.domain.epp_DomainContact[] value) { m_contacts = value; }
  /**
   * Accessor method for the array of contacts to be associated with the domain object
   * @return The array of domain contacts
   * @see #m_contacts
   */
  public org.openrtk.idl.epp0503.domain.epp_DomainContact[] getContacts() { return m_contacts; }

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
  public String toString() { return this.getClass().getName() + ": { m_cmd ["+m_cmd+"] m_name ["+m_name+"] m_period ["+m_period+"] m_name_servers ["+(m_name_servers != null ? java.util.Arrays.asList(m_name_servers) : null)+"] m_registrant ["+m_registrant+"] m_contacts ["+(m_contacts != null ? java.util.Arrays.asList(m_contacts) : null)+"] m_auth_info ["+m_auth_info+"] }"; }

} // class epp_DomainCreateReq
