package com.apap.tutorial4.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.apap.tutorial4.model.PilotModel;
import com.apap.tutorial4.service.PilotService;

@Controller
public class PilotController {
	@Autowired
	private PilotService pilotService;
	
	@RequestMapping("/")
	private String home() {
		return "home";
	}
	
	@RequestMapping(value="/pilot/add",method=RequestMethod.GET)
	private String add(Model model) {
		model.addAttribute("pilot",new PilotModel());
		return "addPilot";
	}
	
	@RequestMapping(value="/pilot/add",method=RequestMethod.POST)
	private String addPilotSubmit(@ModelAttribute PilotModel pilot) {
		pilotService.addPilot(pilot);
		return "add";
	}
	
	@RequestMapping(value="/pilot/view")
	private String viewPilot(
			@RequestParam("licenseNumber") String licenseNumber, 
			Model model
			) {
		PilotModel pilot = pilotService.getPilotByLicenseNumber(licenseNumber);
		model.addAttribute("pilot",pilot);
		return "view-pilot";
	}
	
	@RequestMapping("/pilot/view/{licenseNumber}")
	public String viewPath(@PathVariable String licenseNumber, Model model) {
		PilotModel pilot = pilotService.getPilotByLicenseNumber(licenseNumber);
		if (pilot==null) {
			model.addAttribute("error","Plat nomor "+licenseNumber+" tidak ditemukan.");
			return "error-message";
		}
		else {
			model.addAttribute("pilot", pilot);
			return "view-pilot";
		}
	}
	
	@RequestMapping(value="/pilot/delete/{licenseNumber}")
	private String deletePilot(
			@PathVariable String licenseNumber, 
			Model model
			) {
		PilotModel pilot = pilotService.getPilotByLicenseNumber(licenseNumber);
		pilotService.deletePilot(pilot);
		return "delete";
	}
	
	@RequestMapping(value="/pilot/edit/{licenseNumber}")
	private String edit(@PathVariable String licenseNumber, Model model) {
		PilotModel pilot = pilotService.getPilotByLicenseNumber(licenseNumber);
		model.addAttribute("pilot",pilot);
		return "editPilot";
	}
	
	@RequestMapping(value="/pilot/edit",method=RequestMethod.POST)
	private String editPilotSubmit(@ModelAttribute PilotModel pilot) {
		pilotService.addPilot(pilot);
		return "add";
	}
}
