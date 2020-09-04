package com.fatec.mogi.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fatec.mogi.model.domain.Country;
@RestController
@RequestMapping("/country")
@CrossOrigin(origins = "*", maxAge = 3600L)
public class CountryController extends AbstractController<Country> {

	public CountryController() {
		super(Country.class);
	}


}
