package com.fatec.mogi.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.fatec.mogi.model.domain.Disc;
import com.fatec.mogi.model.domain.Purchase;
import com.fatec.mogi.model.domain.PurchaseItem;
import com.fatec.mogi.model.domain.Sale;
import com.fatec.mogi.repository.ClientRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
@CrossOrigin(origins = "*", maxAge = 3600L)
public class TestController {
	@Autowired
	ClientRepository clientRepository;

	@GetMapping
	public Object retornaModel() {
		// var cli = new Client();
		// cli.setBillingAddress(new Address());
		// cli.setDeliveryAddresses( Arrays.asList(new Address()));
		// cli.setUser(new User());
		Purchase purchase = new Purchase();
		PurchaseItem purchaseItem = new PurchaseItem();
		purchaseItem.setDisc(new Disc());
		List<PurchaseItem> lista = Arrays.asList(purchaseItem);
		purchase.setPurchaseItems(lista);
		return new Sale();

	}

	@GetMapping("/teste")
	public Map<String, String> retornapara(@RequestParam Map<String, String> parameters) {
		// var cli = new Client();
		// cli.setBillingAddress(new Address());
		// cli.setDeliveryAddresses( Arrays.asList(new Address()));
		// cli.setUser(new User());
		return parameters;

	}

}
