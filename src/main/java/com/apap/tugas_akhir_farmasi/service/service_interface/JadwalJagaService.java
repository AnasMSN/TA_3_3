package com.apap.tugas_akhir_farmasi.service.service_interface;

import java.sql.Date;
import java.sql.Time;

import com.apap.tugas_akhir_farmasi.model.JadwalJagaModel;

public interface JadwalJagaService {
	JadwalJagaModel getJadwalJagaDetailsById(long id);
	JadwalJagaModel getJadwalJagaNow();
}
