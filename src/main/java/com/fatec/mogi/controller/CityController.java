package com.fatec.mogi.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fatec.mogi.model.domain.City;
@RestController
@RequestMapping("/city")
@CrossOrigin(origins = "*", maxAge = 3600L)
public class CityController extends AbstractController<City> {

	public CityController() {
		super(City.class);
	}


}
