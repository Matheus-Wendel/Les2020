package com.fatec.mogi.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fatec.mogi.model.domain.CartProduct;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600L)
@RequestMapping("/cartProduct")
public class CartProductController extends AbstractController<CartProduct> {

	public CartProductController() {
		super(CartProduct.class);

	}


}
