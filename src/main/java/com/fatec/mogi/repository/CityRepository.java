package com.fatec.mogi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fatec.mogi.model.domain.City;

public interface CityRepository extends JpaRepository<City, Integer> {

	
	
}
