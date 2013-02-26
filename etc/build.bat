@echo off
rem ---------------------------------------------------------------------------
rem build.bat -- Build Script for the "Struts" Toolkit
rem
rem Supported Environment Variables (default values in square brackets):
rem
rem   JAVA_HOME             Java Development Kit directory. [REQUIRED]
rem
rem   ANT_JAR               Distribution directory for "jakarta-ant".
rem                         [.\lib]
rem
rem   REGEXP_JAR            Distribution directory for "jakarta-regexp".
rem                         [.\lib]
rem
rem   ANT_OPTS              Command line options to pass to the JVM when
rem                         executing Ant. [none]
rem
rem   XERCES_HOME           Distribution directory for the Xerces XML parser
rem                         [.\lib]
rem
rem   LOG4J_HOME            Distribution directory for the Log4j logging provider
rem                         [.\lib]
rem
rem $Id: build.bat,v 1.6 2003/04/28 15:19:00 tubadanm Exp $
rem ---------------------------------------------------------------------------


rem ----- Save Environment Variables That May Change --------------------------

set _ANT_JAR=%ANT_HOME%
set _ANT_OPTS=%ANT_OPTS%
set _REGEXP_HOME=%REGEXP_HOME%
set _XERCES_HOME=%XERCES_HOME%
set _LOG4J_HOME=%LOG4J_HOME%
set _BOUNCY_HOME=%BOUNCY_HOME%


rem ----- Verify and Set Required Environment Variables -----------------------

if not "%JAVA_HOME%" == "" goto gotJavaHome
  echo You must set JAVA_HOME to point at your Java Development Kit directory
  goto cleanUp
:gotJavaHome

if not "%ANT_JAR%" == "" goto gotAntHome
set ANT_JAR=.\lib
:gotAntHome

if not "%REGEXP_JAR%" == "" goto gotRegExpHome
set REGEXP_JAR=.\lib
:gotRegExpHome

if not "%XERCES_HOME%" == "" goto gotXercesHome
set XERCES_HOME=.\lib
:gotXercesHome

if not "%LOG4J_HOME%" == "" goto gotLog4jHome
set LOG4J_HOME=.\lib
:gotLog4jHome

if not "%BOUNCY_HOME%" == "" goto gotBouncyHome
set BOUNCY_HOME=.\lib
:gotBouncyHome

rem ----- Set Up The Runtime Classpath ----------------------------------------

if "%CLASSPATH%" == "" goto noClasspath
set CP=%ANT_JAR%\ant.jar;%REGEXP_JAR%\regexp.jar;%XERCES_HOME%\xerces.jar;%LOG4J_HOME%\log4j.jar;%BOUNCY_HOME%\bcprov-jdk14-115.jar;%JAVA_HOME%\lib\tools.jar;%CLASSPATH%
goto gotClasspath
:noClasspath
set CP=%ANT_JAR%\ant.jar;%REGEXP_JAR%\regexp.jar;%XERCES_HOME%\xerces.jar;%LOG4J_HOME%\log4j.jar;%BOUNCY_HOME%\bcprov-jdk14-115.jar;%JAVA_HOME%\lib\tools.jar;
:gotClasspath


rem ----- Execute The Requested Build -----------------------------------------

%JAVA_HOME%\bin\java %ANT_OPTS% -classpath "%CP%" org.apache.tools.ant.Main -Dant.home=%ANT_JAR% %1 %2 %3 %4 %5 %6 %7 %8 %9


rem ----- Clean Up Environment Variables --------------------------------------

:cleanup

set ANT_JAR=%_ANT_HOME%
set REGEXP_JAR=%_REGEXP_HOME%
set ANT_OPTS=%_ANT_OPTS%
set XERCES_HOME=%_XERCES_HOME%
set LOG4J_HOME=%_LOG4J_HOME%
set BOUNCY_HOME=%_BOUNCY_HOME%

set _ANT_JAR=
set _REGEXP_JAR=
set _ANT_OPTS=
set _XERCES_HOME=
set _LOG4J_HOME=
set _BOUNCY_HOME=

