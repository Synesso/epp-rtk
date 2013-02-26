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
 * $Header: /cvsroot/epp-rtk/epp-rtk/java/src/com/tucows/oxrs/epp02/rtk/example/ContactExample.java,v 1.1 2003/03/21 17:13:40 tubadanm Exp $
 * $Revision: 1.1 $
 * $Date: 2003/03/21 17:13:40 $
 */

package com.tucows.oxrs.epp02.rtk.example;

import java.util.*;

import com.tucows.oxrs.epp02.rtk.*;
import com.tucows.oxrs.epp02.rtk.xml.*;

import org.openrtk.idl.epp02.*;
import org.openrtk.idl.epp02.domain.*;
import org.openrtk.idl.epp02.contact.*;


/**
 * Example code for the EPP Contact object.  Also shows creation of
 * EPPClient instance, login, logout and disconnect.
 *
 * @author Daniel Manley
 * @version $Revision: 1.1 $ $Date: 2003/03/21 17:13:40 $
 * @see com.tucows.oxrs.epp02.rtk.EPPClient
 * @see com.tucows.oxrs.epp02.rtk.xml.EPPGreeting
 * @see com.tucows.oxrs.epp02.rtk.xml.EPPContactCheck
 * @see com.tucows.oxrs.epp02.rtk.xml.EPPContactInfo
 * @see com.tucows.oxrs.epp02.rtk.xml.EPPContactCreate
 * @see com.tucows.oxrs.epp02.rtk.xml.EPPContactUpdate
 * @see com.tucows.oxrs.epp02.rtk.xml.EPPContactDelete
**/
public class ContactExample
{

    private static String USAGE = "Usage: com.tucows.oxrs.epp02.rtk.example.ContactExample epp_contact_name epp_contact_port epp_client_id epp_password";

