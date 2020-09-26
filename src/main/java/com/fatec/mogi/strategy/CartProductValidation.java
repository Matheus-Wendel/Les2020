package com.fatec.mogi.strategy;

import java.util.Date;

import com.fatec.mogi.auth.AuthUtils;
import com.fatec.mogi.model.domain.CartProduct;
import com.fatec.mogi.model.domain.DomainEntity;
import com.fatec.mogi.model.domain.Stock;
import com.fatec.mogi.repository.DiscRepository;

public class CartProductValidation implements IStrategy {

	DiscRepository discRepository;

	public CartProductValidation(DiscRepository discRepository) {
		this.discRepository = discRepository;
	}

	@Override
	public String process(DomainEntity entity) {
		var cartProduct = (CartProduct) entity;
		var sb = new StringBuilder();

		if (cartProduct.getQuantity() == 0) {
			sb.append("Quantidade invalida");
		}
		if (cartProduct.getDisc() == null || cartProduct.getDisc().getId() == null) {
			sb.append("Disco invalido");
		}
		if (sb.length() > 0) {
			return sb.toString();
		}
		var optionalDisc = discRepository.findById(cartProduct.getDisc().getId());
		if (optionalDisc.isEmpty()) {
			sb.append("Disco invalido");
			return sb.toString();
		}
		var disc = optionalDisc.get();
		int discTotalStock = disc.getStock().stream().mapToInt(s -> s.getQuantity()).sum();
		if (discTotalStock < cartProduct.getQuantity()) {
			sb.append("Quantidade adicionada maior que o estoque do produto");
		}
		var clientCart = AuthUtils.getLoggedClient().getCart();
		var present = clientCart.getCartProducts().stream().anyMatch(cp -> cp.getDisc().getId() == disc.getId());
		if (present) {
			sb.append("Disco ja esta no carrinho");
		}
		if (sb.length() == 0) {
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
			cartProduct.setCart(clientCart);
			cartProduct.setDisc(disc);
			cartProduct.setAddedDate(new Date());

		}
		return sb.toString();
	}

}
