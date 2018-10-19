package com.ifs.eportal.controller;

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

	// end
}