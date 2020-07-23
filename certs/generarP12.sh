#!/bin/bash

# Generacion del keystore en PKCS#12
openssl pkcs12 -export -in certificado.crt -inkey certificado.key -name "dodo" -out certificado.p12

