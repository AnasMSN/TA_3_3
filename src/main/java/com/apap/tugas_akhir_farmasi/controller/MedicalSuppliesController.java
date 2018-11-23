package com.apap.tugas_akhir_farmasi.controller;

import com.apap.tugas_akhir_farmasi.data_model.EntityResponse;
import com.apap.tugas_akhir_farmasi.data_model.SingleEntityResponse;
import com.apap.tugas_akhir_farmasi.data_model.TimeValidatorResponse;
import com.apap.tugas_akhir_farmasi.model.JadwalJagaModel;
import com.apap.tugas_akhir_farmasi.service.service_interface.JadwalJagaService;
import com.apap.tugas_akhir_farmasi.service.service_interface.MedicalSuppliesService;
import com.apap.tugas_akhir_farmasi.service_implementation.ScheduleValidatorService;
import com.apap.tugas_akhir_farmasi.web_service.Rest.Setting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/medical-supplies")
public class MedicalSuppliesController {
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

        //@TODO Delete , just for test
        System.err.println(staf);
        System.err.println(fieldMulai);
        System.err.println(fieldSelesai);
        //End of test range

        /*
        String url = Setting.urlGenerator(staf,Setting.getStafUrl);
        SingleEntityResponse entityResponse = restAppointment.getForObject(url,SingleEntityResponse.class);
        System.err.println(entityResponse.getStaf().getNama());*/

       jadwalJagaModel.setWaktuJaga(fieldMulai,fieldSelesai);
       jadwalJagaModel.setIdStaff(Integer.parseInt(staf));

        //Test

        System.err.println(jadwalJagaModel.getTanggal().toString());
        System.err.println(jadwalJagaModel.getWaktuMulai().toString());
        System.err.println(jadwalJagaModel.getWaktuSelesai().toString());

        //Test

        List<JadwalJagaModel> jagaStaffAll = jadwalJagaService.findByStaffId(jadwalJagaModel.getIdStaff());
        List<JadwalJagaModel> jadwalStaffMatchDate = new ArrayList<>();
        for (JadwalJagaModel jadwalJaga : jagaStaffAll){
            if (jadwalJaga.getTanggal().equals(jadwalJaga.getTanggal())){
                jadwalStaffMatchDate.add(jadwalJaga);
            }
        }

        TimeValidatorResponse response = ScheduleValidatorService.validate(jadwalJagaModel,jadwalStaffMatchDate);

        if (!response.isValid()){
            model.addAttribute("error-message",response.getErrorMessage());
            // return to error page
        }

        jadwalJagaService.add(jadwalJagaModel);

        return "home";
    }

    //@TODO fitur Melihat jadwal staf apoteker jaga
    @RequestMapping("/jadwal-staf")
    private String listAllJadwal(Model model){
        System.err.println("GET Mapping");
        return "home";
    }


}
