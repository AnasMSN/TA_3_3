package com.apap.tugas_akhir_farmasi.service_implementation;
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
    public MedicalSuppliesModel addMedicalSupplies(MedicalSuppliesModel medicalSupplies) {
        return medicalSuppliesDb.save(medicalSupplies);
    }
	
	@Override
	public MedicalSuppliesModel getDetailMedicalSuppliesById(long id) {
		return medicalSuppliesDb.findById(id);
	}
	
	@Override
	public void updateMedicalSupplies(MedicalSuppliesModel newMedicalSupplies, long id) {
		MedicalSuppliesModel oldMedicalSupplies= this.getDetailMedicalSuppliesById(id);
		oldMedicalSupplies.setNama(newMedicalSupplies.getNama());
		oldMedicalSupplies.setJumlah(newMedicalSupplies.getJumlah());
		oldMedicalSupplies.setPrice(newMedicalSupplies.getPrice());
		oldMedicalSupplies.setDeskripsi(newMedicalSupplies.getDeskripsi());
		oldMedicalSupplies.setJenisMedicalSuppliesModel(newMedicalSupplies.getJenisMedicalSuppliesModel());
	}
}
