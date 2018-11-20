package com.apap.tugas_akhir_farmasi.controller;

import com.apap.tugas_akhir_farmasi.service.service_interface.MedicalSuppliesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("medical-supplies")
public class MedicalSuppliesController {
    @Autowired
    private MedicalSuppliesService medicalSuppliesService;


}
