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

package org.openrtk.idl.epp0705.host;


/**
 * Master external interface for the implementor of the EPP Host Info command.</p>
 * The interface brings together epp_HostInfoOperations, epp_Action and standard
 * IDL classes.
 * $Header: /cvsroot/epp-rtk/epp-rtk/java/src/org/openrtk/idl/epp0705/host/epp_HostInfo.java,v 1.2 2003/09/10 21:29:59 tubadanm Exp $<br>
 * $Revision: 1.2 $<br>
 * $Date: 2003/09/10 21:29:59 $<br>
 * @see com.tucows.oxrs.epp0705.rtk.xml.EPPHostInfo
 */
public interface epp_HostInfo extends epp_HostInfoOperations, org.openrtk.idl.epp0705.epp_Action, org.omg.CORBA.portable.IDLEntity 
{
} // interface epp_HostInfo
