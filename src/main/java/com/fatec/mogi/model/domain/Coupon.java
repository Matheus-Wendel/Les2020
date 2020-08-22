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
public class Coupon extends DomainEntity {
	@Column(nullable = false)
	private double value;
	@Column(nullable = false)
	private boolean active;
	@Column(nullable = false)
	private String type;
	@Column(nullable = false)
	@DateTimeFormat(pattern = ConstantsUtil.DATE_FORMAT)
	@Temporal(TemporalType.DATE)
	private Date ExpirationDate;
	@Column(nullable = false)
	private String code;
	@ManyToOne
	private Client client;

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getExpirationDate() {
		return ExpirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		ExpirationDate = expirationDate;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

}
