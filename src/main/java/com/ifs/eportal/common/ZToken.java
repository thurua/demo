package com.ifs.eportal.common;

import java.util.Date;

import org.springframework.http.HttpHeaders;

import com.ifs.eportal.dto.PayloadDto;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;

/**
 * 
 * @author ToanNguyen 2018-Oct-17 (verified)
 *
 */
public class ZToken {
	// region -- Fields --

	// end

	// region -- Methods --

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
	 * Check token is expired
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
	 * Get token
	 * 
	 * @param header
	 * @return
	 */
	private static String getToken(HttpHeaders header) {
		String t = header.get(Const.Authentication.HEADER_STRING).get(0);
		String res = t.replace(Const.Authentication.TOKEN_PREFIX, "");
		return res;
	}

	/**
	 * Get token information
	 * 
	 * @param header
	 * @return
	 */
	private static Claims getInfo(HttpHeaders header) {
		String token = getToken(header);
		String jwt = System.getenv("JWT_SIGNING");
		JwtParser t = Jwts.parser().setSigningKey(jwt);
		Claims res = t.parseClaimsJws(token).getBody();
		return res;
	}

	// end
}