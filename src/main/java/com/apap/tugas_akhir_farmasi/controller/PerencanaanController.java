package com.apap.tugas_akhir_farmasi.controller;

import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.apap.tugas_akhir_farmasi.model.MedicalSuppliesModel;
import com.apap.tugas_akhir_farmasi.model.PerencanaanModel;
import com.apap.tugas_akhir_farmasi.model.PermintaanModel;
import com.apap.tugas_akhir_farmasi.model.StatusPermintaanModel;
import com.apap.tugas_akhir_farmasi.rest.BaseResponse;
import com.apap.tugas_akhir_farmasi.rest.KebutuhanDetail;
import com.apap.tugas_akhir_farmasi.service.service_interface.MedicalSuppliesService;
import com.apap.tugas_akhir_farmasi.service.service_interface.PerencanaanService;
import com.apap.tugas_akhir_farmasi.service.service_interface.PermintaanService;
import com.apap.tugas_akhir_farmasi.service.service_interface.StatusPermintaanService;
import com.apap.tugas_akhir_farmasi.web_service.Rest.Setting;


@Controller
public class PerencanaanController {

	@Autowired
	private PerencanaanService perencanaanService;
	
	@Autowired
	private MedicalSuppliesService medicalSuppliesService;
	

	
	public String perencanaan(Model model){
		List<MedicalSuppliesModel> medicalSupPerencanaan = null;
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
			medicalSupPerencanaan = medicalSuppliesService.findAll();
	    }
	    else {
	    	// get only all urgent medical supplies
			medicalSupPerencanaan = medicalSuppliesService.findByUrgent(); 
	    }
	    
	    // get api laboratorium untuk penampilan medical supplies
//	    BaseResponse<ArrayList<KebutuhanDetail>> baseResponse = this.getLabKebutuhan();
	    
//	    System.out.println(baseResponse.getResult());
	    model.addAttribute("perencanaan", perencanaan);
//	    model.addAttribute("listKebutuhanLab", baseResponse.getResult());
		model.addAttribute("date_now", date);
		model.addAttribute("medicalSupPerencanaan", medicalSupPerencanaan);
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
//		model.addAttribute("user", "Staf Apoteker");
		model.addAttribute("statusPlan", statusArraylist);
		return "tampilan-perencanaan";
	}
	
	@RequestMapping(value = "/medical-supplies/perencanaan/ganti-status", method = RequestMethod.POST)
	public String instansiSearch(@RequestParam(value = "id") Long id, 
			@RequestParam(value = "status") String status,
			@RequestParam(value = "jumlah") int jumlah){
		
		// ubah status berdasarkan status baru
		perencanaanService.setStatus(id, status, jumlah);
		
		return "redirect:/medical-supplies/perencanaan";
	}
	

	
	


}
