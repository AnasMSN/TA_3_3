package com.apap.tugas_akhir_farmasi.controller;

import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.apap.tugas_akhir_farmasi.model.MedicalSuppliesModel;
import com.apap.tugas_akhir_farmasi.model.PerencanaanModel;
import com.apap.tugas_akhir_farmasi.model.PermintaanModel;
import com.apap.tugas_akhir_farmasi.service.service_interface.MedicalSuppliesService;
import com.apap.tugas_akhir_farmasi.service.service_interface.PerencanaanService;
import com.apap.tugas_akhir_farmasi.service.service_interface.PermintaanService;


@Controller
public class PerencanaanController {

	@Autowired
	private PerencanaanService perencanaanService;
	
	@Autowired
	private MedicalSuppliesService medicalSuppliesService;
	
	
	@RequestMapping(value = "/medical-supplies", method = RequestMethod.GET)
	public String perencanaan(Model model){
		List<MedicalSuppliesModel> medicalSup = null;
		PerencanaanModel perencanaan = new PerencanaanModel();
		
		// get date now
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  
		java.util.Date utilDate = new java.util.Date();
		Date date = new Date(utilDate.getTime());
		perencanaan.setTanggal(date);
	    
	    String tanggalHariIni = formatter.format(date);
		
	    String day = tanggalHariIni.substring(tanggalHariIni.length()-2, tanggalHariIni.length());
	    
	    if ((Integer.parseInt(day) >= 1 && Integer.parseInt(day) <= 7) || 
	    		(Integer.parseInt(day) >= 15 && Integer.parseInt(day) <= 21)  ) {
	    	// get all medical supplies
			medicalSup = medicalSuppliesService.findAll();
	    }
	    else {
	    	// get only all urgent medical supplies
			medicalSup = medicalSuppliesService.findByUrgent(); 
	    }
	    
	    model.addAttribute("perencanaan", perencanaan);
		model.addAttribute("date_now", date);
		model.addAttribute("medicalSup", medicalSup);
		return "medical-sup";
	}
	
	@RequestMapping(value = "/medical-supplies/perencanaan/tambah", method = RequestMethod.POST)
	public String tambahPerencanaan(@ModelAttribute PerencanaanModel perencanaan){
		perencanaan.setStatus("diajukan");
		perencanaanService.add(perencanaan);
		
		return "redirect:/medical-supplies/perencanaan";
	}
	
	@RequestMapping(value = "/medical-supplies/perencanaan")
	public String tampilanPerencanaan(Model model){
		List<PerencanaanModel> listPlan = perencanaanService.findAll();
		
		// make status list
		String[] statusArray = {"diajukan", "diproses", "tersedia"};
		List<String> statusArraylist = Arrays.asList(statusArray);

		
		model.addAttribute("listPlan", listPlan);
		model.addAttribute("user", "Admin Farmasi");
		model.addAttribute("user", "Staf Apoteker");
//		model.addAttribute("statusPlan", statusArraylist);
		return "tampilan-perencanaan";
	}
	
	@RequestMapping(value = "/medical-supplies/perencanaan/ganti-status", method = RequestMethod.POST)
	public String instansiSearch(@RequestParam(value = "id") Long id, 
			@RequestParam(value = "status") String status){
		
		// ubah status berdasarkan status baru
		perencanaanService.setStatus(id, status);
		
		return "redirect:/medical-supplies/perencanaan";
	}
	
	
	
	
}