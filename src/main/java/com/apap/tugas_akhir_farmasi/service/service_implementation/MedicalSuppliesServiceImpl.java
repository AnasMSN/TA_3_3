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
public class MedicalSuppliesServiceImpl implements MedicalSuppliesService {
	@Autowired
	MedicalSuppliesDb medicalSuppliesDb;
	
	@Override
	public MedicalSuppliesModel getMedicalSuppliesDetailsByNama(String nama) {
		return medicalSuppliesDb.findByNama(nama);
	}
	
	@Override
	public List<MedicalSuppliesModel> getAll() {
		// TODO Auto-generated method stub
		return medicalSuppliesDb.findAll();
	}
}
