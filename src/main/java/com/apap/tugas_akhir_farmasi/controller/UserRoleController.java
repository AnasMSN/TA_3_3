package com.apap.tugas_akhir_farmasi.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.apap.tugas_akhir_farmasi.model.UserRoleModel;
import com.apap.tugas_akhir_farmasi.service.service_interface.UserRoleService;

@Controller

public class UserRoleController {
	@Autowired
	private UserRoleService userRoleService;

	@RequestMapping(value = "user/addUser", method = RequestMethod.POST)
	private String addUserSubmit(@ModelAttribute UserRoleModel user, Model model, Principal principal) {
		UserRoleModel user2 = userRoleService.getUser(principal.getName());

		String myUser = user.getUsername();

		boolean cekUsername = userRoleService.usernameCheck(user.getUsername());
		
		if (cekUsername==true) {
			model.addAttribute("message", "Failed");
			
		}

		else {
			userRoleService.addUser(user);
			model.addAttribute("message", "Success");
		

			
		}
		model.addAttribute("user", user2);
		return "home";
	}
}
