package com.fatec.mogi.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fatec.mogi.model.domain.Employee;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600L)
@RequestMapping("/employee")
public class EmployeeController extends AbstractController<Employee> {

	public EmployeeController() {
		super(Employee.class);

	}

}
