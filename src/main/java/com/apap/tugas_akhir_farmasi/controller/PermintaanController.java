package com.apap.tugas_akhir_farmasi.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import com.apap.tugas_akhir_farmasi.model.PermintaanModel;
import com.apap.tugas_akhir_farmasi.service.service_interface.PermintaanService;
import com.apap.tugas_akhir_farmasi.web_service.Rest.Setting;
import com.apap.tugas_akhir_farmasi.web_service.Rest.StaffDetail;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class PermintaanController {

	@Autowired
	PermintaanService permintaanService;
	
	@Autowired
	RestTemplate restTemplate;
	
	@Bean
	private RestTemplate rest() {
		return new RestTemplate();
	}
	
	@RequestMapping(value="/medical-supplies/permintaan/", method=RequestMethod.GET)
	private String viewDaftarPermintaan(Model model) throws Exception {
		List<PermintaanModel> listPermintaan = permintaanService.getAllPermintaan();
		model.addAttribute("listPermintaan", listPermintaan);
		
		String jsonResponse;
		ObjectMapper mapper = new ObjectMapper();
		JsonNode node;
		JsonNode result;
		long idStaff;
		StaffDetail staff;
		List<StaffDetail> listStaff = new ArrayList<StaffDetail>();
		for(int i = 0; i < listPermintaan.size(); i++) {
			idStaff = listPermintaan.get(i).getJadwalJagaModel().getId();
			jsonResponse = restTemplate.getForEntity(Setting.urlGenerator(String.valueOf(idStaff), Setting.getStafUrl), String.class).getBody();
			node = mapper.readTree(jsonResponse);
			result = node.get("result");
			staff = mapper.treeToValue(result, StaffDetail.class);
			listStaff.add(staff);
		}
		model.addAttribute("listStaff", listStaff);
		model.addAttribute("title", "Daftar Permintaan");
		return "view-daftar-permintaan";
	}
}