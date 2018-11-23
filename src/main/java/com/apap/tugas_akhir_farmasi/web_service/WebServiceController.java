package com.apap.tugas_akhir_farmasi.web_service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.apap.tugas_akhir_farmasi.model.MedicalSuppliesModel;
import com.apap.tugas_akhir_farmasi.repository.MedicalSuppliesDb;
import com.apap.tugas_akhir_farmasi.rest.BaseResponse;
import com.apap.tugas_akhir_farmasi.service.service_interface.MedicalSuppliesService;

@RestController
@RequestMapping("/api")
public class WebServiceController {
	@Autowired
	private MedicalSuppliesDb medSupplies;
	
	@GetMapping(value="/daftar-medical-supplies")
	public BaseResponse<List<MedicalSuppliesModel>> daftarMedSupplies(){
		BaseResponse<List<MedicalSuppliesModel>> response = new BaseResponse<List<MedicalSuppliesModel>>();
		
		response.setStatus(200);
			response.setMessage("Success");
			response.setResult(medSupplies.findAll());
		return response;
	}
	
}
