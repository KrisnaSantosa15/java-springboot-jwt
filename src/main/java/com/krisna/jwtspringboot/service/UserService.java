package com.krisna.jwtspringboot.service;

import java.util.List;

import com.krisna.jwtspringboot.domain.Role;
import com.krisna.jwtspringboot.domain.User;

public interface UserService {
	User saveUser(User user);

	Role saveRole(Role role);

	void addRoleToUser(String username, String roleName);

	User getUser(String username);

	List<User> getUsers();
}
