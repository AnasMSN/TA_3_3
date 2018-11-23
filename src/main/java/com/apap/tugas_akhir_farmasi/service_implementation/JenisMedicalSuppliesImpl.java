package com.apap.tugas_akhir_farmasi.service_implementation;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apap.tugas_akhir_farmasi.model.JenisMedicalSuppliesModel;
import com.apap.tugas_akhir_farmasi.repository.JenisMedicalSuppliesDb;
import com.apap.tugas_akhir_farmasi.service.service_interface.JenisMedicalSupplies;


@Service
@Transactional
public class JenisMedicalSuppliesImpl implements JenisMedicalSupplies {
	@Autowired
	private JenisMedicalSuppliesDb jenisMedicalSuppliesDb;
	
	@Override
	public List getListJenisMedicalSupplies() {
		List<JenisMedicalSuppliesModel> listJenisMedicalSupplies = jenisMedicalSuppliesDb.findAll();
		return listJenisMedicalSupplies;
		
	}
}
