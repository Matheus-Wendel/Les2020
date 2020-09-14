package com.fatec.mogi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fatec.mogi.model.domain.Cart;

public interface CartRepository extends JpaRepository<Cart, Integer> {

	
	
}
