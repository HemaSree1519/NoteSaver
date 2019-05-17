package com.noteSaver.notesaver.controller;

import com.noteSaver.notesaver.exception.ResourceNotFoundException;
import com.noteSaver.notesaver.model.User;
import com.noteSaver.notesaver.repository.UserRepository;
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
import java.util.Optional;

@CrossOrigin(origins = "http://localhost")
@RestController
@RequestMapping("/notesaver")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Create a new user
    @PostMapping("/users/add")
    public User createUser(@Valid @RequestBody User data) {
        String email = data.getEmail();
        Optional<User> user = userRepository.findById(email);
        user.toString();
        if (user.toString().equals("Optional.empty")) {
            return userRepository.save(data);
        } else throw new ResourceNotFoundException("User", "email", email);
    }

    // Get a Single User
    @GetMapping("/users/{email}")
    public User getUserByEmail(@PathVariable(value = "email") String email) {
        User user = userRepository.findById(email)
                .orElseThrow(() -> new ResourceNotFoundException("user", "id", email));
        return user;
    }
}