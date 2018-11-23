package com.apap.tugas_akhir_farmasi.repository;

import com.apap.tugas_akhir_farmasi.model.FlagUrgentModel;
import com.apap.tugas_akhir_farmasi.model.JenisMedicalSuppliesModel;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JenisMedicalSuppliesDb extends JpaRepository<JenisMedicalSuppliesModel,Long> {
    JenisMedicalSuppliesModel findById(long id);

	List<JenisMedicalSuppliesModel> findByFlagUrgentModel(FlagUrgentModel urgent);
}
