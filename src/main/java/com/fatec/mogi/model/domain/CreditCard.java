package com.fatec.mogi.model.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fatec.mogi.serializer.DomainEntityIdSerializer;
import com.fatec.mogi.util.ConstantsUtil;

@Entity
public class CreditCard extends DomainEntity {
	@Column(nullable = false)
	private String number;
	@Column(nullable = false)
	private String name;
	@Size(max = 3, min = 3)
	@Column(nullable = false)
	private String cvv;
	@DateTimeFormat(pattern = ConstantsUtil.CREDIT_CARD_EXPIRATION_DATE_FORMAT)
	@Temporal(TemporalType.DATE)
	@JsonFormat(pattern = "yyyy-MM")
	private Date expirationDate;
	@Column(nullable = false)
	private boolean vailid;
	@ManyToOne
	@JoinColumn(name="client_id", referencedColumnName="id")
	@JsonSerialize(using = DomainEntityIdSerializer.class)
	private Client client;
	@ManyToOne
	private CardBrand cardBrand;

	public CardBrand getCardBrand() {
		return cardBrand;
	}

	public void setCardBrand(CardBrand cardBrand) {
		this.cardBrand = cardBrand;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCvv() {
		return cvv;
	}

	public void setCvv(String cvv) {
		this.cvv = cvv;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	public boolean isVailid() {
		return vailid;
	}

	public void setVailid(boolean vailid) {
		this.vailid = vailid;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

}
