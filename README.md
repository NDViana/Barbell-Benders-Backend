# Barbell-Benders-Backend

**Barbell Benders** is a workout tracking backend built with Spring Boot, JWT-based authentication, and PostgreSQL. It provides a RESTful API to register users, log in, and manage workouts (CRUD).

## Features

- User registration and login with JWT authentication
- Secure endpoints with role-based access control
- Full CRUD support for workouts
- Swagger UI for API documentation
- PostgreSQL database integration
- Modular project structure (Controller, Service, Repository)

## Tech Stack

- Java 17
- Spring Boot 3.5.0
- Spring Security + JWT
- Spring Data JPA
- PostgreSQL
- Swagger / OpenAPI
- Maven

## Live Demo

- Swagger Docs: [https://barbell-benders-backend.onrender.com/swagger-ui/index.html](https://barbell-benders-backend.onrender.com/swagger-ui/index.html)

## Running Locally

1. **Clone the repository**
```bash
git clone https://github.com/NDViana/Barbell-Benders-Backend.git
cd Barbell-Benders-Backend
```
2. **Edit application.properties**
```
src/main/resources/application.properties
target/classes/application.properties
```

Add the following (with your own database credentials):

```properties
spring.application.name=BarbellBenders

# Database Configuration
spring.datasource.url=jdbc:postgresql://localhost:5432/barbellbenders
spring.datasource.username=your_postgres_username
spring.datasource.password=your_postgres_password
spring.datasource.driver-class-name=org.postgresql.Driver

# JPA & Hibernate
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.properties.hibernate.format_sql=true
```
3. **Run the application**
```bash
./mvnw spring-boot:run
```
4. **Open Swagger UI**
```bash
http://localhost:8080/swagger-ui/index.html
```

From here, you can test endpoints directly. Here's how to use key features:

### Register and Login

1. **Register a new user**  
   Use the `POST /api/auth/register` endpoint. Click "Try it out," enter a JSON like:

   ```json
   {
     "username": "testuser",
     "password": "securePassword",
     "email": "test@example.com"
   }
   ```

2. **Login**  
   Use `POST /api/auth/login` to get a JWT token. Paste:

   ```json
   {
     "username": "testuser",
     "password": "securePassword"
   }
   ```

   Copy the returned `token`.

### Authorize with JWT

1. Click the **"Authorize"** button at the top right.
2. In the `bearerAuth` field, type your token in the value section
3. Click **"Authorize"** and then **"Close"**.

Now all protected endpoints will be accessible with your JWT.

### Manage Workouts

- `POST /api/workouts/addWorkout` — Add a workout
- `GET /api/workouts/myWorkouts` — View your workouts
- `GET /api/workouts/{id}` — Get a specific workout
- `PUT /api/workouts/{id}` — Update a workout
- `DELETE /api/workouts/{id}` — Delete a workout

Click “Try it out” on any of these to test with JSON input and your JWT.


## License

This project is licensed under the MIT License. See the [LICENSE](./LICENSE) file for details.


