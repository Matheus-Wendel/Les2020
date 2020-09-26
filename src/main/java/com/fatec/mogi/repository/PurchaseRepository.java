package com.fatec.mogi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fatec.mogi.model.domain.Purchase;

public interface PurchaseRepository extends JpaRepository<Purchase, Integer> {

	
	
}
