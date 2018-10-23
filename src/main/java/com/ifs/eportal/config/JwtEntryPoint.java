package com.ifs.eportal.config;

import java.io.IOException;
import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

/**
 * 
 * @author ToanNguyen 2018-Oct-19 (verified)
 *
 */
@Component
public class JwtEntryPoint implements AuthenticationEntryPoint, Serializable {
	// region -- Fields --

	private static final long serialVersionUID = -6405424739985815617L;

	// end

	// region -- Methods --

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException {
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
	}

	// end
}