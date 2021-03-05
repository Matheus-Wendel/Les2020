package com.fatec.mogi.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fatec.mogi.model.domain.Client;
import com.fatec.mogi.repository.ClientRepository;

@RestController
@RequestMapping("/test")
@CrossOrigin(origins = "*", maxAge = 3600L)
public class TestController {
@Autowired
ClientRepository clientRepository;
	
	
	@GetMapping
	public Client retornaModel() {
//		var cli = new Client();
//		cli.setBillingAddress(new Address());
//		cli.setDeliveryAddresses( Arrays.asList(new Address()));
//		cli.setUser(new User());
		return clientRepository.findAll().get(0);
	
	}
	@GetMapping("/teste")
	public Map<String, String> retornapara(@RequestParam Map<String,String> parameters) {
//		var cli = new Client();
//		cli.setBillingAddress(new Address());
//		cli.setDeliveryAddresses( Arrays.asList(new Address()));
//		cli.setUser(new User());
		return parameters;
		
	}

}
