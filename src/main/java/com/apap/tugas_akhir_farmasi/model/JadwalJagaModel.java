package com.apap.tugas_akhir_farmasi.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Time;
import java.util.List;

@Entity
@Table(name = "jadwal_jaga")
public class JadwalJagaModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Column(name = "tanggal",nullable = false)
    private Time tanggal;

    @NotNull
    @Column(name = "waktu_mulai",nullable = false)
    private Time waktuMulai;

    @NotNull
    @Column(name = "wkatu_selesai",nullable = false)
    private Time waktuSelesai;


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_staff",referencedColumnName = "id",nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<StaffModel> staffModels;
}
