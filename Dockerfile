# Usar una imagen base de Maven para compilar el proyecto
FROM maven:3.8.1-jdk-11-slim as builder

# Configurar las variables de entorno para Maven
ENV MAVEN_HOME /usr/share/maven
ENV M2_HOME /usr/share/maven
ENV PATH $M2_HOME/bin:$PATH

# Configurar el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copiar todos los archivos del proyecto al contenedor
COPY . .

# Limpiar la caché de Maven
RUN rm -rf /root/.m2/repository/*
# Cambiar permisos de ejecución al archivo mvnw
RUN chmod +x ./mvnw

# Ejecutar Maven para compilar el proyecto
RUN ./mvnw clean install

# Usar una imagen base de OpenJDK para ejecutar la aplicación
FROM openjdk:11-jre-slim

# Copiar el archivo .jar generado desde el contenedor builder
COPY --from=builder /app/target/backend-sistem-0.0.1-SNAPSHOT.jar /app/backend-sistem-0.0.1-SNAPSHOT.jar

# Comando para ejecutar la aplicación Java
CMD ["java", "-jar", "/app/backend-sistem-0.0.1-SNAPSHOT.jar"]
