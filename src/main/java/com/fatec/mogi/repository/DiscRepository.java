package com.fatec.mogi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fatec.mogi.model.domain.Disc;

public interface DiscRepository extends JpaRepository<Disc, Integer> {

	
	List<Disc> findByNameContainingIgnoreCase(String name);
	
	@Modifying(clearAutomatically = true)
	@Query("update Disc d set d.active=0 where d.id= :id")
	public void deactivate(@Param(value = "id") Integer id); 
}
