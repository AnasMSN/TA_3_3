package com.apap.tugas_akhir_farmasi.service_implementation;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apap.tugas_akhir_farmasi.model.PermintaanModel;
import com.apap.tugas_akhir_farmasi.repository.PermintaanDb;
import com.apap.tugas_akhir_farmasi.service.service_interface.PermintaanService;

@Service
@Transactional
public class PermintaanSericeImpl implements PermintaanService {
	@Autowired
	PermintaanDb permintaanDb;
	@Override
	public void save(PermintaanModel permintaan) {
		permintaanDb.save(permintaan);
	}
}
