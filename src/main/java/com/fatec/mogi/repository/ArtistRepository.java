package com.fatec.mogi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fatec.mogi.model.domain.Artist;

public interface ArtistRepository extends JpaRepository<Artist, Integer> {

	
	
}
