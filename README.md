# Users Management System

## Description
The Users Management System is a simple web application for managing users. It provides functionality to create, read, update, and delete user records. This project uses Java with the Spring Boot framework and is designed to be straightforward and easy to understand.

## Technologies
- **Java**: The programming language used.
- **Spring Boot**: Framework for building the web application.
- **Spring Data JPA**: For data access and repository management.
- **PostgreSQL**: The database used for storing user records.
- **JUnit 5**: For unit testing of the application components.

## Features
- **Get All Users**: Retrieve a list of all users.
- **Get User by ID**: Fetch a specific user by their ID.
- **Create User**: Add a new user to the system.
- **Update User**: Modify an existing user's details.
- **Delete User**: Remove a user from the system.

## API Endpoints
- **GET** - `/users` - Retrieve all users.
- **GET** - `/users/{id}` - Retrieve a user by ID.
- **POST** - `/users` - Create a new user.
- **PUT** - `/users/{id}` - Update an existing user.
- **DELETE** - `/users/{id}` - Delete a user by ID.

## Classes

### 1. User
Represents the user entity with fields such as `id`, `userName`, `age`, `gender`

### 2. UserRepository
An interface that extends `JpaRepository` for CRUD operations on the `User` entity. It includes custom query methods like `existsByUserName`.

### 3. UserService
Provides business logic related to user management. It interacts with `UserRepository` to perform operations like retrieving, creating, updating, and deleting users.

### 4. UserController
Handles HTTP requests related to users. It maps the endpoints to the appropriate service methods and returns the results as HTTP responses.

## Exception Handling

### 1. UserNotFoundException
A custom exception thrown when a user is not found in the database. This exception is typically used in service methods when an operation requires a user that does not exist.

### 2. GlobalExceptionHandler
A class annotated with `@ControllerAdvice` to handle exceptions globally. It contains methods to handle different types of exceptions, including `UserNotFoundException`, and returns appropriate HTTP responses with error messages.

### 3. UserNameAlreadyExists
A custom exception thrown when the provided username is already taken while updating user or creating new one.

## Tests

### 1. Unit Tests
Unit tests are used to test individual components of the application in isolation. The primary framework used for unit testing is JUnit 5.

- **UserServiceTest**: Tests the business logic in `UserService`.
- **UserControllerTest**: Tests the endpoints in `UserController` to ensure they correctly map to the service layer and return the expected responses.

## Installation
1. **Clone the repository:**
   ```bash
   git clone https://github.com/MichalDucher/users.git
2. **Navigate to the project directory:**
    ```bash
    cd users
3. **Build the project:**
    ```bash
    mvnw clean install
4. **Run the application:**
    ```bash
    mvnw spring-boot:run

