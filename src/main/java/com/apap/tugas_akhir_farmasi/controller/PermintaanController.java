package com.apap.tugas_akhir_farmasi.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.apap.tugas_akhir_farmasi.model.MedicalSuppliesModel;
import com.apap.tugas_akhir_farmasi.model.PermintaanModel;
import com.apap.tugas_akhir_farmasi.service.service_interface.PermintaanService;

@Controller
public class PermintaanController {
	
	
	@Autowired
	PermintaanService permintaanService;
	
	@RequestMapping(value = "/medical-supplies/permintaan", method = RequestMethod.GET)
	public String tampilanPermintaan(Model model){
		// get all permintaanS
		List<PermintaanModel> requestList = permintaanService.findAll();
		
		model.addAttribute("requestList", requestList);
		return "permintaan";
	}
	
	
	

}
