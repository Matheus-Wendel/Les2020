package com.fatec.mogi.model.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;

import com.fatec.mogi.enumeration.PurchaseItemEnum;

@Entity
public class PurchaseItem extends DomainEntity {
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private PurchaseItemEnum status;
	@ManyToOne
	private Disc disc;

	public PurchaseItem() {
	}
	public PurchaseItem(PurchaseItemEnum status, Disc disc) {
		this.status = status;
		this.disc = disc;
	}

	public PurchaseItemEnum getStatus() {
		return status;
	}

	public void setStatus(PurchaseItemEnum status) {
		this.status = status;
	}

	public Disc getDisc() {
		return disc;
	}

	public void setDisc(Disc disc) {
		this.disc = disc;
	}

}
