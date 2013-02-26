/*
**
** EPP RTK Java
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

package org.openrtk.idl.epp0705.domain;


/**
 * This class is use exclusively to hold data associate with a Poll response for a pending action notification.</p>
 * $Header: /cvsroot/epp-rtk/epp-rtk/java/src/org/openrtk/idl/epp0705/domain/epp_DomainPanData.java,v 1.1 2003/03/20 22:42:30 tubadanm Exp $<br>
 * $Revision: 1.1 $<br>
 * $Date: 2003/03/20 22:42:30 $<br>
 */
public class epp_DomainPanData extends org.openrtk.idl.epp0705.epp_PanData implements org.openrtk.idl.epp0705.epp_PollResData 
{

  /**
   * Constant variable defined by IDLs.</p>
   * Used when this classes is used to hold the poll response
   * data pertaining to domain pending action notifications.  The value
   * of this constant links this class to a set of poll response
   * data.
   */
  public static final String m_type = "domain:panData";
  /**
   * Access method for the m_type constant.
   * @see #m_type
   */
  public String getType() { return m_type; }

  /**
   * The name of the domain for this pending action notification
   */
  public String m_name;

  /**
   * Empty constructor
   */
  public epp_DomainPanData ()
  {
  } // ctor

  /**
   * The constructor with initializing variables.
   * @param _m_name The name of the domain object in the registry
   * @param _m_result The boolean result flag for the action: true == successful execution
   * @param _m_trid The server and client trid pair associated with this pending action
   * @param _m_date The date the pending action completed (or failed)
   */
  public epp_DomainPanData (String _m_name, boolean _m_result, org.openrtk.idl.epp0705.epp_TransID _m_trid, String _m_date)
  {
    super( _m_result, _m_trid, _m_date );
    m_name = _m_name;
  } // ctor

  /**
   * Accessor method for the name of the domain object in the registry
   * @param value The domain name
   * @see #m_name
   */
  public void setName(String value) { m_name = value; }
  /**
   * Accessor method for the name of the domain object in the registry
   * @return The domain name
   * @see #m_name
   */
  public String getName() { return m_name; }

  /**
   * Converts this class into a string.
   * Typically used to view the object in debug output.
   * @return The string representation of this object instance
   */
  public String toString() { return this.getClass().getName() + ": { m_name ["+m_name+"] m_result ["+m_result+"] m_trid ["+m_trid+"] m_date ["+m_date+"] }"; }

} // interface epp_DomainPanData
