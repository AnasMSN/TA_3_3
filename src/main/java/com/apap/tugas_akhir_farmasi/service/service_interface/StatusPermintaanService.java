package com.apap.tugas_akhir_farmasi.service.service_interface;
import java.util.List;

import com.apap.tugas_akhir_farmasi.model.StatusPermintaanModel;

public interface StatusPermintaanService {
	StatusPermintaanModel getStatusPermintaanDetailByNama(String nama);

	List<StatusPermintaanModel> findAll();

	StatusPermintaanModel findById(int status);
}
