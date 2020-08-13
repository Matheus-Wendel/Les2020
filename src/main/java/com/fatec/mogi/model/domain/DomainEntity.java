package com.fatec.mogi.model.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import com.fatec.mogi.model.IEntity;

@MappedSuperclass
public abstract class DomainEntity implements IEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
