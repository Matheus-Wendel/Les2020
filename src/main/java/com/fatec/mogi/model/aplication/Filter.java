package com.fatec.mogi.model.aplication;

import com.fatec.mogi.model.domain.DomainEntity;

public class Filter<T extends DomainEntity> extends AplicationEntity {

	private T entity;
	private Class<? extends DomainEntity> clazz;

	public Filter(T entity, Class<? extends DomainEntity> clazz) {
		this.entity = entity;
		this.clazz = clazz;
	}



	public T getEntity() {
		return entity;
	}

	public void setEntity(T entity) {
		this.entity = entity;
	}

	public Class<? extends DomainEntity> getClazz() {
		return clazz;
	}

	public void setClazz(Class<? extends DomainEntity> clazz) {
		this.clazz = clazz;
	}



	@Override
	public String toString() {
		return "Filter [entity=" + entity + ", clazz=" + clazz + "]";
	}

	

}
