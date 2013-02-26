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

package org.openrtk.idl.epp0604.domain;


/**
 * Class that contains the elements necessary to transfer a domain
 * in the registry or to query the transfer status from the registry.</p>
 * $Header: /cvsroot/epp-rtk/epp-rtk/java/src/org/openrtk/idl/epp0604/domain/epp_DomainTransferReq.java,v 1.2 2003/09/10 21:29:58 tubadanm Exp $<br>
 * $Revision: 1.2 $<br>
 * $Date: 2003/09/10 21:29:58 $<br>
 * @see com.tucows.oxrs.epp0604.rtk.xml.EPPDomainTransfer
 * @see org.openrtk.idl.epp0604.domain.epp_DomainTransferRsp
 */
public class epp_DomainTransferReq implements org.omg.CORBA.portable.IDLEntity
{
  /**
   * The common and generic command element.
   * @see #setCmd(org.openrtk.idl.epp0604.epp_Command)
   * @see #getCmd()
   */
  public org.openrtk.idl.epp0604.epp_Command m_cmd = null;
  /**
   * The common and gereric transfer request element.
   * The element encapsulates the authorization information and the transfer operation type.
   * @see #setTrans(org.openrtk.idl.epp0604.epp_TransferRequest)
   * @see #getTrans()
   */
  public org.openrtk.idl.epp0604.epp_TransferRequest m_trans = null;
  /**
   * The name of the domain object for the transfer request.
   * @see #setName(String)
   * @see #getName()
   */
  public String m_name = null;
  /**
   * The number of units to be added to the registration period of the domain object.
   * @see #setPeriod(org.openrtk.idl.epp0604.domain.epp_DomainPeriod)
   * @see #getPeriod()
   */
  public org.openrtk.idl.epp0604.domain.epp_DomainPeriod m_period = null;

  /**
   * Empty constructor
   */
  public epp_DomainTransferReq ()
  {
  } // ctor

  /**
   * The constructor with initializing variables.
   * @param _m_cmd The common and generic command element
   * @param _m_trans The common and gereric transfer request element
   * @param _m_name The name of the domain object for the transfer request
   * @param _m_period The number of units to be added to the registration period of the domain object
   */
  public epp_DomainTransferReq (org.openrtk.idl.epp0604.epp_Command _m_cmd, org.openrtk.idl.epp0604.epp_TransferRequest _m_trans, String _m_name, org.openrtk.idl.epp0604.domain.epp_DomainPeriod _m_period)
  {
    m_cmd = _m_cmd;
    m_trans = _m_trans;
    m_name = _m_name;
    m_period = _m_period;
  } // ctor

  /**
   * Accessor method for the common and generic command element
   * @param value The command element
   * @see #m_cmd
   */
  public void setCmd(org.openrtk.idl.epp0604.epp_Command value) { m_cmd = value; }
  /**
   * Accessor method for the common and generic command element
   * @return The command element
   * @see #m_cmd
   */
  public org.openrtk.idl.epp0604.epp_Command getCmd() { return m_cmd; }

  /**
   * Accessor method for the common and gereric transfer request element
   * @param value The domain transfer request object
   * @see #m_trans
   */
  public void setTrans(org.openrtk.idl.epp0604.epp_TransferRequest value) { m_trans = value; }
  /**
   * Accessor method for the common and gereric transfer request element
   * @return The domain transfer request object
   * @see #m_trans
   */
  public org.openrtk.idl.epp0604.epp_TransferRequest getTrans() { return m_trans; }

  /**
   * Accessor method for the name of the domain object for the transfer request
   * @param value The domain name
   * @see #m_name
   */
  public void setName(String value) { m_name = value; }
  /**
   * Accessor method for the name of the domain object for the transfer request
   * @return The domain name
   * @see #m_name
   */
  public String getName() { return m_name; }

  /**
   * Accessor method for the number of units to be added to the registration period of the domain object
   * @param value The domain period
   * @see #m_period
   */
  public void setPeriod(org.openrtk.idl.epp0604.domain.epp_DomainPeriod value) { m_period = value; }
  /**
   * Accessor method for the number of units to be added to the registration period of the domain object
   * @return The domain period
   * @see #m_period
   */
  public org.openrtk.idl.epp0604.domain.epp_DomainPeriod getPeriod() { return m_period; }

  /**
   * Converts this class into a string.
   * Typically used to view the object in debug output.
   * @return The string representation of this object instance
   */
  public String toString() { return this.getClass().getName() + ": { m_cmd ["+m_cmd+"] m_trans ["+m_trans+"] m_name ["+m_name+"] m_period ["+m_period+"] }"; }

} // class epp_DomainTransferReq
