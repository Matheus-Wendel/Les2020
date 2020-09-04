package com.fatec.mogi.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.fatec.mogi.model.aplication.Filter;
import com.fatec.mogi.model.aplication.Result;
import com.fatec.mogi.model.domain.Client;
import com.fatec.mogi.model.domain.DomainEntity;
@Service
public class ClientDAO extends AbstractDAO<Client> {

	@Autowired
	public ClientDAO(JpaRepository<Client, Integer> repository) {
		super(repository);
	}

}
