# 📋 Task Manager API

A secure, containerized RESTful API backend for task tracking and management. Built using **Spring Boot**, **Spring Security**, **JWT (JSON Web Token)**, **MySQL**, and **Docker**.

<div align="center">
  <img src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white" alt="Java 17" />
  <img src="https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white" alt="Spring Boot 3" />
  <img src="https://img.shields.io/badge/Spring_Security-6DB33F?style=for-the-badge&logo=springsecurity&logoColor=white" alt="Spring Security" />
  <img src="https://img.shields.io/badge/JWT-000000?style=for-the-badge&logo=jsonwebtokens&logoColor=white" alt="JWT" />
  <img src="https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=mysql&logoColor=white" alt="MySQL" />
  <img src="https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=docker&logoColor=white" alt="Docker" />
</div>

---

## ✨ Features

*   **Secure User Authentication:** JWT (JSON Web Token) authentication with custom filters, stateless session management, and password hashing using BCrypt.
*   **Secure API Endpoints:** Public access is limited only to authentication endpoints (`/auth/**`) and H2 console; task endpoints are fully protected.
*   **Full CRUD Capabilities:** Manage tasks (create, view, update, delete) associated with users, with input data validation.
*   **Layered Architecture:** Follows the clean MVC standard (Controller, Service, Repository, Model/Entity).
*   **Database Integration:** Supports **MySQL** for production environments and **H2 database** for development/testing.
*   **Dockerized Deployment:** Features a **multi-stage Docker build** for optimized, lightweight containers.

---

## 🛠️ Tech Stack

*   **Backend:** Java 17, Spring Boot, Spring Security, JWT (jjwt)
*   **Persistence:** Spring Data JPA, Hibernate, MySQL, H2 (for development)
*   **DevOps & Tools:** Docker, Maven, Postman

---

## 📂 Project Structure

```text
├── src/main/java/com/dattatreya/taskmanager/
│   ├── controller/         # REST Controllers (Auth & Task CRUD)
│   ├── model/              # Database Entities (User & Task)
│   ├── repository/         # JPA Repositories (Database access interfaces)
│   ├── security/           # JWT Utilities, JWT Filter, & Spring Security Configuration
│   ├── service/            # Business logic orchestration
│   └── TaskmanagerApplication.java
│
├── Dockerfile              # Multi-stage Docker build config
└── pom.xml                 # Maven dependency file
```

---

## 🚀 Getting Started

### Option 1: Running locally with Maven
1.  **Configure Database:**  
    Create a MySQL database named `task_manager`:
    ```sql
    CREATE DATABASE task_manager;
    ```
    If your local MySQL username/password is different than `root`/`root`, update `src/main/resources/application.properties`.

2.  **Compile & Run:**
    ```bash
    ./mvnw spring-boot:run
    ```
    The server will start on `http://localhost:8080`.

---

### Option 2: Running with Docker (Containerized)
1.  **Build the Docker Image:**  
    This uses a multi-stage build to package the application with Maven and run it inside a lightweight JRE environment.
    ```bash
    docker build -t task-manager-api .
    ```

2.  **Run the Container:**
    ```bash
    docker run -p 8080:8080 task-manager-api
    ```

---

## 🔒 API Specifications & Endpoints

### 🔐 Authentication (Public)
*   `POST /auth/register` - Register a new user account.
*   `POST /auth/login` - Authenticate credentials and get a JWT bearer token.
    *   *Payload:*
        ```json
        {
          "username": "dattatreya",
          "password": "securepassword"
        }
        ```

### 📋 Task Management (Protected - requires header: `Authorization: Bearer <JWT_TOKEN>`)
*   `GET /tasks` - Retrieve all tasks for the authenticated user.
*   `GET /tasks/{id}` - Retrieve a specific task by ID.
*   `POST /tasks` - Create a new task.
    *   *Payload:*
        ```json
        {
          "title": "Complete Project README",
          "description": "Add Security and Docker sections",
          "completed": false
        }
        ```
*   `PUT /tasks/{id}` - Update details or status of a task.
*   `DELETE /tasks/{id}` - Delete a task.
