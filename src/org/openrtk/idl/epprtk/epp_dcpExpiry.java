/*
**
** EPP RTK Java
** Copyright (C) 2002, Tucows, Inc.
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

package org.openrtk.idl.epprtk;


/**
 * Class used to specifiy the expiry date of the data collection policy.</p>
 * $Header: /cvsroot/epp-rtk/epp-rtk/java/src/org/openrtk/idl/epprtk/epp_dcpExpiry.java,v 1.1 2004/12/07 15:27:49 ewang2004 Exp $<br>
 * $Revision: 1.1 $<br>
 * $Date: 2004/12/07 15:27:49 $<br>
 * @see org.openrtk.idl.epprtk.epp_DataCollectionPolicy
 * @see com.tucows.oxrs.epprtk.rtk.xml.EPPGreeting
 */
public class epp_dcpExpiry implements org.omg.CORBA.portable.IDLEntity
{
  public org.openrtk.idl.epprtk.epp_dcpExpiryType m_type = null;
  public String m_value = null;

  public epp_dcpExpiry ()
  {
  } // ctor

  public epp_dcpExpiry (org.openrtk.idl.epprtk.epp_dcpExpiryType _m_type, String _m_value)
  {
    m_type = _m_type;
    m_value = _m_value;
  } // ctor

  public void setType(org.openrtk.idl.epprtk.epp_dcpExpiryType value) { m_type = value; }
  public org.openrtk.idl.epprtk.epp_dcpExpiryType getType() { return m_type; }

  public void setValue(String value) { m_value = value; }
  public String getValue() { return m_value; }

  public String toString() { return this.getClass().getName() + ": { m_type ["+m_type+"] m_value ["+m_value+"] }"; }

} // class epp_dcpExpiry
