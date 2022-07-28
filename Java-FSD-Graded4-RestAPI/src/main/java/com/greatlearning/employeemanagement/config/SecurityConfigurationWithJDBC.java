package com.greatlearning.employeemanagement.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.greatlearning.employeemanagement.serviceImpl.EmployeeUserDetailsService;

@Configuration
public class SecurityConfigurationWithJDBC extends WebSecurityConfigurerAdapter {

	

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		AuthenticationProvider authenticationProvider = customAuthenticationProvider();
		auth.authenticationProvider(authenticationProvider);
	}
	
	@Bean
	public UserDetailsService customUserDetailsService() {
		return new EmployeeUserDetailsService();
		
	}

	@Bean
	PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/h2-console/**");
	}
	
	@Bean
	public DaoAuthenticationProvider customAuthenticationProvider() {
		DaoAuthenticationProvider daoProvider = new DaoAuthenticationProvider();
		
		daoProvider.setUserDetailsService(customUserDetailsService());
		daoProvider.setPasswordEncoder(getPasswordEncoder());
		return daoProvider;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/createService/addEmployee","/h2-console/**", "/create-signup/**" )
				.hasAuthority("admin")
				.antMatchers("/readService/**", "/deleteService/deleteById", "/updateService/updateExistingEmployee")
				.hasAnyAuthority("admin", "user")
				.anyRequest().authenticated()
				.and()
				.formLogin().loginProcessingUrl("/login").successForwardUrl("/").permitAll()
				.and()
				.logout().logoutSuccessUrl("/login").permitAll()
				.and()
				.cors().and().csrf().disable();

   }
}
