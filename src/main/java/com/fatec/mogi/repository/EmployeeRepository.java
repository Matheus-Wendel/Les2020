package com.fatec.mogi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fatec.mogi.model.domain.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	public Employee findByUserEmail(String email);
	
}
