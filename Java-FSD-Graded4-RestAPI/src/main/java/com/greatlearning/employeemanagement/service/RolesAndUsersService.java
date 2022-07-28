package com.greatlearning.employeemanagement.service;

import com.greatlearning.employeemanagement.entity.Role;
import com.greatlearning.employeemanagement.entity.User;

public interface RolesAndUsersService {

	Role addRole(String name);

	User addNewUser(String username, String password, String rolename);

}