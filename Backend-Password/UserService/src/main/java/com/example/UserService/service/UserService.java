package com.example.UserService.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.UserService.client.PasswordStrengthClient;
import com.example.UserService.entity.User;
import com.example.UserService.repository.UserRepository;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordStrengthClient passwordStrengthClient;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User createUser(User user) {
        
        String strength = passwordStrengthClient.validatePassword(user.getPassword());

        
        String encryptedPassword = passwordEncoder.encode(user.getPassword());

        
        user.setPassword(encryptedPassword);
        user.setPasswordStrength(strength);

        
        userRepository.save(user);

        return user;
    }
    
    public boolean checkPassword(User user, String rawPassword) {
        return passwordEncoder.matches(rawPassword, user.getPassword());
    }
    
    
}








