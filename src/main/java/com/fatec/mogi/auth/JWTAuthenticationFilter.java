package com.fatec.mogi.auth;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private AuthenticationManager authenticationManager;

	public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}
	
	 @Override
	    public Authentication attemptAuthentication(HttpServletRequest req,
	                                                HttpServletResponse res) throws AuthenticationException {
	        try {
	        	 com.fatec.mogi.model.domain.User creds = new ObjectMapper()
	                    .readValue(req.getInputStream(),  com.fatec.mogi.model.domain.User.class);

	            return authenticationManager.authenticate(
	                    new UsernamePasswordAuthenticationToken(
	                            creds.getEmail(),
	                            creds.getPassword(),
	                            new ArrayList<>())
	            );
	        } catch (IOException e) {
	            throw new RuntimeException(e);
	        }
	    }

	    @Override
	    protected void successfulAuthentication(HttpServletRequest req,
	                                            HttpServletResponse res,
	                                            FilterChain chain,
	                                            Authentication auth) throws IOException, ServletException {

//	        String token = JWT.create()
//	                .withSubject(((org.springframework.security.core.userdetails.User) auth.getPrincipal()).getUsername())
//	                .withExpiresAt(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
//	                .sign(Algorithm.HMAC512(SecurityConstants.SECRET.getBytes()));
//	        res.addHeader(SecurityConstants.HEADER_STRING, SecurityConstants.TOKEN_PREFIX + token);
//	        
//	        PrintWriter writer = res.getWriter();
//	        res.setContentType("application/json");
//	        res.setCharacterEncoding("UTF-8");
//	        
//	        String body ="\""+ SecurityConstants.TOKEN_PREFIX + token+"\"";
//			writer.print(body.toString());
//	        writer.flush();
	    	
	    	
	    	Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
	    	for (GrantedAuthority grantedAuthority : authorities) {
				System.err.println( grantedAuthority.getAuthority());
			}
	    	 String token = JWT.create()
		                .withSubject(((org.springframework.security.core.userdetails.User) auth.getPrincipal()).getUsername())
		                .withExpiresAt(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
		                .sign(Algorithm.HMAC512(SecurityConstants.SECRET.getBytes()));
	//		        res.addHeader(SecurityConstants.HEADER_STRING, SecurityConstants.TOKEN_PREFIX + token);
	//		        res.addHeader(SecurityConstants.PERMISSION_STRING, auth.getAuthorities().iterator().next().toString());
	    	 res.setContentType("application/json");
		        PrintWriter writer = res.getWriter();

		        String body ="{\"Authorization\":\""+ SecurityConstants.TOKEN_PREFIX + token+"\",";
		        body+="\"Permission\":\""+auth.getAuthorities().iterator().next().toString()+"\"}";
				writer.print(body);
		        writer.flush();

	    }
}
