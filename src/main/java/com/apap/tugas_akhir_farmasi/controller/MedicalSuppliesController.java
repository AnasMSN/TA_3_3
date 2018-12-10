package com.apap.tugas_akhir_farmasi.controller;


import com.apap.tugas_akhir_farmasi.model.*;
import com.apap.tugas_akhir_farmasi.rest.KebutuhanDetail;
import com.apap.tugas_akhir_farmasi.service.service_interface.*;
import com.apap.tugas_akhir_farmasi.web_service.Rest.Setting;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


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

	@Autowired
	PermintaanService permintaanService;
	
	@Autowired
	UserRoleService userRoleService;

	@Autowired
	@Qualifier(value="rest5")
	RestTemplate restTemplate5;
	
	@Primary
	@Bean
	public RestTemplate rest5() {
		return new RestTemplate();
	}


	@RequestMapping(value = "/medical-supplies/", method = RequestMethod.POST)
	private String addMedicalSubmit(@ModelAttribute MedicalSuppliesModel medicalSupplies, Model model, RedirectAttributes redir) {
		medicalSuppliesService.addMedicalSupplies(medicalSupplies);
		
		redir.addFlashAttribute("message", "Success");
		
		return "redirect:/medical-supplies/";
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
	
	@RequestMapping(value = "/medical-supplies/{idMedicalSupplies}", method = RequestMethod.POST)
	private String updateMedicalSuppliesSubmit(@PathVariable(value = "idMedicalSupplies") long id,
			@ModelAttribute MedicalSuppliesModel newMedicalSuppliesModel, Model model, RedirectAttributes redir) {
		medicalSuppliesService.updateMedicalSupplies(newMedicalSuppliesModel, id);
		System.out.println("masukk     fsa");
		redir.addFlashAttribute("message", "Success");
		String pathRedir = "redirect:/medical-supplies/"+id;
		return pathRedir;
	}
	
	@RequestMapping(value="/medical-supplies/", method=RequestMethod.GET)
	private String viewAllMedicalSupplies(@ModelAttribute MedicalSuppliesModel medSupplies, Model model) throws IOException{

		List<MedicalSuppliesModel> listMedSupplies = medicalSuppliesService.getAll();
		model.addAttribute("listMedSupplies", listMedSupplies);
		
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
	    // get api laboratorium untuk penampilan medical supplies
	    List<KebutuhanDetail> listKebutuhan = this.getLabKebutuhan();
	    
	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    UserRoleModel user = userRoleService.getUser(authentication.getName());
	    model.addAttribute("user", user.getRole());
	    model.addAttribute("listKebutuhanLab", listKebutuhan);
	    model.addAttribute("perencanaan", perencanaan);
		model.addAttribute("date_now", date);
		model.addAttribute("medicalSupPerencanaan", medicalSupPerencanaan);
		
		/*Akhir Bagian Perencanaan*/

		return "view-allmedsupplies";
	}
	
	@RequestMapping(value="/medical-supplies/{idMedicalSupplies}")
	private String viewMedicalSupplies(@PathVariable(value="idMedicalSupplies") long id, Model model) {
		MedicalSuppliesModel medSupplies = medicalSuppliesService.getMedicalSuppliesById(id);
		model.addAttribute("medSupplies", medSupplies);
		return "view-medsupplies";
	}
	

	@RequestMapping(value = "/rawat-jalan/obat/tambah/", method = RequestMethod.POST)
	private String addMedicalSupplyToRawatJalan(@RequestParam String nama, int jumlah, RedirectAttributes attributes, Model model) throws Exception {
		
		MedicalSuppliesModel medSupp = medicalSuppliesService.getMedicalSuppliesDetailsByNama(nama);
		if(jumlah < 1) {
			model.addAttribute("msg", "failed jumlah format");
		}
		else if(medSupp.getJumlah() < jumlah) {
			model.addAttribute("msg", "failed medSupp amount");
		}
		else {
			int status = medicalSuppliesService.addMedicalSuppliesToRawatJalan(medSupp, nama, jumlah);
			if (status == 200) {
				model.addAttribute("msg", "success");
			}
			else {
				model.addAttribute("msg", "failed");
			}
		}
		
		List<MedicalSuppliesModel> listMedSupplies = medicalSuppliesService.getAll();
		model.addAttribute("listMedSupplies", listMedSupplies);
		
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
	    // get api laboratorium untuk penampilan medical supplies
	    List<KebutuhanDetail> listKebutuhan = this.getLabKebutuhan();
	    
	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    UserRoleModel user = userRoleService.getUser(authentication.getName());
	    model.addAttribute("user", user.getRole());
	    model.addAttribute("listKebutuhanLab", listKebutuhan);
	    model.addAttribute("perencanaan", perencanaan);
		model.addAttribute("date_now", date);
		model.addAttribute("medicalSupPerencanaan", medicalSupPerencanaan);
		
		return "view-allmedsupplies";
	}

	//consumer SI Farmasi ambil kebutuhan obat lab
	public List<KebutuhanDetail> getLabKebutuhan() throws IOException{
	  String path = Setting.getLabKebutuhan;
	  String penyimpanan = restTemplate5.getForObject(path, String.class);
	  ObjectMapper mapper = new ObjectMapper();
	  
	  JsonNode jsonNodePenyimpanan = mapper.readTree(penyimpanan);
	  
	  String result = jsonNodePenyimpanan.get("result").toString();
	  List<KebutuhanDetail> hasil = mapper.readValue(result, 
			  new TypeReference<ArrayList<KebutuhanDetail>>() {}
				);
	  
	  return hasil;
	}


}
