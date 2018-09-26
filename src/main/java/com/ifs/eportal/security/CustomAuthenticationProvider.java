package com.ifs.eportal.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

/**
 * 
 * @author ToanNguyen 2018-Sep-26
 *
 */
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
	// region -- Methods --

	public CustomAuthenticationProvider() {
		super();
	}

	@Override
	public Authentication authenticate(final Authentication authentication) throws AuthenticationException {
		final String name = authentication.getName();
		final String password = authentication.getCredentials().toString();

		final List<GrantedAuthority> auths = new ArrayList<>();
		auths.add(new SimpleGrantedAuthority("ROLE_USER"));

		final UserDetails principal = new User(name, password, auths);
		final Authentication res = new UsernamePasswordAuthenticationToken(principal, password, auths);

		return res;
	}

	@Override
	public boolean supports(final Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

	// end
}