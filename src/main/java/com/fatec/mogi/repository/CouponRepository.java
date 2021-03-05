package com.fatec.mogi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fatec.mogi.model.domain.Coupon;

public interface CouponRepository extends JpaRepository<Coupon, Integer> {

	
	
}
