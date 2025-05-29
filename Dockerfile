FROM eclipse-temurin:17-jdk

WORKDIR /app

COPY . /app

# Give execute permission to the Maven wrapper
RUN chmod +x mvnw

# Then run the build
RUN ./mvnw clean package -DskipTests

# Run the Spring Boot application
CMD ["java", "-jar", "target/BarbellBenders-0.0.1-SNAPSHOT.jar"]
