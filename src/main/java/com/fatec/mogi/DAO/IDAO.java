package com.fatec.mogi.DAO;

import com.fatec.mogi.model.aplication.Filter;
import com.fatec.mogi.model.aplication.Result;
import com.fatec.mogi.model.domain.DomainEntity;

public interface IDAO {
	public Result find(Filter<? extends DomainEntity> filter);

	public Result save(Filter<? extends DomainEntity> filter);

	public Result update(Filter<? extends DomainEntity> filter);

	public Result delete(Filter<? extends DomainEntity> filter);

}
