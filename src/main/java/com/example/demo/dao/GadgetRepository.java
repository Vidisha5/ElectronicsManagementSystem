package com.example.demo.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Gadget;

@Repository
public interface GadgetRepository extends CrudRepository<Gadget,String>{

	Page<Gadget> findAll(Pageable pageable);
}