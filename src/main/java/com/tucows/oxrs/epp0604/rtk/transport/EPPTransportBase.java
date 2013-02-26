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
 * $Header: /cvsroot/epp-rtk/epp-rtk/java/src/com/tucows/oxrs/epp0604/rtk/transport/EPPTransportBase.java,v 1.1 2003/03/21 15:52:33 tubadanm Exp $
 * $Revision: 1.1 $
 * $Date: 2003/03/21 15:52:33 $
 */

package com.tucows.oxrs.epp0604.rtk.transport;

import com.tucows.oxrs.epp0604.rtk.*;
import java.io.*;
import java.net.*;
import org.openrtk.idl.epp0604.*;

/**
 * Base abstract class for the Transport classes.  Defines common methods 
 * and data members used by all or most Transport classes.
 */
public abstract class EPPTransportBase extends RTKBase
{
    
    /**
     * The Host name of the EPP server.
     */
    protected String epp_host_name_;
    /**
     * The Host port of the EPP server.
     */
    protected int epp_host_port_;
    /**
     * The timeout to apply to persistent connections to the server.
     */
    protected int epp_timeout_;

    /**
     * The default timeout in waiting for server responses.
     * Set to 20 seconds.
     */
    public static final int DEFAULT_SOCKET_TIMEOUT = 20000;

    /**
     * Default constructor -- 
     */
    public EPPTransportBase()
    {
    }

    /**
     * Constructor with EPP Hostname, EPP Host port, and timeout.
     * Note that the timeout value might not have any effect
     * on particular transports.  See the documentation of the 
     * transport you wish to use to verify.
     * @param epp_host_name The EPP Hostname (eg. "host.domain.tld")
     * @param epp_host_port The EPP port
     * @param epp_timeout The timeout in milliseconds to apply to the connection.
     */
    public EPPTransportBase(String epp_host_name,
                            int epp_host_port,
                            int epp_timeout)
    {
        epp_host_name_ = epp_host_name;
        epp_host_port_ = epp_host_port;
        epp_timeout_ = epp_timeout;
    }

    /**
     * Constructor with EPP Hostname and EPP Host port.
     * Default timeout value will be used, if applicable.
     * @param epp_host_name The EPP Hostname (eg. "host.domain.tld")
     * @param epp_host_port The EPP port
     */
    public EPPTransportBase(String epp_host_name,
                            int epp_host_port)
    {
        this(epp_host_name, epp_host_port, DEFAULT_SOCKET_TIMEOUT);
    }

    /**
     * Initializes the transport object with host name, host port and
     * timeout.
     * Note that the timeout value might not be applicable to all
     * transport subclasses.
     * @param epp_host_name The EPP Hostname (eg. "host.domain.tld")
     * @param epp_host_port The EPP port
     * @param epp_timeout The timeout value in milliseconds.
     */
    public void initialize(String epp_host_name,
                           int epp_host_port,
                           int epp_timeout)
    {
        epp_host_name_ = epp_host_name;
        epp_host_port_ = epp_host_port;
        epp_timeout_ = epp_timeout;
    }

    /**
     * Accessor method for the EPP Hostname
     * @param value The hostname string (eg. "host.domain.tld" or "100.101.200.201")
     */
    public void setEPPHostName(String value) { epp_host_name_ = value; }
    /**
     * Accessor method for the EPP Hostname
     * @return The EPP host name String
     */
    public String getEPPHostName() { return epp_host_name_; }

    /**
     * Accessor method for the EPP Host port
     * @param value The int port value
     */
    public void setEPPHostPort(int value) { epp_host_port_ = value; }
    /**
     * Accessor method for the EPP Host port
     */
    public int getEPPHostPort() { return epp_host_port_; }

    /**
     * Accessor method for the EPP server timeout, in milliseconds
     * @param value The int timeout value, in milliseconds
     */
    public void setEPPTimeout(int value) { epp_timeout_ = value; }
    /**
     * Accessor method for the EPP server timeout, in milliseconds
     */
    public int getEPPTimeout() { return epp_timeout_; }



    /**
     * Establishes the connection to the Server
     * Abstract method to be implemented by subclasses.  Though
     * in some subclasses (like SMTP) this method might not have
     * any effect.
     * @throws SocketException
     * @throws IOException
     * @throws UnknownHostException
     */    
    public abstract void connect() throws SocketException, IOException, UnknownHostException;
    
    /**
     * Closes the connection to the Server
     * Abstract method to be implemented by subclasses.  Though
     * in some subclasses (like SMTP) this method might not have
     * any effect.
     * @throws IOException
     */
    public abstract void disconnect() throws IOException;
    
    /**
     * Reads from the Server
     * Abstract method to be implemented by subclasses.
     * For asynchronous transports, like SMTP, this method should be
     * periodically called to "check mail".
     * @return The EPP message from the server.
     * @throws epp_Exception
     */    
    public abstract String readFromServer() throws epp_Exception;
    
    /**
     * Writes to the Server
     * Abstract method to be implemented by subclasses.
     * @param string_to_server The EPP message to the server.
     * @throws epp_Exception
     */
    public abstract void writeToServer(String string_to_server) throws epp_Exception;
    
}    
