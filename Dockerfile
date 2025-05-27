# Usar una imagen base de Maven con JDK 17 para compilar el proyecto
FROM maven:3.8.3-openjdk-17-slim as builder

# Establecer las variables de entorno para Maven
ENV MAVEN_HOME /usr/share/maven
ENV M2_HOME /usr/share/maven
ENV PATH $M2_HOME/bin:$PATH

# Configurar el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copiar todos los archivos del proyecto al contenedor
COPY . .

# Verificar la existencia del archivo mvnw (para asegurarnos de que está ahí)
RUN ls -l ./mvnw

# Cambiar permisos de ejecución al archivo mvnw
RUN chmod +x ./mvnw

# Limpiar la caché de Maven (si es necesario)
RUN rm -rf ~/.m2/repository/*

# Ejecutar Maven para compilar el proyecto
RUN mvn clean install  # Usar Maven directamente en lugar de mvnw

# Usar una imagen base de OpenJDK 17
FROM openjdk:17-slim

# Copiar el archivo .jar generado desde el contenedor builder
COPY --from=builder /app/target/backend-sistem-0.0.1-SNAPSHOT.jar /app/backend-sistem-0.0.1-SNAPSHOT.jar

# Comando para ejecutar la aplicación Java
CMD ["java", "-jar", "/app/backend-sistem-0.0.1-SNAPSHOT.jar"]
