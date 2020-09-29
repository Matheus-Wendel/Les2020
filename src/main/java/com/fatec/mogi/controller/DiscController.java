package com.fatec.mogi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fatec.mogi.model.aplication.Filter;
import com.fatec.mogi.model.domain.Disc;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600L)
@RequestMapping("/disc")
public class DiscController extends AbstractController<Disc> {

	public DiscController() {
		super(Disc.class);

	}

	@SuppressWarnings("rawtypes")
	@GetMapping(path = "/{name}")
	public ResponseEntity find( @PathVariable(name = "name") String name) {
		System.err.println(name);
		var disc = new Disc();
		disc.setName(name);
		Filter<Disc> filter =new Filter<Disc>(disc,this.clazz);
		return findCommand.execute(filter).buildResponse();
	}

}
