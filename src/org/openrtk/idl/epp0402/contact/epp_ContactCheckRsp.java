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

package org.openrtk.idl.epp0402.contact;


/**
 * Class that contains the elements used to represent the contact check
 * response from the EPP server.</p>
 * Note usually instantiated by the RTK user, but rather by EPPContactCheck
 * and retrieved using that class' getResponseData() method.</p>
 * $Header: /cvsroot/epp-rtk/epp-rtk/java/src/org/openrtk/idl/epp0402/contact/epp_ContactCheckRsp.java,v 1.2 2003/09/10 21:29:55 tubadanm Exp $<br>
 * $Revision: 1.2 $<br>
 * $Date: 2003/09/10 21:29:55 $<br>
 * @see com.tucows.oxrs.epp0402.rtk.xml.EPPContactCheck
 * @see org.openrtk.idl.epp0402.contact.epp_ContactCheckReq
 */
public class epp_ContactCheckRsp implements org.omg.CORBA.portable.IDLEntity
{
  /**
   * The common and generic response element.
   * @see #getRsp()
   */
  public org.openrtk.idl.epp0402.epp_Response m_rsp = null;
  /**
   * The generic check response array.
   * @see #getResults()
   * @see com.tucows.oxrs.epp0402.rtk.xml.EPPXMLBase#getCheckResultFor(epp_CheckResult[], String)
   */
  public org.openrtk.idl.epp0402.epp_CheckResult m_results[] = null;

  /**
   * Empty constructor
   */
  public epp_ContactCheckRsp ()
  {
  } // ctor

  /**
   * The constructor with initializing variables.
   * @param _m_rsp The common and generic response element
   * @param _m_results The array of contact check results
   */
  public epp_ContactCheckRsp (org.openrtk.idl.epp0402.epp_Response _m_rsp, org.openrtk.idl.epp0402.epp_CheckResult[] _m_results)
  {
    m_rsp = _m_rsp;
    m_results = _m_results;
  } // ctor

  /**
   * Accessor method for the common and generic response element.
   * @param value The new value for the response element
   * @see #m_rsp
   */
  public void setRsp(org.openrtk.idl.epp0402.epp_Response value) { m_rsp = value; }
  /**
   * Accessor method for the common and generic response element.
   * @return The value for the response element
   * @see #m_rsp
   */
  public org.openrtk.idl.epp0402.epp_Response getRsp() { return m_rsp; }

  /**
   * Accessor method for the contact check results
   * @param value The new value for the contact check results array
   * @see #m_results
   */
  public void setResults(org.openrtk.idl.epp0402.epp_CheckResult[] value) { m_results = value; }
  /**
   * Accessor method for the contact check results
   * @return The value for the contact check results array
   * @see #m_results
   */
  public org.openrtk.idl.epp0402.epp_CheckResult[] getResults() { return m_results; }

  /**
   * Converts this class into a string.
   * Typically used to view the object in debug output.
   * @return The string representation of this object instance
   */
  public String toString() { return this.getClass().getName() + ": { m_rsp ["+m_rsp+"] m_results ["+(m_results != null ? java.util.Arrays.asList(m_results) : null)+"] }"; }

} // class epp_ContactCheckRsp
