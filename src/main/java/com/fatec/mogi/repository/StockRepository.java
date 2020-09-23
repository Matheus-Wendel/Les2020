package com.fatec.mogi.repository;

import com.fatec.mogi.model.domain.Stock;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<Stock, Integer> {

	
	
}
