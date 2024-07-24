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

