package com.hcl.bootcamp.fs.springboot.app.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hcl.bootcamp.fs.springboot.app.jpa.SectionRepository;
import com.hcl.bootcamp.fs.springboot.app.model.Seat;
import com.hcl.bootcamp.fs.springboot.app.model.Section;
import com.hcl.bootcamp.fs.springboot.app.model.SectionForm;
import com.hcl.bootcamp.fs.springboot.app.service.UserDetailsHeper;

import lombok.val;

@CrossOrigin
@Controller
public class SectionController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private SectionRepository m_SectionRepository;

	@RequestMapping(value = { "/", "/screen2" }, method = RequestMethod.GET)
	public String welcome(@ModelAttribute("sectionForm") SectionForm sectionForm, BindingResult bindingResult,Model model) {
		if (logger.isInfoEnabled()) {
			logger.info("[START] SectionController screen22 GET");
		}
		val x = m_SectionRepository.findAll();
		model.addAttribute("sectionForm", new SectionForm());
		model.addAttribute("sections", x);
		if (x != null && x.size() > 0) {
			Section i_Section = x.get(0);
			List<Seat> i_SeatsList = i_Section.getSeats();
			List<Seat> i_SeatsAvailableList = new ArrayList<Seat>();
			for ( Seat i_Seat: i_SeatsList) {
				if (i_Seat.getAvailable() == Boolean.FALSE ) {
					logger.info("  *** i_Seat " + i_Seat.getName() + " already booked");
				}else {
					logger.info("  $ i_Seat " + i_Seat.getName() + " to be booked");
					i_SeatsAvailableList.add(i_Seat);
				}
			}
			model.addAttribute("seats", i_SeatsAvailableList);
		}
		if (logger.isInfoEnabled()) {
			logger.info("[END] SectionController screen22 GET");
		}		
		return "screen2";
	}

	@RequestMapping(value = "/booktickets")
	public String bookTickets(@ModelAttribute("sectionForm") SectionForm sectionForm, BindingResult bindingResult,Model model) {
		if (logger.isInfoEnabled()) {
			logger.info("[START] SectionController booktickets POST");
		}
//		Long i_SecretValue = sectionForm.getSecretValue();
//		if (logger.isInfoEnabled()) {
//			logger.info("	i_SecretValue [" + i_SecretValue + "] ");
//		}
//		if (!i_SecretValue.equals("0")) {
//			logger.info("	********************************************************************************* ");
//			Optional<Section> i_Section = m_SectionRepository.findById(i_SecretValue);
//			if (i_Section.isPresent()) {
//				model.addAttribute("seats", i_Section.get().getSeats());
//				return "screen2";
//			}else {
//				if (logger.isWarnEnabled()) {
//					logger.warn("	Error, section  " + i_SecretValue + " is NOT present");
//				}
//				return "screen2";
//			}
//		}
		String i_User = UserDetailsHeper.findLoggedInUsername();
		Long i_SectionID = sectionForm.getSectionId();
		//Long i_SeatID = sectionForm.getSeatId();
		String i_SeatName = sectionForm.getSeatName();
		if (logger.isInfoEnabled()) {
			logger.info("	i_SectionID [" + i_SectionID + "]  i_User [" + i_User + "] i_SeatName [" + i_SeatName +"]");
		}		
		Optional<Section> i_Section = m_SectionRepository.findById(i_SectionID);
		//Optional<Section> i_Section = m_SectionRepository.findById(i_SectionID);
		if (i_Section.isPresent()) {
			List<Seat> i_SeatList = i_Section.get().getSeats();
			if (logger.isInfoEnabled()) {
				logger.info("SectionController i_SeatList size " + i_SeatList.size());
			}
			for (Seat i_Seat : i_SeatList) {
				if (logger.isInfoEnabled()) {
					logger.info("SectionController i_Seat Name [" + i_Seat.getName() + "] ID [" + i_Seat.getId() + "] "
							+ i_Seat.getUserName());
					if (i_SeatName.equals( i_Seat.getName())) {
						i_Seat.setAvailable(false);
						i_Seat.setUserName(i_User);
						m_SectionRepository.save(i_Section.get());
						logger.info("SectionController i_Seat Update Done");
						model.addAttribute("seatcount", 1);
						break;
					}
				}
			}
		} else {
			logger.warn("Section Entity is NOT present by ID " + i_SectionID);
		}
		if (logger.isInfoEnabled()) {
			logger.info("[END] SectionController booktickets POST");
		}		
		return "screen3";
	}
}