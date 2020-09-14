package com.fatec.mogi;

import java.util.Arrays;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.fatec.mogi.enumeration.PermissionEnum;
import com.fatec.mogi.model.domain.Address;
import com.fatec.mogi.model.domain.CardBrand;
import com.fatec.mogi.model.domain.Cart;
import com.fatec.mogi.model.domain.City;
import com.fatec.mogi.model.domain.Client;
import com.fatec.mogi.model.domain.CreditCard;
import com.fatec.mogi.model.domain.User;
import com.fatec.mogi.repository.AddressRepository;
import com.fatec.mogi.repository.ClientRepository;

@SpringBootApplication()
public class ApiLesApplication implements CommandLineRunner {

	@Autowired
	ClientRepository clientRepository;

	@Autowired
	AddressRepository addressRepository;

	public static void main(String[] args) {
		SpringApplication.run(ApiLesApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		var client = new Client();
		var deliveryAddresses1 = new Address();
		var deliveryAddresses2 = new Address();
		var billingAddres = new Address();
		var creditCard1 = new CreditCard();
		var creditCard2 = new CreditCard();

		var cardBrand1 = new CardBrand();
		var cardBrand2 = new CardBrand();

		var user = new User();
		var cart = new Cart();

		var dA1City = new City();
		dA1City.setId(10);
		var dA2City = new City();
		dA2City.setId(2);

		var bACity = new City();
		bACity.setId(17);

		cardBrand1.setId(1);
		cardBrand2.setId(3);

		creditCard1.setCardBrand(cardBrand1);
		creditCard1.setCvv("111");
		creditCard1.setName("MAE DA SILVA");
		creditCard1.setNumber("4444777755552222");
		creditCard1.setVailid(true);
		var calendar1 = new Calendar.Builder().set(Calendar.MONTH, 10).set(Calendar.YEAR, 2025).build();
		creditCard1.setExpirationDate(calendar1.getTime());

		creditCard2.setCardBrand(cardBrand2);
		creditCard2.setCvv("452");
		creditCard2.setName("EU DA SILVA");
		creditCard2.setNumber("7777888855552222");
		var calendar2 = new Calendar.Builder().set(Calendar.MONTH, 8).set(Calendar.YEAR, 2025).build();
		creditCard2.setExpirationDate(calendar2.getTime());
		creditCard2.setVailid(false);

		user.setEmail("matheus");
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		user.setPassword(bCryptPasswordEncoder.encode("matheus"));

		user.setPermission(PermissionEnum.CLIENT);
		deliveryAddresses1.setAddressDescription("Rua do Quinto da fatex");
		deliveryAddresses1.setAddressType("Rua");
		deliveryAddresses1.setDescription("Casa");
		deliveryAddresses1.setDistrict("Centro");
		deliveryAddresses1.setNumber("44");
		deliveryAddresses1.setObservations("Prox ao inferno");
		deliveryAddresses1.setCity(dA1City);

		deliveryAddresses2.setAddressDescription("Via do meio");
		deliveryAddresses2.setAddressType("VIA");
		deliveryAddresses2.setDescription("Trabalho");
		deliveryAddresses2.setDistrict("Vila do leste");
		deliveryAddresses2.setNumber("81");
		deliveryAddresses2.setCity(dA2City);

		billingAddres.setAddressDescription("AV da mae ");
		billingAddres.setAddressType("Avenida");
		billingAddres.setDescription("Apartamento");
		billingAddres.setDistrict("Vila vial");
		billingAddres.setNumber("4");
		billingAddres.setCity(bACity);

		client.setCode("UNIQUECODE CHANGE TO ID?");
		client.setCpf("44444444444");
		client.setGenre("M");
		client.setName("Eu da Silva");
		client.setRanking(5);
		client.setTelephone("1199999999");
		client.setCart(cart);
		client.setUser(user);
		client.setCreditCards(Arrays.asList(creditCard1, creditCard2));
client.setDeliveryAddresses(Arrays.asList(deliveryAddresses1,deliveryAddresses2));
		client.getDeliveryAddresses().stream().forEach(a -> a.setClient(client));
		clientRepository.save(client);
//		deliveryAddresses1.setClient(client);
//		deliveryAddresses2.setClient(client);
//		addressRepository.save(deliveryAddresses1);
//		addressRepository.save(deliveryAddresses2);
	}

}
