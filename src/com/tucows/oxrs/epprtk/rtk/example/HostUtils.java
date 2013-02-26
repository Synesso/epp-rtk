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
 * $Header: /cvsroot/epp-rtk/epp-rtk/java/src/com/tucows/oxrs/epprtk/rtk/example/HostExample.java,v 1.1 2004/12/07 15:53:26 ewang2004 Exp $
 * $Revision: 1.1 $
 * $Date: 2004/12/07 15:53:26 $
 */

package com.tucows.oxrs.epprtk.rtk.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.openrtk.idl.epprtk.epp_CheckResult;
import org.openrtk.idl.epprtk.epp_Command;
import org.openrtk.idl.epprtk.epp_Exception;
import org.openrtk.idl.epprtk.epp_XMLException;
import org.openrtk.idl.epprtk.host.epp_HostAddress;
import org.openrtk.idl.epprtk.host.epp_HostAddressType;
import org.openrtk.idl.epprtk.host.epp_HostCheckReq;
import org.openrtk.idl.epprtk.host.epp_HostCreateReq;
import org.openrtk.idl.epprtk.host.epp_HostCreateRsp;
import org.openrtk.idl.epprtk.host.epp_HostDeleteReq;
import org.openrtk.idl.epprtk.host.epp_HostDeleteRsp;

import com.tucows.oxrs.epprtk.rtk.EPPClient;
import com.tucows.oxrs.epprtk.rtk.xml.EPPHostCheck;
import com.tucows.oxrs.epprtk.rtk.xml.EPPHostCreate;
import com.tucows.oxrs.epprtk.rtk.xml.EPPHostDelete;
import com.tucows.oxrs.epprtk.rtk.xml.EPPXMLBase;

public class HostUtils
{
    private HostUtils() { }

    /**
     * Checks if each host is available.
     *  
     * @param client
     * @param hostNames
     * @return Boolean Map confirming whether each host is available.
     * 
     * @throws epp_XMLException
     * @throws epp_Exception
     * @throws Exception
     */
    public static Map checkHosts(EPPClient client, List hostNames) throws epp_XMLException, epp_Exception, Exception {
        Map hostsAvail = new HashMap();
        
        epp_HostCheckReq host_check_request = new epp_HostCheckReq();
        
        epp_Command command = new epp_Command();
        
        command.setClientTrid(getClientTrid(client.getEPPClientID()));
        host_check_request.setCmd(command);

        host_check_request.setNames(EPPXMLBase.convertListToStringArray(hostNames));
        
        EPPHostCheck host_check = new EPPHostCheck();
        host_check.setRequestData(host_check_request);
        
        host_check = (EPPHostCheck) client.processAction(host_check);

        epp_CheckResult[] check_results = host_check.getResponseData().getResults();
        
        for (Iterator it = hostNames.iterator(); it.hasNext();) {
            String hostName = (String) it.next();
            Boolean hostAvail = EPPXMLBase.getAvailResultFor(check_results, hostName);

            hostsAvail.put(hostName, hostAvail);
        }
        
        return hostsAvail;
    }

    /**
     * Checks a host to see if it exists.
     * 
     * @param client
     * @param hostName
     * @return Boolean confirm whether specified host exists.
     * 
     * @throws epp_XMLException
     * @throws epp_Exception
     * @throws Exception
     */
    public static Boolean checkHost(EPPClient client, String hostName) throws epp_XMLException, epp_Exception, Exception 
    {
        List hostNames = new ArrayList();
        hostNames.add(hostName);
        
        Map hostsAvail = checkHosts(client, hostNames);
        
        return (Boolean) hostsAvail.get(hostName);
    }

    public static epp_HostCreateRsp createHost(
            EPPClient client, 
            String hostName,
            List ipAddresses) throws epp_XMLException, epp_Exception, Exception 
    {
        epp_HostCreateReq host_create_request = new epp_HostCreateReq();
        
        epp_Command command_data = new epp_Command();
        
        command_data.setClientTrid(getClientTrid(client.getEPPClientID()));
        host_create_request.setCmd(command_data);

        if (ipAddresses != null) {
            List hostAddresses = new ArrayList();
            
            for (Iterator it = ipAddresses.iterator(); it.hasNext();) {
                hostAddresses.add(new epp_HostAddress(epp_HostAddressType.IPV4, (String) it.next()));
            }

            host_create_request.setAddresses((epp_HostAddress[])EPPXMLBase.convertListToArray((new epp_HostAddress()).getClass(), hostAddresses));
        }
        
        host_create_request.setName(hostName);

        EPPHostCreate host_create = new EPPHostCreate();
        host_create.setRequestData(host_create_request);

        // Send request to server...
        host_create = (EPPHostCreate) client.processAction(host_create);

        // Process response from server...
        return host_create.getResponseData();
    }

    /**
     * Creates an external host. (i.e. not owned by the registry)
     * 
     * @param client
     * @param hostName
     * @return
     * @throws epp_XMLException
     * @throws epp_Exception
     * @throws Exception
     */
    public static epp_HostCreateRsp createExternalHost(EPPClient client, String hostName) throws epp_XMLException, epp_Exception, Exception 
    {
        return createHost(client, hostName, null);
    }

    /**
     * Deletes a host.
     * 
     * @param client
     * @param hostName
     * @return
     * @throws epp_XMLException
     * @throws epp_Exception
     * @throws Exception
     */
    public static epp_HostDeleteRsp deleteHost(EPPClient client, String hostName) throws epp_XMLException, epp_Exception, Exception 
    {
        epp_HostDeleteReq host_delete_request = new epp_HostDeleteReq();
        
        epp_Command command_data = new epp_Command();
        command_data.setClientTrid(getClientTrid(client.getEPPClientID()));
        host_delete_request.setCmd(command_data);

        host_delete_request.setName(hostName);
        
        EPPHostDelete host_delete = new EPPHostDelete();
        host_delete.setRequestData(host_delete_request);

        // Send request to server...
        host_delete = (EPPHostDelete) client.processAction(host_delete);

        // Process response from server...
        return host_delete.getResponseData();
    }
    
    protected static String getClientTrid(String epp_client_id)
    {
        return "ABC:"+epp_client_id+":"+System.currentTimeMillis();
    }
}
