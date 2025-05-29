FROM eclipse-temurin:17-jdk

# Create working directory
WORKDIR /app

# Copy project files
COPY . /app

# Package the application (without running tests to speed up build)
RUN ./mvnw clean package -DskipTests

# Run the Spring Boot app using env variables
CMD ["java", "-jar", "target/BarbellBenders-0.0.1-SNAPSHOT.jar"]
