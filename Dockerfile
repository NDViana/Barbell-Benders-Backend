FROM eclipse-temurin:17-jdk

WORKDIR /app

COPY . /app

RUN ./mvnw clean install

CMD ["java", "-jar", "target/BarbellBenders-0.0.1-SNAPSHOT.jar"]
