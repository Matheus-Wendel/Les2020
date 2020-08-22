package com.fatec.mogi.model.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class PurchaseItem extends DomainEntity {
	@Column(nullable = false)
	private String status;
	@ManyToOne
	private Disc disc;

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Disc getDisc() {
		return disc;
	}
	public void setDisc(Disc disc) {
		this.disc = disc;
	}

}
