package com.apap.tugas_akhir_farmasi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apap.tugas_akhir_farmasi.model.MedicalSuppliesModel;
import com.apap.tugas_akhir_farmasi.model.PerencanaanModel;

@Repository
public interface PerencanaanDb extends JpaRepository<PerencanaanModel,Long> {
    PerencanaanModel findById(long id);
    List<PerencanaanModel> findPerencanaanModelByMedicalSupplies(MedicalSuppliesModel medicalSuppliesModel);
}
