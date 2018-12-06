package com.apap.tugas_akhir_farmasi.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.apap.tugas_akhir_farmasi.model.MedicalSuppliesModel;
import com.apap.tugas_akhir_farmasi.model.PermintaanModel;
import com.apap.tugas_akhir_farmasi.service.service_interface.PermintaanService;

@Controller
public class PermintaanController {

	@Autowired
	PermintaanService permintaanService;
	
	@RequestMapping(value="/medical-supplies/permintaan", method=RequestMethod.GET)
    private String viewDaftarPermintaan(Model model) {
		List<PermintaanModel> listPermintaan = permintaanService.getAllPermintaan();
		
		model.addAttribute("user", "Admin Farmasi");
		model.addAttribute("listPermintaan", listPermintaan);
		model.addAttribute("title", "Daftar Permintaan");
		return "view-daftar-permintaan";
	}
	
	@RequestMapping(value=" /medical-supplies/permintaan/ubah/{idPermintaan}", method=RequestMethod.POST)
    private String ubahPermintaan(@PathVariable String idPermintaan ,Model model) {
		List<PermintaanModel> listPermintaan = permintaanService.getAllPermintaan();
		
		model.addAttribute("listPermintaan", listPermintaan);
		model.addAttribute("title", "Daftar Permintaan");
		return "view-daftar-permintaan";
	}
}