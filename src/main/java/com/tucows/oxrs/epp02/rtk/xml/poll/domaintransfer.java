package com.tucows.oxrs.epp02.rtk.xml.poll;

import com.tucows.oxrs.epp02.rtk.xml.EPPXMLBase;
import org.openrtk.idl.epp02.*;
import org.w3c.dom.*;


/**
 * An implementation of PollResData for contact transfer poll
 * response data.
 * Populates the epp_PollResData with the epp_PollResDataUnion
 * and m_domain_transfer member set.
 */
public class domaintransfer extends EPPXMLBase implements PollResData
{

    private epp_PollResData poll_res_data_;
    
    
    /**
     * Populates the poll res data with epp_PollDomainTransfer
     * data.
     */
     public void fromXML(Node res_data_node) throws epp_XMLException
    {
        String method_name = "fromXML(Node)";
        
        NodeList domain_transfer_data_list = res_data_node.getChildNodes();
        
        epp_PollResData poll_res_data_ = new epp_PollResData();
        
        epp_PollResDataUnion poll_res_data_union = new epp_PollResDataUnion();
        
        epp_PollDomainTransfer domain_transfer = new epp_PollDomainTransfer();
        
        debug(DEBUG_LEVEL_TWO,method_name,"domain:transfer's node count ["+domain_transfer_data_list.getLength()+"]");

        if ( domain_transfer_data_list.getLength() == 0 )
        {
            throw new epp_XMLException("missing domain transfer data");
        }

        for (int count = 0; count < domain_transfer_data_list.getLength(); count++)
        {
            Node a_node = domain_transfer_data_list.item(count);

            if ( a_node.getNodeName().equals("domain:name") ) { domain_transfer.m_domain_name = a_node.getFirstChild().getNodeValue(); }

            if ( a_node.getNodeName().equals("domain:authInfo") )
            {
                domain_transfer.m_auth_info = new epp_AuthInfo();
                domain_transfer.m_auth_info.m_value = a_node.getFirstChild().getNodeValue();
                domain_transfer.m_auth_info.m_type = (epp_AuthInfoType)auth_type_string_to_type_hash_.get( ((Element)a_node).getAttribute("type") );
                domain_transfer.m_auth_info.m_roid = ((Element)a_node).getAttribute("roid");
            }

        }
        
        poll_res_data_union.m_domain_transfer(domain_transfer);
        
        poll_res_data_.m_type = epp_PollResDataType.DOMAIN_TRANSFER;
        poll_res_data_.m_data = poll_res_data_union;
    }
    
    /**
     * Returns the epp_PollResData private member.
     * Should only be called after a successful fromXML()
     * otherwise, the poll res data will be null.
     */
     public epp_PollResData getPollResData()
    {
        return poll_res_data_;
    }

}
