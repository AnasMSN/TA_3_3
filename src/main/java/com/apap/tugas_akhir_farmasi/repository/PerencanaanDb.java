package com.apap.tugas_akhir_farmasi.repository;

import com.apap.tugas_akhir_farmasi.model.MedicalSuppliesModel;
import com.apap.tugas_akhir_farmasi.model.PerencanaanModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PerencanaanDb extends JpaRepository<PerencanaanModel,Long> {
    PerencanaanModel findById(long id);
    List<PerencanaanModel> findPerencanaanModelByMedicalSupplies(MedicalSuppliesModel medicalSuppliesModel);
}
