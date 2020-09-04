package com.fatec.mogi.strategy;

import com.fatec.mogi.enumeration.PermissionEnum;
import com.fatec.mogi.model.domain.Address;
import com.fatec.mogi.model.domain.Client;
import com.fatec.mogi.model.domain.DomainEntity;

public class ClientValidation implements IStrategy {

	@Override
	public String process(DomainEntity entity) {
		var client = (Client) entity;
		var addressValidation = new AddressValidation();
		StringBuilder sb = new StringBuilder();

		
		if (client.getGenre() == null || client.getGenre().isBlank()) {
			sb.append("Genero Não pode ficar vazio");

		} else {
			if (client.getGenre().equals("M")&& client.getGenre().equals("F")) {
				sb.append("Genero Invalido ");
			}
		}
		if (client.getTelephone() == null || client.getTelephone().isBlank()) {
			sb.append("Telefone não pode ficar vazio");
		}

		if (!(client.getBillingAddress() == null)) {
			sb.append(addressValidation.process(client.getBillingAddress()));
		}else {
			sb.append("Endereço de cobrança vazio");
		}
		if(!(client.getDeliveryAddresses()==null)) {
			for (Address address : client.getDeliveryAddresses()) {
				sb.append(addressValidation.process(address));
			}
		}
		client.setRanking(5);
		client.setCode("Akamska");
		client.getUser().setPermission(PermissionEnum.CLIENT);
		var personValidation = new PersonValidation();
		sb.append(personValidation.process(entity));

		return sb.toString();
	}

}
