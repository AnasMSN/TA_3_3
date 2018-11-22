package com.apap.tugas_akhir_farmasi.controller;

import java.sql.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apap.tugas_akhir_farmasi.model.JadwalJagaModel;
import com.apap.tugas_akhir_farmasi.model.MedicalSuppliesModel;
import com.apap.tugas_akhir_farmasi.model.PermintaanModel;
import com.apap.tugas_akhir_farmasi.model.StatusPermintaanModel;
import com.apap.tugas_akhir_farmasi.rest.BaseResponse;
import com.apap.tugas_akhir_farmasi.service.service_interface.JadwalJagaService;
import com.apap.tugas_akhir_farmasi.service.service_interface.MedicalSuppliesService;
import com.apap.tugas_akhir_farmasi.service.service_interface.PermintaanService;
import com.apap.tugas_akhir_farmasi.service.service_interface.StatusPermintaanService;

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
        	System.out.println(bindingResult.toString());
            response.setStatus(500);
            response.setMessage("error data");
        } else {
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
        	permintaan.setJadwalJagaModel(jadwalJagaNow);
        	
        	StatusPermintaanModel statusPermintaan = statusPermintaanService.getStatusPermintaanDetailByNama("Pending");
        	permintaan.setStatusPermintaanModel(statusPermintaan);
        	
        	permintaan = permintaanService.save(permintaan);
        	
        	permintaan.getMedicalSuppliesModel().setJenisMedicalSuppliesModel(null);
        	
            response.setStatus(200);
            response.setMessage("success");
            response.setResult(permintaan);
        }
        return response;
    }
}
