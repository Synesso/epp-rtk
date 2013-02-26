/*
**
** EPP RTK Java
** Copyright (C) 2001-2002, Tucows, Inc.
** Copyright (C) 2003, Afilias, Ltd.
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

package com.tucows.oxrs.epprtk.rtk.example;

import org.openrtk.idl.epprtk.epp_TransferOpType;

import com.tucows.oxrs.epprtk.rtk.EPPClient;

public class ContactTransferExample
{
    private static String USAGE = "Usage: com.tucows.oxrs.epprtk.rtk.example.ContactTransferExample <epp host> <epp port> <client1> <password1> <client2> <password2> <contact>";
    private static String AUTH_INFO = "123456";
    
    public static void main(String[] args) {
        if (args.length < 7)
        {
            System.err.println(USAGE);
            System.exit(1);
        }

        String host      = args[0];
        int port         = Integer.parseInt(args[1]);
        String user      = args[2];
        String password  = args[3];
        String user2     = args[4];
        String password2 = args[5];
        String contactId   = args[6]; 

        try {
            EPPClient client = new EPPClient();
            client.setEPPHostName(host);
            client.setEPPHostPort(port);
            client.setLang("en");
            
            // Connecting to EPP server...
            System.out.println("Connecting to EPP server...");
            client.connectAndGetGreeting();

            System.out.println("Logging in as \"" + user + "\"...");
            String trid = getClientTrid(user);
            client.login(trid, user, password);
            
            // Create contact as losing registrar...
            
            System.out.println("Checking availability of contact \"" + contactId + "\"...");
            
            if (!ContactUtils.checkContact(client, contactId).booleanValue()) {
                throw new Exception("Contact \"" + contactId + "\" already exists. Please choose a different name.");
            }
            
            System.out.println("Creating contact \"" + contactId + "\"...");
            ContactUtils.createContact(client, contactId, AUTH_INFO);
            
            System.out.println("Logging out...");
            client.logout(trid);

            System.out.println("Logging in as \"" + user2 + "\"...");
            trid = getClientTrid(user2);
            client.login(trid, user2, password2);

            // Request transfer as gaining registrar... 
            System.out.println("Requesting transfer...");
            ContactUtils.transferContact(client, contactId, epp_TransferOpType.REQUEST, AUTH_INFO);

            System.out.println("Logging out...");
            client.logout(trid);

            System.out.println("Logging in as \"" + user + "\"...");
            trid = getClientTrid(user);
            client.login(trid, user, password);

            // Approve transfer as losing registrar...
            System.out.println("Approving transfer...");
            ContactUtils.transferContact(client, contactId, epp_TransferOpType.APPROVE, AUTH_INFO);

            System.out.println("Logging out...");
            client.logout(trid);

            System.out.println("Logging in as \"" + user + "\"...");
            trid = getClientTrid(user2);
            client.login(trid, user2, password2);
            
            // Perform clean up (delete contact)...
            System.out.println("Deleting contact...");
            ContactUtils.deleteContact(client, contactId);

            System.out.println("Logging out...");
            client.logout(trid);

            System.out.println("Disconnecting from EPP server...");
            client.disconnect();
        }
        catch (Exception e) {
            System.err.println("ERROR: " + e.getMessage());
            e.printStackTrace(System.err);
        }
    }
    
    private static String getClientTrid(String epp_client_id)
    {
        return "ABC:"+epp_client_id+":"+System.currentTimeMillis();
    }
}
