#!/bin/sh

mkdir -p certificates/server certificates/client

echo " ---> Generate Root CA Server private key"
openssl genrsa \
    -out "certificates/server/caserver.key" \
    4096

echo " ---> Generate Root CA Server certificate request"
openssl req \
    -new \
    -key "certificates/server/caserver.key" \
    -out "certificates/server/caserver.csr" \
    -subj "/C=PE/ST=Lima/L=Lima/CN=mynginx"

echo " ---> Generate self-signed Root CA Server certificate"
openssl req \
    -x509 \
    -key "certificates/server/caserver.key" \
    -in "certificates/server/caserver.csr" \
    -out "certificates/server/caserver.crt" \
    -days 365

echo " ---> Generate Root CA Client private key"
openssl genrsa \
    -out "certificates/client/caclient.key" \
    4096

echo " ---> Generate Root CA Client certificate request"
openssl req \
    -new \
    -key "certificates/client/caclient.key" \
    -out "certificates/client/caclient.csr" \
    -subj "/C=PE/ST=Lima/L=Lima/CN=myclient"

echo " ---> Generate self-signed Root CA Client certificate"
openssl req \
    -x509 \
    -key "certificates/client/caclient.key" \
    -in "certificates/client/caclient.csr" \
    -out "certificates/client/caclient.crt" \
    -days 365