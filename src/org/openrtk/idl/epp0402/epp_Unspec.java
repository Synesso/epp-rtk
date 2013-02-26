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

package org.openrtk.idl.epp0402;


/**
 * Master external interface for the implementor of the EPP Unspec element.</p>
 * The Unspc(unspecified) element may be used for server-defined command extensions.</p>
 * The interface brings together epp_UnspecOperations and standard IDL classes.
 * $Header: /cvsroot/epp-rtk/epp-rtk/java/src/org/openrtk/idl/epp0402/epp_Unspec.java,v 1.1 2003/03/21 16:35:40 tubadanm Exp $<br>
 * $Revision: 1.1 $<br>
 * $Date: 2003/03/21 16:35:40 $<br>
 */
public interface epp_Unspec extends epp_UnspecOperations, org.omg.CORBA.Object, org.omg.CORBA.portable.IDLEntity 
{
} // interface epp_Unspec
