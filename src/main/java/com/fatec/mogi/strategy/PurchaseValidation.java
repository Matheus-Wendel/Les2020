package com.fatec.mogi.strategy;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import com.fatec.mogi.auth.AuthUtils;
import com.fatec.mogi.enumeration.CouponTypeEnum;
import com.fatec.mogi.enumeration.PurchaseStatus;
import com.fatec.mogi.model.domain.ActivationDetails;
import com.fatec.mogi.model.domain.CartProduct;
import com.fatec.mogi.model.domain.Coupon;
import com.fatec.mogi.model.domain.Disc;
import com.fatec.mogi.model.domain.DomainEntity;
import com.fatec.mogi.model.domain.Purchase;
import com.fatec.mogi.model.domain.PurchaseCard;
import com.fatec.mogi.model.domain.PurchaseItem;
import com.fatec.mogi.repository.CreditCardRepository;
import com.fatec.mogi.repository.DiscRepository;

public class PurchaseValidation implements IStrategy {

	CreditCardRepository creditCardRepository;
	private DiscRepository discRepository;

	public PurchaseValidation(CreditCardRepository creditCardRepository, DiscRepository discRepository) {
		this.creditCardRepository = creditCardRepository;
		this.discRepository = discRepository;
	}

	@Override
	public String process(DomainEntity entity) {
		var purchase = (Purchase) entity;
		var sb = new StringBuilder();

		var client = AuthUtils.getLoggedClient();
		if (client.getCart().getCartProducts().isEmpty()) {
			sb.append("Carrinho de compras vazio;;");
			return sb.toString();
		}
		if (purchase.getPurchaseCards().isEmpty() && purchase.getTradeCoupons().isEmpty() && purchase.getPromotionalCoupon().getId()==null ) {
			sb.append("Nenhuma forma de pagamento selecionada;;");
		}
		if (purchase.getDeliveryAddress() == null) {
			sb.append("Endereço de entrega não selecionado;;");
		}
		if (purchase.getPurchaseCards() != null) {
			for (PurchaseCard purchaseCard : purchase.getPurchaseCards()) {
				var card = creditCardRepository.findById(purchaseCard.getCreditCard().getId());
				if (card.isPresent()) {
					if (!card.get().isVailid()) {
						sb.append("O Cartão bandeira: " + card.get().getCardBrand().getDescription() + " é invalido;;");
						break;
					}
				}
			}
		}
		double purchaseValue = client.getCart().getTotal();
		double valueInTradeCoupons = 0;
		if (purchase.getTradeCoupons() != null && !purchase.getTradeCoupons().isEmpty()) {
			valueInTradeCoupons = purchase.getTradeCoupons().stream().mapToDouble(s -> s.getValue()).sum();

		}
		double valueInPromotionalCoupon = 0;
		if (purchase.getPromotionalCoupon() != null) {
			valueInPromotionalCoupon = purchase.getPromotionalCoupon().getValue();
		}
		double valueInCoupons = valueInTradeCoupons + valueInPromotionalCoupon;
		// VALOR EM CUPOM MAIOR QUE O DA COMPRA
		if (purchaseValue - valueInCoupons<= 0) {
			if (!purchase.getPurchaseCards().isEmpty()) {
				sb.append("Valor pago em cupons igual ou superior ao valor da compra, remova os cartões da compra;;");
				return sb.toString();
			}
			if (purchaseValue - valueInPromotionalCoupon <= 0 && valueInTradeCoupons != 0) {
				sb.append(
						"Valor pago em cupom promocional igual ou superior ao valor da compra, remova os cupons de troca;;");
				return sb.toString();
			}
			double exchangeCouponValue = purchaseValue - valueInTradeCoupons - valueInPromotionalCoupon;
			if (valueInTradeCoupons != 0) {

				Coupon c = new Coupon();
				c.setActive(true);
				c.setCode(String.valueOf(new Date().getTime()));
				c.setType(CouponTypeEnum.TRADE);
				c.setValue(Math.abs(exchangeCouponValue));
				Calendar date = Calendar.getInstance();
				Date expirationDate = new GregorianCalendar(date.get(Calendar.YEAR) + 1, date.get(Calendar.MONTH),
						date.get(Calendar.DAY_OF_MONTH)).getTime();
				c.setExpirationDate(expirationDate);
				c.setClient(client);
				purchase.setChangeCoupon(c);
				
				
			}

		}else {
			//VALOR EM CUPOM MENOR QUE O DA COMPRA
			double paymentInCard = purchaseValue - valueInCoupons;
			if(purchase.getPurchaseCards().isEmpty() ) {
				sb.append("Valor em cupons não é sufuciente para finalizar a compra, adicione um cartão a compra;;");
				return sb.toString();
			}
			
			if(paymentInCard<=10  &&purchase.getPurchaseCards().size()>=2) {
				sb.append("Valor minimo por cartão é R$10, remova cartões da compra;;");
				return sb.toString();
			}
			double valueSum = purchase.getPurchaseCards().stream().mapToDouble(s -> s.getValue()).sum();
			
			if(valueSum>purchaseValue) {
				sb.append("Valor definido para os cartões excede o valor total da compra, revise os valores por cartão;;");
				return sb.toString();
			}
			
			if(valueSum<purchaseValue) {
				var firstCard = purchase.getPurchaseCards().get(0);
				firstCard.setValue(firstCard.getValue() + purchaseValue - valueSum-valueInCoupons);
			}
			
		}
		if (sb.length() > 0) {
			return sb.toString();
		}
		var cartProducts = client.getCart().getCartProducts();

		List<PurchaseItem> purchaseItems = new ArrayList<>();
		for (CartProduct cartProduct : cartProducts) {
			var disc = cartProduct.getDisc();
			Disc dbDisc = discRepository.findById(disc.getId()).get();
			if(dbDisc.getTotalStock()==0) {
				ActivationDetails activationDetails = dbDisc.getActivationDetails();
				disc.setActive(false);
				activationDetails.setCategory("FORA DE MERCADO");
				activationDetails.setMotive("INATIVAÇÂO AUTOMATICA");
				disc.setActivationDetails(activationDetails);
			}
		//TODO ENUM REMOVIDO DE ITEM DE COMPRA , TROCAR CONSTRUTOR COM INSTACIA DE TROCA	
			for (int i = 0; i < cartProduct.getQuantity(); i++) {
				purchaseItems.add(new PurchaseItem( cartProduct.getDisc(),cartProduct.getDisc().getValue()));

			}

		}
		if(purchase.getPromotionalCoupon().getId()==null) {
			purchase.setPromotionalCoupon(null);
		}

		for (Coupon tradecpn : purchase.getTradeCoupons()) {
			tradecpn.setActive(false);
		}
		purchase.setClient(client);
		purchase.setPurchaseDate(new Date());
		purchase.setValue(purchaseValue);
		purchase.setPurchaseItems(purchaseItems);
		purchase.setPurchaseStatus(PurchaseStatus.PROCESSING);

		return sb.toString();
	}

}
