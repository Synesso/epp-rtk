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
 * Class that contains elements to state the EPP server's abilities.</p>
 * The greeting returned by the server is in response to a successful connection
 * or the hello request initiated by the client.</p>
 * It lists the languages and protocol versions supported by the server and the
 * services offered by the server.</p>
 * $Header: /cvsroot/epp-rtk/epp-rtk/java/src/org/openrtk/idl/epp0402/epp_Greeting.java,v 1.2 2003/09/10 21:29:55 tubadanm Exp $<br>
 * $Revision: 1.2 $<br>
 * $Date: 2003/09/10 21:29:55 $<br>
 * @see com.tucows.oxrs.epp0402.rtk.xml.EPPGreeting#getResponseData()
 * @see com.tucows.oxrs.epp0402.rtk.EPPClient#getGreeting()
 * @see com.tucows.oxrs.epp0402.rtk.EPPClient#hello()
 */
public class epp_Greeting implements org.omg.CORBA.portable.IDLEntity
{
  /**
   * The name of the server.
   * @see #setServerId(String)
   * @see #getServerId()
   */
  public String m_server_id = null;
  /**
   * The server's current date and time in UTC.
   * @see #setServerDate(String)
   * @see #getServerDate()
   */
  public String m_server_date = null;
  /**
   * The array of protocol versions supported by the server.
   * @see #setVersions(String[])
   * @see #getVersions()
   */
  public String m_versions[] = null;
  /**
   * The array of languages supported by the server.
   * @see #setLangs(String[])
   * @see #getLangs()
   */
  public String m_langs[] = null;
  /**
   * The array of services offered by the server.
   * @see #setServices(org.openrtk.idl.epp0402.epp_Service[])
   * @see #getServices()
   */
  public org.openrtk.idl.epp0402.epp_Service m_services[] = null;
  /**
   * The array of custom object extensions supported by the server.
   * @see #setUnspecServices(org.openrtk.idl.epp0402.epp_Service[])
   * @see #getUnspecServices()
   */
  public org.openrtk.idl.epp0402.epp_Service m_unspec_services[] = null;

  /**
   * Empty constructor
   */
  public epp_Greeting ()
  {
  } // ctor

  /**
   * The constructor with initializing variables.
   * @param _m_server_id The name of the server
   * @param _m_server_date The server's current date and time in UTC
   * @param _m_versions The array of protocol versions supported by the server
   * @param _m_langs The array of languages supported by the server
   * @param _m_services The array of services offered by the server
   * @param _m_unspec_services The array of custom object extensions supported by the server
   */
  public epp_Greeting (String _m_server_id, String _m_server_date, String[] _m_versions, String[] _m_langs, org.openrtk.idl.epp0402.epp_Service[] _m_services, org.openrtk.idl.epp0402.epp_Service[] _m_unspec_services)
  {
    m_server_id = _m_server_id;
    m_server_date = _m_server_date;
    m_versions = _m_versions;
    m_langs = _m_langs;
    m_services = _m_services;
    m_unspec_services = _m_unspec_services;
  } // ctor

  /**
   * Accessor method for the name of the server
   * @param value The name of the server
   * @see #m_server_id
   */
  public void setServerId(String value) { m_server_id = value; }
  /**
   * Accessor method for the name of the server
   * @return The name of the server
   * @see #m_server_id
   */
  public String getServerId() { return m_server_id; }

  /**
   * Accessor method for the server's current date and time in UTC
   * @param value The server's current date and time
   * @see #m_server_date
   */
  public void setServerDate(String value) { m_server_date = value; }
  /**
   * Accessor method for the server's current date and time in UTC
   * @return The server's current date and time
   * @see #m_server_date
   */
  public String getServerDate() { return m_server_date; }

  /**
   * Accessor method for the array of protocol versions supported by the server
   * @param value The array of protocol versions
   * @see #m_versions
   */
  public void setVersions(String[] value) { m_versions = value; }
  /**
   * Accessor method for the array of protocol versions supported by the server
   * @return The array of protocol versions
   * @see #m_versions
   */
  public String[] getVersions() { return m_versions; }

  /**
   * Accessor method for the array of languages supported by the server
   * @param value The array of languages supported by the server
   * @see #m_langs
   */
  public void setLangs(String[] value) { m_langs = value; }
  /**
   * Accessor method for the array of languages supported by the server
   * @return The array of languages supported by the server
   * @see #m_langs
   */
  public String[] getLangs() { return m_langs; }

  /**
   * Accessor method for the array of services offered by the server
   * @param value The array of services
   * @see #m_services
   */
  public void setServices(org.openrtk.idl.epp0402.epp_Service[] value) { m_services = value; }
  /**
   * Accessor method for the array of services offered by the server
   * @return The array of services
   * @see #m_services
   */
  public org.openrtk.idl.epp0402.epp_Service[] getServices() { return m_services; }

  /**
   * Accessor method for the array of custom object extensions supported by the server
   * @param value The array of service extensions
   * @see #m_unspec_services
   */
  public void setUnspecServices(org.openrtk.idl.epp0402.epp_Service[] value) { m_unspec_services = value; }
  /**
   * Accessor method for the array of custom object extensions supported by the server
   * @return The array of service extensions
   * @see #m_unspec_services
   */
  public org.openrtk.idl.epp0402.epp_Service[] getUnspecServices() { return m_unspec_services; }

  /**
   * Converts this class into a string.
   * Typically used to view the object in debug output.
   * @return The string representation of this object instance
   */
  public String toString() { return this.getClass().getName() + ": { m_server_id ["+m_server_id+"] m_server_date ["+m_server_date+"] m_versions ["+(m_versions != null ? java.util.Arrays.asList(m_versions) : null)+"] m_langs ["+(m_langs != null ? java.util.Arrays.asList(m_langs) : null)+"] m_services ["+(m_services != null ? java.util.Arrays.asList(m_services) : null)+"] m_unspec_services ["+(m_unspec_services != null ? java.util.Arrays.asList(m_unspec_services) : null)+"] }"; }

} // class epp_Greeting
