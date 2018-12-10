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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.apap.tugas_akhir_farmasi.model.UserRoleModel;
import com.apap.tugas_akhir_farmasi.service.service_interface.UserRoleService;

@Controller

public class UserRoleController {
	@Autowired
	private UserRoleService userRoleService;

	@RequestMapping(value = "user/addUser", method = RequestMethod.POST)
	private String addUserSubmit(@ModelAttribute UserRoleModel user, Model model, Principal principal, RedirectAttributes redir) {
		UserRoleModel user2 = userRoleService.getUser(principal.getName());

		String myUser = user.getUsername();

		boolean cekUsername = userRoleService.usernameCheck(user.getUsername());

		if (cekUsername == true) {
			redir.addFlashAttribute("message","Failed");
			

		}

		else {
			userRoleService.addUser(user);
			redir.addFlashAttribute("message", "Success");
			

		}
		model.addAttribute("user", user2);
		return "redirect:/";
	}

	@RequestMapping(value = "/user/addUserAdmin", method = RequestMethod.POST)
	private String addUserSubmitAdmin(@ModelAttribute UserRoleModel user, Model model, RedirectAttributes redir) {
		


		
		userRoleService.addUser(user);
			
		
		return "login";
	}

	@RequestMapping(value = "user/updatepassword", method = RequestMethod.POST)
	private String updatePasswordSubmit(@RequestParam(value = "passwordLama") String passwordLama,
			@RequestParam(value = "passwordBaru") String passwordBaru,
			@RequestParam(value = "konfirmasiPassword") String konfirmasiPassword, HttpServletRequest request,
			Model model, RedirectAttributes redir) {

		UserRoleModel user = userRoleService
				.getUser(SecurityContextHolder.getContext().getAuthentication().getName());

		if (passwordBaru.equals(konfirmasiPassword)) {
			
			if (userRoleService.cekPassword(passwordLama, user.getPassword())) {
				user.setPassword(passwordBaru);
				userRoleService.addUser(user);

				model.addAttribute("user", user);
				redir.addFlashAttribute("message", "success");
				
				return "redirect:/";
			}
			
			else {
				model.addAttribute("user", user);
				redir.addFlashAttribute("message","tidakcocok");
				
				return "redirect:/";
			}
		}
		
		else {
			model.addAttribute("user", user);
			redir.addFlashAttribute("message","konfirmasi");
			
			return "redirect:/";

		}
	}
}
