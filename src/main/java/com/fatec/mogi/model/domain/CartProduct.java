package com.fatec.mogi.model.domain;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fatec.mogi.util.ConstantsUtil;

@Entity
public class CartProduct extends DomainEntity {
	
	

	@Column(nullable = false)
	private int quantity;
	@ManyToOne(cascade = CascadeType.MERGE)
	private Disc disc;

	@ManyToOne
	@JoinColumn(name = "cart_id", referencedColumnName = "id")
	@JsonIgnore	
	private Cart cart;
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

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public Date getAddedDate() {
		return AddedDate;
	}

	public void setAddedDate(Date addedDate) {
		AddedDate = addedDate;
	}
	@Override
	public String toString() {
		return "CartProduct [quantity=" + quantity + ", disc=" + disc + ", cart=" + cart + ", AddedDate=" + AddedDate
				+ ", getId()=" + getId() + "]";
	}

}
