package com.apap.tugas_akhir_farmasi.service.service_interface;
import java.util.List;

import com.apap.tugas_akhir_farmasi.model.PermintaanModel;

public interface PermintaanService {
	PermintaanModel save(PermintaanModel permintaan);
	List<PermintaanModel> getAllPermintaan();
}
