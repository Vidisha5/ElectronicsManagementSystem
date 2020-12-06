package com.example.demo.controller;
   
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.dao.UserRepository;
import com.example.demo.domain.User;
   
@Service 
public class MyUserDetailsService implements UserDetailsService{
   
   @Autowired 
   UserRepository userRepository;
   
   @Override 
   public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException 
   { 
	  
	   User user=userRepository.findByUsername(username)
	   .orElseThrow(()->new UsernameNotFoundException("User Not found"));
   
	   UserDetails userDetails=org.springframework.security.core.userdetails.User
			   					.withUsername(user.getUsername())
			   					.password(user.getPassword())
			   					.authorities(user.getRole().getRolename())
			   					.build();
   
   	   return userDetails; 
   }
   
}
 
  
  