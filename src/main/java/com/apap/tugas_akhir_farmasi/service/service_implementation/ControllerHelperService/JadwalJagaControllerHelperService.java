package com.apap.tugas_akhir_farmasi.service.service_implementation.ControllerHelperService;

import com.apap.tugas_akhir_farmasi.data_model.SingleEntityResponse;
import com.apap.tugas_akhir_farmasi.model.JadwalJagaModel;
import com.apap.tugas_akhir_farmasi.service.service_implementation.ScheduleValidatorService;
import com.apap.tugas_akhir_farmasi.service.service_interface.JadwalJagaService;
import com.apap.tugas_akhir_farmasi.web_service.Rest.Setting;
import org.springframework.ui.Model;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;

public class JadwalJagaControllerHelperService {

    public static void staffContent(Model model, JadwalJagaService jadwalJagaService, RestTemplate restTemplate){
        List<JadwalJagaModel> jadwalJagaModels = jadwalJagaService.findAll();
        HashMap<Long,String> mapper = new HashMap<>();
        HashMap<Long,String> timeMulai = new HashMap<>();
        HashMap<Long,String> timeSelesai = new HashMap<>();
        HashMap<Long,Boolean> editable = new HashMap<>();
        for (JadwalJagaModel jadwalJagaModel : jadwalJagaModels){
            String url = Setting.urlGenerator(Integer.toString(jadwalJagaModel.getIdStaff()),Setting.getStafUrl);
            SingleEntityResponse singleEntityResponse = restTemplate.getForObject(url,SingleEntityResponse.class);
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


    }


}
