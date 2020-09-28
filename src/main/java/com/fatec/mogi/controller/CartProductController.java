package com.fatec.mogi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fatec.mogi.model.domain.CartProduct;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600L)
@RequestMapping("/cartProduct")
public class CartProductController extends AbstractController<CartProduct> {

	public CartProductController() {
		super(CartProduct.class);

	}

	@DeleteMapping
	public ResponseEntity delete(@RequestParam Integer id) {
		var cartProduct = new CartProduct();
		cartProduct.setId(id);

		return super.delete(cartProduct);
	}

}
