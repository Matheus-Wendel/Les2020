package com.fatec.mogi.DAO;

import com.fatec.mogi.model.aplication.Filter;
import com.fatec.mogi.model.aplication.Result;
import com.fatec.mogi.model.domain.DomainEntity;
import com.fatec.mogi.model.domain.Employee;
import com.fatec.mogi.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class EmployeeDAO extends AbstractDAO<Employee> {

	@Autowired
	UserRepository userRepository;

	@Autowired
	public EmployeeDAO(JpaRepository<Employee, Integer> repository) {
		super(repository);
	}

	@Override
	public Result save(Filter<? extends DomainEntity> filter) {
		var emplyee = (Employee) filter.getEntity();
		userRepository.save(emplyee.getUser());
		return super.save(filter);
	}

}
