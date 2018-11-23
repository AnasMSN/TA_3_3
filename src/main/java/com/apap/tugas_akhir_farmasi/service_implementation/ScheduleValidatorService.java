package com.apap.tugas_akhir_farmasi.service_implementation;

import com.apap.tugas_akhir_farmasi.data_model.TimeValidatorResponse;
import com.apap.tugas_akhir_farmasi.model.JadwalJagaModel;

import java.sql.Time;
import java.time.LocalTime;
import java.util.List;

public class ScheduleValidatorService {

    public static TimeValidatorResponse validate(JadwalJagaModel target, List<JadwalJagaModel> allJadwal){
        LocalTime localStart = target.getWaktuMulai().toLocalTime();
        LocalTime localFinish = target.getWaktuSelesai().toLocalTime();


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
}
