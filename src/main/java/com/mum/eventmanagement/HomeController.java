package com.mum.eventmanagement;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@PreAuthorize("authenticated()")
	@RequestMapping(value = { "/", "/index", "/home" }, method = RequestMethod.GET)
	public String home(Locale locale, Model model) {		
		logger.info("Welcome home! The client locale is {}.", locale);
		return "forward:/event/events";
	}

	@RequestMapping(value = "/sidebar", method = RequestMethod.GET)
	public String sideBar() {
		return "sidebar";
	}

	@RequestMapping(value = "/topNews", method = RequestMethod.GET)
	public String topNews() {
		return "topNews";
	}

}
