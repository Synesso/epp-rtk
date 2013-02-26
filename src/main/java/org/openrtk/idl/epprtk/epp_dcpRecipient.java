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
 * Wrapper class for the DCP Recipient.</p>
 * $Header: /cvsroot/epp-rtk/epp-rtk/java/src/org/openrtk/idl/epprtk/epp_dcpRecipient.java,v 1.1 2004/12/07 15:27:49 ewang2004 Exp $<br>
 * $Revision: 1.1 $<br>
 * $Date: 2004/12/07 15:27:49 $<br>
 * @see org.openrtk.idl.epprtk.epp_dcpStatement
 */
public class epp_dcpRecipient implements org.omg.CORBA.portable.IDLEntity
{
  public org.openrtk.idl.epprtk.epp_dcpRecipientType m_type = null;
  public String m_rec_desc = null;

  public epp_dcpRecipient ()
  {
  } // ctor

  public epp_dcpRecipient (org.openrtk.idl.epprtk.epp_dcpRecipientType _m_type, String _m_rec_desc)
  {
    m_type = _m_type;
    m_rec_desc = _m_rec_desc;
  } // ctor

  public void setType(org.openrtk.idl.epprtk.epp_dcpRecipientType value) { m_type = value; }
  public org.openrtk.idl.epprtk.epp_dcpRecipientType getType() { return m_type; }

  public void setRecDesc(String value) { m_rec_desc = value; }
  public String getRecDesc() { return m_rec_desc; }

  public String toString() { return this.getClass().getName() + ": { m_type ["+m_type+"] m_rec_desc ["+m_rec_desc+"] }"; }

} // class epp_dcpRecipient
