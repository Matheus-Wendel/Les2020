package com.fatec.mogi.strategy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fatec.mogi.repository.CityRepository;
import com.fatec.mogi.repository.ClientRepository;
import com.fatec.mogi.util.CrudOperationEnum;

@Service
public class StrategyUtil {

	@Autowired
	ClientRepository clientRepository;

	@Autowired
	CityRepository cityRepository;

	public Map<String, List<IStrategy>> getStrategies() {
		// Strategies Lists
		List<IStrategy> clientValidations = new ArrayList<>();
		List<IStrategy> addressValidations = new ArrayList<>();

		// Strategies Instances
		AddressValidation addressValidation = new AddressValidation(cityRepository);
		ClientValidation clientValidation = new ClientValidation(clientRepository, addressValidation);

		// Filling the lists
		clientValidations.add(clientValidation);
		addressValidations.add(addressValidation);

		// Strategy map
		Map<String, List<IStrategy>> strategiesMap = new HashMap<>();

		// Filling the map
		strategiesMap.put("client" + CrudOperationEnum.SAVE.name(), clientValidations);
		strategiesMap.put("address" + CrudOperationEnum.SAVE.name(), addressValidations);
		strategiesMap.put("address" + CrudOperationEnum.UPDATE.name(), addressValidations);

		return strategiesMap;
	}

}
