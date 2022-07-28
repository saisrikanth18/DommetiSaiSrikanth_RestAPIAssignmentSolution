package com.greatlearning.employeemanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.greatlearning.employeemanagement.entity.Role;
import com.greatlearning.employeemanagement.entity.User;
import com.greatlearning.employeemanagement.service.RolesAndUsersService;

@RestController
@RequestMapping("/create-signup")
public class RolesAndUsersController {
	
	@Autowired
	RolesAndUsersService rolesAndUsersService;
	
	@PostMapping("/add-role")
	public Role addRole(String name) {
		return rolesAndUsersService.addRole(name);
	}
	
	@PostMapping("/add-newuser")
	public User addNewUser(String username, String password, String rolename) {
		return rolesAndUsersService.addNewUser(username, password, rolename);
	}

}
