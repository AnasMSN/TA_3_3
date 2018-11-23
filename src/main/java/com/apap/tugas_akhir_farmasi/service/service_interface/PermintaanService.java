package com.apap.tugas_akhir_farmasi.service.service_interface;
import com.apap.tugas_akhir_farmasi.model.PermintaanModel;

import java.util.List;

import com.apap.tugas_akhir_farmasi.model.PermintaanModel;

public interface PermintaanService {

	List<PermintaanModel> findAll();
	void save(PermintaanModel permintaan);
}
