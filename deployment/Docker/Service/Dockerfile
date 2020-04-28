# Imagen base
FROM openjdk:8-jre

# Mover ficheros al contenedor
COPY ./Service.jar /usr/app/
WORKDIR /usr/app/

# Instalar RabbitMQ
RUN wget -O- https://www.rabbitmq.com/rabbitmq-release-signing-key.asc | apt-key add -
RUN apt-get update
RUN apt-get install -y rabbitmq-server

# Ejecutar aplicación
CMD ["java", "-jar", "./Service.jar"]
