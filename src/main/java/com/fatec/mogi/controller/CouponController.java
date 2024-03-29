package com.fatec.mogi.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fatec.mogi.model.domain.Coupon;
@RestController
@RequestMapping("/coupon")
@CrossOrigin(origins = "*", maxAge = 3600L)
public class CouponController extends AbstractController<Coupon> {

	public CouponController() {
		super(Coupon.class);
	}


}
