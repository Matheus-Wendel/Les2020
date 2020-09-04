package com.fatec.mogi.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fatec.mogi.model.domain.Client;
@RestController
@RequestMapping("/client")
@CrossOrigin(origins = "*", maxAge = 3600L)
public class ClientController extends AbstractController<Client> {

	public ClientController() {
		super(Client.class);
	}


}
