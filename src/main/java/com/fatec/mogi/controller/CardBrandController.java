package com.fatec.mogi.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fatec.mogi.model.domain.CardBrand;
@RestController
@RequestMapping("/cardBrand")
@CrossOrigin(origins = "*", maxAge = 3600L)
public class CardBrandController extends AbstractController<CardBrand> {

	public CardBrandController() {
		super(CardBrand.class);
	}


}
