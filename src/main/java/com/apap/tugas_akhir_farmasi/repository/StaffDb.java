package com.apap.tugas_akhir_farmasi.repository;

import com.apap.tugas_akhir_farmasi.model.StaffModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StaffDb extends JpaRepository<StaffModel,Integer> {
    StaffModel findById(int id);
    List<StaffModel> findAllByNama(String nama);
}
