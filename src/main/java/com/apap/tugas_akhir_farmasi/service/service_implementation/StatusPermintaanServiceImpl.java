package com.apap.tugas_akhir_farmasi.service.service_implementation;

import java.util.List;

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

	@Override
	public List<StatusPermintaanModel> findAll() {
		// TODO Auto-generated method stub
		return statusPermintaanDb.findAll();
	}

	@Override
	public StatusPermintaanModel findById(int status) {
		// TODO Auto-generated method stub
		return statusPermintaanDb.findById(status);
	}

}
