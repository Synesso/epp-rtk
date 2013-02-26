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
 * $Header: /cvsroot/epp-rtk/epp-rtk/java/src/com/tucows/oxrs/epp0402/rtk/example/SessionExample.java,v 1.1 2003/03/21 16:35:35 tubadanm Exp $
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
import org.openrtk.idl.epp0402.host.*;
import org.openrtk.idl.epp0402.contact.*;


/**
 * Example code for a typical logical EPP sessions.
 * For more information on the creation of Domain, Host and Contact
 * objects, please see their respective example Java source files.
 *
 * @author Daniel Manley
 * @version $Revision: 1.1 $ $Date: 2003/03/21 16:35:35 $
 * @see com.tucows.oxrs.epp0402.rtk.example.DomainExample
 * @see com.tucows.oxrs.epp0402.rtk.example.HostExample
 * @see com.tucows.oxrs.epp0402.rtk.example.ContactExample
**/
public class SessionExample
{

    private static String USAGE = "Usage: com.tucows.oxrs.epp0402.rtk.example.SessionExample epp_host_name epp_host_port epp_client_id epp_password domain_name [contact_id1] [contact_id2]";

    /**
     * Main of the example.  Performs typical Domain, Host and Contact operations
     * in a logical order.
     */
    public static void main(String args[])
    {

        System.out.println("Start of the Session example");

        Date domain_exp_date = null;
        epp_AuthInfo domain_auth_info = null;
        epp_AuthInfo contact1_auth_info = null;
        epp_AuthInfo contact2_auth_info = null;
        // This date will be used in the client trid
        // because the .biz registry requires unique
        // trid's per client session.
	Date current_time = new Date();

        try
        {

            String[] domain_nameservers = null;
            epp_Command command_data = null;
            epp_TransferRequest transfer_request = null;
            epp_CheckResult[] check_results = null;

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
            String domain_name  = args[4];
            String contact_id1 = null;
            String contact_id2 = null;
            if ( args.length > 5 )
            {
                contact_id1 = args[5];
            }
            if ( args.length > 6 )
            {
                contact_id2 = args[6];
            }

            if ( contact_id1 == null ) contact_id1 = epp_client_id + "001";
            if ( contact_id2 == null ) contact_id2 = epp_client_id + "002";
            
            int epp_host_port = Integer.parseInt(epp_host_port_string);

            EPPClient epp_client = new EPPClient(epp_host_name,
                                                 epp_host_port,
                                                 epp_client_id,
                                                 epp_password);

            epp_client.setLang("en");
            
            // we use plain socket for this example
            // epp_client.setProtocol(EPPClient.PROTOCOL_TCP_TLS);
            // epp_client.setProtocol(EPPClient.PROTOCOL_TCP);

            System.out.println("Connecting to the EPP Server and getting the greeting");

            /*
             * Uncomment following line if you don't want to send RTK version
             * number on Login. Although Liberty RTK recomends to use this unspec
             * tag on Login request.
             */
            //epp_client.setVersionSentOnLogin( false );

            epp_Greeting greeting = epp_client.connectAndGetGreeting();

            System.out.println("greeting's server: ["+greeting.getServerId()+"]");
            System.out.println("greeting's server-date: ["+greeting.getServerDate()+"]");
            if ( greeting.getVersions() != null && greeting.getVersions().length > 0 )
            {
                System.out.println("greeting's version: ["+greeting.getVersions()[0]+"]");
            }
            if ( greeting.getLangs() != null && greeting.getLangs().length > 0 )
            {
                System.out.println("greeting's lang: ["+greeting.getLangs()[0]+"]");
            }

            // The .biz registry requires unique client trid's for
            // a session, so we're using the date here to keep it unique
            String client_trid = "ABC:"+epp_client_id+":"+current_time.getTime();

            command_data = new epp_Command();
            command_data.setClientTrid( client_trid );

            System.out.println("Logging into the EPP Server");
            epp_client.login(client_trid);

            try
            {
                // ***************************
                // Poll (for waiting messages)
                // ***************************
                System.out.println("Polling the server...");
                current_time = new Date();
                client_trid = "ABC:"+epp_client_id+":"+current_time.getTime();
                epp_PollRsp poll_response = epp_client.poll(client_trid);

                epp_Response response = poll_response.getRsp();
                System.out.println("Poll results: "+response);
                System.out.println("Poll ResData type: "+poll_response.getResData().getType());
                System.out.println("Poll ResData: "+poll_response.getResData());
            }
            catch ( epp_XMLException xcp )
            {
                // Either the request was missing some required data in
                // validation before sending to the server, or the server's
                // response was either unparsable or missing some required data.
                System.err.println("epp_XMLException! ["+xcp.getErrorMessage()+"]");
            }
            catch ( epp_Exception xcp )
            {
                // The EPP Server has responded with an error code with
                // some optional messages to describe the error.
                System.err.println("epp_Exception!");
                epp_Result[] results = xcp.getDetails();
                System.err.println("\tcode: ["+results[0].getCode()+"] lang: ["+results[0].getLang()+"] msg: ["+results[0].getMsg()+"]");
                if ( results[0].getValues() != null && results[0].getValues().length > 0 )
                {
                    System.err.println("\tvalue: ["+results[0].getValues()[0]+"]");
                }
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
                //
                // First, the registrar should check if the given domain
                // exists in the registry.  If it does, we'll skip the domain
                // create step.
                //
                // ***************************
                System.out.println("Creating the Domain Check command");
                epp_DomainCheckReq domain_check_request = new epp_DomainCheckReq();

                // The .biz registry requires unique client trid's for
                // a session, so we're using the date here to keep it unique
                current_time = new Date();
                client_trid = "ABC:"+epp_client_id+":"+current_time.getTime();
                command_data.setClientTrid( client_trid );
                domain_check_request.setCmd( command_data );

                List domain_list = (List)new ArrayList();
                domain_list.add(domain_name);
                domain_check_request.setNames( EPPXMLBase.convertListToStringArray(domain_list) );

                EPPDomainCheck domain_check = new EPPDomainCheck();
                domain_check.setRequestData(domain_check_request);

                domain_check = (EPPDomainCheck) epp_client.processAction(domain_check);

                epp_DomainCheckRsp domain_check_response = domain_check.getResponseData();
                check_results = domain_check_response.getResults();
                System.out.println("DomainCheck results: domain ["+domain_name+"] exists? ["+EPPXMLBase.getCheckResultFor(check_results, domain_name)+"]");

                if ( EPPXMLBase.getCheckResultFor(check_results, domain_name) != null &&
                     ! EPPXMLBase.getCheckResultFor(check_results, domain_name).booleanValue() )
                {

                    // We're going to be creating the domain in the registry.
                    // Let's see if the user gave us a contact_id to use
                    // in the domain creation, or if we have to create a contact
                    // as well.

                    boolean contact1_exists = false;
                    boolean contact2_exists = false;

                    if ( contact_id1 != null )
                    {
                        // ***************************
                        // Contact Check
                        //
                        // Make sure the contact_roid we were given exists
                        // in the registry.
                        //
                        // ***************************
                        System.out.println("Creating the Contact Check command for ["+contact_id1+"]");
                        epp_ContactCheckReq contact_check_request = new epp_ContactCheckReq();

                        current_time = new Date();
                        client_trid = "ABC:"+epp_client_id+":"+current_time.getTime();
                        command_data.setClientTrid( client_trid );
                        contact_check_request.setCmd( command_data );

                        List contact_list = (List)new ArrayList();
                        contact_list.add(contact_id1);
                        if ( contact_id2 != null )
                        {
                            contact_list.add(contact_id2);
                        }
                        contact_check_request.setIds( EPPXMLBase.convertListToStringArray(contact_list) );

                        EPPContactCheck contact_check = new EPPContactCheck();
                        contact_check.setRequestData(contact_check_request);

                        contact_check = (EPPContactCheck) epp_client.processAction(contact_check);

                        epp_ContactCheckRsp contact_check_response = contact_check.getResponseData();
                        check_results = contact_check_response.getResults();
                        System.out.println("ContactCheck results: contact ["+contact_id1+"] exists? ["+EPPXMLBase.getCheckResultFor(check_results, contact_id1)+"]");
                        System.out.println("ContactCheck results: contact ["+contact_id2+"] exists? ["+EPPXMLBase.getCheckResultFor(check_results, contact_id2)+"]");
                        if ( EPPXMLBase.getCheckResultFor(check_results, contact_id1) != null )
                        {
                            contact1_exists = EPPXMLBase.getCheckResultFor(check_results, contact_id1).booleanValue();
                        }
                        if ( contact_id2 != null &&
                             EPPXMLBase.getCheckResultFor(check_results, contact_id2) != null )
                        {
                            contact2_exists = EPPXMLBase.getCheckResultFor(check_results, contact_id2).booleanValue();
                        }
                    }

                    // id 1 will be used as the registrant for the domain.
                    if ( contact_id1 == null || ! contact1_exists )
                    {
                        // ***************************
                        // Contact Create
                        //
                        // The given contact_id1 does not exist in the registry
                        // or there was no contact_id1 specified, so let's
                        // create one now.
                        //
                        // ***************************
                        System.out.println("Creating the Contact Create command");
                        epp_ContactCreateReq contact_create_request = new epp_ContactCreateReq();

                        current_time = new Date();
                        client_trid = "ABC:"+epp_client_id+":"+current_time.getTime();
                        command_data.setClientTrid( client_trid );
                        contact_create_request.setCmd( command_data );
                        contact_create_request.setId( contact_id1 );
                        
                        BufferedReader buffed_reader = new BufferedReader(new InputStreamReader(System.in));
                        contact1_auth_info = new epp_AuthInfo();
                        System.out.print("Dear registrant, please enter a passphrase for the new registrant contact(min 6, max 16): ");
                        while ( contact1_auth_info.getValue() == null ||
                                contact1_auth_info.getValue().length() == 0 )
                        {
                            contact1_auth_info.setValue( buffed_reader.readLine() );
                        }
                        contact1_auth_info.setType( epp_AuthInfoType.PW );
                        contact_create_request.setAuthInfo( contact1_auth_info );

                        epp_ContactNameAddress name_address = new epp_ContactNameAddress();
                        name_address.setName( "John Doe" );
                        name_address.setOrg( "ACME Solutions" );
                        epp_ContactAddress address = new epp_ContactAddress();
                        address.setStreet1( "100 Centre St" );
                        address.setCity( "Townsville" );
                        address.setStateProvince( "County Derry" );
                        address.setPostalCode( "Z1Z1Z1" );
                        address.setCountryCode( "CA" );
                        name_address.setAddress( address );

                        contact_create_request.setAsciiAddress( name_address );
                        contact_create_request.setVoice( new epp_ContactPhone("1234", "+1.4165559999") );
                        contact_create_request.setFax( new epp_ContactPhone("9876", "+1.4165558888") );
                        contact_create_request.setEmail( "john.doe@company.info" );

                        EPPContactCreate contact_create = new EPPContactCreate();
                        contact_create.setRequestData(contact_create_request);

                        contact_create = (EPPContactCreate) epp_client.processAction(contact_create);

                        epp_ContactCreateRsp contact_create_response = contact_create.getResponseData();
                        System.out.println("ContactCreate results: contact id ["+contact_create_response.getId()+"]");

                    }

                    // id 2 will be used as the "tech" contact for the domain
                    // we'll be creating later.
                    if ( contact_id2 == null || ! contact2_exists )
                    {
                        // ***************************
                        // Contact Create
                        //
                        // The given contact_id2 does not exist in the registry
                        // or there was no contact_id2 specified, so let's
                        // create one now.
                        //
                        // ***************************
                        System.out.println("Creating the Contact Create command");
                        epp_ContactCreateReq contact_create_request = new epp_ContactCreateReq();

                        current_time = new Date();
                        client_trid = "ABC:"+epp_client_id+":"+current_time.getTime();
                        command_data.setClientTrid( client_trid );
                        contact_create_request.setCmd( command_data );
                        contact_create_request.setId( contact_id2 );
                        
                        BufferedReader buffed_reader = new BufferedReader(new InputStreamReader(System.in));
                        contact2_auth_info = new epp_AuthInfo();
                        System.out.print("Dear registrant, please enter a passphrase for the new tech contact:(min 6, max 16) ");
                        while ( contact2_auth_info.getValue() == null ||
                                contact2_auth_info.getValue().length() == 0 )
                        {
                            contact2_auth_info.setValue( buffed_reader.readLine() );
                        }
                        contact2_auth_info.setType( epp_AuthInfoType.PW );
                        contact_create_request.setAuthInfo( contact2_auth_info );

                        epp_ContactNameAddress name_address = new epp_ContactNameAddress();
                        name_address.setName( "Jane Doe" );
                        name_address.setOrg( "ACME Technicians" );
                        epp_ContactAddress address = new epp_ContactAddress();
                        address.setStreet1( "101 Centre St" );
                        address.setCity( "Townsville" );
                        address.setStateProvince( "County Derry" );
                        address.setPostalCode( "Z1Z1Z1" );
                        address.setCountryCode( "CA" );
                        name_address.setAddress( address );

                        contact_create_request.setAsciiAddress( name_address );
                        contact_create_request.setVoice( new epp_ContactPhone("1234", "+1.4165551111") );
                        contact_create_request.setFax( new epp_ContactPhone("9876", "+1.4165552222") );
                        contact_create_request.setEmail( "jane.doe@company.info" );

                        EPPContactCreate contact_create = new EPPContactCreate();
                        contact_create.setRequestData(contact_create_request);

                        contact_create = (EPPContactCreate) epp_client.processAction(contact_create);

                        epp_ContactCreateRsp contact_create_response = contact_create.getResponseData();
                        System.out.println("ContactCreate results: contact id ["+contact_create_response.getId()+"]");

                    }


                    // Since we're going to create the domain,
                    // we have to ask the registrant for
                    // authorization information (a secret password,
                    // or something similar).
                    BufferedReader buffed_reader = new BufferedReader(new InputStreamReader(System.in));
                    domain_auth_info = new epp_AuthInfo();
                    System.out.print("Dear registrant, please enter a passphrase for your new domain:(min 6, max 16) ");
                    while ( domain_auth_info.getValue() == null ||
                            domain_auth_info.getValue().length() == 0 )
                    {
                        domain_auth_info.setValue( buffed_reader.readLine() );
                    }
                    domain_auth_info.setType( epp_AuthInfoType.PW );

                    // ***************************
                    // Domain Create
                    //
                    // Domain doesn't exist in the registry, so create it now.
                    //
                    // ***************************
                    System.out.println("Creating the Domain Create command");
                    epp_DomainCreateReq domain_create_request = new epp_DomainCreateReq();

                    current_time = new Date();
                    client_trid = "ABC:"+epp_client_id+":"+current_time.getTime();
                    command_data.setClientTrid( client_trid );
                    domain_create_request.setCmd( command_data );

                    domain_create_request.setName( domain_name );
                    epp_DomainPeriod period = new epp_DomainPeriod();
                    // Note that some openrtk might not accept registration
                    // periods by months.
                    period.setUnit( epp_DomainPeriodUnitType.YEAR );
                    period.setValue( (short) 2 );
                    domain_create_request.setPeriod( period );

                    domain_create_request.setRegistrant( contact_id1 );
                    List domain_contacts = new ArrayList();
                    // EPP Domain registries often require at least one
                    // of each type of contact.
                    domain_contacts.add( new epp_DomainContact( epp_DomainContactType.TECH, contact_id2 ) );
                    domain_contacts.add( new epp_DomainContact( epp_DomainContactType.ADMIN, contact_id1 ) );
                    domain_contacts.add( new epp_DomainContact( epp_DomainContactType.BILLING, contact_id2 ) );
                    domain_create_request.setContacts( (epp_DomainContact[]) EPPXMLBase.convertListToArray((new epp_DomainContact()).getClass(), domain_contacts) );

                    domain_create_request.setAuthInfo( domain_auth_info );

                    // From an EPP perspective, nameserver associations are
                    // optional for a domain, so we're not specifying them
                    // here.  We will add them later in the domain update.

                    EPPDomainCreate domain_create = new EPPDomainCreate();
                    domain_create.setRequestData(domain_create_request);

                    domain_create = (EPPDomainCreate) epp_client.processAction(domain_create);

                    // We don't particularily care about the response here.
                    // As long as an expection was not thrown, then the
                    // creation was successful.  We'll get the expiration
                    // date later in a domain info.

                } // end if for domain not existing in registry.



                // OK, before trying to do anything to this domain,
                // we should check to see if we have a situation where
                // the domain already existed and it's not owned by us.
                // If we're not the sponsoring registrar then we can't do
                // anything to it and the session end here.

                // ***************************
                // Domain Info
                //
                // Info will return to us a list of nameservers and
                // the auth Info if we're the owner.
                //
                // ***************************
                System.out.println("Creating the Domain Info command");
                epp_DomainInfoReq domain_info_request = new epp_DomainInfoReq();

                current_time = new Date();
                client_trid = "ABC:"+epp_client_id+":"+current_time.getTime();
                command_data.setClientTrid( client_trid );
                domain_info_request.setCmd( command_data );

                domain_info_request.setName( domain_name );

                EPPDomainInfo domain_info = new EPPDomainInfo();
                domain_info.setRequestData(domain_info_request);

                domain_info = (EPPDomainInfo) epp_client.processAction(domain_info);

                epp_DomainInfoRsp domain_info_response = domain_info.getResponseData();

                System.out.println("DomainInfo Results: registrant ["+domain_info_response.getRegistrant()+"]");
                System.out.println("DomainInfo Results: status count ["+domain_info_response.getStatus().length+"]");
                for ( int i = 0; i < domain_info_response.getStatus().length; i++ )
                {
                    System.out.println("\tstatus["+i+"] string ["+EPPDomainBase.domainStatusToString( domain_info_response.getStatus()[i].getType() )+"]");
                    System.out.println("\tstatus["+i+"] note ["+domain_info_response.getStatus()[i].getValue()+"]");
                }
                // Save the expiration date for the renew command later
                domain_exp_date = RTKBase.UTC_FMT.parse(domain_info_response.getExpirationDate());
                // Save the list of nameservers
                domain_nameservers = domain_info_response.getNameServers();

                // Save the auth ID.
                domain_auth_info = domain_info_response.getAuthInfo();
                if ( domain_info_response.getAuthInfo() == null )
                {
                    // We're out of luck, this domain is owned by another
                    // registrar.  The session ends.

                    System.out.println("Domain belongs to another registrar, building transfer command.");

                    // ***************************
                    // Domain Transfer (Request)
                    //
                    // Ok, so the domain is not owned by us, so let's try to transfer it
                    //
                    // ***************************

                    // First we have to know the auth info, so let's ask the registrant
                    BufferedReader buffed_reader = new BufferedReader(new InputStreamReader(System.in));
                    epp_AuthInfo other_domain_auth_info = new epp_AuthInfo();
                    System.out.print("Dear registrant, please enter the passphrase for the domain you wish to transfer:(min 6, max 16) ");
                    while ( other_domain_auth_info.getValue() == null ||
                            other_domain_auth_info.getValue().length() == 0 )
                    {
                        other_domain_auth_info.setValue( buffed_reader.readLine() );
                    }
                    other_domain_auth_info.setType( epp_AuthInfoType.PW );

                    System.out.println("Creating the Domain Transfer command");
                    epp_DomainTransferReq domain_transfer_request = new epp_DomainTransferReq();

                    current_time = new Date();
                    client_trid = "ABC:"+epp_client_id+":"+current_time.getTime();
                    command_data.setClientTrid( client_trid );
                    domain_transfer_request.setCmd( command_data );

                    transfer_request = new epp_TransferRequest();
                    transfer_request.setOp( epp_TransferOpType.REQUEST );
                    // we just asked for the auth info, so let's use it here.
                    transfer_request.setAuthInfo( other_domain_auth_info );
                    domain_transfer_request.setTrans( transfer_request );

                    domain_transfer_request.setName( domain_name );

                    EPPDomainTransfer domain_transfer = new EPPDomainTransfer();
                    domain_transfer.setRequestData(domain_transfer_request);

                    domain_transfer = (EPPDomainTransfer) epp_client.processAction(domain_transfer);

                    epp_DomainTransferRsp domain_transfer_response = domain_transfer.getResponseData();
                    System.out.println("DomainTransfer Results: transfer status ["+EPPXMLBase.transferStatusToString( domain_transfer_response.getTrnData().getTransferStatus() )+"]");

                    // If an exception was thrown to this command, then probably
                    // the auth ID we used was wrong, so maybe someone
                    // transfered the domain away from us.

                    System.out.println("Logging out from the EPP Server");
                    epp_client.logout(client_trid);
                    System.out.println("Disconnecting from the EPP Server");
                    epp_client.disconnect();
                    System.exit(1);
                }


                // The domain is ours.
                // Now let's create some hosts in this domain.
                // If the domain had the possibility of existing before and it
                // did than maybe the hosts exists in the registry too.
                // Let's check....

                // ***************************
                // Host Check
                //
                // Check for the existance of two hosts, ns1 and ns2
                // in the domain given to us by the user.
                //
                // ***************************
                System.out.println("Creating the Host Check command");
                epp_HostCheckReq host_check_request = new epp_HostCheckReq();

                current_time = new Date();
                client_trid = "ABC:"+epp_client_id+":"+current_time.getTime();
                command_data.setClientTrid( client_trid );
                host_check_request.setCmd( command_data );

                List host_list = (List)new ArrayList();
                host_list.add("ns1."+domain_name);
                host_list.add("ns2."+domain_name);
                host_check_request.setNames( EPPXMLBase.convertListToStringArray(host_list) );

                EPPHostCheck host_check = new EPPHostCheck();
                host_check.setRequestData(host_check_request);

                host_check = (EPPHostCheck) epp_client.processAction(host_check);

                epp_HostCheckRsp host_check_response = host_check.getResponseData();
                check_results = host_check_response.getResults();
                System.out.println("HostCheck results: host [ns1."+domain_name+"] exists? ["+EPPXMLBase.getCheckResultFor(check_results, "ns1."+domain_name)+"]");
                System.out.println("HostCheck results: host [ns2."+domain_name+"] exists? ["+EPPXMLBase.getCheckResultFor(check_results, "ns2."+domain_name)+"]");

                if ( EPPXMLBase.getCheckResultFor(check_results, "ns1."+domain_name) == null ||
                     EPPXMLBase.getCheckResultFor(check_results, "ns1."+domain_name).booleanValue() == false )
                {
                    // ***************************
                    // Host Create
                    //
                    // Host ns1."domain_name" doesn't exist, so let's create it.
                    //
                    // ***************************
                    System.out.println("Creating the Host Create command");
                    epp_HostCreateReq host_create_request = new epp_HostCreateReq();

                    current_time = new Date();
                    client_trid = "ABC:"+epp_client_id+":"+current_time.getTime();
                    command_data.setClientTrid( client_trid );
                    host_create_request.setCmd( command_data );

                    host_create_request.setName( "ns1."+domain_name );

                    List ip_list = (List)new ArrayList();
                    // Some registries restrict the number of IPs per address type to 1,
                    // so, we'll only use 1 in this example.  Also, some registries
                    // restrict the number of times an IP address may be used to 1,
                    // so we'll ask the user for a unique value.
                    BufferedReader buffed_reader = new BufferedReader(new InputStreamReader(System.in));
                    System.out.print("Dear registrant, please enter an IPv4 address for the nameserver "+host_create_request.getName()+"\n(it must not already be used and must not be a restricted address): ");
                    String ipAddr = null;
                    while ( ipAddr == null || ipAddr.length() == 0 )
                    {
                        ipAddr = buffed_reader.readLine();
                    }
                    ip_list.add(new epp_HostAddress(epp_HostAddressType.IPV4, ipAddr));
                    host_create_request.setAddresses( (epp_HostAddress[])EPPXMLBase.convertListToArray((new epp_HostAddress()).getClass(), ip_list) );

                    EPPHostCreate host_create = new EPPHostCreate();
                    host_create.setRequestData(host_create_request);

                    host_create = (EPPHostCreate) epp_client.processAction(host_create);

                    // As long as an exception is not thrown than the host
                    // create succeeded.

                }

                if ( EPPXMLBase.getCheckResultFor(check_results, "ns2."+domain_name) == null ||
                     EPPXMLBase.getCheckResultFor(check_results, "ns2."+domain_name).booleanValue() == false )
                {
                    // ***************************
                    // Host Create
                    //
                    // Host ns2."domain_name" doesn't exist, so let's create it.
                    //
                    // ***************************
                    System.out.println("Creating the Host Create command");
                    epp_HostCreateReq host_create_request = new epp_HostCreateReq();

                    current_time = new Date();
                    client_trid = "ABC:"+epp_client_id+":"+current_time.getTime();
                    command_data.setClientTrid( client_trid );
                    host_create_request.setCmd( command_data );

                    host_create_request.setName( "ns2."+domain_name );

                    List ip_list = (List)new ArrayList();
                    // Like in the creation of the first host, we'll ask the 
                    // registrant for a valid IPv4 address
                    BufferedReader buffed_reader = new BufferedReader(new InputStreamReader(System.in));
                    System.out.print("Dear registrant, please enter an IPv4 address for the nameserver "+host_create_request.getName()+"\n(it must not already be used and must not be a restricted address): ");
                    String ipAddr = null;
                    while ( ipAddr == null || ipAddr.length() == 0 )
                    {
                        ipAddr = buffed_reader.readLine();
                    }
                    ip_list.add(new epp_HostAddress(epp_HostAddressType.IPV4, ipAddr));
                    host_create_request.setAddresses( (epp_HostAddress[])EPPXMLBase.convertListToArray((new epp_HostAddress()).getClass(), ip_list) );

                    EPPHostCreate host_create = new EPPHostCreate();
                    host_create.setRequestData(host_create_request);

                    host_create = (EPPHostCreate) epp_client.processAction(host_create);

                    // As long as an exception is not thrown than the host
                    // create succeeded.

                }

                // ***************************
                // Let's do an info on one of the hosts to find its owner
                // and its status
                // ***************************
                System.out.println("Creating the Host Info command");
                epp_HostInfoReq host_info_request = new epp_HostInfoReq();

                current_time = new Date();
                client_trid = "ABC:"+epp_client_id+":"+current_time.getTime();
                command_data.setClientTrid( client_trid );
                host_info_request.setCmd( command_data );

                host_info_request.setName( "ns2."+domain_name );

                EPPHostInfo host_info = new EPPHostInfo();
                host_info.setRequestData(host_info_request);

                host_info = (EPPHostInfo) epp_client.processAction(host_info);

                epp_HostInfoRsp host_info_response = host_info.getResponseData();

                System.out.println("HostInfo results: clID ["+host_info_response.getClientId()+"] crID ["+host_info_response.getCreatedBy()+"]");
                System.out.println("HostInfo results: crDate ["+host_info_response.getCreatedDate()+"] upDate ["+host_info_response.getUpdatedDate()+"]");
                System.out.println("HostInfo results: number of ipaddresses ["+( host_info_response.getAddresses() == null ? 0 : host_info_response.getAddresses().length )+"]");
                for ( int i = 0; i < host_info_response.getAddresses().length; i++ )
                {
                    System.out.println("\taddress["+i+"] type ["+EPPHostBase.hostAddressTypeToString(host_info_response.getAddresses()[i].getType())+"] value ["+host_info_response.getAddresses()[i].getIp()+"]");
                }

                System.out.println("HostInfo Results: status count ["+host_info_response.getStatus().length+"]");
                for ( int i = 0; i < host_info_response.getStatus().length; i++ )
                {
                    System.out.println("\tstatus["+i+"] string ["+EPPHostBase.hostStatusToString( host_info_response.getStatus()[i].getType() )+"]");
                    System.out.println("\tstatus["+i+"] note ["+host_info_response.getStatus()[i].getValue()+"]");
                }


                epp_DomainUpdateAddRemove add = null;
                epp_DomainUpdateAddRemove remove = null;

                // OK, the domain exists, the hosts exists.  Are
                // they already assigned to the domain as nameservers?

                if ( domain_nameservers == null )
                {
                    // No nameservers serving the domain
                    // so let's add the two we created (or which already existed)
                    List add_list = (List) new ArrayList();
                    System.out.println("adding both ns1 and ns2 to domain");
                    add_list.add("ns1."+domain_name);
                    add_list.add("ns2."+domain_name);
                    add = new epp_DomainUpdateAddRemove();
                    add.setNameServers( EPPXMLBase.convertListToStringArray(add_list) );
                }
                else
                {
                    // Already nameservers for this domain,
                    // so let's see if the two we want are there and
                    // add or remove accordingly.
                    int nameserver_count = domain_nameservers.length;
                    List remove_list = (List) new ArrayList();
                    List add_list = (List) new ArrayList();
                    boolean found_ns1 = false;
                    boolean found_ns2 = false;

                    for ( int index = 0; index < nameserver_count; index++ )
                    {
                        if ( domain_nameservers[index].equalsIgnoreCase("ns1."+domain_name) )
                        {
                            System.out.println("removing ns1 from domain");
                            remove_list.add("ns1."+domain_name);
                            found_ns1 = true;
                        }
                        else if ( domain_nameservers[index].equalsIgnoreCase("ns2."+domain_name) )
                        {
                            System.out.println("removing ns2 from domain");
                            remove_list.add("ns2."+domain_name);
                            found_ns2 = true;
                        }

                    }

                    if ( found_ns1 == false )
                    {
                        System.out.println("adding ns1 to domain");
                        add_list.add("ns1."+domain_name);
                    }
                    if ( found_ns2 == false )
                    {
                        System.out.println("adding ns2 to domain");
                        add_list.add("ns2."+domain_name);
                    }

                    if ( add_list.size() > 0 )
                    {
                        add = new epp_DomainUpdateAddRemove();
                        add.setNameServers( EPPXMLBase.convertListToStringArray(add_list) );
                    }
                    if ( remove_list.size() > 0 )
                    {
                        remove = new epp_DomainUpdateAddRemove();
                        remove.setNameServers( EPPXMLBase.convertListToStringArray(remove_list) );
                    }
                }

                // Let's modify the domain to have these hosts act
                // as its nameservers.

                try
                {
                    // ***************************
                    // Domain Update
                    //
                    // Adding two nameservers to this domain
                    //
                    // ***************************
                    System.out.println("Creating the Domain Update command");
                    epp_DomainUpdateReq domain_update_request = new epp_DomainUpdateReq();

                    current_time = new Date();
                    client_trid = "ABC:"+epp_client_id+":"+current_time.getTime();
                    command_data.setClientTrid( client_trid );
                    domain_update_request.setCmd( command_data );

                    domain_update_request.setName( domain_name );

                    // We determined a little earlier which operations to perform.
                    if ( add != null )
                    {
                        domain_update_request.setAdd( add );
                    }
                    if ( remove != null )
                    {
                        domain_update_request.setRemove( remove );
                    }

                    EPPDomainUpdate domain_update = new EPPDomainUpdate();
                    domain_update.setRequestData(domain_update_request);

                    domain_update = (EPPDomainUpdate) epp_client.processAction(domain_update);
                }
                catch ( epp_Exception xcp )
                {
                    // If the domain is in a pendingTransfer state,
                    // some registries will throw an error when we
                    // try to update it.  We'll catch it, report it
                    // and move on.
                    System.err.println("epp_Exception!");
                    epp_Result[] results = xcp.getDetails();
                    System.err.println("\t"+results[0]);
                }


                // As long as no exception was thrown, the update was a success


                // ***************************
                // Let's do an info on one of the contacts to find its owner
                // and its status
                // ***************************
                System.out.println("Creating the Contact Info command");
                epp_ContactInfoReq contact_info_request = new epp_ContactInfoReq();

                current_time = new Date();
                client_trid = "ABC:"+epp_client_id+":"+current_time.getTime();
                command_data.setClientTrid( client_trid );
                contact_info_request.setCmd( command_data );

                contact_info_request.setId( domain_info_response.m_registrant );

                EPPContactInfo contact_info = new EPPContactInfo();
                contact_info.setRequestData(contact_info_request);

                contact_info = (EPPContactInfo) epp_client.processAction(contact_info);

                epp_ContactInfoRsp contact_info_response = contact_info.getResponseData();

                System.out.println("ContactInfo results: clID ["+contact_info_response.getClientId()+"] crID ["+contact_info_response.getCreatedBy()+"]");
                System.out.println("ContactInfo results: crDate ["+contact_info_response.getCreatedDate()+"] upDate ["+contact_info_response.getUpdatedDate()+"]");
                System.out.println("ContactInfo results: address street 1 ["+contact_info_response.getAsciiAddress().getAddress().getStreet1()+"]");
                System.out.println("ContactInfo results: address street 2 ["+contact_info_response.getAsciiAddress().getAddress().getStreet2()+"]");
                System.out.println("ContactInfo results: address street 3 ["+contact_info_response.getAsciiAddress().getAddress().getStreet3()+"]");
                System.out.println("ContactInfo results: fax ["+contact_info_response.getFax()+"]");

                System.out.println("ContactInfo Results: status count ["+contact_info_response.getStatus().length+"]");
                for ( int i = 0; i < contact_info_response.getStatus().length; i++ )
                {
                    System.out.println("\tstatus["+i+"] string ["+EPPContactBase.contactStatusToString( contact_info_response.getStatus()[i].getType() )+"]");
                    System.out.println("\tstatus["+i+"] note ["+contact_info_response.getStatus()[i].getValue()+"]");
                }


                // ***************************
                // Domain Transfer (Query)
                //
                // Now, let's pretent that some time has passed...
                // Let's check up on our domain to see
                // if anyone happens to be requesting a transfer on it.
                //
                // ***************************
                try
                {
                    System.out.println("Creating the Domain Transfer command");
                    epp_DomainTransferReq domain_transfer_request = new epp_DomainTransferReq();

                    current_time = new Date();
                    client_trid = "ABC:"+epp_client_id+":"+current_time.getTime();
                    command_data.setClientTrid( client_trid );
                    domain_transfer_request.setCmd( command_data );

                    transfer_request = new epp_TransferRequest();
                    transfer_request.setOp( epp_TransferOpType.QUERY );
                    // Use the auth info from the creation of the domain
                    transfer_request.setAuthInfo( domain_auth_info );
                    domain_transfer_request.setTrans( transfer_request );

                    domain_transfer_request.setName( domain_name );

                    EPPDomainTransfer domain_transfer = new EPPDomainTransfer();
                    domain_transfer.setRequestData(domain_transfer_request);

                    domain_transfer = (EPPDomainTransfer) epp_client.processAction(domain_transfer);

                    epp_DomainTransferRsp domain_transfer_response = domain_transfer.getResponseData();
                    System.out.println("DomainTransfer Results: transfer status ["+EPPXMLBase.transferStatusToString( domain_transfer_response.getTrnData().getTransferStatus() )+"]");

                    if ( domain_transfer_response.getTrnData().getTransferStatus() == epp_TransferStatusType.PENDING )
                    {
                        // hmmm... there's a transfer pending on this domain,

                        System.out.println("Creating the Domain Transfer command");
                        domain_transfer_request = new epp_DomainTransferReq();

                        current_time = new Date();
                        client_trid = "ABC:"+epp_client_id+":"+current_time.getTime();
                        command_data.setClientTrid( client_trid );
                        domain_transfer_request.setCmd( command_data );

                        transfer_request = new epp_TransferRequest();

                        // Let's find out from the registrant/registrar if they want
                        // the transfer approved.
                        BufferedReader buffed_reader = new BufferedReader(new InputStreamReader(System.in));
                        System.out.print("Do you wish to approve the domain's transfer [y]? ");
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
                            System.out.println("Going to approve the transfer");
                            transfer_request.setOp( epp_TransferOpType.APPROVE );
                        }
                        else
                        {
                            System.out.println("Going to reject the transfer");
                            transfer_request.setOp( epp_TransferOpType.REJECT );
                        }


                        // Use the auth info from the creation of the domain
                        transfer_request.setAuthInfo( domain_auth_info );
                        domain_transfer_request.setTrans( transfer_request );

                        domain_transfer_request.setName( domain_name );

                        domain_transfer = new EPPDomainTransfer();
                        domain_transfer.setRequestData(domain_transfer_request);

                        domain_transfer = (EPPDomainTransfer) epp_client.processAction(domain_transfer);

                        domain_transfer_response = domain_transfer.getResponseData();
                        System.out.println("DomainTransfer Results: transfer status ["+EPPXMLBase.transferStatusToString( domain_transfer_response.getTrnData().getTransferStatus() )+"]");

                        if ( transfer_request.getOp() == epp_TransferOpType.APPROVE )
                        {
                            // We've approved the domain's transfer, so
                            // since we don't own it anymore, we can't
                            // continue working on it.
                            System.out.println("Logging out from the EPP Server");
                            epp_client.logout(client_trid);
                            System.out.println("Disconnecting from the EPP Server");
                            epp_client.disconnect();
                            System.exit(1);
                        }

                    }
                }
                catch ( epp_Exception xcp )
                {
                    // If an exception was thrown to this command, then maybe
                    // the auth info we used was wrong, or maybe someone
                    // transfered the domain away from us, or maybe
                    // there is not transfer information to report on.
                    epp_Result[] results = xcp.getDetails();
                    if ( results[0].getCode() == epp_Session.EPP_OBJECT_NOT_PENDING_TRANSFER )
                    {
                        System.out.println("The domain is not currently in pending transfer state");
                    }
                    else if ( results[0].getCode() == epp_Session.EPP_UNIMPLEMENTED_OPTION )
                    {
                        System.out.println("This EPP command option has not been implemented in the registry yet.  That's OK, let's continue...");
                    }
                    else
                    {
                        // Something else unexpected happended, so throw the exception up
                        throw xcp;
                    }
                }


                // Let's ask to see if the user wants to renew the domain
                BufferedReader buffed_reader = new BufferedReader(new InputStreamReader(System.in));
                System.out.print("Do you wish to renew your domain [y]? ");
                String answer = buffed_reader.readLine();

                while ( ( answer != null ) &&
                        ( answer.length() != 0 ) &&
                        ( ! answer.equalsIgnoreCase("n") ) &&
                        ( ! answer.equalsIgnoreCase("y") ) )
                {
                    answer = buffed_reader.readLine();
                }

                if ( ! answer.equalsIgnoreCase("n") )
                {

                    // ***************************
                    // Domain Renew
                    //
                    // Now, assuming no exception was thrown from the transfer
                    // query request, we can probably try to renew the
                    // domain.
                    //
                    // ***************************
                    System.out.println("Creating the Domain Renew command");
                    epp_DomainRenewReq domain_renew_request = new epp_DomainRenewReq();

                    current_time = new Date();
                    client_trid = "ABC:"+epp_client_id+":"+current_time.getTime();
                    command_data.setClientTrid( client_trid );
                    domain_renew_request.setCmd( command_data );

                    domain_renew_request.setName( domain_name );
                    // How about for another 6 years?
                    // Note that some openrtk might not accept renewal
                    // periods by months.
                    epp_DomainPeriod period = new epp_DomainPeriod();
                    period.setUnit( epp_DomainPeriodUnitType.YEAR );
                    period.setValue( (short) 6 );
                    domain_renew_request.setPeriod( period );
                    // The domain's current expiration must also be specified
                    // to unintentional multiple renew request from succeeding.
                    // The format of the expiration date must be "YYYY-MM-DD"
                    domain_renew_request.setCurrentExpirationDate( RTKBase.DATE_FMT.format(domain_exp_date) );

                    EPPDomainRenew domain_renew = new EPPDomainRenew();
                    domain_renew.setRequestData(domain_renew_request);

                    domain_renew = (EPPDomainRenew) epp_client.processAction(domain_renew);

                    epp_DomainRenewRsp domain_renew_response = domain_renew.getResponseData();
                    // The domain renew action returns the domain's new expiration
                    // date if the request was successful
                    System.out.println("DomainRenew results: new exDate ["+domain_renew_response.getExpirationDate()+"]");
                    domain_exp_date = RTKBase.UTC_FMT.parse(domain_renew_response.getExpirationDate());

                }

                // Let's ask to see if the user wants to delete the domain.
                // You would not want to delete the domain if you want to
                // see domain transfer in action.
                buffed_reader = new BufferedReader(new InputStreamReader(System.in));
                System.out.print("Do you wish to delete your domain [y]? ");
                answer = buffed_reader.readLine();

                while ( ( answer != null ) &&
                        ( answer.length() != 0 ) &&
                        ( ! answer.equalsIgnoreCase("n") ) &&
                        ( ! answer.equalsIgnoreCase("y") ) )
                {
                    answer = buffed_reader.readLine();
                }

                if ( ! answer.equalsIgnoreCase("n") )
                {

                    // ***************************
                    // Domain Delete
                    //
                    // Finally, let's end the session by deleting the domain
                    //
                    // Recent tests with the .biz registry show that this
                    // command will fail because the domain has nameservers
                    // that are associated with a domain.  Oddly enough, they
                    // are associated with their own domain.
                    //
                    // ***************************
                    System.out.println("Creating the Domain Delete command");
                    epp_DomainDeleteReq domain_delete_request = new epp_DomainDeleteReq();

                    current_time = new Date();
                    client_trid = "ABC:"+epp_client_id+":"+current_time.getTime();
                    command_data.setClientTrid( client_trid );
                    domain_delete_request.setCmd( command_data );

                    domain_delete_request.setName( domain_name );

                    EPPDomainDelete domain_delete = new EPPDomainDelete();
                    domain_delete.setRequestData(domain_delete_request);

                    domain_delete = (EPPDomainDelete) epp_client.processAction(domain_delete);

                }


            }
            catch ( epp_XMLException xcp )
            {
                // Either the request was missing some required data in
                // validation before sending to the server, or the server's
                // response was either unparsable or missing some required data.
                System.err.println("epp_XMLException! ["+xcp.getErrorMessage()+"]");
            }
            catch ( epp_Exception xcp )
            {
                // The EPP Server has responded with an error code with
                // some optional messages to describe the error.
                System.err.println("epp_Exception!");
                epp_Result[] results = xcp.getDetails();
                System.err.println("\t"+results[0]);
            }
            catch ( Exception xcp )
            {
                // Other unexpected exceptions
                System.err.println("EPP Action failed! ["+xcp.getClass().getName()+"] ["+xcp.getMessage()+"]");
                xcp.printStackTrace();
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
