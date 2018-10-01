package com.ifs.eportal.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.ifs.eportal.dal.PortalUserDao;
import com.ifs.eportal.dto.PortalUserDto;

/**
 * 
 * @author ToanNguyen 2018-Sep-26
 *
 */
@Component
public class CustomAuthenticationProvider {
	// region -- Fields --

	@Autowired
	private PortalUserDao portalUserDao;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	// end

	// region -- Methods --

	public CustomAuthenticationProvider() {
		super();
	}

	public Authentication authenticate(final Authentication authentication) throws AuthenticationException {
		final String name = authentication.getName();
		final String password = authentication.getCredentials().toString();

		final List<GrantedAuthority> auths = new ArrayList<>();
		auths.add(new SimpleGrantedAuthority("ROLE_USER"));

		PortalUserDto o = portalUserDao.getBy(name);
		if (o.getId() == 0) {
			return null;
		}

		boolean ok = bCryptPasswordEncoder.matches(password, o.getPassword());
		if (!ok) {
			return null;
		}

		final UserDetails principal = new User(name, password, auths);
		final Authentication res = new UsernamePasswordAuthenticationToken(principal, password, auths);

		return res;
	}

	public boolean supports(final Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

	// end
}