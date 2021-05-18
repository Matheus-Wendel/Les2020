package com.fatec.mogi.model.domain;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class PurchaseItem extends DomainEntity {

	@ManyToOne
	private Disc disc;
	@OneToOne(mappedBy = "purchaseItem")
	@JsonIgnoreProperties("purchaseItem")
	private Trade trade;

	private double value;

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public PurchaseItem() {
	}

	public PurchaseItem(Disc disc,double value) {
		this.disc = disc;
		this.value = value;
	}

	public Trade getTrade() {
		return trade;
	}

	public void setTrade(Trade trade) {
		this.trade = trade;
	}

	public Disc getDisc() {
		return disc;
	}

	public void setDisc(Disc disc) {
		this.disc = disc;
	}

}
