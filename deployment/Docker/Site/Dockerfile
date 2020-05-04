# Imagen base
FROM openjdk:8-jre

# Mover ficheros al contenedor
COPY ./Site.jar /usr/app/
WORKDIR /usr/app/

# Ejecutar aplicación
CMD ["java", "-jar", "./Site.jar"]
