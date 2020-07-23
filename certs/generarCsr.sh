#!/bin/bash


# Generacion de requerimiento del certificado en PKCS#10
openssl req -new -key certificado.key -out certificado.csr -config openssl.cnf
