package com.fatec.mogi.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.fatec.mogi.model.domain.Country;
@Service
public class CountryDAO extends AbstractDAO<Country> {

	@Autowired
	public CountryDAO(JpaRepository<Country, Integer> repository) {
		super(repository);
	}

	

}
