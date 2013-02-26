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

package org.openrtk.idl.epp0402.host;


/**
 * Internal IDL interface which is never referenced externally.</p>
 * Sub-interface epp_HostUpdate is implemented by EPPHostUpdate.
 * $Header: /cvsroot/epp-rtk/epp-rtk/java/src/org/openrtk/idl/epp0402/host/epp_HostUpdateOperations.java,v 1.1 2003/03/21 16:35:42 tubadanm Exp $<br>
 * $Revision: 1.1 $<br>
 * $Date: 2003/03/21 16:35:42 $<br>
 * @see org.openrtk.idl.epp0402.host.epp_HostUpdate
 */
public interface epp_HostUpdateOperations  extends org.openrtk.idl.epp0402.epp_ActionOperations
{
  /**
   * Sets the request data for an outgoing Host Update EPP request.
   * The implementor of this method is responsible for translating
   * the request parms into equivalent Host Update EPP XML.
   * @param parms The EPP request data
   */
  void setRequestData (org.openrtk.idl.epp0402.host.epp_HostUpdateReq parms);
  /**
   * Accessor for the data representing EPP response from a server for the
   * host update command.
   * The implementor of this method is responsible for translating
   * the response EPP XML into an instance of epp_HostUpdateRsp.
   * @returns The Host Update response
   */
  org.openrtk.idl.epp0402.host.epp_HostUpdateRsp getResponseData ();
} // interface epp_HostUpdateOperations
