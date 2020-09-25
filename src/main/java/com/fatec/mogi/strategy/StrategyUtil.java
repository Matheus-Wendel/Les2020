package com.fatec.mogi.strategy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fatec.mogi.repository.CardBrandRepository;
import com.fatec.mogi.repository.CartProductRepository;
import com.fatec.mogi.repository.CityRepository;
import com.fatec.mogi.repository.ClientRepository;
import com.fatec.mogi.repository.DiscRepository;
import com.fatec.mogi.util.CrudOperationEnum;

@Service
public class StrategyUtil {

	@Autowired
	ClientRepository clientRepository;

	@Autowired
	CityRepository cityRepository;
	@Autowired
	CardBrandRepository cardBrandRepository;
	
	@Autowired
	DiscRepository discRepository;
	@Autowired
	CartProductRepository cartProductRepository;

	public Map<String, List<IStrategy>> getStrategies() {
		// Strategies Lists
		List<IStrategy> clientValidations = new ArrayList<>();
		List<IStrategy> clientUpdateValidations = new ArrayList<>();
		List<IStrategy> addressValidations = new ArrayList<>();
		List<IStrategy> creditCardValidations = new ArrayList<>();
		List<IStrategy> cartProductsValidations = new ArrayList<>();
		List<IStrategy> cartProductsDeleteValidations = new ArrayList<>();

		// Strategies Instances
		AddressValidation addressValidation = new AddressValidation(cityRepository);
		ClientValidation clientValidation = new ClientValidation(clientRepository, addressValidation);
		ClientUpdateValidation clientUpdateValidation = new ClientUpdateValidation(addressValidation);
		CreditCardValidation creditCardValidation = new CreditCardValidation(cardBrandRepository);
		CartProductValidation cartProductValidation = new CartProductValidation(discRepository);
		CartProductDeleteValidation cartProductDeleteValidation = new CartProductDeleteValidation(discRepository, cartProductRepository);
		// Filling the lists
		clientValidations.add(clientValidation);
		addressValidations.add(addressValidation);
		creditCardValidations.add(creditCardValidation);
		clientUpdateValidations.add(clientUpdateValidation);
		cartProductsValidations.add(cartProductValidation);
		cartProductsDeleteValidations.add(cartProductDeleteValidation);

		// Strategy map
		Map<String, List<IStrategy>> strategiesMap = new HashMap<>();

		// Filling the map
		strategiesMap.put("client" + CrudOperationEnum.SAVE.name(), clientValidations);
		strategiesMap.put("client" + CrudOperationEnum.UPDATE.name(), clientUpdateValidations);
		strategiesMap.put("address" + CrudOperationEnum.SAVE.name(), addressValidations);
		strategiesMap.put("address" + CrudOperationEnum.UPDATE.name(), addressValidations);
		strategiesMap.put("creditcard" + CrudOperationEnum.SAVE.name(), creditCardValidations);
		strategiesMap.put("cartproduct" + CrudOperationEnum.SAVE.name(), cartProductsValidations);
		strategiesMap.put("cartproduct" + CrudOperationEnum.DELETE.name(), cartProductsDeleteValidations);

		return strategiesMap;
	}

}
