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
 * Class that contains the elements necessary to delete a domain
 * in the registry.</p>
 * $Header: /cvsroot/epp-rtk/epp-rtk/java/src/org/openrtk/idl/epp0604/domain/epp_DomainDeleteReq.java,v 1.2 2003/09/10 21:29:58 tubadanm Exp $<br>
 * $Revision: 1.2 $<br>
 * $Date: 2003/09/10 21:29:58 $<br>
 * @see com.tucows.oxrs.epp0604.rtk.xml.EPPDomainDelete
 * @see org.openrtk.idl.epp0604.domain.epp_DomainDeleteRsp
 */
public class epp_DomainDeleteReq implements org.omg.CORBA.portable.IDLEntity
{
  /**
   * The common and generic command element.
   * @see #setCmd(org.openrtk.idl.epp0604.epp_Command)
   * @see #getCmd()
   */
  public org.openrtk.idl.epp0604.epp_Command m_cmd = null;
  /**
   * The name of the domain object to be deleted in the registry
   * @see #setName(String)
   * @see #getName()
   */
  public String m_name = null;

  /**
   * Empty constructor
   */
  public epp_DomainDeleteReq ()
  {
  } // ctor

  /**
   * The constructor with initializing variables.
   * @param _m_cmd The common and generic command element
   * @param _m_name The name of the domain object to be deleted in the registry
   */
  public epp_DomainDeleteReq (org.openrtk.idl.epp0604.epp_Command _m_cmd, String _m_name)
  {
    m_cmd = _m_cmd;
    m_name = _m_name;
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
   * Accessor method for the name of the domain object to be deleted in the registry
   * @param value The domain name
   * @see #m_name
   */
  public void setName(String value) { m_name = value; }
  /**
   * Accessor method for the name of the domain object to be deleted in the registry
   * @return The domain name
   * @see #m_name
   */
  public String getName() { return m_name; }

  /**
   * Converts this class into a string.
   * Typically used to view the object in debug output.
   * @return The string representation of this object instance
   */
  public String toString() { return this.getClass().getName() + ": { m_cmd ["+m_cmd+"] m_name ["+m_name+"] }"; }

} // class epp_DomainDeleteReq
