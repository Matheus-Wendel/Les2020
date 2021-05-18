package com.fatec.mogi.DAO;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.fatec.mogi.auth.AuthUtils;
import com.fatec.mogi.enumeration.PermissionEnum;
import com.fatec.mogi.enumeration.TradeStatusEnum;
import com.fatec.mogi.model.aplication.Filter;
import com.fatec.mogi.model.aplication.Result;
import com.fatec.mogi.model.domain.DomainEntity;
import com.fatec.mogi.model.domain.Trade;
import com.fatec.mogi.repository.CouponRepository;
import com.fatec.mogi.repository.DiscRepository;
import com.fatec.mogi.repository.TradeRepository;

@Service
public class TradeDAO extends AbstractDAO<Trade> {

	@Autowired
	DiscRepository discRepository;
	@Autowired
	CouponRepository couponRepository;
	TradeRepository tradeRepository;

	@Autowired
	public TradeDAO(JpaRepository<Trade, Integer> repository) {

		super(repository);
		this.tradeRepository = (TradeRepository) repository;
	}

	@Override
	public Result update(Filter<? extends DomainEntity> filter) {
		Trade trade = (Trade) filter.getEntity();
		discRepository.save(trade.getPurchaseItem().getDisc());
		if(trade.getChangeCoupon()!=null) {
			
			couponRepository.save(trade.getChangeCoupon());
		}
		return super.update(filter);
	}

	@Override
	public Result find(Filter<? extends DomainEntity> filter) {
		Map<String, String> parameters = filter.getParameters();
		Result result = new Result();

		if (parameters.containsKey("status")) {
			List<Trade> findTrades = this.tradeRepository
					.findByStatus(TradeStatusEnum.valueOf(parameters.get("status")));
			result.setResultList(findTrades);
			return result;
		}
		var loggedUser = AuthUtils.getLoggedUser();
		if (loggedUser.getPermission() != PermissionEnum.CLIENT) {
			return super.find(filter);
		}

		result.setError(true);
		result.getMessages().put("Mensagem", "Não autorizado");
		result.getMessages().put("Causa", "Não autorizado");
		return result;

	}

}
