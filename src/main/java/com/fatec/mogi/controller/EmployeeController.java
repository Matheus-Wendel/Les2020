package com.fatec.mogi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fatec.mogi.auth.AuthUtils;
import com.fatec.mogi.model.aplication.Filter;
import com.fatec.mogi.model.domain.Employee;
import com.fatec.mogi.model.domain.User;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600L)
@RequestMapping("/employee")
public class EmployeeController extends AbstractController<Employee> {

	public EmployeeController() {
		super(Employee.class);

	}
	
	@SuppressWarnings("rawtypes")
	@GetMapping(path = "/me")
	public ResponseEntity find( ) {
		var employee = new Employee();
		User loggedUser = AuthUtils.getLoggedUser();
		employee.setUser(loggedUser);
		Filter<Employee> filter =new Filter<Employee>(employee,this.clazz);
		return findCommand.execute(filter).buildResponse();
	}

}
