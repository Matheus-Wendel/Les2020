package com.fatec.mogi.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.fatec.mogi.command.DeleteCommand;
import com.fatec.mogi.command.FindCommand;
import com.fatec.mogi.command.SaveCommand;
import com.fatec.mogi.command.UpdateCommand;
import com.fatec.mogi.model.aplication.Filter;
import com.fatec.mogi.model.domain.DomainEntity;

@SuppressWarnings("rawtypes")
public class AbstractController<T extends DomainEntity> {
	
	@Autowired
	DeleteCommand deleteCommand;
	@Autowired
	SaveCommand saveCommand;
	@Autowired
	FindCommand findCommand;
	@Autowired
	UpdateCommand updateCommand;

	Class<? extends DomainEntity> clazz;
	
	
	public AbstractController(Class<? extends DomainEntity> clazz) {
		this.clazz = clazz;
	}

	@PostMapping
	public ResponseEntity save(@RequestBody T entity) {
		Filter<T> filter =new Filter<T>(entity,clazz);
		return saveCommand.execute(filter).buildResponse(); 
	}

	@GetMapping
	public ResponseEntity find(@RequestParam Map<String,String> parameters) {
		Filter<T> filter =new Filter<T>(parameters,clazz);
		return findCommand.execute(filter).buildResponse();
	}

	public ResponseEntity delete(T  entity) {
		Filter<T> filter =new Filter<T>(entity,clazz);
		return deleteCommand.execute(filter).buildResponse();
	}

	@PutMapping
	public ResponseEntity  update(@RequestBody(required = true) T entity) {
		Filter<T> filter =new Filter<T>(entity,clazz);
		return updateCommand.execute(filter).buildResponse();
	}

}
