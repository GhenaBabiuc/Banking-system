FROM openjdk:23-jdk-slim

WORKDIR /app

COPY pom.xml .
COPY src ./src

COPY target/Banking-system-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
