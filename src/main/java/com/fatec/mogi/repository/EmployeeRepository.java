package com.fatec.mogi.repository;

import com.fatec.mogi.model.domain.Employee;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	
	
}
