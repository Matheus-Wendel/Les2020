package com.fatec.mogi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fatec.mogi.model.domain.Genre;

public interface GenreRepository extends JpaRepository<Genre, Integer> {

	
	
}
