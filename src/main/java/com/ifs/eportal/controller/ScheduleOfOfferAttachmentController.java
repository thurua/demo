package com.ifs.eportal.controller;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ifs.eportal.bll.ScheduleOfOfferAttachmentService;
import com.ifs.eportal.common.ZConfig;
import com.ifs.eportal.common.ZFile;
import com.ifs.eportal.common.ZToken;
import com.ifs.eportal.dto.AttachmentDto;
import com.ifs.eportal.dto.SortDto;
import com.ifs.eportal.filter.AttachmentFilter;
import com.ifs.eportal.model.ScheduleOfOfferAttachment;
import com.ifs.eportal.req.AttachmentReq;
import com.ifs.eportal.req.PagingReq;
import com.ifs.eportal.rsp.BaseRsp;
import com.ifs.eportal.rsp.MultipleRsp;

/**
 * 
 * @author ToanNguyen 2018-Oct-05 (verified)
 *
 */
@RestController
@RequestMapping("/schedule-of-offer-attachment")
public class ScheduleOfOfferAttachmentController {
	// region -- Fields --

	private static final Logger _log = Logger.getLogger(ScheduleOfOfferAttachmentController.class.getName());

	@Autowired
	private ScheduleOfOfferAttachmentService scheduleOfOfferAttachmentService;

	// end

	// region -- Methods --

	public ScheduleOfOfferAttachmentController() {
		// ZConfig._allowUpload = true;
	}

	/**
	 * Upload for Angular (ScheduleOfOfferAttachment)
	 * 
	 * @param header
	 * @param files
	 * @return
	 * @author ToanNguyen 2018-Oct-04
	 */
	@PostMapping("/upload-multi")
	public ResponseEntity<?> upload(@RequestHeader HttpHeaders header, @RequestParam("files") MultipartFile[] files,
			@RequestParam("req") String req) {
		MultipleRsp res = new MultipleRsp();

		try {
			ObjectMapper mapper = new ObjectMapper();
			AttachmentReq o = mapper.readValue(req, AttachmentReq.class);

			// Get data
			String sfId = ZToken.getSfId(header);
			String parentUuId = o.getParentUuId();

			for (int i = 0; i < files.length; i++) {
				String originalName = files[i].getOriginalFilename() + "";
				String type = files[i].getContentType();
				float size = (float) files[i].getSize();

				// Get extension
				int t = originalName.lastIndexOf(".") + 1;
				String extension = originalName.substring(t);

				// Create name
				String path = parentUuId + "/Attachment";
				String uuId = UUID.randomUUID().toString();
				String name = uuId + "." + extension;

				// Upload file to S3
				String url = "";
				if (ZConfig._allowUpload) {
					InputStream in = files[i].getInputStream();
					url = ZFile.upload(in, name, path);
				}

				ScheduleOfOfferAttachment m = new ScheduleOfOfferAttachment();
				m.setSequence((float) i);
				m.setName(originalName);
				m.setExtension(extension);
				m.setUploadedBy(sfId);
				m.setContentType(type);
				m.setFilePath(url);
				m.setFileSize(size);
				m.setUuId(uuId);
				m.setParentUuId(parentUuId);

				scheduleOfOfferAttachmentService.create(m);
			}

			// Load file from DB
			if (files.length > 0) {
				List<SortDto> sort = new ArrayList<SortDto>();
				sort.add(new SortDto("uploadedOn", "DESC"));
				PagingReq pr;
				pr = new PagingReq(new AttachmentFilter(parentUuId), sort, false);
				return search(pr);
			}
		} catch (Exception ex) {
			res.setError(ex.getMessage());
		}

		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	/**
	 * Search by
	 * 
	 * @param req
	 * @return
	 */
	@PostMapping("/search")
	public ResponseEntity<?> search(@RequestBody PagingReq req) {
		MultipleRsp res = new MultipleRsp();

		try {
			// Handle
			List<AttachmentDto> t;
			t = scheduleOfOfferAttachmentService.search(req);

			// Set data
			Map<String, Object> data = new LinkedHashMap<>();
			data.put("page", req.getPage());
			data.put("size", req.getSize());
			data.put("total", req.getTotal());
			data.put("data", t);

			res.setResult(data);
		} catch (Exception ex) {
			if (ZConfig._printTrace) {
				ex.printStackTrace();
			}
			if (ZConfig._writeLog) {
				_log.log(Level.SEVERE, ex.getMessage(), ex);
			}
		}

		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	/**
	 * Delete by
	 * 
	 * @param id
	 * @return
	 */
	@PostMapping("/delete")
	public ResponseEntity<?> delete(@RequestBody Integer id) {
		BaseRsp res = new BaseRsp();

		try {
			// Handle
			res = scheduleOfOfferAttachmentService.delete(id);
		} catch (Exception ex) {
			if (ZConfig._printTrace) {
				ex.printStackTrace();
			}
			if (ZConfig._writeLog) {
				_log.log(Level.SEVERE, ex.getMessage(), ex);
			}

			res.setError("Cannot delete file");
		}

		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	// end
}