package com.fatec.mogi.model.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
@Entity
public class Recorder extends DomainEntity {
	@Column(nullable = false)
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
