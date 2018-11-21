package com.apap.tugas_akhir_farmasi.repository;

import com.apap.tugas_akhir_farmasi.model.MedicalSuppliesModel;
import com.apap.tugas_akhir_farmasi.model.PermintaanModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PermintaanDb extends JpaRepository<PermintaanModel,Long> {
    PermintaanModel findById(long id);
    List<PermintaanModel> findAllByMedicalSuppliesModel(MedicalSuppliesModel medicalSuppliesModel);

}
