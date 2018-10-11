package com.ifs.eportal.common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;

/**
 * 
 * @author ToanNguyen 2018-Oct-10 (verified)
 *
 */
public class ZFile {
	// region -- Fields --

	private static final Logger _log = Logger.getLogger(ZFile.class.getName());

	private static String _name = "";

	private static String _key = "";

	private static String _secret = "";

	private static String _region = "";

	private static String _folder = "";

	private static String _url = "";

	// end

	// region -- Methods --

	/**
	 * Read file
	 * 
	 * @param s Full path file name
	 * @return
	 */
	public static String read(String s) {
		String res = "";

		try {
			FileReader fr = new FileReader(s);
			BufferedReader br = new BufferedReader(fr);

			StringBuilder sb = new StringBuilder();
			String ls = System.getProperty("line.separator");

			String line = null;
			while ((line = br.readLine()) != null) {
				sb.append(line);
				sb.append(ls);
			}

			res = sb.toString();
			br.close();
		} catch (Exception ex) {
			if (Utils.printStackTrace) {
				ex.printStackTrace();
			}
			if (Utils.writeLog) {
				_log.log(Level.SEVERE, ex.getMessage(), ex);
			}
		}

		return res;
	}

	/**
	 * Get real path of Java application at runtime + sub-path
	 * 
	 * @param s Sub-path
	 * @return
	 */
	public static String getPath(String s) {
		return System.getProperty("user.dir") + "/" + s + "/";
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
		} catch (Exception ex) {
			if (Utils.printStackTrace) {
				ex.printStackTrace();
			}
			if (Utils.writeLog) {
				_log.log(Level.SEVERE, ex.getMessage(), ex);
			}
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
			String path = ("public".equals(_folder) ? "public/" : "") + sub + "/" + name;
			File x = new File(pathFile);
			PutObjectRequest y = new PutObjectRequest(_name, path, x);
			y = y.withCannedAcl(CannedAccessControlList.Private);
			d.putObject(y);

			res = _url + "/" + path;
		} catch (Exception ex) {
			if (Utils.printStackTrace) {
				ex.printStackTrace();
			}
			if (Utils.writeLog) {
				_log.log(Level.SEVERE, ex.getMessage(), ex);
			}
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
			String path = ("public".equals(_folder) ? "public/" : "") + sub + "/" + name;
			ObjectMetadata met = new ObjectMetadata();
			PutObjectRequest y = new PutObjectRequest(_name, path, in, met);
			y = y.withCannedAcl(CannedAccessControlList.Private);
			d.putObject(y);

			res = _url + "/" + path;
		} catch (Exception ex) {
			if (Utils.printStackTrace) {
				ex.printStackTrace();
			}
			if (Utils.writeLog) {
				_log.log(Level.SEVERE, ex.getMessage(), ex);
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
		_url = System.getenv("BUCKETEER_BUCKET_URL");
	}

	// end
}