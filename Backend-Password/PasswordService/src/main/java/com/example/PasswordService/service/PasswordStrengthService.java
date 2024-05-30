package com.example.PasswordService.service;

import org.springframework.stereotype.Service;

@Service
public class PasswordStrengthService {

    public String validatePasswordStrength(String password) {
        int length = password.length();
        boolean hasUpper = password.chars().anyMatch(Character::isUpperCase);
        boolean hasLower = password.chars().anyMatch(Character::isLowerCase);
        boolean hasDigit = password.chars().anyMatch(Character::isDigit);
        boolean hasSpecial = password.chars().anyMatch(ch -> "!@#$%^&*()_+[]{}|;:,.<>?".indexOf(ch) >= 0);

        if (length >= 8 && hasUpper && hasLower && hasDigit && hasSpecial) {
            return "Strong";
        } else if (length >= 6 && (hasUpper || hasLower) && (hasDigit || hasSpecial)) {
            return "Medium";
        } else {
            return "Weak";
        }
    }
}