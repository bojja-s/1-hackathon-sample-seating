package com.hcl.bootcamp.fs.springboot.app.controller;

import java.util.Calendar;
import java.util.LinkedHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hcl.bootcamp.fs.springboot.app.model.User;
import com.hcl.bootcamp.fs.springboot.app.model.UserForm;
import com.hcl.bootcamp.fs.springboot.app.service.SecurityService;
import com.hcl.bootcamp.fs.springboot.app.service.UserService;
import com.hcl.bootcamp.fs.springboot.app.validator.UserValidator;

@Controller
public class UserController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private UserService userService;

	@Autowired
	private SecurityService securityService;

	@Autowired
	private UserValidator userValidator;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public String registration(@ModelAttribute("userForm") UserForm userForm, BindingResult bindingResult,Model model) {
		if (logger.isInfoEnabled()){
			logger.info("UserController registration POST" );
		}
		userValidator.validate(userForm, bindingResult);
		if (bindingResult.hasErrors()) {
			return "login";
		}
		userService.save(buildUser(userForm));
		securityService.autologin(userForm.getEmail(), userForm.getPasswordConfirm());
		return "redirect:/screen2";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model, String error, String logout) {
		if (logger.isInfoEnabled()){
			logger.info("UserController login GET" );
		}

		System.out.println("login GET [" + logout + "] " + " [" + error + "]");
		model.addAttribute("userForm", new UserForm());
		if (error != null) {
			model.addAttribute("error", "Your username and password is invalid.");
		}

		if (logout != null) {
			model.addAttribute("message", "You have been logged out successfully.");
		}

		LinkedHashMap<Integer, String> states = new LinkedHashMap<Integer, String>();
		states.put(1, "Alabama");
		states.put(2, "Alaska");
		states.put(3, "Arizona");
		states.put(4, "Arkansas");
		states.put(5, "California");
		model.addAttribute("states", states);
		return "login";
	}

	private User buildUser(UserForm userForm) {
		if (logger.isInfoEnabled()){
			logger.info("UserController buildUser" );
		}
		User user = User.builder().updatedAt(Calendar.getInstance().getTime()).userName(userForm.getEmail())
				.firstName(userForm.getFirstName()).lastName(userForm.getLastName()).enable(true)
				.location(userForm.getLocation()).country(userForm.getCountry()).password(passwordEncoder.encode(userForm.getPassword()))
				.createdAt(Calendar.getInstance().getTime()).build();
		if (logger.isInfoEnabled()){
			logger.info( user.getUserName() );
			//logger.info( user.getEmail() );
			logger.info( user.getFirstName() );
			logger.info( user.getLastName() );
			logger.info( user.getLocation() );
			logger.info( user.getCountry() );
			logger.info( user.getPassword() );
		}		
		return user;
	}

}
