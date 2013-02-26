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

/*
 * $Header: /cvsroot/epp-rtk/epp-rtk/java/src/com/tucows/oxrs/epp0705/rtk/transport/EPPTransportException.java,v 1.1 2003/03/20 22:41:59 tubadanm Exp $
 * $Revision: 1.1 $
 * $Date: 2003/03/20 22:41:59 $
 */

package com.tucows.oxrs.epp0705.rtk.transport;


/**
 * This class groups all exceptions having to do with the instantiation of
 * transports in EPPClient, such a ClassNotFoundException,
 * InstantiationException, etc...
 */
public class EPPTransportException extends Exception
{

    /**
     * Basic construction for exceptions with messages.
     */
    public EPPTransportException (String message)
    {
        super(message);
    }
} 
