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
 * $Header: /cvsroot/epp-rtk/epp-rtk/java/src/com/tucows/oxrs/epp02/rtk/example/HostExample.java,v 1.1 2003/03/21 17:13:40 tubadanm Exp $
 * $Revision: 1.1 $
 * $Date: 2003/03/21 17:13:40 $
 */

package com.tucows.oxrs.epp02.rtk.example;

import java.util.*;

import com.tucows.oxrs.epp02.rtk.*;
import com.tucows.oxrs.epp02.rtk.xml.*;

import org.openrtk.idl.epp02.*;
import org.openrtk.idl.epp02.domain.*;
import org.openrtk.idl.epp02.host.*;


/**
 * Example code for the EPP Host object.  Also shows creation of
 * EPPClient instance, login, logout and disconnect.
 *
 * @author Daniel Manley
 * @version $Revision: 1.1 $ $Date: 2003/03/21 17:13:40 $
 * @see com.tucows.oxrs.epp02.rtk.EPPClient
 * @see com.tucows.oxrs.epp02.rtk.xml.EPPGreeting
 * @see com.tucows.oxrs.epp02.rtk.xml.EPPHostCheck
 * @see com.tucows.oxrs.epp02.rtk.xml.EPPHostInfo
 * @see com.tucows.oxrs.epp02.rtk.xml.EPPHostCreate
 * @see com.tucows.oxrs.epp02.rtk.xml.EPPHostUpdate
 * @see com.tucows.oxrs.epp02.rtk.xml.EPPHostDelete
**/
public class HostExample
{

    private static String USAGE = "Usage: com.tucows.oxrs.epp02.rtk.example.HostExample epp_host_name epp_host_port epp_client_id epp_password";

