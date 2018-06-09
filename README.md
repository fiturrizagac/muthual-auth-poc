Pages

https://blog.codeship.com/how-to-set-up-mutual-tls-authentication/

https://www.javacodegeeks.com/2016/03/set-mutual-tls-authentication.html

https://dzone.com/articles/mutual-problems

Password

ca.key -> topsecretcakey
client.p12 -> topsecretclientp12

Commands

docker run --name local-nginx -v /Users/fiturrizaga/devenv/source-code/pocs/belatrix/mutual-auth/certs/nginx/tls.default.conf:/etc/nginx/conf.d/default.conf -v /Users/fiturrizaga/devenv/source-code/pocs/belatrix/mutual-auth/certs/nginx/server/client-ssl.fraber.com.crt:/opt/client-ssl.fraber.com.crt -v /Users/fiturrizaga/devenv/source-code/pocs/belatrix/mutual-auth/certs/nginx/server/client-ssl.fraber.com.key:/opt/client-ssl.fraber.com.key -p 443:443 nginx

docker run --name local-nginx -v /Users/fiturrizaga/devenv/source-code/pocs/belatrix/mutual-auth/certs/nginx/mutual.tls.default.conf:/etc/nginx/conf.d/default.conf -v /Users/fiturrizaga/devenv/source-code/pocs/belatrix/mutual-auth/certs/nginx/server/client-ssl.fraber.com.crt:/opt/client-ssl.fraber.com.crt -v /Users/fiturrizaga/devenv/source-code/pocs/belatrix/mutual-auth/certs/nginx/server/client-ssl.fraber.com.key:/opt/client-ssl.fraber.com.key -v /Users/fiturrizaga/devenv/source-code/pocs/belatrix/mutual-auth/certs/nginx/ca/ca.crt:/opt/ca.crt -p 443:443 nginx