package com.ifs.eportal.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import com.ifs.eportal.bll.PortalUserAccessService;
import com.ifs.eportal.common.Const;
import com.ifs.eportal.common.ZConfig;
import com.ifs.eportal.common.ZDate;
import com.ifs.eportal.common.ZLog;
import com.ifs.eportal.common.ZToken;
import com.ifs.eportal.dto.PayloadDto;
import com.ifs.eportal.dto.PortalUserAccessDto;

import io.jsonwebtoken.Claims;

/**
 * 
 * @author ToanNguyen 2018-Oct-19 (verified)
 *
 */
public class JwtFilter extends OncePerRequestFilter {
	// region -- Fields --

	@Autowired
	private PortalUserAccessService portalUserAccessService;

	// end

	// region -- Methods --

	@Override
	protected void doFilterInternal(HttpServletRequest req, HttpServletResponse rsp, FilterChain chn)
			throws IOException, ServletException {
		String token = req.getHeader(Const.Authentication.HEADER_STRING);
		Claims claim = ZToken.getInfo(token);

		if (!claim.isEmpty()) {
			if (!ZToken.isExpired(claim)) {
				PayloadDto payload = ZToken.getUser(claim);

				// Calculate last access
				long now = new Date().getTime() / 1000;
				long span = now - ZConfig._lastAccess;
				ZConfig._lastAccess = now;

				if (span > 120) { // second
					String uuId = payload.getUuId();

					// Check logout
					PortalUserAccessDto m = portalUserAccessService.getBy(uuId);
					boolean ok = m.getId() > 0 && m.getLogoutOn() == null;
					if (!ok) {
						return;
					}
				}

				String userId = payload.getUserId();
				String iat = ZDate.toString(claim.getIssuedAt(), Const.DateTime.FULL);
				String exp = ZDate.toString(claim.getExpiration(), Const.DateTime.FULL);

				List<SimpleGrantedAuthority> sga;
				sga = new ArrayList<SimpleGrantedAuthority>();

				UserDetails userDetails = new User(userId, "", sga);

				UsernamePasswordAuthenticationToken upat;
				upat = new UsernamePasswordAuthenticationToken(userDetails, null, sga);

				upat.setDetails(new WebAuthenticationDetailsSource().buildDetails(req));

				String t = "JWT time [" + iat + " - " + exp + "]";
				ZLog.println(t);
				t = "Authenticated user " + userId + ", setting security context";
				ZLog.println(t);

				SecurityContextHolder.getContext().setAuthentication(upat);
			}
		}

		chn.doFilter(req, rsp);
	}

	// end
}