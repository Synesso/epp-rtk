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

package org.openrtk.idl.epprtk.domain;


/**
 * Master external interface for the implementor of the EPP Domain Check command.</p>
 * The interface brings together epp_DomainCheckOperations, epp_Action and standard
 * IDL classes.
 * $Header: /cvsroot/epp-rtk/epp-rtk/java/src/org/openrtk/idl/epprtk/domain/epp_DomainCheck.java,v 1.1 2004/12/07 15:27:49 ewang2004 Exp $<br>
 * $Revision: 1.1 $<br>
 * $Date: 2004/12/07 15:27:49 $<br>
 * @see com.tucows.oxrs.epprtk.rtk.xml.EPPDomainCheck
 */

public interface epp_DomainCheck extends epp_DomainCheckOperations, org.openrtk.idl.epprtk.epp_Action, org.omg.CORBA.portable.IDLEntity 
{
} // interface epp_DomainCheck
