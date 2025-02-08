# Usar una imagen base con Java
FROM eclipse-temurin:17-jdk-alpine

# Establecer el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copiar el archivo JAR generado en el contenedor
COPY target/comex-0.0.1-SNAPSHOT.jar app.jar

# Copiar los recursos al directorio correspondiente
COPY src/main/resources /app/resources

# Exponer el puerto en el que la aplicación corre
EXPOSE 8080

# Definir el comando de ejecución
CMD ["java", "-jar", "app.jar"]
