package com.apap.tugas_akhir_farmasi.controller;

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
            response.setStatus(500);
            response.setMessage("error data");
        } else {
        	
        	JadwalJagaModel jadwalJaga = jadwalJagaService.getJadwalJagaDetailsById(permintaan.getJadwalJagaModel().getId());
        	permintaan.setJadwalJagaModel(jadwalJaga);
        	
        	MedicalSuppliesModel medicalSupplies = medicalSuppliesService.getMedicalSuppliesDetailsByNama(permintaan.getMedicalSuppliesModel().getNama());
        	permintaan.setMedicalSuppliesModel(medicalSupplies); 
        	
        	StatusPermintaanModel statusPermintaan = statusPermintaanService.getStatusPermintaanDetailByNama("Pending");
        	permintaan.setStatusPermintaanModel(statusPermintaan);
        	
        	permintaanService.save(permintaan);
        	
        	permintaan.getMedicalSuppliesModel().setJenisMedicalSuppliesModel(null);
        	
            response.setStatus(200);
            response.setMessage("success");
            response.setResult(permintaan);
        }
        return response;
    }
}
