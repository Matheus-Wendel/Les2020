package com.fatec.mogi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.fatec.mogi.command.DeleteCommand;
import com.fatec.mogi.command.FindCommand;
import com.fatec.mogi.command.SaveCommand;
import com.fatec.mogi.command.UpdateCommand;
import com.fatec.mogi.model.aplication.Filter;
import com.fatec.mogi.model.domain.DomainEntity;
import com.sun.istack.NotNull;

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

	@PostMapping
	public ResponseEntity save(@RequestBody T entidade) {
		Filter<T> filter =new Filter<T>(entidade.getClass());
		return saveCommand.execute(filter).buildResponse(); 
	}

	@GetMapping
	public ResponseEntity find( @NotNull T entidade) {
		Filter<T> filter =new Filter<T>(entidade.getClass());
		return findCommand.execute(filter).buildResponse();
	}

	@DeleteMapping
	public ResponseEntity delete(@RequestBody(required = true) T  entidade) {
		Filter<T> filter =new Filter<T>(entidade.getClass());
		return deleteCommand.execute(filter).buildResponse();
	}

	@PutMapping
	public ResponseEntity  update(@RequestBody(required = true) T entidade) {
		Filter<T> filter =new Filter<T>(entidade.getClass());
		return updateCommand.execute(filter).buildResponse();
	}

}
