package com.example.demo.dao;


import java.util.stream.Stream;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Gadget;




@Repository 
public interface GadgetRepository extends CrudRepository<Gadget,String>{
	
	
	@Query("from Gadget g") 
	Stream<Gadget> getAll();
}

