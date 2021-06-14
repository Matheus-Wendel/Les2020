package com.fatec.mogi.DAO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.fatec.mogi.auth.AuthUtils;
import com.fatec.mogi.enumeration.PermissionEnum;
import com.fatec.mogi.model.aplication.Filter;
import com.fatec.mogi.model.aplication.Result;
import com.fatec.mogi.model.domain.CartProduct;
import com.fatec.mogi.model.domain.Client;
import com.fatec.mogi.model.domain.DomainEntity;
import com.fatec.mogi.model.domain.Purchase;
import com.fatec.mogi.repository.CartProductRepository;
import com.fatec.mogi.repository.ClientRepository;
import com.fatec.mogi.repository.CouponRepository;
import com.fatec.mogi.repository.PurchaseCardRepository;
import com.fatec.mogi.repository.PurchaseItemRepository;
import com.fatec.mogi.repository.PurchaseRepository;

@Service
public class PurchaseDAO extends AbstractDAO<Purchase> {

	@Autowired
	PurchaseItemRepository purchaseItemRepository;
	@Autowired
	PurchaseCardRepository purchaseCardRepository;
	@Autowired
	CartProductRepository cartProductRepository;
	@Autowired
	CouponRepository couponRepository;
	@Autowired
	ClientRepository clientRepository;

	PurchaseRepository purchaseRepository;

	@Autowired
	public PurchaseDAO(JpaRepository<Purchase, Integer> repository) {
		super(repository);
		this.purchaseRepository = (PurchaseRepository) repository;
	}

	@Override
	public Result find(Filter<? extends DomainEntity> filter) {
		var loggedUser = AuthUtils.getLoggedUser();
		if (loggedUser.getPermission() != PermissionEnum.CLIENT) {
			Map<String, String> allparameters = filter.getParameters();
			Map<String, String> parameters = new HashMap<String, String>();

			allparameters.forEach((key, value) -> {
				if (!value.isBlank()) {
					parameters.put(key, value);
				}
			});

			if (parameters.containsKey("startDate") && parameters.containsKey("endDate")) {
				try {
					Date startDate = new SimpleDateFormat("yyyy-MM-dd").parse(parameters.get("startDate"));
					Date endDate = new SimpleDateFormat("yyyy-MM-dd").parse(parameters.get("endDate"));
					Result result = new Result();
					result.setResultList(
							this.purchaseRepository.findByPurchaseDateBetweenOrderByPurchaseDateAsc(startDate, endDate));
					return result;
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return super.find(filter);
		}
		Result result = new Result();
		Client loggedClient = AuthUtils.getLoggedClient();
		result.setResultList(this.purchaseRepository.findByClientId(loggedClient.getId()));
		return result;
	}

	@Override
	@Transactional
	public Result save(Filter<? extends DomainEntity> filter) {
		Purchase purchase = (Purchase) filter.getEntity();
		// purchase.getPurchaseItems().forEach(pi->pi.setPurchase(purchase));
		purchaseItemRepository.saveAll(purchase.getPurchaseItems());
		purchaseCardRepository.saveAll(purchase.getPurchaseCards());
		couponRepository.saveAll(purchase.getTradeCoupons());
		var loggedClientEmail = AuthUtils.getUserEmail();
		var client = clientRepository.findByUserEmail(loggedClientEmail);
		// Client client = clientRepository.findById(loggedClient.getId()).get();
		List<CartProduct> cartProducts = client.getCart().getCartProducts();
		cartProductRepository.deleteAll(cartProducts);
		//
		return super.save(filter);
	}

}
