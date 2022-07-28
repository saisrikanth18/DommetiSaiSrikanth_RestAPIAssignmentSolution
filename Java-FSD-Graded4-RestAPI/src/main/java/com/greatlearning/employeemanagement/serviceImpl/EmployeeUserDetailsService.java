package com.greatlearning.employeemanagement.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import com.greatlearning.employeemanagement.config.MyCustomUserDetails;
import com.greatlearning.employeemanagement.entity.User;
import com.greatlearning.employeemanagement.repository.UserRepository;

@Repository
public class EmployeeUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User userEntity = userRepository.getUserByUsername(username);
		if (userEntity==null) {
			throw new UsernameNotFoundException("Could not find User");
		}
		return new MyCustomUserDetails(userEntity);
	}

}
