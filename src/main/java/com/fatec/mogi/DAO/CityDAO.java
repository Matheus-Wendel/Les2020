package com.fatec.mogi.DAO;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.fatec.mogi.model.aplication.Filter;
import com.fatec.mogi.model.aplication.Result;
import com.fatec.mogi.model.domain.City;
import com.fatec.mogi.model.domain.DomainEntity;
import com.fatec.mogi.repository.CityRepository;

@Service
public class CityDAO extends AbstractDAO<City> {

	CityRepository cityRepository;

	@Autowired
	public CityDAO(JpaRepository<City, Integer> repository) {
		super(repository);
		this.cityRepository = (CityRepository) repository;
	}

	@Override
	public Result find(Filter<? extends DomainEntity> filter) {
		Map<String, String> parameters = filter.getParameters();
		Result result = new Result();
		
		if (parameters.containsKey("state")) {
			List<City> findByStateId = this.cityRepository.findByStateId(Integer.parseInt( parameters.get("state")));
			result.setResultList(findByStateId);
			return result;
		}
		return super.find(filter);
	}
}
