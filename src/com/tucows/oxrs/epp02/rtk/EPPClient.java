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
 * $Header: /cvsroot/epp-rtk/epp-rtk/java/src/com/tucows/oxrs/epp02/rtk/EPPClient.java,v 1.3 2003/09/09 16:33:22 tubadanm Exp $
 * $Revision: 1.3 $
 * $Date: 2003/09/09 16:33:22 $
 */

package com.tucows.oxrs.epp02.rtk;

import java.io.*;
import java.net.*;
import javax.net.ssl.*;
import java.util.*;
import javax.security.cert.*;
import java.security.*;
import org.openrtk.idl.epp02.*;
import com.tucows.oxrs.epp02.rtk.xml.*;

/**
 * EPP Client -- encapsulates all the connection and communication with the
 * EPP Server.
 *
 * @author Daniel Manley
 * @version $Revision: 1.3 $ $Date: 2003/09/09 16:33:22 $
 * @see com.tucows.oxrs.epp02.rtk.example.DomainExample
 * @see com.tucows.oxrs.epp02.rtk.example.ContactExample
 * @see com.tucows.oxrs.epp02.rtk.example.HostExample
**/
public class EPPClient extends RTKBase implements epp_Session
{

    // Public class constants
    /**
     * The current EPP version.  Sent to the EPP Server on login.
     */
    public static final String VERSION = "1.0";
    /**
     * The default language/locale: "en_US".  Sent to the EPP Server on login.
     */
    public static final String DEFAULT_LANG = "en_US";
    /**
     * The default timeout in waiting for server responses.  Set to 20 seconds.
     */
    public static final int DEFAULT_EPP_TIMEOUT = 20000;
    /**
     * The indicator for using only TCP streaming socket connection. No SSL/TLS.
     */
    public static final int PROTOCOL_TCP = 0;
    /**
     * The indicator for using TCP streaming socket connection with SSL/TLS.
     */
    public static final int PROTOCOL_TCP_TLS = 1;
    /**
     * The indicator for using BXXP.  Not currently supported.
     * Introduced for possible future work.
     */
    public static final int PROTOCOL_BXXP = 2;

    // private memeber variable
    private String epp_host_name_;
    private int epp_host_port_;
    private int epp_timeout_;
    private String epp_client_id_;
    private String epp_password_;

    private String version_;
    private String lang_;

    private Socket socket_to_epp_server_;
    private BufferedReader reader_from_server_;
    private PrintWriter writer_to_server_;

    // SSL enhancements [wessorh]
    private SSLContext ctx_ = null;
    private KeyStore ks_ = null;
    private KeyManagerFactory kmf_ = null;
    private SecureRandom rnd_ = null;

    // keep track of the time of the last successfull responce from the server
    // used to determine when we need to send a keepAlive message.
    private long lastNetTransaction = 0;

    // is the session current/valid, used to check if an exception
    // occured and the session needs to be restarted.
    private boolean isValid = false;
    
    // int to indicate the protocol that should be used
    // in the connection to the server.
    private int protocol_;

    // Is RTK version number included as unspec in login
    private boolean sendver_ = true;

    /**
     * Default constructor -- uses default version, lang and timeout values.
     * Also defaults to use TCP/TLS in the connection to the EPP Server.
     */
    public EPPClient()
    {
        version_ = VERSION;
        lang_    = DEFAULT_LANG;
        protocol_ = PROTOCOL_TCP_TLS;
        epp_timeout_ = DEFAULT_EPP_TIMEOUT;
    }

    /**
     * Construtor with client id and password
     * @param epp_client_id The EPP client id
     * @param epp_password The password associated with the client id
     */
    public EPPClient(String epp_client_id,
                     String epp_password)
    {
        this();

        epp_client_id_ = epp_client_id;
        epp_password_  = epp_password;
    }

