package com.fatec.mogi.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.fatec.mogi.model.aplication.Filter;
import com.fatec.mogi.model.aplication.Result;
import com.fatec.mogi.model.domain.CartProduct;
import com.fatec.mogi.model.domain.DomainEntity;
import com.fatec.mogi.repository.DiscRepository;
import com.fatec.mogi.repository.StockRepository;

@Service
public class CartProductDAO extends AbstractDAO<CartProduct> {

	@Autowired
	StockRepository stockRepository;

	@Autowired
	DiscRepository discRepository;

	@Autowired
	public CartProductDAO(JpaRepository<CartProduct, Integer> repository) {
		super(repository);
	}

	@Override
	public Result save(Filter<? extends DomainEntity> filter) {
		var cartProduct = (CartProduct) filter.getEntity();
		stockRepository.saveAll(cartProduct.getDisc().getStock());
		return super.save(filter);
	}

	
	@Override
	public Result delete(Filter<? extends DomainEntity> filter) {
		var cartProduct = (CartProduct) filter.getEntity();
		stockRepository.saveAll(cartProduct.getDisc().getStock());
		return super.delete(filter);
	}
}
