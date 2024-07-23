package com.users.users.services;

import com.users.users.exceptions.UserNotFoundException;
import com.users.users.exceptions.UsernameAlreadyExistsException;
import com.users.users.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.users.users.repositories.UserRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    // Injecting the UserRepository
    @Autowired
    private UserRepository userRepository;

    // Method to retrieve all user from repository
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    // Method to retrieve user by their ID
    // Throws UserNotFoundException if user is not found
    public User getUserById(long id) throws UserNotFoundException{
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
    }

    // Method to create a new user
    // Throws an exception if user with the same username already exists
    public User createUser(User user) throws Exception{
        String username = user.getUserName();
        if(userRepository.existsByUserName(username))
            throw new Exception("User with username " + username + " already exists");

        user.setCreationDate(LocalDateTime.now());
        return userRepository.save(user);
    }


    // Method to update user information
    // Throws UserNotFoundException if user is not found
    // Throws an exception if new username already exist(excluding current user's username)
    public User updateUser(long id, User user) throws UserNotFoundException{
        if(!userRepository.existsById(id))
            throw new UserNotFoundException("User not found with id: " + id);

        User checkUser = userRepository.findByUserName(user.getUserName());

        if(checkUser != null && userRepository.existsByUserName(checkUser.getUserName()) && checkUser.getId() != id)
            throw new UsernameAlreadyExistsException("User with username: " + user.getUserName() + " already exists");

        user.setCreationDate(userRepository.findById(id).get().getCreationDate()); // Account's creation date stays unchanged
        user.setId(id);                                                            // Account's id stays unchanged
        return userRepository.save(user);
    }


    // Method to delete user with specified id
    // Throws UserNotFoundException if user is not found
    public void deleteUser(long id) throws UserNotFoundException{
        if(!userRepository.existsById(id))
            throw new UserNotFoundException("User not found with id: " + id);

        userRepository.deleteById(id);
    }
}
