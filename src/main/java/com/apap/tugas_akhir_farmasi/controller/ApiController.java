package com.apap.tugas_akhir_farmasi.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apap.tugas_akhir_farmasi.model.PermintaanModel;
import com.apap.tugas_akhir_farmasi.model.StatusPermintaanModel;
import com.apap.tugas_akhir_farmasi.rest.BaseResponse;
import com.apap.tugas_akhir_farmasi.service.service_interface.PermintaanService;
import com.apap.tugas_akhir_farmasi.service.service_interface.StatusPermintaanService;

@RestController
@RequestMapping("/api")
public class ApiController {
	@Autowired
	StatusPermintaanService statusPermintaanService;
	
	@Autowired
	PermintaanService permintaanService;
	
	@PostMapping(value = "/medical-supplies/permintaan/")
    public BaseResponse<PermintaanModel> addPermintaan(@RequestBody @Valid PermintaanModel permintaan,
    												  BindingResult bindingResult) {
        BaseResponse<PermintaanModel> response = new BaseResponse<PermintaanModel>();
        if (bindingResult.hasErrors()) {
            response.setStatus(500);
            response.setMessage("error data");
        } else {
        	StatusPermintaanModel statusPermintaan = statusPermintaanService.getStatusPermintaanDetailByNama("pending");
        	permintaan.setStatusPermintaanModel(statusPermintaan);
        	permintaanService.save(permintaan);
            response.setStatus(200);
            response.setMessage("success");
            response.setResult(permintaan);
        }
        return response;
    }
}
