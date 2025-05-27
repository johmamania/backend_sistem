# Usar una imagen base de Maven para compilar el proyecto
FROM maven:3.8.1-jdk-11-slim as builder

# Configurar el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copiar todos los archivos del proyecto al contenedor
COPY . .
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
