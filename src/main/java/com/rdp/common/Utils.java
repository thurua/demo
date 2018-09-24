package com.rdp.common;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

public class Utils {
	// region -- Methods --

	public static List<SimpleGrantedAuthority> getAuthorities(List<String> roles) {
		if (roles != null) {
			return roles.stream().map(r -> new SimpleGrantedAuthority(r)).collect(Collectors.toList());
		}

		return Collections.emptyList();
	}

	// end
}