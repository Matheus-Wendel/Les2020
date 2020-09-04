package com.fatec.mogi.model.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.fatec.mogi.util.ConstantsUtil;

@Entity
public class CartProduct extends DomainEntity {
	@Column(nullable = false)
	private int quantity;
	@ManyToOne
	private Disc disc;

	@DateTimeFormat(pattern = ConstantsUtil.DATE_FORMAT)
	@Temporal(TemporalType.DATE)
	private Date AddedDate;

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Disc getDisc() {
		return disc;
	}

	public void setDisc(Disc disc) {
		this.disc = disc;
	}

	public Date getAddedDate() {
		return AddedDate;
	}

	public void setAddedDate(Date addedDate) {
		AddedDate = addedDate;
	}

}