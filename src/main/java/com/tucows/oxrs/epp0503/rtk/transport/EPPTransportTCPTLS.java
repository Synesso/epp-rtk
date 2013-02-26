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
 * $Header: /cvsroot/epp-rtk/epp-rtk/java/src/com/tucows/oxrs/epp0503/rtk/transport/EPPTransportTCPTLS.java,v 1.1 2003/03/21 16:18:22 tubadanm Exp $
 * $Revision: 1.1 $
 * $Date: 2003/03/21 16:18:22 $
 */

package com.tucows.oxrs.epp0503.rtk.transport;

import java.io.*;
import java.net.*;
import javax.net.ssl.*;
import java.util.*;
import javax.security.cert.*;
import java.security.*;

/**
 * Provides methods necessary to build connection with EPP Server
 * using a SSL-TLS socket.  It only overrides the connect() method
 * from EPPTransportTCP since the remaining socket operations are
 * identical to unencrypted sockets.
 * <P>
 * This class uses Sun's JSSE to establish a secure connection with the
 * server.  It makes use of the RTK property "ssl.props.location" to
 * retrieve the ssl.properties.  This properties file contains
 * information necessary to locate the java keystore, the names of the keys
 * and certificates contained inside it and the necessary passwords to
 * access the keystore.  The epp-rtk/java/ssl director contains more information
 * regarding the data required to create a keystore and the steps to do so.
 * <P>
 * If the user does not wish to use the default Sun JSSE but rather a
 * third party SSL implemention, then the socket connection should
 * be established externally and an instance of EPPTransportTCP should be
 * created using the connected socket. 
 * <P>
 * Please see the EPP RTK User's Guide for more information on secure
 * connections to EPP servers.
 */
public class EPPTransportTCPTLS extends EPPTransportTCP
{

    private SSLContext ctx_ = null;
    private KeyStore ks_ = null;
    private KeyManagerFactory kmf_ = null;
    private SecureRandom rnd_ = null;

    
    /**
     * Default Construtor
     */    
    public EPPTransportTCPTLS() 
    {
        super();
    }	

    /**
     * Construtor with Hostname, Host port and timeout value
     * @param host_name The server Hostname
     * @param host_port The server Host port
     * @param timeout The int socket timeout value, in milliseconds
     */    
    public EPPTransportTCPTLS(String host_name,
                              int host_port,
			      int timeout) 
    {
        super(host_name, host_port, timeout);
    }	

    /**
     * Connects to the Server using previously set Hostname and port.
     * If connection has been already established, the operation will be ignored. 
     * The method also sets the SO timeout.
     * @throws SocketException
     * @throws IOException
     * @throws UnknownHostException
     */ 
    public void connect() throws SocketException, IOException, UnknownHostException
    {
        String method_name = "connect()";

        debug(DEBUG_LEVEL_THREE,method_name,"Entered");
        
	if( ! preset_ )
        {
            // Initialize to null the socket to the server
            socket_to_server_ = null;

            debug(DEBUG_LEVEL_TWO,method_name,"Using SSL/TLS");

            Properties system_props = System.getProperties();
            String ssl_props_location = (String) system_props.getProperty("ssl.props.location");
            if ( ssl_props_location == null ||
                 ssl_props_location.length() == 0 )
            {
                throw new IOException("No ssl props location specified");
            }
            Properties ssl_props = new Properties();
            ssl_props.load(new FileInputStream(ssl_props_location + "/ssl.properties"));

            SSLSocketFactory ssl_factory = null;

            try
            {
                char[] passphrase1 = ((String)ssl_props.get("ssl.keystore.passphrase")).toCharArray();
                char[] passphrase2 = ((String)ssl_props.get("ssl.signedcert.passphrase")).toCharArray();
                if ( ctx_ == null )
                {
                    ctx_ = SSLContext.getInstance(((String)ssl_props.get("ssl.protocol")));
                }
                if ( ks_ == null )
                {
                    ks_ = KeyStore.getInstance(((String)ssl_props.get("ssl.keystore.format")));
                    ks_.load(new FileInputStream(ssl_props_location + "/" +
                                ((String)ssl_props.get("ssl.keystore.file"))), passphrase1);
                }
                if ( kmf_ == null )
                {
                    kmf_ = KeyManagerFactory.getInstance(((String)ssl_props.get("ssl.keymanagerfactory.format")));
                    kmf_.init(ks_, passphrase2);
                }

                // SSL Performance improvement from wessorh
                try{
                    byte seed[] = new byte[1024];
                    FileInputStream is = new FileInputStream("/dev/urandom");
                    is.read(seed);
                    is.close();

                    rnd_ = java.security.SecureRandom.getInstance("SHA1PRNG");
                    rnd_.setSeed(seed);
                    debug(DEBUG_LEVEL_TWO,method_name,"SecureRandom seed set.");

                }
                catch (Exception xcp)
                {
                    debug(DEBUG_LEVEL_TWO,method_name,"Error initializing SecureRandom ["+xcp.getMessage()+"], using default initialization.");
                    rnd_ = null;
                }

                ctx_.init(kmf_.getKeyManagers(), null, rnd_);
                ssl_factory = ctx_.getSocketFactory();
            }
            catch (Exception xcp)
            {
                throw new IOException(xcp.getMessage());
            }

            socket_to_server_ = (Socket)ssl_factory.createSocket(epp_host_name_, epp_host_port_);
        }
	
	socket_to_server_.setSoTimeout(epp_timeout_);
	
        reader_from_server_ = new BufferedReader(new InputStreamReader(socket_to_server_.getInputStream()));
        writer_to_server_ = new PrintWriter(new OutputStreamWriter(socket_to_server_.getOutputStream()),true);

	debug(DEBUG_LEVEL_TWO,method_name,"Connected to ["+socket_to_server_.getInetAddress()+":"+socket_to_server_.getPort()+"]");

        debug(DEBUG_LEVEL_THREE,method_name,"Leaving");
	
	return;
    }

}    
