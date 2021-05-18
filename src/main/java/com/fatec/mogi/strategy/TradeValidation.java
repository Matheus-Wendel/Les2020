package com.fatec.mogi.strategy;

import java.util.Date;
import java.util.Optional;

import com.fatec.mogi.auth.AuthUtils;
import com.fatec.mogi.enumeration.TradeStatusEnum;
import com.fatec.mogi.model.domain.DomainEntity;
import com.fatec.mogi.model.domain.PurchaseItem;
import com.fatec.mogi.model.domain.Trade;
import com.fatec.mogi.repository.PurchaseItemRepository;

public class TradeValidation implements IStrategy {

	PurchaseItemRepository purchaseItemRepository;

	public TradeValidation(PurchaseItemRepository purchaseItemRepository) {
		this.purchaseItemRepository = purchaseItemRepository;

	}

	@Override
	public String process(DomainEntity entity) {
		var trade = (Trade) entity;
		StringBuilder sb = new StringBuilder();

		if (trade.getPurchaseItem() == null || trade.getPurchaseItem().getId() == null) {
			sb.append("Nenhum item selecionado, ou item selcionado invalido;;");
			return sb.toString();
		}

		Optional<PurchaseItem> findById = purchaseItemRepository.findById(trade.getPurchaseItem().getId());
		if (findById.isEmpty()) {
			sb.append("Item selecionado invalido;;");
			return sb.toString();
		}

		PurchaseItem purchaseItem = findById.get();
		if (purchaseItem.getTrade() != null && purchaseItem.getTrade().getId() != null) {
			sb.append("Troca de item ja solicitada;;");
			return sb.toString();

		}
		trade.setPurchaseItem(purchaseItem);
		trade.setStatus(TradeStatusEnum.REQUESTED);
		trade.setRequestDate(new Date());
		trade.setClient(AuthUtils.getLoggedClient());
		return sb.toString();
	}

}
