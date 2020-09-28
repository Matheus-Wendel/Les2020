package com.fatec.mogi.DAO;

import java.util.Arrays;

import com.fatec.mogi.auth.AuthUtils;
import com.fatec.mogi.enumeration.PermissionEnum;
import com.fatec.mogi.model.aplication.Filter;
import com.fatec.mogi.model.aplication.Result;
import com.fatec.mogi.model.domain.CreditCard;
import com.fatec.mogi.model.domain.DomainEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
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

	@Override
	public Result find(Filter<? extends DomainEntity> filter) {
		var loggedUser = AuthUtils.getLoggedUser();
		if(loggedUser.getPermission()!=PermissionEnum.CLIENT) {
			return super.find(filter);
		}
		Result result = new Result();
		result.setResultList((AuthUtils.getLoggedClient().getCreditCards()));
		return result;
	}

}
