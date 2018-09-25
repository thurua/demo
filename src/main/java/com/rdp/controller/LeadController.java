package com.rdp.controller;

import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.amazonaws.services.s3.model.S3Object;
import com.rdp.common.CaptchaService;
import com.rdp.common.Genarator;
import com.rdp.model.Lead;
import com.rdp.model.Package;
import com.rdp.req.BaseReq;
import com.rdp.req.LeadReq;
import com.rdp.req.LeadValidReq;
import com.rdp.req.SglocateReq;
import com.rdp.rsp.MultipleRsp;
import com.rdp.rsp.SingleRsp;
import com.rdp.service.LeadService;
import com.rdp.service.PackageService;

@RestController
@RequestMapping("/lead")
public class LeadController {
	// region -- Fields --

	@Autowired
	private LeadService leadService;

	@Autowired
	private CaptchaService captchaService;

	@Autowired
	private PackageService packageService;

	@Autowired
	Genarator pdfGenarator;

	// end

	// region -- Methods --

	/**
	 * Search by company, status and user name
	 * 
	 * @param header
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public ResponseEntity<?> search(@RequestHeader HttpHeaders header, @RequestBody BaseReq req) {
		MultipleRsp res = new MultipleRsp();

		try {
			// Handle
			List<Lead> dtos = leadService.search(req);

			// Set data
			Map<String, Object> data = new LinkedHashMap<>();
			data.put("count", dtos.size());
			data.put("data", dtos);
			res.setResult(data);
		} catch (Exception ex) {
			res.setCallstatus("error");
			res.setMessage(ex.getMessage());
		}

		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ResponseEntity<?> save(@RequestHeader HttpHeaders header, @RequestBody LeadReq req) {
		MultipleRsp res = new MultipleRsp();

		try {
			//-Start of Pen Test validations
			String inerrmsg = "Failed to create Lead!";
			boolean isNumeric = req.getEbsNumber().chars().allMatch( Character::isDigit );
			
			Pattern pattern = Pattern.compile("^.+@.+\\..+$");
			Matcher emailmatcher = pattern.matcher(req.getEmail());
			
			
			pattern = Pattern.compile("^[6][0-4]\\d{4}$");
			Matcher postalcodematcher = pattern.matcher(req.getPostalCode());
			
			
			/*System.out.println("isNumeric: " + !isNumeric
							+ ",req.getEbsNumber().length() : " + req.getEbsNumber().length() != 10 
							+ ",emailmatcher.matches() : " + emailmatcher.matches()
							+ ",postalcodematcher.matches():" + postalcodematcher.matches()
							+ ",Math.round(req.getDuration()):" + ( Math.round(req.getDuration()) != 12 && Math.round(req.getDuration()) != 24 )
							+ ",req.getAgreedFactSheet():" + !req.getAgreedFactSheet()
							+ ",req.getAgreedTermsConditions():" + !req.getAgreedTermsConditions()
							+ ",req.getStatus(): " + !(req.getStatus().equalsIgnoreCase("New"))
							);*/
			
			if(!isNumeric ||
				req.getEbsNumber().length() != 10 ||
				!emailmatcher.matches() || !postalcodematcher.matches() ||
				( Math.round(req.getDuration()) != 12 && Math.round(req.getDuration()) != 24 ) ||
				!req.getAgreedFactSheet() || !req.getAgreedTermsConditions() ||
				!(req.getStatus().equalsIgnoreCase("New"))
				) {
				
				System.out.println("Error on data validation!!!");
				res.setCallstatus("error");
				res.setMessage(inerrmsg);
				return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
			}
			
			
			//-End of Pen Test validations
			
			
			// Validate both ebsNumber and nricNo
			String ebsNumber = req.getEbsNumber();
			String nricNo = req.getNricNo();
			LeadValidReq v = new LeadValidReq(ebsNumber, nricNo);
			boolean ok = leadService.checkExist(v);
			if (ok) {
				res.setCallstatus("error");
				res.setMessage("101"); // error code 101 - both ebsNumber and nricNo duplicate
				return new ResponseEntity<>(res, HttpStatus.OK);
			}

			String reCaptcha = req.getReCaptcha();
			ok = captchaService.verify(reCaptcha);
			if (!ok) {
				res.setCallstatus("error");
				res.setMessage("102"); // error code 102 - invalid reCAPCHA
				return new ResponseEntity<>(res, HttpStatus.OK);
			}

			// Get environment variable
			String eSaveSfid = System.getenv("SFID_ESAVE");
			String eFixSfid = System.getenv("SFID_EFIX");
			String eQPlusSfid = System.getenv("SFID_EQ_PLUS");

