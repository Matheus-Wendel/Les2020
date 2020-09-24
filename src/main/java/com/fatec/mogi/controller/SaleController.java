package com.fatec.mogi.controller;

import com.fatec.mogi.model.domain.Sale;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600L)
@RequestMapping("/sale")
public class SaleController extends AbstractController<Sale> {

	public SaleController() {
		super(Sale.class);

	}

}
