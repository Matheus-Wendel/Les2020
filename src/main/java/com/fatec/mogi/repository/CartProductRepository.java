package com.fatec.mogi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fatec.mogi.model.domain.CartProduct;

public interface CartProductRepository extends JpaRepository<CartProduct, Integer> {

	
	
}
