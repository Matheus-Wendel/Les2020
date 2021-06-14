package com.fatec.mogi.strategy;

import com.fatec.mogi.enumeration.CouponTypeEnum;
import com.fatec.mogi.model.domain.Coupon;
import com.fatec.mogi.model.domain.DomainEntity;
import com.fatec.mogi.repository.CouponRepository;

public class CouponValidation implements IStrategy {

	CouponRepository couponRepository;

	AddressValidation addressValidation;

	public CouponValidation(CouponRepository couponRepository) {
		this.couponRepository = couponRepository;
	}

	@Override
	public String process(DomainEntity entity) {
		var coupon = (Coupon) entity;

		StringBuilder sb = new StringBuilder();

		if (coupon.getCode() == null || coupon.getCode().isBlank()) {
			sb.append("Código não pode ficar vazio;;");
		}
		if (coupon.getCode() == null || coupon.getCode().isBlank() && coupon.getCode().contains(" ")) {
			sb.append("Código não pode conter espaços;;");
		}
		if (coupon.getValue() <= 0) {
			sb.append("Valor invalido;;");
		}
		if (sb.length() == 0) {
			Coupon findByCode = couponRepository.findByCode(coupon.getCode());
			if (findByCode != null && findByCode.getId() != coupon.getId()) {
				sb.append("Cupom com o mesmo código ja existe;;");
				return sb.toString();
			}
			// SAVING A NEW ONE, SETTING DEFAULT VALUES
			coupon.setClient(null);
			coupon.setType(CouponTypeEnum.PROMOTIONAL);

		}

		return sb.toString();
	}

}
