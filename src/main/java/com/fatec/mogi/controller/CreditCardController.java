package com.fatec.mogi.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fatec.mogi.model.domain.CreditCard;
@RestController
@RequestMapping("/creditCard")
@CrossOrigin(origins = "*", maxAge = 3600L)
public class CreditCardController extends AbstractController<CreditCard> {

	public CreditCardController() {
		super(CreditCard.class);
	}


}
