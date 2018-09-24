package com.ifs.eportal.common;

import java.io.IOException;
import java.text.MessageFormat;

import org.springframework.util.StringUtils;

import com.sendgrid.Content;
import com.sendgrid.Email;
import com.sendgrid.Mail;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;

/**
 * Email service
 * 
 * @author T
 *
 */
public class EmailService {
	// region -- Methods --

	/**
	 * Send email
	 * 
	 * @param to  To email
	 * @param sub Email's subject
	 * @param msg Email's content
	 */
	private static void sendMail(String to, String sub, String msg) {
		try {
			String from = System.getenv(Const.APP_FROM_EMAIL);

			Email eFrom = new Email(from);
			Email eTo = new Email(to);
			Content content = new Content("text/html", msg);
			Mail mail = new Mail(eFrom, sub, eTo, content);

			String skey = System.getenv(Const.Email.SENDGRID_API_KEY);
			if (skey == null || skey == "") {
				return;
			}

			Request req = new Request();
			req.setMethod(Method.POST);
			req.setEndpoint("mail/send");
			req.setBody(mail.build());

			SendGrid sg = new SendGrid(skey);
			Response rsp = sg.api(req);

			System.out.println(rsp.getStatusCode());
			System.out.println(rsp.getBody());
			System.out.println(rsp.getHeaders());
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Send email with forgot password template
	 * 
	 * @param to   To email
	 * @param code User's token
	 * @param name First name
	 */
	public static void forgotPassword(String to, String code, String name) {
		try {
			String url = System.getenv(Const.APP_BASE_URL);
			String template = Const.Email.TEMPLATE_FORGOT_PASSWORD;

			StringBuilder t = new StringBuilder(url);
			t.append("/#/forgot-password?token=");
			t.append(code);

			if (StringUtils.hasText(to)) {
				String subject = "Reset your Money Keeper password";
				String content = MessageFormat.format(template, name, t);
				sendMail(to, subject, content);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Send email with active code template
	 * 
	 * @param to   To email
	 * @param name First name
	 * @param code Active code
	 */
	public static void getActiveCode(String to, String name, String code) {
		try {
			String template = Const.Email.TEMPLATE_ACTIVE_CODE;

			if (StringUtils.hasText(to)) {
				String subject = "Get your Money Keeper active code";
				String content = MessageFormat.format(template, name, code);
				sendMail(to, subject, content);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// end
}