package com.fatec.mogi.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.fatec.mogi.model.domain.Client;
@Service
public class ClientDAO extends AbstractDAO<Client> {

	@Autowired
	public ClientDAO(JpaRepository<Client, Integer> repository) {
		super(repository);
	}

}
