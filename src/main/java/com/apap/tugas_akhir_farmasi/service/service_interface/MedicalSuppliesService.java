package com.apap.tugas_akhir_farmasi.service.service_interface;

import java.util.List;

import com.apap.tugas_akhir_farmasi.model.MedicalSuppliesModel;

public interface MedicalSuppliesService {

	List<MedicalSuppliesModel> findAll();

	List<MedicalSuppliesModel> findByUrgent();
}
