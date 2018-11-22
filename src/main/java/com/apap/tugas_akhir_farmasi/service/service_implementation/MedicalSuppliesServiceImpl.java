package com.apap.tugas_akhir_farmasi.service.service_implementation;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apap.tugas_akhir_farmasi.model.MedicalSuppliesModel;
import com.apap.tugas_akhir_farmasi.repository.MedicalSuppliesDb;
import com.apap.tugas_akhir_farmasi.service.service_interface.MedicalSuppliesService;

@Service
@Transactional
public class MedicalSuppliesServiceImpl implements MedicalSuppliesService{
	@Autowired
	private MedicalSuppliesDb medSuppliesDb;

	@Override
	public List<MedicalSuppliesModel> getAll() {
		// TODO Auto-generated method stub
		return medSuppliesDb.findAll();
	}

	@Override
	public MedicalSuppliesModel getMedicalSuppliesById(long id) {
		// TODO Auto-generated method stub
		return medSuppliesDb.findById(id);
	}
	
	
}
