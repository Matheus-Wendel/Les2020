package com.fatec.mogi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fatec.mogi.model.domain.Client;

public interface ClientRepository extends JpaRepository<Client, Integer> {


	public Client findByUserEmail(String email);
	
	@Modifying(clearAutomatically = true)
	@Query("update Client c set c.active=0 where c.id= :id")
	public void deactivate(@Param(value = "id") Integer id); 
}
