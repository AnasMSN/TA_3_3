package com.apap.tugas_akhir_farmasi.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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

		if (cekUsername == true) {
			model.addAttribute("message", "Failed");

		}

		else {
			userRoleService.addUser(user);
			model.addAttribute("message", "Success");

		}
		model.addAttribute("user", user2);
		return "home";
	}


	@RequestMapping(value = "user/updatepassword", method = RequestMethod.POST)
	private String updatePasswordSubmit(@RequestParam(value = "passwordLama") String passwordLama,
			@RequestParam(value = "passwordBaru") String passwordBaru,
			@RequestParam(value = "konfirmasiPassword") String konfirmasiPassword, HttpServletRequest request,
			Model model) {

		UserRoleModel user = userRoleService
				.getUser(SecurityContextHolder.getContext().getAuthentication().getName());

		if (passwordBaru.equals(konfirmasiPassword)) {
			
			if (userRoleService.cekPassword(passwordLama, user.getPassword())) {
				user.setPassword(passwordBaru);
				userRoleService.addUser(user);

				model.addAttribute("user", user);
				model.addAttribute("message", "success");
				return "home";
			}
			
			else {
				model.addAttribute("user", user);
				model.addAttribute("message", "tidakcocok");
				return "home";
			}
		}
		
		else {
			model.addAttribute("user", user);
			model.addAttribute("message", "konfirmasi");
			return "home";

		}
	}
}
