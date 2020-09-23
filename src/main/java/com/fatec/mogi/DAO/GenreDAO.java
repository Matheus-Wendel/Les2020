package com.fatec.mogi.DAO;

import com.fatec.mogi.model.domain.Genre;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
@Service
public class GenreDAO extends AbstractDAO<Genre> {

	@Autowired
	public GenreDAO(JpaRepository<Genre, Integer> repository) {
		super(repository);
	}

	

}
