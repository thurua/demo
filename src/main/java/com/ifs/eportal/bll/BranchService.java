package com.ifs.eportal.bll;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ifs.eportal.common.Utils;
import com.ifs.eportal.dal.BranchDao;
import com.ifs.eportal.dto.BranchDto;
import com.ifs.eportal.dto.LineItemDto;

/**
 * 
 * @author ToanNguyen 2018-Oct-04 (verified)
 *
 */
@Service(value = "branchService")
@Transactional
public class BranchService {
	// region -- Fields --

	@Autowired
	private BranchDao branchDao;

	// end

	// region -- Methods --

	/**
	 * Get list active Branch by
	 * 
	 * @param l
	 * @return
	 */
	public List<HashMap<String, String>> getBy(List<LineItemDto> l) {
		List<HashMap<String, String>> res = new ArrayList<>();

		List<String> names = Utils.getNames(l);
		List<BranchDto> lbr = branchDao.getBy(names);

		HashMap<String, String> l1 = new HashMap<String, String>();
		HashMap<String, String> l2 = new HashMap<String, String>();
		for (BranchDto i : lbr) {
			String t = l1.get(i.getName());
			if (t == null) {
				String t1 = i.getCode() + ",";
				l1.put(i.getName(), t1);
			} else {
				t += i.getCode() + ",";
				l1.put(i.getName(), t);
			}

			t = l2.get(i.getName());
			if (t == null) {
				String t1 = i.getSfId() + "--.--" + i.getCode() + ",";
				l2.put(i.getName(), t1);
			} else {
				t += i.getSfId() + "--.--" + i.getCode() + ",";
				l2.put(i.getName(), t);
			}
		}
		res.add(l1);
		res.add(l2);

		return res;
	}

	// end
}