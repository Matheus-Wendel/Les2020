package com.fatec.mogi.DAO;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.fatec.mogi.auth.AuthUtils;
import com.fatec.mogi.enumeration.CouponTypeEnum;
import com.fatec.mogi.enumeration.PermissionEnum;
import com.fatec.mogi.model.aplication.Filter;
import com.fatec.mogi.model.aplication.Result;
import com.fatec.mogi.model.domain.Client;
import com.fatec.mogi.model.domain.Coupon;
import com.fatec.mogi.model.domain.DomainEntity;
import com.fatec.mogi.repository.CouponRepository;

@Service
public class CouponDAO extends AbstractDAO<Coupon> {

	CouponRepository couponRepository;
	@Autowired
	public CouponDAO(JpaRepository<Coupon, Integer> repository) {
		
		super(repository);
		this.couponRepository = (CouponRepository) repository;
	}


	@Override
	public Result find(Filter<? extends DomainEntity> filter) {
		Map<String, String> parameters = filter.getParameters();
		Result result = new Result();
		
		if (parameters.containsKey("promotional")&&parameters.containsKey("code")) {
			List<Coupon> findPromotionalCoupon = this.couponRepository.findByCodeAndTypeAndExpirationDateAfter(parameters.get("code"),CouponTypeEnum.PROMOTIONAL,new Date());;
			result.setResultList(findPromotionalCoupon);
			return result;
		}
		var loggedUser = AuthUtils.getLoggedUser();
		if(loggedUser.getPermission()!=PermissionEnum.CLIENT) {
			return super.find(filter);
		}
		Client loggedClient = AuthUtils.getLoggedClient();
		List<Coupon> findClientCoupons = this.couponRepository.findByClientIdAndTypeAndExpirationDateAfter(loggedClient.getId(), CouponTypeEnum.TRADE, new Date());
		result.setResultList(findClientCoupons);
		return result;
		
	}
}
