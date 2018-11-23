package com.apap.tugas_akhir_farmasi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
 
	@RequestMapping("/")
	private String home(Model model) {
		model.addAttribute("title", "Home");
		return "home";
	}
}
