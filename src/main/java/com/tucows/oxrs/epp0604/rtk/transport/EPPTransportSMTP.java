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

/*
 * $Header: /cvsroot/epp-rtk/epp-rtk/java/src/com/tucows/oxrs/epp0604/rtk/transport/EPPTransportSMTP.java,v 1.1 2003/03/21 15:52:35 tubadanm Exp $
 * $Revision: 1.1 $
 * $Date: 2003/03/21 15:52:35 $
 */

package com.tucows.oxrs.epp0604.rtk.transport;

import org.openrtk.idl.epp0604.*;
import java.io.*;
import java.net.*;

/**
 *  Provides methods necessary to communicate via SMTP with an EPP Server.
 */
public class EPPTransportSMTP extends EPPTransportBase 
{

    /**
     * Default construtor
     */    
    public EPPTransportSMTP()
    {
	super();
    }	

    /**
     * Empty implemention of the connect method from EPPTransportBase.
     * Does nothing because there is no persistent connection to the
     * server in SMTP.
     */ 
    public void connect() throws IOException, UnknownHostException
    {
        // nothing to be done
	return;
    }	

    /**
     * Empty implemention of the disconnect method from EPPTransportBase.
     * Does nothing because there is no persistent connection to the
     * server in SMTP.
     */
    public void disconnect() throws IOException
    {
        // nothing to be done
	return;
    }
    
    /**
     * Reads an EPP message received from the server via SMTP (i.e. an email).
     * Does nothing at this time.  Code must be writen.
     */
    public String readFromServer() throws epp_Exception
    {
        // Should write some code that works.
	return null;
    }

    /**
     * Sends an EPP message to the server via SMTP (i.e. an email).
     * Does nothing at this time.  Code must be writen.
     */
    public void writeToServer(String string_to_server) throws epp_Exception
    {
	// Should write some code that works.
	return;
    }
    
}    
