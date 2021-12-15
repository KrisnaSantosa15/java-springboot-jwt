package com.krisna.jwtspringboot;

import java.util.ArrayList;

import com.krisna.jwtspringboot.domain.Role;
import com.krisna.jwtspringboot.domain.User;
import com.krisna.jwtspringboot.service.UserService;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class JwtspringbootApplication {

	public static void main(String[] args) {
		SpringApplication.run(JwtspringbootApplication.class, args);
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	};

	@Bean
	CommandLineRunner run(UserService userService) {
		return args -> {
			userService.saveRole(new Role(null, "ROLE_DOSEN"));
			userService.saveRole(new Role(null, "ROLE_MAHASISWA"));
			userService.saveRole(new Role(null, "ROLE_SUPER_ADMIN"));

			userService.saveUser(new User(null, "Dosen Pertama", "dosenpertama", "12345", new ArrayList<>()));
			userService.saveUser(new User(null, "Mahasiswa Satu", "mahasiswasatu", "67890", new ArrayList<>()));
			userService.saveUser(new User(null, "Super Admin", "superadmin", "123123", new ArrayList<>()));

			userService.addRoleToUser("dosenpertama", "ROLE_DOSEN");
			userService.addRoleToUser("mahasiswasatu", "ROLE_MAHASISWA");
			userService.addRoleToUser("superadmin", "ROLE_SUPER_ADMIN");
		};
	}

}
