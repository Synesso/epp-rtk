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

package org.openrtk.idl.epprtk;


/**
 * Internal IDL interface which is never referenced externally.</p>
 * Sub-interface epp_Hello is implemented by EPPGreeting.
 * $Header: /cvsroot/epp-rtk/epp-rtk/java/src/org/openrtk/idl/epprtk/epp_HelloOperations.java,v 1.1 2004/12/07 15:27:49 ewang2004 Exp $<br>
 * $Revision: 1.1 $<br>
 * $Date: 2004/12/07 15:27:49 $<br>
 * @see org.openrtk.idl.epprtk.epp_Hello
 */
public interface epp_HelloOperations  extends org.openrtk.idl.epprtk.epp_ActionOperations
{
  /**
   * Accessor for the data representing EPP greeting response from a
   * server in response to the hello request issued by the client
   * The implementor of this method is responsible for translating
   * the response EPP XML into an instance of epp_Greeting.
   * @returns The Greeting element
   */
  org.openrtk.idl.epprtk.epp_Greeting getResponseData ();
} // interface epp_HelloOperations
