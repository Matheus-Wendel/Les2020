package com.fatec.mogi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fatec.mogi.model.domain.Purchase;

public interface PurchaseRepository extends JpaRepository<Purchase, Integer> {

	
	List<Purchase> findByClientId(Integer id);
}
