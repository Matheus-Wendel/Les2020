package com.fatec.mogi.DAO;

import java.util.Arrays;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fatec.mogi.model.aplication.Filter;
import com.fatec.mogi.model.aplication.Result;
import com.fatec.mogi.model.domain.DomainEntity;
import com.fatec.mogi.util.MessagesUtil;

@SuppressWarnings("unchecked")
// https://stackoverflow.com/questions/32728843/spring-data-optional-parameter-in-query-method
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
			result.setError(true);
			result.getMessages().put("Mensagem", e.getMessage());
			result.getMessages().put("Causa", e.getCause().toString());
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
					result.getMessages().put(MessagesUtil.NOT_FOUND, "id: " + entity.getId());
				}
				return result;
			}

			result.setResultList(repository.findAll());

			return result;

		} catch (Exception e) {
			result.setError(true);
			result.getMessages().put("Mensagem", e.getMessage());
			result.getMessages().put("Causa", e.getCause().toString());
			return result;
		}
	}

	@Override
	public Result update(Filter<? extends DomainEntity> filter) {
		T entity = (T) filter.getEntity();
		Result result = new Result();
		try {
			if (repository.existsById(entity.getId())) {
				T updatedEntity = repository.save(entity);
				result.setResultList(Arrays.asList(updatedEntity));
			} else {
				result.getMessages().put(MessagesUtil.NOT_FOUND, "id: " + entity.getId());
			}
			return result;
		} catch (Exception e) {
			result.setError(true);
			result.getMessages().put("Mensagem", e.getMessage());
			result.getMessages().put("Causa", e.getCause().toString());
			return result;
		}
	}

	@Override
	public Result delete(Filter<? extends DomainEntity> filter) {
		T entity = (T) filter.getEntity();
		Result result = new Result();
		try {
			if (repository.existsById(entity.getId())) {
				repository.deleteById(entity.getId());
				result.getMessages().put(MessagesUtil.DELETED, "id: " + entity.getId());

			} else {
				result.getMessages().put(MessagesUtil.NOT_FOUND, "id: " + entity.getId());
			}
			return result;
		} catch (Exception e) {
			result.setError(true);
			result.getMessages().put("Mensagem", e.getMessage());
			result.getMessages().put("Causa", e.getCause().toString());
			return result;
		}
	}

}
