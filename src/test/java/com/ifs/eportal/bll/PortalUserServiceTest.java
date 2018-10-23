package com.ifs.eportal.bll;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ifs.eportal.common.Utils;

/**
 * 
 * @author ToanNguyen 2018-Oct-03
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PortalUserServiceTest {
	// region -- Fields --

	// end

	// region -- Methods --

	@Test
	public void test01() {
		PortalUserService bll = new PortalUserService();
		Object o = bll.getByUserId("hoan.nguyen@tanvieta.co");
		Utils.toString(o);
	}

	// end
}