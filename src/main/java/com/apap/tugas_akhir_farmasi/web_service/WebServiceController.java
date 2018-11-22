package com.apap.tugas_akhir_farmasi.web_service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apap.tugas_akhir_farmasi.model.MedicalSuppliesModel;
import com.apap.tugas_akhir_farmasi.service.service_interface.MedicalSuppliesService;

@RestController
@RequestMapping("/api")
public class WebServiceController {
	@Autowired
	private MedicalSuppliesService medSuppliesService;
	
	@GetMapping(value="/daftar-medical-supplies")
	public List<MedicalSuppliesModel> viewAllFlight(Model model){
		List<MedicalSuppliesModel> listFlight = medSuppliesService.getAll();
		return listFlight;
	}
	
}
