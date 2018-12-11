package com.apap.tugas_akhir_farmasi.controller;

import java.sql.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apap.tugas_akhir_farmasi.model.JadwalJagaModel;
import com.apap.tugas_akhir_farmasi.model.MedicalSuppliesModel;
import com.apap.tugas_akhir_farmasi.model.PermintaanModel;
import com.apap.tugas_akhir_farmasi.model.StatusPermintaanModel;
import com.apap.tugas_akhir_farmasi.service.service_interface.JadwalJagaService;
import com.apap.tugas_akhir_farmasi.service.service_interface.MedicalSuppliesService;
import com.apap.tugas_akhir_farmasi.service.service_interface.PermintaanService;
import com.apap.tugas_akhir_farmasi.service.service_interface.StatusPermintaanService;
import com.apap.tugas_akhir_farmasi.web_service.Rest.BaseResponse;

@RestController
@RequestMapping("/api")
public class ApiController {
	@Autowired
	StatusPermintaanService statusPermintaanService;
	@Autowired
	PermintaanService permintaanService;
	@Autowired
	JadwalJagaService jadwalJagaService;
	@Autowired
	MedicalSuppliesService medicalSuppliesService;
	
	@PostMapping(value = "/medical-supplies/permintaan/")
    public BaseResponse<PermintaanModel> addPermintaan(@RequestBody @Valid PermintaanModel permintaan,
    												  BindingResult bindingResult) {
        BaseResponse<PermintaanModel> response = new BaseResponse<PermintaanModel>();
        if (bindingResult.hasErrors()) {
            response.setStatus(500);
            response.setMessage("error data");
        } 
        else {
        	if(permintaan.getJumlahMedicalSupplies() < 1) {
        		response.setStatus(400);
        		response.setMessage("Bad Request. Format jumlah tidak boleh kurang dari 1.");
        		return response;
        	}
        	
        	MedicalSuppliesModel medicalSupplies = medicalSuppliesService.getMedicalSuppliesDetailsByNama(permintaan.getMedicalSuppliesModel().getNama());
        	if(medicalSupplies == null) {
        		response.setStatus(500);
        		response.setMessage("Medical supplies tidak ditemukan");
        		return response;
        	}
        	permintaan.setMedicalSuppliesModel(medicalSupplies);
        	
        	// Set tanggal permintaan hari ini
        	long millis=System.currentTimeMillis();
        	Date todayDate = new Date(millis);
        	permintaan.setTanggal(todayDate);
        	
        	//Set jadwal_jaga yang sedang berjaga
        	JadwalJagaModel jadwalJagaNow = jadwalJagaService.getJadwalJagaNow();
        	if (jadwalJagaNow == null) {
        		response.setStatus(500);
        		response.setMessage("Tidak aja jadwal jaga saat ini");
        		return response;
        	}
        	permintaan.setJadwalJagaModel(jadwalJagaNow);
        	
        	StatusPermintaanModel statusPermintaan = statusPermintaanService.getStatusPermintaanDetailByNama("pending");
        	permintaan.setStatusPermintaanModel(statusPermintaan);
        	
        	permintaan = permintaanService.save(permintaan);
        	
            response.setStatus(200);
            response.setMessage("success");
            response.setResult(permintaan);
        }
        return response;
    }
	
	@GetMapping(value="/daftar-medical-supplies")
	public BaseResponse<List<MedicalSuppliesModel>> daftarMedSupplies(){
		BaseResponse<List<MedicalSuppliesModel>> response = new BaseResponse<List<MedicalSuppliesModel>>();
		List<MedicalSuppliesModel> listMedSup = medicalSuppliesService.getAll();
		if(!listMedSup.isEmpty()) {
			response.setStatus(200);
			response.setMessage("Success");
			response.setResult(medicalSuppliesService.getAll());
		}
		else {
			response.setStatus(404);
			response.setMessage("Not Available");
		}
		return response;
	}
	

}
