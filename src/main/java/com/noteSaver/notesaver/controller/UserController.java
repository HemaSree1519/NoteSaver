package com.noteSaver.notesaver.controller;

import com.noteSaver.notesaver.model.User;
import com.noteSaver.notesaver.repository.UserRepository;
import com.noteSaver.notesaver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/notesaver")
public class UserController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;

    // Get all users
    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    // Create a new user
    @PostMapping("/users/add")
    public User addUser(@Valid @RequestBody User userDetails) {
        return userService.addUser(userDetails);
    }

    // Get a single user
    @GetMapping("/users/{email}")
    public User getUser(@PathVariable(value = "email") String email) {
        return userService.getUser(email);
    }
}