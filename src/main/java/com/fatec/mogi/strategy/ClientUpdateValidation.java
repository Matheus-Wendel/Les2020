package com.fatec.mogi.strategy;

import com.fatec.mogi.auth.AuthUtils;
import com.fatec.mogi.enumeration.PermissionEnum;
import com.fatec.mogi.model.domain.Client;
import com.fatec.mogi.model.domain.DomainEntity;

public class ClientUpdateValidation implements IStrategy {

	
	AddressValidation addressValidation;
	public ClientUpdateValidation(AddressValidation addressValidation) {
		this.addressValidation = addressValidation;
	}
	@Override
	public String process(DomainEntity entity) {
		var client = (Client) entity;
		
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
		}
		var personValidation = new PersonValidation();
		sb.append(personValidation.process(entity));
		if(sb.length()==0) {
			var oldClient = AuthUtils.getLoggedClient();
			client.setRanking(oldClient.getRanking());
			client.setCode(oldClient.getCode());
			client.setDeliveryAddresses(oldClient.getDeliveryAddresses());
			client.setCart(oldClient.getCart());
			client.setCreditCards(oldClient.getCreditCards());
			client.setId(oldClient.getId());
			client.getUser().setPermission(PermissionEnum.CLIENT);
		}

		return sb.toString();
	}

}
