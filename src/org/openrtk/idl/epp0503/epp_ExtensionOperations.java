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

package org.openrtk.idl.epp0503;


/**
 * Internal IDL interface which is never referenced externally.</p>
 * Sub-interface epp_Extension may be implemented for the server-defined
 * command extensions.
 * $Header: /cvsroot/epp-rtk/epp-rtk/java/src/org/openrtk/idl/epp0503/epp_ExtensionOperations.java,v 1.1 2003/03/21 16:18:25 tubadanm Exp $<br>
 * $Revision: 1.1 $<br>
 * $Date: 2003/03/21 16:18:25 $<br>
 * @see org.openrtk.idl.epp0503.epp_Extension
 */
public interface epp_ExtensionOperations 
{
  /**
   * Converts the data into XML to be put into the unspec section of the request
   * @return the XML String.
   * @throws epp_XMLException if required data is missing.
   */
  String toXML () throws org.openrtk.idl.epp0503.epp_XMLException;
  /**
   * Parses an XML String of data from the unspec section of a response from the Registry.
   * @param The XML String to parse.
   * @throws epp_XMLException if the response XML is not parsable or does not contain the expected data.
   */
  void fromXML (String xml) throws org.openrtk.idl.epp0503.epp_XMLException;
} // interface epp_ExtensionOperations
