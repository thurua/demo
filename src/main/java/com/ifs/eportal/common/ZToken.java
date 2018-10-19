package com.ifs.eportal.common;

import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.http.HttpHeaders;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.ifs.eportal.dto.PayloadDto;
import com.ifs.eportal.dto.PortalUserDto;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * 
 * @author ToanNguyen 2018-Oct-17 (verified)
 *
 */
public class ZToken {
	// region -- Fields --

	private static final Logger _log = Logger.getLogger(ZToken.class.getName());

	// end

	// region -- Methods --

	/**
	 * Create JWT
	 * 
	 * @param m   User information
	 * @param sga Authorities
	 * @return
	 */
	public static String create(PortalUserDto m, List<SimpleGrantedAuthority> sga) {
		Claims claim = Jwts.claims().setSubject(m.getUserId());
		claim.put("scopes", sga);
		claim.put(Const.Authentication.PAYLOAD_NAME, PayloadDto.convert(m));

		long t = System.currentTimeMillis();
		Date iat = new Date(t);
		Date exp = new Date(t + ZConfig._jwtTime);
		System.out.println("iat " + iat);
		System.out.println("exp " + exp);

		JwtBuilder jb;
		jb = Jwts.builder().setClaims(claim).setIssuedAt(iat).setExpiration(exp);
		jb = jb.signWith(SignatureAlgorithm.HS256, ZConfig._jwtSigning);

		String res = jb.compact();
		return res;
	}

	/**
	 * Get user information
	 * 
	 * @param header
	 * @return
	 */
	public static PayloadDto getUser(HttpHeaders header) {
		Claims t = getInfo(header);
		Object o = t.get(Const.Authentication.PAYLOAD_NAME);
		PayloadDto res = PayloadDto.convert(o);
		return res;
	}

	/**
	 * Get user information
	 * 
	 * @param claim
	 * @return
	 */
	public static PayloadDto getUser(Claims claim) {
		Object o = claim.get(Const.Authentication.PAYLOAD_NAME);
		PayloadDto res = PayloadDto.convert(o);
		return res;
	}

	/**
	 * Check claim is expired
	 * 
	 * @param header
	 * @return
	 */
	public static boolean isExpired(HttpHeaders header) {
		Claims t = getInfo(header);
		Date expiration = t.getExpiration();
		boolean res = expiration.before(new Date());
		return res;
	}

	/**
	 * Check claim is expired
	 * 
	 * @param claim
	 * @return
	 */
	public static boolean isExpired(Claims claim) {
		Date expiration = claim.getExpiration();
		boolean res = expiration.before(new Date());
		return res;
	}

	/**
	 * Get UserId
	 * 
	 * @param header
	 * @return
	 */
	public static String getUserId(HttpHeaders header) {
		PayloadDto t = getUser(header);
		String res = t.getUserId();
		return res;
	}

	/**
	 * Get SfId
	 * 
	 * @param header
	 * @return
	 */
	public static String getSfId(HttpHeaders header) {
		PayloadDto t = getUser(header);
		String res = t.getSfId();
		return res;
	}

	/**
	 * Get UuId
	 * 
	 * @param header
	 * @return
	 */
	public static String getUuId(HttpHeaders header) {
		PayloadDto t = getUser(header);
		String res = t.getUuId();
		return res;
	}

	/**
	 * Get Id
	 * 
	 * @param header
	 * @return
	 */
	public static Integer getId(HttpHeaders header) {
		PayloadDto t = getUser(header);
		Integer res = t.getId();
		return res;
	}

	/**
	 * Get claim information
	 * 
	 * @param header
	 * @return
	 */
	public static Claims getInfo(HttpHeaders header) {
		String t = header.get(Const.Authentication.HEADER_STRING).get(0);
		Claims res = getInfo(t);
		return res;
	}

	/**
	 * Get claim information
	 * 
	 * @param token
	 * @return
	 */
	public static Claims getInfo(String token) {
		Claims res = Jwts.claims();

		try {
			String jwt = token.replace(Const.Authentication.TOKEN_PREFIX, "");
			if (jwt.isEmpty()) {
				return res;
			}

			String t1 = System.getenv("JWT_SIGNING");
			JwtParser t2 = Jwts.parser().setSigningKey(t1);

			res = t2.parseClaimsJws(jwt).getBody();
		} catch (Exception ex) {
			if (ZConfig._printTrace) {
				ex.printStackTrace();
			}
			if (ZConfig._writeLog) {
				_log.log(Level.SEVERE, ex.getMessage(), ex);
			}
		}

		return res;
	}

	// end
}