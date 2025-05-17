# Usa una base con Java (runtime)
FROM openjdk:17-jdk-slim

# Directory di lavoro dentro il container
WORKDIR /app

# Copia il JAR compilato nel container
COPY target/demo-0.0.1-SNAPSHOT.jar app.jar

# Espone la porta dell'app Spring Boot (modifica se usi un'altra)
EXPOSE 8080

# Comando di esecuzione
ENTRYPOINT ["java", "-jar", "app.jar"]