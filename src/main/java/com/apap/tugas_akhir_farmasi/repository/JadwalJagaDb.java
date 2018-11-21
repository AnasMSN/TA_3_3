package com.apap.tugas_akhir_farmasi.repository;

import com.apap.tugas_akhir_farmasi.model.JadwalJagaModel;
import com.apap.tugas_akhir_farmasi.model.StaffModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JadwalJagaDb extends JpaRepository<JadwalJagaModel,Long> {
    JadwalJagaModel findById(long id);
    List<JadwalJagaModel> findByStaffModels(StaffModel staffModel);
}
