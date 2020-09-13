package com.fatec.mogi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fatec.mogi.model.domain.CardBrand;

public interface CardBrandRepository extends JpaRepository<CardBrand, Integer> {

}
