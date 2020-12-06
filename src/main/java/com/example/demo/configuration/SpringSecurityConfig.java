package com.example.demo.configuration;
   
   
import javax.annotation.Resource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.demo.controller.MyUserDetailsService;
   




@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter{
   
   @Resource 
   MyUserDetailsService userDetailsService;
   
   
   
   
   @Override 
   protected void configure(AuthenticationManagerBuilder auth) throws Exception {
   
	   auth.authenticationProvider(authProvider());
   }
   
   @Override 
   protected void configure(HttpSecurity http) throws Exception { 
	   
	   http
	   		.authorizeRequests() 
	   		.antMatchers("/api/v1/gadgets").hasAnyRole("USER","SALES","ADMIN")
	   		.antMatchers("/api/v1/gadgets/**").hasRole("ADMIN")
	   		.antMatchers("/register").hasRole("ADMIN")
			.and()
			.formLogin();
   }
   
   
   
   @Bean 
   public PasswordEncoder passwordEncoder() { 
	   return new BCryptPasswordEncoder(); 
   }
   
   
   @Bean 
   public DaoAuthenticationProvider authProvider() throws Exception 
   {
	   DaoAuthenticationProvider provider=new DaoAuthenticationProvider();
	   provider.setUserDetailsService(userDetailsService);
	   provider.setPasswordEncoder(passwordEncoder()); 
	   return provider;  
   }
   
  
}
 
   
   
   
   
   
 