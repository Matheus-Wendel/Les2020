package com.fatec.mogi.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.fatec.mogi.auth.AuthUtils;
import com.fatec.mogi.model.aplication.Filter;
import com.fatec.mogi.model.aplication.Result;
import com.fatec.mogi.model.domain.CreditCard;
import com.fatec.mogi.model.domain.DomainEntity;
@Service
public class CreditCardDAO extends AbstractDAO<CreditCard> {

	@Autowired
	public CreditCardDAO(JpaRepository<CreditCard, Integer> repository) {
		super(repository);
	}

	@Override
	public Result save(Filter<? extends DomainEntity> filter) {
	var creditCard = (CreditCard) filter.getEntity();
	creditCard.setClient(AuthUtils.getLoggedClient());
		return super.save(filter);
	}

}
