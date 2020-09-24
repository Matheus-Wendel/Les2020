package com.fatec.mogi.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fatec.mogi.model.domain.Pricing;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600L)
@RequestMapping("/pricing")
public class PricingController extends AbstractController<Pricing> {

	public PricingController() {
		super(Pricing.class);

	}

}
