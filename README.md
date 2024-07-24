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

### 3. UserNameAlreadyExistsException
A custom exception thrown when the provided username is already taken while updating user or creating new one.

## Tests

### 1. Unit Tests
Unit tests are used to test individual components of the application in isolation. The primary framework used for unit testing is JUnit 5.

- **UserServiceTest**: Tests the business logic in `UserService`.
#### Test Methods

- **getAllUsers()**
  - Prepares mock data for a list of users.
  - Mocks the `findAll` method of `UserRepository` to return the mock data.
  - Calls `getAllUsers` on the `UserService`.
  - Asserts that the returned list matches the mock data.

- **getUserById_UserExists()**
  - Prepares mock data for a user with a specific ID.
  - Mocks the `findById` method of `UserRepository` to return the mock user.
  - Calls `getUserById` on the `UserService`.
  - Asserts that the returned user matches the mock data.

- **getUserById_UserNotFound()**
  - Mocks the `findById` method of `UserRepository` to return an empty `Optional`.
  - Calls `getUserById` on the `UserService`.
  - Asserts that a `UserNotFoundException` is thrown.

- **createUser_UserNameAlreadyExists()**
  - Prepares mock data for a user with a specific username.
  - Mocks the `existsByUserName` method of `UserRepository` to return `true`.
  - Calls `createUser` on the `UserService`.
  - Asserts that an `Exception` is thrown due to the username already existing.

- **createUser_Success()**
  - Prepares mock data for a user and the expected saved user.
  - Mocks the `existsByUserName` method of `UserRepository` to return `false`.
  - Mocks the `save` method of `UserRepository` to return the saved user.
  - Calls `createUser` on the `UserService`.
  - Asserts that the returned user matches the saved user.

- **updateUser_UserNotFound()**
  - Mocks the `existsById` method of `UserRepository` to return `false`.
  - Calls `updateUser` on the `UserService`.
  - Asserts that a `UserNotFoundException` is thrown.

- **updateUser_UserNameAlreadyExists()**
  - Prepares mock data for a new user and a conflicting user.
  - Mocks the `existsById`, `findByUserName`, and `existsByUserName` methods of `UserRepository` to simulate a username conflict.
  - Calls `updateUser` on the `UserService`.
  - Asserts that a `UsernameAlreadyExistsException` is thrown.

- **updateUser_Success()**
  - Prepares mock data for an existing user and an updated user.
  - Mocks the `existsById`, `findById`, `findByUserName`, and `save` methods of `UserRepository` to simulate a successful update.
  - Calls `updateUser` on the `UserService`.
  - Asserts that the returned user matches the updated user.

- **deleteUser_UserNotFound()**
  - Mocks the `existsById` method of `UserRepository` to return `false`.
  - Calls `deleteUser` on the `UserService`.
  - Asserts that a `UserNotFoundException` is thrown.

- **deleteUser_Success()**
  - Mocks the `existsById` method of `UserRepository` to return `true`.
  - Calls `deleteUser` on the `UserService`.
  - Verifies that the `deleteById` method of `UserRepository` is called once.

- **UserControllerTest**: Tests the endpoints in `UserController` to ensure they correctly map to the service layer and return the expected responses.
#### Test Methods
- **getAllUsers()**: Tests the retrieval of all users and verifies the response status and body.
- **getUserById()**: Tests the retrieval of a user by ID and verifies the response status and body.
- **createUser()**: Tests the creation of a new user and verifies the response status and body.
- **updateUser()**: Tests the update of an existing user and verifies the response status and body.
- **deleteUser()**: Tests the deletion of a user and verifies the response status.

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

