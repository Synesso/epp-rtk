/*
**
** EPP RTK Java
** Copyright (C) 2001-2002, Tucows, Inc.
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
 * Class that contains elements used to describe the services supported by the 
 * EPP registry server.</p>
 * $Header: /cvsroot/epp-rtk/epp-rtk/java/src/org/openrtk/idl/epp0705/epp_ServiceMenu.java,v 1.2 2003/09/10 21:29:58 tubadanm Exp $<br>
 * $Revision: 1.2 $<br>
 * $Date: 2003/09/10 21:29:58 $<br>
 * @see org.openrtk.idl.epp0705.epp_Greeting
 * @see com.tucows.oxrs.epp0705.rtk.xml.EPPGreeting
 */
public class epp_ServiceMenu implements org.omg.CORBA.portable.IDLEntity
{
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
   * @see #setServices(String[])
   * @see #getServices()
   */
  public String m_services[] = null;
  /**
   * The array of object extensions supported by the server.
   * @see #setExtensions(String[])
   * @see #getExtensions()
   */
  public String m_extensions[] = null;

  /**
   * Empty constructor
   */
  public epp_ServiceMenu ()
  {
  } // ctor

  /**
   * The constructor with initializing variables.
   * @param _m_versions The array of protocol versions supported by the server
   * @param _m_langs The array of languages supported by the server
   * @param _m_services The array of services offered by the server
   * @param _m_extensions The array of object extensions supported by the server
   */
  public epp_ServiceMenu (String[] _m_versions, String[] _m_langs, String[] _m_services, String[] _m_extensions)
  {
    m_versions = _m_versions;
    m_langs = _m_langs;
    m_services = _m_services;
    m_extensions = _m_extensions;
  } // ctor

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
  public void setServices(String[] value) { m_services = value; }
  /**
   * Accessor method for the array of services offered by the server
   * @return The array of services
   * @see #m_services
   */
  public String[] getServices() { return m_services; }

  /**
   * Accessor method for the array of object extensions supported by the server
   * @param value The array of service extensions
   * @see #m_extensions
   */
  public void setExtensions(String[] value) { m_extensions = value; }
  /**
   * Accessor method for the array of object extensions supported by the server
   * @return The array of service extensions
   * @see #m_extensions
   */
  public String[] getExtensions() { return m_extensions; }

  /**
   * Converts this class into a string.
   * Typically used to view the object in debug output.
   * @return The string representation of this object instance
   */
  public String toString() { return this.getClass().getName() + ": { m_versions ["+(m_versions != null ? java.util.Arrays.asList(m_versions) : null)+"] m_langs ["+(m_langs != null ? java.util.Arrays.asList(m_langs) : null)+"] m_services ["+(m_services != null ? java.util.Arrays.asList(m_services) : null)+"] m_extensions ["+(m_extensions != null ? java.util.Arrays.asList(m_extensions) : null)+"] }"; }

} // class epp_ServiceMenu
