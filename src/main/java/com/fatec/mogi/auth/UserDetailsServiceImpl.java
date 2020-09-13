package com.fatec.mogi.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.fatec.mogi.model.domain.Client;
import com.fatec.mogi.repository.ClientRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private ClientRepository clientRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Client client = clientRepository.findByUserEmail(email);
		if(client==null) {
			throw new UsernameNotFoundException(email);			
		}
		com.fatec.mogi.model.domain.User user = client.getUser();
		if (user == null) {
			throw new UsernameNotFoundException(email);
		}
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
				getAuthorities(user));
	}

	private static Collection<? extends GrantedAuthority> getAuthorities(com.fatec.mogi.model.domain.User user) {

		// CRIA UMA LISTA DE PERMISSOES
		// A QUAL SERA A ATRIBUIDA A ELA AS PERMICOES QUE OS NOMES ESTAO SALVOS NO
		// PROPRIO USUARIO( usuario.getPermissao() )
		Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority(user.getPermission().toString()));

		return authorities;
	}

}
