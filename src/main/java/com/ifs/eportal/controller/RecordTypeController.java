package com.ifs.eportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ifs.eportal.bll.RecordTypeService;

@RestController
@RequestMapping("/record-type")
public class RecordTypeController {

	// region -- Fields --

	@Autowired
	private RecordTypeService recordTypeService;

	// end

	// region -- Methods --

	// end
}