			// Get data
			String sfid = req.getPlanName();
			int duration = Math.round(req.getDuration());
			String date = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
			String forStandardPricePlan = "";
			String forStandardPricePlan1 = "";
			String forStandardPricePlan2 = "";
			String forStandardPricePlan3 = "";
			String currentMeterType = req.getCurrentMeterType();
			String changeToAmiMeter = req.getChangeToAmiMeter();
			String durationEFix = "Not Applicable", durationESave = "Not Applicable", durationEQPlus = "Not Applicable";
			String timeEFix = "", timeESave = "", timeEQPlus = "";

			Calendar c = Calendar.getInstance();
			Date now = new Date();
			c.setTime(now);
			Integer offset = ZonedDateTime.now().getOffset().getTotalSeconds();

			// Add offset
			int seconds = req.getOffset();
			if (offset != seconds) {
				c.add(Calendar.SECOND, seconds);
				now = c.getTime();
			}

			String currentDate = new SimpleDateFormat("dd-MMM-yyyy").format(now);
			String currentDateTime = new SimpleDateFormat("dd-MMM-yyyy, hh:mm a").format(now);
			String name = req.getName();

			// Get Package information
			String month = Integer.toString(duration);
			Boolean isPromo = req.getIsPromo();

			Float rate1 = null;
			Float percent1 = null;
			Float nightRate1 = null;

			// Generate EMAF file PDF
			Map<String, String> data = new HashMap<String, String>();
			Package p;
			if (isPromo == null || !isPromo) {
				p = packageService.search(sfid, month);
				data.put("PackageDescription", "Not Applicable");

				rate1 = p.getRate();
				percent1 = p.getPercentDiscount();
				nightRate1 = p.getNightRateDollars();

			} else {
				p = packageService.searchPromo(sfid, month);
				String packageDescription = p.getPackageDescription();
				data.put("PackageDescription", packageDescription);

				rate1 = p.getRateOem();
				percent1 = p.getPercentDiscountOem();
				nightRate1 = p.getNightRateDollarsOem();
			}

			String planName = p.getName();
			Float rate = p.getRate();
			Float percent = p.getPercentDiscount();
			Float nightRate = p.getNightRateDollars();
			String nameOfPricePlanMeter = "";
			String nameOfPricePlan = "";
			String eFix = "false";
			String eSave = "false";
			String eQPlus = "false";

			if ("Cumulative_SRLP".equals(currentMeterType) && "No".equals(changeToAmiMeter)) {
				data.put("ToUseSmartMeter", "No");
				nameOfPricePlanMeter = " EXISTING METER";
			} else {
				data.put("ToUseSmartMeter", "Yes");
				nameOfPricePlanMeter = " SMART METER";
			}

			if ("Cumulative_SRLP".equals(currentMeterType) && "Yes".equals(changeToAmiMeter)) {
				data.put("PrevailingMeterCharge", "A one-time installation fee of $40 will apply.");
			} else {
				data.put("PrevailingMeterCharge", "Not Applicable");
			}

			data.put("TypeOfPricePlan", "Standard");

			if (eFixSfid.equals(sfid)) {
				forStandardPricePlan = "Fixed price plan: " + rate1 + " cents/kWh";
				eFix = "true";
				durationEFix = "Service Commitment (“Term”):";
				timeEFix = duration + " Months";
				nameOfPricePlan = planName + " " + duration / 12 + "YR (RESIDENTIAL-" + nameOfPricePlanMeter + ")";
			} else if (eSaveSfid.equals(sfid)) {
				forStandardPricePlan = "Discount-off regulated tariff plan: " + percent1 + "%";
				eSave = "true";
				durationESave = "Service Commitment (“Term”):";
				timeESave = duration + " Months";
				nameOfPricePlan = planName + " " + duration / 12 + "YR (RESIDENTIAL-" + nameOfPricePlanMeter + ")";
			} else if (eQPlusSfid.equals(sfid)) {
				forStandardPricePlan = "Peak Period:\r\n";
				forStandardPricePlan1 = "i.	" + rate1 + " cents/kWh from 7a.m. to 7p.m. on weekdays\r\n";
				forStandardPricePlan2 = "Off-Peak Period:\r\n";
				forStandardPricePlan3 = "ii.	" + nightRate1
						+ " cents/kWh from 7p.m. to 7a.m. on weekdays and all day on weekends and public holidays\r\n";
				eQPlus = "true";
				durationEQPlus = "Service Commitment (“Term”):";
				timeEQPlus = duration + " Months";
				nameOfPricePlan = planName + "(RESIDENTIAL-" + nameOfPricePlanMeter + ")";
			}

