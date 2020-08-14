package com.fatec.mogi.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fatec.mogi.model.domain.User;
@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*", maxAge = 3600L)
public class UserController extends AbstractController<User> {

	public UserController() {
		super(User.class);
	}

}
