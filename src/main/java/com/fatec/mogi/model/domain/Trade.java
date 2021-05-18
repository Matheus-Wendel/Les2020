package com.fatec.mogi.model.domain;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fatec.mogi.enumeration.TradeStatusEnum;
import com.fatec.mogi.util.ConstantsUtil;

@Entity
public class Trade extends DomainEntity {
	@OneToOne
	@JoinColumn(name = "purchase_item_id", referencedColumnName = "id")
//	@JsonManagedReference(value = "trade")
//	@JsonIgnoreProperties("trade")
	private PurchaseItem purchaseItem;
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private TradeStatusEnum status;
	@OneToOne(cascade = CascadeType.PERSIST)
	private Coupon changeCoupon;
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern = ConstantsUtil.TIMESTAMP_FORMAT)
	private Date requestDate;
	@OneToOne
	@JoinColumn(name = "client_id", referencedColumnName = "id")
	@JsonIgnoreProperties("purchases")
	private Client client;
	private Boolean returnToStock;

	public PurchaseItem getPurchaseItem() {
		return purchaseItem;
	}

	public Boolean getReturnToStock() {
		return returnToStock;
	}

	public void setReturnToStock(Boolean returnToStock) {
		this.returnToStock = returnToStock;
	}

	public void setPurchaseItem(PurchaseItem purchaseItem) {
		this.purchaseItem = purchaseItem;
	}

	public TradeStatusEnum getStatus() {
		return status;
	}

	public void setStatus(TradeStatusEnum status) {
		this.status = status;
	}

	public Coupon getChangeCoupon() {
		return changeCoupon;
	}

	public void setChangeCoupon(Coupon changeCoupon) {
		this.changeCoupon = changeCoupon;
	}

	public Date getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

}
