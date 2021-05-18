package com.fatec.mogi.strategy;

import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.fatec.mogi.enumeration.CouponTypeEnum;
import com.fatec.mogi.enumeration.TradeStatusEnum;
import com.fatec.mogi.model.domain.Coupon;
import com.fatec.mogi.model.domain.DomainEntity;
import com.fatec.mogi.model.domain.PurchaseItem;
import com.fatec.mogi.model.domain.Stock;
import com.fatec.mogi.model.domain.Trade;
import com.fatec.mogi.repository.PurchaseItemRepository;
import com.fatec.mogi.repository.TradeRepository;

public class TradeUpdateValidation implements IStrategy {

	TradeRepository tradeRepository;

	PurchaseItemRepository purchaseItemRepository;

	public TradeUpdateValidation(TradeRepository tradeRepository, PurchaseItemRepository purchaseItemRepository) {
		this.tradeRepository = tradeRepository;
		this.purchaseItemRepository = purchaseItemRepository;
	}

	@Override
	public String process(DomainEntity entity) {
		var trade = (Trade) entity;
		StringBuilder sb = new StringBuilder();

		Optional<Trade> findById = tradeRepository.findById(trade.getId());
		if (findById.isEmpty()) {
			sb.append("Troca selecionado invalida;;");
			return sb.toString();
		}

		if (trade.getStatus() == TradeStatusEnum.FINISHED) {
			sb.append("Troca ja finalizada;;");
			return sb.toString();
		}
		
		trade.setStatus(trade.getStatus().getNextStep());
		PurchaseItem purchaseItem = purchaseItemRepository.findById(trade.getPurchaseItem().getId()).get();

		if (trade.getStatus() == TradeStatusEnum.FINISHED) {
			if (trade.getReturnToStock() == null) {
				sb.append("Selecione se o disco deve ou não voltar ao estoque;;");
				return sb.toString();

			}
			if (trade.getReturnToStock()) {
				List<Stock> stockList = purchaseItem.getDisc().getStock();
				var stock = Collections.max(stockList, Comparator.comparing(s -> s.getCostPrice()));
				stock.setQuantity(stock.getQuantity() + 1);

			}
			Coupon coupon = new Coupon();
			coupon.setClient(trade.getClient());
			coupon.setCode(Long.toString(new Date().getTime()));
			Calendar c = Calendar.getInstance();

			c.add(Calendar.YEAR, 1);
			var newDate = c.getTime();
			coupon.setExpirationDate(newDate);
			coupon.setActive(true);
			coupon.setType(CouponTypeEnum.TRADE);
			coupon.setValue(purchaseItem.getValue());
			trade.setChangeCoupon(coupon);
		}
		trade.setPurchaseItem(purchaseItem);

		return sb.toString();
	}

}
