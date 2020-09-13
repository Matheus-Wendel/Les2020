package com.fatec.mogi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fatec.mogi.model.domain.CreditCard;

public interface CreditCardRepository extends JpaRepository<CreditCard, Integer> {

}
