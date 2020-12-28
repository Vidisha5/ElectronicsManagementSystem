package com.example.demo.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.example.demo.ElectronicsManagementSystemApplication;
import com.example.demo.dao.GadgetRepository;
import com.example.demo.domain.Gadget;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=ElectronicsManagementSystemApplication.class,webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GadgetControllerTest {
	

	@MockBean
	private GadgetController gadgetControllerMock;
	
	@MockBean
	private GadgetRepository gadgetRepository;

	private static String URL = "/api/v1/gadgets";

	private static String GADGET_ID = "1";

	@Mock
	private Gadget gadgetMock;
	


	@Test
	public void testGetOne() throws Exception {

		Optional<Gadget> gadget=Optional.of(new Gadget("1","Samsung J7 Next",10000.0,"Mobile Phones"));

		Mockito.when(gadgetRepository.findById(GADGET_ID)).thenReturn(gadget);
		
		Gadget gadget2=gadget.get();
		when(gadgetControllerMock.getById(GADGET_ID)).thenReturn(gadget2);
		 
		assertTrue(gadgetControllerMock.getById(GADGET_ID).getGadgetName().contains("Samsung J7 Next"));
		
		

	}
	
	
	
	
	

}
