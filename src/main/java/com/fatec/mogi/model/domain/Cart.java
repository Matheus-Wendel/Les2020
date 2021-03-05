package com.fatec.mogi.model.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

@Entity
public class Cart extends DomainEntity {
	@OneToMany(mappedBy = "cart")
	private List<CartProduct> CartProducts;

	@Transient
	private double total;
	public List<CartProduct> getCartProducts() {
		return CartProducts;
	}

	public void setCartProducts(List<CartProduct> cartProducts) {
		CartProducts = cartProducts;
	}

	public double getTotal() {
		return 0;
		//return this.CartProducts.stream().mapToDouble(cp->cp.getDisc().getValue()*cp.getQuantity()).sum();
	}

	public void setTotal(double total) {
		this.total = total;
	}
	
	

}
