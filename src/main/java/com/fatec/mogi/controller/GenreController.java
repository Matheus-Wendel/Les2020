package com.fatec.mogi.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fatec.mogi.model.domain.Genre;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600L)
@RequestMapping("/genre")
public class GenreController extends AbstractController<Genre> {

	public GenreController() {
		super(Genre.class);

	}

}
