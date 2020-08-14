package com.fatec.mogi.strategy;

import com.fatec.mogi.model.domain.DomainEntity;

public interface IStrategy {

	public String process(DomainEntity entity);
}
