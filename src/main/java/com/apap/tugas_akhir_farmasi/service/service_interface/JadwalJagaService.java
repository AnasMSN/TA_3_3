package com.apap.tugas_akhir_farmasi.service.service_interface;

import java.sql.Date;
import java.sql.Time;

import com.apap.tugas_akhir_farmasi.model.JadwalJagaModel;

import java.util.List;

public interface JadwalJagaService {
	JadwalJagaModel getJadwalJagaDetailsById(long id);

	JadwalJagaModel getJadwalJagaNow();

	List<JadwalJagaModel> findAll();

	List<JadwalJagaModel> findByStaffId(int id);

	void add(JadwalJagaModel jadwalJagaModel);
}
