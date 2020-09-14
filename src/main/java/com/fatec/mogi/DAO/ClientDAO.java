package com.fatec.mogi.DAO;

import java.util.Arrays;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.fatec.mogi.auth.AuthUtils;
import com.fatec.mogi.model.aplication.Filter;
import com.fatec.mogi.model.aplication.Result;
import com.fatec.mogi.model.domain.Client;
import com.fatec.mogi.model.domain.DomainEntity;
import com.fatec.mogi.repository.AddressRepository;
import com.fatec.mogi.repository.ClientRepository;
import com.fatec.mogi.util.MessagesUtil;

@Service
public class ClientDAO extends AbstractDAO<Client> {

	@Autowired
	AddressRepository addressRepository;
	
	ClientRepository clientRepository;

	@Autowired
	public ClientDAO(JpaRepository<Client, Integer> repository) {
		super(repository);
		this.clientRepository = (ClientRepository) repository;
	}

	@Override
	public Result save(Filter<? extends DomainEntity> filter) {
		var client = (Client) filter.getEntity();
		client.getDeliveryAddresses().stream().forEach(a -> a.setClient(client));
		return super.save(filter);
	}

	@Override

	@Transactional
	public Result delete(Filter<? extends DomainEntity> filter) {
		Client entity = AuthUtils.getLoggedClient();
		Result result = new Result();
		try {
			if (repository.existsById(entity.getId())) {
				this.clientRepository.deactivate(entity.getId());
				result.getMessages().put(MessagesUtil.DEACTIVATED, "id: " + entity.getId());

			} else {
				result.getMessages().put(MessagesUtil.NOT_FOUND, "id: " + entity.getId());
			}
			return result;
		} catch (Exception e) {
			result.setError(true);
			result.getMessages().put("Message", e.getMessage());
			result.getMessages().put("Cause", e.getCause().toString());
			return result;
		}
	}
	
	@Override
	public Result find(Filter<? extends DomainEntity> filter) {
		Result result = new Result();
		System.err.println(AuthUtils.getLoggedClient());
		result.setResultList(Arrays.asList(AuthUtils.getLoggedClient()));
		return result;
	}

}
