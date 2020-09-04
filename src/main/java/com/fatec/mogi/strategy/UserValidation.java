package com.fatec.mogi.strategy;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.fatec.mogi.model.domain.DomainEntity;
import com.fatec.mogi.model.domain.User;

public class UserValidation implements IStrategy {


	@Override
	public String process(DomainEntity entity) {

		User user = (User) entity;
		StringBuilder sb = new StringBuilder();

		if (user.getEmail() == null || user.getEmail().isBlank()) {
			sb.append("Email não pode ser vazio").append(",	");
		}

		if (user.getPassword() == null || user.getPassword().isBlank()) {
			sb.append("Senha não pode ser vazia").append(",	");
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

		return sb.toString();

	}

}
