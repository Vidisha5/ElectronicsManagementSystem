package com.example.demo.domain;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;


@Entity
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long userId;
	
	@NotBlank(message="Please provide a username")
	private String username;
	
	@NotBlank(message="Please provide a password")
	private String password;
	
	private boolean enabled;
	
	
	
	 
	@ManyToOne
	@JoinColumn(name="role_id")
	@JsonProperty(access=JsonProperty.Access.WRITE_ONLY)
	private Role role;

	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}

	

	public Long getUserId() {
		return userId;
	}



	public void setUserId(Long userId) {
		this.userId = userId;
	}



	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public User() {
		super();
	}



	public Role getRole() {
		return role;
	}



	public void setRole(Role role) {
		this.role = role;
	}



	
	
}
	

	
	
	 
	
	 
	
	
