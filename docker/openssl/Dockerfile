FROM alpine

WORKDIR /opt

RUN apk add --update openssl && \
    rm -rf /var/cache/apk/*

ENTRYPOINT ["sh","-c","./entrypoint.sh"]