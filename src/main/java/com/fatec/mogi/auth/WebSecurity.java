package com.fatec.mogi.auth;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter{
	 private UserDetailsServiceImpl userDetailsService;
	    private BCryptPasswordEncoder bCryptPasswordEncoder;

	   
	    public WebSecurity(UserDetailsServiceImpl userDetailsService) {
	        this.userDetailsService = userDetailsService;
	        this.bCryptPasswordEncoder = new BCryptPasswordEncoder();
	    }

	    @Override
	    protected void configure(HttpSecurity http) throws Exception {
	        http.cors().and().csrf().disable().authorizeRequests()
	                .antMatchers(HttpMethod.POST, "/client").permitAll()
	                .antMatchers(HttpMethod.GET, "/country").permitAll()
	                .antMatchers(HttpMethod.GET, "/city").permitAll()
	                .antMatchers(HttpMethod.GET, "/state").permitAll()
	                .antMatchers("/h2-console").permitAll()
	                .antMatchers("/h2-console/**").permitAll()
	                .anyRequest().authenticated()
	                .and()
	                .addFilter(new JWTAuthenticationFilter(authenticationManager()))
	                .addFilter(new JWTAuthorizationFilter(authenticationManager()))
	                // this disables session creation on Spring Security
	                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	        http.headers().frameOptions().disable();
	    }

	    @Override
	    public void configure(AuthenticationManagerBuilder auth) throws Exception {
	        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
	    }

	  @Bean
	  CorsConfigurationSource corsConfigurationSource() {
	    final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	    source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
	    return source;
	  }
}
