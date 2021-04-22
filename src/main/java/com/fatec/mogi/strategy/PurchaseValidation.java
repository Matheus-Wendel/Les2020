package com.fatec.mogi.strategy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import com.fatec.mogi.auth.AuthUtils;
import com.fatec.mogi.enumeration.PurchaseItemEnum;
import com.fatec.mogi.enumeration.SaleStatusEnum;
import com.fatec.mogi.model.domain.CartProduct;
import com.fatec.mogi.model.domain.DomainEntity;
import com.fatec.mogi.model.domain.Purchase;
import com.fatec.mogi.model.domain.PurchaseCard;
import com.fatec.mogi.model.domain.PurchaseItem;
import com.fatec.mogi.model.domain.Stock;
import com.fatec.mogi.repository.CreditCardRepository;

public class PurchaseValidation implements IStrategy {

	CreditCardRepository creditCardRepository;

	public PurchaseValidation(CreditCardRepository creditCardRepository) {
		this.creditCardRepository = creditCardRepository;
	}

	@Override
	public String process(DomainEntity entity) {
		var purchase = (Purchase) entity;
		var sb = new StringBuilder();

		var client = AuthUtils.getLoggedClient();
		if (client.getCart().getCartProducts().isEmpty()) {
			sb.append("Carrinho de compras vazio");
		}
		if (purchase.getPurchaseCards().isEmpty() && purchase.getTradeCoupons().isEmpty()) {
			sb.append("Nenhuma forma de pagamento selecionada");
		}
		if (purchase.getDeliveryAddress() == null) {
			sb.append("Endereço de entrega não selecionado");
		}
		if (purchase.getPurchaseCards() != null) {
			for (PurchaseCard purchaseCard : purchase.getPurchaseCards()) {
				var card = creditCardRepository.findById(purchaseCard.getCreditCard().getId());
				if (card.isPresent()) {
					if (!card.get().isVailid()) {
						sb.append("Um ou mais cartões selecionados são invalidos");
						break;
					}
				}
			}
		}

		if (sb.length() > 0) {
			return sb.toString();
		}
		var cartProducts = client.getCart().getCartProducts();
		double purchaseValue = 0;
		List<PurchaseItem> purchaseItems = new ArrayList<>();
		for (CartProduct cartProduct : cartProducts) {
			Stock MaxValuestock = Collections.max(cartProduct.getDisc().getStock(),
					Comparator.comparing(s -> s.getCostPrice()));

			double discPriceProfit = 0;
			if (cartProduct.getDisc().getPricing().getSale().getStatus() == SaleStatusEnum.ACTIVE) {
				discPriceProfit = cartProduct.getDisc().getPricing().getSale().getProfit();
			} else {
				discPriceProfit = cartProduct.getDisc().getPricing().getDefautProfit();
			}

			purchaseValue += discPriceProfit * MaxValuestock.getCostPrice() * cartProduct.getQuantity();
			for (int i = 0; i < cartProduct.getQuantity(); i++) {
				purchaseItems.add(new PurchaseItem(PurchaseItemEnum.PROCESSING, cartProduct.getDisc()));

			}
		}
		double valueSum = purchase.getPurchaseCards().stream().mapToDouble(s -> s.getValue()).sum();
		if (purchaseValue != valueSum) {
			var firstCard = purchase.getPurchaseCards().get(0);
			firstCard.setValue(firstCard.getValue() + purchaseValue - valueSum);
		}
		purchase.setClient(client);
		purchase.setPurchaseDate(new Date());
		purchase.setValue(purchaseValue);
		purchase.setPurchaseItems(purchaseItems);

		return sb.toString();
	}

}
