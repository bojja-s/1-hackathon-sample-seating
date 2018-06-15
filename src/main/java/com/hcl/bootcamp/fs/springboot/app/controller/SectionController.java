package com.hcl.bootcamp.fs.springboot.app.controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hcl.bootcamp.fs.springboot.app.jpa.SectionRepository;
import lombok.val;

@CrossOrigin
@Controller
public class SectionController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private SectionRepository sectionsRepository;

	@RequestMapping(value = { "/", "/screen2" }, method = RequestMethod.GET)
	public String welcome(Model model) {
		System.out.println("screen2 GET");
		List<Section> sections = sectionsRepository.findAll();
		System.out.println("************************");
		System.out.println(sections);
		model.addAttribute("sections", sections);
		System.out.println("************************");			
		return "screen2";
	}	
}