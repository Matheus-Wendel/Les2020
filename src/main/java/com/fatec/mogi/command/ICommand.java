package com.fatec.mogi.command;

import com.fatec.mogi.model.aplication.Filter;
import com.fatec.mogi.model.aplication.Result;
import com.fatec.mogi.model.domain.DomainEntity;

public interface ICommand {

	public Result execute(Filter<? extends DomainEntity> filter);
}
