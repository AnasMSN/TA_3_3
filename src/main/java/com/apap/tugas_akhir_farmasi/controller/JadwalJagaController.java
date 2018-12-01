package com.apap.tugas_akhir_farmasi.controller;

import com.apap.tugas_akhir_farmasi.data_model.EntityResponse;
import com.apap.tugas_akhir_farmasi.data_model.SingleEntityResponse;
import com.apap.tugas_akhir_farmasi.data_model.TimeValidatorResponse;
import com.apap.tugas_akhir_farmasi.model.JadwalJagaModel;
import com.apap.tugas_akhir_farmasi.service.service_interface.JadwalJagaService;
import com.apap.tugas_akhir_farmasi.service.service_interface.MedicalSuppliesService;
import com.apap.tugas_akhir_farmasi.service.service_implementation.ScheduleValidatorService;
import com.apap.tugas_akhir_farmasi.web_service.Rest.Setting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/medical-supplies")
public class JadwalJagaController {
    @Autowired
    private MedicalSuppliesService medicalSuppliesService;

    @Autowired
    private JadwalJagaService jadwalJagaService;

    @Autowired
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

       jadwalJagaModel.setWaktuJaga(fieldMulai,fieldSelesai);
       jadwalJagaModel.setIdStaff(Integer.parseInt(staf));

        List<JadwalJagaModel> jagaStaffAll = jadwalJagaService.findByStaffId(jadwalJagaModel.getIdStaff());
        List<JadwalJagaModel> jadwalStaffMatchDate = new ArrayList<>();
        for (JadwalJagaModel jadwalJaga : jagaStaffAll){
            if (jadwalJaga.getTanggal().equals(jadwalJaga.getTanggal())){
                jadwalStaffMatchDate.add(jadwalJaga);
            }
        }

        TimeValidatorResponse response = ScheduleValidatorService.validate(jadwalJagaModel,jadwalStaffMatchDate);

        if (!response.isValid()){
            System.err.println(response.getErrorMessage());
            model.addAttribute("errorMessage",response.getErrorMessage());
            return "error-page";
        }


        jadwalJagaService.add(jadwalJagaModel);

        return "jadwal-jaga-all";
    }


    @RequestMapping("/jadwal-staf")
    private String listAllJadwal(Model model){
        List<JadwalJagaModel> jadwalJagaModels = jadwalJagaService.findAll();
        HashMap<Long,String> mapper = new HashMap<>();
        HashMap<Long,String> timeMulai = new HashMap<>();
        HashMap<Long,String> timeSelesai = new HashMap<>();
        HashMap<Long,Boolean> editable = new HashMap<>();
        for (JadwalJagaModel jadwalJagaModel : jadwalJagaModels){
            String url = Setting.urlGenerator(Integer.toString(jadwalJagaModel.getIdStaff()),Setting.getStafUrl);
            SingleEntityResponse singleEntityResponse = restAppointment.getForObject(url,SingleEntityResponse.class);
            mapper.put(jadwalJagaModel.getId(),singleEntityResponse.getStaf().getNama());
            timeMulai.put(jadwalJagaModel.getId(),jadwalJagaModel.mulai());
            timeSelesai.put(jadwalJagaModel.getId(),jadwalJagaModel.selesai());
            editable.put(jadwalJagaModel.getId(),ScheduleValidatorService.dateValidation(jadwalJagaModel));
        }

        model.addAttribute("jadwalJaga",jadwalJagaModels);
        model.addAttribute("stafMap",mapper);
        model.addAttribute("mulai",timeMulai);
        model.addAttribute("selesai",timeSelesai);
        model.addAttribute("status",editable);

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
