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
 * Internal IDL interface which is never referenced externally.</p>
 * Sub-interface epp_Status is implemented by EPPStatus.</p>
 * $Header: /cvsroot/epp-rtk/epp-rtk/java/src/org/openrtk/idl/epp0604/epp_StatusOperations.java,v 1.2 2003/09/10 21:29:57 tubadanm Exp $<br>
 * $Revision: 1.2 $<br>
 * $Date: 2003/09/10 21:29:57 $<br>
 * @see org.openrtk.idl.epp0604.epp_Status
 * @see com.tucows.oxrs.epp0604.rtk.xml.EPPStatus
 */
public interface epp_StatusOperations  extends org.openrtk.idl.epp0604.epp_ActionOperations
{
  void setRequestData (org.openrtk.idl.epp0604.epp_StatusReq data);
  org.openrtk.idl.epp0604.epp_StatusRsp getResponseData ();
} // interface epp_StatusOperations
