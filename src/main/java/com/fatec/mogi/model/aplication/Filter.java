package com.fatec.mogi.model.aplication;

import com.fatec.mogi.model.domain.DomainEntity;

public class Filter<T extends DomainEntity> extends AplicationEntity {

	private T entity;
	
	
	
	public Filter() {
	}


	@SuppressWarnings({ "deprecation", "unchecked" })
	public Filter(Class<? extends DomainEntity> clazz) {
		try {
			this.entity = (T) clazz.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}




	public T getEntity() {
		return entity;
	}


	public void setEntity(T entity) {
		this.entity = entity;
	}
	
	
}
