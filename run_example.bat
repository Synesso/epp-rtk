@echo off
rem ---------------------------------------------------------------------------
rem Donated by Dan Kirkdorffer, eNom (http://www.eNom.com/)
rem ---------------------------------------------------------------------------
rem Supported Environment Variables (default values in square brackets):
rem
rem   JAVA_HOME             Java Development Kit directory. [REQUIRED]
rem
rem   RTK_HOME              Distribution directory for Registrar Toolkit. [REQUIRED]
rem
rem ---------------------------------------------------------------------------


rem ----- Save Environment Variables That May Change --------------------------

set _XERCES_HOME=%XERCES_HOME%

rem ----- Verify and Set Required Environment Variables -----------------------

set USAGE="usage: %0 rtk|0705|0604|0503|0402|02 SessionExample|ContactExample|ContactTransferExample|DomainExample|DomainTransferExample|HostExample epp_host_name epp_host_port epp_client_id epp_password domain_name [registrant_contact_id] [tech_contact_id]"

if not "%JAVA_HOME%" == "" goto gotJavaHome
  echo You must set JAVA_HOME to point at your Java Development Kit directory
  goto cleanUp
:gotJavaHome

if not "%RTK_HOME%" == "" goto gotRTKHome
  echo You must set RTK_HOME to point at your Registrar Toolkit directory
  goto cleanUp
:gotRTKHome

set EPP_VERSION=%1
set EXAMPLE_CLASS=%2
set EPP_HOST=%3
set EPP_PORT=%4
set CLIENT_ID=%5
set PASSWORD=%6
set DOMAIN=%7
set REGISTRANT_CONTACT=%8
set TECH_CONTACT=%9

if not "%EXAMPLE_CLASS%" == "" goto gotExampleClass
  echo %USAGE%
  goto cleanUp
:gotExampleClass

rem ----- Set Up The Runtime Classpath ----------------------------------------

if "%CLASSPATH%" == "" goto noClasspath
set CP=%RTK_HOME%\java\lib\xerces.jar;%RTK_HOME%\java\lib\regexp.jar;%RTK_HOME%\java\lib\log4j.jar;%RTK_HOME%\java\lib\bcprov-jdk14-115.jar;%RTK_HOME%\java\lib\epp-rtk-java.jar;%CLASSPATH%
goto gotClasspath
:noClasspath
set CP=%RTK_HOME%\java\lib\xerces.jar;%RTK_HOME%\java\lib\regexp.jar;%RTK_HOME%\java\lib\log4j.jar;%RTK_HOME%\java\lib\bcprov-jdk14-115.jar;%RTK_HOME%\java\lib\epp-rtk-java.jar;
:gotClasspath

rem ----- Run The Example -----------------------------------------------------

%JAVA_HOME%\bin\java -Dssl.props.location="%RTK_HOME%\java\ssl" -Drtk.props.file="%RTK_HOME%\java\etc\rtk.properties" -classpath "%CP%" com.tucows.oxrs.epp%EPP_VERSION%.rtk.example.%EXAMPLE_CLASS% %EPP_HOST% %EPP_PORT% %CLIENT_ID% %PASSWORD% %DOMAIN% %REGISTRANT_CONTACT% %TECH_CONTACT%

rem ----- Clean Up Environment Variables --------------------------------------

:cleanup

