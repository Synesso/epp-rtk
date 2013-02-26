package org.openrtk.idl.epp02.contact;


/**
* Deprecated class to maintain some backward compatibility.
* epp_ContactPhone is the preferred class to use for the
* Contact's m_voice data member.
* @deprecated As of EPP-RTK version 0.3.8,
*      replaced by <code>epp_ContactPhone</code>.
*/


// it is still valid for used with contact's m_voice.
public class epp_ContactVoice extends epp_ContactPhone
{

  public epp_ContactVoice ()
  {
  }

  public epp_ContactVoice (String _m_extension, String _m_value)
  {
    super( _m_extension, _m_value );
  }


} // class epp_ContactVoice
