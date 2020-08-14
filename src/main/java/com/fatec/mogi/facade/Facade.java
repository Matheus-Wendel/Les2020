package com.fatec.mogi.facade;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fatec.mogi.DAO.IDAO;
import com.fatec.mogi.DAO.UserDAO;
import com.fatec.mogi.model.aplication.Filter;
import com.fatec.mogi.model.aplication.Result;
import com.fatec.mogi.model.domain.DomainEntity;
import com.fatec.mogi.strategy.IStrategy;
import com.fatec.mogi.strategy.StrategyUtil;

@Service
public class Facade implements IFacade {

	@Autowired
	UserDAO userDAO;

	private Map<String, IDAO> daoMap;
	private Map<String, List<IStrategy>> mapStrategy;

	@Autowired
	public Facade(Map<String, IDAO> daoMap, StrategyUtil util) {
		this.daoMap = daoMap;
		mapStrategy = util.getStrategies();

		for (String s : mapStrategy.keySet()) {
			System.err.println(s);
		}
	}

	@Override
	public Result find(Filter<? extends DomainEntity> filter) {
		return getDAO(filter).find(filter);
	}

	@Override
	public Result save(Filter<? extends DomainEntity> filter) {
		Map<String,String> validationResultMap = processStrategies(getStrategies(filter), filter);
		
		if(!validationResultMap.isEmpty()) {
			return new Result(validationResultMap, null, true);
		}
		return getDAO(filter).save(filter);
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

	private IDAO getDAO(Filter<? extends DomainEntity> filter) {
		String daoClassName = filter.getClazz().getSimpleName();
		daoClassName = Character.toLowerCase(daoClassName.charAt(0)) + daoClassName.substring(1) + "DAO";
		return daoMap.get(daoClassName);
	}

	private List<IStrategy> getStrategies(Filter<? extends DomainEntity> filter) {
		String strategyListName = filter.getClazz().getSimpleName().toLowerCase();
		return mapStrategy.get(strategyListName);
	};

	private Map<String, String> processStrategies(List<IStrategy> strategies, Filter<? extends DomainEntity> filter) {
		Map<String, String> messagesMap = new HashMap<>();
		if (strategies == null || strategies.isEmpty()) {
			return messagesMap;
		}
		DomainEntity entity = filter.getEntity();
		for (IStrategy strategy : strategies) {
			String processResult = strategy.process(entity);
			if (!processResult.isBlank()) {
				messagesMap.put(strategy.getClass().getSimpleName(), processResult);
			}
		}
		return messagesMap;
	}

}
