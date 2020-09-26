package com.fatec.mogi.model.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class PurchaseCard extends DomainEntity {
	@Column(nullable = false)
	private double value;
	@ManyToOne

	@JsonIgnoreProperties("client")
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
