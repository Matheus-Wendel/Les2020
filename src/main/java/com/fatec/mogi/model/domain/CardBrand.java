package com.fatec.mogi.model.domain;

import javax.persistence.Column;

public class CardBrand extends DomainEntity {
	@Column(nullable = false, unique = true)
	private String description;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
