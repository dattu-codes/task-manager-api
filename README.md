# Task Manager API (Spring Boot)

A RESTful backend API for managing tasks, built using **Java**, **Spring Boot**, and **MySQL**.
This project allows users to perform CRUD operations on tasks such as creating, viewing, updating, and deleting tasks.

The goal of this project is to demonstrate backend development concepts including **REST API design, Spring Data JPA integration, layered architecture (Controller, Service, Repository), and database persistence**.

---

## Features

- Create new tasks
- Retrieve all tasks
- Retrieve a specific task by ID
- Update task information
- Delete tasks
- Layered MVC architecture
- Persistent data storage with MySQL

---

## Tech Stack

**Backend**: Java 17, Spring Boot, Spring Web
**Database**: MySQL, Spring Data JPA, Hibernate
**Tools**: Maven, Postman, Git

---

## Prerequisites

- Java 17
- Maven
- MySQL Server running on `localhost:3306`

---

## Installation & Setup

1. **Clone the repository**
   ```bash
   git clone https://github.com/dattu-codes/task-manager-api.git
   cd task-manager-api
   ```

2. **Configure Database**
   Create a database in MySQL named `task_manager`:
   ```sql
   CREATE DATABASE task_manager;
   ```
   Update the database credentials in `src/main/resources/application.properties` if they differ from your local setup (default is `root`/`root`).

3. **Run the Application**
   ```bash
   ./mvnw spring-boot:run
   ```
   The server will start on `http://localhost:8080`.

---

## API Endpoints

### Get all tasks
`GET /tasks`

### Get task by ID
`GET /tasks/{id}`

### Create a task
`POST /tasks`
```json
{
  "title": "Complete project",
  "description": "Finish task manager backend",
  "completed": false
}
```

### Update a task
`PUT /tasks/{id}`
```json
{
  "title": "Updated title",
  "description": "Updated description",
  "completed": true
}
```

### Delete a task
`DELETE /tasks/{id}`

---

## Author

**Dattatreya Teella**

GitHub: [dattu-codes](https://github.com/dattu-codes)
