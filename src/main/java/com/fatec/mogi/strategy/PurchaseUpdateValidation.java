package com.fatec.mogi.strategy;

import java.util.Optional;

import com.fatec.mogi.model.domain.DomainEntity;
import com.fatec.mogi.model.domain.Purchase;
import com.fatec.mogi.repository.PurchaseRepository;

public class PurchaseUpdateValidation implements IStrategy {

	PurchaseRepository purchaseRepository;

	public PurchaseUpdateValidation(PurchaseRepository purchaseRepository) {
		this.purchaseRepository = purchaseRepository;
	}

	@Override
	public String process(DomainEntity entity) {
		Purchase purchase = (Purchase) entity;
		StringBuilder sb = new StringBuilder();
		Optional<Purchase> findById = purchaseRepository.findById(entity.getId());
		if (findById.isEmpty()) {
			sb.append("Compra id: " + entity.getId() + " invalida");
		}
		Purchase dbPurchase = findById.get();

		purchase.setPurchaseStatus(dbPurchase.getPurchaseStatus().getNextStep());
		return sb.toString();
	}

}
