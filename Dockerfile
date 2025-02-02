# Use an official Java runtime as base image
FROM eclipse-temurin:17-jdk

# Set working directory inside the container
WORKDIR /app

# Copy project files to the container
COPY . .

# Build the application
RUN chmod +x mvnw

# Build the application
RUN ./mvnw clean package

# Expose the port your Spring Boot app runs on
EXPOSE 8080

# Run the application
CMD ["java", "-jar", "target/tracking-0.0.1-SNAPSHOT.jar"]
