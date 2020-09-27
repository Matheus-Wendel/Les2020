package com.fatec.mogi.strategy;

import java.util.Collections;
import java.util.Comparator;

import com.fatec.mogi.auth.AuthUtils;
import com.fatec.mogi.model.domain.CartProduct;
import com.fatec.mogi.model.domain.DomainEntity;
import com.fatec.mogi.model.domain.Stock;
import com.fatec.mogi.repository.CartProductRepository;
import com.fatec.mogi.repository.DiscRepository;

public class CartProductUpdateValidation implements IStrategy {

	DiscRepository discRepository;

	CartProductRepository cartProductRepository;

	public CartProductUpdateValidation(DiscRepository discRepository, CartProductRepository cartProductRepository) {
		this.discRepository = discRepository;
		this.cartProductRepository = cartProductRepository;
	}

	@Override
	public String process(DomainEntity entity) {
		var cartProduct = (CartProduct) entity;
		var sb = new StringBuilder();

		if (cartProduct.getQuantity() == 0) {
			sb.append("Quantidade invalida");
		}
		if (cartProduct.getId() == null) {
			sb.append("comando invalido");
		}
		if (sb.length() > 0) {
			return sb.toString();
		}

		var optionalOldCartProduct = cartProductRepository.findById(cartProduct.getId());
		if (optionalOldCartProduct.isEmpty()) {
			sb.append("item não existe no carrinho");
			return sb.toString();
		}
		var oldCartProduct = optionalOldCartProduct.get();
		var disc = oldCartProduct.getDisc();
		cartProduct.setDisc(oldCartProduct.getDisc());

		int discTotalStock = disc.getStock().stream().mapToInt(s -> s.getQuantity()).sum();
		
		if (discTotalStock < cartProduct.getQuantity()&&(oldCartProduct.getQuantity()<cartProduct.getQuantity())) {
			sb.append("Quantidade adicionada maior que o estoque do produto");
		}
		var clientCart = AuthUtils.getLoggedClient().getCart();

		if (sb.length() == 0) {
			if (oldCartProduct.getQuantity() > cartProduct.getQuantity()) {
				var stock = Collections.max(oldCartProduct.getDisc().getStock(),
						Comparator.comparing(s -> s.getCostPrice()));

				stock.setQuantity(stock.getQuantity() + (oldCartProduct.getQuantity() - cartProduct.getQuantity()));

				cartProduct.setDisc(oldCartProduct.getDisc());
			} else {

				var discStocks = disc.getStock();
				int productQuantity = cartProduct.getQuantity();
				for (Stock stock : discStocks) {
					if (productQuantity == 0) {
						break;
					}
					if (stock.getQuantity() >= productQuantity) {
						stock.setQuantity(stock.getQuantity() - productQuantity);
						break;
					}
					if (stock.getQuantity() < productQuantity && stock.getQuantity() > 0) {
						productQuantity = productQuantity - stock.getQuantity();
						stock.setQuantity(0);
					}

				}
			}
			cartProduct.setCart(clientCart);
			cartProduct.setDisc(disc);

		}
		return sb.toString();
	}

}
