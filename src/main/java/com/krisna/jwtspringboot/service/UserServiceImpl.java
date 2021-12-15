package com.krisna.jwtspringboot.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.krisna.jwtspringboot.domain.Role;
import com.krisna.jwtspringboot.domain.User;
import com.krisna.jwtspringboot.repo.RoleRepo;
import com.krisna.jwtspringboot.repo.UserRepo;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {

	private final UserRepo userRepo;
	private final RoleRepo roleRepo;
	private final PasswordEncoder passwordEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepo.findByUsername(username);
		if (user == null) {
			log.error("User not found in our database");
			throw new UsernameNotFoundException("User not found in our database");
		} else {
			log.info("User found in our database, {}", username);
		}
		Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
		user.getRoles().forEach(role -> {
			authorities.add(new SimpleGrantedAuthority(role.getName()));
		});
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				authorities);
	}

	@Override
	public void addRoleToUser(String username, String roleName) {
		log.info("Adding role {} to User {}", roleName, username);
		User user = userRepo.findByUsername(username);
		Role role = roleRepo.findByName(roleName);

		user.getRoles().add(role);
	}

	@Override
	public User getUser(String username) {
		log.info("Fetching user {}", username);
		return userRepo.findByUsername(username);
	}

	@Override
	public List<User> getUsers() {
		log.info("Fetching all users");
		return userRepo.findAll();
	}

	@Override
	public Role saveRole(Role role) {
		log.info("Saving new {} role", role.getName());
		return roleRepo.save(role);
	}

	@Override
	public User saveUser(User user) {
		log.info("Saving new {} user", user.getName());
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepo.save(user);
	}

}
