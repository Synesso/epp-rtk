#!/bin/bash

if [ "$1" = "" ]; then
    KEY_PEM=key.pem
else
    KEY_PEM=$1
fi

if [ "$2" = "" ]; then
    CERT_PEM=cert.pem
else
    CERT_PEM=$2
fi

if [ "$3" = "" ]; then
    CA_CERT_PEM=
else
    CA_CERT_PEM=$3
fi

if [ "$4" = "" ]; then
    CHAIN_CERT_PEM=
else
    CHAIN_CERT_PEM=$4
fi

if [ ! -f "$KEY_PEM" ]; then
    echo "ERR: key PEM file not found: $KEY_PEM"
    exit 1
fi

if [ ! -f "$CERT_PEM" ]; then
    echo "ERR: cert PEM file not found: $CERT_PEM"
    exit 1
fi

if [ "$CA_CERT_PEM" != "" -a ! -f "$CA_CERT_PEM" ]; then
    echo "ERR: cacert PEM file not found: $CA_CERT_PEM"
    exit 1
fi

if [ "$CHAIN_CERT_PEM" != "" -a ! -f "$CHAIN_CERT_PEM" ]; then
    echo "ERR: chaincert PEM file not found: $CHAIN_CERT_PEM"
    exit 1
fi

cat $KEY_PEM $CERT_PEM $CHAIN_CERT_PEM $CA_CERT_PEM | openssl pkcs12 -export -out epp_rtk_keystore.p12

#openssl pkcs12 -export -out epp_rtk_keystore.p12 -inkey key.pem -in cert.pem -name "mycert" -caname "mycacert"
#openssl pkcs12 -info -in epp_rtk_keystore.p12
