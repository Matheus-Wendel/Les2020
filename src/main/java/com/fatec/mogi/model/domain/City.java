package com.fatec.mogi.model.domain;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
@Entity
public class City extends DomainEntity {
	@ManyToOne
	private State state;
	private String description;

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
