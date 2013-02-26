#!/bin/bash

USAGE="usage: $0 rtk|0705|0604|0503|0402|02 SessionExample|ContactExample|ContactTransferExample|DomainExample|DomainTransferExample|HostExample epp_host_name epp_host_port epp_client_id epp_password domain_name [registrant_contact_id] [tech_contact_id]"

EPP_VERSION=$1
EXAMPLE_CLASS=$2
EPP_HOST=$3
EPP_PORT=$4
CLIENT_ID=$5
PASSWORD=$6
DOMAIN=$7
REGISTRANT_CONTACT=$8
TECH_CONTACT=$9

if [ "$RTK_HOME" = "" ] ; then
  echo You must set RTK_HOME to point to the RTK installation directory
  exit 1
fi

if [ "$EPP_VERSION" = "" ] ; then
  echo $USAGE
  exit 1
fi

if [ "$EXAMPLE_CLASS" = "" ] ; then
  echo $USAGE
  exit 1
fi

java -Dssl.props.location=$RTK_HOME/java/ssl \
     -Drtk.props.file=$RTK_HOME/java/etc/rtk.properties \
     -cp $RTK_HOME/java/lib/xerces.jar:$RTK_HOME/java/lib/epp-rtk-java.jar:$RTK_HOME/java/lib/regexp.jar:$RTK_HOME/java/lib/log4j.jar:$RTK_HOME/java/lib/bcprov-jdk14-115.jar \
     com.tucows.oxrs.epp$EPP_VERSION.rtk.example.$EXAMPLE_CLASS \
        $EPP_HOST $EPP_PORT $CLIENT_ID $PASSWORD \
        $DOMAIN $REGISTRANT_CONTACT $TECH_CONTACT
