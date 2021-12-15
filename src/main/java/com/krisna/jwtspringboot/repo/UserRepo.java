package com.krisna.jwtspringboot.repo;

import com.krisna.jwtspringboot.domain.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
	User findByUsername(String username);	
}