    /**
     * Constructor with EPP Hostname, EPP Host port, client id and password,
     * in that order.
     * @param epp_host_name The EPP Hostname (eg. "host.domain.tld")
     * @param epp_host_port The EPP port
     * @param epp_client_id The EPP client id
     * @param epp_password The password associated with the client id
     */
    public EPPClient(String epp_host_name,
                     int epp_host_port,
                     String epp_client_id,
                     String epp_password)
    {
        this(epp_client_id, epp_password);

        epp_host_name_ = epp_host_name;
        epp_host_port_ = epp_host_port;
    }

    /**
     * Accessor method for the version
     * @param value The version string
     */
    public void setVersion(String value) { version_ = value; }
    /**
     * Accessor method for the version
     * @return String the version
     */
    public String getVersion() { return version_; }

    /**
     * Accessor method for the lang
     * @param value The lang/locale String (eg. "fr_CA")
     */
    public void setLang(String value) { lang_ = value; }
    /**
     * Accessor method for the lang
     * @return String lang
     */
    public String getLang() { return lang_; }

    /**
     * Accessor method for the protocol indicator
     * @param value int protocol indicator
     */
    public void setProtocol(int value) { protocol_ = value; }
    /**
     * Accessor method for the protocol indicator
     * @return int protocol indicator
     */
    public int getProtocol() { return protocol_; }

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
     * Accessor method for the client id.  This value is also used
     * in the creation of the default EPPTransID
     * @param value The client is String
     */
    public void setEPPClientID(String value) { epp_client_id_ = value; }
    /**
     * Accessor method for the client id.  This value is also used
     * in the creation of the default EPPTransID
     */
    public String getEPPClientID() { return epp_client_id_; }

    /**
     * Accessor method for the password
     * @param value The password String
     */
    public void setEPPPassword(String value) { epp_password_ = value; }
    /**
     * Accessor method for the password
     */
    public String getEPPPassword() { return epp_password_; }

    /**
     * Accessor method for the time of the last successfull
     * network transaction.
     *
     * useful for tracking when a keepAlive (poll) transaction
     * needs to be sent. 
     */
    public long getLastNetTransaction()
    {
        return this.lastNetTransaction;
    }

    /**
     * returns if the connections is valid.
     * A connection is valid if the following are true
     * <ul> 
     * <li> a coonection has been established to the server
     * <li> an I/O Error has not been thrown
     * <li> a login has been successfully completed
     * </ul>
     */
    public boolean isValid()
    {
        return this.isValid;
    }
    
    /**
     * Bypass to the EPPClient's connect() methods.  Allows a connection
     * to be established externally.  Mostly useful if opting to not use JSSE
     * in favour of another SSL package.  This method also sets the SO Timeout
     * to the value from getEPPTimeout().
     * @param value The Socket to the EPP Server
     * @see #getEPPTimeout()
     */
    public void setSocketToEPPServer(Socket value)
            throws epp_Exception,
                   UnknownHostException,
                   SocketException,
                   IOException
    {
        String method_name = "setSocketToEPPServer";
        debug(DEBUG_LEVEL_THREE,method_name,"Enterd");
        socket_to_epp_server_ = value;

        // Set the socket's timeout.
        socket_to_epp_server_.setSoTimeout(epp_timeout_);

        // with a new Socket, come new Reader and Writer
        reader_from_server_ = new BufferedReader(new InputStreamReader(socket_to_epp_server_.getInputStream()));
        writer_to_server_ = new PrintWriter(new OutputStreamWriter(socket_to_epp_server_.getOutputStream()),true);

        debug(DEBUG_LEVEL_THREE,method_name,"Leaving");
    }

    /**
     * Connects to the EPP Server using the given hostname and port and
     * using the previously set client id and password.
     * It is recommended to use connectAndGetGreeting() to connect and
     * retrieve the EPPGreeting in one single call.
     * @param epp_host_name The EPP Hostname (eg. "host.domain.tld")
     * @param epp_host_port The EPP port
     * @see #connectAndGetGreeting()
     */
    public void connect(String epp_host_name,
                        int epp_host_port)
            throws epp_Exception,
                   UnknownHostException,
                   SocketException,
                   IOException
    {
        setEPPHostName(epp_host_name);
        setEPPHostPort(epp_host_port);

        connect();
    }

