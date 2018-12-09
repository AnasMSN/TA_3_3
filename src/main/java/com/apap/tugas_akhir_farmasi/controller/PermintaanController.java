package com.apap.tugas_akhir_farmasi.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.apap.tugas_akhir_farmasi.model.PermintaanModel;
import com.apap.tugas_akhir_farmasi.model.StatusPermintaanModel;
import com.apap.tugas_akhir_farmasi.model.UserRoleModel;
import com.apap.tugas_akhir_farmasi.service.service_interface.PermintaanService;
import com.apap.tugas_akhir_farmasi.service.service_interface.StatusPermintaanService;
import com.apap.tugas_akhir_farmasi.service.service_interface.UserRoleService;
import com.apap.tugas_akhir_farmasi.web_service.Rest.BaseResponse;
import com.apap.tugas_akhir_farmasi.web_service.Rest.Setting;
import com.apap.tugas_akhir_farmasi.web_service.Rest.StaffDetail;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class PermintaanController {

	@Autowired
	PermintaanService permintaanService;
	
	@Autowired
	private StatusPermintaanService statusPermintaanService;

	@Autowired
	private UserRoleService userRoleService;
	
	@Autowired
    @Qualifier(value="rest3")
	RestTemplate restTemplate;
	
	@Primary
	@Bean
	public RestTemplate rest3() {
		return new RestTemplate();
	}
	
	@RequestMapping(value="/medical-supplies/permintaan/", method=RequestMethod.GET)
	private String viewDaftarPermintaan(Model model) throws Exception {
		List<PermintaanModel> listPermintaan = permintaanService.getAllPermintaan();
		// get status permintaan
		List<StatusPermintaanModel> statusRequest = statusPermintaanService.findAll();
		
		/*Untuk edit status*/
		// ambil role dari user
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    UserRoleModel user = userRoleService.getUser(authentication.getName());
	    model.addAttribute("user", user.getRole());
		
		model.addAttribute("statusRequest", statusRequest);
		model.addAttribute("listPermintaan", listPermintaan);
		
		/*Akhir untuk edit status*/
		
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
	
	@RequestMapping(value="/medical-supplies/permintaan/ubah/{idPermintaan}", method=RequestMethod.POST)
    private String ubahStatusPermintaan(@PathVariable Long idPermintaan,
    		@RequestParam("idPasien") int idPasien,
    		@RequestParam("jumlah") int jumlah,
    		@RequestParam("status") int status,
    		RedirectAttributes redir) {
		PermintaanModel permintaan = permintaanService.findById(idPermintaan);
		
		// untuk pengecekan status dari permintaan yang akan diubah
		StatusPermintaanModel statusRequest = statusPermintaanService.findById(status);
		
		BaseResponse<String> baseResponse = null;
		
		
		if (!permintaan.getStatusPermintaanModel().getNama().equals("diterima") &&
				statusRequest.getNama().equals("diterima")) {
			baseResponse = this.sendAddBilling(jumlah, idPasien);
			
		}
		
		permintaanService.changeStatus(permintaan, statusRequest);
		
		if (baseResponse != null && baseResponse.getStatus() == 200) {
			redir.addFlashAttribute("message", "berhasil");
		}
		else if (baseResponse != null && baseResponse.getStatus() == 500) {
			redir.addFlashAttribute("message", "gagal");
		}
		else {
			redir.addFlashAttribute("message", "noChange");
		}
		
		return "redirect:/medical-supplies/permintaan/";
	}
	
	//consumer SI Farmasi untuk request obat
		public BaseResponse<String> sendAddBilling(int jumlahTagihan, int idPasien) {
		  String path = Setting.addBilling;
	   
		  LinkedHashMap<String, Object> toSend = new LinkedHashMap<String,Object>();
	   

		  toSend.put("jumlahTagihan", jumlahTagihan);
		  
		  SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  
		  java.util.Date utilDate = new java.util.Date();
		  Date date = new Date(utilDate.getTime());
		  String tanggalTagihan = formatter.format(date);
		  
		  toSend.put("tanggalTagihan", tanggalTagihan);
		  
		  LinkedHashMap<String, Object> pasien = new LinkedHashMap<String,Object>();
		  pasien.put("id", idPasien);
		  toSend.put("pasien", pasien);
	   
	   
		  JSONObject json = new JSONObject(toSend);
	   
		  HttpHeaders headers = new HttpHeaders();
	   
		  headers.setContentType(MediaType.APPLICATION_JSON);
	   
		  HttpEntity<String> request = new HttpEntity<String>(json.toString(), headers);
	   
		  return restTemplate.postForEntity(path, request, BaseResponse.class).getBody();
		}
	
}