package com.greatlearning.employeemanagement.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.greatlearning.employeemanagement.entity.Role;
import com.greatlearning.employeemanagement.entity.User;
import com.greatlearning.employeemanagement.repository.RoleRepository;
import com.greatlearning.employeemanagement.repository.UserRepository;
import com.greatlearning.employeemanagement.service.RolesAndUsersService;

@Service
public class RolesAndUsersServiceImpl implements RolesAndUsersService {
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	UserRepository userRepository;
	
	
	@Override
	public Role addRole(String name) {
		Role role = roleRepository.findRoleByName(name);
		
		if (role!=null) {
			return role;
		}else {
		role = new Role();
		role.setName(name);
		return roleRepository.save(role);
		}
	}
	
	@Override
	public User addNewUser(String username, String password, String rolename) {
		
		User user = new User();
		user.setUsername(username);
		user.setPassword(new BCryptPasswordEncoder().encode(password));
		
		List<Role> roles = new ArrayList<>();
		Role role = roleRepository.findRoleByName(rolename);
		
		if(role == null)
			role = addRole(rolename);
		roles.add(role);
		user.setRoles(roles);
		
		return userRepository.save(user);
	}

}
