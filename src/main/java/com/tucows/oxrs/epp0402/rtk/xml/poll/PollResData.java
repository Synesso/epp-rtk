package com.tucows.oxrs.epp0402.rtk.xml.poll;

import org.openrtk.idl.epp0402.epp_PollResData;
import org.openrtk.idl.epp0402.epp_XMLException;
import org.w3c.dom.Node;

/**
 * This interface is used by EPPPoll to instantiate a Poll parser
 * class without having to rely on a particular classname.
 * Implementors of this class have to be able to populate
 * an epp_PollResData structure (via fromXML()) and should 
 * be able to pass this data back (via getPollResData()).
 */
public interface PollResData
{

    /**
     * Converts a poll response data sub node (and its children nodes
     * to the appropriate epp struct depending on the implementing class.
     * An example of a res data subnode would be "domain:transfer".
     */
    public void fromXML(Node res_data_sub_node) throws epp_XMLException;
    
    /**
     * Returns the epp_PollResData that was populated in the call
     * to "fromXML()".
     */
    public epp_PollResData getPollResData();

}
