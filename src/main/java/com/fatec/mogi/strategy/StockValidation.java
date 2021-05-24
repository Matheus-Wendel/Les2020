package com.fatec.mogi.strategy;

import java.util.Date;
import java.util.Optional;

import com.fatec.mogi.model.domain.Disc;
import com.fatec.mogi.model.domain.DomainEntity;
import com.fatec.mogi.model.domain.Stock;
import com.fatec.mogi.repository.DiscRepository;

public class StockValidation implements IStrategy {

	private DiscRepository discRepository;

	public StockValidation(DiscRepository discRepository) {
		this.discRepository = discRepository;
	}

	@Override
	public String process(DomainEntity entity) {
		var stock = (Stock) entity;
		var sb = new StringBuilder();
//
		if (stock.getCostPrice() <= 0) {
			sb.append("Não é possivel cadastrar um estoque com custo 0;;");

		}
		if (stock.getQuantity() <= 0) {
			sb.append("Não é possivel cadastrar um estoque com quantidade 0;;");

		}
		if (stock.getDisc() == null || stock.getDisc().getId() == null) {

			sb.append("Selecione um disco para vincular a entrada de estoque;;");
		}

		if (sb.length() > 0) {
			return sb.toString();
		}

		Optional<Disc> findById = discRepository.findById(stock.getDisc().getId());

		if (findById.isEmpty()) {
			sb.append("Disco selecionado invalido;;");
		}
		
		
		stock.setPurchaceDate(new Date());
		
//		if(pricing.getDescription()==null||pricing.getDescription().isBlank()) {
//			sb.append("Nome da precificação não pode ser vazio;;");
//		}

//		Sale sale = new Sale();
//		sale.setProfit(pricing.getMinimumProfit());
//		sale.setStatus(SaleStatusEnum.INACTIVE);
//		pricing.setSale(sale);

		return sb.toString();
	}

}
