package com.hcl.bootcamp.fs.springboot.app.controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.hcl.bootcamp.fs.springboot.app.jpa.SectionRepository;
import com.hcl.bootcamp.fs.springboot.app.model.Section;

@CrossOrigin
@Controller
public class SectionController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private SectionRepository sectionsRepository;

//    @RequestMapping("/")
//    public String home(Model model) {
//    	////val x = sectionsRepository.findAll();
//    	////model.addAttribute("sections", x);
//    	
//        return "home";
//    }
}