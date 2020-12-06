package com.example.demo.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.dao.AccessoryRepository;
import com.example.demo.domain.Accessory;

@RestController
@RequestMapping("/api/v1/accessories")
public class AccessoryController {
	
	@Autowired
	AccessoryRepository accessoryRepository;
	
	@PreAuthorize("hasRole('SALES')")
	@GetMapping
	public List<Accessory> getAccessories(){
		List<Accessory> accessoryList=new ArrayList<>();
		accessoryRepository.findAll().forEach(accessoryList::add);
		return accessoryList;
	}
	
	@PreAuthorize("hasRole('SALES')")
	@PostMapping
	public ResponseEntity<Accessory> create(@RequestBody Accessory accessory) {
		Accessory savedAccessory=accessoryRepository.save(accessory);
		URI location=ServletUriComponentsBuilder.fromCurrentRequest().path("/{accessoryId}")
				.buildAndExpand(savedAccessory.getAccessoryId()).toUri();
		return ResponseEntity.created(location).body(savedAccessory);
	}
	
	@PreAuthorize("hasRole('SALES')")
	@PutMapping("/{accessoryId}")
	public ResponseEntity<Accessory> update(@PathVariable String accessoryId,@RequestBody Accessory accessory) {
		
		Optional<Accessory> optionalAccessory=accessoryRepository.findById(accessoryId);
		if(!optionalAccessory.isPresent()) {
			return ResponseEntity.unprocessableEntity().build();
		}
		
		accessory.setAccessoryId(optionalAccessory.get().getAccessoryId());
		accessoryRepository.save(accessory);
		
		
		return ResponseEntity.noContent().build();
	}
	

	@PreAuthorize("hasRole('SALES')") 
	@PatchMapping("/{accessoryId}")
	public ResponseEntity<Accessory> patch(@PathVariable String accessoryId,@RequestBody Accessory accessory) {
		
		Optional<Accessory> optionalAccessory=accessoryRepository.findById(accessoryId);
		if(!optionalAccessory.isPresent()) {
			return ResponseEntity.unprocessableEntity().build();
		}
		
		accessory.setAccessoryId(optionalAccessory.get().getAccessoryId());
		accessoryRepository.save(accessory);
		
		
		return ResponseEntity.noContent().build();
	}
	
	
	@PreAuthorize("hasRole('SALES')")
	@DeleteMapping("/{accessoryId}")
	public ResponseEntity<Accessory> delete(@PathVariable String accessoryId) {
		Optional<Accessory> optionalAccessory=accessoryRepository.findById(accessoryId);
		if(!optionalAccessory.isPresent()) {
			return ResponseEntity.unprocessableEntity().build();
		}
		
		accessoryRepository.delete(optionalAccessory.get());
		return ResponseEntity.noContent().build();
	}

}
