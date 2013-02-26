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

package org.openrtk.idl.epp0705.domain;


/**
 * Internal IDL interface which is never referenced externally.</p>
 * Sub-interface epp_DomainUpdate is implemented by EPPDomainUpdate.
 * $Header: /cvsroot/epp-rtk/epp-rtk/java/src/org/openrtk/idl/epp0705/domain/epp_DomainUpdateOperations.java,v 1.1 2003/03/20 22:42:31 tubadanm Exp $<br>
 * $Revision: 1.1 $<br>
 * $Date: 2003/03/20 22:42:31 $<br>
 * @see org.openrtk.idl.epp0705.domain.epp_DomainUpdate
 */
public interface epp_DomainUpdateOperations  extends org.openrtk.idl.epp0705.epp_ActionOperations
{
  /**
   * Sets the request data for an outgoing Domain Update EPP request.
   * The implementor of this method is responsible for translating
   * the request parms into equivalent Domain Update EPP XML.
   * @param parms The EPP request data
   */
  void setRequestData (org.openrtk.idl.epp0705.domain.epp_DomainUpdateReq parms);
  /**
   * Accessor for the data representing EPP response from a server for the
   * domain update command.
   * The implementor of this method is responsible for translating
   * the response EPP XML into an instance of epp_DomainUpdateRsp.
   * @returns The Domain Update response
   */
  org.openrtk.idl.epp0705.domain.epp_DomainUpdateRsp getResponseData ();
} // interface epp_DomainUpdateOperations
