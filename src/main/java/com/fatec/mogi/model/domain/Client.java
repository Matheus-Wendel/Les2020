package com.fatec.mogi.model.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Where(clause="active=1")
public class Client extends Person {
	@Column(nullable = false)
	private String code;
	@Column(nullable = false)
	private String genre;
	@Column(nullable = false)
	private String telephone;
	@Column(nullable = false)
	private double ranking;
	@OneToOne
	private Address billingAddress;
	@OneToMany(mappedBy = "client")
	@JsonIgnoreProperties("client")
	private List<Address> deliveryAddresses;
	@OneToMany(mappedBy = "client")
	@JsonIgnoreProperties("client")
	private List<CreditCard> creditCards;
	@OneToOne
	private Cart cart;
	@Column(name = "active")
	private boolean active = true;

	public boolean getActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public double getRanking() {
		return ranking;
	}

	public void setRanking(double ranking) {
		this.ranking = ranking;
	}

	public Address getBillingAddress() {
		return billingAddress;
	}

	public void setBillingAddress(Address billingAddress) {
		this.billingAddress = billingAddress;
	}

	public List<Address> getDeliveryAddresses() {
		return deliveryAddresses;
	}

	public void setDeliveryAddresses(List<Address> deliveryAddresses) {
		this.deliveryAddresses = deliveryAddresses;
	}

	public List<CreditCard> getCreditCards() {
		return creditCards;
	}

	public void setCreditCards(List<CreditCard> creditCards) {
		this.creditCards = creditCards;
	}

	@Override
	public String toString() {
		return "Client [code=" + code + ", genre=" + genre + ", telephone=" + telephone + ", ranking=" + ranking
				+ ", billingAddress=" + billingAddress + ", deliveryAddresses=" + deliveryAddresses + ", creditCards="
				+ creditCards + ", cart=" + cart + ", active=" + active + ", getId()=" + getId() + "]";
	}


	

}
