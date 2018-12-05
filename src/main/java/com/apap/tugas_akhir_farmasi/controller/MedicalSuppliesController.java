package com.apap.tugas_akhir_farmasi.controller;

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
import com.apap.tugas_akhir_farmasi.service.service_interface.FlagUrgentService;
import com.apap.tugas_akhir_farmasi.service.service_interface.JenisMedicalSupplies;
import com.apap.tugas_akhir_farmasi.service.service_interface.MedicalSuppliesService;

@Controller

public class MedicalSuppliesController {
	@Autowired
	MedicalSuppliesService medicalSuppliesService;

	@Autowired
	FlagUrgentService flagUrgentService;

	@Autowired
	JenisMedicalSupplies jenisMedicalSuppliesService;

	@RequestMapping(value = "/medical-supplies/", method = RequestMethod.POST)
	private String addMedicalSubmit(@ModelAttribute MedicalSuppliesModel medicalSupplies, Model model) {
		medicalSuppliesService.addMedicalSupplies(medicalSupplies);
		List<MedicalSuppliesModel> listMedSupplies = medicalSuppliesService.getAll();
		model.addAttribute("listMedSupplies", listMedSupplies);
		model.addAttribute("message", "Success");
		return "view-allmedsupplies";
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
	private String updateMedicalSubmit(@PathVariable(value = "idMedicalSupplies") long id,
			@ModelAttribute MedicalSuppliesModel newMedicalSuppliesModel, Model model) {
		medicalSuppliesService.updateMedicalSupplies(newMedicalSuppliesModel, id);
		return "sukses";
	}
	
	@RequestMapping(value="/medical-supplies/", method=RequestMethod.GET)
	private String viewAllMedicalSupplies(@ModelAttribute MedicalSuppliesModel medSupplies, Model model) {
		List<MedicalSuppliesModel> listMedSupplies = medicalSuppliesService.getAll();
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
