package com.apap.tugas_akhir_farmasi.controller;

import com.apap.tugas_akhir_farmasi.data_model.Staf;
import com.apap.tugas_akhir_farmasi.data_model.StafResponse;
import com.apap.tugas_akhir_farmasi.model.JadwalJagaModel;
import com.apap.tugas_akhir_farmasi.service.service_interface.MedicalSuppliesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Controller
@RequestMapping("/medical-supplies")
public class MedicalSuppliesController {
    @Autowired
    private MedicalSuppliesService medicalSuppliesService;

    @Autowired
    RestTemplate restAppointment;

    @Bean
    public RestTemplate restAppointment(){
        return new RestTemplate();
    }

    @RequestMapping("/jadwal-staf/tambah")
    private String tambahJadwalJagaApoteker(Model model){
        HttpHeaders headers = new HttpHeaders();

        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        Staf objArticle = new Staf();

        HttpEntity<Staf> requestEntity = new HttpEntity<Staf>(objArticle, headers);
        System.err.println(requestEntity.getBody().getNama());

        StafResponse stafResponse = restAppointment.getForObject("http://si-appointment.herokuapp.com/api/getStaff/1", StafResponse.class);
        System.err.println(restAppointment.getForEntity("http://si-appointment.herokuapp.com/api/getStaff/1",StaffModel.class).getBody());
//        System.err.println(staffModel.getId());
        /*ResponseEntity<List<StaffModel>> responseEntity  = restAppointment.exchange(
                "http://si-appointment.herokuapp.com/api/getStaff",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<StaffModel>>(){}
        );
        List<StaffModel> staffModels = responseEntity.getBody();
        for (StaffModel staffModel : staffModels){
            System.err.println(staffModel);
        }*/
        return "jadwal-staf-tambah";
    }

    @PostMapping("/jadwal-staf")
    private String addJadwalJagaApoteker(@ModelAttribute JadwalJagaModel jadwalJagaModel){
        return "success";
    }


}
