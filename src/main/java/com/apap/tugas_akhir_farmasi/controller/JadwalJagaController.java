package com.apap.tugas_akhir_farmasi.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.apap.tugas_akhir_farmasi.service.service_implementation.ControllerHelperService.JadwalJagaControllerHelperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import com.apap.tugas_akhir_farmasi.data_model.EntityResponse;
import com.apap.tugas_akhir_farmasi.data_model.SingleEntityResponse;
import com.apap.tugas_akhir_farmasi.data_model.TimeValidatorResponse;
import com.apap.tugas_akhir_farmasi.model.JadwalJagaModel;
import com.apap.tugas_akhir_farmasi.service.service_implementation.ScheduleValidatorService;
import com.apap.tugas_akhir_farmasi.service.service_interface.JadwalJagaService;
import com.apap.tugas_akhir_farmasi.service.service_interface.MedicalSuppliesService;
import com.apap.tugas_akhir_farmasi.web_service.Rest.Setting;

@Controller
@RequestMapping("/medical-supplies")
public class JadwalJagaController {
    @Autowired
    private MedicalSuppliesService medicalSuppliesService;

    @Autowired
    private JadwalJagaService jadwalJagaService;

    @Autowired
    @Qualifier(value="restAppointment")
    RestTemplate restAppointment;

    @Bean
    public RestTemplate restAppointment(){
        return new RestTemplate();
    }

    @RequestMapping("/jadwal-staf/tambah")
    private String tambahJadwalJagaApoteker(Model model){
        String url = Setting.urlGenerator("3",Setting.stafFarmasiUrl);
        EntityResponse stafResponse = restAppointment.getForObject(url, EntityResponse.class);
        JadwalJagaModel jadwalJagaModel = new JadwalJagaModel();
        model.addAttribute("staffList",stafResponse.getStaf());
        model.addAttribute("jadwalJaga",jadwalJagaModel);
        return "jadwal-staf-tambah";
    }

    @PostMapping("/jadwal-staf")
    private String addJadwalJagaApoteker(@ModelAttribute JadwalJagaModel jadwalJagaModel,HttpServletRequest request,Model model){
        String staf = request.getParameter("staf");
        String fieldMulai = request.getParameter("waktuMulaiField");
        String fieldSelesai = request.getParameter("waktuSelesaiField");

        if (staf==null || fieldMulai==null || fieldSelesai==null){
            String response = "Data can't be processed due a null field";
            model.addAttribute("errorMessage",response);
            return "error-page";
        }

       jadwalJagaModel.setWaktuJaga(fieldMulai,fieldSelesai);
       jadwalJagaModel.setIdStaff(Integer.parseInt(staf));

        List<JadwalJagaModel> jagaStaffAll = jadwalJagaService.findAll();
        List<JadwalJagaModel> jadwalStaffMatchDate = new ArrayList<>();
        for (JadwalJagaModel jadwalJaga : jagaStaffAll){
            if (jadwalJaga.getTanggal().equals(jadwalJagaModel.getTanggal())){
                jadwalStaffMatchDate.add(jadwalJaga);
            }
        }

        TimeValidatorResponse response = ScheduleValidatorService.validate(jadwalJagaModel,jadwalStaffMatchDate,jadwalJagaService);

        if (!response.isValid()){
            model.addAttribute("errorMessage",response.getErrorMessage());
            return "error-page";
        }


        jadwalJagaService.add(jadwalJagaModel);

        JadwalJagaControllerHelperService.staffContent(model,jadwalJagaService,restAppointment);
        model.addAttribute("successMessage","Data berhasil di tambahkan");
        return "jadwal-jaga-all";
    }


    @RequestMapping("/jadwal-staf")
    private String listAllJadwal(Model model){
        JadwalJagaControllerHelperService.staffContent(model,jadwalJagaService,restAppointment);
        return "jadwal-jaga-all";
    }
    @RequestMapping(value = "/jadwal-staf/{idJadwalStaf}")
    private String updateJadwalJaga(@PathVariable(name = "idJadwalStaf")long id,Model model){
        String url = Setting.urlGenerator("3",Setting.stafFarmasiUrl);
        EntityResponse stafResponse = restAppointment.getForObject(url, EntityResponse.class);
        JadwalJagaModel jadwalJagaModel = jadwalJagaService.getJadwalJagaDetailsById(id);
        String urlStaf = Setting.urlGenerator(Integer.toString(jadwalJagaModel.getIdStaff()),Setting.getStafUrl);
        SingleEntityResponse singleEntityResponse = restAppointment.getForObject(urlStaf,SingleEntityResponse.class);
        model.addAttribute("stafJagaSekarang",singleEntityResponse.getStaf());
        model.addAttribute("staffList",stafResponse.getStaf());
        model.addAttribute("jadwalJaga",jadwalJagaModel);


        return "jadwal-staf-tambah";

    }


}
