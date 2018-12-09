package com.apap.tugas_akhir_farmasi.service.service_interface;

import java.util.List;

import com.apap.tugas_akhir_farmasi.model.JadwalJagaModel;

public interface JadwalJagaService {
	JadwalJagaModel getJadwalJagaDetailsById(long id);

	JadwalJagaModel getJadwalJagaNow();

	List<JadwalJagaModel> findAll();

	List<JadwalJagaModel> findByStaffId(int id);

	void add(JadwalJagaModel jadwalJagaModel);
}
