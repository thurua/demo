package com.rdp.common;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.rdp.dto.ReCaptchaDto;

@PropertySource("classpath:application.properties")
@Service
public class CaptchaService {
	private final RestTemplate restTemplate;

	public CaptchaService(RestTemplateBuilder restTemplateBuilder) {
		this.restTemplate = restTemplateBuilder.build();
	}

	public boolean verify(String response) {
		String recaptchaSecret = System.getenv("CAPTCHA_SECRET_KEY");
		String recaptchaVerifyUrl = "https://www.google.com/recaptcha/api/siteverify";

		MultiValueMap<String, String> param = new LinkedMultiValueMap<>();
		param.add("secret", recaptchaSecret);
		param.add("response", response);

		ReCaptchaDto res = null;

		try {
			res = this.restTemplate.postForObject(recaptchaVerifyUrl, param, ReCaptchaDto.class);
		} catch (RestClientException e) {
			System.out.print(e.getMessage());
		}

		if (res.isSuccess()) {
			return true;
		} else {
			return false;
		}
	}
}