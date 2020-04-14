package com.saty.springdemo.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@Configuration
@EnableWebSecurity
public class DemoSecurityConfig extends WebSecurityConfigurerAdapter {

	//add a reference to our security data source
	
	@Autowired
	private DataSource securityDataSource;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// add our users for in memory authentication
		auth.jdbcAuthentication().dataSource(securityDataSource);
	}

	//configure security of web paths in application for eg. login,logout
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.authorizeRequests()
			.antMatchers("/").permitAll()
			.antMatchers("/customer/list").hasAnyRole("EMPLOYEE","MANAGER","ADMIN")
			.antMatchers("/customer/showFormForUpdate").hasAnyRole("MANAGER","ADMIN")
			.antMatchers("/customer/delete").hasRole("ADMIN")
			.and()
			.formLogin()
				.loginPage("/showMyLoginPage")
					.loginProcessingUrl("/authenticateTheUser")
						.permitAll()
						.and()
						.logout()
						.logoutSuccessUrl("/")
						.permitAll()
						.and()
						.exceptionHandling().accessDeniedPage("/access-denied");
						
	}
	
	
	
	
}
