package com.fatec.mogi;

import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication()
public class ApiLesApplication implements CommandLineRunner {
//
//	@Autowired
//	ClientDAO clientDao;
//	@Autowired
//	EmployeeDAO employeeDAO;
//	@Autowired
//	RecorderDAO recorderDAO;
//	@Autowired
//	ArtistDAO artistDAO;
//	@Autowired
//	GenreDAO genreDAO;
//	@Autowired
//	PricingDAO pricingDAO;
//
//	@Autowired
//	StockDAO stockDAO;
//
//	@Autowired
//	DiscDAO discDAO;
//	@Autowired
//	CartProductDAO cartProductDAO;
//	
//	@Autowired
//	CouponRepository couponRepository;
//	@Autowired
//	CreditCardRepository creditCardRepository;
//

	public static void main(String[] args) {
		SpringApplication.run(ApiLesApplication.class, args);
	}
// REGEX PARA SELECIONAR UM COMANDO DE CREATE INTEIRO, MESMO ATRAVEZ DE LINHAS
//CREATE([\s\S]*?);
//DROP ALL OBJECTS ;
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
	}
	@PostConstruct
	  public void init(){
	    // Setting Spring Boot SetTimeZone
	    TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
	  }

//	@Override
//	public void run(String... args) throws Exception {
//		var client = new Client();
//		var deliveryAddresses1 = new Address();
//		var deliveryAddresses2 = new Address();
//		var billingAddres = new Address();
//		var creditCard1 = new CreditCard();
//		var creditCard2 = new CreditCard();
//
//		var cardBrand1 = new CardBrand();
//		var cardBrand2 = new CardBrand();
//
//		var user = new User();
//		var cart = new Cart();
//
//		var dA1City = new City();
//		dA1City.setId(10);
//		var dA2City = new City();
//		dA2City.setId(2);
//
//		var bACity = new City();
//		bACity.setId(17);
//
//		cardBrand1.setId(1);
//		cardBrand2.setId(3);
//
//		creditCard1.setCardBrand(cardBrand1);
//		creditCard1.setCvv("111");
//		creditCard1.setName("MAE DA SILVA");
//		creditCard1.setNumber("4444777755552222");
//		creditCard1.setVailid(true);
//		var calendar1 = new Calendar.Builder().set(Calendar.MONTH, 10).set(Calendar.YEAR, 2025).build();
//		creditCard1.setExpirationDate(calendar1.getTime());
//
//		creditCard2.setCardBrand(cardBrand2);
//		creditCard2.setCvv("452");
//		creditCard2.setName("EU DA SILVA");
//		creditCard2.setNumber("7777888855552222");
//		var calendar2 = new Calendar.Builder().set(Calendar.MONTH, 8).set(Calendar.YEAR, 2025).build();
//		creditCard2.setExpirationDate(calendar2.getTime());
//		creditCard2.setVailid(false);
//
//		user.setEmail("matheus");
//		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
//		user.setPassword(bCryptPasswordEncoder.encode("matheus"));
//
//		user.setPermission(PermissionEnum.CLIENT);
//		deliveryAddresses1.setAddressDescription("Rua do Quinto da fatex");
//		deliveryAddresses1.setAddressType("Rua");
//		deliveryAddresses1.setDescription("Casa");
//		deliveryAddresses1.setDistrict("Centro");
//		deliveryAddresses1.setNumber("44");
//		deliveryAddresses1.setObservations("Prox ao inferno");
//		deliveryAddresses1.setCity(dA1City);
//
//		deliveryAddresses2.setAddressDescription("Via do meio");
//		deliveryAddresses2.setAddressType("VIA");
//		deliveryAddresses2.setDescription("Trabalho");
//		deliveryAddresses2.setDistrict("Vila do leste");
//		deliveryAddresses2.setNumber("81");
//		deliveryAddresses2.setCity(dA2City);
//
//		billingAddres.setAddressDescription("AV da mae ");
//		billingAddres.setAddressType("Avenida");
//		billingAddres.setDescription("Apartamento");
//		billingAddres.setDistrict("Vila vial");
//		billingAddres.setNumber("4");
//		billingAddres.setCity(bACity);
//
//		client.setCpf("44444444444");
//		client.setGenre("M");
//		client.setName("Eu da Silva");
//		client.setRanking(5);
//		client.setTelephone("1199999999");
//		client.setCart(cart);
//		client.setUser(user);
//		client.setBillingAddress(billingAddres);
//		client.setDeliveryAddresses(Arrays.asList(deliveryAddresses1, deliveryAddresses2));
//		creditCard1.setClient(client);
//		creditCard2.setClient(client);
//
//		clientDao.save(new Filter<Client>(client, Client.class));
//		creditCardRepository.save(creditCard1);
//		creditCardRepository.save(creditCard2);
//
//		Employee employee = new Employee();
//		employee.setCpf("43199999999");
//		employee.setName("matheus");
//		user = new User();
//
//		user.setEmail("employee");
//		user.setPassword(bCryptPasswordEncoder.encode("employee"));
//		user.setPermission(PermissionEnum.EMPLOYEE);
//		employee.setUser(user);
//		employeeDAO.save(new Filter<Employee>(employee, Employee.class));
//
//		var recorderList = Arrays.asList(createRecorder("Sony music"), createRecorder("Warner records"));
//		recorderList.forEach(r -> recorderDAO.save(new Filter<Recorder>(r, Recorder.class)));
//
//		var artistArray = Arrays.asList(createArtist("Daft Punk"), createArtist("Tim maia"));
//		artistArray.forEach(a -> artistDAO.save(new Filter<Artist>(a, Artist.class)));
//
//		var genreArray = Arrays.asList(createGenre("Eletronic", "lo fi hip hop beats to study and relax to"),
//				createGenre("Soul", "Musica para a alma"));
//		genreArray.forEach(g -> genreDAO.save(new Filter<Genre>(g, Genre.class)));
//
//		var pricingArray = Arrays.asList(
//				createPricing(1, "Discos nacionais", 0.7, createSale(0.5, SaleStatusEnum.INACTIVE)),
//				createPricing(1.5, "Discos internacionais", 1, createSale(1, SaleStatusEnum.ACTIVE)));
//		pricingArray.forEach(p -> pricingDAO.save(new Filter<Pricing>(p, Pricing.class)));
//
//		var activationDetails = new ActivationDetails();
//		activationDetails.setCategory("ACTIVATION");
//		activationDetails.setMotive("FORA_DE_MERCADO");
//
//		var stock = new Stock();
//		stock.setCostPrice(5);
//		stock.setPurchaceDate(new Date());
//		stock.setQuantity(10);
//		Disc disc = createDisc(activationDetails, true, artistArray, "Bowl Cut", genreArray,
//				"https://studiosol-a.akamaihd.net/uploadfile/letras/albuns/7/c/f/a/817421574970931.jpg", "Bowl Cut", pricingArray.get(0),
//				recorderList.get(0), new Date(), "Qurs", 100, Arrays.asList(stock));
//
////		stockDAO.save(new Filter<Stock>(stock, Stock.class));
////
////		discDAO.save(new Filter<Disc>(disc, Disc.class));
////
////		discDAO.save(new Filter<Disc>(createDisc(createActivationDetails(), true, Arrays.asList(artistArray.get(0)), "World Class", Arrays.asList(genreArray.get(0)),
////				"https://img.discogs.com/zGV9buJV8NGAzOlu7pPFantEVWk=/fit-in/600x600/filters:strip_icc():format(webp):mode_rgb():quality(90)/discogs-images/R-7264886-1548635423-4660.jpeg.jpg", "World Class", pricingArray.get(1),
////				recorderList.get(1), new Date(), "", 0.0, Arrays.asList(createStock(10, 50), createStock(500, 12))),
////				Disc.class));
////
////		discDAO.save(new Filter<Disc>(createDisc(createActivationDetails(), true, Arrays.asList(artistArray.get(1)), "Melophobia", Arrays.asList(genreArray.get(1)),
////				"https://img.discogs.com/xDy8zBL4J8bUQpqOjzjcmbvCcqE=/fit-in/600x600/filters:strip_icc():format(jpeg):mode_rgb():quality(90)/discogs-images/R-4997585-1393452059-9334.jpeg.jpg", "Melophobia",
////				pricingArray.get(0), recorderList.get(1), new Date(), "", 0.0,
////				Arrays.asList(createStock(20, 50), createStock(25, 12))), Disc.class));
////
////		CartProduct cartProduct = new CartProduct();
////		cartProduct.setAddedDate(new Date());
////		cartProduct.setDisc(disc);
////		cartProduct.setQuantity(2);
////		cartProduct.setCart(client.getCart());
////
////		cartProductDAO.save(new Filter<CartProduct>(cartProduct, CartProduct.class));
////		
////		couponRepository.save(createCoupon(true, null, "123123", new GregorianCalendar(2022	, 12, 1).getTime(), CouponTypeEnum.PROMOTIONAL, 10));
////		couponRepository.save(createCoupon(true, client, "aaa111", new GregorianCalendar(2022	, 12, 1).getTime(), CouponTypeEnum.TRADE, 20	));
////		
//
//	}
//
//	private Recorder createRecorder(String name) {
//		Recorder recorder = new Recorder();
//		recorder.setName(name);
//		return recorder;
//	}
//
//	private Artist createArtist(String name) {
//		Artist artist = new Artist();
//		artist.setName(name);
//		return artist;
//	}
//
//	private Genre createGenre(String name, String description) {
//		Genre g = new Genre();
//		g.setName(name);
//		g.setDescription(description);
//		return g;
//	}
//
//	private Sale createSale(double profit, SaleStatusEnum status) {
//		var sale = new Sale();
//		sale.setProfit(profit);
//		sale.setStatus(status);
//		return sale;
//	}
//
//	private Pricing createPricing(double dprofit, String description, double mprofit, Sale sale) {
//		Pricing pricing = new Pricing();
//		pricing.setDefautProfit(dprofit);
//		pricing.setDescription("Discos nacionais");
//		pricing.setMinimumProfit(0.7);
//		pricing.setSale(sale);
//		return pricing;
//	}
//
//	private Stock createStock(double costPrice, int quantity) {
//		Stock stock = new Stock();
//		stock.setCostPrice(costPrice);
//		stock.setPurchaceDate(new Date());
//		stock.setQuantity(quantity);
//		return stock;
//	}
//
//	private ActivationDetails createActivationDetails() {
//		var activationDetails = new ActivationDetails();
//		activationDetails.setCategory("ACTIVATION");
//		activationDetails.setMotive("FORA_DE_MERCADO");
//		return activationDetails;
//	}
//
//	private Disc createDisc(ActivationDetails activationDetails, boolean active, List<Artist> artists,
//			String description, List<Genre> genres, String imgLink, String name, Pricing pricing, Recorder recorder,
//			Date releaseDate, String status, double value, List<Stock> stock) {
//		Disc disc = new Disc();
//		disc.setActivationDetails(activationDetails);
//		disc.setActive(active);
//		disc.setArtists(artists);
//		disc.setDescription(description);
//		disc.setGenres(genres);
//		disc.setImgLink(imgLink);
//		disc.setName(name);
//		disc.setPricing(pricing);
//		disc.setRecorder(recorder);
//		disc.setReleaseDate(releaseDate);
//		//disc.setStatus(status);
//		disc.setValue(value);
//		disc.setStock(stock);
//		return disc;
//
//	}
//	private Coupon createCoupon(boolean active, Client client,String code,Date expirationDate, CouponTypeEnum type, double value) {
//		Coupon coupon = new Coupon();
//		coupon.setActive(active);
//		coupon.setClient(client);
//		coupon.setCode(code);
//		coupon.setExpirationDate(expirationDate);
//		coupon.setType(type);
//		coupon.setValue(value);
//		return coupon;
//	}

}
