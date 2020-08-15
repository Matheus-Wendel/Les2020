package com.fatec.mogi.strategy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fatec.mogi.repository.UserRepository;
import com.fatec.mogi.util.CrudOperationEnum;
@Service
public class StrategyUtil {

	@Autowired
	UserRepository userRepository;
	
	
	
	public Map<String, List<IStrategy>> getStrategies() {
		//Strategies Lists
		List<IStrategy> userValidations =new ArrayList<>();
		
		//Strategies Instances
		UserValidation userValidation = new UserValidation(userRepository);
		
		//Filling the lists		
		userValidations.add(userValidation);
		
		//Strategy map
		Map<String, List<IStrategy>> strategiesMap = new HashMap<>();
		
		//Filling the map
		strategiesMap.put("user"+CrudOperationEnum.SAVE.name(), userValidations);
		strategiesMap.put("user"+CrudOperationEnum.UPDATE.name(), userValidations);
		
		return strategiesMap;
	}
	
	
}
