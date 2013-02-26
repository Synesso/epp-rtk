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

package org.openrtk.idl.epprtk.contact;


/**
 * This class is use exclusively to hold data associate with a Poll response for a pending action notification.</p>
 * $Header: /cvsroot/epp-rtk/epp-rtk/java/src/org/openrtk/idl/epprtk/contact/epp_ContactPanData.java,v 1.1 2004/12/07 15:27:49 ewang2004 Exp $<br>
 * $Revision: 1.1 $<br>
 * $Date: 2004/12/07 15:27:49 $<br>
 */
public class epp_ContactPanData extends org.openrtk.idl.epprtk.epp_PanData implements org.openrtk.idl.epprtk.epp_PollResData 
{

  /**
   * Constant variable defined by IDLs.</p>
   * Used when this classes is used to hold the poll response
   * data pertaining to contact pending action notifications.  The value
   * of this constant links this class to a set of poll response
   * data.
   */
  public static final String m_type = "contact:panData";
  /**
   * Access method for the m_type constant.
   * @see #m_type
   */
  public String getType() { return m_type; }

  /**
   * The client id of the contact for this pending action notification
   */
  public String m_id;

  /**
   * Empty constructor
   */
  public epp_ContactPanData ()
  {
  } // ctor

  /**
   * The constructor with initializing variables.
   * @param _m_id The name of the contact object in the registry
   * @param _m_result The boolean result flag for the action: true == successful execution
   * @param _m_trid The server and client trid pair associated with this pending action
   * @param _m_date The date the pending action completed (or failed)
   */
  public epp_ContactPanData (String _m_id, boolean _m_result, org.openrtk.idl.epprtk.epp_TransID _m_trid, String _m_date)
  {
    super( _m_result, _m_trid, _m_date );
    m_id = _m_id;
  } // ctor

  /**
   * Accessor method for the client id of the contact object in the registry
   * @param value The contact client id
   * @see #m_id
   */
  public void setId(String value) { m_id = value; }
  /**
   * Accessor method for the client id of the contact object in the registry
   * @return The contact client id
   * @see #m_id
   */
  public String getId() { return m_id; }

  /**
   * Converts this class into a string.
   * Typically used to view the object in debug output.
   * @return The string representation of this object instance
   */
  public String toString() { return this.getClass().getName() + ": { m_id ["+m_id+"] m_result ["+m_result+"] m_trid ["+m_trid+"] m_date ["+m_date+"] }"; }

} // interface epp_ContactPanData
