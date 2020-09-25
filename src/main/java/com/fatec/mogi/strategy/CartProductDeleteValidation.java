package com.fatec.mogi.strategy;

import java.util.Collections;
import java.util.Comparator;

import com.fatec.mogi.model.domain.CartProduct;
import com.fatec.mogi.model.domain.DomainEntity;
import com.fatec.mogi.repository.CartProductRepository;
import com.fatec.mogi.repository.DiscRepository;

public class CartProductDeleteValidation implements IStrategy {

	DiscRepository discRepository;

	CartProductRepository cartProductRepository;

	public CartProductDeleteValidation(DiscRepository discRepository, CartProductRepository cartProductRepository) {
		this.discRepository = discRepository;
		this.cartProductRepository = cartProductRepository;
	}

	@Override
	public String process(DomainEntity entity) {
		var cartProduct = (CartProduct) entity;
		var sb = new StringBuilder();

		if (cartProduct.getId() == null) {
			sb.append("Comando invalido");
		}
		if (sb.length() > 0) {
			return sb.toString();
		}
		var optionalCartProduct = cartProductRepository.findById(cartProduct.getId());
		if (optionalCartProduct.isEmpty()) {
			sb.append("Disco invalido");
			return sb.toString();
		}

		var oldCart = optionalCartProduct.get();
		// get stock with biggest price

		var stock = Collections.max(oldCart.getDisc().getStock(), Comparator.comparing(s -> s.getCostPrice()));

		stock.setQuantity(stock.getQuantity() + oldCart.getQuantity());

		cartProduct.setDisc(oldCart.getDisc());

		return sb.toString();
	}

}
