package com.fatec.mogi.strategy;

import com.fatec.mogi.model.domain.DomainEntity;
import com.fatec.mogi.model.domain.Person;

public class PersonValidation implements IStrategy{

	@Override
	public String process(DomainEntity entity) {
		var person = (Person) entity;
		var sb = new StringBuilder();
		if (person.getCpf() == null || person.getCpf().isBlank()) {
			sb.append("CPF não pode ser vazio;;");
		}
		if (person.getName() == null || person.getName().isBlank()) {
			sb.append("Nome não pode ser vazio;;");
		}
		if (person.getUser()== null) {
			sb.append("Dados de usuarios invalidos;;");
		}else {
			var userValidation = new UserValidation();
			sb.append(userValidation.process(person.getUser()));
		}

		return sb.toString();
	}

}
