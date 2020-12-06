package com.example.demo.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Accessory;

@Repository
public interface AccessoryRepository extends CrudRepository<Accessory,String>{

}
