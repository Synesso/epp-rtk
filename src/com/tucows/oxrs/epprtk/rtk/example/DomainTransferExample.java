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
import java.util.Iterator;
import java.util.List;

import org.openrtk.idl.epprtk.epp_TransferOpType;
import org.openrtk.idl.epprtk.domain.epp_DomainTransferRsp;

import com.tucows.oxrs.epprtk.rtk.EPPClient;

public class DomainTransferExample
{
    private static String USAGE = "Usage: com.tucows.oxrs.epprtk.rtk.example.DomainExample <epp host> <epp port> <client1> <password1> <client2> <password2> <domain name>";
    private static String AUTH_INFO = "123456";

    public static void main(String[] args) {
        if (args.length < 7)
        {
            System.err.println(USAGE);
            System.exit(1);
        }

        String host        = args[0];
        int port           = Integer.parseInt(args[1]);
        String user        = args[2];
        String password    = args[3];
        String user2       = args[4];
        String password2   = args[5];
        String domainName  = args[6];
        
        if (domainName.indexOf('.') == -1) {
            System.err.println("ERROR: Domain name must contain a TLD.");
            System.exit(1);
        }
        String contactId   = domainName.substring(0, domainName.indexOf('.')) + "-C";
        
        List nameServers = new ArrayList();
        nameServers.add("ns1.example.com");
        nameServers.add("ns2.example.com");

        System.out.println("      HOST: " + host);
        System.out.println("      PORT: " + port);
        System.out.println("   CLIENT1: " + user);
        System.out.println("   CLIENT2: " + user2);
        System.out.println("    DOMAIN: " + domainName);
        System.out.println("   CONTACT: " + contactId);

        for (Iterator it = nameServers.iterator();it.hasNext();) {
            System.out.println("NAMESERVER: " + it.next());
        }
        System.out.println();
        
        try {
            EPPClient client = new EPPClient();
            client.setEPPHostName(host);
            client.setEPPHostPort(port);
            client.setLang("en");
            
            // Connecting to EPP server...
            System.out.println("Connecting to EPP server...");
            client.connectAndGetGreeting();
            System.out.println();

            System.out.println("Logging in as \"" + user + "\"...");            
            String trid = getClientTrid(user);
            client.login(trid, user, password);
            System.out.println();
            
            // Create name servers if required...            
            for (Iterator it = nameServers.iterator(); it.hasNext();) {
                String hostName = (String) it.next();
                                
                // Create the name server if required...
                System.out.println("Checking availability of host \"" + hostName + "\"...");
                
                if (HostUtils.checkHost(client, hostName).booleanValue()) {
                    System.out.println("  Host \"" + hostName + "\" does not exist. Creating...");
                    HostUtils.createExternalHost(client, hostName);
                }
                else {
                    System.out.println("  Host \"" + hostName + "\" already exists...");
                }
                System.out.println();
            }

            // Create contacts if required...
            System.out.println("Checking availability of contact \"" + contactId + "\"...");

            if (ContactUtils.checkContact(client, contactId).booleanValue()) {
                System.out.println("  Contact \"" + contactId + "\" does not exist. Creating...");
                ContactUtils.createContact(client, contactId, AUTH_INFO);
            }
            System.out.println();
            
            // Create domain as losing registrar...
            System.out.println("Checking availability of domain \"" + domainName + "\"...");
            
            if (!DomainUtils.checkDomain(client, domainName).booleanValue()) {
                throw new Exception("Domain \"" + domainName + "\" already exists! Please choose a different name.");
            }
            System.out.println();
            
            System.out.println("Creating domain \"" + domainName + "\"...");

            DomainUtils.createDomain(
                    client, 
                    domainName, 
                    2, 
                    nameServers, 
                    AUTH_INFO, 
                    contactId, 
                    contactId, 
                    contactId, 
                    contactId);
            System.out.println();
            
            System.out.println("Logging out...");
            client.logout(trid);

            System.out.println("Logging in as \"" + user2 + "\"...");
            trid = getClientTrid(user2);
            client.login(trid, user2, password2);
            System.out.println();

            // Request transfer as gaining registrar... 
            System.out.println("Requesting transfer of domain \"" + domainName + "\"...");
            epp_DomainTransferRsp domainTransferRsp = DomainUtils.transferDomain(client, domainName, epp_TransferOpType.REQUEST, "123456");
            
            System.out.println("  Result code:    " + domainTransferRsp.getRsp().getResults()[0].getCode());
            System.out.println("  Result message: " + domainTransferRsp.getRsp().getResults()[0].getMsg());
            System.out.println();

            System.out.println("Logging out...");
            client.logout(trid);
            System.out.println();

            System.out.println("Logging in as \"" + user + "\"...");
            trid = getClientTrid(user);
            client.login(trid, user, password);
            System.out.println();

            // Approve transfer as losing registrar...
            System.out.println("Approving transfer of domain \"" + domainName + "\"...");
            domainTransferRsp = DomainUtils.transferDomain(client, domainName, epp_TransferOpType.APPROVE, AUTH_INFO);

            System.out.println("  Result code:    " + domainTransferRsp.getRsp().getResults()[0].getCode());
            System.out.println("  Result message: " + domainTransferRsp.getRsp().getResults()[0].getMsg()); 
            System.out.println();
            
            System.out.println("Logging out...");
            client.logout(trid);
            System.out.println();

            System.out.println("Logging in as \"" + user + "\"...");
            trid = getClientTrid(user2);
            client.login(trid, user2, password2);
            System.out.println();

            // Perform clean up (delete domain)...
            System.out.println("Deleting domain...");
            DomainUtils.deleteDomain(client, domainName);
            System.out.println();

            System.out.println("Logging out...");
            client.logout(trid); 
            System.out.println();

            System.out.println("Disconnecting from EPP server...");
            client.disconnect();
            System.out.println();
        }
        catch (Exception e) {
            System.err.println("ERROR: " + e.getMessage());
            e.printStackTrace(System.err);
        }
    }
    
    protected static String getClientTrid(String epp_client_id)
    {
        return "ABC:"+epp_client_id+":"+System.currentTimeMillis();
    }
}
