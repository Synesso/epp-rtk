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
 * $Header: /cvsroot/epp-rtk/epp-rtk/java/src/com/tucows/oxrs/epp0503/rtk/transport/EPPTransportTCP.java,v 1.1 2003/03/21 16:18:22 tubadanm Exp $
 * $Revision: 1.1 $
 * $Date: 2003/03/21 16:18:22 $
 */

package com.tucows.oxrs.epp0503.rtk.transport;

import java.io.*;
import java.net.*;
import org.openrtk.idl.epp0503.*;

/**
 *  Provides methods necessary to establish connection and to communicate with
 *  an EPP Server using plain socket (no encryption).
 */
public class EPPTransportTCP extends EPPTransportBase 
{

    /**
     * The socket to the server.
     */
    protected Socket socket_to_server_;
    /**
     * Input stream from the server.
     * Begotten from the socket.
     */
    protected BufferedReader reader_from_server_;
    /**
     * Output stream to the server.
     * Begotten from the socket.
     */
    protected PrintWriter writer_to_server_;
    /**
     * Flag to indicate if the transports socket has been
     * preset externally.
     */
    protected boolean preset_ = false;

    /**
     * Default Construtor
     */    
    public EPPTransportTCP() 
    {
        super();
    }	

    /**
     * Construtor with established socket and timeout value
     * If the timeout value is zero, the default timeout value 
     * from EPPTransportBase is used.
     * @param socket The Socket to the server
     * @param timeout The int socket timeout value, in milliseconds
     */
    public EPPTransportTCP(Socket socket,
                           int timeout) 
    {
	socket_to_server_ = socket;
	epp_timeout_ = timeout > 0 ? timeout : DEFAULT_SOCKET_TIMEOUT;
	preset_ = true;
    }

    /**
     * Construtor with Hostname, Host port and timeout value
     * If the timeout value is zero, the default timeout value 
     * from EPPTransportBase is used.
     * @param host_name The server Hostname
     * @param host_port The server Host port
     * @param timeout The int socket timeout value, in milliseconds
     */    
    public EPPTransportTCP(String host_name,
                           int host_port,
			   int timeout) 
    {
        super(host_name, host_port, timeout > 0 ? timeout : DEFAULT_SOCKET_TIMEOUT);
    }	

    /**
     * Connects to the Server using previously set Hostname and port.
     * If the socket was provided externally, the connection operation is
     * skipped, but the input and output buffers are still extracted.
     * The method also sets the SO timeout of the socket regardless
     * of its origins.
     */ 
    public void connect() throws SocketException, IOException, UnknownHostException
    {
        String method_name = "connect()";

        debug(DEBUG_LEVEL_THREE,method_name,"Entered");

        if( ! preset_ )
        {
            // Initialize to null the socket to the server
            socket_to_server_ = null;
            socket_to_server_ = new Socket(epp_host_name_, epp_host_port_);
        }
	
	socket_to_server_.setSoTimeout(epp_timeout_);
	
        reader_from_server_ = new BufferedReader(new InputStreamReader(socket_to_server_.getInputStream()));
        writer_to_server_ = new PrintWriter(new OutputStreamWriter(socket_to_server_.getOutputStream()),true);

	debug(DEBUG_LEVEL_TWO,method_name,"Connected to ["+socket_to_server_.getInetAddress()+":"+socket_to_server_.getPort()+"]");

        debug(DEBUG_LEVEL_THREE,method_name,"Leaving");
	
	return;
    }	

    /*
     * Reads a single character from a server via a BufferedReader. Since SocketExceptions
     * still get thrown east and west when the server disconnects even before you reach the
     * end of the buffer, we ignore the exception so that the caller of read() will be
     * able to read until the last character, and will be informed of the disconnect by
     * reading the -1 (EOF) marker instead of an exception while there is still data to
     * be read
     */
    private int read() 
    {
        String method_name = "read()";
        int ch = -1;
        boolean ok = false;
        while (!ok) {
            try {
                ch = reader_from_server_.read();
                ok = true;
            } catch (IOException xcp) {
                debug(DEBUG_LEVEL_ONE,method_name,xcp);
            }
        }
        return ch;
    }

