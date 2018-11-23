package com.apap.tugas_akhir_farmasi.service.service_interface;

import com.apap.tugas_akhir_farmasi.model.MedicalSuppliesModel;

public interface MedicalSuppliesService {
	MedicalSuppliesModel getDetailMedicalSuppliesById(long id);
	MedicalSuppliesModel addMedicalSupplies(MedicalSuppliesModel medicalSupplies);
	void updateMedicalSupplies(MedicalSuppliesModel newMedicalSupplies, long id);
	

	MedicalSuppliesModel getMedicalSuppliesDetailsByNama(String nama);

}
