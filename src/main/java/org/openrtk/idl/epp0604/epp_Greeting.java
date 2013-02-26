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

package org.openrtk.idl.epp0604;


/**
 * Class that contains elements to state the EPP server's abilities.</p>
 * The greeting returned by the server is in response to a successful connection
 * or the hello request initiated by the client.</p>
 * It lists the languages and protocol versions supported by the server, the
 * services and data collection policy offered by the server.</p>
 * $Header: /cvsroot/epp-rtk/epp-rtk/java/src/org/openrtk/idl/epp0604/epp_Greeting.java,v 1.2 2003/09/10 21:29:57 tubadanm Exp $<br>
 * $Revision: 1.2 $<br>
 * $Date: 2003/09/10 21:29:57 $<br>
 * @see com.tucows.oxrs.epp0604.rtk.xml.EPPGreeting#getResponseData()
 * @see com.tucows.oxrs.epp0604.rtk.EPPClient#getGreeting()
 * @see com.tucows.oxrs.epp0604.rtk.EPPClient#hello()
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
   * The services offered by the server.
   * @see #setSvcMenu(org.openrtk.idl.epp0604.epp_ServiceMenu)
   * @see #getSvcMenu()
   */
  public org.openrtk.idl.epp0604.epp_ServiceMenu m_svc_menu = null;
  /**
   * The data collection policy of the server.
   * @see #setDcp(org.openrtk.idl.epp0604.epp_DataCollectionPolicy)
   * @see #getDcp()
   */
  public org.openrtk.idl.epp0604.epp_DataCollectionPolicy m_dcp = null;;

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
   * @param _m_svc_menu The services offered by the server
   * @param _m_dcp The data collection policy of the server
   */
  public epp_Greeting (String _m_server_id, String _m_server_date, org.openrtk.idl.epp0604.epp_ServiceMenu _m_svc_menu, org.openrtk.idl.epp0604.epp_DataCollectionPolicy _m_dcp)
  {
    m_server_id = _m_server_id;
    m_server_date = _m_server_date;
    m_svc_menu = _m_svc_menu;
    m_dcp = _m_dcp;
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
   * Accessor method for the services offered by the server
   * @param value The service element
   * @see #m_svc_menu
   */
  public void setSvcMenu(org.openrtk.idl.epp0604.epp_ServiceMenu value) { m_svc_menu = value; }
  /**
   * Accessor method for the services offered by the server
   * @return The service element
   * @see #m_svc_menu
   */
  public org.openrtk.idl.epp0604.epp_ServiceMenu getSvcMenu() { return m_svc_menu; }

  /**
   * Accessor method for the data collection policy of the server
   * @param value The data collection policy of the server
   * @see #m_dcp
   */
  public void setDcp(org.openrtk.idl.epp0604.epp_DataCollectionPolicy value) { m_dcp = value; }
  /**
   * Accessor method for the data collection policy of the server
   * @return The data collection policy of the server
   * @see #m_dcp
   */
  public org.openrtk.idl.epp0604.epp_DataCollectionPolicy getDcp() { return m_dcp; }

  /**
   * Converts this class into a string.
   * Typically used to view the object in debug output.
   * @return The string representation of this object instance
   */
  public String toString() { return this.getClass().getName() + ": { m_server_id ["+m_server_id+"] m_server_date ["+m_server_date+"] m_svc_menu ["+m_svc_menu+"] m_dcp ["+m_dcp+"] }"; }
} // class epp_Greeting
