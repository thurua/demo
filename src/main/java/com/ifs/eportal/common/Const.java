package com.ifs.eportal.common;

public class Const {
	public static final String STATUS_ACTIVE = "ACT";

	public static final String STATUS_INACTIVE = "INA";

	public static final String APP_BASE_URL = "APP_BASE_URL";

	public static final String APP_FROM_EMAIL = "APP_FROM_EMAIL";

	public class Authentication {
		public static final long TOKEN_TIME = 24 * 60 * 60;

		public static final String SIGNING_KEY = "auth123key";

		public static final String TOKEN_PREFIX = "Bearer ";

		public static final String HEADER_STRING = "Authorization";

		public static final String ROLE_ADMIN = "Admin";

		public static final String RSA_PRIVATE = "RSA_PRIVATE_KEY";

		public static final String RSA_PUBLIC = "RSA_PUBLIC_KEY";

		public static final String PAYLOAD_NAME = "user";

		public static final String TOKEN_KEY1 = "E3B51E3A5B0035DDEA2D063728B4C79E39E9F259138CB42481AFA41084DB9F0B";

		public static final String TOKEN_KEY2 = "9C8F616E0317998A10F3518D6272EA31B3CB9FECAA4C9FAF1A2FEDB6B6C2AA9E";

		public static final int TOKEN_NUMBER = 6;
	}

	public class HTTP {
		public static final String STATUS_SUCCESS = "success";

		public static final String STATUS_ERROR = "error";
	}

	public class User {
		public static final int FAILED_AUTH_ATTEMPTS = 2;
	}

	public class Mode {
		public static final String DEV = "DEV_MODE";

		public static final String RSA = "RSA_MODE";
	}

	public class Email {
		public static final String TEMPLATE_FORGOT_PASSWORD = "<div style=\"font-family:arial;\">Hi {0},<br/>"
				+ "<p>We recently received a password reset request for your Money Keeper account login.<br/>"
				+ "If you would like to reset your password, please <a href=\"{1}\">click here</a>.</p>"
				+ "<p>If you did not request a password reset, please ignore this email.<br/>"
				+ "Your password won't change until you access the link above and create a new one.</p>"
				+ "<p>With regards,<br><strong>Money Keeper Team</strong></p></div>";

		public static final String TEMPLATE_ACTIVE_CODE = "<div style=\"font-family:arial;\">Hi {0},<br/>"
				+ "<p>We recently received a activation code request for your Money Keeper account login.<br/>"
				+ "If you would like to active your Token application, please use activation code <b>{1}</b>.</p>"
				+ "<p>If you did not request a Token application, please ignore this email.<br/>"
				+ "Your Token application won't change until you access the link above and create a new one.</p>"
				+ "<p>With regards,<br><strong>Money Keeper Team</strong></p></div>";

		public static final String SENDGRID_API_KEY = "SENDGRID_API_KEY";
	}

	public class DateTime {
		public static final String FULL = "yyyy-MM-dd HH:mm:ss";

		public static final String TOKEN = "yyyy-MM-dd HH:mm";

		public static final String DMY_HMS = "dd-MM-yyyy hh:mm:ss";

		public static final String YMD = "yyyy-MM-dd";
	}

	public class SpecialString {
		public static final String Slash = "/";

		public static final String BackSlash = "\\";

		public static final String Space = " ";

		public static final String Semicolon = ";";

		public static final String Comma = ",";

		public static final String Question = "?";

		public static final String Asterisk = "*";

		public static final String Caret = "^";

		public static final String Plus = "+";

		public static final String Blank = "";

		public static final String Minus = "-";

		public static final String Dot = ".";

		public static final String Colon = ":";

		public static final String Quotation = "\"";

		public static final String LeftSquare = "[";

		public static final String RightSquare = "]";

		public static final String Underscore = "_";

		public static final String VBar = "|";

		public static final String Ampersand = "&";

		public static final String Percent = "%";

		public static final String AtSign = "@";
	}

	public class SpecialChar {
		public static final char Slash = '/';

		public static final char BackSlash = '\\';

		public static final char Space = ' ';

		public static final char Semicolon = ';';

		public static final char Comma = ',';

		public static final char Question = '?';

		public static final char Asterisk = '*';

		public static final char Caret = '^';

		public static final char Plus = '+';

		public static final char Minus = '-';

		public static final char Dot = '.';

		public static final char Colon = ':';

		public static final char Quotation = '\'';

		public static final char LeftSquare = '[';

		public static final char RightSquare = ']';

		public static final char Underscore = '_';

		public static final char VBar = '|';

		public static final char Ampersand = '&';

		public static final char Percent = '%';

		public static final char AtSign = '@';
	}
}