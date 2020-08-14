package com.fatec.mogi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fatec.mogi.model.domain.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	
	List<User> findByEmail(String email);
	
}
