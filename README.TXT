Copyright (c) 2003 Afilias, Ltd. All rights reserved.


TABLE OF CONTENTS
==================
1. EPP RTK Java Overview
2. Quick Start
3. Environment
4. Building The Source
5. Documentation
6. How To Contribute


1. EPP RTK Java Overview
=========================
The EPP Registrar Toolkit (RTK) consists of EPP IDLs (Interface Definition
Language), a working Java Application Programming Interface (API) and
sample code that registrars can use to communicate with an EPP-compliant
registry (for example, the registries for .name, .info and .pro).

To use this package, you will require at least:
    + knowledge of EPP (Internet-Drafts 02)
    + a Java JRE (tested on v1.6.0)
    + a JSSE implementation (default from Sun available at 
      http://java.sun.com/products/jsse)
    + an SSL certificate recognized from an approved 
      Certificate Authority (such as Verisign or Thawte)

To make changes and compile this package, you will additionally require:
    + a Java SDK (tested on 1.4.2 and 1.6.0)
    + knowledge of XML

The RTK requires Xerces-J in order to run and to build.  The Xerces-J jar
is included in the lib directory.  Include this file in your CLASSPATH when
using the EPP RTK Java.  You may, optionally, download the full Xerces-J
package at http://xml.apache.org.  The Xerces version used in the RTK is 1.3.0.

Ant is required in order to build the RTK.  The Ant jar is included in the lib
directory and the build.[sh|bat] file is setup to read the Ant and Xerces jar
files in this directory.  You may, optionally, download the full Ant
distribution to build the RTK that is available at
http://jakarta.apache.org/ant.  If you do, you must override the ANT_HOME
environment variable to find the jar file in your distribution.  The version
of Ant being used in the development of the EPP RTK Java is 1.3.

The EPP RTK makes use of a set of IDLs based on EPP objects and actions that
were developed jointly by a group of EPP registrars.  These have been converted
to Java classes, which are meaningful to the Java RTK.  The resulting Java
source can be found in the src/org/openrtk/idl/epp directory.  These IDL
files and their use do not imply any guarantee of CORBA functionality in the
RTK.  The sole use of the IDL files is to follow the registry group's standard
interface in creating EPP-compliant Registrar Toolkits.  The versions of the
IDL files used in this release of the RTK are included in this package for
reference only.

Please see the full set of documentation included in the java/doc directory.

Javadocs can be viewed in the doc directory.


2. Quick Start
===============

Before using RTK, an SSL certificate recognized from an approved Certificate
Authority is required.
The certificate should replace the files in the $RTK_HOME/ssl directory.

Define the following environment variables:
export RTK_HOME=<path to epp-rtk>

To run RTK without modification, use script run_example.[sh|bat] to simplify 
the running of the examples provided in this package. The syntax is:

./run_example.sh SessionExample|ContactExample|DomainExample|HostExample \
    epp_host_name epp_host_port epp_client_id epp_password [domain_name] \
    [registrant_contact_roid] [tech_contact_roid]

The optional parameters are used by the SessionExample.  Actually, the 
domain_name becomes mandatory if using the SessionExample.

The script is useful because it also includes the setting of the 
ssl.props.location property, which is required for a secure connection
to the registry using JSSE.  Take a look at java/ssl/ssl.properties
and the method connect() to see how all of this hooks together.

Note: The protocol of choice in the sample is TCP with TLS (SSL).
To turn TLS off, you'll have to edit the samples and rebuild the RTK.


3. Environment
===============
The RTK and Xerces-J jar files are required to use the RTK.
To meet this requirement, include the jar files from the lib directory in
the CLASSPATH.

To use JSSE for TCP/TLS, you will require at least Sun's JSSE installed.  
Please see http://java.sun.com/products/jsse for more information on how
to install JSSE.

To use a different implementation of SSL in Java, you must have the necessary
packages installed.  Please see the Java API documentation and the Javadoc's
EPPClient.setSocketToEPPServer() for more information.


4. Building The Source
=======================
Development was done using JDK 1.6.

EPP RTK Java comes ready-to-use with a bundled jar file (compiled using 
JDK 1.6).  If you wish to modify the source code or compile against an older 
version of Java (such as 1.4.2), you will need to rebuild the jar file.

The cross-platform build tool Ant is used to build the EPP RTK Java.  Please
see http://jakarta.apache.org/ant for more information on Ant.

You do not need to download Ant in order to build the RTK, as the
necessary Ant jar file is included in the distribution. The Ant build files
are included in the etc directory.  Copy the build.* files from etc to the
main java directory (i.e. "cp etc/build.* .").

You can run Ant for the following targets:

    ./build.[sh|bat]                 - Build classes from source
    ./build.[sh|bat] targets         - Show the list of targets
    ./build.[sh|bat] all             - Clean and build dist
    ./build.[sh|bat] library         - Build classes from source (in build dir)
    ./build.[sh|bat] javadoc         - Build the Javadocs (in build dir)
    ./build.[sh|bat] dist            - Build everything and create a distribution
    ./build.[sh|bat] dist.nojavadoc  - Build everything but the javadocs
                                       and create a distribution
    ./build.[sh|bat] jar             - Build the jar file portion of the 
                                       distribution
    ./build.[sh|bat] clean           - Remove generated files and directories

build.[sh|bat] looks for Ant and Xerces jar files in the lib directory.
If you want to download full distributions of Ant and Xerces,
you will have to override the ANT_HOME and XERCES_HOME environment variables
to find the required jar files.

There's also a Makefile in java/etc.  It's included as a convenience
for those (like me) who get tires of calling "./build.sh dist".  A plain
old make calls "./build.sh dist.nojavadoc", also a little convenience
because the javadocs take the longest to build.  Because of my use
of Linux, I've tailored the Makefile for a unix-style environment.  If
someone wants to contribute a Makefile for Windows, I'd welcome it.


5. Documentation
=================
All of the Java API documenation needed are contained in the java/doc directory.
Javadocs are included in the java/api-doc directory.  Please also take a look
at the sample code provided.  The samples can be found in the 
src/com/tucows/oxrs/epp{version}/rtk/example directory.


6. How To Contribute
=====================

The EPP RTK is an Open Source project on Sourceforge.  You can access the
project at http://sourceforge.net/projects/epp-rtk
On this site you may download the latest version of the RTK, participate in
surveys, and sign up for the RTK mailing lists.

Contributors to the RTK are welcome.  The RTK needs people for ports to
other languages, bug fixes, enhancements, add-ons and documentation.

Stop by Sourceforge and say hi!

