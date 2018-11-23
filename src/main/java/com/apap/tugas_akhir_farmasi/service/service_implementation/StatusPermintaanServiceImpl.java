package com.apap.tugas_akhir_farmasi.service.service_implementation;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apap.tugas_akhir_farmasi.model.StatusPermintaanModel;
import com.apap.tugas_akhir_farmasi.repository.StatusPermintaanDb;
import com.apap.tugas_akhir_farmasi.service.service_interface.StatusPermintaanService;

@Service
@Transactional
public class StatusPermintaanServiceImpl implements StatusPermintaanService {
	@Autowired
	StatusPermintaanDb statusPermintaanDb;
	
	@Override
	public StatusPermintaanModel getStatusPermintaanDetailByNama(String nama) {
		return statusPermintaanDb.findByNama(nama);
	}

}
