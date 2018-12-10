package com.apap.tugas_akhir_farmasi.service.service_interface;

import java.io.IOException;
import java.util.List;

import com.apap.tugas_akhir_farmasi.model.MedicalSuppliesModel;

public interface MedicalSuppliesService {

	List<MedicalSuppliesModel> findAll();

	List<MedicalSuppliesModel> findByUrgent();
	MedicalSuppliesModel getDetailMedicalSuppliesById(long id);
	MedicalSuppliesModel addMedicalSupplies(MedicalSuppliesModel medicalSupplies);
	void updateMedicalSupplies(MedicalSuppliesModel newMedicalSupplies, long id);
	MedicalSuppliesModel getMedicalSuppliesDetailsByNama(String nama);
	List<MedicalSuppliesModel> getAll();
	MedicalSuppliesModel getMedicalSuppliesById(long id);
	int addMedicalSuppliesToRawatJalan(MedicalSuppliesModel medSupp, String nama, int jumlah) throws IOException;
}
