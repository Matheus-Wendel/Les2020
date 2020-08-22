package com.fatec.mogi.model.domain;

import javax.persistence.Entity;

@Entity
public class Country extends DomainEntity {
	private String description;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
