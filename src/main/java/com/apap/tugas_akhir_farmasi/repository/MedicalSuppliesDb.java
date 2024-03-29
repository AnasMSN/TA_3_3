package com.apap.tugas_akhir_farmasi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apap.tugas_akhir_farmasi.model.JenisMedicalSuppliesModel;
import com.apap.tugas_akhir_farmasi.model.MedicalSuppliesModel;

@Repository
public interface MedicalSuppliesDb extends JpaRepository<MedicalSuppliesModel,Long> {
    MedicalSuppliesModel findById(long id);

	List<MedicalSuppliesModel> findByJenisMedicalSuppliesModel(JenisMedicalSuppliesModel jenisUrg);

    MedicalSuppliesModel findByNama(String nama);
}
