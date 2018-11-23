package com.apap.tugas_akhir_farmasi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.apap.tugas_akhir_farmasi.model.MedicalSuppliesModel;
import com.apap.tugas_akhir_farmasi.service.service_interface.MedicalSuppliesService;

@Controller
public class MedicalSuppliesController {
	@Autowired
	public MedicalSuppliesService medSuppliesService;
	
	@RequestMapping(value="/medical-supplies/", method=RequestMethod.GET)
	private String viewAllMedicalSupplies(@ModelAttribute MedicalSuppliesModel medSupplies, Model model) {
		List<MedicalSuppliesModel> listMedSupplies = medSuppliesService.getAll();
		model.addAttribute("listMedSupplies", listMedSupplies);
		model.addAttribute("title", "Daftar Medical Supplies");
		return "view-allmedsupplies";
	}

}
