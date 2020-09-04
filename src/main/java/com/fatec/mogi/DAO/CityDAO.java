package com.fatec.mogi.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.fatec.mogi.model.domain.City;
@Service
public class CityDAO extends AbstractDAO<City> {

	@Autowired
	public CityDAO(JpaRepository<City, Integer> repository) {
		super(repository);
	}

	

}
