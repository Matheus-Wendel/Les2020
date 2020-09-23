package com.fatec.mogi.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.fatec.mogi.model.domain.Stock;
@Service
public class StockDAO extends AbstractDAO<Stock> {

	@Autowired
	public StockDAO(JpaRepository<Stock, Integer> repository) {
		super(repository);
	}

	

}
