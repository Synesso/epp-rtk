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

package org.openrtk.idl.epprtk.domain;


/**
 * Class that contains the elements necessary to renew a domain
 * in the registry.</p>
 * $Header: /cvsroot/epp-rtk/epp-rtk/java/src/org/openrtk/idl/epprtk/domain/epp_DomainRenewReq.java,v 1.1 2004/12/07 15:27:50 ewang2004 Exp $<br>
 * $Revision: 1.1 $<br>
 * $Date: 2004/12/07 15:27:50 $<br>
 * @see com.tucows.oxrs.epprtk.rtk.xml.EPPDomainRenew
 * @see org.openrtk.idl.epprtk.domain.epp_DomainRenewRsp
 */
public class epp_DomainRenewReq implements org.omg.CORBA.portable.IDLEntity
{
  /**
   * The common and generic command element.
   * @see #setCmd(org.openrtk.idl.epprtk.epp_Command)
   * @see #getCmd()
   */
  public org.openrtk.idl.epprtk.epp_Command m_cmd = null;
  /**
   * The name of the domain object to be renewed in the registry.
   * @see #setName(String)
   * @see #getName()
   */
  public String m_name = null;
  /**
   * The current expiration date associated with the domain object.
   * @see #setCurrentExpirationDate(String)
   * @see #getCurrentExpirationDate()
   */
  public String m_current_expiration_date = null;
  /**
   * The number of units to be added to the registration period of the domain object.
   * @see #setPeriod(org.openrtk.idl.epprtk.domain.epp_DomainPeriod)
   * @see #getPeriod()
   */
  public org.openrtk.idl.epprtk.domain.epp_DomainPeriod m_period = null;

  /**
   * Empty constructor
   */
  public epp_DomainRenewReq ()
  {
  } // ctor

  /**
   * The constructor with initializing variables.
   * @param _m_cmd The common and generic command element
   * @param _m_name The name of the domain object to be renewed in the registry
   * @param _m_current_expiration_date The current expiration date associated with the domain object
   * @param _m_period The number of units to be added to the registration period of the domain object
   */
  public epp_DomainRenewReq (org.openrtk.idl.epprtk.epp_Command _m_cmd, String _m_name, String _m_current_expiration_date, org.openrtk.idl.epprtk.domain.epp_DomainPeriod _m_period)
  {
    m_cmd = _m_cmd;
    m_name = _m_name;
    m_current_expiration_date = _m_current_expiration_date;
    m_period = _m_period;
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
   * Accessor method for the name of the domain object to be renewed in the registry
   * @param value The domain name
   * @see #m_name
   */
  public void setName(String value) { m_name = value; }
  /**
   * Accessor method for the name of the domain object to be renewed in the registry
   * @return The domain name
   * @see #m_name
   */
  public String getName() { return m_name; }

  /**
   * Accessor method for the current expiration date associated with the domain object
   * @param value The domain current expiration date
   * @see #m_current_expiration_date
   */
  public void setCurrentExpirationDate(String value) { m_current_expiration_date = value; }
  /**
   * Accessor method for the current expiration date associated with the domain object
   * @return The domain current expiration date
   * @see #m_current_expiration_date
   */
  public String getCurrentExpirationDate() { return m_current_expiration_date; }

  /**
   * Accessor method for the number of units to be added to the registration period of the domain object
   * @param value The domain period
   * @see #m_period
   */
  public void setPeriod(org.openrtk.idl.epprtk.domain.epp_DomainPeriod value) { m_period = value; }
  /**
   * Accessor method for the number of units to be added to the registration period of the domain object
   * @return The domain period
   * @see #m_period
   */
  public org.openrtk.idl.epprtk.domain.epp_DomainPeriod getPeriod() { return m_period; }

  /**
   * Converts this class into a string.
   * Typically used to view the object in debug output.
   * @return The string representation of this object instance
   */
  public String toString() { return this.getClass().getName() + ": { m_cmd ["+m_cmd+"] m_name ["+m_name+"] m_current_expiration_date ["+m_current_expiration_date+"] m_period ["+m_period+"] }"; }

} // class epp_DomainRenewReq
