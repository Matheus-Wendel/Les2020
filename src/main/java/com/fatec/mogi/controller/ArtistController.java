package com.fatec.mogi.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fatec.mogi.model.domain.Artist;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600L)
@RequestMapping("/artist")
public class ArtistController extends AbstractController<Artist> {

	public ArtistController() {
		super(Artist.class);

	}

}
