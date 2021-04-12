package com.fatec.mogi.strategy;

import com.fatec.mogi.auth.AuthUtils;
import com.fatec.mogi.model.domain.CartProduct;
import com.fatec.mogi.model.domain.Client;
import com.fatec.mogi.model.domain.DomainEntity;
import com.fatec.mogi.model.domain.Stock;
import com.fatec.mogi.repository.CartProductRepository;
import com.fatec.mogi.repository.ClientRepository;
import com.fatec.mogi.repository.DiscRepository;
import com.fatec.mogi.repository.StockRepository;

import jdk.jfr.Unsigned;

public class CartProductUpdateValidation implements IStrategy {

	DiscRepository discRepository;

	CartProductRepository cartProductRepository;

	StockRepository stockRepository;

	ClientRepository clientRepository;

	public CartProductUpdateValidation(DiscRepository discRepository, CartProductRepository cartProductRepository,
			StockRepository stockRepository, ClientRepository clientRepository) {
		this.discRepository = discRepository;
		this.cartProductRepository = cartProductRepository;
		this.stockRepository = stockRepository;
		this.clientRepository = clientRepository;

	}

	@Override
	public String process(DomainEntity entity) {
		var cartProduct = (CartProduct) entity;
		var sb = new StringBuilder();

		if (cartProduct.getQuantity() == 0) {
			sb.append("Quantidade invalida;;");
		}
		if (cartProduct.getId() == null) {
			sb.append("comando invalido;;");
		}
		if (sb.length() > 0) {
			return sb.toString();
		}

		var optionalOldCartProduct = cartProductRepository.findById(cartProduct.getId());
		if (optionalOldCartProduct.isEmpty()) {
			sb.append("item não existe no carrinho;;");
			return sb.toString();
		}

		var oldCartProduct = optionalOldCartProduct.get();
		var disc = oldCartProduct.getDisc();
		cartProduct.setDisc(oldCartProduct.getDisc());

		int discTotalStock = disc.getTotalStock();
		var stockQuantitytoChange = oldCartProduct.getQuantity() - cartProduct.getQuantity();

		if (discTotalStock < -stockQuantitytoChange && (stockQuantitytoChange < 0)) {
			sb.append("Quantidade adicionada maior que o estoque do produto " + cartProduct.getDisc().getDescription()
					+ ";;");
		}

		String userEmail = AuthUtils.getUserEmail();
		Client client = clientRepository.findByUserEmail(userEmail);
		if (sb.length() == 0) {

			if (stockQuantitytoChange < 0) {
				var stockList = stockRepository.findByDiscIdOrderByCostPriceDesc(disc.getId());
				int productQuantity = stockQuantitytoChange;
				for (Stock stock : stockList) {
					if (productQuantity == 0) {
						break;
					}
					if (stock.getQuantity() >= -productQuantity) {
						stock.setQuantity(stock.getQuantity() + productQuantity);
						break;
					}
					if (stock.getQuantity() < -productQuantity && stock.getQuantity() > 0) {
						productQuantity = productQuantity + stock.getQuantity();
						stock.setQuantity(0);
					}

				}
			} else if (stockQuantitytoChange > 0) {
				var stockList = stockRepository.findByDiscIdOrderByCostPriceDesc(disc.getId());
				var stock = stockList.get(0);
				stock.setQuantity(stock.getQuantity() + stockQuantitytoChange);

				cartProduct.setDisc(oldCartProduct.getDisc());
				cartProduct.setCart(client.getCart());
			}
			cartProduct.setDisc(disc);
			cartProduct.setCart(client.getCart());

		}
		return sb.toString();
	}

}
