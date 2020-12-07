package com.example.demo.dao;


import java.util.stream.Stream;

import javax.persistence.QueryHint;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Gadget;




@Repository 
public interface GadgetRepository extends CrudRepository<Gadget,String>{
	
	@QueryHints(value = @QueryHint(name = "HINT_FETCH_SIZE", value = ""+Integer.MIN_VALUE))
	@Query("from Gadget g") 
	Stream<Gadget> getAll();
}

