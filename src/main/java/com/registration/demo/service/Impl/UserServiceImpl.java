package com.registration.demo.service.Impl;

import com.registration.demo.model.User;
import com.registration.demo.repository.UserRepository;
import com.registration.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public ResponseEntity<Object> addUser(User user) {
        if (isUserExist(user.getEmail())) {
            String errorMessage = "A user with the same email address already exists.";
            return new ResponseEntity<>(errorMessage, HttpStatus.CONFLICT);
        } else {
            User createdUser = userRepository.save(user);
            return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
        }
    }

    @Override
    public ResponseEntity<Object> getUser(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<Object> updateUser(Long id, User updatedUser) {
        if (id == null) {
            return new ResponseEntity<>("ID cannot be null.", HttpStatus.BAD_REQUEST);
        }

        if (userRepository.existsByEmail(updatedUser.getEmail())) {
            return new ResponseEntity<>("E-mail address is not unique.", HttpStatus.BAD_REQUEST);
        }

        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setName(updatedUser.getName());
            user.setLastName(updatedUser.getLastName());
            user.setPassword(updatedUser.getPassword());
            user.setBirthDate(updatedUser.getBirthDate());
            user.setEmail(updatedUser.getEmail());

            User savedUser = userRepository.save(user);
            return new ResponseEntity<>(savedUser, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User not found.", HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<Object> deleteUser(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            userRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public boolean isUserExist(String email) {
        return userRepository.existsByEmail(email);
    }



}
