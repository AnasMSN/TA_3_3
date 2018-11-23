package com.apap.tugas_akhir_farmasi.service.service_interface;

import com.apap.tugas_akhir_farmasi.model.JadwalJagaModel;

import java.util.List;

public interface JadwalJagaService {
	JadwalJagaModel getJadwalJagaDetailsById(long id);

	List<JadwalJagaModel> findAll();

	List<JadwalJagaModel> findByStaffId(int id);

	void add(JadwalJagaModel jadwalJagaModel);
}
