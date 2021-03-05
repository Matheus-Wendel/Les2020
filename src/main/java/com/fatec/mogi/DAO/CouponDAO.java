package com.fatec.mogi.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.fatec.mogi.model.domain.Coupon;

@Service
public class CouponDAO extends AbstractDAO<Coupon> {

	@Autowired
	public CouponDAO(JpaRepository<Coupon, Integer> repository) {
		super(repository);
	}

}
