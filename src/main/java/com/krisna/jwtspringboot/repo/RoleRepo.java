package com.krisna.jwtspringboot.repo;

import com.krisna.jwtspringboot.domain.Role;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<Role, Long> {
	Role findByName(String name);
}
