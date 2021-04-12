package com.fatec.mogi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fatec.mogi.model.aplication.Filter;
import com.fatec.mogi.model.domain.CartProduct;
import com.fatec.mogi.model.domain.Disc;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600L)
@RequestMapping("/cartProduct")
public class CartProductController extends AbstractController<CartProduct> {

	public CartProductController() {
		super(CartProduct.class);

	}
	
	@PostMapping("/add")
	public ResponseEntity addProductToCart(@RequestBody Disc entity) {
		var c = new CartProduct();
		c.setDisc(entity);
		Filter<CartProduct> filter = new Filter<CartProduct>(c, clazz);
		return saveCommand.execute(filter).buildResponse();
	}


}
