package com.users.users.controllers;

import com.users.users.exceptions.UserNotFoundException;
import com.users.users.exceptions.UsernameAlreadyExistsException;
import com.users.users.models.User;
import com.users.users.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    // Injecting the UserService
    @Autowired
    private UserService userService;

    // Endpoint to get all users
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        try {
            List<User> users = userService.getAllUsers();
            return ResponseEntity.ok(users);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // Endpoint to get a user by ID
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable long id){
        try{
            User user = userService.getUserById(id);
            return  ResponseEntity.ok(user);
        }catch (UserNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // Endpoint to create a new user
    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody User user){
        try{
            User createdUser = userService.createUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).body("User created with ID: " + user.getId());
        }catch (Exception ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }
    // Endpoint to update an existing user by ID
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable long id, @RequestBody User user){
        try{
            User updatedUser = userService.updateUser(id, user);
            return ResponseEntity.ok(updatedUser);
        }catch(UserNotFoundException ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }catch(UsernameAlreadyExistsException ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    // Endpoint to delete a user by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable long id){
        try{
            userService.deleteUser(id);
            return ResponseEntity.noContent().build();
        }catch(UserNotFoundException ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
