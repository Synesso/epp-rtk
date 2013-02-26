package com.tucows.oxrs.epprtk.rtk.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.openrtk.idl.epprtk.epp_AuthInfo;
import org.openrtk.idl.epprtk.epp_AuthInfoType;
import org.openrtk.idl.epprtk.epp_CheckResult;
import org.openrtk.idl.epprtk.epp_Command;
import org.openrtk.idl.epprtk.epp_Exception;
import org.openrtk.idl.epprtk.epp_TransferOpType;
import org.openrtk.idl.epprtk.epp_TransferRequest;
import org.openrtk.idl.epprtk.epp_XMLException;
import org.openrtk.idl.epprtk.contact.epp_ContactAddress;
import org.openrtk.idl.epprtk.contact.epp_ContactCheckReq;
import org.openrtk.idl.epprtk.contact.epp_ContactCreateReq;
import org.openrtk.idl.epprtk.contact.epp_ContactCreateRsp;
import org.openrtk.idl.epprtk.contact.epp_ContactDeleteReq;
import org.openrtk.idl.epprtk.contact.epp_ContactDeleteRsp;
import org.openrtk.idl.epprtk.contact.epp_ContactNameAddress;
import org.openrtk.idl.epprtk.contact.epp_ContactPhone;
import org.openrtk.idl.epprtk.contact.epp_ContactPostalInfoType;
import org.openrtk.idl.epprtk.contact.epp_ContactTransferReq;
import org.openrtk.idl.epprtk.contact.epp_ContactTransferRsp;

import com.tucows.oxrs.epprtk.rtk.EPPClient;
import com.tucows.oxrs.epprtk.rtk.xml.EPPContactCheck;
import com.tucows.oxrs.epprtk.rtk.xml.EPPContactCreate;
import com.tucows.oxrs.epprtk.rtk.xml.EPPContactDelete;
import com.tucows.oxrs.epprtk.rtk.xml.EPPContactTransfer;
import com.tucows.oxrs.epprtk.rtk.xml.EPPXMLBase;

public class ContactUtils {

    private ContactUtils() { }
    
    public static Map checkContacts(EPPClient client, List contacts) throws epp_XMLException, epp_Exception, Exception 
    {
        Map contactsExist = new HashMap();
        
        epp_ContactCheckReq contactCheckReq = new epp_ContactCheckReq();

        epp_Command command = new epp_Command();

        command.setClientTrid(getClientTrid(client.getEPPClientID()));
        contactCheckReq.setCmd(command);

        // List the contacts we want to check...
        contactCheckReq.setIds(EPPXMLBase.convertListToStringArray(contacts));

        EPPContactCheck contactCheck = new EPPContactCheck();
        contactCheck.setRequestData(contactCheckReq);

        // Send request to EPP server...
        contactCheck = (EPPContactCheck) client.processAction(contactCheck);

        // Process response from EPP server... 
        epp_CheckResult[] checkResults = contactCheck.getResponseData().getResults();

        for (Iterator it = contacts.iterator(); it.hasNext();) {
            String contactId = (String) it.next();
            Boolean contactExists = EPPXMLBase.getAvailResultFor(checkResults, contactId);
            
            contactsExist.put(contactId, contactExists);
        }
        
        return contactsExist;
    }

    public static Boolean checkContact(EPPClient client, String contactId) throws epp_XMLException, epp_Exception, Exception
    {
        List contacts = new ArrayList();
        contacts.add(contactId);
        
        Map contactsExist = checkContacts(client, contacts);
        
        return (Boolean) contactsExist.get(contactId);
    }

