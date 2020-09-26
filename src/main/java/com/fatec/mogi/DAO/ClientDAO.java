package com.fatec.mogi.DAO;

import java.util.Arrays;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.fatec.mogi.auth.AuthUtils;
import com.fatec.mogi.enumeration.PermissionEnum;
import com.fatec.mogi.model.aplication.Filter;
import com.fatec.mogi.model.aplication.Result;
import com.fatec.mogi.model.domain.Client;
import com.fatec.mogi.model.domain.DomainEntity;
import com.fatec.mogi.repository.AddressRepository;
import com.fatec.mogi.repository.CartRepository;
import com.fatec.mogi.repository.ClientRepository;
import com.fatec.mogi.repository.UserRepository;
import com.fatec.mogi.util.MessagesUtil;

@Service
public class ClientDAO extends AbstractDAO<Client> {

	@Autowired
	AddressRepository addressRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	CartRepository cartRepository;
	
	ClientRepository clientRepository;
	

	@Autowired
	public ClientDAO(JpaRepository<Client, Integer> repository) {
		super(repository);
		this.clientRepository = (ClientRepository) repository;
	}

	@Override
	@Transactional
	public Result save(Filter<? extends DomainEntity> filter) {
		var client = (Client) filter.getEntity();
		client.getDeliveryAddresses().stream().forEach(a -> a.setClient(client));
		addressRepository.saveAll(client.getDeliveryAddresses());
		addressRepository.save(client.getBillingAddress());
		userRepository.save(client.getUser());
		cartRepository.save(client.getCart());
		
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
		var loggedUser = AuthUtils.getLoggedUser();
		if(loggedUser.getPermission()!=PermissionEnum.CLIENT) {
			return super.find(filter);
		}
		Result result = new Result();
		result.setResultList(Arrays.asList(AuthUtils.getLoggedClient()));
		return result;
	}

	@Override
	@Transactional
	public Result update(Filter<? extends DomainEntity> filter) {
		var client = (Client) filter.getEntity();
		
		var oldClient = AuthUtils.getLoggedClient();
		client.setId(oldClient.getId());
		if(client.getBillingAddress()!=null) {
			client.getBillingAddress().setId(oldClient.getBillingAddress().getId());
			addressRepository.save(client.getBillingAddress());
		}
		if(client.getUser()!=null) {
			client.getUser().setId(oldClient.getUser().getId());
			userRepository.save(client.getUser());
		}
		return super.update(filter);
	}
}
