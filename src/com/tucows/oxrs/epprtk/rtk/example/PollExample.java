/*
**
** EPP RTK Java
** Copyright (C) 2003, LibertyRMS, Inc.
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
 * $Header: /cvsroot/epp-rtk/epp-rtk/java/src/com/tucows/oxrs/epprtk/rtk/example/PollExample.java,v 1.1 2004/12/07 15:53:26 ewang2004 Exp $
 * $Revision: 1.1 $
 * $Date: 2004/12/07 15:53:26 $
 */

package com.tucows.oxrs.epprtk.rtk.example;

import java.util.*;
import java.io.*;

import com.tucows.oxrs.epprtk.rtk.*;
import com.tucows.oxrs.epprtk.rtk.xml.*;

import org.openrtk.idl.epprtk.*;
import org.openrtk.idl.epprtk.domain.*;
import org.openrtk.idl.epprtk.host.*;
import org.openrtk.idl.epprtk.contact.*;


/**
 * Example code for a querying and acknowledging Poll messages.
 *
 * @author Daniel Manley
 * @version $Revision: 1.1 $ $Date: 2004/12/07 15:53:26 $
**/
public class PollExample
{

    private static String USAGE = "Usage: com.tucows.oxrs.epprtk.rtk.example.PollExample epp_host_name epp_host_port epp_client_id epp_password";

    /**
     * Main of the example.
     */
    public static void main(String args[])
    {

        // This date will be used in the client trid
        // because the .biz registry requires unique
        // trid's per client session.
	Date current_time = new Date();

        System.out.println("Start of the Poll example");

        try
        {

            epp_Command command_data = null;

            if (args.length < 5)
            {
                System.err.println(USAGE);
                System.exit(1);
            }

            // calling setDebugLevel() with no params makes the RTK
            // read the "rtk.debuglevel" property from rtk.properties file
            RTKBase.setDebugLevel();

            String epp_host_name = args[0];
            String epp_host_port_string = args[1];
            String epp_client_id = args[2];
            String epp_password  = args[3];

            int epp_host_port = Integer.parseInt(epp_host_port_string);

            EPPClient epp_client = new EPPClient(epp_host_name,
                                                 epp_host_port,
                                                 epp_client_id,
                                                 epp_password);

            epp_client.setLang("en");
            
            System.out.println("Connecting to the EPP Server and getting the greeting");

            /*
             * Uncomment following line if you don't want to send RTK version
             * number on Login. Although Liberty RMS recomends to use this unspec
             * tag on Login request.
             */
            //epp_client.setVersionSentOnLogin( false );

            epp_Greeting greeting = epp_client.connectAndGetGreeting();

            System.out.println("greeting's server: ["+greeting.getServerId()+"]");
            System.out.println("greeting's server-date: ["+greeting.getServerDate()+"]");
            epp_ServiceMenu svc_menu = greeting.getSvcMenu();
            if ( svc_menu != null )
            {
                if ( svc_menu.getVersions() != null && svc_menu.getVersions().length > 0 )
                {
                    System.out.println("svc_menu's version: ["+svc_menu.getVersions()[0]+"]");
                }
                if ( svc_menu.getLangs() != null && svc_menu.getLangs().length > 0 )
                {
                    System.out.println("svc_menu's lang: ["+svc_menu.getLangs()[0]+"]");
                }
            }

            // The .biz registry requires unique client trid's for
            // a session, so we're using the date here to keep it unique
            String client_trid = "ABC:"+epp_client_id+":"+current_time.getTime();

            command_data = new epp_Command();
            command_data.setClientTrid( client_trid );

            System.out.println("Logging into the EPP Server");
            epp_client.login(client_trid);

            boolean keep_polling = true;
            
            while ( keep_polling )
            {
                String message_id = "";
                int response_code = 1000;
                epp_PollRsp poll_response;
                epp_Response response;
                
                // ***************************
                // Poll (for waiting messages)
                // ***************************
                System.out.println("Polling the server...");
                current_time = new Date();
                client_trid = "ABC:"+epp_client_id+":"+current_time.getTime();
                poll_response = epp_client.poll(client_trid);

                response = poll_response.getRsp();
                System.out.println("Poll results: "+response);
                System.out.println("Poll ResData: "+poll_response.getResData());
                if ( poll_response.getResData() != null )
                {
                    System.out.println("Poll ResData type: "+poll_response.getResData().getType());
                }

                if ( response.getMessageQueue() != null ) 
                {
                    message_id = response.getMessageQueue().getId();
                    response_code = response.getResults()[0].getCode();
    
    
                    if ( response_code != epp_Session.EPP_COMMAND_COMPLETED_SUCCESSFULLY_NO_MESSAGES )
                    {
                        BufferedReader buffed_reader = new BufferedReader(new InputStreamReader(System.in));
                        System.out.print("Do you wish to acknowledge this poll message [y]? ");
                        String answer = buffed_reader.readLine();
    
                        while ( ( answer != null ) &&
                                ( answer.length() != 0 ) &&
                                ( ! answer.equalsIgnoreCase("y") ) &&
                                ( ! answer.equalsIgnoreCase("n") ) )
                        {
                            answer = buffed_reader.readLine();
                        }
    
                        if ( ! answer.equalsIgnoreCase("n") )
                        {
                            System.out.println("Going to acknowledge poll message id ["+message_id+"]");
                            // ***************************
                            // Poll (acknowledge a message)
                            // ***************************
                            System.out.println("Polling the server...");
                            current_time = new Date();
                            client_trid = "ABC:"+epp_client_id+":"+current_time.getTime();
                            poll_response = epp_client.poll(client_trid, message_id);
    
                            response = poll_response.getRsp();
                            System.out.println("Poll results: "+response);
                            System.out.println("Poll ResData: "+poll_response.getResData());
    
                        }
                        else
                        {
                            System.out.println("Going to reject the transfer");
                            keep_polling = false;
                        }
                    }
                    else
                    {
                        keep_polling = false;
                    }
                }
                else 
                {
                    keep_polling = false;
                }

            }

            // All done with this session, so let's log out...
            System.out.println("Logging out from the EPP Server");
            epp_client.logout(client_trid);

            // ... and disconnect
            System.out.println("Disconnecting from the EPP Server");
            epp_client.disconnect();

        }
        catch ( epp_XMLException xcp )
        {
            System.err.println("epp_XMLException! ["+xcp.getErrorMessage()+"]");
        }
        catch ( epp_Exception xcp )
        {
            System.err.println("epp_Exception!");
            epp_Result[] results = xcp.getDetails();
            // We're taking advantage epp_Result's toString() here
            // for debugging.  Take a look at the javadocs for
            // the full list of attributes in the class.
            System.err.println("\tresult: ["+results[0]+"]");
        }
        catch ( Exception xcp )
        {
            System.err.println("Exception! ["+xcp.getClass().getName()+"] ["+xcp.getMessage()+"]");
            xcp.printStackTrace();
        }

    }
}
