/*
**
** EPP RTK Java
** Copyright (C) 2002, Tucows, Inc.
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
 * Class that contains elements used to describe EPP Server's policy for data
 * collection and management.</p>
 * $Header: /cvsroot/epp-rtk/epp-rtk/java/src/org/openrtk/idl/epp0604/epp_DataCollectionPolicy.java,v 1.2 2003/09/10 21:29:57 tubadanm Exp $<br>
 * $Revision: 1.2 $<br>
 * $Date: 2003/09/10 21:29:57 $<br>
 * @see org.openrtk.idl.epp0604.epp_Greeting
 * @see com.tucows.oxrs.epp0604.rtk.xml.EPPGreeting
 */
public class epp_DataCollectionPolicy implements org.omg.CORBA.portable.IDLEntity
{
  /**
   * The access type.
   * @see #setAccess(epp_dcpAccessType)
   * @see #getAccess()
   */
  public org.openrtk.idl.epp0604.epp_dcpAccessType m_access = null;
  /**
   * The array of statement elements.
   * @see #setStatements(epp_dcpStatement[])
   * @see #getStatements()
   */
  public org.openrtk.idl.epp0604.epp_dcpStatement m_statements[] = null;

  public org.openrtk.idl.epp0604.epp_dcpExpiry m_expiry = null;

  /**
   * Empty constructor
   */
  public epp_DataCollectionPolicy ()
  {
  } // ctor

  /**
   * The constructor with initializing variables.
   * @param _m_access The access type
   * @param _m_statements The array of statement elements
   */
  public epp_DataCollectionPolicy (org.openrtk.idl.epp0604.epp_dcpAccessType _m_access, org.openrtk.idl.epp0604.epp_dcpStatement[] _m_statements, org.openrtk.idl.epp0604.epp_dcpExpiry _m_expiry)
  {
    m_access = _m_access;
    m_statements = _m_statements;
    m_expiry = _m_expiry;
  } // ctor

  /**
   * Accessor method for the access type
   * @param value The access type
   * @see #m_access
   */
  public void setAccess(org.openrtk.idl.epp0604.epp_dcpAccessType value) { m_access = value; }
  /**
   * Accessor method for the access type
   * @return value The access type
   * @see #m_access
   */
  public org.openrtk.idl.epp0604.epp_dcpAccessType getAccess() { return m_access; }

  /**
   * Accessor method for the array of statement elements
   * @param value The array of statement elements
   * @see #m_statements
   */
  public void setStatements(org.openrtk.idl.epp0604.epp_dcpStatement[] value) { m_statements = value; }
  /**
   * Accessor method for the array of statement elements
   * @return value The array of statement elements
   * @see #m_statements
   */
  public org.openrtk.idl.epp0604.epp_dcpStatement[] getStatements() { return m_statements; }

  public void setExpiry(org.openrtk.idl.epp0604.epp_dcpExpiry value) { m_expiry = value; }
  public org.openrtk.idl.epp0604.epp_dcpExpiry getExpiry() { return m_expiry; }

  /**
   * Converts this class into a string.
   * Typically used to view the object in debug output.
   * @return The string representation of this object instance
   */
  public String toString() { return this.getClass().getName() + ": { m_access ["+m_access+"] m_statements ["+(m_statements != null ? java.util.Arrays.asList(m_statements) : null)+"] m_expiry ["+m_expiry+"] }"; }

} // class epp_DataCollectionPolicy
