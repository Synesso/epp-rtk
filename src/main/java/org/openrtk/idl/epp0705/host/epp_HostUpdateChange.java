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

package org.openrtk.idl.epp0705.host;


/**
 * Class that contains the host object elements to be changed in the registry.</p>
 * This class can only be used when modifying a host object.</p>
 * $Header: /cvsroot/epp-rtk/epp-rtk/java/src/org/openrtk/idl/epp0705/host/epp_HostUpdateChange.java,v 1.1 2003/03/20 22:42:31 tubadanm Exp $<br>
 * $Revision: 1.1 $<br>
 * $Date: 2003/03/20 22:42:31 $<br>
 * @see org.openrtk.idl.epp0705.host.epp_HostUpdateReq#setChange(epp_HostUpdateChange)
 */
public class epp_HostUpdateChange implements org.omg.CORBA.portable.IDLEntity
{
  /**
   * The new host name of the host object.
   * @see #setName(String)
   * @see #getName()
   */
  public String m_name = null;

  /**
   * Empty constructor
   */
  public epp_HostUpdateChange ()
  {
  } // ctor

  /**
   * The constructor with initializing variables.
   * @param _m_name The new host name of the host object
   */
  public epp_HostUpdateChange (String _m_name)
  {
    m_name = _m_name;
  } // ctor

  /**
   * Accessor method for the new host name of the host object
   * @param value The new host name
   * @see #m_name
   */
  public void setName(String value) { m_name = value; }
  /**
   * Accessor method for the new host name of the host object
   * @return The new host name
   * @see #m_name
   */
  public String getName() { return m_name; }

  /**
   * Converts this class into a string.
   * Typically used to view the object in debug output.
   * @return The string representation of this object instance
   */
  public String toString() { return this.getClass().getName() + ": { m_name ["+m_name+"] }"; }

} // class epp_HostUpdateChange
