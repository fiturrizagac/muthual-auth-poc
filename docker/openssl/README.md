
## deben estar a nombre del usuario
chown +x frankieic:frankieic  caclient.crt caclient.key

openssl pkcs12 -export -out certificate.pfx -inkey caclient.key -in caclient.crt -passout pass:clientp12





chown rabbitmq:rabbitmq /etc/rabbitmq/ssl/* \
    && chmod 400 /etc/rabbitmq/ssl/*
    
RUN chown 999:999 /etc/rabbitmq/ssl/* \
    && chmod 400 /etc/rabbitmq/ssl/*