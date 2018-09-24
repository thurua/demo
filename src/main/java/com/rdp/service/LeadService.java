package com.rdp.service;

import java.util.List;

import com.rdp.model.Lead;
import com.rdp.req.BaseReq;
import com.rdp.req.LeadReq;
import com.rdp.req.LeadValidReq;

public interface LeadService {
	List<Lead> search(BaseReq req);

	String save(LeadReq req);

	boolean checkExist(LeadValidReq req);
}