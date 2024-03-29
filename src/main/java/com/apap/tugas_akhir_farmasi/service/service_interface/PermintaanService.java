package com.apap.tugas_akhir_farmasi.service.service_interface;
import java.util.List;

import com.apap.tugas_akhir_farmasi.model.PermintaanModel;
import com.apap.tugas_akhir_farmasi.model.StatusPermintaanModel;

public interface PermintaanService {
	PermintaanModel save(PermintaanModel permintaan);
	List<PermintaanModel> getAllPermintaan();
	List<PermintaanModel> findAll();
	PermintaanModel findById(Long idPermintaan);
	void changeStatus(PermintaanModel permintaan, StatusPermintaanModel statusRequest);
}
