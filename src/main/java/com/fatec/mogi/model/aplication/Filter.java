package com.fatec.mogi.model.aplication;

import java.util.Map;

import com.fatec.mogi.model.domain.DomainEntity;

public class Filter<T extends DomainEntity> {

	private T entity;
	private Map<String, String> parameters;
	private Class<? extends DomainEntity> clazz;

	public Filter(T entity, Class<? extends DomainEntity> clazz) {
		this.entity = entity;
		this.clazz = clazz;
	}
	public Filter(Map<String, String> parameters, Class<? extends DomainEntity> clazz) {
		this.parameters = parameters;
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

	public Map<String, String> getParameters() {
		return parameters;
	}

	public void setParameters(Map<String, String> parameters) {
		this.parameters = parameters;
	}

	@Override
	public String toString() {
		return "Filter [entity=" + entity + ", clazz=" + clazz + "]";
	}

}
