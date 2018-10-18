package com.ifs.eportal.config;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.function.Function;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.ifs.eportal.common.Const;
import com.ifs.eportal.common.ZConfig;
import com.ifs.eportal.dto.PayloadDto;
import com.ifs.eportal.dto.PortalUserDto;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
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

	public String doGenerateToken(PortalUserDto m, List<SimpleGrantedAuthority> authorities) {
		Claims claims = Jwts.claims().setSubject(m.getUserId());
		claims.put("scopes", authorities);
		claims.put(Const.Authentication.PAYLOAD_NAME, PayloadDto.convert(m));

		long t = System.currentTimeMillis();
		Date iat = new Date(t);
		Date exp = new Date(t + ZConfig._jwtTime);
		System.out.println("iat " + iat);
		System.out.println("exp " + exp);

		JwtBuilder jb;
		jb = Jwts.builder().setClaims(claims).setIssuedAt(iat).setExpiration(exp);
		jb = jb.signWith(SignatureAlgorithm.HS256, ZConfig._jwtSigning);

		String res = jb.compact();
		return res;
	}

	public Boolean validateToken(String token, UserDetails userDetails) {
		final String username = getUsernameFromToken(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}

	private Claims getAllClaimsFromToken(String token) {
		return Jwts.parser().setSigningKey(ZConfig._jwtSigning).parseClaimsJws(token).getBody();
	}

	public Boolean isTokenExpired(String token) {
		final Date expiration = getExpirationDateFromToken(token);
		return expiration.before(new Date());
	}

	// end
}