    /**
     * Main of the example.  Performs Host check, info, create, update and delete.
    **/
    public static void main(String args[])
    {

        System.out.println("Start of the Host example");

            int current_expiration_year = 2002;
        epp_Command command_data = null;
        
        try
        {
            if (args.length != 4)
            {
                System.err.println(USAGE);
                System.exit(1);
            }

            RTKBase.setDebugLevel(RTKBase.DEBUG_LEVEL_TWO);

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
            epp_client.setProtocol(EPPClient.PROTOCOL_TCP_TLS);

            System.out.println("Connecting to the EPP Server and getting the greeting");
            epp_Greeting greeting = epp_client.connectAndGetGreeting();

            System.out.println("greeting's server: ["+greeting.m_server_id+"]");
            System.out.println("greeting's server-date: ["+greeting.m_server_date+"]");
            if ( greeting.m_versions != null && greeting.m_versions.length > 0 )
            {
                System.out.println("greeting's version: ["+greeting.m_versions[0]+"]");
            }
            if ( greeting.m_langs != null && greeting.m_langs.length > 0 )
            {
                System.out.println("greeting's lang: ["+greeting.m_langs[0]+"]");
            }

            String client_trid = "ABC:"+epp_client_id+":123";
            
            System.out.println("Logging into the EPP Server");
            epp_client.login(client_trid);

            try
            {
                // ***************************
                // Host Check
                // ***************************
                System.out.println("Creating the Host Check command");
                epp_HostCheckReq host_check_request = new epp_HostCheckReq();
                
                command_data = new epp_Command();
                // The client trid is optional.  it's main use
                // is for registrar tracking and logging of requests,
                // especially for data creation or modification requests
                command_data.m_client_trid = client_trid;
                host_check_request.m_cmd = command_data;

                // The Host Check request can accept an array of host
                // names.  In this example, an ArrayList is used to dynamically
                // create the List of host names and then EPPXMLBase's
                // utility method convertListToStringArray() is used
                // to convert the List to a String array.
                List host_list = (List)new ArrayList();
                host_list.add("ns1."+epp_client_id+".info");
                host_list.add("ns2."+epp_client_id+".info");
                host_list.add("dns.host.info");
                host_check_request.m_names = EPPXMLBase.convertListToStringArray(host_list);
                
                EPPHostCheck host_check = new EPPHostCheck();
                host_check.setRequestData(host_check_request);
                
                // Now ask the EPPClient to process the request and retrieve 
                // a response from the server.
                host_check = (EPPHostCheck) epp_client.processAction(host_check);
                // or, alternatively, this method can be used...
                //host_check.fromXML(epp_client.processXML(host_check.toXML()));

                epp_HostCheckRsp host_check_response = host_check.getResponseData();
                epp_Response response = host_check_response.m_rsp;
                epp_Result[] results = response.m_results;
                System.out.println("HostCheck results: ["+results[0].m_code+"] ["+results[0].m_msg+"]");
                // All EPP Check requests, regardless of the object being checked,
                // will return a generic epp_CheckResult array.  To find the
                // check results for a particular object, EPPXMLBase's utility
                // method getCheckResultFor() can be used.  This method returns
                // a Boolean object or null if the value was not found in the
                // epp_CheckResult array.
                epp_CheckResult[] check_results = host_check_response.m_results;
                System.out.println("HostCheck results: host [ns1."+epp_client_id+".info] exists? ["+EPPXMLBase.getCheckResultFor(check_results, "ns1."+epp_client_id+".info")+"]");
                System.out.println("HostCheck results: host [ns2."+epp_client_id+".info] exists? ["+EPPXMLBase.getCheckResultFor(check_results, "ns2."+epp_client_id+".info")+"]");
                System.out.println("HostCheck results: host [dns.host.info] exists? ["+EPPXMLBase.getCheckResultFor(check_results, "dns.host.info")+"]");
            }
            catch ( epp_XMLException xcp )
            {
                // Either the request was missing some required data in
                // validation before sending to the server, or the server's
                // response was either unparsable or missing some required data.
                System.err.println("epp_XMLException! ["+xcp.m_error_message+"]");
            }
            catch ( epp_Exception xcp )
            {
                // The EPP Server has responded with an error code with
                // some optional messages to describe the error.
                System.err.println("epp_Exception!");
                epp_Result[] results = xcp.m_details;
                System.err.println("\tcode: ["+results[0].m_code+"] lang: ["+results[0].m_lang+"] msg: ["+results[0].m_msg+"]");
                if ( results[0].m_values != null && results[0].m_values.length > 0 )
                {
                    System.err.println("\tvalue: ["+results[0].m_values[0]+"]");
                }
            }
            catch ( Exception xcp )
            {
                // Other unexpected exceptions
                System.err.println("EPP Host Check failed! ["+xcp.getClass().getName()+"] ["+xcp.getMessage()+"]");
                xcp.printStackTrace();
            }


            // need a Domain Create so that the Hosts can be created
            try
            {
                // ***************************
                // Domain Create
                // ***************************
                
                // Please see the DomainExample.java source file for more information
                // on the Domain Create operation.  It is included here only because
                // a domain must exist before creating hosts in its namespace.
                
                System.out.println("Creating the Domain Create command");
                epp_DomainCreateReq domain_create_request = new epp_DomainCreateReq();
                
                command_data = new epp_Command();
                command_data.m_client_trid = client_trid;
                domain_create_request.m_cmd = command_data;

                domain_create_request.m_name = epp_client_id+"-host-test.info";
                                
                EPPDomainCreate domain_create = new EPPDomainCreate();
                domain_create.setRequestData(domain_create_request);
                
                domain_create = (EPPDomainCreate) epp_client.processAction(domain_create);
                // or, alternatively, this method can be used...
                //domain_create.fromXML(epp_client.processXML(domain_create.toXML()));

                epp_DomainCreateRsp domain_create_response = domain_create.getResponseData();
                epp_Response response = domain_create_response.m_rsp;

            }
            catch ( epp_Exception xcp )
            {
                System.err.println("epp_Exception!");
                epp_Result[] results = xcp.m_details;
                if ( results[0].m_code != epp_Session.EPP_OBJECT_EXISTS )
                {
                    System.err.println("Failed to create domain required for host example");
                    System.exit(1);
                }
            }
            catch ( Exception xcp )
            {
                System.err.println("Failed to create domain required for host example");
                System.exit(1);
            }


            try
            {
                // ***************************
                // Host Create
                // ***************************
                System.out.println("Creating the Host Create command");
                epp_HostCreateReq host_create_request = new epp_HostCreateReq();
                
                command_data = new epp_Command();
                // The client trid is optional.  it's main use
                // is for registrar tracking and logging of requests,
                // especially for data creation or modification requests
                command_data.m_client_trid = client_trid;
                host_create_request.m_cmd = command_data;

                host_create_request.m_name = "ns1."+epp_client_id+"-host-test.info";

                // When creating a host in a TLD for which the registry is authoritative,
                // at least one IP address is required.  An array is expected here,
                // but to avoid using Java native arrays, we're using an ArrayList
                // and then converting it to a String array.
                List ip_list = (List)new ArrayList();
                ip_list.add(new epp_HostAddress(epp_HostAddressType.IPV4, "100.103.44.151"));
                ip_list.add(new epp_HostAddress(epp_HostAddressType.IPV6, "1080:0:0:0:8:800:200C:417A"));
                host_create_request.m_addresses = (epp_HostAddress[])EPPXMLBase.convertListToArray((new epp_HostAddress()).getClass(), ip_list);
                                
                EPPHostCreate host_create = new EPPHostCreate();
                host_create.setRequestData(host_create_request);
                
                // Now ask the EPPClient to process the request and retrieve 
                // a response from the server.
                host_create = (EPPHostCreate) epp_client.processAction(host_create);
                // or, alternatively, this method can be used...
                //host_create.fromXML(epp_client.processXML(host_create.toXML()));

                epp_HostCreateRsp host_create_response = host_create.getResponseData();
                epp_Response response = host_create_response.m_rsp;

                epp_Result[] results = response.m_results;
                System.out.println("HostCreate results: ["+results[0].m_code+"] ["+results[0].m_msg+"]");
                System.out.println("HostCreate results: host roid ["+host_create_response.m_roid+"]");
            }
            catch ( epp_XMLException xcp )
            {
                // Either the request was missing some required data in
                // validation before sending to the server, or the server's
                // response was either unparsable or missing some required data.
                System.err.println("epp_XMLException! ["+xcp.m_error_message+"]");
            }
            catch ( epp_Exception xcp )
            {
                // The EPP Server has responded with an error code with
                // some optional messages to describe the error.
                System.err.println("epp_Exception!");
                epp_Result[] results = xcp.m_details;
                System.err.println("\tcode: ["+results[0].m_code+"] lang: ["+results[0].m_lang+"] msg: ["+results[0].m_msg+"]");
                if ( results[0].m_values != null && results[0].m_values.length > 0 )
                {
                    System.err.println("\tvalue: ["+results[0].m_values[0]+"]");
                }
            }
            catch ( Exception xcp )
            {
                // Other unexpected exceptions
                System.err.println("EPP Host Create failed! ["+xcp.getClass().getName()+"] ["+xcp.getMessage()+"]");
                xcp.printStackTrace();
            }


            try
            {
                // ***************************
                // Host Info
                // ***************************
                System.out.println("Creating the Host Info command");
                epp_HostInfoReq host_info_request = new epp_HostInfoReq();
                
                command_data = new epp_Command();
                // The client trid is optional.  it's main use
                // is for registrar tracking and logging of requests,
                // especially for data creation or modification requests
                command_data.m_client_trid = client_trid;
                host_info_request.m_cmd = command_data;

                host_info_request.m_name = "ns1."+epp_client_id+"-host-test.info";
                
                EPPHostInfo host_info = new EPPHostInfo();
                host_info.setRequestData(host_info_request);
                
                // Now ask the EPPClient to process the request and retrieve 
                // a response from the server.
                host_info = (EPPHostInfo) epp_client.processAction(host_info);
                // or, alternatively, this method can be used...
                //host_info.fromXML(epp_client.processXML(host_info.toXML()));

                epp_HostInfoRsp host_info_response = host_info.getResponseData();
                epp_Response response = host_info_response.m_rsp;
                
                epp_Result[] results = response.m_results;
                System.out.println("HostInfo results: ["+results[0].m_code+"] ["+results[0].m_msg+"]");
                // The Info command returns some standard information like
                // the current sponsoring client id, the creator client id,
                // the create time and the last update time.
                // For a Host Info, the hosts's addresses are also returned.
                System.out.println("HostInfo results: clID ["+host_info_response.m_client_id+"] crID ["+host_info_response.m_created_by+"]");
                System.out.println("HostInfo results: crDate ["+host_info_response.m_created_date+"] upDate ["+host_info_response.m_updated_date+"]");
                System.out.println("HostInfo results: number of ipaddresses ["+( host_info_response.m_addresses == null ? 0 : host_info_response.m_addresses.length )+"]");
            }
            catch ( epp_XMLException xcp )
            {
                // Either the request was missing some required data in
                // validation before sending to the server, or the server's
                // response was either unparsable or missing some required data.
                System.err.println("epp_XMLException! ["+xcp.m_error_message+"]");
            }
            catch ( epp_Exception xcp )
            {
                // The EPP Server has responded with an error code with
                // some optional messages to describe the error.
                System.err.println("epp_Exception!");
                epp_Result[] results = xcp.m_details;
                System.err.println("\tcode: ["+results[0].m_code+"] lang: ["+results[0].m_lang+"] msg: ["+results[0].m_msg+"]");
                if ( results[0].m_values != null && results[0].m_values.length > 0 )
                {
                    System.err.println("\tvalue: ["+results[0].m_values[0]+"]");
                }
            }
            catch ( Exception xcp )
            {
                // Other unexpected exceptions
                System.err.println("EPP Host Info failed! ["+xcp.getClass().getName()+"] ["+xcp.getMessage()+"]");
                xcp.printStackTrace();
            }


            try
            {
                // ***************************
                // Host Update
                // ***************************
                System.out.println("Creating the Host Update command");
                epp_HostUpdateReq host_update_request = new epp_HostUpdateReq();
                
                command_data = new epp_Command();
                // The client trid is optional.  it's main use
                // is for registrar tracking and logging of requests,
                // especially for data creation or modification requests
                command_data.m_client_trid = client_trid;
                host_update_request.m_cmd = command_data;

                host_update_request.m_name = "ns1."+epp_client_id+"-host-test.info";
                
                epp_HostUpdateAddRemove add = new epp_HostUpdateAddRemove();
                // Here is a list of addresses to add to the host.
                // An array is expected, here, like in the host create,
                // we're using an ArrayList and then converting to a 
                // String array.
                List ip_list = (List)new ArrayList();
                ip_list.add(new epp_HostAddress(epp_HostAddressType.IPV4, "101.22.55.99"));
                add.m_addresses = (epp_HostAddress[])EPPXMLBase.convertListToArray((new epp_HostAddress()).getClass(), ip_list);

                // Set the add information
                host_update_request.m_add = add;
                
                epp_HostUpdateAddRemove remove = new epp_HostUpdateAddRemove();
                // Now to remove 1 address.
                ip_list = (List)new ArrayList();
                ip_list.add(new epp_HostAddress(epp_HostAddressType.IPV6, "1080:0:0:0:8:800:200C:417A"));
                remove.m_addresses = (epp_HostAddress[])EPPXMLBase.convertListToArray((new epp_HostAddress()).getClass(), ip_list);

                // Set the remove information
                host_update_request.m_remove = remove;

                // We're also specifying the information to change.
                // Only the host's name can be changed.                
                host_update_request.m_change = new epp_HostUpdateChange();
                host_update_request.m_change.m_name = "ns99."+epp_client_id+"-host-test.info";

                EPPHostUpdate host_update = new EPPHostUpdate();
                host_update.setRequestData(host_update_request);
                
                // Now ask the EPPClient to process the request and retrieve 
                // a response from the server.
                host_update = (EPPHostUpdate) epp_client.processAction(host_update);
                // or, alternatively, this method can be used...
                //host_update.fromXML(epp_client.processXML(host_update.toXML()));

                epp_HostUpdateRsp host_update_response = host_update.getResponseData();
                epp_Response response = host_update_response.m_rsp;
                epp_Result[] results = response.m_results;
                System.out.println("HostUpdate results: ["+results[0].m_code+"] ["+results[0].m_msg+"]");
            }
            catch ( epp_XMLException xcp )
            {
                // Either the request was missing some required data in
                // validation before sending to the server, or the server's
                // response was either unparsable or missing some required data.
                System.err.println("epp_XMLException! ["+xcp.m_error_message+"]");
            }
            catch ( epp_Exception xcp )
            {
                // The EPP Server has responded with an error code with
                // some optional messages to describe the error.
                System.err.println("epp_Exception!");
                epp_Result[] results = xcp.m_details;
                System.err.println("\tcode: ["+results[0].m_code+"] lang: ["+results[0].m_lang+"] msg: ["+results[0].m_msg+"]");
                if ( results[0].m_values != null && results[0].m_values.length > 0 )
                {
                    System.err.println("\tvalue: ["+results[0].m_values[0]+"]");
                }
            }
            catch ( Exception xcp )
            {
                // Other unexpected exceptions
                System.err.println("Host Update failed! ["+xcp.getClass().getName()+"] ["+xcp.getMessage()+"]");
                xcp.printStackTrace();
            }


            try
            {
                // ***************************
                // Host Delete
                // ***************************
                System.out.println("Creating the Host Delete command");
                epp_HostDeleteReq host_delete_request = new epp_HostDeleteReq();
                
                command_data = new epp_Command();
                // The client trid is optional.  it's main use
                // is for registrar tracking and logging of requests,
                // especially for data creation or modification requests
                command_data.m_client_trid = client_trid;
                host_delete_request.m_cmd = command_data;

                host_delete_request.m_name = "ns1."+epp_client_id+"-host-test.info";
                
                EPPHostDelete host_delete = new EPPHostDelete();
                host_delete.setRequestData(host_delete_request);
                
                // Now ask the EPPClient to process the request and retrieve 
                // a response from the server.
                host_delete = (EPPHostDelete) epp_client.processAction(host_delete);
                // or, alternatively, this method can be used...
                //host_delete.fromXML(epp_client.processXML(host_delete.toXML()));

                epp_HostDeleteRsp host_delete_response = host_delete.getResponseData();
                epp_Response response = host_delete_response.m_rsp;
                epp_Result[] results = response.m_results;
                System.out.println("HostDelete results: ["+results[0].m_code+"] ["+results[0].m_msg+"]");
            }
            catch ( epp_XMLException xcp )
            {
                // Either the request was missing some required data in
                // validation before sending to the server, or the server's
                // response was either unparsable or missing some required data.
                System.err.println("epp_XMLException! ["+xcp.m_error_message+"]");
            }
            catch ( epp_Exception xcp )
            {
                // The EPP Server has responded with an error code with
                // some optional messages to describe the error.
                System.err.println("epp_Exception!");
                epp_Result[] results = xcp.m_details;
                System.err.println("\tcode: ["+results[0].m_code+"] lang: ["+results[0].m_lang+"] msg: ["+results[0].m_msg+"]");
                if ( results[0].m_values != null && results[0].m_values.length > 0 )
                {
                    System.err.println("\tvalue: ["+results[0].m_values[0]+"]");
                }
            }
            catch ( Exception xcp )
            {
                // Other unexpected exceptions
                System.err.println("EPP Host Delete failed! ["+xcp.getClass().getName()+"] ["+xcp.getMessage()+"]");
                xcp.printStackTrace();
            }



            System.out.println("Logging out from the EPP Server");
            epp_client.logout(client_trid);
            
            System.out.println("Disconnecting from the EPP Server");
            epp_client.disconnect();

        }
        catch ( epp_XMLException xcp )
        {
            System.err.println("epp_XMLException! ["+xcp.m_error_message+"]");
        }
        catch ( epp_Exception xcp )
        {
            System.err.println("epp_Exception!");
            epp_Result[] results = xcp.m_details;
            System.err.println("\tcode: ["+results[0].m_code+"] lang: ["+results[0].m_lang+"] msg: ["+results[0].m_msg+"]");
            if ( results[0].m_values != null && results[0].m_values.length > 0 )
            {
                System.err.println("\tvalue: ["+results[0].m_values[0]+"]");
            }
        }
        catch ( Exception xcp )
        {
            System.err.println("Exception! ["+xcp.getClass().getName()+"] ["+xcp.getMessage()+"]");
            xcp.printStackTrace();
        }

    }
}
