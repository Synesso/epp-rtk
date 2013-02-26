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
 * $Header: /cvsroot/epp-rtk/epp-rtk/java/src/com/tucows/oxrs/epp0402/rtk/example/DomainExample.java,v 1.1 2003/03/21 16:35:35 tubadanm Exp $
 * $Revision: 1.1 $
 * $Date: 2003/03/21 16:35:35 $
 */

package com.tucows.oxrs.epp0402.rtk.example;

import java.util.*;
import java.io.*;

import com.tucows.oxrs.epp0402.rtk.*;
import com.tucows.oxrs.epp0402.rtk.xml.*;

import org.openrtk.idl.epp0402.*;
import org.openrtk.idl.epp0402.domain.*;
import org.openrtk.idl.epp0402.contact.*;


/**
 * Example code for the EPP Domain object.  Also shows creation of
 * EPPClient instance, login, logout and disconnect.
 *
 * @author Daniel Manley
 * @version $Revision: 1.1 $ $Date: 2003/03/21 16:35:35 $
 * @see com.tucows.oxrs.epp0402.rtk.EPPClient
 * @see com.tucows.oxrs.epp0402.rtk.xml.EPPGreeting
 * @see com.tucows.oxrs.epp0402.rtk.xml.EPPDomainCheck
 * @see com.tucows.oxrs.epp0402.rtk.xml.EPPDomainInfo
 * @see com.tucows.oxrs.epp0402.rtk.xml.EPPDomainCreate
 * @see com.tucows.oxrs.epp0402.rtk.xml.EPPDomainUpdate
 * @see com.tucows.oxrs.epp0402.rtk.xml.EPPDomainRenew
 * @see com.tucows.oxrs.epp0402.rtk.xml.EPPDomainTransfer
 * @see com.tucows.oxrs.epp0402.rtk.xml.EPPDomainDelete
**/
public class DomainExample
{

    private static String USAGE = "Usage: com.tucows.oxrs.epp0402.rtk.example.DomainExample epp_host_name epp_host_port epp_client_id epp_password epp_domain_name";

    /**
     * Main of the example.  Performs Domain check, info, create, update, renew, transfer and delete.
    **/
    public static void main(String args[])
    {

        System.out.println("Start of the Domain example");

        epp_Command command_data = null;
        epp_AuthInfo domain_auth_info = null;
        Date domain_exp_date = null;
        
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
            if ( greeting.m_versions != null && greeting.m_versions.length > 0 )
            {
                System.out.println("greeting's version: ["+greeting.m_versions[0]+"]");
            }
            if ( greeting.m_langs != null && greeting.m_langs.length > 0 )
            {
                System.out.println("greeting's lang: ["+greeting.m_langs[0]+"]");
            }
            
	    if ( greeting.m_services != null && greeting.m_services.length >0 )
	    {
		System.out.println("services provided by epp: ");
		for (int i=0; i<greeting.m_services.length; i++)
		{
		    System.out.println((epp_Service)greeting.m_services[i]);
		}
	    }
	    
	    if ( greeting.m_unspec_services != null && greeting.m_unspec_services.length >0 )
	    {
		System.out.println("\nunspecified services provided by epp: ");
		for (int i=0; i<greeting.m_unspec_services.length; i++)
		{
		    System.out.println((epp_Service)greeting.m_unspec_services[i]);
		}
	    }
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
                // Poll (for waiting messages)
                // ***************************
                System.out.println("Creating the Poll command");
                epp_PollReq poll_request = new epp_PollReq();
                command_data = new epp_Command();
                command_data.m_client_trid = getClientTrid(epp_client_id);
                
                poll_request.m_cmd = command_data;
                // Set the Poll "op" to REQ (i.e. request).  This will
                // query the message queue for the first message available.
                // Note that the REQ op does not dequeue the message.
                // The ACK op is required to acknowledge messages and 
                // remove them from the waiting queue.
                //
                // Note that the Poll op type of REQ is the default, so
                // it could have been left null here.
                poll_request.m_op = epp_PollOpType.REQ;

                EPPPoll poll = new EPPPoll();
                poll.setRequestData(poll_request);
                
                // Now ask the EPPClient to process the request and retrieve 
                // a response from the server.
                poll = (EPPPoll) epp_client.processAction(poll);
                // or, alternatively, this method can be used...
                //poll.fromXML(epp_client.processXML(poll.toXML()));

                epp_PollRsp poll_response = poll.getResponseData();
                epp_Response response = poll_response.m_rsp;
                epp_Result[] results = response.m_results;
                System.out.println("Poll results: ["+results[0].m_code+"] ["+results[0].m_msg+"]");
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
                // This single line will show you all data contained inside
                // this first result array element.
                //System.err.println("\tresult: ["+results[0]+"]");
            }
            catch ( Exception xcp )
            {
                // Other unexpected exceptions
                System.err.println("EPP Poll failed! ["+xcp.getClass().getName()+"] ["+xcp.getMessage()+"]");
                xcp.printStackTrace();
            }


