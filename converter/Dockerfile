# Stage 1: Build the application
# Use a base image with OpenJDK 17 (or appropriate version for your application)
FROM openjdk:17-jdk-alpine

# Install ImageMagick inside the container
RUN apt-get update && apt-get install -y imagemagick && rm -rf /var/lib/apt/lists/*

# Create a directory to hold the app's jar file
WORKDIR /app

# Copy the Spring Boot jar file from your build into the container
COPY target/converter-0.0.1-SNAPSHOT.jar /app/app.jar

# Expose the port your Spring Boot app runs on (default is 8080)
EXPOSE 8080

# Run the jar file when the container starts

ENTRYPOINT ["java", "-Xmx512m", "-jar", "/app/app.jar"]

