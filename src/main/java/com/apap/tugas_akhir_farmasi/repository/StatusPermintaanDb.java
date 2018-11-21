package com.apap.tugas_akhir_farmasi.repository;

import com.apap.tugas_akhir_farmasi.model.StatusPermintaanModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusPermintaanDb extends JpaRepository<StatusPermintaanModel,Integer> {
    StatusPermintaanModel findById(int id);
    StatusPermintaanModel findByNama(String nama);
}
