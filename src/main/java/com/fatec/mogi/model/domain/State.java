package com.fatec.mogi.model.domain;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class State extends DomainEntity {
@ManyToOne
	private Country country;
	private String description;

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
