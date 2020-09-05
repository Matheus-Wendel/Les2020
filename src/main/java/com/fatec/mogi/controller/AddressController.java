package com.fatec.mogi.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fatec.mogi.model.domain.Address;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600L)
@RequestMapping("/address")
public class AddressController extends AbstractController<Address> {

	public AddressController() {
		super(Address.class);

	}

}
