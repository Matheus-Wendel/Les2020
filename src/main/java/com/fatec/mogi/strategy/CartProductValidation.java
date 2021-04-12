package com.fatec.mogi.strategy;

import java.util.Date;
import java.util.List;

import com.fatec.mogi.auth.AuthUtils;
import com.fatec.mogi.model.domain.CartProduct;
import com.fatec.mogi.model.domain.DomainEntity;
import com.fatec.mogi.model.domain.Stock;
import com.fatec.mogi.repository.DiscRepository;
import com.fatec.mogi.repository.StockRepository;

public class CartProductValidation implements IStrategy {

	DiscRepository discRepository;

	StockRepository stockRepository;

	public CartProductValidation(DiscRepository discRepository, StockRepository stockRepository) {
		this.discRepository = discRepository;
		this.stockRepository = stockRepository;
	}

	@Override
	public String process(DomainEntity entity) {
		var cartProduct = (CartProduct) entity;
		var sb = new StringBuilder();
		cartProduct.setQuantity(1);

		if (cartProduct.getDisc() == null || cartProduct.getDisc().getId() == null) {
			sb.append("Disco invalido;;");
		}
		if (sb.length() > 0) {
			return sb.toString();
		}
		var optionalDisc = discRepository.findById(cartProduct.getDisc().getId());
		if (optionalDisc.isEmpty()) {
			sb.append("Disco invalido;;");
			return sb.toString();
		}
		var disc = optionalDisc.get();
		List<Stock> discStocks = stockRepository.findByDiscIdOrderByPurchaceDateAsc(disc.getId());
		if (discStocks.isEmpty()) {
			sb.append("Disco não possui nenhum estoque;;");
		}
		int discTotalStock = discStocks.stream().mapToInt(s -> s.getQuantity()).sum();
		if (discTotalStock < cartProduct.getQuantity()) {
			sb.append("Quantidade adicionada maior que o estoque do produto;;");
		}
		var clientCart = AuthUtils.getLoggedClient().getCart();
		var present = clientCart.getCartProducts().stream().anyMatch(cp -> cp.getDisc().getId() == disc.getId());
		if (present) {
			sb.append("Disco ja esta no carrinho;;");
		}
		if (sb.length() == 0) {
			for (Stock stock : discStocks) {
				if (stock.getQuantity() >= 1) {
					stock.setQuantity(stock.getQuantity() - 1);
					break;
				}

			}
			disc.setStock(discStocks);
			cartProduct.setCart(clientCart);
			cartProduct.setDisc(disc);
			cartProduct.setAddedDate(new Date());

		}
		return sb.toString();
	}

}
