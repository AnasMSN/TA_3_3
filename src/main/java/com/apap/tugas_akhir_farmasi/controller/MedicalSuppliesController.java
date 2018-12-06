package com.apap.tugas_akhir_farmasi.controller;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.apap.tugas_akhir_farmasi.model.FlagUrgentModel;
import com.apap.tugas_akhir_farmasi.model.JenisMedicalSuppliesModel;
import com.apap.tugas_akhir_farmasi.model.MedicalSuppliesModel;
import com.apap.tugas_akhir_farmasi.model.PerencanaanModel;
import com.apap.tugas_akhir_farmasi.service.service_interface.FlagUrgentService;
import com.apap.tugas_akhir_farmasi.service.service_interface.JenisMedicalSupplies;
import com.apap.tugas_akhir_farmasi.service.service_interface.MedicalSuppliesService;
import com.apap.tugas_akhir_farmasi.service.service_interface.PerencanaanService;

@Controller

public class MedicalSuppliesController {
	@Autowired
	MedicalSuppliesService medicalSuppliesService;

	@Autowired
	FlagUrgentService flagUrgentService;

	@Autowired
	JenisMedicalSupplies jenisMedicalSuppliesService;
	
	@Autowired
	PerencanaanService perencanaanService;

	@RequestMapping(value = "/medical-supplies/sukses", method = RequestMethod.POST)
	private String addMedicalSubmit(@ModelAttribute MedicalSuppliesModel medicalSupplies, Model model) {
		medicalSuppliesService.addMedicalSupplies(medicalSupplies);
		return "sukses";
	}

	@RequestMapping(value = "/medical-supplies/tambah", method = RequestMethod.GET)
	public String add(Model model) {
		MedicalSuppliesModel medicalSupplies = new MedicalSuppliesModel();

		List<FlagUrgentModel> listFlag = flagUrgentService.getListFlagUrgent();
		List<JenisMedicalSuppliesModel> listJenisMedicalSupplies = jenisMedicalSuppliesService
				.getListJenisMedicalSupplies();

		model.addAttribute("medicalSupplies", medicalSupplies);
		model.addAttribute("listJenisMedicalSupplies", listJenisMedicalSupplies);

		return "addMedicalSupplies";
	}

	@RequestMapping(value = "/medical-supplies/ubah/{idMedicalSupplies}", method = RequestMethod.GET)
	public String update(@PathVariable(value = "idMedicalSupplies") long id, Model model) {
		MedicalSuppliesModel medicalSupplies = medicalSuppliesService.getDetailMedicalSuppliesById(id);
		List<JenisMedicalSuppliesModel> listJenisMedicalSupplies = jenisMedicalSuppliesService
				.getListJenisMedicalSupplies();
		for (int i=0 ; i<listJenisMedicalSupplies.size();i++) {

			if (medicalSupplies.getJenisMedicalSuppliesModel().getJenisMedicalSupplies().equals(listJenisMedicalSupplies.get(i).getJenisMedicalSupplies())) {
				listJenisMedicalSupplies.remove(i);
				break;
			}
		}
		model.addAttribute("listJenisMedicalSupplies", listJenisMedicalSupplies);
		model.addAttribute("medicalSupplies", medicalSupplies);

		return "updateMedicalSupplies";
	}
	
	@RequestMapping(value = "/medical-supplies/{idMedicalSupplies}/sukses", method = RequestMethod.POST)
	private String updatePilotSubmit(@PathVariable(value = "idMedicalSupplies") long id,
			@ModelAttribute MedicalSuppliesModel newMedicalSuppliesModel, Model model) {
		medicalSuppliesService.updateMedicalSupplies(newMedicalSuppliesModel, id);
		return "sukses";
	}
	
	@RequestMapping(value="/medical-supplies/", method=RequestMethod.GET)
	private String viewAllMedicalSupplies(@ModelAttribute MedicalSuppliesModel medSupplies, Model model) {
		List<MedicalSuppliesModel> listMedSupplies = medicalSuppliesService.getAll();
		
		/*Bagian Perencanaan*/
		
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
	    
	    model.addAttribute("perencanaan", perencanaan);
		model.addAttribute("date_now", date);
		model.addAttribute("medicalSupPerencanaan", medicalSupPerencanaan);
		
		/*Akhir Bagian Perencanaan*/
		
		
		model.addAttribute("listMedSupplies", listMedSupplies);
		model.addAttribute("title", "Daftar Medical Supplies");
		return "view-allmedsupplies";
	}
	
	@RequestMapping(value="/medical-supplies/{idMedicalSupplies}")
	private String viewMedicalSupplies(@PathVariable(value="idMedicalSupplies") long id, Model model) {
		MedicalSuppliesModel medSupplies = medicalSuppliesService.getMedicalSuppliesById(id);
		model.addAttribute("medSupplies", medSupplies);
		return "view-medsupplies";
	}

}
