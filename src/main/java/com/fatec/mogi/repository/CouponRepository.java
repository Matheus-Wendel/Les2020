package com.fatec.mogi.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fatec.mogi.enumeration.CouponTypeEnum;
import com.fatec.mogi.model.domain.Coupon;

public interface CouponRepository extends JpaRepository<Coupon, Integer> {

	List<Coupon> findByCodeAndTypeAndExpirationDateAfter(String code, CouponTypeEnum type, Date expirationDate);

	List<Coupon> findByType(CouponTypeEnum type);

	Coupon findByCode(String code);

	List<Coupon> findByClientIdAndTypeAndExpirationDateAfter(Integer id, CouponTypeEnum type, Date expirationDate);
}