            try
            {
                // ***************************
                // Domain Check
                // ***************************
                System.out.println("Creating the Domain Check command");
                epp_DomainCheckReq domain_check_request = new epp_DomainCheckReq();
                
                command_data = new epp_Command();
                // The client trid is optional by EPP.  it's main use
                // is for registrar tracking and logging of requests,
                // especially for data creation or modification requests.
                // Some registries make it mandatory and unique per session.
                command_data.m_client_trid = getClientTrid(epp_client_id);
                domain_check_request.m_cmd = command_data;

                // The Domain Check request can accept an array of domain
                // names.  In this example, an ArrayList is used to dynamically
                // create the List of domain names and then EPPXMLBase's
                // utility method convertListToStringArray() is used
                // to convert the List to a String array.
                List domain_list = (List)new ArrayList();
                domain_list.add(epp_client_id+"1.info");
                domain_list.add(epp_client_id+"2.info");
                domain_list.add(epp_domain_name);
                domain_check_request.m_names = EPPXMLBase.convertListToStringArray(domain_list);
                
                EPPDomainCheck domain_check = new EPPDomainCheck();
                domain_check.setRequestData(domain_check_request);
                
                // Now ask the EPPClient to process the request and retrieve 
                // a response from the server.
                domain_check = (EPPDomainCheck) epp_client.processAction(domain_check);
                // or, alternatively, this method can be used...
                //domain_check.fromXML(epp_client.processXML(domain_check.toXML()));

                epp_DomainCheckRsp domain_check_response = domain_check.getResponseData();
                epp_Response response = domain_check_response.m_rsp;
                epp_Result[] results = response.m_results;
                System.out.println("DomainCheck results: ["+results[0].m_code+"] ["+results[0].m_msg+"]");
                // All EPP Check requests, regardless of the object being checked,
                // will return a generic epp_CheckResult array.  To find the
                // check results for a particular object, EPPXMLBase's utility
                // method getCheckResultFor() can be used.  This method returns
                // a Boolean object or null if the value was not found in the
                // epp_CheckResult array.
                epp_CheckResult[] check_results = domain_check_response.m_results;
                System.out.println("DomainCheck results: domain ["+epp_client_id+"1.info] exists? ["+EPPXMLBase.getCheckResultFor(check_results, epp_client_id+"1.info")+"]");
                System.out.println("DomainCheck results: domain ["+epp_client_id+"2.info] exists? ["+EPPXMLBase.getCheckResultFor(check_results, epp_client_id+"2.info")+"]");
                System.out.println("DomainCheck results: domain ["+epp_domain_name+"] exists? ["+EPPXMLBase.getCheckResultFor(check_results, epp_domain_name)+"]");
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
                System.err.println("Domain Check failed! ["+xcp.getClass().getName()+"] ["+xcp.getMessage()+"]");
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
 
            // This example uses the client trid to generate a unique contact id
                // so the create doesn't fail throw the whole example out the window
                // (we're dividing the current time by 1000 because without it
                // the contact id is too long)
                contact_create_request.m_id = epp_client_id+(System.currentTimeMillis()/1000);
 
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


            try
            {
                // ***************************
                // Domain Create
                // ***************************
                System.out.println("Creating the Domain Create command");
                epp_DomainCreateReq domain_create_request = new epp_DomainCreateReq();
                
                command_data = new epp_Command();
                // The client trid is optional by EPP.  it's main use
                // is for registrar tracking and logging of requests,
                // especially for data creation or modification requests.
                // Some registries make it mandatory and unique per session.
                command_data.m_client_trid = getClientTrid(epp_client_id);
                domain_create_request.m_cmd = command_data;

                domain_create_request.m_name = epp_domain_name;
                // The domain's period is optional.  It is specified with
                // an object that contains the unit of measurement (years or 
                // months) and the actual period value.
                domain_create_request.m_period = new epp_DomainPeriod();
                domain_create_request.m_period.m_unit = epp_DomainPeriodUnitType.YEAR;
                domain_create_request.m_period.m_value = (short) 2;
                
                // At domain creation, if the registry requires nameservers,
                // you must specify another domain's nameserver's in the request.
                // You can't use nameserver in the same namespace as the domain,
                // but those host objects don't exist yet.
                List name_server_list = (List)new ArrayList();

                // quick little note -- it just happens that these
                // nameservers exist in many OT&E domain registries
                // out there, so they were chosen to make sure the 
                // create succeeds.  Feel free to use other values
                // in this example.
                name_server_list.add("ns1.domaindirect.com");
                name_server_list.add("ns2.domaindirect.com");
                domain_create_request.m_name_servers = EPPXMLBase.convertListToStringArray(name_server_list);
                                
                BufferedReader buffed_reader = new BufferedReader(new InputStreamReader(System.in));
                domain_auth_info = new epp_AuthInfo();
                System.out.print("Dear registrant, please enter a passphrase for your new domain(min 6, max 16): ");
                while ( domain_auth_info.m_value == null ||
                        domain_auth_info.m_value.length() == 0 )
                {
                    domain_auth_info.m_value = buffed_reader.readLine();
                }
                // For the current spec of EPP, PW is the only allowed type
                // of auth info.  So, the type can be left null and the RTK will
                // fill in the value for you.
                domain_auth_info.m_type = epp_AuthInfoType.PW;
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
                
                // Now ask the EPPClient to process the request and retrieve 
                // a response from the server.
                domain_create = (EPPDomainCreate) epp_client.processAction(domain_create);
                // or, alternatively, this method can be used...
                //domain_create.fromXML(epp_client.processXML(domain_create.toXML()));

                epp_DomainCreateRsp domain_create_response = domain_create.getResponseData();
                epp_Response response = domain_create_response.m_rsp;

                epp_Result[] results = response.m_results;
                System.out.println("DomainCreate results: ["+results[0].m_code+"] ["+results[0].m_msg+"]");
                // The domain's name and expiration date are returned on a
                // successful domain creation.
                System.out.println("DomainCreate results: domain name ["+domain_create_response.m_name+"] exp date ["+domain_create_response.m_expiration_date+"]");
                // Save the expiration date for the renew command later
                domain_exp_date = RTKBase.UTC_FMT.parse(domain_create_response.m_expiration_date);
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
                System.err.println("Domain Create failed! ["+xcp.getClass().getName()+"] ["+xcp.getMessage()+"]");
                xcp.printStackTrace();
            }


            try
            {
                // ***************************
                // Domain Info
                // ***************************
                System.out.println("Creating the Domain Info command");
                epp_DomainInfoReq domain_info_request = new epp_DomainInfoReq();

                command_data = new epp_Command();
                // The client trid is optional by EPP.  it's main use
                // is for registrar tracking and logging of requests,
                // especially for data creation or modification requests.
                // Some registries make it mandatory and unique per session.
                command_data.m_client_trid = getClientTrid(epp_client_id);
                domain_info_request.m_cmd = command_data;

                // The only domain-specific parameter is the domain name itself.
                domain_info_request.m_name = epp_domain_name;
                
                EPPDomainInfo domain_info = new EPPDomainInfo();
                domain_info.setRequestData(domain_info_request);
                
                // Now ask the EPPClient to process the request and retrieve 
                // a response from the server.
                domain_info = (EPPDomainInfo) epp_client.processAction(domain_info);
                // or, alternatively, this method can be used...
                //domain_info.fromXML(epp_client.processXML(domain_info.toXML()));

                epp_DomainInfoRsp domain_info_response = domain_info.getResponseData();
                epp_Response response = domain_info_response.m_rsp;
                epp_Result[] results = response.m_results;

                // You can also save the authorization information from an info where
                // the calling registrar is the sponsoring client for the
                // object.
                domain_auth_info = domain_info_response.m_auth_info;
                
                System.out.println("DomainInfo results: ["+results[0].m_code+"] ["+results[0].m_msg+"]");
                // The Info command returns some standard information like
                // the current sponsoring client id, the creator client id,
                // the create time and the last update time.
                // For a Domain Info, the domain's nameservers, hosts, status
                // last transfer client id, last transfer date,
                // expiration date and domain ROID are returned.
                System.out.println("DomainInfo results: clID ["+domain_info_response.m_client_id+"] crID ["+domain_info_response.m_created_by+"]");
                System.out.println("DomainInfo results: crDate ["+domain_info_response.m_created_date+"] upDate ["+domain_info_response.m_updated_date+"]");
                System.out.println("DomainInfo results: exDate ["+domain_info_response.m_expiration_date+"]");
                // Save the expiration date for the renew command later
                domain_exp_date = RTKBase.UTC_FMT.parse(domain_info_response.m_expiration_date);
                if ( domain_auth_info != null )
                {
                    System.out.println("Domain's authID ["+domain_auth_info.m_value+"]");
                }
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
                System.err.println("Domain Info failed! ["+xcp.getClass().getName()+"] ["+xcp.getMessage()+"]");
                xcp.printStackTrace();
            }


            try
            {
                // ***************************
                // Domain Update
                // ***************************
                System.out.println("Creating the Domain Update command");
                epp_DomainUpdateReq domain_update_request = new epp_DomainUpdateReq();
                
                command_data = new epp_Command();
                // The client trid is optional by EPP.  it's main use
                // is for registrar tracking and logging of requests,
                // especially for data creation or modification requests.
                // Some registries make it mandatory and unique per session.
                command_data.m_client_trid = getClientTrid(epp_client_id);
                domain_update_request.m_cmd = command_data;

                domain_update_request.m_name = epp_domain_name;
                
                epp_DomainUpdateAddRemove add = new epp_DomainUpdateAddRemove();
                // Here is a list of nameservers to add to the domain.
                // An array is expected here, but to avoid using Java native
                // arrays, we're using an ArrayList and then converting it to a 
                // String array.
                List name_server_list = (List)new ArrayList();
                name_server_list.add("ns1.domain.info");
                name_server_list.add("ns2.domain.info");
                add.m_name_servers = EPPXMLBase.convertListToStringArray(name_server_list);
                // We also want to add the clientTransferProhibited status to the domain.
                // This time we'll create an array of epp_DomainStatus 
                // directly.  EPP allows for registrar notes in the 
                // status field.  EPP even allows for a language specifier.
                add.m_status = new epp_DomainStatus[1];
                add.m_status[0] = new epp_DomainStatus();
                add.m_status[0].m_type = epp_DomainStatusType.CLIENT_TRANSFER_PROHIBITED;
                add.m_status[0].m_lang = "fr";
                add.m_status[0].m_value = "Le client n'as pas envoyé de l'argent";


                // Set the add information
                domain_update_request.m_add = add;
                
                epp_DomainUpdateAddRemove remove = new epp_DomainUpdateAddRemove();
                // Now to remove the two old nameservers.
                name_server_list = (List)new ArrayList();
                name_server_list.add("ns1.domaindirect.com");
                name_server_list.add("ns2.domaindirect.com");
                remove.m_name_servers = EPPXMLBase.convertListToStringArray(name_server_list);

                // Set the remove information
                domain_update_request.m_remove = remove;

                // We're also specifying the information to change.
                // Only the auth info and the registrant information can be changed.                
                domain_update_request.m_change = new epp_DomainUpdateChange();
                
                // We would need to have created another contact to really
                // change it, so we won't do this one for now.
                //domain_update_request.m_change.m_registrant = "registrar100";

                BufferedReader buffed_reader = new BufferedReader(new InputStreamReader(System.in));
		epp_AuthInfo domain_auth_info_new = new epp_AuthInfo();
                System.out.println("---Now, give you an opportunity to change the passphrase for your domain---");
		System.out.print("Input the new passphrase or just press \'Enter\' to ignore (min 6, max 16): ");
		domain_auth_info_new.m_value = buffed_reader.readLine();
                if ( domain_auth_info_new.m_value != null ) 
		{
		    domain_update_request.m_change.m_auth_info = domain_auth_info_new;
		    domain_auth_info = domain_auth_info_new;
		}
		
                EPPDomainUpdate domain_update = new EPPDomainUpdate();
                domain_update.setRequestData(domain_update_request);
                
                // Now ask the EPPClient to process the request and retrieve 
                // a response from the server.
                domain_update = (EPPDomainUpdate) epp_client.processAction(domain_update);
                // or, alternatively, this method can be used...
                //domain_update.fromXML(epp_client.processXML(domain_update.toXML()));

                epp_DomainUpdateRsp domain_update_response = domain_update.getResponseData();
                epp_Response response = domain_update_response.m_rsp;
                epp_Result[] results = response.m_results;
                System.out.println("DomainUpdate results: ["+results[0].m_code+"] ["+results[0].m_msg+"]");
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
                System.err.println("Domain Update failed! ["+xcp.getClass().getName()+"] ["+xcp.getMessage()+"]");
                xcp.printStackTrace();
            }


            try
            {
                // ***************************
                // Domain Renew
                // ***************************
                System.out.println("Creating the Domain Renew command");
                epp_DomainRenewReq domain_renew_request = new epp_DomainRenewReq();
                
                command_data = new epp_Command();
                // The client trid is optional by EPP.  it's main use
                // is for registrar tracking and logging of requests,
                // especially for data creation or modification requests.
                // Some registries make it mandatory and unique per session.
                command_data.m_client_trid = getClientTrid(epp_client_id);
                domain_renew_request.m_cmd = command_data;

                domain_renew_request.m_name = epp_domain_name;
                // As in the domain create operation, the domain period
                // may be specified here also.
                domain_renew_request.m_period = new epp_DomainPeriod();
                domain_renew_request.m_period.m_unit = epp_DomainPeriodUnitType.YEAR;
                domain_renew_request.m_period.m_value = (short) 2;
                // The domain's current expiration must also be specified
                // to unintentional multiple renew request from succeeding.
                // The format of the expiration date must be "YYYY-MM-DD"
                domain_renew_request.m_current_expiration_date = RTKBase.DATE_FMT.format(domain_exp_date);
                
                EPPDomainRenew domain_renew = new EPPDomainRenew();
                domain_renew.setRequestData(domain_renew_request);
                
                // Now ask the EPPClient to process the request and retrieve 
                // a response from the server.
                domain_renew = (EPPDomainRenew) epp_client.processAction(domain_renew);
                // or, alternatively, this method can be used...
                //domain_renew.fromXML(epp_client.processXML(domain_renew.toXML()));

                epp_DomainRenewRsp domain_renew_response = domain_renew.getResponseData();
                epp_Response response = domain_renew_response.m_rsp;
                epp_Result[] results = response.m_results;
                System.out.println("DomainRenew results: ["+results[0].m_code+"] ["+results[0].m_msg+"]");
                // The domain renew action returns the domain's new expiration
                // date if the request was successful
                System.out.println("DomainRenew results: new exDate ["+domain_renew_response.m_expiration_date+"]");
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
                System.err.println("Domain Renew failed! ["+xcp.getClass().getName()+"] ["+xcp.getMessage()+"]");
                xcp.printStackTrace();
            }


            try
            {
                // ***************************
                // Domain Transfer (Query)
                // ***************************
                System.out.println("Creating the Domain Transfer command");
                epp_DomainTransferReq domain_transfer_request = new epp_DomainTransferReq();
                
                command_data = new epp_Command();
                // The client trid is optional by EPP.  it's main use
                // is for registrar tracking and logging of requests,
                // especially for data creation or modification requests.
                // Some registries make it mandatory and unique per session.
                command_data.m_client_trid = getClientTrid(epp_client_id);
                domain_transfer_request.m_cmd = command_data;

                // The Domain Transfer request is an EPP "transfer" request,
                // meaning it requires an "op" value and the object's
                // current auth info for successful processing.
                epp_TransferRequest transfer_request = new epp_TransferRequest();
                // A transfer query will only query the status of the current
                // pending transfer or the last completed/cancelled/rejected
                // transfer.  To request a transfer, the "op" should be set to
                // REQUEST.
                //
                // QUERY is the default transfer op type, so it 
                // could have been left null here.
                transfer_request.m_op = epp_TransferOpType.QUERY;
                // domain_auth_info is REQUIRED only when transfer op type
		// is REQUEST, so the line below is unnecessary, it will 
		// be ignored by the server
                transfer_request.m_auth_info = domain_auth_info;
                domain_transfer_request.m_trans = transfer_request;

                domain_transfer_request.m_name = epp_domain_name;
                
                // On a transfer request, EPP allows for a domain renewal
                // to be bundled into a domain transfer.
                // If the period is not specified, 1 year is the default.
                //
                // So if this was a transfer request, we could do this.
                /*
                domain_transfer_request.m_period = new epp_DomainPeriod();
                domain_transfer_request.m_period.m_unit = epp_DomainPeriodUnitType.YEAR;
                domain_transfer_request.m_period.m_value = (short) 2;
                */
                
                EPPDomainTransfer domain_transfer = new EPPDomainTransfer();
                domain_transfer.setRequestData(domain_transfer_request);
                
                // Now ask the EPPClient to process the request and retrieve 
                // a response from the server.
                domain_transfer = (EPPDomainTransfer) epp_client.processAction(domain_transfer);
                // or, alternatively, this method can be used...
                //domain_transfer.fromXML(epp_client.processXML(domain_transfer.toXML()));

                epp_DomainTransferRsp domain_transfer_response = domain_transfer.getResponseData();
                epp_Response response = domain_transfer_response.m_rsp;
                epp_Result[] results = response.m_results;
                System.out.println("DomainTransfer results: ["+results[0].m_code+"] ["+results[0].m_msg+"]");

                System.out.println("DomainTransfer Results: transfer status ["+domain_transfer_response.m_trn_data.m_transfer_status+"]");
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
                System.err.println("Domain Transfer (Query) failed! ["+xcp.getClass().getName()+"] ["+xcp.getMessage()+"]");
                xcp.printStackTrace();
            }


            try
            {
                // ***************************
                // Domain Delete
                // ***************************
                System.out.println("Creating the Domain Delete command");
                epp_DomainDeleteReq domain_delete_request = new epp_DomainDeleteReq();
                
                command_data = new epp_Command();
                // The client trid is optional by EPP.  it's main use
                // is for registrar tracking and logging of requests,
                // especially for data creation or modification requests.
                // Some registries make it mandatory and unique per session.
                command_data.m_client_trid = getClientTrid(epp_client_id);
                domain_delete_request.m_cmd = command_data;

                domain_delete_request.m_name = epp_domain_name;
                
                EPPDomainDelete domain_delete = new EPPDomainDelete();
                domain_delete.setRequestData(domain_delete_request);
                
                // Now ask the EPPClient to process the request and retrieve 
                // a response from the server.
                domain_delete = (EPPDomainDelete) epp_client.processAction(domain_delete);
                // or, alternatively, this method can be used...
                //domain_delete.fromXML(epp_client.processXML(domain_delete.toXML()));

                epp_DomainDeleteRsp domain_delete_response = domain_delete.getResponseData();
                epp_Response response = domain_delete_response.m_rsp;
                epp_Result[] results = response.m_results;
                System.out.println("DomainDelete results: ["+results[0].m_code+"] ["+results[0].m_msg+"]");
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
                System.err.println("Domain Delete failed! ["+xcp.getClass().getName()+"] ["+xcp.getMessage()+"]");
                xcp.printStackTrace();
            }


            // All done with this session, so let's log out...
            System.out.println("Logging out from the EPP Server");
            epp_client.logout(getClientTrid(epp_client_id));

            // ... and disconnect            
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
