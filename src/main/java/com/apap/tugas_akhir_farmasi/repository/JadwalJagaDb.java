package com.apap.tugas_akhir_farmasi.repository;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apap.tugas_akhir_farmasi.model.JadwalJagaModel;

@Repository
public interface JadwalJagaDb extends JpaRepository<JadwalJagaModel,Long> {
    JadwalJagaModel findById(long id);
    List<JadwalJagaModel> findByidStaff(int idStaff);
    JadwalJagaModel findByTanggalAndWaktuMulaiLessThanEqualAndWaktuSelesaiGreaterThanEqual(Date tanggal, Time waktuMulai, Time waktuSelesai);
}
