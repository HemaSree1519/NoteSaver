package com.noteSaver.notesaver.service;

import com.noteSaver.notesaver.exception.DuplicateEntryException;
import com.noteSaver.notesaver.exception.ResourceNotFoundException;
import com.noteSaver.notesaver.model.User;
import com.noteSaver.notesaver.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User addUser(User userDetails) {
        String email = userDetails.getEmail();
        Optional<User> user = userRepository.findById(email);
        user.toString();
        if (user.toString().equals("Optional.empty")) {
            return userRepository.save(userDetails);
        } else {
            System.out.println("Checking the duplicate exception");
            throw new DuplicateEntryException("User", "email", email);
        }
    }

    public User getUser(String email) {
        return userRepository.findById(email)
                .orElseThrow(() -> new ResourceNotFoundException("user", "id", email));
    }
}
