package com.fatec.mogi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fatec.mogi.model.domain.Stock;

public interface StockRepository extends JpaRepository<Stock, Integer> {

	List<Stock> findByDiscIdOrderByPurchaceDateAsc(Integer id);	
	List<Stock>  findByDiscIdOrderByCostPriceDesc(Integer id);	
	
	
}