			data.put("NameOfPricePlan", nameOfPricePlan);
			data.put("ForStandardPricePlan", forStandardPricePlan);
			data.put("ForStandardPricePlan1", forStandardPricePlan1);
			data.put("ForStandardPricePlan2", forStandardPricePlan2);
			data.put("ForStandardPricePlan3", forStandardPricePlan3);
			data.put("ContractDuration", duration + " Months");
			data.put("Name", name);
			data.put("CurrentDate", currentDate);
			data.put("CurrentDateTime", currentDateTime);

			String url = System.getenv("BUCKETEER_BUCKET_URL") + "/";
			String fs = pdfGenarator.createPdf("EMAF", "factsheet", data);
			req.setFactSheet(url + fs);

			// Generate SAGM file PDF
			data = new HashMap<String, String>();
			data.put("Name", name);
			data.put("Email", req.getEmail());
			data.put("MobileNo", req.getMobilePhone());
			data.put("CurrentDate", currentDate);
			data.put("CurrentDateTime", currentDateTime);

			String addressOfSupply = req.getAddress1() + ", " + req.getAddress2() + ", Singapore "
					+ req.getPostalCode();
			data.put("AddressOfSupply", addressOfSupply);
			data.put("ResidenceType", req.getResidentialType());
			data.put("AccountNumber", req.getEbsNumber());
			data.put("RequestForMeterChangeToAMI", req.getChangeToAmiMeter());
			data.put("CurrentMeterType", req.getCurrentMeterType().replace("_", " / "));
			data.put("MeterSelfRead", req.getMeterSelfRead());

			date = new SimpleDateFormat("dd/MM/yyyy").format(req.getRegistrationDate());
			data.put("RegistrationDate", date);
			data.put("NricNo", req.getNricNo());
			data.put("TelephoneName", req.getPhone());

			date = new SimpleDateFormat("dd/MM/yyyy").format(req.getPlanStartDate());
			data.put("PreferredDateOfSupply", date);

			date = new SimpleDateFormat("dd/MM/yyyy").format(req.getContractEndDateOem());
			data.put("ContractEndDate", date);

			data.put("DurationEFIX", durationEFix);
			data.put("DurationESAVE", durationESave);
			data.put("DurationEQPlus", durationEQPlus);
			data.put("TimeEFIX", timeEFix);
			data.put("TimeESAVE", timeESave);
			data.put("TimeEQPlus", timeEQPlus);

			String rateDollars = "";
			String discount = "";

			if (rate == null) {
				rateDollars = "-";
			} else {
				rate = rate / 100; // convert cents to $
				req.setRate(rate);
				rateDollars = rate + "";
			}

			if (nightRate != null) {
				nightRate = nightRate / 100; // convert cents to $
				req.setNightRate(nightRate);
			}

			if (percent == null) {
				discount = "-";
			} else {
				discount = percent + "";
			}

			if (eQPlusSfid.equals(sfid)) {
				rateDollars = "-";
				discount = "-";
			}

			data.put("RateDollars", rateDollars);
			data.put("Discount", discount);
			data.put("eSAVE", eSave);
			data.put("eFIX", eFix);
			data.put("eQPlus", eQPlus);

			String sa = pdfGenarator.createPdf("SAGM", "salesagreement", data);
			req.setSalesAgreement(url + sa);

			// Handle
			String no = leadService.save(req);