    /**
     * Connects to the EPP Server using the given client id and password and
     * using the previously set EPP hostname and port.
     * It is recommended to use connectAndGetGreeting() to connect and
     * retrieve the EPPGreeting in one single call.
     * @param epp_client_id The EPP client id
     * @param epp_password The password associated with the client id
     * @see #connectAndGetGreeting()
     */
    public void connect(String epp_client_id,
                        String epp_password)
            throws epp_Exception,
                   UnknownHostException,
                   SocketException,
                   IOException
    {
        setEPPClientID(epp_client_id);
        setEPPPassword(epp_password);

        connect();
    }

    /**
     * Connects to and logs into the EPP Server using the given hostname,
     * port, client id and password
     * It is recommended to use connectAndGetGreeting() to connect and
     * retrieve the EPPGreeting in one single call.
     * @param epp_host_name The EPP Hostname (eg. "host.domain.tld")
     * @param epp_host_port The EPP port
     * @param epp_client_id The EPP client id
     * @param epp_password The password associated with the client id
     * @see #connectAndGetGreeting()
     */
    public void connect(String epp_host_name,
                        int epp_host_port,
                        String epp_client_id,
                        String epp_password)
            throws epp_Exception,
                   UnknownHostException,
                   SocketException,
                   IOException
    {
        setEPPHostName(epp_host_name);
        setEPPHostPort(epp_host_port);
        setEPPClientID(epp_client_id);
        setEPPPassword(epp_password);

        connect();
    }

    /**
     * Connects to the EPP Server using previously set hostname and port.
     * It is recommended to use connectAndGetGreeting() to connect and
     * retrieve the EPPGreeting in one single call.
     * @see #connectAndGetGreeting()
     */
    public void connect()
            throws epp_Exception,
                   UnknownHostException,
                   SocketException,
                   IOException
    {
        String method_name = "connect()";

        debug(DEBUG_LEVEL_THREE,method_name,"Entered");

        // Initialize to null the socket to the server
        socket_to_epp_server_ = null;

        if (protocol_ == PROTOCOL_TCP_TLS)
        {
            // init SSL connection
            debug(DEBUG_LEVEL_TWO,method_name,"Using SSL/TLS");

            Properties system_props = System.getProperties();
            String ssl_props_location = (String) system_props.getProperty("ssl.props.location");
            if ( ssl_props_location == null ||
                 ssl_props_location.length() == 0 )
            {
                isValid = false;
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
                    if ( ssl_props.get("ssl.keystore.provider") == null )
                    {
                        ks_ = KeyStore.getInstance((String)ssl_props.get("ssl.keystore.format"));
                    }
                    else
                    {
                        if ( ssl_props.get("ssl.keystore.provider").equals("BC") ) 
                        {
                            java.security.Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
                        }
                        ks_ = KeyStore.getInstance((String)ssl_props.get("ssl.keystore.format"), (String)ssl_props.get("ssl.keystore.provider"));
                    }
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
                isValid = false;
                throw new IOException(xcp.getMessage());
            }

            socket_to_epp_server_ = (Socket)ssl_factory.createSocket(epp_host_name_, epp_host_port_);

        }
        else if (protocol_ == PROTOCOL_TCP)
        {
            debug(DEBUG_LEVEL_TWO,method_name,"Using plain TCP");
            socket_to_epp_server_ = new Socket(epp_host_name_, epp_host_port_);
        }
        else
        {
            debug(DEBUG_LEVEL_TWO,method_name,"Wanted to use something not supported ["+protocol_+"]");
            throw new IOException("Protocol not supported");
        }

        socket_to_epp_server_.setSoTimeout(epp_timeout_);

        reader_from_server_ = new BufferedReader(new InputStreamReader(socket_to_epp_server_.getInputStream()));
        writer_to_server_ = new PrintWriter(new OutputStreamWriter(socket_to_epp_server_.getOutputStream()),true);

        debug(DEBUG_LEVEL_TWO,method_name,"Connected to ["+socket_to_epp_server_.getInetAddress()+":"+socket_to_epp_server_.getPort()+"]");

        debug(DEBUG_LEVEL_THREE,method_name,"Leaving");

        return;
    }

