package com.fatec.mogi.DAO;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.fatec.mogi.auth.AuthUtils;
import com.fatec.mogi.model.aplication.Filter;
import com.fatec.mogi.model.aplication.Result;
import com.fatec.mogi.model.domain.DomainEntity;
import com.fatec.mogi.model.domain.Purchase;
import com.fatec.mogi.repository.CartProductRepository;
import com.fatec.mogi.repository.PurchaseCardRepository;
import com.fatec.mogi.repository.PurchaseItemRepository;

@Service
public class PurchaseDAO extends AbstractDAO<Purchase> {

	@Autowired
	PurchaseItemRepository purchaseItemRepository;
	@Autowired
	PurchaseCardRepository purchaseCardRepository;
	@Autowired
	CartProductRepository cartProductRepository;

	@Autowired
	public PurchaseDAO(JpaRepository<Purchase, Integer> repository) {
		super(repository);
	}

	@Override
	@Transactional
	public Result save(Filter<? extends DomainEntity> filter) {
		Purchase purchase = (Purchase) filter.getEntity();
		purchaseItemRepository.saveAll(purchase.getPurchaseItems());
		purchaseCardRepository.saveAll(purchase.getPurchaseCards());
		cartProductRepository.deleteAll(AuthUtils.getLoggedClient().getCart().getCartProducts());
		return super.save(filter);
	}

}
