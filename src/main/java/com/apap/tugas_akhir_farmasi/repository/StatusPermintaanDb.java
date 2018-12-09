package com.apap.tugas_akhir_farmasi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apap.tugas_akhir_farmasi.model.StatusPermintaanModel;

@Repository
public interface StatusPermintaanDb extends JpaRepository<StatusPermintaanModel,Integer> {
    StatusPermintaanModel findById(int id);
    StatusPermintaanModel findByNama(String nama);
}