    /**
     * Reads a complete XML message from the EPP Server.  You would normally
     * then use this XML string in an epp_Action.fromXML().
     * @return Full XML String
     * @throws org.openrtk.idl.epp02.epp_Exception if there was a socket error in reading from the EPP Server
     */
    public String readFromServer() throws epp_Exception
    {
        String method_name = "readFromServer()";

        debug(DEBUG_LEVEL_THREE,method_name,"Entered");

        String line;
        StringBuffer buf;

        try
        {

            buf = new StringBuffer();

            while((line = reader_from_server_.readLine()) != null)
            {
                buf.append(line);
                if (line.trim().endsWith("</epp>")) break;
            }

            if (line == null)
            {
                debug(DEBUG_LEVEL_TWO,method_name,"Connection closed by server.");

                disconnect();

                epp_Result[] result = new epp_Result[1];
                result[0] = new epp_Result();
                result[0].m_code = epp_Session.RTK_UNEXPECTED_SERVER_DISCONNECT;
                result[0].m_msg = "Unexpected server disconnect";
                this.isValid = false;
                throw new epp_Exception( result );
            }

        }
        catch (IOException xcp)
        {
            debug(DEBUG_LEVEL_ONE,method_name,xcp);
            epp_Result[] result = new epp_Result[1];
            result[0] = new epp_Result();
            result[0].m_code = epp_Session.RTK_COMMUNICATIONS_FAILURE;
            result[0].m_msg = "Failed to read from server ["+xcp.getClass().toString()+"] ["+xcp.getMessage()+"]";
            this.isValid = false;
            throw new epp_Exception( result );
            
        }

        this.lastNetTransaction = System.currentTimeMillis();
        
        debug(DEBUG_LEVEL_THREE,method_name,"Leaving");

        return buf.toString();

    }

    /**
     * Method required by the epp_Action interface.
     * Sends raw XML to the server and returns the XML response.
     * @throws org.openrtk.idl.epp02.epp_Exception if a communication error occurs
     */
    public String processXML(String request_xml) throws epp_Exception
    {
        String method_name = "processXML(String)";

        debug(DEBUG_LEVEL_THREE,method_name,"Entered");

        debug(DEBUG_LEVEL_TWO,method_name,"Request XML is ["+request_xml+"]");
        writeToServer(request_xml);

        String response_xml = readFromServer();
        debug(DEBUG_LEVEL_TWO,method_name,"Response XML is ["+response_xml+"]");

        debug(DEBUG_LEVEL_THREE,method_name,"Leaving");
        return response_xml;

    }

    /**
     * Method required by the epp_Action interface.
     * Sends raw XML to the server and returns the XML response.
     * @throws org.openrtk.idl.epp02.epp_XMLException if any request data or response data is missing
     * @throws org.openrtk.idl.epp02.epp_Exception if a communication error occurs or if the server returns an error code
     */
    public epp_Action processAction(epp_Action request) throws epp_XMLException, epp_Exception
    {
        String method_name = "processAction(epp_Action)";

        debug(DEBUG_LEVEL_THREE,method_name,"Entered");

        String request_xml = request.toXML();

        epp_Action response = request;

        String response_xml = processXML(request_xml);

        response.fromXML(response_xml);

        debug(DEBUG_LEVEL_THREE,method_name,"Leaving");
        return response;

    }

