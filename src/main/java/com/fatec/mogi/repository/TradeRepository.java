package com.fatec.mogi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fatec.mogi.enumeration.TradeStatusEnum;
import com.fatec.mogi.model.domain.Trade;

public interface TradeRepository extends JpaRepository<Trade, Integer> {

	List<Trade> findByStatus(TradeStatusEnum status);
	
	
	
}
