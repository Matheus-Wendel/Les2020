package com.fatec.mogi.strategy;

import java.util.List;
import java.util.Optional;

import com.fatec.mogi.model.domain.Cart;
import com.fatec.mogi.model.domain.CartProduct;
import com.fatec.mogi.model.domain.DomainEntity;
import com.fatec.mogi.repository.CartRepository;

public class CartUpdateValidation implements IStrategy {

	CartRepository cartRepository;
	CartProductUpdateValidation cartProductUpdateValidation;

	public CartUpdateValidation(CartRepository cartRepository,CartProductUpdateValidation cartProductUpdateValidation) {
		this.cartRepository = cartRepository;
		this.cartProductUpdateValidation = cartProductUpdateValidation;
	}

	@Override
	public String process(DomainEntity entity) {
		var cartEntity = (Cart) entity;
		var sb = new StringBuilder();

		Optional<Cart> findById = cartRepository.findById(cartEntity.getId());

		if (findById.isEmpty()) {
			sb.append("Erro carrinho não encontrado");
			return sb.toString();
		}

		List<CartProduct> cartProducts = cartEntity.getCartProducts();
		for (CartProduct cartProduct : cartProducts) {
			sb.append(cartProductUpdateValidation.process(cartProduct));
		}
		
		return sb.toString();
	}

}
