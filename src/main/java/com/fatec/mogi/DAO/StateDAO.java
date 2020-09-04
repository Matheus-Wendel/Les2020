package com.fatec.mogi.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.fatec.mogi.model.domain.State;
@Service
public class StateDAO extends AbstractDAO<State> {

	@Autowired
	public StateDAO(JpaRepository<State, Integer> repository) {
		super(repository);
	}

	

}
