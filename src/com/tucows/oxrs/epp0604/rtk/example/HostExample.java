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
 * $Header: /cvsroot/epp-rtk/epp-rtk/java/src/com/tucows/oxrs/epp0604/rtk/example/HostExample.java,v 1.1 2003/03/21 15:52:30 tubadanm Exp $
 * $Revision: 1.1 $
 * $Date: 2003/03/21 15:52:30 $
 */

package com.tucows.oxrs.epp0604.rtk.example;

import java.util.*;

import com.tucows.oxrs.epp0604.rtk.*;
import com.tucows.oxrs.epp0604.rtk.xml.*;

import org.openrtk.idl.epp0604.*;
import org.openrtk.idl.epp0604.domain.*;
import org.openrtk.idl.epp0604.host.*;
import org.openrtk.idl.epp0604.contact.*;


/**
 * Example code for the EPP Host object.  Also shows creation of
 * EPPClient instance, login, logout and disconnect.
 *
 * @author Daniel Manley
 * @version $Revision: 1.1 $ $Date: 2003/03/21 15:52:30 $
 * @see com.tucows.oxrs.epp0604.rtk.EPPClient
 * @see com.tucows.oxrs.epp0604.rtk.xml.EPPGreeting
 * @see com.tucows.oxrs.epp0604.rtk.xml.EPPHostCheck
 * @see com.tucows.oxrs.epp0604.rtk.xml.EPPHostInfo
 * @see com.tucows.oxrs.epp0604.rtk.xml.EPPHostCreate
 * @see com.tucows.oxrs.epp0604.rtk.xml.EPPHostUpdate
 * @see com.tucows.oxrs.epp0604.rtk.xml.EPPHostDelete
**/
public class HostExample
{

    private static String USAGE = "Usage: com.tucows.oxrs.epp0604.rtk.example.HostExample epp_host_name epp_host_port epp_client_id epp_password epp_domain_name";