    /**
     * Main of the example.  Performs Contact check, info, create, update and delete.
    **/
    public static void main(String args[])
    {

        System.out.println("Start of the Contact example");

        epp_AuthInfo contact_auth_info = null;
        String saved_contact_roid = "";
        epp_Command command_data = null;
        
        try
        {
            if (args.length != 4)
            {
                System.err.println(USAGE);
                System.exit(1);
            }

            RTKBase.setDebugLevel(RTKBase.DEBUG_LEVEL_ONE);

            String epp_contact_name = args[0];
            String epp_contact_port_string = args[1];
            String epp_client_id = args[2];
            String epp_password  = args[3];

            int epp_contact_port = Integer.parseInt(epp_contact_port_string);

            EPPClient epp_client = new EPPClient(epp_contact_name,
                                                 epp_contact_port,
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
                // Contact Check
                // ***************************
                System.out.println("Creating the Contact Check command");
                epp_ContactCheckReq contact_check_request = new epp_ContactCheckReq();
                
                command_data = new epp_Command();
                // The client trid is optional.  it's main use
                // is for registrar tracking and logging of requests,
                // especially for data creation or modification requests
                command_data.m_client_trid = client_trid;
                contact_check_request.m_cmd = command_data;

                List contact_list = (List)new ArrayList();
                contact_list.add("C100-LRMS");
                contact_list.add("C102-LRMS");
                contact_list.add("C999-LRMS");
                contact_check_request.m_roids = EPPXMLBase.convertListToStringArray(contact_list);
                
                EPPContactCheck contact_check = new EPPContactCheck();
                contact_check.setRequestData(contact_check_request);
                
                contact_check = (EPPContactCheck) epp_client.processAction(contact_check);
                // or, alternatively, this method can be used...
                //contact_check.fromXML(epp_client.processXML(contact_check.toXML()));

                epp_ContactCheckRsp contact_check_response = contact_check.getResponseData();
                epp_Response response = contact_check_response.m_rsp;
                epp_Result[] results = response.m_results;
                System.out.println("ContactCheck results: ["+results[0].m_code+"] ["+results[0].m_msg+"]");
                epp_CheckResult[] check_results = contact_check_response.m_results;
                System.out.println("ContactCheck results: contact [C100-LRMS] exists? ["+EPPXMLBase.getCheckResultFor(check_results, "C100-LRMS")+"]");
                System.out.println("ContactCheck results: contact [C102-LRMS] exists? ["+EPPXMLBase.getCheckResultFor(check_results, "C102-LRMS")+"]");
                System.out.println("ContactCheck results: contact [C999-LRMS] exists? ["+EPPXMLBase.getCheckResultFor(check_results, "C999-LRMS")+"]");
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
                System.err.println("Contact Check failed! ["+xcp.getClass().getName()+"] ["+xcp.getMessage()+"]");
                xcp.printStackTrace();
            }


            try
            {
                // ***************************
                // Contact Create
                // ***************************
                System.out.println("Creating the Contact Create command");
                epp_ContactCreateReq contact_create_request = new epp_ContactCreateReq();
                
                command_data = new epp_Command();
                // The client trid is optional.  it's main use
                // is for registrar tracking and logging of requests,
                // especially for data creation or modification requests
                command_data.m_client_trid = client_trid;
                contact_create_request.m_cmd = command_data;

		contact_auth_info = new epp_AuthInfo();
		contact_auth_info.m_value = "test password";
		contact_auth_info.m_type = epp_AuthInfoType.PW;

		contact_create_request.m_auth_info = contact_auth_info;

                epp_ContactNameAddress name_address = new epp_ContactNameAddress();
                name_address.m_name = "John Doe";
                name_address.m_org = "ACME Solutions";
                name_address.m_address = new epp_ContactAddress();
                // Up to three street values may be specified.
                name_address.m_address.m_street1 = "100 Centre St";
                name_address.m_address.m_city = "Townsville";
                name_address.m_address.m_state_province = "County Derry";
                name_address.m_address.m_postal_code = "Z1Z1Z1";
                // The country code must be a valid ISO country code.
                name_address.m_address.m_country_code = "CA";

                // Now assign that name/address set to the contact's
                // ASCII address.  If you wish to also use UTF-8
                // characters in the name/address data, set the 
                // m_i15d_address data member.
                contact_create_request.m_ascii_address = name_address;
                // The voice, fax and the email values can only be in ASCII.
                // The voice value may contain an optional extension.
                
                // Ceate a voice value with an extenstion.
                contact_create_request.m_voice = new epp_ContactPhone("1234", "+1.4165559999");
                contact_create_request.m_fax = new epp_ContactPhone("9876", "+1.4165558888");
                contact_create_request.m_email = "jdoe@company.info";
                                                
                EPPContactCreate contact_create = new EPPContactCreate();
                contact_create.setRequestData(contact_create_request);
                
                contact_create = (EPPContactCreate) epp_client.processAction(contact_create);
                // or, alternatively, this method can be used...
                //contact_create.fromXML(epp_client.processXML(contact_create.toXML()));

                epp_ContactCreateRsp contact_create_response = contact_create.getResponseData();
                epp_Response response = contact_create_response.m_rsp;

                // save the ROID for the further operations on this contact
                saved_contact_roid = contact_create_response.m_roid;
                
                epp_Result[] results = response.m_results;
                System.out.println("ContactCreate results: ["+results[0].m_code+"] ["+results[0].m_msg+"]");
                System.out.println("ContactCreate results: contact roid ["+contact_create_response.m_roid+"]");
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
                System.err.println("Contact Create failed! ["+xcp.getClass().getName()+"] ["+xcp.getMessage()+"]");
                xcp.printStackTrace();
            }


            try
            {
                // ***************************
                // Contact Info
                // ***************************
                System.out.println("Creating the Contact Info command");
                epp_ContactInfoReq contact_info_request = new epp_ContactInfoReq();
                
                command_data = new epp_Command();
                // The client trid is optional.  it's main use
                // is for registrar tracking and logging of requests,
                // especially for data creation or modification requests
                command_data.m_client_trid = client_trid;
                contact_info_request.m_cmd = command_data;

                // Used the ROID saved from the contact create.
                contact_info_request.m_roid = saved_contact_roid;
                
                EPPContactInfo contact_info = new EPPContactInfo();
                contact_info.setRequestData(contact_info_request);
                
                contact_info = (EPPContactInfo) epp_client.processAction(contact_info);
                // or, alternatively, this method can be used...
                //contact_info.fromXML(epp_client.processXML(contact_info.toXML()));

                epp_ContactInfoRsp contact_info_response = contact_info.getResponseData();
                epp_Response response = contact_info_response.m_rsp;
                epp_Result[] results = response.m_results;

                // You can also save the auID (auth ID) from an info where
                // the calling registrar is the sponsoring client for the
                // object.
                contact_auth_info = contact_info_response.m_auth_info;
                
                System.out.println("ContactInfo results: ["+results[0].m_code+"] ["+results[0].m_msg+"]");
		System.out.println("              Voice: ["+contact_info_response.m_voice+"]");
		System.out.println("              Fax:   ["+contact_info_response.m_fax+"]");
		System.out.println("          Auth Info: ["+contact_auth_info.m_value+"]");

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
                System.err.println("Contact Info failed! ["+xcp.getClass().getName()+"] ["+xcp.getMessage()+"]");
                xcp.printStackTrace();
            }


            try
            {
                // ***************************
                // Contact Update
                // ***************************
                System.out.println("Creating the Contact Update command");
                epp_ContactUpdateReq contact_update_request = new epp_ContactUpdateReq();
                
                command_data = new epp_Command();
                // The client trid is optional.  it's main use
                // is for registrar tracking and logging of requests,
                // especially for data creation or modification requests
                command_data.m_client_trid = client_trid;
                contact_update_request.m_cmd = command_data;

		contact_update_request.m_roid = saved_contact_roid;

                epp_ContactUpdateChange contact_update_change = new epp_ContactUpdateChange();
                
                epp_ContactNameAddress name_address = new epp_ContactNameAddress();
                // In a contact change request, you only need to explicitly
                // specify the values that are changing, leaving the remain
                // values set to null (although, there is no harm in sending the
                // complete contact information).  If an optional value is
                // to be removed, then specify an empty string for the new value.
                name_address.m_name = "Jane Doe";
                name_address.m_org = "ACME Systems";
                name_address.m_address = new epp_ContactAddress();
                // Up to three street values may be specified.
                name_address.m_address.m_street1 = "999 Front St";
                name_address.m_address.m_postal_code = "A9A9A9";

                // The contact update operation allows changes to both
                // the ASCII and "i15d" name/address information.
                contact_update_change.m_ascii_address = name_address;
                // The voice and email values are not changing, but we want
                // to remove the fax value.  Can't just use null here
                // because then it would not be present in the request XML,
                // so the server would affect no change to the fax.
                contact_update_change.m_fax = new epp_ContactPhone("", "");

                contact_update_request.m_change = contact_update_change;
                
                EPPContactUpdate contact_update = new EPPContactUpdate();
                contact_update.setRequestData(contact_update_request);
                
                contact_update = (EPPContactUpdate) epp_client.processAction(contact_update);
                // or, alternatively, this method can be used...
                //contact_update.fromXML(epp_client.processXML(contact_update.toXML()));

                epp_ContactUpdateRsp contact_update_response = contact_update.getResponseData();
                epp_Response response = contact_update_response.m_rsp;
                epp_Result[] results = response.m_results;
                System.out.println("ContactUpdate results: ["+results[0].m_code+"] ["+results[0].m_msg+"]");
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
                System.err.println("Contact Update failed! ["+xcp.getClass().getName()+"] ["+xcp.getMessage()+"]");
                xcp.printStackTrace();
            }


            try
            {
                // ***************************
                // Contact Info
                // ***************************
                System.out.println("Creating the Contact Info command");
                epp_ContactInfoReq contact_info_request = new epp_ContactInfoReq();
                
                command_data = new epp_Command();
                // The client trid is optional.  it's main use
                // is for registrar tracking and logging of requests,
                // especially for data creation or modification requests
                command_data.m_client_trid = client_trid;
                contact_info_request.m_cmd = command_data;

                // Used the ROID saved from the contact create.
                contact_info_request.m_roid = saved_contact_roid;
                
                EPPContactInfo contact_info = new EPPContactInfo();
                contact_info.setRequestData(contact_info_request);
                
                contact_info = (EPPContactInfo) epp_client.processAction(contact_info);
                // or, alternatively, this method can be used...
                //contact_info.fromXML(epp_client.processXML(contact_info.toXML()));

                epp_ContactInfoRsp contact_info_response = contact_info.getResponseData();
                epp_Response response = contact_info_response.m_rsp;
                epp_Result[] results = response.m_results;

                // You can also save the auID (auth ID) from an info where
                // the calling registrar is the sponsoring client for the
                // object.
                contact_auth_info = contact_info_response.m_auth_info;
                
                System.out.println("ContactInfo results: ["+results[0].m_code+"] ["+results[0].m_msg+"]");
		System.out.println("              Voice: ["+contact_info_response.m_voice+"]");
		System.out.println("              Fax:   ["+contact_info_response.m_fax+"]");
		System.out.println("          Auth Info: ["+contact_auth_info.m_value+"]");

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
                System.err.println("Contact Info failed! ["+xcp.getClass().getName()+"] ["+xcp.getMessage()+"]");
                xcp.printStackTrace();
            }


            try
            {
                // ***************************
                // Contact Transfer (Query)
                // ***************************
                System.out.println("Creating the Contact Transfer command (expected to fail)");
                epp_ContactTransferReq contact_transfer_request = new epp_ContactTransferReq();
                
                command_data = new epp_Command();
                // The client trid is optional.  it's main use
                // is for registrar tracking and logging of requests,
                // especially for data creation or modification requests
                command_data.m_client_trid = client_trid;
                contact_transfer_request.m_cmd = command_data;

                // The Contact Transfer request is an EPP "transfer" request,
                // meaning it requires an "op" value and the object's
                // current auID (auth ID) for successful processing.
                epp_TransferRequest transfer_request = new epp_TransferRequest();
                // A transfer query will only query the status of the current
                // pending transfer or the last completed/cancelled/rejected
                // transfer.  To request a transfer, the "op" should be set to
                // REQUEST.
                //
                // QUERY is the default transfer op type, so it 
                // could have been left null here.
                transfer_request.m_op = epp_TransferOpType.QUERY;
                // 
                transfer_request.m_auth_info = contact_auth_info;
                contact_transfer_request.m_trans = transfer_request;

                // Used the ROID from the contact create.
                contact_transfer_request.m_roid = saved_contact_roid;

                EPPContactTransfer contact_transfer = new EPPContactTransfer();
                contact_transfer.setRequestData(contact_transfer_request);
                
                // Now ask the EPPClient to process the request and retrieve 
                // a response from the server.
                contact_transfer = (EPPContactTransfer) epp_client.processAction(contact_transfer);
                // or, alternatively, this method can be used...
                //contact_transfer.fromXML(epp_client.processXML(contact_transfer.toXML()));

                epp_ContactTransferRsp contact_transfer_response = contact_transfer.getResponseData();
                epp_Response response = contact_transfer_response.m_rsp;
                epp_Result[] results = response.m_results;
                System.out.println("ContactTransfer results: ["+results[0].m_code+"] ["+results[0].m_msg+"]");
                // If the transfer "op" was REQUEST, when the transfer is
                // approved, the trID returned by the successful REQUEST will
                // become the object's new auth ID.
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
                System.err.println("Contact Transfer (Query) failed! ["+xcp.getClass().getName()+"] ["+xcp.getMessage()+"]");
                xcp.printStackTrace();
            }


            try
            {
                // ***************************
                // Contact Delete
                // ***************************
                System.out.println("Creating the Contact Delete command");
                epp_ContactDeleteReq contact_delete_request = new epp_ContactDeleteReq();
                
                command_data = new epp_Command();
                // The client trid is optional.  it's main use
                // is for registrar tracking and logging of requests,
                // especially for data creation or modification requests
                command_data.m_client_trid = client_trid;
                contact_delete_request.m_cmd = command_data;

                // Used the ROID from the contact create.
                contact_delete_request.m_roid = saved_contact_roid;
                
                EPPContactDelete contact_delete = new EPPContactDelete();
                contact_delete.setRequestData(contact_delete_request);
                
                // Now ask the EPPClient to process the request and retrieve 
                // a response from the server.
                contact_delete = (EPPContactDelete) epp_client.processAction(contact_delete);
                // or, alternatively, this method can be used...
                //contact_delete.fromXML(epp_client.processXML(contact_delete.toXML()));

                epp_ContactDeleteRsp contact_delete_response = contact_delete.getResponseData();
                epp_Response response = contact_delete_response.m_rsp;
                epp_Result[] results = response.m_results;
                System.out.println("ContactDelete results: ["+results[0].m_code+"] ["+results[0].m_msg+"]");
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
                System.err.println("Contact Delete failed! ["+xcp.getClass().getName()+"] ["+xcp.getMessage()+"]");
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
