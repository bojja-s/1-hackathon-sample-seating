package com.krishna.seatbooking;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hcl.bootcamp.fs.springboot.app.jpa.SeatRepository;
import com.hcl.bootcamp.fs.springboot.app.jpa.SectionRepository;
import com.hcl.bootcamp.fs.springboot.app.model.Seat;
import com.hcl.bootcamp.fs.springboot.app.model.Section;
import com.hcl.bootcamp.fs.springboot.app.model.User;
import com.hcl.bootcamp.fs.springboot.app.service.SecurityService;
import com.hcl.bootcamp.fs.springboot.app.service.UserService;
import com.hcl.bootcamp.fs.springboot.app.validator.UserValidator;

@Controller
public class UserController {
	
	//@Autowired
	//private UserService userService;

	///@Autowired
	///private SecurityService securityService;

	@Autowired
	private UserValidator userValidator;

	@Autowired
	private SectionRepository sectionsRepository;
	
	@Autowired
	private SeatRepository seatRepository;	
	
//	@RequestMapping(value = "/registration", method = RequestMethod.GET)
//	public String registration(Model model) {
//		System.out.println("registration GET");
//		model.addAttribute("userForm", new User());
//
//		return "registration";
//	}

	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {
		System.out.println("registration POST");
		userValidator.validate(userForm, bindingResult);

		System.out.println(bindingResult.hasErrors());
		if (bindingResult.hasErrors()) {
			return "login";
		}

		////userService.save(userForm);

		///securityService.autologin(userForm.getUsername(), userForm.getPasswordConfirm());
		///securityService.autologin(userForm.getEmail(), userForm.getPasswordConfirm());
		List<Section> sections = sectionsRepository.findAll();
		System.out.println("************************");
		System.out.println(sections);
		model.addAttribute("sections", sections);
		System.out.println( sections.get(0).getId());
		System.out.println( sections.get(0).getName());
		System.out.println("************************");
		return "screen2";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model, String error, String logout) {
		System.out.println("home GET");
	
		Section ii_Section = new Section();
		ii_Section.setId(1L);
		Seat i_Seat = new Seat();
		i_Seat.setId(1L);
		i_Seat.setName("1_6");
		i_Seat.setSection(ii_Section);
		i_Seat.setAvailable(Boolean.TRUE);
		
		List<Seat> i_SeatList = new ArrayList<Seat>();
		i_SeatList.add(i_Seat);
		Section i_Section = new Section();
		i_Section.setId(1L);
		i_Section.setName("103");
		i_Section.setLayoutInfo("___fffffff");
		i_Section.setSeats(i_SeatList);
		sectionsRepository.save(i_Section);
		
		seatRepository.save(i_Seat);	
		
		System.out.println("login GET [" + logout + "] " + " [" + error +"]");
		model.addAttribute("userForm", new User());
		if (error != null)
			model.addAttribute("error", "Your username and password is invalid.");

		if (logout != null)
			model.addAttribute("message", "You have been logged out successfully.");

		LinkedHashMap<Integer, String> states = new LinkedHashMap<Integer, String>();
		states.put(1, "Alabama");
		states.put(2, "Alaska");
		states.put(3, "Arizona");
		states.put(4, "Arkansas");
		states.put(5, "California");		
		model.addAttribute("states", states);
		return "login";
	}

	@RequestMapping(value = { "/", "/screen2" }, method = RequestMethod.GET)
	public String welcome(Model model) {
		System.out.println("screen2 GET");
		List<Section> sections = sectionsRepository.findAll();
		System.out.println("************************");
		System.out.println(sections);
		model.addAttribute("sections", sections);
		System.out.println( sections.get(0).getId());
		System.out.println( sections.get(0).getName());
		System.out.println("************************");			
		return "screen2";
	}
}
