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
3. **Prepare your environment:**

   - Create a PostgreSQL database for the application.
   - Create a .env file in the root directory of the project and configure it with your PostgreSQL database details. The .env file should look like this:
   ```bash
   DB_URL=jdbc:postgresql://your-database-url:5432/your-database-name
   DB_USERNAME=your-database-username
   DB_PASSWORD=your-database-password
4. **Build the project:**
    ```bash
    mvn clean install
5. **Run the application:**
    ```bash
    mvn spring-boot:run