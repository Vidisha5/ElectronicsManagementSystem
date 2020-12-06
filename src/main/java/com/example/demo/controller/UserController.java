package com.example.demo.controller;

import java.util.Optional;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.dao.RoleRepository;
import com.example.demo.dao.UserRepository;
import com.example.demo.domain.Role;
import com.example.demo.domain.User;


@Controller
public class UserController {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	private static final Logger LOGGER=LoggerFactory.getLogger(UserController.class);
	
	@RolesAllowed("ADMIN")
	@GetMapping("/register")
	public String registerForm(User user){
		
		return "registration";
	}
	
	
	@PostMapping("/register")
	public String createUser(@Valid User user,BindingResult result){
		
		if(result.hasErrors()) {
			return "registration";
		}
		
		Optional<Role> role=roleRepository.findByRolename("ROLE_USER");
		LOGGER.info("Rolename is:"+role.get().getRolename());
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setRole(role.get());
		userRepository.save(user);
		LOGGER.info("User saved");
		return "result";
	}
}