    public static epp_ContactCreateRsp createContact(
            EPPClient client, 
            String contactId, 
            String passphrase) throws epp_XMLException, epp_Exception, Exception 
    {
        epp_ContactCreateReq contactCreateReq = new epp_ContactCreateReq();
        
        epp_Command command_data = new epp_Command();
        command_data.setClientTrid(getClientTrid(client.getEPPClientID()));
        contactCreateReq.setCmd(command_data);
        contactCreateReq.setId(contactId);

        epp_ContactNameAddress contactNameAddress = new epp_ContactNameAddress();
        contactNameAddress = new epp_ContactNameAddress();
        contactNameAddress.setType(epp_ContactPostalInfoType.INT);
        contactNameAddress.setName("John Doe");
        contactNameAddress.setOrg("ACME Solutions");
        contactNameAddress.setAddress(new epp_ContactAddress());
        contactNameAddress.getAddress().setStreet1("100 Centre St");
        contactNameAddress.getAddress().setCity("Townsville");
        contactNameAddress.getAddress().setStateProvince("County Derry");
        contactNameAddress.getAddress().setPostalCode("Z1Z1Z1");
        contactNameAddress.getAddress().setCountryCode("CA");
        
        epp_ContactNameAddress[] contactNameAddresses = new epp_ContactNameAddress[1];
        contactNameAddresses[0] = contactNameAddress;
        
        contactCreateReq.setAddresses(contactNameAddresses);
        
        contactCreateReq.setVoice(new epp_ContactPhone("1234", "+1.4165559999"));
        contactCreateReq.setFax(new epp_ContactPhone("9876", "+1.4165558888"));
        contactCreateReq.setEmail("jdoe@company.info");
        
        epp_AuthInfo authInfo = new epp_AuthInfo();
        authInfo.setType(epp_AuthInfoType.PW);
        authInfo.setValue(passphrase);
        contactCreateReq.setAuthInfo(authInfo);

        EPPContactCreate contactCreate = new EPPContactCreate();
        contactCreate.setRequestData(contactCreateReq);

        // Send request to EPP server...
        contactCreate = (EPPContactCreate) client.processAction(contactCreate);

        // Process response from EPP server...
        return contactCreate.getResponseData();
    }

    public static epp_ContactTransferRsp transferContact(
            EPPClient client, 
            String contactId, 
            epp_TransferOpType operation, 
            String passphrase) throws epp_XMLException, epp_Exception, Exception 
    {
        epp_ContactTransferReq contactTransferReq = new epp_ContactTransferReq();
        
        epp_Command command_data = new epp_Command();
        command_data.setClientTrid(getClientTrid(client.getEPPClientID()));
        contactTransferReq.setCmd(command_data);

        epp_TransferRequest transferRequest = new epp_TransferRequest();
        transferRequest.setOp(operation);
        
        epp_AuthInfo authInfo = new epp_AuthInfo();
        authInfo.setType(epp_AuthInfoType.PW);
        authInfo.setValue(passphrase);
        transferRequest.setAuthInfo(authInfo);
        
        contactTransferReq.setTrans(transferRequest);
        contactTransferReq.setId(contactId);

        EPPContactTransfer contact_transfer = new EPPContactTransfer();
        contact_transfer.setRequestData(contactTransferReq);

        contact_transfer = (EPPContactTransfer) client.processAction(contact_transfer);

        return contact_transfer.getResponseData();
    }

    public static epp_ContactDeleteRsp deleteContact(EPPClient client, String contactId) throws epp_XMLException, epp_Exception, Exception 
    {
        epp_ContactDeleteReq contact_delete_request = new epp_ContactDeleteReq();
        
        epp_Command command_data = new epp_Command();
        
        command_data.setClientTrid(getClientTrid(client.getEPPClientID()));
        
        contact_delete_request.setCmd(command_data);
        contact_delete_request.setId(contactId);
        
        EPPContactDelete contact_delete = new EPPContactDelete();
        contact_delete.setRequestData(contact_delete_request);
        
        // Send request to server...
        contact_delete = (EPPContactDelete) client.processAction(contact_delete);

        // Process response from server...
        return contact_delete.getResponseData();
    }
    
    public static String getClientTrid(String epp_client_id)
    {
        return "ABC:"+epp_client_id+":"+System.currentTimeMillis();
    }    
}
