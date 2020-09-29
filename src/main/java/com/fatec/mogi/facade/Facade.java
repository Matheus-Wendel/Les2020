package com.fatec.mogi.facade;

import java.util.HashMap;
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
import com.fatec.mogi.util.CrudOperationEnum;

@Service
public class Facade implements IFacade {

	@Autowired
	UserDAO userDAO;

	private Map<String, IDAO> daoMap;
	private Map<String, Map<CrudOperationEnum, IStrategy>> mapStrategy;

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
		Map<String, String> validationResultMap = processStrategies(filter, CrudOperationEnum.FIND);
		if (!validationResultMap.isEmpty()) {
			return new Result(validationResultMap, null, true);
		}
		return getDAO(filter).find(filter);
	}

	@Override
	public Result save(Filter<? extends DomainEntity> filter) {
		Map<String, String> validationResultMap = processStrategies(filter, CrudOperationEnum.SAVE);

		if (!validationResultMap.isEmpty()) {
			return new Result(validationResultMap, null, true);
		}
		return getDAO(filter).save(filter);
	}

	@Override
	public Result update(Filter<? extends DomainEntity> filter) {
		Map<String, String> validationResultMap = processStrategies(filter, CrudOperationEnum.UPDATE);

		if (!validationResultMap.isEmpty()) {
			return new Result(validationResultMap, null, true);
		}
		return getDAO(filter).update(filter);
	}

	@Override
	public Result delete(Filter<? extends DomainEntity> filter) {
		Map<String, String> validationResultMap = processStrategies(filter, CrudOperationEnum.DELETE);

		if (!validationResultMap.isEmpty()) {
			return new Result(validationResultMap, null, true);
		}
		return getDAO(filter).delete(filter);
	}

	private IDAO getDAO(Filter<? extends DomainEntity> filter) {
		String daoClassName = filter.getClazz().getSimpleName();
		daoClassName = Character.toLowerCase(daoClassName.charAt(0)) + daoClassName.substring(1) + "DAO";
		return daoMap.get(daoClassName);
	}

	private Map<CrudOperationEnum, IStrategy> getStrategies(String strategyListName) {
		Map<CrudOperationEnum, IStrategy> strategyMap = mapStrategy.get(strategyListName);
		if (strategyMap == null || strategyMap.isEmpty()) {
			return new HashMap<CrudOperationEnum, IStrategy>();
		}
		return strategyMap;
	};

	private Map<String, String> processStrategies(Filter<? extends DomainEntity> filter, CrudOperationEnum operation) {
		var strategyMap = getStrategies(filter.getClazz().getSimpleName().toLowerCase());

		Map<String, String> messagesMap = new HashMap<>();
		var strategy = strategyMap.get(operation);

		if (strategyMap.isEmpty() || strategy == null) {
			return messagesMap;
		}

		DomainEntity entity = filter.getEntity();

		String processResult = strategy.process(entity);
		if (!processResult.isBlank()) {
			messagesMap.put("error", processResult);
		}

		return messagesMap;
	}

}
