package com.apap.tugas_akhir_farmasi.repository;

import com.apap.tugas_akhir_farmasi.model.FlagUrgentModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlagUrgentDb extends JpaRepository<FlagUrgentModel,Integer> {
    FlagUrgentModel findById(int id);

	FlagUrgentModel findByFlag(short flag);
}
