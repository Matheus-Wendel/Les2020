package com.fatec.mogi.DAO;

import java.util.Arrays;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fatec.mogi.model.aplication.Filter;
import com.fatec.mogi.model.aplication.Result;
import com.fatec.mogi.model.domain.DomainEntity;

@SuppressWarnings("unchecked")
public abstract class AbstractDAO<T extends DomainEntity> implements IDAO {
	protected JpaRepository<T, Integer> repository;

	public AbstractDAO(JpaRepository<T, Integer> repository) {
		this.repository = repository;
	}

	@Override
	public Result save(Filter<? extends DomainEntity> filter) {
		T entity = (T) filter.getEntity();
		Result result = new Result();
		try {
			T savedEntity = repository.save(entity);
			result.setResultList(Arrays.asList(savedEntity));
			return result;
		} catch (Exception e) {
			e.getCause().toString();
			result.setError(true);
			result.getMessages().put("Message", e.getMessage());
			result.getMessages().put("Cause", e.getCause().toString());
			return result;
		}
	}

	@Override
	public Result find(Filter<? extends DomainEntity> filter) {
		T entity = (T) filter.getEntity();
		Result result = new Result();
		try {
			if (entity != null && entity.getId() != null) {

				Optional<T> findById = repository.findById(entity.getId());

				if (findById.isPresent()) {
					result.setResultList(Arrays.asList(findById.get()));
				} else {
					result.getMessages().put("Not Found", "id: " + entity.getId());
				}
				return result;
			}

			result.setResultList(repository.findAll());

			return result;

		} catch (Exception e) {
			e.getCause().toString();
			result.setError(true);
			result.getMessages().put("Message", e.getMessage());
			result.getMessages().put("Cause", e.getCause().toString());
			return result;
		}
	}

	@Override
	public Result update(Filter<? extends DomainEntity> filter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result delete(Filter<? extends DomainEntity> filter) {
		// TODO Auto-generated method stub
		return null;
	}

}
