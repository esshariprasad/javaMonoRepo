# Start from JDK 21 base image
FROM openjdk:21-jdk-slim

WORKDIR /app

# Copy the JAR built by Maven
COPY target/user-service-*.jar app.jar

# Expose service port
EXPOSE 8081

# Command to run the jar
ENTRYPOINT ["java", "-jar", "app.jar"]
