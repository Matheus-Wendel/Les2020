package com.fatec.mogi.DAO;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.fatec.mogi.auth.AuthUtils;
import com.fatec.mogi.enumeration.PermissionEnum;
import com.fatec.mogi.model.aplication.Filter;
import com.fatec.mogi.model.aplication.Result;
import com.fatec.mogi.model.domain.Cart;
import com.fatec.mogi.model.domain.DomainEntity;
@Service
public class CartDAO extends AbstractDAO<Cart> {

	@Autowired
	public CartDAO(JpaRepository<Cart, Integer> repository) {
		super(repository);
	}

	@Override
	public Result find(Filter<? extends DomainEntity> filter) {
		var loggedUser = AuthUtils.getLoggedUser();
		if(loggedUser.getPermission()!=PermissionEnum.CLIENT) {
			return super.find(filter);
		}
		Result result = new Result();
		result.setResultList(Arrays.asList(AuthUtils.getLoggedClient().getCart()));
		return result;
	}
	

}
