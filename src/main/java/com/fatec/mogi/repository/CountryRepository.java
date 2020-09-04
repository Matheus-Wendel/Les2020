package com.fatec.mogi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fatec.mogi.model.domain.Country;

public interface CountryRepository extends JpaRepository<Country, Integer> {

	
	
}
