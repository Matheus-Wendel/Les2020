package com.fatec.mogi.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.fatec.mogi.model.domain.Client;
import com.fatec.mogi.repository.ClientRepository;
import com.fatec.mogi.repository.UserRepository;
@Service
public class AuthUtils {
private static ClientRepository clientRepository;
private UserRepository userRepository;

	
	@Autowired
	public AuthUtils(ClientRepository clientRepository,UserRepository userRepository ) {
		this.clientRepository=clientRepository;
		this.userRepository=userRepository;
	}
	public static String getUserEmail() {	
		return  SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString(); 
	}
	public static Client getLoggedClient() {
		var email = getUserEmail();
		
		if(email==null) {
			return null;
		}
		return clientRepository.findByUserEmail(email);
		
	}
}
