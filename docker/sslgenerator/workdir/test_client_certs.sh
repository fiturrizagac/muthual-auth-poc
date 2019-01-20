#!/bin/sh

curl -vvv \
  -k \
  --cacert certificates/server/caserver.crt \
  --key certificates/client/caclient.key \
  --cert certificates/client/caclient.crt \
  https://localhost