package com.fatec.mogi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fatec.mogi.model.domain.Pricing;

public interface PricingRepository extends JpaRepository<Pricing, Integer> {

	Pricing findBySaleId(Integer id);
	
}
