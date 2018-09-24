package com.rdp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rdp.dao.LeadDao;
import com.rdp.model.Lead;
import com.rdp.req.BaseReq;
import com.rdp.req.LeadReq;
import com.rdp.req.LeadValidReq;
import com.rdp.service.LeadService;
import com.rdp.service.RunningRefNoService;

@Service(value = "leadService")
@Transactional
public class LeadServiceImpl implements LeadService {
	// region -- Fields --

	@Autowired
	private LeadDao leadDao;

	@Autowired
	private RunningRefNoService runningRefNoService;

	// end

	// region -- Methods --

	@Override
	public List<Lead> search(BaseReq req) {
		String keyword = req.getKeyword();

		// Check empty
		if (keyword == null || keyword.isEmpty()) {
			keyword = "%";
		}
		return leadDao.search(keyword);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public String save(LeadReq req) {
		Lead m = new Lead();

		m.setAgreedFactSheet(req.getAgreedFactSheet());
		m.setAgreedTermsConditions(req.getAgreedTermsConditions());
		m.setIsDeleted(req.getIsDeleted());
		m.setCreatedDate(req.getCreatedDate());
		m.setPlanStartDate(req.getPlanStartDate());
		m.setRegistrationDate(req.getRegistrationDate());
		m.setSystemModStamp(req.getSystemModStamp());
		m.setDuration(req.getDuration());
		m.setMasterRecordExternalId(req.getMasterRecordExternalId());
		m.setNightRate(req.getNightRate());
		m.setPercentDiscount(req.getPercentDiscount());
		m.setRate(req.getRate());
		m.setAddress1(req.getAddress1());
		m.setAddress2(req.getAddress2());
		m.setChangeToAmiMeter(req.getChangeToAmiMeter());
		m.setCompany(req.getCompany());
		m.setCurrentMeterType(req.getCurrentMeterType());
		m.setEbsNumber(req.getEbsNumber());
		m.setEmail(req.getEmail());
		m.setFactSheet(req.getFactSheet());
		m.setLastName(req.getLastName());
		m.setLeadSource(req.getLeadSource());
		m.setMasterRecordId(req.getMasterRecordId());
		m.setMeterSelfRead(req.getMeterSelfRead());
		m.setMobilePhone(req.getMobilePhone());
		m.setName(req.getName());
		m.setNricNo(req.getNricNo());
		m.setPhone(req.getPhone());
		m.setPlanName(req.getPlanName());
		m.setPostalCode(req.getPostalCode());
		m.setResidentialType(req.getResidentialType());
		m.setSalesAgreement(req.getSalesAgreement());
		m.setSalesPerson(req.getSalesPerson());
		m.setStatus(req.getStatus());
		m.setStatus(req.getStatus());
		m.setContractEndDateOem(req.getContractEndDateOem());

		String recordTypeId = System.getenv("SF_LEAD_RECORD_TYPE");
		m.setRecordTypeId(recordTypeId);

		// Generate reference number
		String type = req.getType();
		String no = runningRefNoService.createRefNo(type);
		m.setWebReferenceNo(no);

		leadDao.save(m);

		return no;
	}

	@Override
	public boolean checkExist(LeadValidReq req) {
		// Get data
		String ebsNumber = req.getEbsNumber();
		String nricNo = req.getNricNo();

		// Check empty
		if (ebsNumber == null || ebsNumber.isEmpty()) {
			ebsNumber = "";
		}
		if (nricNo == null || nricNo.isEmpty()) {
			nricNo = "";
		}

		// Handle
		List<Lead> tmp = leadDao.checkExist(ebsNumber, nricNo);
		return tmp.size() > 0;
	}

	// end
}