package com.fatec.mogi.strategy;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fatec.mogi.repository.CardBrandRepository;
import com.fatec.mogi.repository.CartProductRepository;
import com.fatec.mogi.repository.CityRepository;
import com.fatec.mogi.repository.ClientRepository;
import com.fatec.mogi.repository.CreditCardRepository;
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
	@Autowired
	CreditCardRepository creditCardRepository;

	public Map<String, Map<CrudOperationEnum, IStrategy>> getStrategies() {
		// Strategies maps
		Map<CrudOperationEnum, IStrategy> clientMap = new HashMap<>();
		Map<CrudOperationEnum, IStrategy> addressMap = new HashMap<>();
		Map<CrudOperationEnum, IStrategy> creditCardMap = new HashMap<>();
		Map<CrudOperationEnum, IStrategy> cartProductMap = new HashMap<>();
		Map<CrudOperationEnum, IStrategy> purchaseMap = new HashMap<>();

		// Strategies Instances
		AddressValidation addressValidation = new AddressValidation(cityRepository);
		ClientValidation clientValidation = new ClientValidation(clientRepository, addressValidation);
		ClientUpdateValidation clientUpdateValidation = new ClientUpdateValidation(addressValidation);
		CreditCardValidation creditCardValidation = new CreditCardValidation(cardBrandRepository);
		CartProductValidation cartProductValidation = new CartProductValidation(discRepository);
		CartProductDeleteValidation cartProductDeleteValidation = new CartProductDeleteValidation(discRepository,
				cartProductRepository);
		PurchaseValidation purchaseValidation = new PurchaseValidation(creditCardRepository);
		CartProductUpdateValidation cartProductUpdateValidation = new CartProductUpdateValidation(discRepository,
				cartProductRepository);

		clientMap.put(CrudOperationEnum.SAVE, clientValidation);
		clientMap.put(CrudOperationEnum.UPDATE, clientUpdateValidation);

		addressMap.put(CrudOperationEnum.SAVE, addressValidation);
		addressMap.put(CrudOperationEnum.UPDATE, addressValidation);

		creditCardMap.put(CrudOperationEnum.SAVE, creditCardValidation);

		cartProductMap.put(CrudOperationEnum.SAVE, cartProductValidation);
		cartProductMap.put(CrudOperationEnum.DELETE, cartProductDeleteValidation);
		cartProductMap.put(CrudOperationEnum.UPDATE, cartProductUpdateValidation);

		purchaseMap.put(CrudOperationEnum.SAVE, purchaseValidation);
		// Filling the lists

		// Strategy map
		Map<String, Map<CrudOperationEnum, IStrategy>> strategiesMap = new HashMap<>();

		// Filling the map
		strategiesMap.put("client", clientMap);
		strategiesMap.put("address", addressMap);
		strategiesMap.put("creditcard", creditCardMap);
		strategiesMap.put("cartproduct", cartProductMap);
		strategiesMap.put("purchase", purchaseMap);

		return strategiesMap;
	}

}
