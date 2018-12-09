package com.apap.tugas_akhir_farmasi.service.service_interface;

import java.util.List;

import com.apap.tugas_akhir_farmasi.model.PerencanaanModel;

public interface PerencanaanService {

	List<PerencanaanModel> findAll();

	void add(PerencanaanModel perencanaan);

	PerencanaanModel findById(Long id);

	void setStatus(Long id, String status, int jumlah);

}
