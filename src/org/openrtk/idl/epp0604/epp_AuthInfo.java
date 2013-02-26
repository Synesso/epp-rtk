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
 * Class that contains the authorization information required to authorize transfer
 * requests to the EPP server.</p>
 * The authorization information must be provided by client when creating EPP Domain
 * and Contact objects.</p>
 * Currently, only one type is permitted: PW (password).</p>
 * $Header: /cvsroot/epp-rtk/epp-rtk/java/src/org/openrtk/idl/epp0604/epp_AuthInfo.java,v 1.1 2003/03/21 15:53:44 tubadanm Exp $<br>
 * $Revision: 1.1 $<br>
 * $Date: 2003/03/21 15:53:44 $<br>
 * @see org.openrtk.idl.epp0604.epp_TransferRequest
 * @see org.openrtk.idl.epp0604.domain.epp_DomainCreateReq
 * @see org.openrtk.idl.epp0604.domain.epp_DomainUpdateChange
 */
public class epp_AuthInfo implements org.omg.CORBA.portable.IDLEntity
{
  /**
   * The authorization information type.
   * @see #setType(org.openrtk.idl.epp0604.epp_AuthInfoType)
   * @see #getType()
   */
  public org.openrtk.idl.epp0604.epp_AuthInfoType m_type = null;
  /**
   * The authorizing object's repository object identifier.
   * Only used in transfer requests and only if the registry 
   * permits "third-party" authorization for a transfer.  That is,
   * for example, using an admin contact's auth info to authorize a
   * transfer of a domain to which it is associated.  Note, that
   * no currently existing registries support this option.
   * @see #setRoid(String)
   * @see #getRoid()
   */
  public String m_roid = null;
  /**
   * The authorizing object's passphrase.
   * If the m_roid value is null, then the current object is the 
   * authorizing object.
   * @see #setValue(String)
   * @see #getValue()
   */
  public String m_value = null;

  /**
   * Empty constructor
   */
  public epp_AuthInfo ()
  {
  } // ctor

  /**
   * The constructor with initializing variables.
   * @param _m_type The authorization information type
   * @param _m_roid The authorizing object's repository object identifier
   * @param _m_value The authorizing object's passphrase
   */
  public epp_AuthInfo (org.openrtk.idl.epp0604.epp_AuthInfoType _m_type, String _m_roid, String _m_value)
  {
    m_type = _m_type;
    m_roid = _m_roid;
    m_value = _m_value;
  } // ctor

  /**
   * Accessor method for the authorization information type
   * @param value The authorization information type
   * @see #m_type
   */
  public void setType(org.openrtk.idl.epp0604.epp_AuthInfoType value) { m_type = value; }
  /**
   * Accessor method for the authorization information type
   * @return The authorization information type
   * @see #m_type
   */
  public org.openrtk.idl.epp0604.epp_AuthInfoType getType() { return m_type; }

  /**
   * Accessor method for the authorizing object's repository object identifier
   * @param value The authorizing object's roid
   * @see #m_roid
   */
  public void setRoid(String value) { m_roid = value; }
  /**
   * Accessor method for the authorizing object's repository object identifier
   * @return The authorizing object's roid
   * @see #m_roid
   */
  public String getRoid() { return m_roid; }

  /**
   * Accessor method for the authorizing object's passphrase
   * @param value The authorizing object's passphrase
   * @see #m_value
   */
  public void setValue(String value) { m_value = value; }
  /**
   * Accessor method for the authorizing object's passphrase
   * @return The authorizing object's passphrase
   * @see #m_value
   */
  public String getValue() { return m_value; }

  /**
   * Converts this class into a string.
   * Typically used to view the object in debug output.
   * @return The string representation of this object instance
   */
  public String toString() { return this.getClass().getName() + ": { m_type ["+m_type+"] m_roid ["+m_roid+"] m_value ["+m_value+"] }"; }

} // class epp_AuthInfo
