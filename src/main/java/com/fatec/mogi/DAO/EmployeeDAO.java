package com.fatec.mogi.DAO;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.fatec.mogi.model.aplication.Filter;
import com.fatec.mogi.model.aplication.Result;
import com.fatec.mogi.model.domain.DomainEntity;
import com.fatec.mogi.model.domain.Employee;
import com.fatec.mogi.repository.EmployeeRepository;
import com.fatec.mogi.repository.UserRepository;

@Service
public class EmployeeDAO extends AbstractDAO<Employee> {

	@Autowired
	UserRepository userRepository;

	EmployeeRepository employeeRepository;
	
	@Autowired
	public EmployeeDAO(JpaRepository<Employee, Integer> repository) {
		super(repository);
		this.employeeRepository =(EmployeeRepository)  repository;
	}

	@Override
	public Result save(Filter<? extends DomainEntity> filter) {
		var emplyee = (Employee) filter.getEntity();
		userRepository.save(emplyee.getUser());
		return super.save(filter);
	}
	
	@Override
	public Result update(Filter<? extends DomainEntity> filter) {
		var emplyee = (Employee) filter.getEntity();
		userRepository.save(emplyee.getUser());
		return super.update(filter);
	}
	
	@Override
	public Result find(Filter<? extends DomainEntity> filter) {
		Employee employee = (Employee) filter.getEntity();
		if(employee!=null && employee.getUser()!=null && employee.getUser().getId()!=null) {
			Result result = new Result();
			result.setResultList(Arrays.asList(this.employeeRepository.findByUserEmail(employee.getUser().getEmail())));
			return result;
		}
		
		return super.find(filter);
	}


}
