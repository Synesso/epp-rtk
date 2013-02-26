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
 * $Header: /cvsroot/epp-rtk/epp-rtk/java/src/com/tucows/oxrs/epp0604/rtk/example/ContactExample.java,v 1.1 2003/03/21 15:52:27 tubadanm Exp $
 * $Revision: 1.1 $
 * $Date: 2003/03/21 15:52:27 $
 */

package com.tucows.oxrs.epp0604.rtk.example;

import java.util.*;

import com.tucows.oxrs.epp0604.rtk.*;
import com.tucows.oxrs.epp0604.rtk.xml.*;

import org.openrtk.idl.epp0604.*;
import org.openrtk.idl.epp0604.domain.*;
import org.openrtk.idl.epp0604.contact.*;


/**
 * Example code for the EPP Contact object.  Also shows creation of
 * EPPClient instance, login, logout and disconnect.
 *
 * @author Daniel Manley
 * @version $Revision: 1.1 $ $Date: 2003/03/21 15:52:27 $
 * @see com.tucows.oxrs.epp0604.rtk.EPPClient
 * @see com.tucows.oxrs.epp0604.rtk.xml.EPPGreeting
 * @see com.tucows.oxrs.epp0604.rtk.xml.EPPContactCheck
 * @see com.tucows.oxrs.epp0604.rtk.xml.EPPContactInfo
 * @see com.tucows.oxrs.epp0604.rtk.xml.EPPContactCreate
 * @see com.tucows.oxrs.epp0604.rtk.xml.EPPContactUpdate
 * @see com.tucows.oxrs.epp0604.rtk.xml.EPPContactTransfer
 * @see com.tucows.oxrs.epp0604.rtk.xml.EPPContactDelete
**/
public class ContactExample
{

    private static String USAGE = "Usage: com.tucows.oxrs.epp0604.rtk.example.ContactExample epp_contact_name epp_contact_port epp_client_id epp_password";

