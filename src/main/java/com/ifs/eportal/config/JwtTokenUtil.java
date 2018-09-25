package com.ifs.eportal.config;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.function.Function;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.ifs.eportal.common.Const;
import com.ifs.eportal.dto.PayloadDto;
import com.ifs.eportal.model.PortalUser;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenUtil implements Serializable {
	// region -- Fields --

	private static final long serialVersionUID = -4160292099516778353L;

	// end

	// region -- Methods --

	public String getUsernameFromToken(String token) {
		return getClaimFromToken(token, Claims::getSubject);
	}

	public Date getExpirationDateFromToken(String token) {
		return getClaimFromToken(token, Claims::getExpiration);
	}

	public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}

	public String doGenerateToken(PortalUser m, List<SimpleGrantedAuthority> authorities) {
		Claims claims = Jwts.claims().setSubject(m.getEmail());
		claims.put("scopes", authorities);
		claims.put(Const.Authentication.PAYLOAD_NAME, getPayload(m));

		return Jwts.builder().setClaims(claims).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + Const.Authentication.TOKEN_TIME * 1000))
				.signWith(SignatureAlgorithm.HS256, Const.Authentication.SIGNING_KEY).compact();
	}

	public Boolean validateToken(String token, UserDetails userDetails) {
		final String username = getUsernameFromToken(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}

	private Claims getAllClaimsFromToken(String token) {
		return Jwts.parser().setSigningKey(Const.Authentication.SIGNING_KEY).parseClaimsJws(token).getBody();
	}

	private Boolean isTokenExpired(String token) {
		final Date expiration = getExpirationDateFromToken(token);
		return expiration.before(new Date());
	}

	private PayloadDto getPayload(PortalUser m) {
		PayloadDto res = new PayloadDto();

		res.setId(m.getId());
		res.setUserName(m.getEmail());
		res.setFirstName(m.getFirstName());
		res.setLastName(m.getLastName());

		return res;
	}

	// end
}