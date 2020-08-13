package com.fatec.mogi.facade;

import org.springframework.stereotype.Service;

import com.fatec.mogi.model.aplication.Filter;
import com.fatec.mogi.model.aplication.Result;
import com.fatec.mogi.model.domain.DomainEntity;

@Service
public class Facade implements IFacade {

	@Override
	public Result find(Filter<? extends DomainEntity> filter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result save(Filter<? extends DomainEntity> filter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result update(Filter<? extends DomainEntity> filter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result delete(Filter<? extends DomainEntity> filter) {
		// TODO Auto-generated method stub
		return null;
	}

}
