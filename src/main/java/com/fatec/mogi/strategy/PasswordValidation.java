package com.fatec.mogi.strategy;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.fatec.mogi.model.domain.DomainEntity;
import com.fatec.mogi.model.domain.User;

public class PasswordValidation implements IStrategy{

	@Override
	public String process(DomainEntity entity) {
		StringBuilder sb = new StringBuilder();
		try {
			User user = (User) entity;
			
			  Pattern pattern = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$");
			  
		      Matcher matcher = pattern.matcher(user.getPassword());
		 
		      if (matcher.matches()) {
		    	  BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		    	  user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		    	  return sb.toString();
		      } else {
		    	  sb.append("Senha invalida");
		      }

		} catch (Exception e) {
			sb.append(e.getMessage());

		}
		return sb.toString();
	}

}
