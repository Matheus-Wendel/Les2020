package com.fatec.mogi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fatec.mogi.model.domain.Disc;

public interface DiscRepository extends JpaRepository<Disc, Integer> {

	
	List<Disc> findByNameContaining(String name);
}
