# User Management API

A RESTful User Management API built with **Spring Boot** and **Java 21**, featuring full CRUD operations for users and a scaffolded tasks module.

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
| Database | H2 (in-memory) |
| ORM | Spring Data JPA / Hibernate |
| Migrations | Flyway |
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
DATABASE_URL=jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE
DATABASE_USERNAME=sa
DATABASE_PASSWORD=
```

> **Note:** The default configuration uses an H2 in-memory database. All data is lost when the application stops.

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
| JDBC URL | `jdbc:h2:mem:testdb` |
| Username | `sa` |
| Password | *(leave empty)* |

---

## API Reference

### Users

Base path: `/user`

#### GET `/user/welcome`

Health-check / welcome endpoint.

**Response `200 OK`**
```
this is the first message of this route
```

---

#### POST `/user/register`

Register a new user.

**Request body**
```json
{
  "name": "John Doe",
  "email": "john@example.com",
  "age": 30,
  "rank": "GOLD"
}
```

**Response `200 OK`**
```json
{
  "id": 1,
  "name": "John Doe",
  "email": "john@example.com",
  "age": 30,
  "rank": "GOLD",
  "task": null
}
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
    "task": null
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
  "task": null
}
```

---

#### PUT `/user/update/{id}`

Update an existing user.

> **Note:** Despite `{id}` appearing in the path, the `id` is read from the **query string** (`@RequestParam`). Call this endpoint as `/user/update/1?id=1`.

| Parameter | Type | Location | Description |
|-----------|------|----------|-------------|
| `id` | `Long` | Query string (`?id=`) | ID of the user to update |

**Request body**
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
  "task": null
}
```

---

#### DELETE `/user/delete/{id}`

Delete a user by ID.

| Parameter | Type | Description |
|-----------|------|-------------|
| `id` | `Long` | User ID (path variable) |

**Response `200 OK`** *(no body)*

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

## Database

The project uses an **H2 in-memory database** managed with **Flyway** migrations.

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
│   ├── java/dev/java10x/cadastroapi/
│   │   ├── UserManagementApplication.java   # Application entry point
│   │   ├── Users/
│   │   │   ├── Controller/UserController.java
│   │   │   ├── Entity/UserEntity.java
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
│       └── db/migrations/
│           └── V2__Add_rank_tb_users.sql
└── test/
    └── java/dev/java10x/cadastroapi/
        └── CadastroApiApplicationTests.java
```

---

## License

This project is licensed under the terms found in the [LICENSE](LICENSE) file.
