# User Management API

A RESTful User Management API built with **Spring Boot** and **Java 21**, featuring full CRUD operations for users, a Thymeleaf-powered web UI, and a scaffolded tasks module.

---

## Table of Contents

- [Tech Stack](#tech-stack)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Configuration](#configuration)
- [Running the Application](#running-the-application)
- [API Reference](#api-reference)
  - [Users](#users)
  - [Tasks](#tasks)
- [Web UI](#web-ui)
- [API Documentation (Swagger)](#api-documentation-swagger)
- [Database](#database)
- [Testing](#testing)
- [Project Structure](#project-structure)
- [License](#license)

---

## Tech Stack

| Layer | Technology |
|-------|-----------|
| Language | Java 21 |
| Framework | Spring Boot 4.0.3 |
| Build Tool | Maven |
| Database | H2 (file-based, testing — to be replaced) |
| ORM | Spring Data JPA / Hibernate |
| Migrations | Flyway |
| Template Engine | Thymeleaf |
| API Docs | SpringDoc OpenAPI / Swagger UI |
| Utilities | Lombok |

---

## Prerequisites

- **Java 21** or higher — [Download](https://adoptium.net/)
- **Maven 3.6+** — [Download](https://maven.apache.org/download.cgi) *(or use the included `mvnw` wrapper)*

---

## Installation

```bash
# Clone the repository
git clone https://github.com/diegocorteL33T/user-management-api.git
cd user-management-api

# Download dependencies
./mvnw dependency:resolve
```

---

## Configuration

The application requires the following environment variables. Create a `.env` file or export them in your shell:

```env
DATABASE_URL=jdbc:h2:file:./data/testdb;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE
DATABASE_USERNAME=sa
DATABASE_PASSWORD=
```

> **Note:** The current configuration uses a file-based H2 database for testing purposes. The database file persists between restarts but will be replaced with a production-grade database in a future update.

The full Spring Boot configuration is in `src/main/resources/application.properties`:

```properties
spring.datasource.url=${DATABASE_URL}
spring.datasource.driver-class-name=org.h2.Driver
spring.datasource.username=${DATABASE_USERNAME}
spring.datasource.password=${DATABASE_PASSWORD}

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.h2.console.enabled=true

spring.flyway.enabled=true
spring.flyway.locations=classpath:db/migrations
spring.flyway.baseline-on-migrate=true
```

---

## Running the Application

```bash
# Using the Maven wrapper
./mvnw spring-boot:run

# OR build a JAR and run it
./mvnw package
java -jar target/CadastroAPI-0.0.1-SNAPSHOT.jar
```

The server starts on **http://localhost:8080**.

### H2 Console

A web-based database console is available at **http://localhost:8080/h2-console** while the application is running.

| Field | Value |
|-------|-------|
| JDBC URL | `jdbc:h2:file:./data/testdb` |
| Username | `sa` |
| Password | *(leave empty)* |

---

## API Reference

### Users

Base path: `/user`

#### POST `/user/register`

Register a new user.

**Request body**
```json
{
  "name": "John Doe",
  "email": "john@example.com",
  "age": 30,
  "rank": "GOLD",
  "profile_picture": "https://example.com/photo.jpg"
}
```

**Response `201 Created`**
```
User created successfully: John Doe
```

---

#### GET `/user/list`

Retrieve all registered users.

**Response `200 OK`**
```json
[
  {
    "id": 1,
    "name": "John Doe",
    "email": "john@example.com",
    "age": 30,
    "rank": "GOLD",
    "task": null,
    "profile_picture": "https://example.com/photo.jpg"
  }
]
```

---

#### GET `/user/list/{id}`

Retrieve a single user by ID.

| Parameter | Type | Description |
|-----------|------|-------------|
| `id` | `Long` | User ID (path variable) |

**Response `200 OK`**
```json
{
  "id": 1,
  "name": "John Doe",
  "email": "john@example.com",
  "age": 30,
  "rank": "GOLD",
  "task": null,
  "profile_picture": "https://example.com/photo.jpg"
}
```

**Response `404 Not Found`**
```
User not found with id 1
```

---

#### PUT `/user/update/{id}`

Update an existing user by ID (path variable).

| Parameter | Type | Location | Description |
|-----------|------|----------|-------------|
| `id` | `Long` | Path (`/{id}`) | ID of the user to update |

**Request body** *(only include fields you want to update)*
```json
{
  "name": "John Updated",
  "email": "john.updated@example.com",
  "age": 31,
  "rank": "PLATINUM"
}
```

**Response `200 OK`**
```json
{
  "id": 1,
  "name": "John Updated",
  "email": "john.updated@example.com",
  "age": 31,
  "rank": "PLATINUM",
  "task": null,
  "profile_picture": "https://example.com/photo.jpg"
}
```

**Response `404 Not Found`**
```
User not found with id 1
```

---

#### DELETE `/user/delete/{id}`

Delete a user by ID.

| Parameter | Type | Description |
|-----------|------|-------------|
| `id` | `Long` | User ID (path variable) |

**Response `200 OK`**
```
User deleted successfully
```

**Response `404 Not Found`**
```
User not found with id 1
```

---

### Tasks

Base path: `/task`

> ⚠️ **Work in progress.** Task endpoints are scaffolded and return placeholder responses. Full implementation is pending.

| Method | Endpoint | Description |
|--------|----------|-------------|
| `POST` | `/task/create` | Create a task |
| `GET` | `/task/all` | List all tasks |
| `PUT` | `/task/update` | Update a task |
| `DELETE` | `/task/delete` | Delete a task |

---

## Web UI

A Thymeleaf-powered web interface is available alongside the REST API.

Base path: `/user/ui`

| Method | Endpoint | Description |
|--------|----------|-------------|
| `GET` | `/user/ui/list` | Show all users in an HTML table |
| `GET` | `/user/ui/{id}` | Show details for a specific user |
| `GET` | `/user/ui/update/{id}` | Show the update form for a user |
| `PUT` | `/user/ui/update/{id}` | Submit the update form |
| `DELETE` | `/user/ui/delete/{id}` | Delete a user and redirect to the list |

---

## API Documentation (Swagger)

Interactive API documentation is provided by **SpringDoc OpenAPI** and is available at:

**http://localhost:8080/swagger-ui/index.html**

All user endpoints include summary, description, and response code annotations.

---

## Database

The project currently uses a **file-based H2 database** (for testing) managed with **Flyway** migrations. This database will be replaced with a production-grade database in a future update.

### Schema

**`tb_users`**

| Column | Type | Constraints |
|--------|------|-------------|
| `id` | BIGINT | Primary Key, Auto-increment |
| `name` | VARCHAR | NOT NULL |
| `email` | VARCHAR | NOT NULL, UNIQUE |
| `age` | INT | NOT NULL |
| `rank` | VARCHAR | — |
| `task_id` | BIGINT | Foreign Key → `tb_tasks(id)` |
| `profile_picture` | VARCHAR | — |

**`tb_tasks`**

| Column | Type | Constraints |
|--------|------|-------------|
| `id` | BIGINT | Primary Key, Auto-increment |
| `name` | VARCHAR | NOT NULL |
| `difficulty` | VARCHAR | NOT NULL |

### Relationships

- A **User** belongs to one **Task** (`@ManyToOne`)
- A **Task** can be assigned to many **Users**

### Migrations

Flyway migration scripts are located in `src/main/resources/db/migrations/`.

| Version | Description |
|---------|-------------|
| `V2` | Add `rank` column to `tb_users` |
| `V3` | Add `profile_picture` column to `tb_users` |

---

## Testing

```bash
./mvnw test
```

Tests use **JUnit 5** via the Spring Boot test framework. The test suite currently includes a Spring context loading test.

---

## Project Structure

```
src/
└── main/
│   ├── java/dev/java10x/usermanagementapi/
│   │   ├── UserManagementApplication.java   # Application entry point
│   │   ├── Users/
│   │   │   ├── Controller/UserController.java
│   │   │   ├── ControllerUi/UserControllerUi.java
│   │   │   ├── DTO/UserDTO.java
│   │   │   ├── Entity/UserEntity.java
│   │   │   ├── Mapper/UserMapper.java
│   │   │   ├── Service/UserService.java
│   │   │   └── Repository/UserRepository.java
│   │   └── Tasks/
│   │       ├── Controller/TaskController.java
│   │       ├── Entity/TaskEntity.java
│   │       ├── Enums/TaskDifficulty.java
│   │       ├── Service/TaskService.java
│   │       └── Repository/TaskRepository.java
│   └── resources/
│       ├── application.properties
│       ├── templates/
│       │   ├── showUsers.html
│       │   ├── UserDetails.html
│       │   └── UpdateUser.html
│       └── db/migrations/
│           ├── V2__Add_rank_tb_users.sql
│           └── V3__Add_picture_tb_users.sql
└── test/
    └── java/dev/java10x/usermanagementapi/
        └── CadastroApiApplicationTests.java
```

---

## License

This project is licensed under the terms found in the [LICENSE](LICENSE) file.
