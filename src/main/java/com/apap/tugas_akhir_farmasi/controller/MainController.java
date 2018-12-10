package com.apap.tugas_akhir_farmasi.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


import com.apap.tugas_akhir_farmasi.model.UserRoleModel;
import com.apap.tugas_akhir_farmasi.service.service_interface.UserRoleService;

import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class MainController {
	@Autowired
	UserRoleService userRoleService;
	
	@RequestMapping("/")
	private String home(Model model,Principal principal) {
		UserRoleModel user = userRoleService.getUser(principal.getName());
		model.addAttribute("user", user);
		model.addAttribute("title", "Home");
		
		return "home";
	}
	
	@RequestMapping("/login")
	public String login() {
		return "login";
	}
}
