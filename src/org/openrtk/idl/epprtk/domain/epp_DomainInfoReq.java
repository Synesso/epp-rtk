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

package org.openrtk.idl.epprtk.domain;


/**
 * Class that contains the elements necessary to retrieve detailed
 * information associated with a domain in the registry.</p>
 * $Header: /cvsroot/epp-rtk/epp-rtk/java/src/org/openrtk/idl/epprtk/domain/epp_DomainInfoReq.java,v 1.1 2004/12/07 15:27:50 ewang2004 Exp $<br>
 * $Revision: 1.1 $<br>
 * $Date: 2004/12/07 15:27:50 $<br>
 * @see com.tucows.oxrs.epprtk.rtk.xml.EPPDomainInfo
 * @see org.openrtk.idl.epprtk.domain.epp_DomainInfoRsp
 */
public class epp_DomainInfoReq implements org.omg.CORBA.portable.IDLEntity
{
  /**
   * The common and generic command element.
   * @see #setCmd(org.openrtk.idl.epprtk.epp_Command)
   * @see #getCmd()
   */
  public org.openrtk.idl.epprtk.epp_Command m_cmd = null;
  /**
   * The name of the domain object to be queried.
   * @see #setName(String)
   * @see #getName()
   */
  public String m_name = null;
  /**
   * The types of hosts you would like to see returned by the info command.
   * @see #setHostsType(epp_DomainHostsType)
   * @see #getHostsType()
   */
  public org.openrtk.idl.epprtk.domain.epp_DomainHostsType m_hosts_type = null;
  /**
   * The authorization information required to authorize object transfers.
   * @see #setAuthInfo(org.openrtk.idl.epprtk.epp_AuthInfo)
   * @see #getAuthInfo()
   */
  public org.openrtk.idl.epprtk.epp_AuthInfo m_auth_info = null;

  /**
   * Empty constructor
   */
  public epp_DomainInfoReq ()
  {
  } // ctor

  /**
   * The constructor with initializing variables.
   * The hosts type parameter tells the server how much
   * host information to return in the response.
   * @param _m_cmd The common and generic command element
   * @param _m_name The name of the domain object to be queried
   * @param _m_hosts_type The type of hosts of the domain object to be queried
   * @deprecated
   */
  public epp_DomainInfoReq (org.openrtk.idl.epprtk.epp_Command _m_cmd, String _m_name, org.openrtk.idl.epprtk.domain.epp_DomainHostsType _m_hosts_type)
  {
    m_cmd = _m_cmd;
    m_name = _m_name;
    m_hosts_type = _m_hosts_type;
  } // ctor

  /**
   * The constructor with initializing variables.
   * The hosts type parameter tells the server how much
   * host information to return in the response.
   * @param _m_cmd The common and generic command element
   * @param _m_name The name of the domain object to be queried
   * @param _m_hosts_type The type of hosts of the domain object to be queried
   * @param _m_auth_info The authorization information required to authorize object transfers
   */
  public epp_DomainInfoReq (org.openrtk.idl.epprtk.epp_Command _m_cmd, String _m_name, org.openrtk.idl.epprtk.domain.epp_DomainHostsType _m_hosts_type, org.openrtk.idl.epprtk.epp_AuthInfo _m_auth_info)
  {
    m_cmd = _m_cmd;
    m_name = _m_name;
    m_hosts_type = _m_hosts_type;
    m_auth_info = _m_auth_info;
  } // ctor

  /**
   * Accessor method for the common and generic command element
   * @param value The command element
   * @see #m_cmd
   */
  public void setCmd(org.openrtk.idl.epprtk.epp_Command value) { m_cmd = value; }
  /**
   * Accessor method for the common and generic command element
   * @return The command element
   * @see #m_cmd
   */
  public org.openrtk.idl.epprtk.epp_Command getCmd() { return m_cmd; }

  /**
   * Accessor method for the name of the domain object to be queried
   * @param value The domain name
   * @see #m_name
   */
  public void setName(String value) { m_name = value; }
  /**
   * Accessor method for the name of the domain object to be queried
   * @return The domain name
   * @see #m_name
   */
  public String getName() { return m_name; }

  /**
   * Accessor method for the hosts type of the domain object to be queried
   * @value value The hosts type
   * @see #m_hosts_type
   */
  public void setHostsType(org.openrtk.idl.epprtk.domain.epp_DomainHostsType value) { m_hosts_type = value; }
  /**
   * Accessor method for the hosts type of the domain object to be queried
   * @return The hosts type
   * @see #m_hosts_type
   */
  public org.openrtk.idl.epprtk.domain.epp_DomainHostsType getHostsType() { return m_hosts_type; }

  /**
   * Accessor method for the authorization information
   * @param value The authorization information
   * @see #m_auth_info
   */
  public void setAuthInfo(org.openrtk.idl.epprtk.epp_AuthInfo value) { m_auth_info = value; }
  /**
   * Accessor method for the authorization information
   * @return The authorization information
   * @see #m_auth_info
   */
  public org.openrtk.idl.epprtk.epp_AuthInfo getAuthInfo() { return m_auth_info; }

  /**
   * Converts this class into a string.
   * Typically used to view the object in debug output.
   * @return The string representation of this object instance
   */
  public String toString() { return this.getClass().getName() + ": { m_cmd ["+m_cmd+"] m_name ["+m_name+"] m_hosts_type ["+m_hosts_type+"] m_auth_info ["+m_auth_info+"] }"; }

} // class epp_DomainInfoReq
