package com.apap.tugas_akhir_farmasi.service_implementation;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apap.tugas_akhir_farmasi.controller.PerencanaanController;
import com.apap.tugas_akhir_farmasi.model.PerencanaanModel;
import com.apap.tugas_akhir_farmasi.repository.PerencanaanDb;
import com.apap.tugas_akhir_farmasi.service.service_interface.PerencanaanService;

@Service
@Transactional
public class PerencanaanServiceImpl implements PerencanaanService{

	@Autowired
	PerencanaanDb perencanaanDb;

	@Override
	public List<PerencanaanModel> findAll() {
		// TODO Auto-generated method stub
		return perencanaanDb.findAll();
	}

	@Override
	public void add(PerencanaanModel perencanaan) {
		// TODO Auto-generated method stub
		perencanaanDb.save(perencanaan);
	}
	
}
