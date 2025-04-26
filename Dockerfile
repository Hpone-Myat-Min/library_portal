# Use a lightweight OpenJDK base image
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the built JAR file into the container
COPY target/library_portal-0.0.1-SNAPSHOT.jar library_portal.jar

# Expose port 8080
EXPOSE 8082

# Run the Spring Boot app
ENTRYPOINT ["java", "-jar", "library_portal.jar"]