			// Set data
			Map<String, Object> tmp = new LinkedHashMap<>();
			tmp.put("factSheet", fs);
			tmp.put("salesAgreement", sa);
			tmp.put("webReferenceNo", no);
			res.setResult(tmp);
		} catch (Exception ex) {
			ex.printStackTrace();
			res.setCallstatus("error");
			res.setMessage(ex.getMessage());
		}

		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	@RequestMapping(value = "/valid", method = RequestMethod.POST)
	public ResponseEntity<?> valid(@RequestHeader HttpHeaders header, @RequestBody LeadValidReq req) {
		SingleRsp res = new SingleRsp();

		try {
			// Handle
			boolean tmp = leadService.checkExist(req);
			res.setResult(tmp);
		} catch (Exception ex) {
			ex.printStackTrace();
			res.setCallstatus("error");
			res.setMessage(ex.getMessage());
		}

		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	@RequestMapping(value = "/sgLocate", method = RequestMethod.POST)
	public ResponseEntity<?> sgLocate(@RequestHeader HttpHeaders header, @RequestBody SglocateReq req) {
		// Setting
		String url = "https://www.sglocate.com/api/json/";

		// Get environment variable
		String key = System.getenv("SGLOCATE_API_KEY");
		String secret = System.getenv("SGLOCATE_API_SECRET_KEY");

		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.MULTIPART_FORM_DATA);
		MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
		map.add("APIKey", key);
		map.add("APISecret", secret);

		// Get data
		String postcode = req.getPostcode();
		String block = req.getBlock();
		String streetName = req.getStreetName();

		// Check empty
		if (block == null || block.isEmpty()) {
			block = "";
		}
		if (streetName == null || streetName.isEmpty()) {
			streetName = "";
		}

		// Check use URL
		if (postcode == null || postcode.isEmpty()) {
			url += "searchwithblocknumberandstreetname.aspx";
			map.add("Block", block); // 10
			map.add("StreetName", streetName); // Eunos Road 8
		} else {
			url += "searchwithpostcode.aspx";
			map.add("Postcode", postcode); // 408600
		}

		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);
		ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);

		return response;
	}

	@RequestMapping(value = "/genPdf", method = RequestMethod.GET)
	public ResponseEntity<?> genPdf(@RequestHeader HttpHeaders header) {
		SingleRsp res = new SingleRsp();

		try {
			Map<String, String> data = new HashMap<String, String>();
			data.put("name", "James");

			String tmp = pdfGenarator.createPdf("greeting", "test", data);
			res.setResult(tmp);
		} catch (Exception e) {
			res.setCallstatus("error");
			res.setMessage(e.getMessage());
		}

		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	@RequestMapping(value = "/download", method = RequestMethod.POST)
	public ResponseEntity<InputStreamResource> download(@RequestHeader HttpHeaders header, @RequestBody BaseReq req) {
		// Get data
		String fileName = req.getKeyword();

		// Handle
		S3Object s3 = pdfGenarator.download(fileName);
		long len = s3.getObjectMetadata().getInstanceLength();
		InputStreamResource res = new InputStreamResource(s3.getObjectContent());
		fileName = "attachment;filename=" + fileName;

		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, fileName)
				.contentType(MediaType.APPLICATION_PDF).contentLength(len).body(res);
	}

	/**
	 * Get environment variable for business form
	 * 
	 * @param header
	 * @return
	 */
	@RequestMapping(value = "/formData", method = RequestMethod.GET)
	public ResponseEntity<?> getFormData(@RequestHeader HttpHeaders header) {
		MultipleRsp res = new MultipleRsp();

		try {
			// Handle
			String f1 = System.getenv("BIZ_FORM_1");
			String f2 = System.getenv("BIZ_FORM_2");
			String f3 = System.getenv("BIZ_FORM_3");
			String f4 = System.getenv("BIZ_FORM_4");

			// Set data
			Map<String, Object> data = new LinkedHashMap<>();
			data.put("f1", f1);
			data.put("f2", f2);
			data.put("f3", f3);
			data.put("f4", f4);
			res.setResult(data);
		} catch (Exception ex) {
			res.setCallstatus("error");
			res.setMessage(ex.getMessage());
		}

		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	/**
	 * Get environment variable for residential form
	 * 
	 * @param header
	 * @return
	 */
	@RequestMapping(value = "/packageData", method = RequestMethod.GET)
	public ResponseEntity<?> getPackageData(@RequestHeader HttpHeaders header,
			@RequestParam(name = "isPromo", required = false) Boolean isPromo) {
		MultipleRsp res = new MultipleRsp();

		try {
			// Handle
			String eSaveSfid = System.getenv("SFID_ESAVE");
			String eFixSfid = System.getenv("SFID_EFIX");
			String eQPlusSfid = System.getenv("SFID_EQ_PLUS");
			String siteKey = System.getenv("CAPTCHA_SITE_KEY");

			List<Package> tmp;
			if (isPromo == null || !isPromo) {
				tmp = packageService.search();
			} else {
				tmp = packageService.searchPromo();
			}

			// Find name
			Package s = tmp.stream().filter(p -> p.getSfid().equals(eSaveSfid)).findFirst().orElse(new Package());
			String eSaveName = s.getName();
			s = tmp.stream().filter(p -> p.getSfid().equals(eFixSfid)).findFirst().orElse(new Package());
			String eFixName = s.getName();
			s = tmp.stream().filter(p -> p.getSfid().equals(eQPlusSfid)).findFirst().orElse(new Package());
			String eQPlusName = s.getName();

			// Set data
			Map<String, Object> data = new LinkedHashMap<>();
			data.put("eSaveSfid", eSaveSfid);
			data.put("eFixSfid", eFixSfid);
			data.put("eQPlusSfid", eQPlusSfid);
			data.put("eSaveName", eSaveName);
			data.put("eFixName", eFixName);
			data.put("eQPlusName", eQPlusName);
			data.put("siteKey", siteKey);
			res.setResult(data);
		} catch (Exception ex) {
			res.setCallstatus("error");
			res.setMessage(ex.getMessage());
		}

		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	// end
}