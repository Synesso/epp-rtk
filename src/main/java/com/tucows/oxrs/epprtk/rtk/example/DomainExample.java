/*
**
** EPP RTK Java
** Copyright (C) 2001-2002, Tucows, Inc.
** Copyright (C) 2003, Liberty RMS
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
 * $Header: /cvsroot/epp-rtk/epp-rtk/java/src/com/tucows/oxrs/epprtk/rtk/example/DomainExample.java,v 1.2 2009/08/25 20:37:53 dongjinkim Exp $
 * $Revision: 1.2 $
 * $Date: 2009/08/25 20:37:53 $
 */

package com.tucows.oxrs.epprtk.rtk.example;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.openrtk.idl.epprtk.epp_AuthInfo;
import org.openrtk.idl.epprtk.epp_Exception;
import org.openrtk.idl.epprtk.epp_Greeting;
import org.openrtk.idl.epprtk.epp_PollOpType;
import org.openrtk.idl.epprtk.epp_PollRsp;
import org.openrtk.idl.epprtk.epp_Result;
import org.openrtk.idl.epprtk.epp_XMLException;
import org.openrtk.idl.epprtk.domain.epp_DomainCreateRsp;
import org.openrtk.idl.epprtk.domain.epp_DomainDeleteRsp;
import org.openrtk.idl.epprtk.domain.epp_DomainInfoRsp;
import org.openrtk.idl.epprtk.domain.epp_DomainRenewRsp;
import org.openrtk.idl.epprtk.domain.epp_DomainStatus;
import org.openrtk.idl.epprtk.domain.epp_DomainStatusType;
import org.openrtk.idl.epprtk.domain.epp_DomainUpdateAddRemove;
import org.openrtk.idl.epprtk.domain.epp_DomainUpdateChange;
import org.openrtk.idl.epprtk.domain.epp_DomainUpdateRsp;

import com.tucows.oxrs.epprtk.rtk.EPPClient;
import com.tucows.oxrs.epprtk.rtk.RTKBase;
import com.tucows.oxrs.epprtk.rtk.xml.EPPXMLBase;


/**
 * Example code for the EPP Domain object.  Also shows creation of
 * EPPClient instance, login, logout and disconnect.
 *
 * @author Daniel Manley
 * @version $Revision: 1.2 $ $Date: 2009/08/25 20:37:53 $
 * @see com.tucows.oxrs.epprtk.rtk.EPPClient
 * @see com.tucows.oxrs.epprtk.rtk.xml.EPPGreeting
 * @see com.tucows.oxrs.epprtk.rtk.xml.EPPDomainCheck
 * @see com.tucows.oxrs.epprtk.rtk.xml.EPPDomainInfo
 * @see com.tucows.oxrs.epprtk.rtk.xml.EPPDomainCreate
 * @see com.tucows.oxrs.epprtk.rtk.xml.EPPDomainUpdate
 * @see com.tucows.oxrs.epprtk.rtk.xml.EPPDomainRenew
 * @see com.tucows.oxrs.epprtk.rtk.xml.EPPDomainTransfer
 * @see com.tucows.oxrs.epprtk.rtk.xml.EPPDomainDelete
**/
public class DomainExample
{

    private static String USAGE = "Usage: com.tucows.oxrs.epprtk.rtk.example.DomainExample <epp host> <epp port> <client> <password> <domain name>";
    private static String AUTH_INFO = "123456";
    private static String NEW_AUTH_INFO = "ABCDEF";
    private static int RENEW_PERIOD = 2;
    