    /**
     * Main of the example.  Performs Host check, info, create, update and delete.
     */
    public static void main(String args[])
    {

        System.out.println("Start of the Host example");

        int current_expiration_year = 2002;
        epp_Command command_data = null;
        
        try
        {
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
            String epp_domain_name = args[4];

            int epp_host_port = Integer.parseInt(epp_host_port_string);

            EPPClient epp_client = new EPPClient(epp_host_name,
                                                 epp_host_port,
                                                 epp_client_id,
                                                 epp_password);
            
            epp_client.setLang("en");

            // The protocol used is set by the rtk.transport property
            // in etc/rtk.properties

            System.out.println("Connecting to the EPP Server and getting the greeting");
            epp_Greeting greeting = epp_client.connectAndGetGreeting();

            System.out.println("greeting's server: ["+greeting.m_server_id+"]");
            System.out.println("greeting's server-date: ["+greeting.m_server_date+"]");
            System.out.println("greeting's service menu: ["+greeting.getSvcMenu()+"]");
	    System.out.println();
 	    
            String client_trid = getClientTrid(epp_client_id);
            
            System.out.println("Logging into the EPP Server");

	    // If epp_client.setEPPServices() or epp_client.setEPPUnspecServices() 
            // have been called, epp_client.login() uses services values set by user,
            // otherwise, epp_client.login() fills in default service values for you
            // which are contact, domain and host (pretty standard stuff).
            epp_client.login(client_trid);

            try
            {
                // ***************************
                // Host Check
                // ***************************
                System.out.println("Creating the Host Check command");
                epp_HostCheckReq host_check_request = new epp_HostCheckReq();
                
                command_data = new epp_Command();
                // The client trid is optional by EPP.  it's main use
                // is for registrar tracking and logging of requests,
                // especially for data creation or modification requests.
                // Some registries make it mandatory and unique per session.
                command_data.m_client_trid = getClientTrid(epp_client_id);
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
                System.out.println("HostCheck results: host [ns1."+epp_client_id+".info] available? ["+EPPXMLBase.getAvailResultFor(check_results, "ns1."+epp_client_id+".info")+"]");
                System.out.println("HostCheck results: host [ns2."+epp_client_id+".info] available? ["+EPPXMLBase.getAvailResultFor(check_results, "ns2."+epp_client_id+".info")+"]");
                System.out.println("HostCheck results: host [dns.host.info] available? ["+EPPXMLBase.getAvailResultFor(check_results, "dns.host.info")+"]");
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
                // We're taking advantage epp_Result's toString() here
                // for debugging.  Take a look at the javadocs for
                // the full list of attributes in the class.
                System.err.println("\tresults: "+results[0]+"]");
            }
            catch ( Exception xcp )
            {
                // Other unexpected exceptions
                System.err.println("EPP Host Check failed! ["+xcp.getClass().getName()+"] ["+xcp.getMessage()+"]");
                xcp.printStackTrace();
            }


            // Need to create a contact to be used by domain create
            // because some registries have minimum requirements for
            // contacts associated with domains (i.e. usually, one of
            // each type, plus the registrant)

            // declaring the contact create request outside the try block 
            // because the contact id it contains is required elsewhere.
            System.out.println("Creating the Contact Create command");
            epp_ContactCreateReq contact_create_request = new epp_ContactCreateReq();
 
            try
            {
                // ***************************
                // Contact Create
                // ***************************

                // Please see the ContactExample.java source file for more information
                // on the Contact Create operation.  It is included here only because
                // a contact must exist before creating a domain.

                command_data = new epp_Command();
                command_data.m_client_trid = getClientTrid(epp_client_id);
                contact_create_request.m_cmd = command_data;
 
                // This example uses the client trid to generate a unique contact id (as best we can)
                // so the create doesn't fail and throw the whole example out the window
                Random rng =  new Random();
                String randnum = "" + rng.nextInt() % 999999;
                while ( (epp_client_id + randnum).length() > 16 ) {
                    // The contact ID length is a max of 16 characters
                    // let's chop the front off of random number until it fits
                    randnum = randnum.substring(0, randnum.length() - 1);
                }
                contact_create_request.m_id = epp_client_id + randnum;
 
                epp_ContactNameAddress name_address = new epp_ContactNameAddress();
                name_address.m_name = "John Doe";
                name_address.m_address = new epp_ContactAddress();
                name_address.m_address.m_street1 = "100 Centre St";
                name_address.m_address.m_city = "Townsville";
                name_address.m_address.m_state_province = "County Derry";
                name_address.m_address.m_postal_code = "Z1Z1Z1";
                // The country code must be a valid ISO country code.
                // Note that UK is not a valid ISO country code, use GB instead.
                name_address.m_address.m_country_code = "CA";
 
                contact_create_request.m_ascii_address = name_address;
 
                contact_create_request.m_email = "jdoe@company.info";
 
                // Contact Auth Info must be set, just give a fixed value now
                epp_AuthInfo contact_auth_info = new epp_AuthInfo();
                contact_auth_info.m_value = "changeme";
                contact_auth_info.m_type = epp_AuthInfoType.PW;
                contact_create_request.m_auth_info = contact_auth_info;
 
                EPPContactCreate contact_create = new EPPContactCreate();
                contact_create.setRequestData(contact_create_request);
 
                contact_create = (EPPContactCreate) epp_client.processAction(contact_create);
 
                epp_ContactCreateRsp contact_create_response = contact_create.getResponseData();
                epp_Response response = contact_create_response.m_rsp;
 
                epp_Result[] results = response.m_results;
                System.out.println("ContactCreate results: ["+results[0].m_code+"] ["+results[0].m_msg+"]");
                System.out.println("ContactCreate results: contact id ["+contact_create_response.m_id+"]");
            }
            catch ( epp_XMLException xcp )
            {
                System.err.println("epp_XMLException! ["+xcp.m_error_message+"]");
            }
            catch ( epp_Exception xcp )
            {
                System.err.println("epp_Exception!");
                epp_Result[] results = xcp.m_details;
                // We're taking advantage epp_Result's toString() here
                // for debugging.  Take a look at the javadocs for
                // the full list of attributes in the class.
                System.err.println("\tresult: ["+results[0]+"]");
            }
            catch ( Exception xcp )
            {
                // Other unexpected exceptions
                System.err.println("Contact Create failed! ["+xcp.getClass().getName()+"] ["+xcp.getMessage()+"]");
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
                command_data.m_client_trid = getClientTrid(epp_client_id);
                domain_create_request.m_cmd = command_data;

                domain_create_request.m_name = epp_domain_name;

                epp_AuthInfo domain_auth_info = new epp_AuthInfo();
                domain_auth_info.m_value = "exam123";
                domain_create_request.m_auth_info = domain_auth_info;
                               
                // Some registries require a minimum number of contacts for a domain
                Vector contacts = new Vector();
                contacts.add(new epp_DomainContact(epp_DomainContactType.TECH,"ABC-contact"));
                contacts.add(new epp_DomainContact(epp_DomainContactType.ADMIN,"ABC-contact"));
                contacts.add(new epp_DomainContact(epp_DomainContactType.BILLING,"ABC-contact"));
                domain_create_request.m_contacts = (epp_DomainContact[])contacts.toArray(new epp_DomainContact[1]);

                domain_create_request.m_registrant = contact_create_request.m_id;
                               
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
                epp_Result[] results = xcp.m_details;
                if ( results[0].m_code != epp_Session.EPP_OBJECT_EXISTS )
                {
                    System.out.println("Domain already exists!  Let's continue...");
                }
                else
                {
                    System.err.println("epp_Exception!");
                    // We're taking advantage epp_Result's toString() here
                    // for debugging.  Take a look at the javadocs for
                    // the full list of attributes in the class.
                    System.err.println("\tresult: ["+results[0]+"]");
                }
            }
            catch ( Exception xcp )
            {
                System.err.println("Failed to create domain required for host example ["+xcp.getMessage()+"]");
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
                // The client trid is optional by EPP.  it's main use
                // is for registrar tracking and logging of requests,
                // especially for data creation or modification requests.
                // Some registries make it mandatory and unique per session.
                command_data.m_client_trid = getClientTrid(epp_client_id);
                host_create_request.m_cmd = command_data;

                // When creating a host in a TLD for which the registry is authoritative,
                // at least one IP address is required.  An array is expected here,
                // but to avoid using Java native arrays, we're using an ArrayList
                // and then converting it to a String array.
                List ip_list = (List)new ArrayList();

                // This IP is hard coded here for the sake of the example.  If running this
                // give you an error of a duplicate IP (because some registries don't
                // allow two hosts to share the same IP), change this value and recompile.
                ip_list.add(new epp_HostAddress(epp_HostAddressType.IPV4, "130.111.111.200"));

                // some registries only support IPV4 format
                // ip_list.add(new epp_HostAddress(epp_HostAddressType.IPV6, "1080:0:0:0:8:800:200C:417A"));
                host_create_request.m_addresses = (epp_HostAddress[])EPPXMLBase.convertListToArray((new epp_HostAddress()).getClass(), ip_list);
                                
                host_create_request.m_name = "ns1." + epp_domain_name;

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
                System.out.println("HostCreate results: host name ["+host_create_response.m_name+"]");
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
                // We're taking advantage epp_Result's toString() here
                // for debugging.  Take a look at the javadocs for
                // the full list of attributes in the class.
                System.err.println("\tresult: ["+results[0]+"]");
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
                // The client trid is optional by EPP.  it's main use
                // is for registrar tracking and logging of requests,
                // especially for data creation or modification requests.
                // Some registries make it mandatory and unique per session.
                command_data.m_client_trid = getClientTrid(epp_client_id);
                host_info_request.m_cmd = command_data;

                host_info_request.m_name = "ns1."+ epp_domain_name;
                
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
                // For a Host Info, the hosts's addresses and status are also returned.
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
                // We're taking advantage epp_Result's toString() here
                // for debugging.  Take a look at the javadocs for
                // the full list of attributes in the class.
                System.err.println("\tresult: ["+results[0]+"]");
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
                // The client trid is optional by EPP.  it's main use
                // is for registrar tracking and logging of requests,
                // especially for data creation or modification requests.
                // Some registries make it mandatory and unique per session.
                command_data.m_client_trid = getClientTrid(epp_client_id);
                host_update_request.m_cmd = command_data;

                host_update_request.m_name = "ns1."+ epp_domain_name;
                
                epp_HostUpdateAddRemove add = new epp_HostUpdateAddRemove();
                // Here is a list of addresses to add to the host.
                // An array is expected, here, like in the host create,
                // we're using an ArrayList and then converting to a 
                // String array.
                List ip_list = (List)new ArrayList();
                // This IP is hard coded here for the sake of the example.  If running this
                // give you an error of a duplicate IP (because some registries don't
                // allow two hosts to share the same IP), change this value and recompile.
                ip_list.add(new epp_HostAddress(epp_HostAddressType.IPV4, "130.111.111.201"));
                add.m_addresses = (epp_HostAddress[])EPPXMLBase.convertListToArray((new epp_HostAddress()).getClass(), ip_list);

                // We also want to add the clientUpdateProhibited status to the host.
                add.m_status = new epp_HostStatus[1];
                add.m_status[0] = new epp_HostStatus();
                add.m_status[0].m_type = epp_HostStatusType.CLIENT_UPDATE_PROHIBITED;
                add.m_status[0].m_lang = "en";
                add.m_status[0].m_value = "Want to prevent accidental change of IP";
		
                // Set the add information
                host_update_request.m_add = add;
                
                epp_HostUpdateAddRemove remove = new epp_HostUpdateAddRemove();
                // Now to remove 1 address.
                ip_list = (List)new ArrayList();

                ip_list.add(new epp_HostAddress(epp_HostAddressType.IPV4, "130.111.111.200"));
                remove.m_addresses = (epp_HostAddress[])EPPXMLBase.convertListToArray((new epp_HostAddress()).getClass(), ip_list);

                // Set the remove information
                host_update_request.m_remove = remove;

                // We're also specifying the information to change.
                // Only the host's name can be changed.                
                host_update_request.m_change = new epp_HostUpdateChange();
                host_update_request.m_change.m_name = "ns1." + epp_domain_name;

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
                // We're taking advantage epp_Result's toString() here
                // for debugging.  Take a look at the javadocs for
                // the full list of attributes in the class.
                System.err.println("\tresult: ["+results[0]+"]");
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
                // The client trid is optional by EPP.  it's main use
                // is for registrar tracking and logging of requests,
                // especially for data creation or modification requests.
                // Some registries make it mandatory and unique per session.
                command_data.m_client_trid = getClientTrid(epp_client_id);
                host_delete_request.m_cmd = command_data;

                host_delete_request.m_name = "ns1." + epp_domain_name;
                
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
                // We're taking advantage epp_Result's toString() here
                // for debugging.  Take a look at the javadocs for
                // the full list of attributes in the class.
                System.err.println("\tresult: ["+results[0]+"]");
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

    protected static String getClientTrid(String epp_client_id)
    {
        return "ABC:"+epp_client_id+":"+System.currentTimeMillis();
    }
}
