package com.example.demo.dao;



import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Role;


@Repository
public interface RoleRepository extends CrudRepository<Role,Long>{
	
	Optional<Role> findByRolename(String rolename);
}
