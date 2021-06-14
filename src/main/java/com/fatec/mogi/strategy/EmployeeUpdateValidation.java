package com.fatec.mogi.strategy;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.fatec.mogi.model.domain.DomainEntity;
import com.fatec.mogi.model.domain.Employee;
import com.fatec.mogi.model.domain.Person;
import com.fatec.mogi.model.domain.User;
import com.fatec.mogi.repository.EmployeeRepository;

public class EmployeeUpdateValidation implements IStrategy {


	private EmployeeRepository employeeRepository;
	public EmployeeUpdateValidation(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}
	@Override
	public String process(DomainEntity entity) {
		StringBuilder sb = new StringBuilder();

		var employee = (Employee)entity;
		Optional<Employee> findById = employeeRepository.findById(employee.getId());
		if(findById.isEmpty()) {
			sb.append("FuncionarioInvalido");
			return sb.toString();
		}
		Employee oldEmployee = findById.get();
		var person = (Person) employee;
		if (person.getCpf() == null || person.getCpf().isBlank()) {
			sb.append("CPF não pode ser vazio;;");
		}
		if (person.getName() == null || person.getName().isBlank()) {
			sb.append("Nome não pode ser vazio;;");
		}
		if (person.getUser() == null) {
			sb.append("Dados de usuarios invalidos;;");
		} else {
			User user = employee.getUser();

			if (user.getEmail() == null || user.getEmail().isBlank()) {
				sb.append("Email não pode ser vazio").append(",	");
			}

			if (user.getPassword() == null || user.getPassword().isBlank()) {
				user.setPassword(oldEmployee.getUser().getPassword());
			} else {

				Pattern pattern = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$");

				Matcher matcher = pattern.matcher(user.getPassword());

				if (matcher.matches()) {
					BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
					user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
				} else {
					sb.append("Senha invalida");
				}
			}
		}
	

		return sb.toString();
	}

}
