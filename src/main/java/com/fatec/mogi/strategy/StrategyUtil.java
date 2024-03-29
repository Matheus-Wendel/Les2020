package com.fatec.mogi.strategy;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fatec.mogi.repository.CardBrandRepository;
import com.fatec.mogi.repository.CartProductRepository;
import com.fatec.mogi.repository.CartRepository;
import com.fatec.mogi.repository.CityRepository;
import com.fatec.mogi.repository.ClientRepository;
import com.fatec.mogi.repository.CouponRepository;
import com.fatec.mogi.repository.CreditCardRepository;
import com.fatec.mogi.repository.DiscRepository;
import com.fatec.mogi.repository.EmployeeRepository;
import com.fatec.mogi.repository.PricingRepository;
import com.fatec.mogi.repository.PurchaseItemRepository;
import com.fatec.mogi.repository.PurchaseRepository;
import com.fatec.mogi.repository.StockRepository;
import com.fatec.mogi.repository.TradeRepository;
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
	@Autowired
	CartRepository cartRepository;
	@Autowired
	StockRepository stockRepository;
	@Autowired
	PurchaseRepository purchaseRepository;
	@Autowired
	TradeRepository tradeRepository;

	@Autowired
	PurchaseItemRepository purchaseItemRepository;
	
	@Autowired 
	PricingRepository pricingRepository;
	
	@Autowired 
	EmployeeRepository employeeRepository;
	
	@Autowired
	CouponRepository couponRepository;

	public Map<String, Map<CrudOperationEnum, IStrategy>> getStrategies() {
		// Strategies maps
		Map<CrudOperationEnum, IStrategy> clientMap = new HashMap<>();
		Map<CrudOperationEnum, IStrategy> addressMap = new HashMap<>();
		Map<CrudOperationEnum, IStrategy> creditCardMap = new HashMap<>();
		Map<CrudOperationEnum, IStrategy> cartProductMap = new HashMap<>();
		Map<CrudOperationEnum, IStrategy> purchaseMap = new HashMap<>();
		Map<CrudOperationEnum, IStrategy> cartMap = new HashMap<>();
		Map<CrudOperationEnum, IStrategy> tradeMap = new HashMap<>();
		Map<CrudOperationEnum, IStrategy> pricingMap = new HashMap<>();
		Map<CrudOperationEnum, IStrategy> saleMap = new HashMap<>();
		Map<CrudOperationEnum, IStrategy> discMap = new HashMap<>();
		Map<CrudOperationEnum, IStrategy> stockMap = new HashMap<>();
		Map<CrudOperationEnum, IStrategy> employeeMap  = new HashMap<>();
		Map<CrudOperationEnum, IStrategy> couponMap  = new HashMap<>();

		// Strategies Instances
		AddressValidation addressValidation = new AddressValidation(cityRepository);
		ClientValidation clientValidation = new ClientValidation(clientRepository, addressValidation);
		ClientUpdateValidation clientUpdateValidation = new ClientUpdateValidation(addressValidation);
		CreditCardValidation creditCardValidation = new CreditCardValidation(cardBrandRepository);
		CartProductValidation cartProductValidation = new CartProductValidation(discRepository, stockRepository);
		CartProductDeleteValidation cartProductDeleteValidation = new CartProductDeleteValidation(discRepository,
				cartProductRepository);
		
		PurchaseValidation purchaseValidation = new PurchaseValidation(creditCardRepository,discRepository);
		CartProductUpdateValidation cartProductUpdateValidation = new CartProductUpdateValidation(discRepository,
				cartProductRepository, stockRepository, clientRepository);
		
		CartUpdateValidation cartUpdateValidation = new CartUpdateValidation(cartRepository,
				cartProductUpdateValidation);
		PurchaseUpdateValidation purchaseUpdateValidation = new PurchaseUpdateValidation(purchaseRepository);
		
		TradeValidation tradeValidation = new TradeValidation(purchaseItemRepository,purchaseRepository);
		TradeUpdateValidation tradeUpdateValidation = new TradeUpdateValidation(tradeRepository,purchaseItemRepository);
		
		PricingValidation pricingValidation = new PricingValidation();
		PricingUpdateValidation pricingUpdateValidation = new PricingUpdateValidation(pricingRepository);
		
		SaleUpdateValidation saleUpdateValidation = new SaleUpdateValidation(pricingRepository);
		
		DiscValidation discValidation = new DiscValidation();
		DiscUpdateValidation discUpdateValidation = new DiscUpdateValidation(discRepository);
		
		StockValidation stockValidation = new StockValidation(discRepository);
		
		EmployeeValidation employeeValidation = new EmployeeValidation();
		
		EmployeeUpdateValidation employeeUpdateValidation = new EmployeeUpdateValidation(employeeRepository);
		
		CouponValidation couponValidation = new CouponValidation(couponRepository);

		clientMap.put(CrudOperationEnum.SAVE, clientValidation);
		clientMap.put(CrudOperationEnum.UPDATE, clientUpdateValidation);

		addressMap.put(CrudOperationEnum.SAVE, addressValidation);
		addressMap.put(CrudOperationEnum.UPDATE, addressValidation);

		creditCardMap.put(CrudOperationEnum.SAVE, creditCardValidation);

		cartProductMap.put(CrudOperationEnum.SAVE, cartProductValidation);
		cartProductMap.put(CrudOperationEnum.DELETE, cartProductDeleteValidation);
		cartProductMap.put(CrudOperationEnum.UPDATE, cartProductUpdateValidation);

		cartMap.put(CrudOperationEnum.UPDATE, cartUpdateValidation);

		purchaseMap.put(CrudOperationEnum.SAVE, purchaseValidation);
		purchaseMap.put(CrudOperationEnum.UPDATE, purchaseUpdateValidation);
		
		tradeMap.put(CrudOperationEnum.SAVE, tradeValidation);
		tradeMap.put(CrudOperationEnum.UPDATE, tradeUpdateValidation);
		
		pricingMap.put(CrudOperationEnum.SAVE, pricingValidation);
		pricingMap.put(CrudOperationEnum.UPDATE, pricingUpdateValidation);

		saleMap.put(CrudOperationEnum.UPDATE, saleUpdateValidation);
		
		discMap.put(CrudOperationEnum.SAVE, discValidation);
		discMap.put(CrudOperationEnum.UPDATE, discUpdateValidation);
		
		stockMap.put(CrudOperationEnum.SAVE, stockValidation);
		
		employeeMap.put(CrudOperationEnum.SAVE, employeeValidation);
		employeeMap.put(CrudOperationEnum.UPDATE, employeeUpdateValidation);
		
		
		couponMap.put(CrudOperationEnum.SAVE, couponValidation);
		couponMap.put(CrudOperationEnum.UPDATE, couponValidation);

		// Filling the lists

		// Strategy map
		Map<String, Map<CrudOperationEnum, IStrategy>> strategiesMap = new HashMap<>();

		// Filling the map
		strategiesMap.put("client", clientMap);
		strategiesMap.put("address", addressMap);
		strategiesMap.put("creditcard", creditCardMap);
		strategiesMap.put("cartproduct", cartProductMap);
		strategiesMap.put("cart", cartMap);
		strategiesMap.put("purchase", purchaseMap);
		strategiesMap.put("trade", tradeMap);
		strategiesMap.put("pricing", pricingMap);
		strategiesMap.put("sale", saleMap);
		strategiesMap.put("disc", discMap);
		strategiesMap.put("stock", stockMap);
		strategiesMap.put("employee", employeeMap);
		strategiesMap.put("coupon", couponMap);

		return strategiesMap;
	}

}
