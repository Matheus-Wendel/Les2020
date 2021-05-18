package com.fatec.mogi.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fatec.mogi.model.domain.Trade;
@RestController
@RequestMapping("/trade")
@CrossOrigin(origins = "*", maxAge = 3600L)
public class TradeController extends AbstractController<Trade> {

	public TradeController() {
		super(Trade.class);
	}


}
