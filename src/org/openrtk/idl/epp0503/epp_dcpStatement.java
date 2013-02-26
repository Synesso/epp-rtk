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

package org.openrtk.idl.epp0503;

/**
 * Class that contains elements used to describe EPP Server's policy for data
 * collection and management.</p>
 * Data collection purposes, data recipients, and data retention are described here.</p>
 * $Header: /cvsroot/epp-rtk/epp-rtk/java/src/org/openrtk/idl/epp0503/epp_dcpStatement.java,v 1.2 2003/09/10 21:29:56 tubadanm Exp $<br>
 * $Revision: 1.2 $<br>
 * $Date: 2003/09/10 21:29:56 $<br>
 * @see org.openrtk.idl.epp0503.epp_DataCollectionPolicy
 * @see com.tucows.oxrs.epp0503.rtk.xml.EPPGreeting
 */
public class epp_dcpStatement implements org.omg.CORBA.portable.IDLEntity
{
  /**
   * The array of data collection purpose types.
   * @see #setPurposes(epp_dcpPurposeType[])
   * @see #getPurposes()
   */
  public org.openrtk.idl.epp0503.epp_dcpPurposeType m_purposes[] = null;
  /**
   * The array of data recipient types.
   * @see #setRecipients(epp_dcpRecipientType[])
   * @see #getRecipients()
   */
  public org.openrtk.idl.epp0503.epp_dcpRecipientType m_recipients[] = null;
  /**
   * The data retention type.
   * @see #setRetention(epp_dcpRetentionType)
   * @see #getRetention()
   */
  public org.openrtk.idl.epp0503.epp_dcpRetentionType m_retention = null;

  /**
   * Empty constructor
   */
  public epp_dcpStatement ()
  {
  } // ctor

  /**
   * The constructor with initializing variables.
   * @param _m_purposes The array of data collection purpose types
   * @param _m_recipients The array of data recipient types
   * @param _m_retention The data retention type
   */
  public epp_dcpStatement (org.openrtk.idl.epp0503.epp_dcpPurposeType[] _m_purposes, org.openrtk.idl.epp0503.epp_dcpRecipientType[] _m_recipients, org.openrtk.idl.epp0503.epp_dcpRetentionType _m_retention)
  {
    m_purposes = _m_purposes;
    m_recipients = _m_recipients;
    m_retention = _m_retention;
  } // ctor

  /**
   * Accessor method for the array of data collection purpose types
   * @param value The array of data collection purpose types
   * @see #m_purposes
   */
  public void setPurposes(org.openrtk.idl.epp0503.epp_dcpPurposeType[] value) { m_purposes = value; }
  /**
   * Accessor method for the array of data collection purpose types
   * @return The array of data collection purpose types
   * @see #m_purposes
   */
  public org.openrtk.idl.epp0503.epp_dcpPurposeType[] getPurposes() { return m_purposes; }
  /**
   * Accessor method for the array of data recipient types
   * @param value The array of data recipient types
   * @see #m_recipients
   */
  public void setRecipients(org.openrtk.idl.epp0503.epp_dcpRecipientType[] value) { m_recipients = value; }
  /**
   * Accessor method for the array of data recipient types
   * @return The array of data recipient types
   * @see #m_recipients
   */
  public org.openrtk.idl.epp0503.epp_dcpRecipientType[] getRecipients() { return m_recipients; }
  /**
   * Accessor method for the data retention type
   * @param value The data retention type
   * @see #m_retention
   */
  public void setRetention(org.openrtk.idl.epp0503.epp_dcpRetentionType value) { m_retention = value; }
  /**
   * Accessor method for the data retention type
   * @return value The data retention type
   * @see #m_retention
   */
  public org.openrtk.idl.epp0503.epp_dcpRetentionType getRetention() { return m_retention; }

  /**
   * Converts this class into a string.
   * Typically used to view the object in debug output.
   * @return The string representation of this object instance
   */
  public String toString() { return this.getClass().getName() + ": { m_purposes ["+(m_purposes != null ? java.util.Arrays.asList(m_purposes) : null)+"] m_recipients ["+(m_recipients != null ? java.util.Arrays.asList(m_recipients) : null)+"] m_retention ["+m_retention+"] }"; }

} // class epp_dcpStatement
