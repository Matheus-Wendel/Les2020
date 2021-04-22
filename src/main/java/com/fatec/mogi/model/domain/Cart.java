package com.fatec.mogi.model.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

@Entity
public class Cart extends DomainEntity {
	@OneToMany(mappedBy = "cart" ,cascade = CascadeType.MERGE)
	private List<CartProduct> cartProducts;

	@Transient
	private double total;
	public List<CartProduct> getCartProducts() {
		return cartProducts;
	}

	public void setCartProducts(List<CartProduct> cartProducts) {
		this.cartProducts = cartProducts;
	}

	public double getTotal() {
		
		if(getCartProducts()==null||getCartProducts().isEmpty()) {
			return 0;
		}
		return this.cartProducts.stream().mapToDouble(cp->cp.getDisc().getValue()*cp.getQuantity()).sum();
	}

	public void setTotal(double total) {
		this.total = total;
	}
	
	

}
