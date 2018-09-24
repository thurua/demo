package com.rdp.common;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;

@Component
public class Genarator {
	// region -- Fields --

	@Autowired
	private TemplateEngine templateEngine;

	private static String _name = "";

	private static String _key = "";

	private static String _secret = "";

	private static String _region = "";

	private static String _folder = "";

	// end

	// region -- Methods --

	/**
	 * Create PDF from HTML file
	 * 
	 * @param name Template file name
	 * @param sub  Sub-folder
	 * @param map  Data mapping
	 * @return URL of PDF file on S3
	 * @throws Exception
	 */
	public String createPdf(String name, String sub, Map<?, ?> map) throws Exception {
		String res = "";
		Context ctx = new Context();
		FileOutputStream os = null;

		if (map != null) {
			Iterator<?> itMap = map.entrySet().iterator();
			while (itMap.hasNext()) {
				Map.Entry<?, ?> pair = (Map.Entry<?, ?>) itMap.next();
				ctx.setVariable(pair.getKey().toString(), pair.getValue());
			}
		}

		String html = templateEngine.process(name, ctx);
		String path = "tmp\\";
		name = UUID.randomUUID().toString() + ".pdf";

		try {
			// Check folder
			path = path + "pdf\\";
			File file = new File(path);
			if (!file.exists()) {
				file.mkdirs();
			}

			String pathFile = path + name;
			file = new File(pathFile);
			os = new FileOutputStream(file);

			ITextRenderer renderer = new ITextRenderer();
			renderer.setDocumentFromString(html);
			renderer.layout();
			renderer.createPDF(os, true);

			res = upload(pathFile, name, sub);
		} finally {
			if (os != null) {
				try {
					os.close();
				} catch (IOException e) {
					System.out.println(e.getMessage());
				}
			}
		}

		System.out.println(res);
		return res;
	}

	/**
	 * Download file to S3
	 * 
	 * @param pathFile Full path file name
	 * @return
	 */
	public S3Object download(String pathFile) {
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
	private String upload(String pathFile, String name, String sub) {
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
	 * Get environment variable
	 */
	private void setup() {
		_name = System.getenv("BUCKETEER_BUCKET_NAME");
		_key = System.getenv("BUCKETEER_AWS_ACCESS_KEY_ID");
		_secret = System.getenv("BUCKETEER_AWS_SECRET_ACCESS_KEY");
		_region = System.getenv("BUCKETEER_AWS_REGION");
		_folder = System.getenv("BUCKETEER_BUCKET_FOLDER");
	}

	// end
}