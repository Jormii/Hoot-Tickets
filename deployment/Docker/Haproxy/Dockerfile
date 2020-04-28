# Imagen base
FROM haproxy

# Mover ficheros al contenedor
COPY ./haproxy.cfg /usr/local/etc/haproxy/
COPY ./xip.io.pem /etc/ssl/xip.io/

RUN mkdir -p /run/haproxy/
