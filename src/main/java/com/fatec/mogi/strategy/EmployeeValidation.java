package com.fatec.mogi.strategy;

import com.fatec.mogi.model.domain.DomainEntity;

public class EmployeeValidation implements IStrategy {


	public EmployeeValidation() {
	}
	@Override
	public String process(DomainEntity entity) {
		StringBuilder sb = new StringBuilder();

		
		var personValidation = new PersonValidation();
		sb.append(personValidation.process(entity));
	

		return sb.toString();
	}

}
