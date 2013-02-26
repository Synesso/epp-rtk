/*
**
** EPP RTK Java
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

package org.openrtk.idl.epp0705;


/**
 * Class that holds the message contained in the message queue response from the EPP server.
 * Used by epp_MessageQueue.</p>
 * $Header: /cvsroot/epp-rtk/epp-rtk/java/src/org/openrtk/idl/epp0705/epp_ExtMessage.java,v 1.1 2003/03/20 22:42:18 tubadanm Exp $<br>
 * $Revision: 1.1 $<br>
 * $Date: 2003/03/20 22:42:18 $<br>
 * @see org.openrtk.idl.epp0705.epp_MessageQueue#setExtMessage(epp_ExtMessage)
 */
public class epp_ExtMessage implements org.omg.CORBA.portable.IDLEntity
{

  /**
   * The ISO language of the message.
   * Optional.  Default is "en" if null.
   * @see #setLang(String)
   * @see #getLang()
   */
  public String m_lang = null;
  /**
   * The message text.
   * This is free text and could contain "informal" XML. The RTK 
   * does not parse this XML.
   * @see #setLang(String)
   * @see #getLang()
   */
  public String m_value = null;

  /**
   * Empty constructor
   */
  public epp_ExtMessage ()
  {
  } // ctor

  /**
   * The constructor with initializing variables.
   * @param _m_lang The ISO language of the message
   * @param _m_value The message text.
   */
  public epp_ExtMessage (String _m_lang, String _m_value)
  {
    m_lang = _m_lang;
    m_value = _m_value;
  } // ctor

  /**
   * Accessor method for the ISO language.
   * @param value The optional ISO language of the message.
   * @see #m_lang
   */
  public void setLang(String value) { m_lang = value; }
  /**
   * Accessor method for the ISO language.
   * @return The optional ISO language of the message.
   * @see #m_lang
   */
  public String getLang() { return m_lang; }

  /**
   * Accessor method for the message text.
   * @param value The message text.
   * @see #m_value
   */
  public void setValue(String value) { m_value = value; }
  /**
   * Accessor method for the message text.
   * @return The message text.
   * @see #m_value
   */
  public String getValue() { return m_value; }

  /**
   * Converts this class into a string.
   * Typically used to view the object in debug output.
   * @return The string representation of this object instance
   */
  public String toString() { return this.getClass().getName() + ": { m_lang ["+m_lang+"] m_value ["+m_value+"] }"; }

} // class epp_ExtMessage
