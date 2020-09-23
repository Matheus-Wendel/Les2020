package com.fatec.mogi.DAO;

import com.fatec.mogi.model.domain.Pricing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
@Service
public class PricingDAO extends AbstractDAO<Pricing> {

	@Autowired
	public PricingDAO(JpaRepository<Pricing, Integer> repository) {
		super(repository);
	}

	

}