    /**
     * Main of the example.  Performs Contact check, info, create, update, transfer and delete.
    **/
    public static void main(String args[])
    {

        System.out.println("Start of the Contact example");

        epp_AuthInfo contact_auth_info = null;
        String default_contact_id;
        epp_Command command_data = null;
        
        try
        {
            if (args.length < 4)
            {
                System.err.println(USAGE);
                System.exit(1);
            }

            // calling setDebugLevel() with no params makes the RTK
            // read the "rtk.debuglevel" property from rtk.properties file
            RTKBase.setDebugLevel();

            String epp_contact_name = args[0];
            String epp_contact_port_string = args[1];
            String epp_client_id = args[2];
            String epp_password  = args[3];

            int epp_contact_port = Integer.parseInt(epp_contact_port_string);

            // This example uses the client trid to generate a unique contact id (as best we can)
            // so the create doesn't fail and throw the whole example out the window
            Random rng =  new Random();
            String randnum = "" + rng.nextInt() % 999999;
            while ( (epp_client_id + randnum).length() > 16 ) {
                // The contact ID length is a max of 16 characters
                // let's chop the front off of random number until it fits
                randnum = randnum.substring(0, randnum.length() - 1);
            }
            default_contact_id = epp_client_id + randnum;

            EPPClient epp_client = new EPPClient(epp_contact_name,
                                                 epp_contact_port,
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
                // Contact Check
                // ***************************
                System.out.println("Creating the Contact Check command");
                epp_ContactCheckReq contact_check_request = new epp_ContactCheckReq();
                
                command_data = new epp_Command();
                // The client trid is optional.  it's main use
                // is for registrar tracking and logging of requests,
                // especially for data creation or modification requests
                command_data.m_client_trid = getClientTrid(epp_client_id);
                contact_check_request.m_cmd = command_data;

                List contact_list = (List)new ArrayList();
                contact_list.add("C100-LRMS");
                contact_list.add("C102-LRMS");
                contact_list.add("C999-LRMS");
                contact_check_request.m_ids = EPPXMLBase.convertListToStringArray(contact_list);
                
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
                System.out.println("ContactCheck results: contact [C100-LRMS] available? ["+EPPXMLBase.getAvailResultFor(check_results, "C100-LRMS")+"]");
                System.out.println("ContactCheck results: contact [C102-LRMS] available? ["+EPPXMLBase.getAvailResultFor(check_results, "C102-LRMS")+"]");
                System.out.println("ContactCheck results: contact [C999-LRMS] available? ["+EPPXMLBase.getAvailResultFor(check_results, "C999-LRMS")+"]");
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
                command_data.m_client_trid = getClientTrid(epp_client_id);
                contact_create_request.m_cmd = command_data;

                contact_create_request.m_id = default_contact_id;
		
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
                
                // Contact Auth Info must be set, just give a fixed value now
                contact_auth_info = new epp_AuthInfo();
                contact_auth_info.m_value = "test_contact";
                // For the current spec of EPP, PW is the only allowed type
                // of auth info.  So, the type can be left null and the RTK will
                // fill in the value for you.
                contact_auth_info.m_type = epp_AuthInfoType.PW;
                contact_create_request.m_auth_info = contact_auth_info;

		EPPContactCreate contact_create = new EPPContactCreate();
                contact_create.setRequestData(contact_create_request);
                
                contact_create = (EPPContactCreate) epp_client.processAction(contact_create);
                // or, alternatively, this method can be used...
                //contact_create.fromXML(epp_client.processXML(contact_create.toXML()));

                epp_ContactCreateRsp contact_create_response = contact_create.getResponseData();
                epp_Response response = contact_create_response.m_rsp;

                epp_Result[] results = response.m_results;
                System.out.println("ContactCreate results: ["+results[0].m_code+"] ["+results[0].m_msg+"]");
                System.out.println("ContactCreate results: contact id ["+contact_create_response.m_id+"]");
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
                command_data.m_client_trid = getClientTrid(epp_client_id);
                contact_info_request.m_cmd = command_data;

                // Used the ROID saved from the contact create.
                contact_info_request.m_id = default_contact_id;
                
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
		System.out.println("                Fax: ["+contact_info_response.m_fax+"]");
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
                // We're taking advantage epp_Result's toString() here
                // for debugging.  Take a look at the javadocs for
                // the full list of attributes in the class.
                System.err.println("\tresults: "+results[0]+"]");
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
                command_data.m_client_trid = getClientTrid(epp_client_id);
                contact_update_request.m_cmd = command_data;

                contact_update_request.m_id = default_contact_id;

		// You can ADD or REMOVE contact status here, like
		// epp_ContactUpdateAddRemove add = new epp_ContactUpdateAddRemove();
                // add.m_status = new epp_ContactStatus[1];
                // add.m_status[0] = new epp_ContactStatus();
                // add.m_status[0].m_type = epp_ContactStatusType.CLIENT_DELETE_PROHIBITED;
		// contact_update_request.m_add = add;
		
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
                name_address.m_address.m_city = "Townsville";
                name_address.m_address.m_postal_code = "A9A9A9";
                name_address.m_address.m_country_code = "CA";

                // The contact update operation allows changes to both
                // the ASCII and "i15d" name/address information.
                contact_update_change.m_ascii_address = name_address;
                // The voice and email values are not changing, but we want
                // to remove the fax value.  Can't just use null here
                // because then it would not be present in the request XML,
                // so the server would affect no change to the fax.
                contact_update_change.m_fax = new epp_ContactPhone("", "");

		// You can also change the contact auth info here
		epp_AuthInfo contact_auth_info_new = new epp_AuthInfo();
		contact_auth_info_new.m_value = "test_contact_new";
                contact_update_change.m_auth_info = contact_auth_info_new;
		contact_auth_info = contact_auth_info_new;

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
                // We're taking advantage epp_Result's toString() here
                // for debugging.  Take a look at the javadocs for
                // the full list of attributes in the class.
                System.err.println("\tresults: "+results[0]+"]");
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
                // Contact Transfer (Query)
                // ***************************
                System.out.println("Creating the Contact Transfer command");
                epp_ContactTransferReq contact_transfer_request = new epp_ContactTransferReq();
                
                command_data = new epp_Command();
                // The client trid is optional.  it's main use
                // is for registrar tracking and logging of requests,
                // especially for data creation or modification requests
                command_data.m_client_trid = getClientTrid(epp_client_id);
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
                // contact_auth_info is REQUIRED only when transfer op type
		// is REQUEST, so the line below is unnecessary, it will 
		// be ignored by the server
                transfer_request.m_auth_info = contact_auth_info;
                contact_transfer_request.m_trans = transfer_request;

                // Set the contact id.
                contact_transfer_request.m_id = default_contact_id;

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
                command_data.m_client_trid = getClientTrid(epp_client_id);
                contact_delete_request.m_cmd = command_data;

                // Used the ROID from the contact create.
                contact_delete_request.m_id = default_contact_id;
                
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
                // We're taking advantage epp_Result's toString() here
                // for debugging.  Take a look at the javadocs for
                // the full list of attributes in the class.
                System.err.println("\tresults: "+results[0]+"]");
            }
            catch ( Exception xcp )
            {
                // Other unexpected exceptions
                System.err.println("Contact Delete failed! ["+xcp.getClass().getName()+"] ["+xcp.getMessage()+"]");
                xcp.printStackTrace();
            }


            System.out.println("Logging out from the EPP Server");
            epp_client.logout(getClientTrid(epp_client_id));
            
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
            System.err.println("\tresults: "+results[0]+"]");
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
