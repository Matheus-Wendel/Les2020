package com.fatec.mogi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fatec.mogi.model.domain.Client;

public interface ClientRepository extends JpaRepository<Client, Integer> {

}
