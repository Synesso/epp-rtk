#! /bin/sh
# -----------------------------------------------------------------------------
# build.sh -- Build Script for the "Struts" Toolkit
#
# Supported Environment Variables (default values in square brackets):
#
#   JAVA_HOME             Java Development Kit directory. [REQUIRED]
#
#   ANT_JAR               Location of the Ant jar file.
#                         [./lib]
#
#   REGEXP_JAR            Location of the Jakarta Regexp jar file.
#                         [./lib]
#
#   ANT_OPTS              Command line options to pass to the JVM when
#                         executing Ant. [none]
#
#   XERCES_HOME           Location of the Xerces jar file.
#                         [./lib]
#
#   LOG4J_HOME            Location of the Log4j jar file.
#                         [./lib]
#
# $Id: build.sh,v 1.5 2003/04/28 15:19:01 tubadanm Exp $
# -----------------------------------------------------------------------------


# ----- Verify and Set Required Environment Variables -------------------------

if [ "$JAVA_HOME" = "" ] ; then
  echo You must set JAVA_HOME to point at your Java Development Kit directory
  exit 1
fi


if [ "$ANT_JAR" = "" ] ; then
  ANT_JAR=./lib
fi

if [ "$REGEXP_JAR" = "" ] ; then
  REGEXP_JAR=./lib
fi

if [ "$ANT_OPTS" = "" ] ; then
  ANT_OPTS=""
fi

if [ "$XERCES_HOME" = "" ] ; then
  XERCES_HOME=./lib
fi

if [ "$LOG4J_HOME" = "" ] ; then
  LOG4J_HOME=./lib
fi

if [ "$BOUNCY_HOME" = "" ] ; then
  BOUNCY_HOME=./lib
fi

# ----- Set Up The Runtime Classpath ------------------------------------------

if [ "$CLASSPATH" = "" ] ; then
  CP=$ANT_JAR/ant.jar:$REGEXP_JAR/regexp.jar:$XERCES_HOME/xerces.jar:$LOG4J_HOME/log4j.jar:$BOUNCY_HOME/bcprov-jdk14-115.jar:$JAVA_HOME/lib/tools.jar
else
  CP=$ANT_JAR/ant.jar:$REGEXP_JAR/regexp.jar:$XERCES_HOME/xerces.jar:$LOG4J_HOME/log4j.jar:$BOUNCY_HOME/bcprov-jdk14-115.jar:$JAVA_HOME/lib/tools.jar:$CLASSPATH
fi

# ----- Execute The Requested Build -------------------------------------------

java $ANT_OPTS -classpath "$CP" org.apache.tools.ant.Main \
 -Dant.home=$ANT_JAR \
 "$@"
