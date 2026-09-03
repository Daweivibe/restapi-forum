# 1. Java 25 JDK als Basis-Image nutzen
FROM eclipse-temurin:25-jdk-alpine

# 2. Arbeitsverzeichnis im Container festlegen
WORKDIR /app

# 3. Projektdateien in den Container kopieren
COPY . .

# 4. Maven-Wrapper ausführbar machen und das Projekt bauen
RUN chmod +x mvnw && ./mvnw clean package -DskipTests

# 5. Port freigeben
EXPOSE 8080

# 6. Anwendung starten
CMD ["java", "-jar", "target/restapi-0.0.1-SNAPSHOT.jar"]