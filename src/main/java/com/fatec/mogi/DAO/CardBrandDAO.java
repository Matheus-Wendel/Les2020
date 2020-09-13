package com.fatec.mogi.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.fatec.mogi.model.domain.CardBrand;
@Service
public class CardBrandDAO extends AbstractDAO<CardBrand> {

	@Autowired
	public CardBrandDAO(JpaRepository<CardBrand, Integer> repository) {
		super(repository);
	}

	

}
