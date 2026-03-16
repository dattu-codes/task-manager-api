# Task Manager API

A RESTful backend API for managing tasks, built using **Node.js** and
**Express.js**.\
This project allows users to perform CRUD operations on tasks such as
creating, viewing, updating, and deleting tasks.

The goal of this project is to demonstrate backend development concepts
including **REST API design, route handling, middleware usage, and
server-side logic**.

------------------------------------------------------------------------

## Features

-   Create new tasks
-   Retrieve all tasks
-   Retrieve a specific task by ID
-   Update task information
-   Delete tasks
-   RESTful API architecture
-   Error handling and validation

------------------------------------------------------------------------

## Tech Stack

**Backend** - Node.js - Express.js

**Tools** - Postman (API Testing) - Git - GitHub

------------------------------------------------------------------------

## Project Structure

    task-manager-api
    │
    ├── src
    │   ├── app.js
    │   ├── routes
    │   ├── controllers
    │   └── models
    │
    ├── package.json
    └── README.md

------------------------------------------------------------------------

## Installation

Clone the repository

    git clone https://github.com/dattu-codes/task-manager-api.git

Navigate to the project directory

    cd task-manager-api

Install dependencies

    npm install

------------------------------------------------------------------------

## Running the Application

Start the server

    node src/app.js

or

    npm start

The server will run on:

    http://localhost:3000

------------------------------------------------------------------------

## API Endpoints

### Get all tasks

    GET /tasks

Returns all tasks.

### Get task by ID

    GET /tasks/:id

Returns a specific task.

### Create a task

    POST /tasks

Example request body:

``` json
{
  "title": "Complete project",
  "description": "Finish task manager backend",
  "completed": false
}
```

### Update a task

    PATCH /tasks/:id

Updates an existing task.

### Delete a task

    DELETE /tasks/:id

Deletes a task.

------------------------------------------------------------------------

## Testing the API

You can test the API using:

-   Postman
-   Thunder Client (VS Code)
-   cURL

Example request:

    GET http://localhost:3000/tasks

------------------------------------------------------------------------

## Future Improvements

-   User authentication (JWT)
-   Database integration (MongoDB)
-   Pagination and filtering
-   Task priorities and deadlines
-   Frontend integration

------------------------------------------------------------------------

## Author

**Dattatreya Teella**

GitHub:\
https://github.com/dattu-codes
