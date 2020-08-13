package com.fatec.mogi.command;

import org.springframework.beans.factory.annotation.Autowired;

import com.fatec.mogi.facade.Facade;
import com.fatec.mogi.model.aplication.Filter;
import com.fatec.mogi.model.aplication.Result;
import com.fatec.mogi.model.domain.DomainEntity;

public class DeleteCommand implements ICommand {
@Autowired
	Facade facade;
	@Override
	public Result execute(Filter<? extends DomainEntity> filter) {
		return facade.save(filter);
	}

}
