/*
**
** EPP RTK Java
** Copyright (C) 2001, Liberty Registry Management Services, Inc.
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
 * $Header: /cvsroot/epp-rtk/epp-rtk/java/src/com/tucows/oxrs/epp0604/rtk/xml/extension/RTKVersion.java,v 1.2 2003/09/10 21:31:13 tubadanm Exp $
 * $Revision: 1.2 $
 * $Date: 2003/09/10 21:31:13 $
 */

package com.tucows.oxrs.epp0604.rtk.xml.extension;

import java.io.*;
import java.util.*;
import java.text.*;

import com.tucows.oxrs.epp0604.rtk.*;
import com.tucows.oxrs.epp0604.rtk.xml.*;
import org.openrtk.idl.epp0604.*;

import org.w3c.dom.*;
import org.w3c.dom.traversal.*;
import org.apache.xerces.parsers.*;
import org.xml.sax.*;
import org.apache.xerces.dom.*;
import org.apache.xml.serialize.*;

/**
 * This class is used to exchange RTK version number with the Registry.
 *
 * @author Oleg Snegirev
 * @version $Revision: 1.2 $ $Date: 2003/09/10 21:31:13 $
 */
public class RTKVersion extends EPPXMLBase implements epp_Extension
{
    /**
     * Default constructor
     */
    public RTKVersion()
    {
    }

    /**
     * Returns RTK version number.
     *
     * @return String
     */
    public static String getVersion()
    {
        return RTKBase.RTK_VERSION;
    }

    /**
     * Converts the RTK version number into XML to be put into the extension
     * section of the request.
     * Implemented method from org.openrrc.rtk.epp0604.epp_Extension interface.
     *
     * @throws org.openrtk.idl.epp0604.epp_XMLException if required data is missing
     * @see org.openrtk.idl.epp0604.epp_Extension
     */
    public String toXML() throws epp_XMLException
    {
        String method_name = "toXML()";
        debug(DEBUG_LEVEL_THREE,method_name,"Entered");

        Document doc = new DocumentImpl();

        Element rtktag = doc.createElement( "rtk" );
        Element vernum = doc.createElement( "version" );

        vernum.appendChild( doc.createTextNode( RTK_VERSION ) );
        rtktag.appendChild( vernum );

        doc.appendChild( rtktag );

        String ver_xml;

        try
        {
            ver_xml = createXMLFromDoc( doc );
        }
        catch( IOException xcp )
        {
            throw new epp_XMLException( "IOException in building XML [" + xcp.getMessage() + "]" );
        }

        debug( DEBUG_LEVEL_THREE, method_name, "Returning:\"" + ver_xml + "\"" );
        debug(DEBUG_LEVEL_THREE,method_name,"Leaving");

        return ver_xml;
    }

    /**
     * Parses an XML String of test number data from the Extension section of
     * a response from the Registry.
     * Implemented method from org.openrrc.rtk.epp0604.epp_Extension interface.
     * <br><b>NOTE:</b> this method is empty because RTK version number will
     * never be send from server side to the client.
     *
     * @param A new test number Extension XML String to parse
     * @throws org.openrtk.idl.epp0604.epp_XMLException if the response XML is not parsable or does not contain the expected data
     * @see org.openrtk.idl.epp0604.epp_Action
     */
    public void fromXML( String xml ) throws epp_XMLException
    {
    }

}   // end of class com.tucows.oxrs.epp0604.rtk.xml.extension.RTKVersion
