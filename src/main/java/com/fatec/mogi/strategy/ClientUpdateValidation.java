package com.fatec.mogi.strategy;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.fatec.mogi.auth.AuthUtils;
import com.fatec.mogi.enumeration.PermissionEnum;
import com.fatec.mogi.model.domain.Client;
import com.fatec.mogi.model.domain.DomainEntity;
import com.fatec.mogi.model.domain.Person;
import com.fatec.mogi.model.domain.User;

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
			if (client.getGenre().equals("M") && client.getGenre().equals("F")) {
				sb.append("Genero Invalido ");
			}
		}
		if (client.getTelephone() == null || client.getTelephone().isBlank()) {
			sb.append("Telefone não pode ficar vazio");
		}

		if (!(client.getBillingAddress() == null)) {
			sb.append(addressValidation.process(client.getBillingAddress()));
		}
		var oldClient = AuthUtils.getLoggedClient();
		var person = (Person) client;
		if (person.getCpf() == null || person.getCpf().isBlank()) {
			sb.append("CPF não pode ser vazio;;");
		}
		if (person.getName() == null || person.getName().isBlank()) {
			sb.append("Nome não pode ser vazio;;");
		}
		if (person.getUser() == null) {
			sb.append("Dados de usuarios invalidos;;");
		} else {
			User user = client.getUser();

			if (user.getEmail() == null || user.getEmail().isBlank()) {
				sb.append("Email não pode ser vazio").append(",	");
			}

			if (user.getPassword() == null || user.getPassword().isBlank()) {
				user.setPassword(oldClient.getUser().getPassword());
			} else {

				Pattern pattern = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$");

				Matcher matcher = pattern.matcher(user.getPassword());

				if (matcher.matches()) {
					BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
					user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
				} else {
					sb.append("Senha invalida");
				}
			}
		}

		if (sb.length() == 0) {

			client.setRanking(oldClient.getRanking());
			client.setDeliveryAddresses(oldClient.getDeliveryAddresses());
			client.setCart(oldClient.getCart());
			client.setCreditCards(oldClient.getCreditCards());
			client.setId(oldClient.getId());
			client.getUser().setPermission(PermissionEnum.CLIENT);
		}

		return sb.toString();
	}

}
