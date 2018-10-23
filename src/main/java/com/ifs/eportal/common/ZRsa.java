package com.ifs.eportal.common;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.crypto.Cipher;

import org.apache.commons.codec.binary.Base64;

/**
 * 
 * @author ToanNguyen 2018-Oct-16 (verified)
 *
 */
public class ZRsa {
	// region -- Fields --

	private static final Logger _log = Logger.getLogger(ZRsa.class.getName());

	// end

	// region -- Methods --

	/**
	 * Read key from file
	 * 
	 * @param file Path full file name
	 * @return
	 * @throws IOException
	 */
	private static String getKey(String file) throws IOException {
		String res = "";

		BufferedReader br = new BufferedReader(new FileReader(file));
		String line;

		while ((line = br.readLine()) != null) {
			res += line + "\n";
		}
		br.close();

		return res;
	}

	/**
	 * Constructs a private key RSA from the given file
	 * 
	 * @param file PEM private key in file
	 * @return
	 * @throws IOException
	 * @throws GeneralSecurityException
	 */
	public static RSAPrivateKey getPrivateKey(String file) throws IOException, GeneralSecurityException {
		String privateKeyPEM = getKey(file);
		return getPrivateKeyFromString(privateKeyPEM);
	}

	/**
	 * Constructs a private key RSA from the given string
	 * 
	 * @param key PEM private key
	 * @return
	 * @throws IOException
	 * @throws GeneralSecurityException
	 */
	public static RSAPrivateKey getPrivateKeyFromString(String key) throws IOException, GeneralSecurityException {
		String tmp = key;

		// Remove the first and last lines
		tmp = tmp.replace("-----BEGIN RSA PRIVATE KEY-----\n", "");
		tmp = tmp.replace("-----END RSA PRIVATE KEY-----", "");

		// Base64 decode data
		byte[] encoded = Base64.decodeBase64(tmp);

		KeyFactory kf = KeyFactory.getInstance("RSA");
		RSAPrivateKey privKey = (RSAPrivateKey) kf.generatePrivate(new PKCS8EncodedKeySpec(encoded));

		return privKey;
	}

	/**
	 * Constructs a public key RSA from the given file
	 * 
	 * @param file PEM public key in file
	 * @return
	 * @throws IOException
	 * @throws GeneralSecurityException
	 */
	public static RSAPublicKey getPublicKey(String file) throws IOException, GeneralSecurityException {
		String publicKeyPEM = getKey(file);
		return getPublicKeyFromString(publicKeyPEM);
	}

	/**
	 * Constructs a public key RSA from the given string
	 * 
	 * @param key PEM public key
	 * @return
	 * @throws IOException
	 * @throws GeneralSecurityException
	 */
	public static RSAPublicKey getPublicKeyFromString(String key) throws IOException, GeneralSecurityException {
		String tmp = key;

		// Remove the first and last lines
		tmp = tmp.replace("-----BEGIN PUBLIC KEY-----\n", "");
		tmp = tmp.replace("-----END PUBLIC KEY-----", "");

		// Base64 decode data
		byte[] encoded = Base64.decodeBase64(tmp);

		KeyFactory kf = KeyFactory.getInstance("RSA");
		RSAPublicKey pubKey = (RSAPublicKey) kf.generatePublic(new X509EncodedKeySpec(encoded));
		return pubKey;
	}

	/**
	 * Sign message with private key
	 * 
	 * @param k       Private key
	 * @param message
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeyException
	 * @throws SignatureException
	 * @throws UnsupportedEncodingException
	 */
	public static String sign(PrivateKey k, String message)
			throws NoSuchAlgorithmException, InvalidKeyException, SignatureException, UnsupportedEncodingException {
		Signature sign = Signature.getInstance("SHA1withRSA");
		sign.initSign(k);
		sign.update(message.getBytes("UTF-8"));

		return new String(Base64.encodeBase64(sign.sign()), "UTF-8");
	}

	/**
	 * Verify with public key and signature
	 * 
	 * @param k         Public key
	 * @param message
	 * @param signature
	 * @return
	 * @throws SignatureException
	 * @throws NoSuchAlgorithmException
	 * @throws UnsupportedEncodingException
	 * @throws InvalidKeyException
	 */
	public static boolean verify(PublicKey k, String message, String signature)
			throws SignatureException, NoSuchAlgorithmException, UnsupportedEncodingException, InvalidKeyException {
		Signature sign = Signature.getInstance("SHA1withRSA");
		sign.initVerify(k);
		sign.update(message.getBytes("UTF-8"));

		return sign.verify(Base64.decodeBase64(signature.getBytes("UTF-8")));
	}

	/**
	 * Encrypts the text with the public key RSA
	 * 
	 * @param s Plain text to be encrypted
	 * @param k Public key
	 * @return
	 * @throws IOException
	 * @throws GeneralSecurityException
	 */
	public static String encrypt(String s, PublicKey k) throws IOException, GeneralSecurityException {
		Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
		cipher.init(Cipher.ENCRYPT_MODE, k);

		return Base64.encodeBase64String(cipher.doFinal(s.getBytes("UTF-8")));
	}

	/**
	 * Decrypts the text with the private key RSA
	 * 
	 * @param s Cypher text to be decrypted
	 * @param k Private key
	 * @return Decrypted text base64 encoded
	 * @throws IOException
	 * @throws GeneralSecurityException
	 */
	public static String decrypt(String s, PrivateKey k) throws IOException, GeneralSecurityException {
		Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
		cipher.init(Cipher.DECRYPT_MODE, k);

		return new String(cipher.doFinal(Base64.decodeBase64(s)), "UTF-8");
	}

	/**
	 * Encrypts the text with the public key RSA
	 * 
	 * @param s Plain text to be encrypted
	 * @return
	 */
	public static String encrypt(String s) {
		String res = s;

		try {
			String mod = System.getenv(Const.Mode.RSA);
			if (mod != null && "Y".equals(mod)) {
				String key = System.getenv(Const.Authentication.RSA_PUBLIC);
				PublicKey k = getPublicKeyFromString(key);
				res = encrypt(s, k);
			}
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

	/**
	 * Decrypts the text with the private key RSA
	 * 
	 * @param s Cypher text to be decrypted
	 * @return
	 */
	public static String decrypt(String s) {
		String res = s;

		try {
			String mod = System.getenv(Const.Mode.RSA);
			if (mod != null && "Y".equals(mod)) {
				String key = System.getenv(Const.Authentication.RSA_PRIVATE);
				PrivateKey k = getPrivateKeyFromString(key);
				res = decrypt(s, k);
			}
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

	/**
	 * Generate key pair
	 * 
	 * @throws NoSuchAlgorithmException
	 */
	public static void generate() throws NoSuchAlgorithmException {
		KeyPairGenerator key = KeyPairGenerator.getInstance("RSA");
		key.initialize(1024);

		KeyPair pair = key.generateKeyPair();
		PrivateKey privateKey = pair.getPrivate();
		PublicKey publicKey = pair.getPublic();

		String priKey = java.util.Base64.getEncoder().encodeToString(privateKey.getEncoded());
		String pubKey = java.util.Base64.getEncoder().encodeToString(publicKey.getEncoded());

		System.out.println("Private Key: " + priKey);
		System.out.println("Public Key: " + pubKey);
	}

	// end
}