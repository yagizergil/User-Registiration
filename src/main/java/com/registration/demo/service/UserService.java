package com.registration.demo.service;

import com.registration.demo.model.User;
import org.springframework.http.ResponseEntity;

public interface UserService {

    ResponseEntity<Object> addUser(User user);
    ResponseEntity<Object> getUser(Long id);
    ResponseEntity<Object> updateUser(Long id, User user);
    ResponseEntity<Object> deleteUser(Long id);
    ResponseEntity<Object> getAllUsers();
    boolean isUserExist(String email);


}
