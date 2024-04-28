# Spring Boot Todo List Application

A simple Todo List REST API application built using Spring Boot. This application allows users to manage tasks through various RESTful endpoints.

## Features

- RESTful API for managing tasks.
- Task retrieval by ID.
- Listing all tasks.
- Adding a new task.
- Modifying an existing task.
- Deleting one or more tasks.

## Prerequisites

- Java 21
- Maven 3 or higher
- Spring Boot CLI or an IDE that supports Spring Boot (like Spring Tool Suite, IntelliJ IDEA, Eclipse, etc.)

## Getting Started

1. Clone the repository:
   git clone https://github.com/112Cookie/todo-list-app-springboot.git


2. Navigate to the project directory:
   cd todo-list-app-springboot


3. Build the project using Maven:
   mvn clean install


4. Run the application:
   mvn spring-boot:run


## Usage

The application provides the following endpoints:

### Get a Task by ID
`GET /todo/getTask/{id}`

### List All Tasks
`GET /todo/listTasks`

### Add a New Task
`POST /todo/addTask`

### Modify an Existing Task
`PUT /todo/modifyTask/{id}`

### Delete Tasks by IDs
`DELETE /todo/deleteTasks/{ids}`

Replace `{id}` and `{ids}` with the actual task ID or a list of task IDs separated by commas.

### Example Request Headers and Body for Adding/Modify Task

```http
POST /todo/addTask HTTP/1.1
Host: 127.0.0.1:8080
username: user_a
password: password_a
Content-Type: application/json

{
"title": "Sample Task",
"done": false
}
API Responses
The API uses ApiResponse to encapsulate the response data, status, and message.

Success Response
HttpStatus: 200 OK

{
  "status": 200,
  "message": "success",
  "data": {}
}
Error Response
HttpStatus: 500 Internal Server Error

{
  "status": 500,
  "message": "fail",
  "data": {}
}
License
This project is licensed under the MIT License - see the LICENSE file for details.