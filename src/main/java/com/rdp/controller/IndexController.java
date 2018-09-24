package com.rdp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@RestController
public class IndexController implements ErrorController {
	// region -- Fields --

	private static final String PATH = "/error";

	// end

	// region -- Methods --

	@RequestMapping(value = PATH, method = RequestMethod.GET)
	public ModelAndView gotoNextPage(HttpServletRequest request, HttpServletResponse response) {
		ModelMap model = new ModelMap();

		return new ModelAndView(new RedirectView("/", true), model);
	}

	@Override
	public String getErrorPath() {
		return PATH;
	}

	// end
}