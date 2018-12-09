package com.apap.tugas_akhir_farmasi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apap.tugas_akhir_farmasi.model.FlagUrgentModel;

@Repository
public interface FlagUrgentDb extends JpaRepository<FlagUrgentModel,Integer> {
    FlagUrgentModel findById(int id);

	FlagUrgentModel findByFlag(short flag);
}
