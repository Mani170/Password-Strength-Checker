package com.example.UserService.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.UserService.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByEmail(String email);
}
