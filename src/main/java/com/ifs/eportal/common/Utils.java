package com.ifs.eportal.common;

import java.io.File;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.TimeZone;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import org.springframework.http.HttpHeaders;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.ifs.eportal.dto.AccountDto;
import com.ifs.eportal.dto.ClientAccountCustomerDto;
import com.ifs.eportal.dto.LineItemDto;
import com.ifs.eportal.dto.PayloadDto;
import com.ifs.eportal.rsp.SingleRsp;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;

public class Utils {
	// region -- Fields --

	public static boolean printStackTrace = false;

	public static boolean writeLog = false;

	private static String _name = "";

	private static String _key = "";

	private static String _secret = "";

	private static String _region = "";

	private static String _folder = "";

	private static String _uploadHash = "";

	private static String _uploadKey = "";

	// end

	// region -- Methods --

	/**
	 * Date format
	 * 
	 * @param d String date and time
	 * @return
	 */
	public static Date dateFormat(String d) {
		Date res = null;
		SimpleDateFormat f = new SimpleDateFormat(Const.DateTime.FULL);

		try {
			res = f.parse(d);

		} catch (ParseException e) {
			e.printStackTrace();
		}

		return res;
	}

	/**
	 * Date format
	 * 
	 * @param d      Date and time
	 * @param format Format date e.g: dd/MM/yyyy
	 * @return
	 */
	public static String dateFormat(Date d, String format) {
		String res = "";

		try {
			SimpleDateFormat sm = new SimpleDateFormat(format);
			res = sm.format(d);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return res;
	}

	/**
	 * Get authorities
	 * 
	 * @param roles
	 * @return
	 */
	public static List<SimpleGrantedAuthority> getAuthorities(List<String> roles) {
		if (roles != null) {
			return roles.stream().map(r -> new SimpleGrantedAuthority(r)).collect(Collectors.toList());
		}

		return Collections.emptyList();
	}

	/**
	 * Get token information
	 * 
	 * @param header
	 * @return
	 */
	public static PayloadDto getTokenInfor(HttpHeaders header) {
		String token = header.get(Const.Authentication.HEADER_STRING).get(0);
		token = token.replace(Const.Authentication.TOKEN_PREFIX, "");

		JwtParser x = Jwts.parser().setSigningKey(Const.Authentication.SIGNING_KEY);
		Claims y = x.parseClaimsJws(token).getBody();
		Object z = y.get(Const.Authentication.PAYLOAD_NAME);

		PayloadDto res = PayloadDto.convert(z);
		return res;
	}

	/**
	 * Get UTC date time
	 * 
	 * @param type Choose attribute to add (Calendar.MINUTE, Calendar.HOUR, ...)
	 * @param n    Number want to add
	 * @return
	 */
	public static Date getTime(int type, int n) {
		Date res = null;

		try {
			TimeZone t = TimeZone.getTimeZone("UTC");
			Calendar c = Calendar.getInstance(t);
			c.add(type, n);
			res = c.getTime();
		} catch (Exception ex) {
			if (Utils.printStackTrace) {
				ex.printStackTrace();
			}
			if (Utils.writeLog) {
				System.out.println(ex.getMessage());
			}
		}

		return res;
	}

	/**
	 * Verify with current date
	 * 
	 * @param d Date
	 * @return
	 */
	public static boolean verify(Date d) {
		TimeZone t = TimeZone.getTimeZone("UTC");
		Calendar c = Calendar.getInstance(t);

		if (d.compareTo(c.getTime()) < 0) {
			return false;
		}

		return true;
	}

	/**
	 * Get token
	 * 
	 * @param l length
	 * @return
	 */
	public static String getToken(int l) {
		String chars = "0123456789";
		Random random = new Random();
		StringBuilder token = new StringBuilder(l);

		for (int i = 0; i < l; i++) {
			token.append(chars.charAt(random.nextInt(chars.length())));
		}

		return token.toString();
	}

	/**
	 * Get token with 6 digits
	 * 
	 * @return
	 */
	public static String getToken() {
		int n = Const.Authentication.TOKEN_NUMBER;
		int max = (int) Math.pow(10, n) - 1;

		Random t = new Random();
		int s = t.nextInt(max);

		char[] zeros = new char[n];
		Arrays.fill(zeros, '0');
		String format = String.valueOf(zeros);
		DecimalFormat df = new DecimalFormat(format);
		String res = df.format(s);

		return res;
	}

	/**
	 * Get token
	 * 
	 * @param s   String data
	 * @param num Number of digits will get
	 * @return
	 */
	public static String getToken(String s, int num) {
		String res = "";

		s = Const.Authentication.TOKEN_KEY1 + s + Const.Authentication.TOKEN_KEY2;
		String hash = generateSHA256(s);
		String[] arr = hash.split(Const.SpecialString.Minus);

		if (num > 8 || num < 1) {
			num = 8;
		}

		for (String i : arr) {
			if (num == 0) {
				break;
			}

			eachHash: for (char item : i.toCharArray()) {
				if (Character.isDigit(item)) {
					res += item;
					break eachHash;
				}
			}

			num--;
		}

		return res;
	}

	/**
	 * Download file to S3
	 * 
	 * @param pathFile Full path file name
	 * @return
	 */
	public static S3Object download(String pathFile) {
		S3Object res = null;
		System.out.println("Preparing download...");

		try {
			setup();

			// Authentication
			BasicAWSCredentials a = new BasicAWSCredentials(_key, _secret);
			AWSStaticCredentialsProvider b = new AWSStaticCredentialsProvider(a);
			AmazonS3ClientBuilder c = AmazonS3ClientBuilder.standard().withCredentials(b);
			AmazonS3 d = c.withRegion(_region).build();

			// Downloading
			GetObjectRequest rangeObjectRequest = new GetObjectRequest(_name, pathFile);
			res = d.getObject(rangeObjectRequest);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return res;
	}

	/**
	 * Upload file to S3
	 * 
	 * @param pathFile Full path file name
	 * @param name     File name with extension
	 * @param sub      Sub-folder
	 * @return
	 */
	public static String upload(String pathFile, String name, String sub) {
		String res = "";
		System.out.println("Preparing upload...");

		try {
			setup();

			// Authentication
			BasicAWSCredentials a = new BasicAWSCredentials(_key, _secret);
			AWSStaticCredentialsProvider b = new AWSStaticCredentialsProvider(a);
			AmazonS3ClientBuilder c = AmazonS3ClientBuilder.standard().withCredentials(b);
			AmazonS3 d = c.withRegion(_region).build();

			// Uploading
			res = ("public".equals(_folder) ? "public/" : "") + sub + "/" + name;
			File x = new File(pathFile);
			PutObjectRequest y = new PutObjectRequest(_name, res, x);
			y = y.withCannedAcl(CannedAccessControlList.Private);
			d.putObject(y);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			res = "";
		}

		return res;
	}

	/**
	 * 
	 * @param in   File input stream
	 * @param name File name with extension
	 * @param sub  Sub-folder
	 * @return
	 */
	public static String upload(InputStream in, String name, String sub) {
		String res = "";
		System.out.println("Preparing upload...");

		try {
			setup();

			// Authentication
			BasicAWSCredentials a = new BasicAWSCredentials(_key, _secret);
			AWSStaticCredentialsProvider b = new AWSStaticCredentialsProvider(a);
			AmazonS3ClientBuilder c = AmazonS3ClientBuilder.standard().withCredentials(b);
			AmazonS3 d = c.withRegion(_region).build();

			// Uploading
			res = ("public".equals(_folder) ? "public/" : "") + sub + "/" + name;

			ObjectMetadata met = new ObjectMetadata();
			PutObjectRequest y = new PutObjectRequest(_name, res, in, met);
			y = y.withCannedAcl(CannedAccessControlList.Private);
			d.putObject(y);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			res = "";
		}

		return res;
	}

	/**
	 * Check authentication
	 * 
	 * @param auth Authentication key
	 * @return
	 */
	public static boolean checkAuth(String auth) {
		try {
			if (auth == null || auth.isEmpty()) {
				return false;
			}

			getEnv();

			String[] arr = auth.split(":");

			if (!arr[0].equals(_uploadKey)) {
				return false;
			}

			if (System.currentTimeMillis() - Long.parseLong(arr[1]) > 600000) {
				return false;
			}

			String t = hash(arr[0] + ":" + arr[1], _uploadHash);
			if (t.equals(arr[2])) {
				return true;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return false;
	}

	/**
	 * Get authentication
	 * 
	 * @return
	 */
	public static String getAuth() {
		getEnv();

		String s = _uploadKey + ':' + System.currentTimeMillis();
		String t = hash(s, _uploadHash);

		String res = s + ':' + t;
		return res;
	}

	/**
	 * Hash
	 * 
	 * @param value
	 * @param key
	 * @return
	 */
	public static String hash(String value, String key) {
		String res = "";

		try {
			String s = "HmacSHA512";
			byte[] b = key.getBytes("UTF-8");
			SecretKeySpec k = new SecretKeySpec(b, s);

			Mac mac = Mac.getInstance(s);
			mac.init(k);

			b = value.getBytes("UTF-8");
			b = mac.doFinal(b);

			res = DatatypeConverter.printHexBinary(b);
			res = res.toUpperCase();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return res;
	}

	/**
	 * Add error
	 * 
	 * @param rsp
	 * @param err
	 * @return
	 */
	public static SingleRsp addError(SingleRsp rsp, String err) {
		String s = rsp.getMessage() + "\n" + err;
		rsp.setMessage(s);
		return rsp;
	}

	/**
	 * get all number
	 * 
	 * @param l
	 * @return
	 */
	public static String getAllNo(List<LineItemDto> l) {
		String res = "";

		// Filter
		Stream<String> t1;
		t1 = l.stream().map(r -> r.getNo()).filter(r -> !r.isEmpty());
		List<String> t2 = t1.collect(Collectors.toList());
		for (String i : t2) {
			res += i.trim() + ", ";
		}

		// Remove ", "
		int t3 = res.length();
		if (t3 > 1) {
			res = res.substring(0, t3 - 2);
		}

		return res;
	}

	/**
	 * Get all fields
	 * 
	 * @param fields
	 * @param type
	 * @return
	 */
	public static List<Field> getAllFields(List<Field> fields, Class<?> type) {
		fields.addAll(Arrays.asList(type.getDeclaredFields()));

		if (type.getSuperclass() != null) {
			getAllFields(fields, type.getSuperclass());
		}

		return fields;
	}

	/**
	 * Get all data in object to string
	 * 
	 * @param o Object data
	 * @return
	 */
	public static String toString(Object o) {
		return toString(o, false);
	}

	/**
	 * Get all data in object to string
	 * 
	 * @param o     Object data
	 * @param print Allow to print out screen
	 * @return
	 */
	public static String toString(Object o, boolean print) {
		String res = "";

		StringBuilder sb = new StringBuilder();
		String line = System.getProperty("line.separator");

		sb.append(o.getClass().getName());
		sb.append(" {");
		sb.append(line);

		List<Field> fields = new ArrayList<Field>();
		fields = Utils.getAllFields(fields, o.getClass());

		// Print field names paired with their values
		for (Field field : fields) {
			sb.append("  ");

			try {
				String t1 = field.getName();
				sb.append(t1);
				sb.append(": ");

				// Make get method name
				t1 = "get" + t1.substring(0, 1).toUpperCase() + t1.substring(1);
				Method method = o.getClass().getMethod(t1);
				Object t2 = method.invoke(o);
				t1 = t2 != null ? t2.toString() : "";

				sb.append(t1);
			} catch (Exception e) {
				e.printStackTrace();
			}

			sb.append(line);
		}

		sb.append("}");
		res = sb.toString();

		if (print) {
			System.out.println(res);
		}

		return res;
	}

	/**
	 * Generate SHA-256
	 * 
	 * @param s String data
	 * @return
	 */
	private static String generateSHA256(String s) {
		String res = "";

		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] encodedhash = digest.digest(s.getBytes(StandardCharsets.UTF_8));
			StringBuffer hexString = new StringBuffer();

			for (int i = 0; i < encodedhash.length; i++) {
				String hex = Integer.toHexString(0xff & encodedhash[i]);
				if (hex.length() == 1) {
					hexString.append('0');
				}
				hexString.append(hex);
				if (i % 4 == 3) {
					hexString.append(Const.SpecialChar.Minus);
				}
			}

			res = hexString.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		return res;
	}

	/**
	 * Use for supplier
	 * 
	 * @param l
	 * @param name
	 * @return
	 */
	public static String getAccIdByName(List<AccountDto> l, String name) {
		String res = "";

		if (l == null || l.size() == 0) {
			return res;
		}

		name = name.trim();
		for (AccountDto i : l) {
			if (i.getName().equals(name)) {
				res = i.getSfId();
				break;
			}
		}

		return res;
	}

	/**
	 * Use for customer
	 * 
	 * @param l
	 * @param name
	 * @return
	 */
	public static String getAccCusIdByName(List<ClientAccountCustomerDto> l, String name) {
		String res = "";

		if (l == null || l.size() == 0) {
			return res;
		}

		name = name.trim();
		for (ClientAccountCustomerDto i : l) {
			if (i.getCcName().equals(name)) {
				res = i.getCustomer();
				break;
			}
		}

		return res;
	}

	public static List<String> getNames(List<LineItemDto> l) {
		List<String> res = new ArrayList<>();

		for (LineItemDto i : l) {
			if (i.getName() != null && !i.getName().isEmpty()) {
				res.add(i.getName().trim());
			}
		}

		return res;
	}

	/**
	 * Get environment variable
	 */
	private static void setup() {
		_name = System.getenv("BUCKETEER_BUCKET_NAME");
		_key = System.getenv("BUCKETEER_AWS_ACCESS_KEY_ID");
		_secret = System.getenv("BUCKETEER_AWS_SECRET_ACCESS_KEY");
		_region = System.getenv("BUCKETEER_AWS_REGION");
		_folder = System.getenv("BUCKETEER_BUCKET_FOLDER");
	}

	/**
	 * Get environment variable
	 */
	private static void getEnv() {
		_uploadHash = System.getenv("UPLOAD_HASH");
		_uploadKey = System.getenv("UPLOAD_KEY");
	}

	// end
}