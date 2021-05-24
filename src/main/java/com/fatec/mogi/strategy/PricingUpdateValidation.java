package com.fatec.mogi.strategy;

import java.util.Optional;

import com.fatec.mogi.enumeration.SaleStatusEnum;
import com.fatec.mogi.model.domain.DomainEntity;
import com.fatec.mogi.model.domain.Pricing;
import com.fatec.mogi.model.domain.Sale;
import com.fatec.mogi.repository.PricingRepository;

public class PricingUpdateValidation implements IStrategy {

	private PricingRepository pricingRepository;

	public PricingUpdateValidation(PricingRepository pricingRepository) {
		this.pricingRepository = pricingRepository;
	}

	@Override
	public String process(DomainEntity entity) {

		var pricing = (Pricing) entity;
		var sb = new StringBuilder();

		if (pricing.getDescription() == null || pricing.getDescription().isBlank()) {
			sb.append("Nome da precificação não pode ser vazio;;");
		}
		if (pricing.getDefautProfit() < 0.01 || pricing.getDefautProfit() > 5) {
			sb.append("Lucro padrão invalido;;");
		}

		if (pricing.getMinimumProfit() < 0.01 || pricing.getMinimumProfit() > 5) {
			sb.append("Lucro minimo invalido;;");
		}

		if (pricing.getMinimumProfit() > pricing.getDefautProfit()) {
			sb.append("Lucro minimo não pode ser maior que o lucro padrão;;");
		}

		Optional<Pricing> findById = this.pricingRepository.findById(pricing.getId());
		if (findById.isEmpty()) {
			sb.append("Precificação invalida;;");
		}

		if (sb.length() > 0) {
			return sb.toString();
		}

		Sale sale = findById.get().getSale();

		sale.setProfit(pricing.getMinimumProfit());
		sale.setStatus(SaleStatusEnum.INACTIVE);
		pricing.setSale(sale);

		return sb.toString();
	}

}
