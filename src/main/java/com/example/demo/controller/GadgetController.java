package com.example.demo.controller;

import java.net.URI;
import java.util.Optional;

import javax.annotation.security.RolesAllowed;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//import javax.annotation.security.RolesAllowed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
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

import com.example.demo.dao.GadgetRepository;
import com.example.demo.domain.Gadget;




@RestController
@RequestMapping("/api/v1/gadgets")
public class GadgetController {
	
	Logger logger=LoggerFactory.getLogger(GadgetController.class);
	
	private GadgetRepository gadgetRepository;
	
	
	
	@Autowired
	public GadgetController(GadgetRepository gadgetRepository) {
		this.gadgetRepository = gadgetRepository;
	}

	@RolesAllowed("SALES")
	@GetMapping("/{gadgetId}")
	public ResponseEntity<Gadget> getById(@PathVariable String gadgetId) {
		logger.info("gadgetId"+gadgetId);
		
		Optional<Gadget> optionalGadget=gadgetRepository.findById(gadgetId);
		if(!optionalGadget.isPresent()) {
			return ResponseEntity.unprocessableEntity().build();
		}
		return ResponseEntity.ok(optionalGadget.get());
	}
	
	
	@RolesAllowed({"SALES","USER"})
	@GetMapping
	public ResponseEntity<Page<Gadget>> getAll(Pageable pageable) {
		return ResponseEntity.ok(gadgetRepository.findAll(pageable));
	}
	
	@RolesAllowed("SALES")
	@PostMapping
	public ResponseEntity<Gadget> create(@RequestBody Gadget gadget) {
		Gadget savedGadget=gadgetRepository.save(gadget);
		URI location=ServletUriComponentsBuilder.fromCurrentRequest().path("/{gadgetId}")
				.buildAndExpand(savedGadget.getGadgetId()).toUri();
		return ResponseEntity.created(location).body(savedGadget);
	}
	
	@RolesAllowed("SALES")
	@PutMapping("/{gadgetId}")
	public ResponseEntity<Gadget> update(@PathVariable String gadgetId,@RequestBody Gadget gadget) {
		
		Optional<Gadget> optionalGadget=gadgetRepository.findById(gadgetId);
		if(!optionalGadget.isPresent()) {
			return ResponseEntity.unprocessableEntity().build();
		}
		
		gadget.setGadgetId(optionalGadget.get().getGadgetId());
		gadgetRepository.save(gadget);
		
		
		return ResponseEntity.noContent().build();
	}
	

	@RolesAllowed("SALES")
	@PatchMapping("/{gadgetId}")
	public ResponseEntity<Gadget> patch(@PathVariable String gadgetId,@RequestBody Gadget gadget) {
		
		Optional<Gadget> optionalGadget=gadgetRepository.findById(gadgetId);
		if(!optionalGadget.isPresent()) {
			return ResponseEntity.unprocessableEntity().build();
		}
		
		gadget.setGadgetId(optionalGadget.get().getGadgetId());
		gadgetRepository.save(gadget);
		
		
		return ResponseEntity.noContent().build();
	}
	
	
	@RolesAllowed("SALES")
	@DeleteMapping("/{gadgetId}")
	public ResponseEntity<Gadget> delete(@PathVariable String gadgetId) {
		Optional<Gadget> optionalGadget=gadgetRepository.findById(gadgetId);
		if(!optionalGadget.isPresent()) {
			return ResponseEntity.unprocessableEntity().build();
		}
		
		gadgetRepository.delete(optionalGadget.get());
		return ResponseEntity.noContent().build();
	}
	
	
}
