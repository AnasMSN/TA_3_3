package com.apap.tugas_akhir_farmasi.service.service_implementation;

import com.apap.tugas_akhir_farmasi.data_model.TimeValidatorResponse;
import com.apap.tugas_akhir_farmasi.model.JadwalJagaModel;
import com.apap.tugas_akhir_farmasi.service.service_interface.JadwalJagaService;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class ScheduleValidatorService {



    public static TimeValidatorResponse validate(JadwalJagaModel target, List<JadwalJagaModel> allJadwal,JadwalJagaService jadwalJagaService){
        LocalTime localStart = target.getWaktuMulai().toLocalTime();
        LocalTime localFinish = target.getWaktuSelesai().toLocalTime();

        if (target.getId()!=0) {
            JadwalJagaModel targetFromDataBase = jadwalJagaService.getJadwalJagaDetailsById(target.getId());
            if (scheduleEquals(target,targetFromDataBase))
                return new TimeValidatorResponse(true,null);
        }

        if (!dateValidation(target))
            return new TimeValidatorResponse(false,"The Schedule is passed");

        if (!localStart.isBefore(localFinish))
            return new TimeValidatorResponse(false,"Time start is after time finish");

        if (overlapswithTotal(target,allJadwal)){
            return new TimeValidatorResponse(false,"The Schedule is overlaps with other schedule");
        }




        return new TimeValidatorResponse(true,null);
    }


    public static boolean overlapswithTotal(JadwalJagaModel target, List<JadwalJagaModel> allJadwal){
        for (JadwalJagaModel jadwalJagaModel : allJadwal){
            if (overlapswith(target,jadwalJagaModel))
                return true;
        }

        return false;
    }

    public static boolean overlapswith(JadwalJagaModel jadwalJagaModel1, JadwalJagaModel jadwalJagaModel2){
        LocalTime start1,start2,finish1,finish2;

        start1 = jadwalJagaModel1.getWaktuMulai().toLocalTime();
        finish1 = jadwalJagaModel1.getWaktuSelesai().toLocalTime();

        start2 = jadwalJagaModel2.getWaktuMulai().toLocalTime();
        finish2 = jadwalJagaModel2.getWaktuSelesai().toLocalTime();

        return (start1.isBefore(finish2)) && finish1.isAfter(start2);
    }

    public static boolean dateValidation(JadwalJagaModel jadwalJagaModel){
        LocalDate today = LocalDate.now();
        LocalDate jadwal = jadwalJagaModel.getTanggal().toLocalDate();
        return today.isBefore(jadwal);


    }

    public static boolean scheduleEquals(JadwalJagaModel jadwalJagaModel1,JadwalJagaModel jadwalJagaModel2){
        if (jadwalJagaModel1.getId()!=0 && jadwalJagaModel2.getId()!=0){
            return jadwalJagaModel1.getId()==jadwalJagaModel2.getId() &&
                    jadwalJagaModel1.getTanggal().equals(jadwalJagaModel2.getTanggal())&&
                    jadwalJagaModel1.getWaktuMulai().equals(jadwalJagaModel2.getWaktuMulai()) &&
                    jadwalJagaModel1.getWaktuSelesai().equals(jadwalJagaModel2.getWaktuSelesai());
        }
        return false;
    }


}
