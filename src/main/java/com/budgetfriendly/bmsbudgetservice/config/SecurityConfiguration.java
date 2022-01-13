/*
 *
 * Copyright (c) 2019 OLAM Limited
 *
 * All information contained herein is, and remains the property of OLAM
 * Limited. The intellectual and technical concepts contained herein are
 * proprietary to OLAM and are protected by trade secret or copyright law.
 * Dissemination of this information or reproduction of this material is
 * strictly forbidden unless prior written permission is obtained from OLAM
 * Limited
 *
 */ 
package com.budgetfriendly.bmsbudgetservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * <p>
 *  This Class is used for extending the spring security default extended from Common service.
 *  This class serves as a base for security in contract.
 * </p>
 * @author Nagarajan Ramakrishnan
 *
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
	
	 @Bean
	 @Override
	 protected AuthenticationManager authenticationManager() throws Exception {
	      return super.authenticationManager();
	 }
	 
	
	@Override
	public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/**");    
    }
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.authorizeRequests()
				.antMatchers(HttpMethod.OPTIONS, "/**").permitAll();
		http.csrf().disable();

	}
	
}
