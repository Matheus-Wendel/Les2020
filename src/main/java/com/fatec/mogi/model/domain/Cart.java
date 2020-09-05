package com.fatec.mogi.model.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Cart extends DomainEntity {
	@OneToMany
	private List<CartProduct> CartProducts;
	
}
