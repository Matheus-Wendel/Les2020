package com.fatec.mogi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fatec.mogi.model.domain.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	User findByEmail(String email);
	@Query("SELECT u FROM User u WHERE u.email = :email and u.active=1")
	User findActiveByEmail(@Param("email")String email);
	
	@Modifying(clearAutomatically = true)
	@Query("update User u set u.active=0 where u.id= :id")
	public void deactivate(@Param(value = "id") Integer id); 
}
