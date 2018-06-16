package com.hcl.bootcamp.fs.springboot.app.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@CrossOrigin
@Controller
public class PurchaseSummaryController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	
	@RequestMapping(value = "/screen3",method=RequestMethod.GET)
	public String summary(Model model) {
		if (logger.isInfoEnabled()){
			logger.info("PurchaseSummaryController screen3 GET" );
		}
		return "screen3";
	}
	
}