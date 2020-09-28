package com.fatec.mogi.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.fatec.mogi.auth.AuthUtils;
import com.fatec.mogi.enumeration.PermissionEnum;
import com.fatec.mogi.model.aplication.Filter;
import com.fatec.mogi.model.aplication.Result;
import com.fatec.mogi.model.domain.Address;
import com.fatec.mogi.model.domain.DomainEntity;

@Service
public class AddressDAO extends AbstractDAO<Address> {

	@Autowired
	public AddressDAO(JpaRepository<Address, Integer> repository) {
		super(repository);
	}

	@Override
	public Result save(Filter<? extends DomainEntity> filter) {
		var address = (Address) filter.getEntity();
		address.setClient(AuthUtils.getLoggedClient());
		return super.save(filter);
	}

	@Override
	public Result find(Filter<? extends DomainEntity> filter) {
		var loggedUser = AuthUtils.getLoggedUser();
		if (loggedUser.getPermission() != PermissionEnum.CLIENT) {
			return super.find(filter);
		}
		Result result = new Result();
		result.setResultList((AuthUtils.getLoggedClient().getDeliveryAddresses()));
		return result;
	}
}
