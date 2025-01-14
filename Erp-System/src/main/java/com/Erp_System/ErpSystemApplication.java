package com.Erp_System;

import com.Erp_System.Entity.Role;
import com.Erp_System.Entity.User;
import com.Erp_System.repository.RoleRepo;
import com.Erp_System.repository.UserRepo;
import com.Erp_System.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SpringBootApplication
public class ErpSystemApplication implements CommandLineRunner {

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private RoleRepo roleRepo;

	public static void main(String[] args) {
		SpringApplication.run(ErpSystemApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Role roleAdmin = roleRepo.findByRoleName("ROLE_ADMIN").orElse(null);
		Role roleUser = roleRepo.findByRoleName("ROLE_USER").orElse(null);

		if(roleAdmin == null){
			roleAdmin = new Role();
			roleAdmin.setRoleName("ROLE_ADMIN");
			roleRepo.save(roleAdmin);
		}

		if(roleUser ==null){
			roleUser = new Role();
			roleUser.setRoleName("ROLE_USER");
			roleRepo.save(roleUser);
		}

		User user = userRepo.findByEmail("yash@gmail.com").orElse(null);
		if(user ==null){
			user= new User();
			user.setPhoneNumber("9131747285");
			user.setPassword("yash");
			user.setGender("male");
			user.setEmail("yash@gmail.com");
			user.setAbout("I am a java springboot developer");
			user.setAddress("vijay nagar");
			user.setDomain("java");
			user.setName("yash sharma");
			user.setImageName("yash.jpg");
			user.setEmployeeId("EMP124OP455");

			List<Role> roles = new ArrayList<>();
			roles.add(roleAdmin);
			roles.add(roleUser);

			user.setRoles(List.of(roleAdmin,roleUser));
			userRepo.save(user);

		} else {
			System.out.println("User already exists");
		}
	}
}
