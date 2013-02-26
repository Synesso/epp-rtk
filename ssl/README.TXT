This file contains a few tips and tricks collected from the
SF epp-rtk community.... feel free to contribute to this list...


Creating a JSSE keystore with OpenSSL
=====================================
[ thanks to wessorh for this tip ]

    To create a keystore, readable by the JSSE, that contains a registrar's
    current certs used with NSI do the following on your favorite unix box
    with openSSL 0.9.6a installed.

    example% cat client-key.pem client-cert.pem ca-cert.pem | openssl pkcs12 \
     -export -out keystore.pkcs12 -noiter -nomaciter -name my_cert_name

    the keystore.pkcs12 can be read by any java based keytool, dont forget
    the -storetype pkcs12 option...


Using BouncyCastle and PKCS12 keystores
=======================================
[ based on the tip above; thanks to fotsoft
  for the easy-to-use scripts ]


    Included in this directory is a Unix Bash script to make the creation
    of OpenSSL PKCS12 keystore relatively easy.  It takes a private key,
    public certificate, optional CA certificate and optional chaining
    certificate (linking the public cert with the CA).  The CA Cert is
    usually mandatory (unless your public cert was self-signed, which
    usually is not accept by most domain name registries).

    To create a keystore for use by the Java RTK, you run (from this 
    directory): 

        ./epp_rtk_keystore.sh <key.pem> <cert.pem> [<cacert.pem> [<chaincert.pem>]]

        {you will be prompted for this:}
        Enter Export Password: {enter-your-keystore-password-here}
        Verifying password - Enter Export Password: {reenter-your-keystore-password-here}

    Now in the current directory, you will see the file (epp_rtk_keystore.p12).

    To view the contents of this keystore, you run (from this directory):

        ./epp_rtk_keystore_info.sh

        Enter Import Password: <enter-your-keystore-password-here>

        .
        .
        .


        Enter PEM pass phrase: {enter-your-key-password-here} (created at the time 
        you requested your paid for cert)
        Verifying password - Enter PEM pass phrase: <enter-your-key-password-here>

    The ssl.properties file is already setup for use of the sample key and self-signed
    certificate provided.  The RTK uses the BouncyCastle encryption provider to read the
    keystore.  BouncyCastle is not used for SSL encryption.  When creating your own keystore,
    be sure to change the passwords set in the ssl.properties file.



==========================

If none of these tip work for you, please join the epp-rtk-devel list
on Sourceforge and send your problem there.  Chances are that one of use
has seen the problem before.

