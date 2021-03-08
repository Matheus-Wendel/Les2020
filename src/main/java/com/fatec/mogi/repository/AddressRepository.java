package com.fatec.mogi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fatec.mogi.model.domain.Address;

public interface AddressRepository extends JpaRepository<Address, Integer> {

	
	  List<Address> findByClientId(Integer id);
		
}
