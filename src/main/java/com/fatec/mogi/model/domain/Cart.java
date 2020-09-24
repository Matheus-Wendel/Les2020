package com.fatec.mogi.model.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Cart extends DomainEntity {
	@OneToMany(mappedBy = "cart")
	private List<CartProduct> CartProducts;

	public List<CartProduct> getCartProducts() {
		return CartProducts;
	}

	public void setCartProducts(List<CartProduct> cartProducts) {
		CartProducts = cartProducts;
	}

}
