package com.apap.tugas_akhir_farmasi.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Time;

public class PermintaanModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Column(name = "tanggal",nullable = false)
    private Time tanggal;

    @NotNull
    @Column(name = "id_pasien",nullable = false)
    private int idPasien;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_jadwal",referencedColumnName ="id",nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private JadwalJagaModel jadwalJagaModel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_status_permintaan",referencedColumnName = "id",nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private StatusPermintaanModel statusPermintaanModel;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_medical_supplies",referencedColumnName = "id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private MedicalSuppliesModel medicalSuppliesModel;
}
