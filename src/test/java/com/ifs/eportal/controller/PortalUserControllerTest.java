package com.ifs.eportal.controller;

import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.ifs.eportal.common.ZFile;

/**
 * 
 * @author ToanNguyen 2018-Oct-03
 *
 */
public class PortalUserControllerTest {
	// region -- Fields --

	private String _path;

	private String _url;

	// end

	// region -- Methods --

	/**
	 * Initialize
	 */
	public PortalUserControllerTest() {
		_path = ZFile.getPath("document\\json");
		_url = "https://ifscapital-dev.herokuapp.com/portal-user/";
	}

	@Test
	public void test01() {
		try {
			String s = _path + "portal-user-sign-in.json";
			s = ZFile.read(s);

			RestTemplate rest = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<String> entity = new HttpEntity<String>(s, headers);

			s = _url + "sign-in";
			ResponseEntity<String> rsp;
			rsp = rest.exchange(s, HttpMethod.POST, entity, String.class);

			System.out.println(rsp.getBody());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// end
}