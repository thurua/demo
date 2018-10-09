package com.ifs.eportal.controller;

import java.io.FileInputStream;

import org.junit.Test;
import org.springframework.mock.web.MockMultipartFile;

import com.ifs.eportal.common.Utils;
import com.ifs.eportal.common.ZFile;
import com.ifs.eportal.dto.ExcelDto;

/**
 * 
 * @author ToanNguyen 2018-Oct-03
 *
 */
public class FileControllerTest {
	// region -- Fields --

	private String _path;

	// end

	// region -- Methods --

	/**
	 * Initialize
	 */
	public FileControllerTest() {
		_path = ZFile.getPath("document");
	}

	@Test
	public void test01() {
		String s = _path + "json\\Factoring-INV-0.3.json";
		ExcelDto o = ExcelDto.read(s);
		Utils.toString(o, true);

		MockMultipartFile js;
		js = new MockMultipartFile("test.json", "", "application/json", "{\"key1\": \"value1\"}".getBytes());
		Utils.toString(js, true);
	}

	@Test
	public void test02() {
		try {
			String s = _path + "excel\\Factoring-INV-0.3.xlsx";
			FileInputStream file = new FileInputStream(s);

			MockMultipartFile x = new MockMultipartFile(s, file);

			s = _path + "json\\file-upload.json";
			String req = ZFile.read(s);

			FileController c = new FileController();
			// ResponseEntity<?> rsp = c.upload(x, req);

			// Object o = rsp.getBody();
			// Utils.toString(o, true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// end
}