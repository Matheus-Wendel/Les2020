package com.fatec.mogi.strategy;

import org.springframework.stereotype.Service;

import com.fatec.mogi.model.domain.DomainEntity;
import com.fatec.mogi.model.domain.User;
import com.fatec.mogi.repository.UserRepository;
@Service
public class UserValidation implements IStrategy{

	
	UserRepository repository;
	
	
	
	public UserValidation(UserRepository repository) {	
		this.repository = repository;
	}
	
	@Override
	public String process(DomainEntity entity) {
		User user = (User) entity;
		StringBuilder sb = new StringBuilder();
		
		if(user.getEmail()==null||user.getEmail().isBlank()) {
			sb.append("Email não pode ser vazio").append(",	");
		}
		if(user.getName()==null||user.getName().isBlank()) {
			sb.append("Nome não pode ser vazio").append(", ");
		}
		
		if(sb.length()>0) {
			return sb.toString();
		}
		
		if(!repository.findByEmail(user.getEmail()).isEmpty()) {
			sb.append("Email ja Cadastrado").append(", ");
		}
		
		return sb.toString();
		
	}

}
