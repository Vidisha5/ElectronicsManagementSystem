package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.dao.GadgetRepository;
import com.example.demo.domain.Gadget;




@SpringBootTest
@RunWith(SpringRunner.class)
class StarterProjectApplicationTests {

	@Autowired
	GadgetRepository gadgetRepository;
	
	
	@Test
	public void testFindById() {
		String gadgetName="Tablets";
		Optional<Gadget> optionalGadget=gadgetRepository.findById("2");
		System.out.println(optionalGadget.get().getGadgetName());
		assertThat(optionalGadget.get().getGadgetName()).isEqualTo(gadgetName);	
		
	}

}
