package com.fatec.mogi.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.fatec.mogi.model.domain.User;
@Service
public class UserDAO extends AbstractDAO<User> {

	@Autowired
	public UserDAO(JpaRepository<User, Integer> repository) {
		super(repository);
	}

	

}
