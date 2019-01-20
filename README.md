Password

ca.key -> topsecretcakey
clientV2.p12, client.p12 -> topsecretclientp12
client-ssl.fraber.com.jks -> topsecretjks

client.ca.key -> topsecretclientca



Commands

docker run --name local-nginx -v /Users/fiturrizaga/devenv/source-code/pocs/belatrix/mutual-auth/certs/nginx/tls.default.conf:/etc/nginx/conf.d/default.conf -v /Users/fiturrizaga/devenv/source-code/pocs/belatrix/mutual-auth/certs/nginx/server/client-ssl.fraber.com.crt:/opt/client-ssl.fraber.com.crt -v /Users/fiturrizaga/devenv/source-code/pocs/belatrix/mutual-auth/certs/nginx/server/client-ssl.fraber.com.key:/opt/client-ssl.fraber.com.key -p 443:443 nginx

docker run --name local-nginx -v /Users/fiturrizaga/devenv/source-code/pocs/belatrix/mutual-auth/certs/nginx/mutual.tls.default.conf:/etc/nginx/conf.d/default.conf -v /Users/fiturrizaga/devenv/source-code/pocs/belatrix/mutual-auth/certs/nginx/server/client-ssl.fraber.com.crt:/opt/client-ssl.fraber.com.crt -v /Users/fiturrizaga/devenv/source-code/pocs/belatrix/mutual-auth/certs/nginx/server/client-ssl.fraber.com.key:/opt/client-ssl.fraber.com.key -v /Users/fiturrizaga/devenv/source-code/pocs/belatrix/mutual-auth/certs/nginx/ca/ca.crt:/opt/ca.crt -p 443:443 nginx

cat your_domain_name.crt bundle.crt >> mybundle.crt