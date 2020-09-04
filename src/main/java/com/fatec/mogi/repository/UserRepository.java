package com.fatec.mogi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fatec.mogi.model.domain.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	
	
}
