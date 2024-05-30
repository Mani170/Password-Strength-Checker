package com.example.UserService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.UserService.entity.User;
import com.example.UserService.repository.UserRepository;
import com.example.UserService.service.UserService;


@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
  private UserService userService;
 
    @PostMapping("/signup")
    public ResponseEntity<?> createUser(@RequestBody User user) {
        if (userRepository.findByEmail(user.getEmail()) != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email already in use");
        }
        User savedUser = userService.createUser(user);
        return ResponseEntity.ok(savedUser);
    }


}







