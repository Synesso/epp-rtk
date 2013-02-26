package com.tucows.oxrs.epprtk.rtk.example;

import org.openrtk.idl.epprtk.epp_Command;
import org.openrtk.idl.epprtk.epp_Exception;
import org.openrtk.idl.epprtk.epp_PollOpType;
import org.openrtk.idl.epprtk.epp_PollReq;
import org.openrtk.idl.epprtk.epp_PollRsp;
import org.openrtk.idl.epprtk.epp_XMLException;

import com.tucows.oxrs.epprtk.rtk.EPPClient;
import com.tucows.oxrs.epprtk.rtk.xml.EPPPoll;

public class QueueUtils {

    private QueueUtils() { }
    
    public static epp_PollRsp pollQueue(EPPClient client, epp_PollOpType operation) 
        throws epp_XMLException, epp_Exception, Exception 
    {
        epp_PollReq poll_request = new epp_PollReq();
        epp_Command command_data = new epp_Command();
        command_data.m_client_trid = getClientTrid(client.getEPPClientID());
        
        poll_request.m_cmd = command_data;
        poll_request.m_op = operation;

        EPPPoll poll = new EPPPoll();
        poll.setRequestData(poll_request);

        // Send request to server...
        poll = (EPPPoll) client.processAction(poll);

        // Process response from server...
        return poll.getResponseData();
    }

    private static String getClientTrid(String epp_client_id)
    {
        return "ABC:"+epp_client_id+":"+System.currentTimeMillis();
    }
}
