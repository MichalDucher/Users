package com.users.users.services;

import com.users.users.exceptions.UserNotFoundException;
import com.users.users.exceptions.UsernameAlreadyExistsException;
import com.users.users.models.User;
import com.users.users.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllUsers() {
        // Prepare mock data
        User user1 = new User();
        user1.setId(1L);
        user1.setUserName("user1");

        User user2 = new User();
        user2.setId(2L);
        user2.setUserName("user2");

        List<User> users = Arrays.asList(user1, user2);

        // Mock the repository call
        when(userRepository.findAll()).thenReturn(users);

        // Call the service method
        List<User> result = userService.getAllUsers();

        // Assert the result
        assertEquals(2, result.size());
        assertEquals(users, result);
    }

    @Test
    void getUserById_UserExists() throws UserNotFoundException {
        // Prepare mock data
        User user = new User();
        user.setId(1L);
        user.setUserName("user1");

        // Mock the repository call
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        // Call the service method
        User result = userService.getUserById(1L);

        // Assert the result
        assertEquals(user, result);
    }

    @Test
    void getUserById_UserNotFound() {
        // Mock the repository call
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        // Call the service method and assert exception
        assertThrows(UserNotFoundException.class, () -> userService.getUserById(1L));
    }

    @Test
    void createUser_UserNameAlreadyExists() {
        // Prepare mock data
        User user = new User();
        user.setUserName("user1");

        // Mock the repository call
        when(userRepository.existsByUserName("user1")).thenReturn(true);

        // Call the service method and assert exception
        assertThrows(Exception.class, () -> userService.createUser(user));
    }

    @Test
    void createUser_Success() throws Exception {
        // Prepare mock data
        User user = new User();
        user.setUserName("user1");

        User savedUser = new User();
        savedUser.setId(1L);
        savedUser.setUserName("user1");
        savedUser.setCreationDate(LocalDateTime.now());

        // Mock the repository calls
        when(userRepository.existsByUserName("user1")).thenReturn(false);
        when(userRepository.save(user)).thenReturn(savedUser);

        // Call the service method
        User result = userService.createUser(user);

        // Assert the result
        assertEquals(savedUser, result);
    }

    @Test
    void updateUser_UserNotFound() {
        // Mock the repository call
        when(userRepository.existsById(1L)).thenReturn(false);

        // Call the service method and assert exception
        assertThrows(UserNotFoundException.class, () -> userService.updateUser(1L, new User()));
    }

    @Test
    void updateUser_UserNameAlreadyExists() {
        // Prepare mock data
        User newUser = new User();
        newUser.setId(2L);
        newUser.setUserName("newUser");

        User conflictingUser = new User();
        conflictingUser.setId(3L);
        conflictingUser.setUserName("newUser");

        // Mock the repository calls
        when(userRepository.existsById(2L)).thenReturn(true);
        when(userRepository.findByUserName("newUser")).thenReturn(conflictingUser);
        when(userRepository.existsByUserName("newUser")).thenReturn(true);

        // Mock the existing user data for findById to avoid UserNotFoundException
        when(userRepository.findById(2L)).thenReturn(Optional.of(new User()));

        // Call the service method and assert exception
        assertThrows(UsernameAlreadyExistsException.class, () -> userService.updateUser(2L, newUser));
    }

    @Test
    void updateUser_Success() throws UserNotFoundException, UsernameAlreadyExistsException {
        // Prepare mock data
        User existingUser = new User();
        existingUser.setId(1L);
        existingUser.setUserName("existingUser");
        existingUser.setCreationDate(LocalDateTime.now());

        User updatedUser = new User();
        updatedUser.setUserName("updatedUser");

        // Mock the repository calls
        when(userRepository.existsById(1L)).thenReturn(true);
        when(userRepository.findById(1L)).thenReturn(Optional.of(existingUser));
        when(userRepository.findByUserName("updatedUser")).thenReturn(null);
        when(userRepository.save(updatedUser)).thenReturn(updatedUser);

        // Call the service method
        User result = userService.updateUser(1L, updatedUser);

        // Assert the result
        assertEquals(existingUser.getCreationDate(), result.getCreationDate());
        assertEquals(1L, result.getId());
        assertEquals("updatedUser", result.getUserName());
    }

    @Test
    void deleteUser_UserNotFound() {
        // Mock the repository call
        when(userRepository.existsById(1L)).thenReturn(false);

        // Call the service method and assert exception
        assertThrows(UserNotFoundException.class, () -> userService.deleteUser(1L));
    }

    @Test
    void deleteUser_Success() throws UserNotFoundException {
        // Mock the repository call
        when(userRepository.existsById(1L)).thenReturn(true);

        // Call the service method
        userService.deleteUser(1L);

        // Verify the repository call
        verify(userRepository, times(1)).deleteById(1L);
    }
}
