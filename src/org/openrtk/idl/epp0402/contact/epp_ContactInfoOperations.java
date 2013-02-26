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
 * Internal IDL interface which is never referenced externally.</p>
 * Sub-interface epp_ContactInfo is implemented by EPPContactInfo.
 * $Header: /cvsroot/epp-rtk/epp-rtk/java/src/org/openrtk/idl/epp0402/contact/epp_ContactInfoOperations.java,v 1.1 2003/03/21 16:35:40 tubadanm Exp $<br>
 * $Revision: 1.1 $<br>
 * $Date: 2003/03/21 16:35:40 $<br>
 * @see org.openrtk.idl.epp0402.contact.epp_ContactInfo
 */
public interface epp_ContactInfoOperations  extends org.openrtk.idl.epp0402.epp_ActionOperations
{
  /**
   * Sets the request data for an outgoing Contact Info EPP request.
   * The implementor of this method is responsible for translating
   * the request parms into equivalent Contact Info EPP XML.
   * @param parms The EPP request data
   */
  void setRequestData (org.openrtk.idl.epp0402.contact.epp_ContactInfoReq parms);
  /**
   * Accessor for the data representing EPP response from a server for the
   * contact info command.
   * The implementor of this method is responsible for translating
   * the response EPP XML into an instance of epp_ContactInfoRsp.
   * @returns The Contact Info response
   */
  org.openrtk.idl.epp0402.contact.epp_ContactInfoRsp getResponseData ();
} // interface epp_ContactInfoOperations
