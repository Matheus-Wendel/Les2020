package com.fatec.mogi.DAO;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.fatec.mogi.model.aplication.Filter;
import com.fatec.mogi.model.aplication.Result;
import com.fatec.mogi.model.domain.Disc;
import com.fatec.mogi.model.domain.DomainEntity;
import com.fatec.mogi.repository.ActivationDetailsRepository;
import com.fatec.mogi.repository.StockRepository;

@Service
public class DiscDAO extends AbstractDAO<Disc> {

	@Autowired
	StockRepository stockRepository;

	@Autowired
	ActivationDetailsRepository activationDetailsRepository;

	@Autowired
	public DiscDAO(JpaRepository<Disc, Integer> repository) {
		super(repository);
	}

	@Override
	@Transactional
	public Result save(Filter<? extends DomainEntity> filter) {
		var disc = (Disc) filter.getEntity();
		disc.getStock().stream().forEach(s -> s.setDisc(disc));
		stockRepository.saveAll(disc.getStock());
		activationDetailsRepository.save(disc.getActivationDetails());
		return super.save(filter);
	}

}
