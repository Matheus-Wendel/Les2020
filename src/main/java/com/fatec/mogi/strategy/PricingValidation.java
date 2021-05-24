package com.fatec.mogi.strategy;

import com.fatec.mogi.enumeration.SaleStatusEnum;
import com.fatec.mogi.model.domain.DomainEntity;
import com.fatec.mogi.model.domain.Pricing;
import com.fatec.mogi.model.domain.Sale;

public class PricingValidation implements IStrategy {



	public PricingValidation() {
		
	}

	@Override
	public String process(DomainEntity entity) {
		var pricing = (Pricing) entity;
		var sb = new StringBuilder();

		if(pricing.getDescription()==null||pricing.getDescription().isBlank()) {
			sb.append("Nome da precificação não pode ser vazio;;");
		}
		if(pricing.getDefautProfit()<0.01||pricing.getDefautProfit()>5) {
			sb.append("Lucro padrão invalido;;");
		}
		
		if(pricing.getMinimumProfit()<0.01||pricing.getMinimumProfit()>5) {
			sb.append("Lucro minimo invalido;;");
		}
		
		if(pricing.getMinimumProfit()>pricing.getDefautProfit()){
			sb.append("Lucro minimo não pode ser maior que o lucro padrão;;");
		}
		
	
		
		if (sb.length() > 0) {
			return sb.toString();
		}
		
		Sale sale = new Sale();
		sale.setProfit(pricing.getMinimumProfit());
		sale.setStatus(SaleStatusEnum.INACTIVE);
		pricing.setSale(sale);
		
		return sb.toString();
	}

}
