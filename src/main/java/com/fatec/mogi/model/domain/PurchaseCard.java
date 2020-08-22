package com.fatec.mogi.model.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class PurchaseCard extends DomainEntity {
	@Column(nullable = false)
	private double value;
	@ManyToOne
	private CreditCard creditCard;

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public CreditCard getCreditCard() {
		return creditCard;
	}

	public void setCreditCard(CreditCard creditCard) {
		this.creditCard = creditCard;
	}

}
