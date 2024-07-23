package com.users.users.controllers;

import com.users.users.models.User;
import com.users.users.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserControllerTest {

    // Mocking the UserService to simulate service layer behavior
    @Mock
    private UserService userService;

    // Injecting mocks into UserController to test the controller layer
    @InjectMocks
    private UserController userController;

    // Initializing mocks before each test
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllUsers() {
        // Creating example users
        User user1 = new User();
        user1.setId(1L);
        user1.setUserName("user1");
        user1.setGender("male");
        user1.setAge(16);
        user1.setCreationDate(LocalDateTime.now());

        User user2 = new User();
        user2.setId(2L);
        user2.setUserName("user2");
        user2.setGender("female");
        user2.setAge(26);
        user2.setCreationDate(LocalDateTime.now());

        User user3 = new User();
        user3.setId(3L);
        user3.setUserName("user3");
        user3.setGender("male");
        user3.setAge(40);
        user3.setCreationDate(LocalDateTime.now());

        List<User> users = Arrays.asList(user1, user2, user3);

        // Mocking the service call to return the example users
        when(userService.getAllUsers()).thenReturn(users);

        // Calling the controller method
        ResponseEntity<List<User>> response = userController.getAllUsers();

        // Verifying the response status and body
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(users, response.getBody());
    }

    @Test
    void getUserById() {
        // Creating an example user with Id 1
        User user = new User();
        user.setId(1L);
        user.setUserName("user");
        user.setGender("male");
        user.setAge(16);
        user.setCreationDate(LocalDateTime.now());

        // Mocking the service call to return the example user
        when(userService.getUserById(1L)).thenReturn(user);

        // Calling the controller method
        ResponseEntity<User> response = userController.getUserById(1L);

        // Verifying the response status and body
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(user, response.getBody());
    }

    @Test
    void createUser() throws Exception {
        // Creating an example user to be saved
        User user = new User();
        user.setId(1L);
        user.setUserName("user");
        user.setGender("male");
        user.setAge(16);
        user.setCreationDate(LocalDateTime.now());

        // Mocking the service call to simulate user saving
        User savedUser = new User();
        savedUser.setId(1L);
        savedUser.setUserName("user");
        savedUser.setGender("male");
        savedUser.setAge(16);
        savedUser.setCreationDate(LocalDateTime.now());

        when(userService.createUser(user)).thenReturn(savedUser);

        // Calling the controller method
        ResponseEntity<String> response = userController.createUser(user);

        // Verifying the response status and body
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("User created with ID: " + user.getId(), response.getBody());
    }

    @Test
    void updateUser() {
        // Creating an example existing user with Id 1
        User existingUser = new User();
        existingUser.setId(1L);
        existingUser.setUserName("existingUser");
        existingUser.setGender("male");
        existingUser.setAge(16);
        existingUser.setCreationDate(LocalDateTime.now());

        // Creating an example updated user with Id 1
        User updatedUser = new User();
        updatedUser.setId(1L);
        updatedUser.setUserName("updatedUser");
        updatedUser.setGender("female");
        updatedUser.setAge(26);
        updatedUser.setCreationDate(LocalDateTime.now());

        // Mocking the service call to simulate user update
        User savedUser = new User();
        savedUser.setId(1L);
        savedUser.setUserName("updatedUser");
        savedUser.setGender("female");
        savedUser.setAge(26);
        savedUser.setCreationDate(LocalDateTime.now());

        when(userService.updateUser(1L, updatedUser)).thenReturn(savedUser);

        // Calling the controller method
        ResponseEntity<User> response = userController.updateUser(1L, updatedUser);

        // Verifying the response status and body
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(savedUser, response.getBody());
    }

    @Test
    void deleteUser() {
        // Example Id of the user to delete
        long userId = 1L;

        // Mocking the service call to simulate user deletion
        doNothing().when(userService).deleteUser(userId);

        // Calling the controller method
        ResponseEntity<Void> response = userController.deleteUser(userId);

        // Verifying the response status
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}
