package com.greatlearning.employeemanagement;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.greatlearning.employeemanagement.entity.Role;
import com.greatlearning.employeemanagement.entity.User;
import com.greatlearning.employeemanagement.repository.UserRepository;

@SpringBootApplication
public class JavaFsdGraded4RestApiApplication implements CommandLineRunner {
	
	@Autowired
	UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(JavaFsdGraded4RestApiApplication.class, args);
		System.out.println("Evetything is Okay");
	}

	@Override
	public void run(String... args) throws Exception {
		
		User user = new User();
		user.setId(1);
		user.setUsername("sai");
		user.setPassword(new BCryptPasswordEncoder().encode("sai"));
		List<Role> roles = new ArrayList<>();
		roles.add(new Role(1,"admin"));
		user.setRoles(roles);
		userRepository.save(user);
		
	}

}
