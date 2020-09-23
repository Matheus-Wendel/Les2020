package com.fatec.mogi.DAO;

import com.fatec.mogi.model.domain.Sale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
@Service
public class SaleDAO extends AbstractDAO<Sale> {

	@Autowired
	public SaleDAO(JpaRepository<Sale, Integer> repository) {
		super(repository);
	}

	

}
