package com.fatec.mogi.model.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fatec.mogi.enumeration.PurchaseStatus;
import com.fatec.mogi.util.ConstantsUtil;

@Entity
public class Purchase extends DomainEntity {
	@Column(nullable = false)
	@DateTimeFormat(pattern = ConstantsUtil.TIMESTAMP_FORMAT)
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern = ConstantsUtil.TIMESTAMP_FORMAT)
	private Date purchaseDate;
	@Column(nullable = false)
	private double value;
	@ManyToMany
	private List<Coupon> tradeCoupons;
	@ManyToOne(cascade = CascadeType.MERGE)
	private Coupon promotionalCoupon;
	@OneToMany
	private List<PurchaseCard> purchaseCards;
	@OneToMany
//	@JsonManagedReference(value = "purchase")
	private List<PurchaseItem> purchaseItems;
	@ManyToOne
	@JoinColumn(name = "client_id", referencedColumnName = "id")
	@JsonIgnoreProperties("purchases")
	private Client client;
	@ManyToOne
	@JoinColumn(name = "address_id", referencedColumnName = "id")
	private Address deliveryAddress;
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private PurchaseStatus purchaseStatus;
	@OneToOne(cascade = CascadeType.PERSIST)
	private Coupon changeCoupon;

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

	public List<Coupon> getTradeCoupons() {
		return tradeCoupons;
	}

	public void setTradeCoupons(List<Coupon> coupons) {
		this.tradeCoupons = coupons;
	}

	public Coupon getPromotionalCoupon() {
		return promotionalCoupon;
	}

	public void setPromotionalCoupon(Coupon promotionalCoupon) {
		this.promotionalCoupon = promotionalCoupon;
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

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Address getDeliveryAddress() {
		return deliveryAddress;
	}

	public void setDeliveryAddress(Address deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	public PurchaseStatus getPurchaseStatus() {
		return purchaseStatus;
	}

	public void setPurchaseStatus(PurchaseStatus purchaseStatus) {
		this.purchaseStatus = purchaseStatus;
	}

	public Coupon getChangeCoupon() {
		return changeCoupon;
	}

	public void setChangeCoupon(Coupon changeCoupon) {
		this.changeCoupon = changeCoupon;
	}

}
