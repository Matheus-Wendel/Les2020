package com.fatec.mogi.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fatec.mogi.model.domain.State;
@RestController
@RequestMapping("/state")
@CrossOrigin(origins = "*", maxAge = 3600L)
public class StateController extends AbstractController<State> {

	public StateController() {
		super(State.class);
	}


}
