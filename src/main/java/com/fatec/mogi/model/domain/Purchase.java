package com.fatec.mogi.model.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.fatec.mogi.util.ConstantsUtil;

@Entity
public class Purchase extends DomainEntity {
	@Column(nullable = false)
	@DateTimeFormat(pattern = ConstantsUtil.TIMESTAMP_FORMAT)
	@Temporal(TemporalType.TIMESTAMP)
	private Date purchaseDate;
	@Column(nullable = false)
	private double value;
	@ManyToMany
	private List<Coupon> coupons;
	@OneToMany
	private List<PurchaseCard> purchaseCards;
	@OneToMany
	private List<PurchaseItem> purchaseItems;
	@ManyToOne
	@JoinColumn(name = "client_id", referencedColumnName = "id")
	private Client client;

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Date getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public List<Coupon> getCoupons() {
		return coupons;
	}

	public void setCoupons(List<Coupon> coupons) {
		this.coupons = coupons;
	}

	public List<PurchaseCard> getPurchaseCards() {
		return purchaseCards;
	}

	public void setPurchaseCards(List<PurchaseCard> purchaseCards) {
		this.purchaseCards = purchaseCards;
	}

	public List<PurchaseItem> getPurchaseItems() {
		return purchaseItems;
	}

	public void setPurchaseItems(List<PurchaseItem> purchaseItems) {
		this.purchaseItems = purchaseItems;
	}

}
