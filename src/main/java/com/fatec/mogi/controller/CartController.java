package com.fatec.mogi.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fatec.mogi.model.domain.Cart;
@RestController
@RequestMapping("/cart")
@CrossOrigin(origins = "*", maxAge = 3600L)
public class CartController extends AbstractController<Cart> {

	public CartController() {
		super(Cart.class);
	}


}
