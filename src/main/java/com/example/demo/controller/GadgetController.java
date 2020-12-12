package com.example.demo.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
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

import com.example.demo.exception.RecordNotFoundException;
import com.example.demo.dao.GadgetRepository;
import com.example.demo.domain.Gadget;






@RestController
@RequestMapping("/api/v1/gadgets")
public class GadgetController {
	
	private static final Logger LOGGER=LoggerFactory.getLogger(GadgetController.class);
	
	
	private GadgetRepository gadgetRepository;
	
	
	
	
	
	@Autowired
	public GadgetController(GadgetRepository gadgetRepository) {
		this.gadgetRepository = gadgetRepository;
	
	}

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/{gadgetId}")
	public ResponseEntity<Gadget> getById(@PathVariable String gadgetId){
		LOGGER.info("gadgetId"+gadgetId);
		
		Optional<Gadget> optionalGadget=gadgetRepository.findById(gadgetId);
		if(!optionalGadget.isPresent()) {
			throw new RecordNotFoundException("Record Not found");
		}
		
		return ResponseEntity.ok(optionalGadget.get());
	}
	
	
	@PreAuthorize("hasAnyRole('USER','SALES','ADMIN')")
	@GetMapping
	@Transactional(readOnly=true)
	public List<Gadget>  getAll() {
		
		List<Gadget> mapStream=new ArrayList<>();
		//gadgetRepository.findAll().forEach(list::add);
		/*
		 * gadgetRepository.getAll().forEach(gadget->{ list.add(gadget); });
		 */
		try(Stream<Gadget> gadgetStream=gadgetRepository.getAll()){
			 mapStream = gadgetStream.collect(Collectors.toList());
		}
		return mapStream;
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping
	public ResponseEntity<Gadget> create(@RequestBody Gadget gadget) {
		Gadget savedGadget=gadgetRepository.save(gadget);
		URI location=ServletUriComponentsBuilder.fromCurrentRequest().path("/{gadgetId}")
				.buildAndExpand(savedGadget.getGadgetId()).toUri();
		return ResponseEntity.created(location).body(savedGadget);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
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
	

	@PreAuthorize("hasRole('ADMIN')")
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
	
	
	@PreAuthorize("hasRole('ADMIN')")
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