    /**
     * Reads a complete XML message from the Server. 
     * @return Full XML String
     * @throws org.openrtk.idl.epp0503.epp_Exception if there was a socket error in 
     *         reading from the EPP Server
     * @see org.openrtk.idl.epp0503.epp_Session#RTK_COMMUNICATIONS_FAILURE
     * @see org.openrtk.idl.epp0503.epp_Session#RTK_UNEXPECTED_SERVER_DISCONNECT
     */
    public String readFromServer() throws epp_Exception
    {
        String method_name = "readFromServer()";

        debug(DEBUG_LEVEL_THREE,method_name,"Entered");

        StringBuffer buf = new StringBuffer();

        try
        {

	    int ch;
	   
            while((ch = read()) != -1)
            {
	        buf.append((char)ch);
	        if (ch == '<') {
		   ch = read();
		   if (ch == -1) break;
		   buf.append((char)ch);
		   if (ch == '/') {
		      ch = read();
		      if (ch == -1) break;
		      buf.append((char)ch);	
		      if (ch == 'e') {
			 ch = read();
			 if (ch == -1) break;
			 buf.append((char)ch);	
			 if (ch == 'p') {
			    ch = read();
			    if (ch == -1) break;
			    buf.append((char)ch);	
			    if (ch == 'p') {
			       ch = read();
			       if (ch == -1) break;
			       buf.append((char)ch);	
			       if (ch == '>') break;
			    }
			 }
		      }
		   }
		}
            }

            if (ch == -1)
            {
                debug(DEBUG_LEVEL_TWO,method_name,"Connection closed by server.");

                disconnect();

                epp_Result[] result = new epp_Result[1];
                result[0] = new epp_Result();
                result[0].m_code = epp_Session.RTK_UNEXPECTED_SERVER_DISCONNECT;
                result[0].m_msg = "Unexpected server disconnect";
                throw new epp_Exception( result );
            }

        }
        catch (IOException xcp)
        {
            debug(DEBUG_LEVEL_ONE,method_name,xcp);
	    debug(DEBUG_LEVEL_THREE,method_name, "Read: ["+buf.toString()+"] so far");
            epp_Result[] result = new epp_Result[1];
            result[0] = new epp_Result();
            result[0].m_code = epp_Session.RTK_COMMUNICATIONS_FAILURE;
            result[0].m_msg = "Failed to read from server ["+xcp.getClass().toString()+"] ["+xcp.getMessage()+"]";
            throw new epp_Exception( result );
            
        }

        debug(DEBUG_LEVEL_THREE,method_name,"Leaving");

        return buf.toString();	
    }


    /**
     * Sends an XML string to the Server
     * @throws org.openrtk.idl.epp0503.epp_Exception if there was a socket error in writing to the EPP Server.
     *         The epp_Exception will contain a result with the code epp_Session.RTK_COMMUNICATIONS_FAILURE
     * @see org.openrtk.idl.epp0503.epp_Session#RTK_COMMUNICATIONS_FAILURE
     */
    public void writeToServer(String xml_to_server) throws epp_Exception
    {
        String method_name = "writeToServer(String)";

        debug(DEBUG_LEVEL_THREE,method_name,"Entered");

        try
        {
            String line;
            StringBuffer buf;
            BufferedReader reader_outbound_xml = new BufferedReader(new StringReader(xml_to_server));

            buf = new StringBuffer();

            while((line = reader_outbound_xml.readLine()) != null)
            {
                line = line.trim();
                if (line.startsWith("#") || line.length() == 0) continue;
                if (!line.startsWith("<")) buf.append(' ');
	        // FIXME: Hack to prevent <?xml ..  ?> to be sent multiple times
                if (!line.endsWith("?>")) buf.append(line);
                if (line.trim().endsWith("</epp>")) break;
            }

            String final_xml = buf.toString();
            writer_to_server_.print(final_xml);
            writer_to_server_.print(CRLF);
            writer_to_server_.flush();

        }
        catch (IOException xcp)
        {
            debug(DEBUG_LEVEL_ONE,method_name,xcp);
            epp_Result[] result = new epp_Result[1];
            result[0] = new epp_Result();
            result[0].m_code = epp_Session.RTK_COMMUNICATIONS_FAILURE;
            result[0].m_msg = "Failed to write to server ["+xcp.getClass().toString()+"] ["+xcp.getMessage()+"]";
            throw new epp_Exception( result );
        }

        debug(DEBUG_LEVEL_THREE,method_name,"Leaving");
    }

    /**
     * Closes the connection to the Server
     * @throws IOException if there was a Socket problem
     */
    public void disconnect() throws IOException
    {
        String method_name = "disconnect()";
        debug(DEBUG_LEVEL_THREE,method_name,"Entered");

        try
        {
            if ( socket_to_server_ != null ) { socket_to_server_.close(); }
            socket_to_server_ = null;

            if ( reader_from_server_ != null ) { reader_from_server_.close(); }
            reader_from_server_ = null;

            if ( writer_to_server_ != null ) { writer_to_server_.close(); }
            writer_to_server_ = null;

        }
        catch (IOException xcp)
        {
            debug(DEBUG_LEVEL_ONE,method_name,xcp);

            throw xcp;
        }

        debug(DEBUG_LEVEL_THREE,method_name,"Leaving");
    }
    
}    
