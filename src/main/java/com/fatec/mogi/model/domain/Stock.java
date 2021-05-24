package com.fatec.mogi.model.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fatec.mogi.util.ConstantsUtil;

@Entity
public class Stock extends DomainEntity {
	@Column(nullable = false)
	private int quantity;
	@ManyToOne
	@JoinColumn(name = "disc_stock_id", referencedColumnName = "id")
	private Disc disc;
	@Column(nullable = false)
	private double costPrice;
	@Column(nullable = false)
	@DateTimeFormat(pattern = ConstantsUtil.TIMESTAMP_FORMAT)
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern = ConstantsUtil.TIMESTAMP_FORMAT)
	private Date purchaceDate;

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

	public double getCostPrice() {
		return costPrice;
	}

	public void setCostPrice(double costPrice) {
		this.costPrice = costPrice;
	}

	public Date getPurchaceDate() {
		return purchaceDate;
	}

	public void setPurchaceDate(Date purchaceDate) {
		this.purchaceDate = purchaceDate;
	}

}
