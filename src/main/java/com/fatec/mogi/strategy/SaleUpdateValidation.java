package com.fatec.mogi.strategy;

import com.fatec.mogi.model.domain.DomainEntity;
import com.fatec.mogi.model.domain.Pricing;
import com.fatec.mogi.model.domain.Sale;
import com.fatec.mogi.repository.PricingRepository;

public class SaleUpdateValidation implements IStrategy {

	private PricingRepository pricingRepository;

	public SaleUpdateValidation(PricingRepository pricingRepository) {
		this.pricingRepository = pricingRepository;
	}

	@Override
	public String process(DomainEntity entity) {

		var sale = (Sale) entity;
		var sb = new StringBuilder();


		 Pricing pricing = this.pricingRepository.findBySaleId(sale.getId());
		if (pricing==null) {
			sb.append("Precificação invalida;;");
		}

		if(sale.getProfit()<pricing.getMinimumProfit()) {
			
			sb.append("Valor promocional não pode ser menor que o valor minimo de lucro ;;");
		}
		if(sale.getProfit()>pricing.getDefautProfit()) {
			
			sb.append("Valor promocional não pode ser maior que o valor padrão de lucro;;");
		}
	

		return sb.toString();
	}

}
