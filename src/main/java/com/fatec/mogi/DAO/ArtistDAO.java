package com.fatec.mogi.DAO;

import com.fatec.mogi.model.domain.Artist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
@Service
public class ArtistDAO extends AbstractDAO<Artist> {

	@Autowired
	public ArtistDAO(JpaRepository<Artist, Integer> repository) {
		super(repository);
	}

	

}
