package com.ifs.eportal.controller;

import java.io.FileInputStream;

import org.junit.Test;

import com.ifs.eportal.common.Utils;
import com.ifs.eportal.dto.ExcelDto;

/**
 * 
 * @author ToanNguyen 2018-Oct-03
 *
 */
public class FileControllerTest {
	private String _path;

	public FileControllerTest() {
		_path = "D:\\CTY\\Working\\SyncIFS\\IFS-CWS\\document\\";
	}

	@Test
	public void contextLoads() {
		String s = _path + "json\\Factoring-INV-0.3.json";
		ExcelDto o = ExcelDto.read(s);
	}

	@Test
	public void test01() {

		try {
			String s = "D:\\CTY\\Working\\IFS\\SOUpload\\Factoring-INV-0.3.xlsx";
			FileInputStream file = new FileInputStream(s);

			s = _path + "json\\file-upload.json";
			String req = Utils.readFile(s);

			FileController c = new FileController();
			// c.upload(file, req);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}