package com.hcl.bootcamp.fs.springboot.app.controller;

import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hcl.bootcamp.fs.springboot.app.jpa.SectionRepository;
import com.hcl.bootcamp.fs.springboot.app.model.Section;
import com.hcl.bootcamp.fs.springboot.app.model.User;
import com.hcl.bootcamp.fs.springboot.app.model.UserForm;
import com.hcl.bootcamp.fs.springboot.app.service.SecurityService;
import com.hcl.bootcamp.fs.springboot.app.service.UserService;
import com.hcl.bootcamp.fs.springboot.app.validator.UserValidator;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;

	@Autowired
	private SecurityService securityService;

	@Autowired
	private UserValidator userValidator;

	@Autowired
	private SectionRepository sectionsRepository;
//	
//	@Autowired
//	private SeatRepository seatRepository;	

	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public String registration(@ModelAttribute("userForm") UserForm userForm, BindingResult bindingResult, Model model) {
		System.out.println("registration POST");
		userValidator.validate(userForm, bindingResult);

		System.out.println(bindingResult.hasErrors());
		if (bindingResult.hasErrors()) {
			model.addAttribute("states", getStates());
			return "login";
		}

		userService.save(buildUser(userForm));

		///securityService.autologin(userForm.getUsername(), userForm.getPasswordConfirm());
		securityService.autologin(userForm.getEmail(), userForm.getPasswordConfirm());
		List<Section> sections = sectionsRepository.findAll();
		System.out.println("************************");
		System.out.println(sections);
		model.addAttribute("sections", sections);
		System.out.println("************************");
		return "screen2";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model, String error, String logout) {
		System.out.println("home GET");
		
		System.out.println("login GET [" + logout + "] " + " [" + error +"]");
		model.addAttribute("userForm", new User());
		if (error != null)
			model.addAttribute("error", "Your username and password is invalid.");

		if (logout != null)
			model.addAttribute("message", "You have been logged out successfully.");

		///model.addAttribute("states", states);
		model.addAttribute("states", getStates());
		return "login";
	}

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
	private User buildUser(UserForm userForm) {
		User user = User.builder().updatedAt(Calendar.getInstance().getTime()).userName(userForm.getEmail())
				.firstName(userForm.getFirstName()).lastName(userForm.getLastName()).enable(true)
				.location(userForm.getLocation()).country(userForm.getCountry()).password(userForm.getPassword())
				.createdAt(Calendar.getInstance().getTime()).build();
		return user;
	}	
	private Map<Integer, String> getStates(){
		Map<Integer, String> states = new LinkedHashMap<Integer, String>();
		states.put(1, "Alabama");
		states.put(2, "Alaska");
		states.put(3, "Arizona");
		states.put(4, "Arkansas");
		states.put(5, "California");
		return states;
	}		
}
