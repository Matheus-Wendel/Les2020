package com.fatec.mogi.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fatec.mogi.model.aplication.Filter;
import com.fatec.mogi.model.domain.Purchase;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600L)
@RequestMapping("/purchase")
public class PurchaseController extends AbstractController<Purchase> {

	public PurchaseController() {
		super(Purchase.class);

	}

	@SuppressWarnings("rawtypes")
	@GetMapping(path = "/all")
	public ResponseEntity find(@RequestParam Map<String, String> parameters) {
		Filter<Purchase> filter = new Filter<Purchase>(parameters, this.clazz);
		return findCommand.execute(filter).buildResponse();
	}

}