    /**
     * Tests Domain check, info, create, update, renew and delete.
    **/
    public static void main(String args[])
    {
        try
        {
            if (args.length < 5)
            {
                System.err.println(USAGE);
                System.exit(1);
            }

            String host       = args[0];
            int port          = Integer.parseInt(args[1]);
            String user       = args[2];
            String password   = args[3];
            String domainName = args[4];

            List oldNameServers = new ArrayList();
            oldNameServers.add("ns1.example.com");
            oldNameServers.add("ns2.example.com");
            
            List newNameServers = new ArrayList();
            newNameServers.add("ns3.example.com");
            newNameServers.add("ns4.example.com");

            List allNameServers = new ArrayList();
            allNameServers.addAll(oldNameServers);
            allNameServers.addAll(newNameServers);
            
            if (domainName.indexOf('.') == -1) {
                System.err.println("ERROR: Domain name must contain a TLD.");
                System.exit(1);
            }
            String contactId   = domainName.substring(0, domainName.indexOf('.')) + "-C";

            System.out.println("          HOST: " + host);
            System.out.println("          PORT: " + port);
            System.out.println("        CLIENT: " + user);
            System.out.println("        DOMAIN: " + domainName);
            System.out.println("       CONTACT: " + contactId);

            for (Iterator it = oldNameServers.iterator();it.hasNext();) {
                System.out.println("OLD NAMESERVER: " + it.next());
            }
            for (Iterator it = newNameServers.iterator();it.hasNext();) {
                System.out.println("NEW NAMESERVER: " + it.next());
            }
            System.out.println();
            
            EPPClient client = new EPPClient(host, port, user, password);            
            client.setLang("en");

            // The protocol used is set by the rtk.transport property
            // in etc/rtk.properties

            System.out.println("Connecting to EPP Server...");
            epp_Greeting greeting = client.connectAndGetGreeting();

            System.out.println("  EPP-Server ID:           " + greeting.getServerId());
            System.out.println("  EPP-Server current date: " + greeting.getServerDate());
            System.out.println("  EPP-Server services:     " + greeting.getSvcMenu());
            System.out.println();
	    
            String client_trid = getClientTrid(user);
            
            System.out.println("Logging in as \"" + user + "\"...");
            client.login(client_trid);
            System.out.println();

            // Create name servers if required...            
            for (Iterator it = allNameServers.iterator(); it.hasNext();) {
                String hostName = (String) it.next();
                                
                // Create the name server if required...
                System.out.println("Checking availability of host \"" + hostName + "\"...");
                
                if (HostUtils.checkHost(client, hostName).booleanValue()) {
                    System.out.println("  Host \"" + hostName + "\" does not exist. Creating...");
                    HostUtils.createExternalHost(client, hostName);
                }
                else {
                    System.out.println("  Host \"" + hostName + "\" already exists.");
                }
                System.out.println();
            }
            
            System.out.println("Polling \"" + user + "\"'s message queue...");
            epp_PollRsp pollRsp = QueueUtils.pollQueue(client, epp_PollOpType.REQ);
            System.out.println("  Result code:    " + pollRsp.getRsp().getResults()[0].getCode());
            System.out.println("  Result message: " + pollRsp.getRsp().getResults()[0].getMsg()); 
            System.out.println();
            
            // Check availability of domains to test DomainCheck...
            System.out.println("Checking availability of domains...");

            List domainNames = new ArrayList();
            domainNames.add(user + "1.info"); // should not exist (i.e. domain is available)
            domainNames.add(user + "2.info"); // should not exist (i.e. domain is available)

            Map domainsAvail = DomainUtils.checkDomains(client, domainNames);
            
            for (Iterator it = domainNames.iterator(); it.hasNext();) {
                String domain = (String) it.next();
                
                if (((Boolean)domainsAvail.get(domain)).booleanValue()) {
                    System.out.println("  Domain \"" + domain + "\" is available.");
                }
                else {
                    System.out.println("  Domain \"" + domain + "\" is not available.");
                }
            }
            System.out.println();
            
            System.out.println("Checking availability of domain \"" + domainName + "\"...");
            if (DomainUtils.checkDomain(client, domainName).booleanValue()) {
                System.out.println("  Domain \"" + domainName + "\" is available.");
            }
            else {
                throw new Exception("  Domain \"" + domainName + "\" already exists! Please choose a different name.");
            }
            System.out.println();

            // Need to create a contact to be used by domain create
            // because some registries have minimum requirements for
            // contacts associated with domains (i.e. usually, one of
            // each type, plus the registrant)

            // Create contacts if required...
            System.out.println("Checking availability of contact \"" + contactId + "\"...");

            if (ContactUtils.checkContact(client, contactId).booleanValue()) {
                System.out.println("  Contact \"" + contactId + "\" does not exist. Creating...");
                ContactUtils.createContact(client, contactId, AUTH_INFO);
            }
            System.out.println();
            
            System.out.println("Creating domain \"" + domainName + "\"...");

            epp_DomainCreateRsp domainCreateRsp = DomainUtils.createDomain(
                    client, 
                    domainName, 
                    2, 
                    oldNameServers, 
                    AUTH_INFO, 
                    contactId, 
                    contactId, 
                    contactId, 
                    contactId);
            System.out.println("  Result code:    " + domainCreateRsp.getRsp().getResults()[0].getCode());
            System.out.println("  Result message: " + domainCreateRsp.getRsp().getResults()[0].getMsg()); 
            System.out.println("  Creation date:  " + RTKBase.UTC_FMT.parse(domainCreateRsp.getCreationDate())); 
            System.out.println("  Expiry date:    " + domainCreateRsp.getExpirationDate());
            System.out.println();

            System.out.println("Viewing domain \"" + domainName + "\"...");
            
            epp_DomainInfoRsp domainInfoRsp = DomainUtils.infoDomain(client, domainName);
            Date domainExpiryDate = RTKBase.UTC_FMT.parse(domainInfoRsp.getExpirationDate());
            
            System.out.println("  Result code:    " + domainInfoRsp.getRsp().getResults()[0].getCode());
            System.out.println("  Result message: " + domainInfoRsp.getRsp().getResults()[0].getMsg()); 
            System.out.println("  AuthInfo:       " + domainInfoRsp.getAuthInfo().getValue());
            System.out.println("  Creation date:  " + RTKBase.UTC_FMT.parse(domainInfoRsp.getCreatedDate())); 
            System.out.println("  Expiry date:    " + domainExpiryDate);
            System.out.println();
            
            System.out.println("Updating domain \"" + domainName + "\"...");
            
            epp_DomainUpdateAddRemove add = new epp_DomainUpdateAddRemove();
            
            // Add new name servers to domain...
            add.setNameServers(EPPXMLBase.convertListToStringArray(newNameServers));
            
            // Also set clientTransferProhibited status...
            
            epp_DomainStatus domainStatus = new epp_DomainStatus();
            domainStatus.setType(epp_DomainStatusType.CLIENT_TRANSFER_PROHIBITED);
            domainStatus.setLang("en");
            domainStatus.setValue("The client did not send money.");
            
            epp_DomainStatus[] domainStatuses = new epp_DomainStatus[1];
            domainStatuses[0] = domainStatus;            
            add.setStatus(domainStatuses);

            epp_DomainUpdateAddRemove remove = new epp_DomainUpdateAddRemove();
            
            // Remove the old name servers from the domain...
            remove.setNameServers(EPPXMLBase.convertListToStringArray(oldNameServers));

            // Change authInfo...
            epp_DomainUpdateChange change = new epp_DomainUpdateChange();
            
            change = new epp_DomainUpdateChange();
                
            epp_AuthInfo newAuthInfo = new epp_AuthInfo();
            newAuthInfo.setValue(NEW_AUTH_INFO);

            // Specify the old name servers...
            remove.setNameServers(EPPXMLBase.convertListToStringArray(oldNameServers));
            
            epp_DomainUpdateRsp domainUpdateRsp = DomainUtils.updateDomain(client, domainName, add, remove, change);
            System.out.println("  Result code:    " + domainUpdateRsp.getRsp().getResults()[0].getCode());
            System.out.println("  Result message: " + domainUpdateRsp.getRsp().getResults()[0].getMsg());
            System.out.println();
            
            System.out.println("Renewing domain \"" + domainName + " for " + RENEW_PERIOD + " years...");
            epp_DomainRenewRsp domainRenewRsp = DomainUtils.renewDomain(client, domainName, RENEW_PERIOD, domainExpiryDate);
            
            System.out.println("  Result code:    " + domainRenewRsp.getRsp().getResults()[0].getCode());
            System.out.println("  Result message: " + domainRenewRsp.getRsp().getResults()[0].getMsg());
            System.out.println("  Expiry date:    " + RTKBase.UTC_FMT.parse(domainRenewRsp.getExpirationDate()));
            System.out.println();

            System.out.println("Deleting domain \"" + domainName + "...");
            epp_DomainDeleteRsp domainDeleteRsp = DomainUtils.deleteDomain(client, domainName);
            System.out.println("  Result code:    " + domainDeleteRsp.getRsp().getResults()[0].getCode());
            System.out.println("  Result message: " + domainDeleteRsp.getRsp().getResults()[0].getMsg());
            System.out.println();
            
            // All done with this session, so let's log out...
            System.out.println("Logging out from the EPP Server...");
            client.logout(getClientTrid(user));
            System.out.println();

            // ... and disconnect            
            System.out.println("Disconnecting from the EPP Server...");
            client.disconnect();
            System.out.println();

        }
        catch ( epp_XMLException xcp )
        {
            System.err.println("ERROR: (epp_XMLException): " + xcp.getErrorMessage());
        }
        catch ( epp_Exception xcp )
        {
            System.err.println("ERROR (epp_Exception):");
            epp_Result[] results = xcp.getDetails();
            System.err.println("  Result: ["+results[0]+"]");
        }
        catch ( Exception xcp )
        {
            System.err.println("ERROR: Exception [" + xcp.getClass().getName() + "] [" + xcp.getMessage() + "]");
            xcp.printStackTrace();
        }

    }

    private static String getClientTrid(String epp_client_id)
    {
        return "ABC:"+epp_client_id+":"+System.currentTimeMillis();
    }
}
