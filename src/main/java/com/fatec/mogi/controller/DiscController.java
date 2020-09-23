package com.fatec.mogi.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fatec.mogi.model.domain.Disc;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600L)
@RequestMapping("/disc")
public class DiscController extends AbstractController<Disc> {

	public DiscController() {
		super(Disc.class);

	}

}
