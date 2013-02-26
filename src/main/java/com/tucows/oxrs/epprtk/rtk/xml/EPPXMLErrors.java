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
 * $Header: /cvsroot/epp-rtk/epp-rtk/java/src/com/tucows/oxrs/epprtk/rtk/xml/EPPXMLErrors.java,v 1.1 2004/12/07 15:53:27 ewang2004 Exp $
 * $Revision: 1.1 $
 * $Date: 2004/12/07 15:53:27 $
 */

package com.tucows.oxrs.epprtk.rtk.xml;

import java.util.*;

import org.w3c.dom.*;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.apache.xerces.parsers.*;

/**
 * EPP XML Errors -- Handler for XML parsing errors
 * @author Frank Thompson
 * @version $Revision: 1.1 $ $Date: 2004/12/07 15:53:27 $
**/
public class EPPXMLErrors extends EPPXMLBase implements ErrorHandler 
{
    
    public static int XML_ERROR_TYPE_WARNING = 0; // receoverable
    public static int XML_ERROR_TYPE_ERROR = 1; // recoverable
    public static int XML_ERROR_TYPE_FATAL = 2; // not recoverable
    
    List xml_error_messages_ = new ArrayList();

    public EPPXMLErrors() { }

    public void warning(SAXParseException ex) { store(ex, XML_ERROR_TYPE_WARNING); }

    public void error(SAXParseException ex) { store(ex, XML_ERROR_TYPE_ERROR); }

    public void fatalError(SAXParseException ex) throws SAXException { store(ex, XML_ERROR_TYPE_FATAL); }

    public List getErrorMessages() { return xml_error_messages_; }
    
    /// deprecated ///
    public Hashtable getErrorNodes() { return null; }
    public Object getError(Node errorNode) { return null; }
    //////////////////
    
    public int getErrorCount() { return xml_error_messages_.size(); }

    public void clearErrors() { xml_error_messages_ = new ArrayList(); }

    private void store(SAXParseException ex, int type) 
    {
        String method_name = "store(SAXParseException, String)";
        debug(DEBUG_LEVEL_THREE,method_name,"Entered ["+ex+"] ["+type+"]");
        // build error text
        String errorString= type+" at line number, "+ex.getLineNumber() +": "+ex.getMessage()+"\n";

        xml_error_messages_.add(errorString);
        
        if ( type == XML_ERROR_TYPE_FATAL )
        {
            // DEBUG_NONE here means to always print this message no matter at
            // which level the debug setting.
            debug(DEBUG_NONE,method_name,"Exception in XML Parsing: ["+errorString+"]");
        }

        debug(DEBUG_LEVEL_THREE,method_name,"Leaving");
    }
}
