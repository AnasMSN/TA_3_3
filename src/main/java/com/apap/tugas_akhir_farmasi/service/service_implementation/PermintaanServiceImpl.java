package com.apap.tugas_akhir_farmasi.service.service_implementation;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apap.tugas_akhir_farmasi.model.PermintaanModel;
import com.apap.tugas_akhir_farmasi.model.StatusPermintaanModel;
import com.apap.tugas_akhir_farmasi.repository.PermintaanDb;
import com.apap.tugas_akhir_farmasi.service.service_interface.PermintaanService;

@Service
@Transactional
public class PermintaanServiceImpl implements PermintaanService{
	
	@Autowired
	PermintaanDb permintaanDb;

	@Override
	public List<PermintaanModel> findAll() {
		// TODO Auto-generated method stub
		return permintaanDb.findAll();
	}

  @Override
	public PermintaanModel save(PermintaanModel permintaan) {
		return permintaanDb.save(permintaan);
	}
  
  @Override
	public List<PermintaanModel> getAllPermintaan() {
		return permintaanDb.findAll();
	}

	@Override
	public PermintaanModel findById(Long idPermintaan) {
		// TODO Auto-generated method stub
		return permintaanDb.findById(idPermintaan).get();
	}

	@Override
	public void changeStatus(PermintaanModel permintaan, StatusPermintaanModel statusRequest) {
		// TODO Auto-generated method stub
		permintaan.setStatusPermintaanModel(statusRequest);
	}

	
}
