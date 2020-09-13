package com.fatec.mogi.strategy;

import java.util.Date;

import com.fatec.mogi.model.domain.CreditCard;
import com.fatec.mogi.model.domain.DomainEntity;
import com.fatec.mogi.repository.CardBrandRepository;

public class CreditCardValidation implements IStrategy {

	CardBrandRepository cardBrandRepository;

	public CreditCardValidation(CardBrandRepository cardBrandRepository) {
		this.cardBrandRepository = cardBrandRepository;
	}

	@Override
	public String process(DomainEntity entity) {
		System.err.println("aaa");
		System.err.println(entity.getClass().getSimpleName());
		var creditCard = (CreditCard) entity;
		var sb = new StringBuilder();
		if (creditCard.getCvv()==null||creditCard.getCvv().length() != 3) {
			sb.append("CVV invalido");

		}
		if (creditCard.getCvv()==null||!creditCard.getCvv().matches("[0-9]+")) {
			sb.append("CVV deve conter apenas numeros");
		}
		if (creditCard.getExpirationDate() != null) {
			if (creditCard.getExpirationDate().compareTo(new Date()) < 0) {
				sb.append("Data de expiração invalida");
			}

		} else {
			sb.append("Data de expiração invalida");
		}
		if (creditCard.getName()==null||creditCard.getName().isBlank()) {
			sb.append("Nome invalido");
		}
		
		if (creditCard.getNumber()==null||!(creditCard.getNumber().matches("[0-9]+")) || creditCard.getNumber().length() != 16) {
			sb.append("Numero de cartão invalido");
		}
		if (creditCard.getCardBrand() != null) {
			if (!cardBrandRepository.existsById(creditCard.getCardBrand().getId())) {
				sb.append("Bandeira do cartão invalida");
			}
		} else {
			sb.append("Bandeira não selecionada");
		}
		if(sb.length()==0) {
			if(creditCard.getCardBrand().getId()==3) {
				creditCard.setVailid(false);
			}
			else {
				creditCard.setVailid(true);
			}
		}
		return sb.toString();
	}

}
