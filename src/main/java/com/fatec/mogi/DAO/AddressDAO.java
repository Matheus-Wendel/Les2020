package com.fatec.mogi.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.fatec.mogi.auth.AuthUtils;
import com.fatec.mogi.enumeration.PermissionEnum;
import com.fatec.mogi.model.aplication.Filter;
import com.fatec.mogi.model.aplication.Result;
import com.fatec.mogi.model.domain.Address;
import com.fatec.mogi.model.domain.Client;
import com.fatec.mogi.model.domain.DomainEntity;
import com.fatec.mogi.repository.AddressRepository;

@Service
public class AddressDAO extends AbstractDAO<Address> {

	AddressRepository addressRepository;
	
	@Autowired
	public AddressDAO(JpaRepository<Address, Integer> repository) {
		super(repository);
		this.addressRepository = (AddressRepository) repository;
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
		Client loggedClient = AuthUtils.getLoggedClient();
		Integer id = loggedClient.getId();
		result.setResultList(this.addressRepository.findByClientId(id));
		return result;
	}
}
