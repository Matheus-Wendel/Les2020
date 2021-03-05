package com.fatec.mogi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fatec.mogi.model.domain.City;

public interface CityRepository extends JpaRepository<City, Integer> {

	
	List<City> findByStateId(Integer id);
	
	
}
