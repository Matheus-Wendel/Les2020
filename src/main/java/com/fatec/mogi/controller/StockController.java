package com.fatec.mogi.controller;

import com.fatec.mogi.model.domain.Stock;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600L)
@RequestMapping("/stock")
public class StockController extends AbstractController<Stock> {

	public StockController() {
		super(Stock.class);

	}

}
