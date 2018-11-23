package com.apap.tugas_akhir_farmasi.repository;

import com.apap.tugas_akhir_farmasi.model.MedicalSuppliesModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicalSuppliesDb extends JpaRepository<MedicalSuppliesModel,Long> {
    MedicalSuppliesModel findById(long id);
    MedicalSuppliesModel findByNama(String nama);
}