    /**
     * Sends an XML string to the EPP Server
     * @throws org.openrtk.idl.epp02.epp_Exception if there was a socket error in writing to the EPP Server.
     *         The epp_Exception will contain a result with the cope epp_Session.RTK_COMMUNICATIONS_FAILURE
     */
    public void writeToServer(String outbound_xml) throws epp_Exception
    {
        String method_name = "writeToServer(String)";

        debug(DEBUG_LEVEL_THREE,method_name,"Entered");

        try
        {
            String line;
            StringBuffer buf;
            BufferedReader reader_outbound_xml = new BufferedReader(new StringReader(outbound_xml));

            buf = new StringBuffer();

            while((line = reader_outbound_xml.readLine()) != null)
            {
                line = line.trim();
                if (line.startsWith("#") || line.length() == 0) continue;
                if (!line.startsWith("<")) buf.append(' ');
                buf.append(line);
                if (line.endsWith("?>")) buf.append(CRLF);
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
            this.isValid = false;
            throw new epp_Exception( result );
        }

        debug(DEBUG_LEVEL_THREE,method_name,"Leaving");

    }

    /**
     * Connects to the EPP Server using previously set hostname and port.
     * It also reads the epp_Greeting from the server and returns it
     * @return org.openrtk.idl.epp02.epp_Greeting the greeting from the EPP Server
     * @throws IOException if there was a socket error in connecting to the EPP Server
     * @throws org.openrtk.idl.epp02.epp_Exception if the server greeting is not present
     * @throws org.openrtk.idl.epp02.epp_XMLException if the server's greeting is not parsable
     */
    public epp_Greeting connectAndGetGreeting() throws epp_Exception, IOException, epp_XMLException
    {
        String method_name = "connectAndGetGreeting()";
        debug(DEBUG_LEVEL_THREE,method_name,"Entered");

        epp_Greeting hello_from_server;

        try
        {
            connect();

            hello_from_server = getGreeting();

        }
        catch (IOException xcp)
        {
            debug(DEBUG_LEVEL_ONE,method_name,xcp);
            this.isValid = false;
            throw xcp;
        }
        catch (epp_XMLException xcp)
        {
            debug(DEBUG_LEVEL_ONE,method_name,xcp);
            this.isValid = false;
            throw xcp;
        }

        debug(DEBUG_LEVEL_THREE,method_name,"Leaving");

        return hello_from_server;
    }

    /**
     * Retrieves the greeting from the EPP Server and returns it.
     * This method is useful if a new connection is provided to the
     * EPPClient (eg. in the case of an SSL connection not using the
     * JSSE standard) and the greeting must be read separately.
     * @return org.openrtk.idl.epp02.epp_Greeting the greeting from the EPP Server
     * @throws org.openrtk.idl.epp02.epp_Exception if the server greeting is not present
     * @throws org.openrtk.idl.epp02.epp_XMLException if the server's greeting is not parsable
     */
    public epp_Greeting getGreeting() throws epp_Exception, epp_XMLException
    {
        String method_name = "connectAndGetGreeting()";
        debug(DEBUG_LEVEL_THREE,method_name,"Entered");

        EPPGreeting hello_from_server;

        try
        {
            String xml_from_server = readFromServer();
            debug(DEBUG_LEVEL_TWO,method_name,"Greeting from server: ["+xml_from_server+"]");

            hello_from_server = new EPPGreeting();
            hello_from_server.fromXML(xml_from_server);

        }
        catch (epp_XMLException xcp)
        {
            debug(DEBUG_LEVEL_ONE,method_name,xcp);
            this.isValid = false;
            throw xcp;
        }

        debug(DEBUG_LEVEL_THREE,method_name,"Leaving");
        this.isValid = true;
        
        return hello_from_server.getResponseData();
    }

    /**
     * Issues a hello request to the EPP Server.
     * This forces the server to respond with an epp_Greeting, which
     * is otherwise only sent on initial connections to the EPP Server.
     * @return org.openrtk.idl.epp02.epp_Greeting the greeting from the EPP Server
     * @throws org.openrtk.idl.epp02.epp_Exception if the server greeting is not present
     * @throws org.openrtk.idl.epp02.epp_XMLException if the server's greeting is not parsable
     */
    public epp_Greeting hello() throws epp_Exception, epp_XMLException
    {
        String method_name = "hello()";
        debug(DEBUG_LEVEL_THREE,method_name,"Entered");

        EPPGreeting hello_from_server;

        hello_from_server = new EPPGreeting();
        hello_from_server = (EPPGreeting) processAction( (epp_Action) hello_from_server );

        debug(DEBUG_LEVEL_THREE,method_name,"Leaving");

        return hello_from_server.getResponseData();
    }

    /**
     * Closes the connection to the EPP Server
     * @throws IOException if there was a Socket problem
     */
    public void disconnect() throws IOException
    {
        String method_name = "disconnect()";
        debug(DEBUG_LEVEL_THREE,method_name,"Entered");

        try
        {
            if ( socket_to_epp_server_ != null ) { socket_to_epp_server_.close(); }
            socket_to_epp_server_ = null;

            if ( reader_from_server_ != null ) { reader_from_server_.close(); }
            reader_from_server_ = null;

            if ( writer_to_server_ != null ) { writer_to_server_.close(); }
            writer_to_server_ = null;

            this.isValid = false;
            
        }
        catch (IOException xcp)
        {
            debug(DEBUG_LEVEL_ONE,method_name,xcp);
            this.isValid = false;

            throw xcp;
        }

        debug(DEBUG_LEVEL_THREE,method_name,"Leaving");
    }

    /**
     * Logs into the EPP Server using previously set client id and password
     * @param String the client TRID
     * @throws org.openrtk.idl.epp02.epp_XMLException if the request XML could not be built or if the response XML could not be parsed.
     * @throws org.openrtk.idl.epp02.epp_Exception if the EPP Server responded with an error code
     */
    public void login(String client_tr_id) throws epp_XMLException, epp_Exception
    {
        login(client_tr_id, null);
    }

    /**
     * Logs into the EPP Server using the given client id, password
     * @param String the client TRID
     * @param client_id The EPP client id
     * @param password The password associated with the client id
     * @throws org.openrtk.idl.epp02.epp_XMLException if the request XML could not be built or if the response XML could not be parsed.
     * @throws org.openrtk.idl.epp02.epp_Exception if the EPP Server responded with an error code
     */
    public void login(String client_tr_id, String epp_client_id, String epp_password) throws epp_XMLException, epp_Exception
    {
        setEPPClientID(epp_client_id);
        setEPPPassword(epp_password);
        login(client_tr_id, null);
    }

    /**
     * Logs into the EPP Server using the given client id, password and
     * new password.  Use this method if you want to change your EPP client
     * password in the server.
     * @param String the client TRID
     * @param client_id The EPP client id
     * @param password The password associated with the client id
     * @param new_password The password associated with the client id, use null if you do not want to change the password
     * @throws org.openrtk.idl.epp02.epp_XMLException if the request XML could not be built or if the response XML could not be parsed.
     * @throws org.openrtk.idl.epp02.epp_Exception if the EPP Server responded with an error code
     */
    public void login(String client_tr_id, String client_id, String password, String new_password) throws epp_XMLException, epp_Exception
    {
        setEPPClientID(client_id);
        setEPPPassword(password);
        login(client_tr_id, new_password);
    }

    /**
     * Logs into the EPP Server using the previously set client id, and password
     * If the new password is not null, it is also passed to the server to
     * change your EPP client password.<BR>
     * This method also includes domain, host and contact object/XML information
     * in the services section.  If you wish to omit one of the standard
     * EPP objects (or add any based on registry extensions to EPP),
     * then you'll have to populate the EPPLogin command manually and call
     * processAction() yourself.
     * @param String the client TRID
     * @param new_password The password associated with the client id. Use null if you do not want to change the password
     * @throws org.openrtk.idl.epp02.epp_XMLException if the request XML could not be built or if the response XML could not be parsed.
     * @throws org.openrtk.idl.epp02.epp_Exception if the EPP Server responded with an error code
     */
    public void login(String client_tr_id, String new_password) throws epp_XMLException, epp_Exception
    {
        String method_name = "login()";
        debug(DEBUG_LEVEL_THREE,method_name,"Entered");

        epp_LoginReq login_req = new epp_LoginReq();
        epp_Credentials creds = new epp_Credentials(epp_client_id_, epp_password_, new_password, null);
        creds.setOptions( new epp_Options(version_, lang_) );
        epp_Command command_data = new epp_Command(creds, null, client_tr_id);
        if( sendver_ ) command_data.setUnspec( new com.tucows.oxrs.epp02.rtk.xml.unspec.RTKVersion() );

        epp_Service[] services = new epp_Service[3];
        services[0] = new epp_Service("domain", "urn:iana:xml:ns:domain", "urn:iana:xml:ns:domain domain.xsd");
        services[1] = new epp_Service("host", "urn:iana:xml:ns:host", "urn:iana:xml:ns:host host.xsd");
        services[2] = new epp_Service("contact", "urn:iana:xml:ns:contact", "urn:iana:xml:ns:contact contact.xsd");

        login_req.setServices( services );
        login_req.setCmd( command_data );

        EPPLogin login_action = new EPPLogin();
        login_action.setRequestData(login_req);

	    try{
            processAction((epp_Action)login_action);
        }catch(epp_XMLException e){
			this.isValid = false;
			throw e;
	    }catch(epp_Exception e){
			this.isValid = false;
			throw e;
		}

	
        debug(DEBUG_LEVEL_THREE,method_name,"Leaving");

    }

    /**
     * Logs out of EPP Server
     * @param String the client TRID
     * @throws org.openrtk.idl.epp02.epp_XMLException if the request XML could not be built or if the response XML could not be parsed.
     * @throws org.openrtk.idl.epp02.epp_Exception if the EPP Server responded with an error code
     */
    public void logout(String client_trid) throws epp_XMLException, epp_Exception
    {
        String method_name = "logout()";
        debug(DEBUG_LEVEL_THREE,method_name,"Entered");

        EPPLogout logout_action = new EPPLogout();
        epp_LogoutReq logout_request = new epp_LogoutReq();
        logout_request.m_client_trid = client_trid;
        logout_action.setRequestData(logout_request);

        processAction((epp_Action)logout_action);

        debug(DEBUG_LEVEL_THREE,method_name,"Leaving");

    }

    /**
     * Returns whether RTK version number will be send on Login.
     *
     * @return true in case RTK version number will be send on Login, else false
     */
    public boolean isVersionSentOnLogin()
    {
        return sendver_;
    }

    /**
     * Set whether to send RTK version number on Login.
     *
     * @param send if true RTK version number will be send in unspec section on
     *      Login
     */
    public void setVersionSentOnLogin( boolean send )
    {
        sendver_ = send;
    }

    /**
     * @throws org.openrtk.idl.epp02.epp_XMLException if the request XML could not be built or if the response XML could not be parsed.
     * @throws org.openrtk.idl.epp02.epp_Exception if the EPP Server responded with an error code
     */
    public epp_PollRsp poll(String client_tr_id) throws epp_XMLException, epp_Exception
    {
        return poll(client_tr_id, null);
    }

    /**
     * @param message_id The id of the message to ACK.  If null, the Poll op will be REQ.
     * @throws org.openrtk.idl.epp02.epp_XMLException if the request XML could not be built or if the response XML could not be parsed.
     * @throws org.openrtk.idl.epp02.epp_Exception if the EPP Server responded with an error code
     */
    public epp_PollRsp poll(String client_tr_id, String message_id) throws epp_XMLException, epp_Exception
    {
        String method_name = "Poll()";
        debug(DEBUG_LEVEL_THREE,method_name,"Entered");

        epp_PollReq poll_request = new epp_PollReq();

        epp_Command command_data = new epp_Command(null, null, client_tr_id);
        poll_request.m_cmd = command_data;
        if ( message_id == null )
        {
            poll_request.m_op = epp_PollOpType.REQ;
        }
        else
        {
            poll_request.m_op = epp_PollOpType.ACK;
            poll_request.m_msgID = message_id;
        }

        EPPPoll poll = new EPPPoll();
        poll.setRequestData(poll_request);

        poll = (EPPPoll) processAction(poll);

        epp_PollRsp poll_response = poll.getResponseData();

        debug(DEBUG_LEVEL_THREE,method_name,"Leaving");

        return poll_response;

    }

}
