package com.example.demo.configuration;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;



@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter{
   
   @Override 
   protected void configure(AuthenticationManagerBuilder auth) throws
   Exception 
   {  
	   auth.inMemoryAuthentication()
	   .withUser("springsales").password(passwordEncoder().encode("spring123")).roles("SALES","USER")
	   .and()
	   .withUser("springuser").password(passwordEncoder().encode("user123")).roles("USER");
   
   
   }
   
   @Override 
   protected void configure(HttpSecurity http) throws Exception {
	   http. 
	   		authorizeRequests() 
		   .antMatchers("/api/v1/gadgets/*").hasRole("SALES")
		   .antMatchers("/api/v1/gadgets").hasRole("USER")
		   .anyRequest().authenticated()
		   .and().formLogin(); 
   }
   
   
   @Bean 
   public PasswordEncoder passwordEncoder() 
   { 
	   return new BCryptPasswordEncoder();
   
   } 
}






